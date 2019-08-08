package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dto.MgmtDTO;
import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itf.CarELE;
import com.itf.ErrorCode;
import com.itf.OtherELE;
import com.service.MemberService;
import com.service.MgmtService;
import com.service.SellerService;
import com.util.AES128;
import com.util.JWT;
import com.util.JsonResult;
import com.util.SendMail;
import com.yeoutil.Util;
	
@WebServlet("/Member")
public class MemberServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util util = new Util(this.getClass());
		AES128 aes = new AES128(getServletContext().getInitParameter("aes128").trim());
	
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberService uService = new MemberService();
		SellerService sService = new SellerService();
		MgmtService mService = new MgmtService();
		
		String opt = request.getParameter("opt");
		
		JsonResult json = new JsonResult();
		
		boolean loginCheck =(Boolean)request.getAttribute("mmtLogin");
		 
		if(opt != null)
		{
			int loginTimeMin = Integer.parseInt(getServletContext().getInitParameter("sessionTime"));
			
			switch(opt)
			{
				
				case "loginCheck": // 사용자 아이디 session 체크 및 새로운 토큰 발급
				
					try 
					{
						String toknen = request.getParameter("toknen");
						
						if(toknen != null)
						{
							String  tempID = JWT.getId(toknen);
							ServletContext ctx = getServletContext();
							boolean userLogin = (Boolean)ctx.getAttribute(tempID);
							
							if(tempID != null &&  userLogin)
							{
								String toknenNew = JWT.updateExpiration(toknen);
								
								json.setError(ErrorCode.EX0200);
								json.addDataJson( JWT.getJson(toknen) );
								json.setStatus(true);
								json.setToken(toknenNew);
							}
							else
							{
								json.setError(ErrorCode.EX0560);
								json.setStatus(false);
							}
						}
						else
						{
							json.setInit();
							json.setError(ErrorCode.EX0561);
							json.setStatus(false);
						}
					} 
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
						json.setStatus(false);
					}
					
					break;
				
				case "1":	// 아이디 중복 체크
					
					try 
					{
						String user_id = request.getParameter("user_id");
						
						if(user_id != null)
						{
							UserDTO udto = uService.checkUserid(user_id);
							json.setError(ErrorCode.EX0200);
							
		    				if(udto != null )
		    				{
		    					json.setStatus(true);
		    				}
		    				else
		    				{
		    					json.setStatus(false);
		    				}
						}
						else
						{
							json.setError(ErrorCode.EX0001);
	    					json.setStatus(false);
						}
					} 
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
    					json.setStatus(false);
    				}
					
					break;
					
				case "2":	// 사용자 닉네임 중복 체크
					
					try 
					{
						String user_alias = request.getParameter("user_alias");
						
	    				if(user_alias != null)
						{
							UserDTO udto = uService.checkUseralias(user_alias);
							json.setError(ErrorCode.EX0200);
		    				if(udto != null )
		    				{
		    					json.setStatus(true);
		    				}
		    				else
		    				{
		    					json.setStatus(false);
		    				}
						}
						else
						{
							json.setError(ErrorCode.EX0001);
	    					json.setStatus(false);
						}
					}
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
    					json.setStatus(false);
    				}
    				
					break;
			
				case "3": // 판매자 번호 중복 체크
					
					try
					{
						String seller_num = request.getParameter("seller_num");
						
						if(seller_num != null)
						{
							SellerDTO sdto = sService.checkSellernum(seller_num);
							json.setError(ErrorCode.EX0200);
							if(sdto != null )
							{
								json.setStatus(true);
							}
							else
							{
								json.setStatus(false);
							}
						}
						else
						{
							json.setError(ErrorCode.EX0001);
	    					json.setStatus(false);
						}
					}
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
    					json.setStatus(false);
    				}
					
					break;
					
				case "login": // 사용자 로그인
					
					try
					{
						JWT jwt = new JWT("MMT", "login", loginTimeMin);
						ServletContext ctx = getServletContext();
						
						String logintype = request.getParameter("login_type");
						String userid = request.getParameter("user_id");
						String userpw = aes.Enc(request.getParameter("user_pw"));
						util.log("userpw >>>>>>>>>>>>"+userpw);
						
						HashMap<String, String> param = new HashMap<String, String>();
    					param.put("user_id", userid);
    					param.put("user_pw", userpw);
    					
    					UserDTO uDTO = uService.login(param);
    					if(uDTO != null )
        				{
    						new SendMail(
								getServletContext().getInitParameter("emailID"), 
								getServletContext().getInitParameter("emailPASS")
							).send(
							"MME 관리자", 
							uDTO.getUser_name()+"님 방문을 환영 합니다.", 
							"yeo0419@gmail.com", 
							"안녕 하세요 고객님 즐거운 하루 되세요!", 
							(reultBoolean)->{
								if(reultBoolean)
								{
									Util.log.info("메일 전송 완료");
								}
								else
								{
									Util.log.info("메일 전송 실패");
								}
							});
    						
    						if(logintype.equals("1") && uDTO.getSeller_num().length() > 0)
    						{
								SellerDTO sDTO = sService.checkSellernum(uDTO.getSeller_num() );
								json.setError(ErrorCode.EX0200);
								json.setStatus(true);
		    					jwt.addParam("user_id", uDTO.getUser_id());
		    					ctx.setAttribute(uDTO.getUser_id(), true);
		    					jwt.addParam("user_ip", Inet4Address.getLocalHost().getHostAddress());
		    					
								if(sDTO != null)
    							{	
    		    					jwt.addParam("seller_num", uDTO.getSeller_num());
    								jwt.addParam("user_opt", "1");
    								
    								String tempTok = jwt.encrypt(uDTO.getUser_id(), loginTimeMin);
    								json.addDataJson(JWT.getJson(tempTok));
    								json.setToken(tempTok);
    							}
    							else
    							{
    								jwt.addParam("user_opt", "0");
    		    					
    		    					String tempTok = jwt.encrypt(uDTO.getUser_id(), loginTimeMin);
    								json.addDataJson(JWT.getJson(tempTok));
    								json.setToken(tempTok);
    							}
    						}
    						else if(logintype.equals("2") && uDTO.getManager_code().length() > 0)
    						{
    							List<MgmtDTO> mDTO = mService.getMgmtpage(uDTO.getManager_code());
    							
    							json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
		    					jwt.addParam("user_id", uDTO.getUser_id());
		    					ctx.setAttribute(uDTO.getUser_id(), true);
		    					jwt.addParam("user_ip", Inet4Address.getLocalHost().getHostAddress());
		    					
    							if(mDTO != null)
								{
    								jwt.addParam("manager_code", uDTO.getManager_code());
    		    					jwt.addParam("user_opt", "2");
    								
    		    					String tempTok = jwt.encrypt(uDTO.getUser_id(), loginTimeMin);
    								json.addDataJson(JWT.getJson(tempTok));
    								json.setToken(tempTok);
    							}
    							else
    							{
    								jwt.addParam("user_opt", "0");
    								
    		    					String tempTok = jwt.encrypt(uDTO.getUser_id(), loginTimeMin);
    								json.addDataJson(JWT.getJson(tempTok));
    								json.setToken(tempTok);
    							}
    						}
    						else
    						{
    							json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
		    					jwt.addParam("user_id", uDTO.getUser_id());
		    					ctx.setAttribute(uDTO.getUser_id(), true);
		    					jwt.addParam("user_ip", Inet4Address.getLocalHost().getHostAddress());
		    					jwt.addParam("user_opt", "0");
								
		    					String tempTok = jwt.encrypt(uDTO.getUser_id(), loginTimeMin);
								json.addDataJson(JWT.getJson(tempTok));
								json.setToken(tempTok);
							}
        				}
        				else
        				{
        					json.setError(ErrorCode.EX0202);
	    					json.setStatus(false);
	    				}
					}
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
    					json.setStatus(false);
    				}
					break;
					
				case "logout": // 로그 아웃
					
					String toknen = request.getParameter("toknen");
					
					if(toknen != null)
					{
						String tempID = JWT.getId(toknen);
						
						if(tempID != null)
						{
							json.setError(ErrorCode.EX0201);
							json.setStatus(true);
							
							request.setAttribute("mmtLogin", false);
							util.log("request.getAttribute(\"mmtLogin\")  false");
							
							ServletContext ctx = getServletContext();
							ctx.setAttribute(tempID, false);
							
						}
						else
						{
							json.setError(ErrorCode.EX0560);
							json.setStatus(false);
						}
					}
					else
					{
						json.setInit();
						json.setError(ErrorCode.EX0561);
						json.setStatus(false);
					}
					break;
			}
		}
		else
		{
			json.setError(ErrorCode.EX0100);
			json.setStatus(false);
		}
		out.print(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
