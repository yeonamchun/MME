package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.UserDTO;
import com.util.SessionMgmt;

@WebServlet("/MemberLogoutUi")
public class MemberLogoutUiServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		SessionMgmt se = new SessionMgmt(request);
		
		String nextPath = "mainpage/memberlogout.jsp";
		if(se.getUdto() == null)
		{
			nextPath = "MemberLoginUi";
		}
		else
		{
			request.setAttribute("title", "로그아웃");
			
			Date time = new Date();
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			request.setAttribute("sessionEtime", form.format(time));
			time.setTime(se.session.getCreationTime());
			request.setAttribute("sessionStime", form.format(time));
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPath);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
