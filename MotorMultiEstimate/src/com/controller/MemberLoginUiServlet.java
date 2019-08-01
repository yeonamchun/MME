package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itf.OtherELE;
import com.util.SessionMgmt;

@WebServlet("/MemberLoginUi")
public class MemberLoginUiServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nextPage = "mainpage/memberlogin.jsp";
		
		SessionMgmt se = new SessionMgmt(request);
		
		if(se.getUdto() == null)
		{
			request.setAttribute("title", "로그인");
			request.setAttribute("login_type", OtherELE.getJson(OtherELE.LOGIN_TYPE));
			nextPage = "mainpage/memberlogin.jsp";
		}
		else
		{
			se.setUdto();
			nextPage = "MemberInfoUi";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
