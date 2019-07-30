package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName");
		String userJumin = request.getParameter("userJumin");
		String[] userhabby = request.getParameterValues("userhabby");
		String seller_check = request.getParameter("seller_check");
		
		String[] temp1 = userJumin.split("-");
		String temp2 = temp1[1].substring(0,1);
		if(temp2.equals("1"))
		{
			
		}
		
		
		out.println("<html><body>");
		out.println("이름은 : "+userName);
		out.println("<br>");
		
		if(temp2.equals("1"))
		{
			out.println("성별은 : 남자");
		}
		else
		{
			out.println("성별은 : 여자");
		}
		out.println("<br>");
		
		out.println("취미는 : ");
		for(String t : userhabby)
		{
			out.println(t+" ");
		}
		
		out.println("<br>");
		
		out.println("결혼 상태는 "+seller_check+"입니다.");
		out.println("</body></htm0l>");
		
		out.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
