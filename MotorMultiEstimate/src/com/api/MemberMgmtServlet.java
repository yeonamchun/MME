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

import com.controller.MemberInfoServlet;
import com.dto.MgmtDTO;
import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itf.ErrorCode;
import com.service.MemberService;
import com.service.MgmtService;
import com.service.SellerService;
import com.util.AES128;
import com.util.JWT;
import com.util.JsonResult;
import com.util.SendMail;
import com.yeoutil.Util;
	
@WebServlet("/MemberMgmt")
public class MemberMgmtServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util util = new Util(this.getClass());
		AES128 aes = new AES128(getServletContext().getInitParameter("aes128"));
	
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
	
		String opt = request.getParameter("opt");
		
		JsonResult json = new JsonResult();
		
		int loginTimeMin = Integer.parseInt(getServletContext().getInitParameter("sessionTime"));
		 
		if(opt != null)
		{
			switch(opt)
			{
				case "insert": // 사용자 정보 저장
					try 
					{
						String user_id = request.getParameter("user_id").trim();
				        String user_pw = aes.Enc(request.getParameter("user_pw").trim());
				        String user_name = request.getParameter("user_name").trim();
				        String user_alias = request.getParameter("user_alias").trim();
				        String user_mobile1 = request.getParameter("user_mobile1").trim();
				        String user_mobile2 = request.getParameter("user_mobile2").trim();
				        String user_mobile3 = request.getParameter("user_mobile3").trim();
				        String user_address = request.getParameter("user_address").trim();
				        String user_brand = request.getParameter("user_brand").trim();
				        
				        UserDTO uDTO = new UserDTO(user_id, user_pw, user_name, user_alias, 
				        		user_mobile1, 
				        		user_mobile2, 
				        		user_mobile3, 
				        		user_address, 
				        		user_brand);
				        String seller_check = request.getParameter("seller_check").trim();
				        
				        MemberService uService = new MemberService();
				        
				        if(seller_check.equals("0")) // 일반 사용자
				        {
				        	int checkNum = uService.memberJoin(uDTO);
				        	
				        	if(checkNum > 0)	// 사용자 정보 저장 완료
				        	{
				        		json.setError(ErrorCode.EX0540);
								json.setStatus(true);
							}
				        	else	// 사용자 정보 저장 실패
				        	{
				        		json.setError(ErrorCode.EX0541);
								json.setStatus(false);
				        	}
				        }
				        else	if(seller_check.equals("1"))// 사업자
				        {
				        	SellerService sService = new SellerService();
				        	
				        	String seller_num = request.getParameter("seller_num");
				            String seller_name = request.getParameter("seller_name");
				            String seller_post = request.getParameter("seller_post");
				            String seller_address1 = request.getParameter("seller_address1");
				            String seller_address2 = request.getParameter("seller_address2");
				            String seller_product_type = request.getParameter("seller_product_type");
				            
				            SellerDTO sDTO = new SellerDTO(seller_num, 
				            		seller_name, 
				            		seller_post, 
				            		seller_address1, 
				            		seller_address2, 
				            		seller_product_type
				            		);
				            
				            uDTO.setSeller_num(seller_num); // 사업자 코드 를 변경 한다.
				            
				            int checkNum = sService.sellerJoin(uDTO, sDTO);
				            
				            if(checkNum > 0)	// 사업자 정보 저장 완료
				        	{
				            	json.setError(ErrorCode.EX0542);
								json.setStatus(true);
				        	}
				        	else // 사업자 저장 실패
				        	{
				        		json.setError(ErrorCode.EX0543);
								json.setStatus(false);
				        	}  
				        }
				        
					}
					catch(Exception ex001)
					{}
					
					break;
		
				case "update":	// 
					
					try 
					{
						String toknen = request.getParameter("toknen");
						
						if(toknen != null)
						{
							String tempID = JWT.getId(toknen);
							
							if(tempID != null)
							{
								String user_id = tempID;
								String user_pw = aes.Enc(request.getParameter("user_pw"));
								String user_name = request.getParameter("user_name");
								String user_alias = request.getParameter("user_alias");
								String user_mobile1 = request.getParameter("user_mobile1");
								String user_mobile2 = request.getParameter("user_mobile2");
								String user_mobile3 = request.getParameter("user_mobile3");
								String user_address = request.getParameter("user_address");
								String user_brand = request.getParameter("user_brand");
								
								UserDTO uDTO = new UserDTO(user_id, user_pw, user_name, user_alias, 
						        		user_mobile1, 
						        		user_mobile2, 
						        		user_mobile3, 
						        		user_address, 
						        		user_brand);
								
								
								MemberService uService = new MemberService();
								
								String seller_check = request.getParameter("seller_check").trim();
								
								if(seller_check.equals("0"))
								{
									int checkNum = uService.memberUpdate(uDTO);
									
									if(checkNum > 0 ) // 사용자 수정 완료
									{
										json.setError(ErrorCode.EX0544);
										json.setStatus(true);
									}
									else // 사용자 수정 실패
									{
										json.setError(ErrorCode.EX0545);
										json.setStatus(false);
									}
								}
								else 	if(seller_check.equals("1"))// 사업자
								{
									String seller_num = request.getParameter("seller_num");
						            String seller_name = request.getParameter("seller_name");
						            String seller_post = request.getParameter("seller_post");
						            String seller_address1 = request.getParameter("seller_address1");
						            String seller_address2 = request.getParameter("seller_address2");
						            String seller_product_type = request.getParameter("seller_product_type");
						            
						            SellerDTO sDTO = new SellerDTO(
						            		seller_num, 
						            		seller_name, 
						            		seller_post, 
						            		seller_address1, 
						            		seller_address2, 
						            		seller_product_type
					            	);
						            
						            SellerService sService = new SellerService();
						            
						            int checkNum = sService.sellerUpdate(uDTO, sDTO);
						            
						            if(checkNum > 0 )
						            {
						            	json.setError(ErrorCode.EX0546);
										json.setStatus(true);
						            }
						            else
					            	{
						            	json.setError(ErrorCode.EX0547);
										json.setStatus(false);
					            	}
								}
							}
						}
					}
					catch(Exception ex001)
					{}
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
