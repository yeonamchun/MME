package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.itf.CarELE;
import com.itf.OtherELE;
import com.service.MemberService;
import com.service.SellerService;

@WebServlet("/MemberJoin")
public class MemberJoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/*** 현재 거주 지역 */
		request.setAttribute("area_info", OtherELE.getJson(OtherELE.AREA_ELE));
		/*** 자동차 선호 브랜드 */
		request.setAttribute("brand_info", OtherELE.getJson(CarELE.BRAND_ELE));
		/*** 견적 가능 차량 종류 */
		request.setAttribute("product_info", OtherELE.getJson(CarELE.NEW_CAR_ELE));
		
		String nextPage = "";
		
        String user_id = request.getParameter("user_id");
        String user_pw = request.getParameter("user_pw");
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
        
        String seller_check = request.getParameter("seller_check");
        
        MemberService service = new MemberService();
        
        if(seller_check.equals("0")) // 일반 사용자
        {
        	int checkNum = service.memberJoin(uDTO);
        	if(checkNum > 0)
        	{
        		request.setAttribute("alert", "사용자 저장 완료 ");	
        		request.setAttribute("title", "사용자 페이지");
        		nextPage = "mainpage/memberinfo.jsp";
        	}
        	else
        	{
        		request.setAttribute("alert", "사용자 저장 실패 ");
        		nextPage = "mainpage/main.jsp";
        	}
        }
        else	// 사업자
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
            
            uDTO.setSeller_num(seller_num);
            
            int checkNum = sService.sellerJoin(uDTO, sDTO);
            
            if(checkNum > 0)
        	{
        		request.setAttribute("alert", "Seller 저장 완료 ");	
        		request.setAttribute("title", "Seller 페이지");
        		
        		uDTO.setUser_pw(null);
        		
        		
    			if(seller_check.equals("0")) // 일반 사용자
                {
    				request.setAttribute("uDTO", uDTO);
        		}
        		else	// 사업자
        		{
        			request.setAttribute("uDTO", uDTO);
        			request.setAttribute("sDTO", sDTO);
        		}
        		
        		nextPage = "mainpage/memberinfo.jsp";
        	}
        	else
        	{
        		request.setAttribute("alert", "Seller 저장 실패 ");
        		nextPage = "mainpage/main.jsp";
        	}  
        }
        
        RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
