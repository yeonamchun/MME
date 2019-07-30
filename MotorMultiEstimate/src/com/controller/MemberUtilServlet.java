package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDAO;
import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.service.MemberService;
import com.service.SellerService;

@WebServlet("/MemberUtil")
public class MemberUtilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String opt = request.getParameter("opt");
		MemberService service = new MemberService();
		SellerService sService = new SellerService();
		
		if(opt != null)
		{
			switch (opt)
			{
    			case "1":
    				try
    				{
    					String user_id = request.getParameter("user_id");
        				
        				UserDTO udto = service.checkUserid(user_id);
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
    			case "2":
    				try
    				{
    					String user_alias = request.getParameter("user_alias");
        				
        				UserDTO udto = service.checkUseralias(user_alias);
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
    				
			}
		}
		else
		{
			out.print("Error");
		}
		
		out.flush();
		
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
