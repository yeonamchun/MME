package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itf.OtherELE;

@WebServlet("/MemberLoginUi")
public class MemberLoginUiServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("title", "로그인");
		
		request.setAttribute("login_type", OtherELE.getJson(OtherELE.LOGIN_TYPE));
		
		RequestDispatcher dis = request.getRequestDispatcher("mainpage/memberlogin.jsp");
		dis.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
