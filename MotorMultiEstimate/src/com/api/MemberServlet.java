package com.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dto.MgmtDTO;
import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.itf.ErrorCode;
import com.itf.CallBackBoolean;
import com.service.MemberService;
import com.service.MgmtService;
import com.service.SellerService;
import com.util.JWT;
import com.util.JsonResult;
import com.util.SendMail;
import com.yeoutil.Util;

import io.jsonwebtoken.Claims;

@WebServlet("/Member")
public class MemberServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util util = new Util(this.getClass());
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberService uService = new MemberService();
		SellerService sService = new SellerService();
		MgmtService mService = new MgmtService();
		
		String opt = request.getParameter("opt");
		
		JsonResult json = new JsonResult();
		
		if(opt != null)
		{
			switch(opt)
			{
				case "login":
				
					try 
					{
						String toknen = request.getParameter("toknen");
						
    					json.setError(ErrorCode.EX0200);
    					json.setStatus(JWT.getSession(toknen));
    					
					} 
					catch(Exception ex001)
					{
						util.log(ex001);
						json.setInit();
						json.setError(ErrorCode.EX0900);
						json.setStatus(false);
					}
					
					break;
				case "1":
					
					try 
					{
						String user_id = request.getParameter("user_id");
						
						if(user_id != null)
						{
							UserDTO udto = uService.checkUserid(user_id);
		    				
		    				if(udto != null )
		    				{
		    					json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
		    				}
		    				else
		    				{
		    					json.setError(ErrorCode.EX0200);
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
					
				case "2":
					
					try 
					{
						String user_alias = request.getParameter("user_alias");
						
	    				if(user_alias != null)
						{
							UserDTO udto = uService.checkUseralias(user_alias);
		    				
		    				if(udto != null )
		    				{
		    					json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
		    				
		    				}
		    				else
		    				{
		    					json.setError(ErrorCode.EX0200);
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
			
				case "3":
					
					try
					{
						String seller_num = request.getParameter("seller_num");
						
						if(seller_num != null)
						{
							SellerDTO sdto = sService.checkSellernum(seller_num);
							
							if(sdto != null )
							{
								json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
							}
							else
							{
								json.setError(ErrorCode.EX0200);
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
					
				case "100":
					
					try
					{
						String logintype = request.getParameter("login_type");
						String userid = request.getParameter("user_id");
						String userpw = request.getParameter("user_pw");
						
						HashMap<String, String> param = new HashMap<String, String>();
    					param.put("user_id", userid);
    					param.put("user_pw", userpw);
    					
    					UserDTO uDTO = uService.login(param);
    					if(uDTO != null )
        				{
    						HttpSession session = request.getSession();
    						uDTO.setUser_pw(null);
							session.setAttribute("uDTO", uDTO);
							
							SendMail mail = new SendMail(getServletContext().getInitParameter("emailID"), getServletContext().getInitParameter("emailPASS"));
							
							mail.send(
								"MME 관리자", 
								uDTO.getUser_name()+"님 방문을 환영 합니다.", 
								"yeo0419@gmail.com", 
								"안녕 하세요 고객님 즐거운 하우 되세요!", 
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
								if(sDTO != null)
    							{
    								session.setAttribute("sDTO", sDTO);
    								HashMap<String, String> paramMap = new HashMap<String, String>();
    								paramMap.put("seller_num",uDTO.getSeller_num());
    								paramMap.put("opt","1");
    								
    								json.setError(ErrorCode.EX0200);
    		    					json.setStatus(true);
    		    					
    								json.setToken(uDTO.getUser_id(), paramMap);
    								
    							}
    							else
    							{
    								HashMap<String, String> paramMap = new HashMap<String, String>();
    								paramMap.put("opt","0");
    								
    								json.setError(ErrorCode.EX0200);
    		    					json.setStatus(true);
    								json.setToken(uDTO.getUser_id(), paramMap);
    								
    								
    							}
    						}
    						else if(logintype.equals("2") && uDTO.getManager_code().length() > 0)
    						{
    							List<MgmtDTO> mDTO = mService.getMgmtpage(uDTO.getManager_code());
    							if(mDTO != null)
								{
    								
    								HashMap<String, String> paramMap = new HashMap<String, String>();
    								paramMap.put("manager_code",uDTO.getManager_code());
    								paramMap.put("opt","2");
    								
    								json.setError(ErrorCode.EX0200);
    		    					json.setStatus(true);
    								json.setToken(uDTO.getUser_id(), paramMap);
    							}
    							else
    							{
    								HashMap<String, String> paramMap = new HashMap<String, String>();
    								paramMap.put("opt","0");
    								
    								json.setError(ErrorCode.EX0200);
    		    					json.setStatus(true);
    								json.setToken(uDTO.getUser_id(), paramMap);
    								
    							}
    						}
    						else
    						{
    							HashMap<String, String> paramMap = new HashMap<String, String>();
								paramMap.put("opt","0");
								
								json.setError(ErrorCode.EX0200);
		    					json.setStatus(true);
								json.setToken(uDTO.getUser_id(), paramMap);
    						}
        				}
        				else
        				{
        					HashMap<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("opt","-1");
							
							json.setError(ErrorCode.EX0200);
	    					json.setStatus(true);
							json.setToken(uDTO.getUser_id(), paramMap);
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
