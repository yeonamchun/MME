package com.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.service.MemberService;
import com.service.SellerService;
import com.yeoutil.Util;

@WebServlet("/Member")
public class MemberServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Util util = new Util(this.getClass());
		
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberService uService = new MemberService();
		SellerService sService = new SellerService();
		
		String opt = request.getParameter("opt");
		
		
		if(opt != null)
		{
			switch(opt)
			{
				case "1":
					String user_id = request.getParameter("user_id");
					
					
					if(user_id != null)
					{
						UserDTO udto = uService.checkUserid(user_id);
	    				
	    				if(udto != null )
	    				{
	    					
	    					result.put("error_code", "EX0000");
	    					result.put("error_msg", "없음");
	    					result.put("result_date", true);
	    				}
	    				else
	    				{
	    					result.put("error_code", "EX0000");
	    					result.put("error_msg", "없음");
	    					result.put("result_date", false);
	    				}
					}
					else
					{
						result.put("error_code", "EX0001");
						result.put("error_msg", "param user_id 에러");
						result.put("result_date", null);
					}
					break;
				case "2":
					
    				String user_alias = request.getParameter("user_alias");
			
    				if(user_alias != null)
					{
						UserDTO udto = uService.checkUseralias(user_alias);
	    				
	    				if(udto != null )
	    				{
	    					result.put("error_code", "EX0000");
	    					result.put("error_msg", "없음");
	    					result.put("result_date", true);
	    				}
	    				else
	    				{
	    					result.put("error_code", "EX0000");
	    					result.put("error_msg", "없음");
	    					result.put("result_date", false);
	    				}
					}
					else
					{
						result.put("error_code", "EX0001");
						result.put("error_msg", "param user_alias 에러");
						result.put("result_date", null);
					}
					break;
			
			
				case "3":
					
					String seller_num = request.getParameter("seller_num");
			
					if(seller_num != null)
					{
						SellerDTO sdto = sService.checkSellernum(seller_num);
						
						if(sdto != null )
						{
							result.put("error_code", "EX0000");
							result.put("error_msg", "없음");
							result.put("result_date", true);
						}
						else
						{
							result.put("error_code", "EX0000");
							result.put("error_msg", "없음");
							result.put("result_date", false);
						}
					}
					else
					{
						result.put("error_code", "EX0001");
						result.put("error_msg", "param seller_num 에러");
						result.put("result_date", null);
					}
					break;
			}
			
		}
		else
		{
			result.put("error_code", "EX0100");
			result.put("error_msg", "opt 코드 에러");
			result.put("result_date", null);
		}
		
		out.print(result.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
