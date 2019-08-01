package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.UserDTO;
import com.itf.CarELE;
import com.itf.OtherELE;
import com.util.SessionMgmt;

@WebServlet("/MemberInfoUi")
public class MemberInfoUiServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		SessionMgmt se = new SessionMgmt(request);
		
		String nextPath = "mainpage/memberinfo.jsp";
		if(se.getUdto() == null)
		{
			nextPath = "MemberLoginUi";
		}
		else
		{
			se.setUdto();
			request.setAttribute("title", "마이페이지");
			request.setAttribute("area_info", OtherELE.getJson(OtherELE.AREA_ELE));
			request.setAttribute("brand_info", OtherELE.getJson(CarELE.BRAND_ELE));
			request.setAttribute("product_info", OtherELE.getJson(CarELE.NEW_CAR_ELE));
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPath);
		dis.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
