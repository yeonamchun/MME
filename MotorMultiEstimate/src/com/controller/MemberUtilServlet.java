package com.controller;

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
import com.filter.CallBackBoolean;
import com.service.MemberService;
import com.service.MgmtService;
import com.service.SellerService;
import com.util.SendMail;
import com.yeoutil.Util;

@WebServlet("/MemberUtil")
public class MemberUtilServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		new Util("MemberUtilServlet");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String opt = request.getParameter("opt");
		MemberService uService = new MemberService();
		SellerService sService = new SellerService();
		MgmtService mService = new MgmtService();
		
		SendMail mail = new SendMail(getServletContext().getInitParameter("emailID"), getServletContext().getInitParameter("emailPASS"));
		
		
		if(opt != null)
		{
			switch (opt)
			{
    			case "1":
    				try
    				{
    					String user_id = request.getParameter("user_id");
        				
        				UserDTO udto = uService.checkUserid(user_id);
        				if(udto != null )
        				{
        					out.print("true");
        				}
        				else
        				{
        					out.print("false");
        				}
    				}
    				catch(Exception ex001)
    				{	
    					Util.log.error(ex001.toString());
    				}
    				break;
    				
    			case "2":
    				try
    				{
    					String user_alias = request.getParameter("user_alias");
        				
        				UserDTO udto = uService.checkUseralias(user_alias);
        				if(udto != null )
        				{
        					out.print("true");
        				}
        				else
        				{
        					out.print("false");
        				}
    				}
    				catch(Exception ex001)
    				{	
    				}
    				break;
    				
    			case "3":
    				try
    				{
    					String seller_num = request.getParameter("seller_num");
        				
        				SellerDTO sdto = sService.checkSellernum(seller_num);
        				if(sdto != null )
        				{
        					out.print("true");
        				}
        				else
        				{
        					out.print("false");
        				}
    				}
    				catch(Exception ex001)
    				{	
    					
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
							
							mail.send(
								"MME 관리자", 
								uDTO.getUser_name()+"님 방문을 환영 합니다.", 
								"yeo0419@gmail.com", 
								"안녕 하세요 고객님 즐거운 하우 되세요!", 
								(boolean result)->{
									if(result)
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
    								out.print("1");
    							}
    							else
    							{
    								out.print("0");
    							}
    						}
    						else if(logintype.equals("2") && uDTO.getManager_code().length() > 0)
    						{
    							List<MgmtDTO> mDTO = mService.getMgmtpage(uDTO.getManager_code());
    							if(mDTO != null)
								{
    								session.setAttribute("mDTO", mDTO);
    								out.print("2");
								}
    							else
    							{
    								out.print("0");
    							}
    						}
    						else
    						{
    							out.print("0");
    						}
        				}
        				else
        				{
        					out.print("-1");
        				}
    				}
    				catch(Exception ex001)
    				{	
    				}
    				break;		
    				
    			case "200":
    				try
    				{
    					HttpSession session = request.getSession();
    					try
    					{
    						session.invalidate();
    						out.print("true");
    					}
    					catch(Exception ex001)
    					{
    						out.print("false");
    					}
    				}
    				catch(Exception ex001)
    				{	
    					Util.log.error(ex001.toString());
    				}
    				break;
			}
		}
		else
		{
			out.print("Error");
		}
		
		out.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
