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

@WebServlet("/MemberJoinUi")
public class MemberJoinUiServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("title", "회원가입");
		
		/**
		 * 현재 거주 지역
		 */
		request.setAttribute("area_info", OtherELE.getJson(OtherELE.AREA_ELE));
		/**
		 * 자동차 선호 브랜드
		 */
		request.setAttribute("brand_info", OtherELE.getJson(CarELE.BRAND_ELE));
		
		/**
		 * 견적 가능 차량 종류
		 */
		request.setAttribute("product_info", OtherELE.getJson(CarELE.NEW_CAR_ELE));
		
		
		RequestDispatcher dis = request.getRequestDispatcher("mainpage/memberjoin.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
