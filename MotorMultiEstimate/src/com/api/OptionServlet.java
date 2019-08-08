package com.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.itf.CarELE;
import com.itf.OtherELE;
import com.util.StreamParam;

@WebServlet("/Option")
public class OptionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		JSONObject jsonReturn = null;
		
		try
		{
			JSONObject jsonParam = StreamParam.getJson(request);
			
			switch(jsonParam.get("opt").toString())
			{
				case "area_ele":	// 사용자 지역
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(OtherELE.AREA_ELE);
					} 
					catch(Exception ex001){}
					
					break;
				case "brand_ele":	// 자동차 브랜드
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.BRAND_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				case "new_car_ele":	// 자동차 판매
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.NEW_CAR_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
					
				case "made_in_type_ele":	// 자동차  국가
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.MADE_IN_TYPE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
					
				case "engine_ele":	// 엔진종류 
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.ENGINE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				
				case "type_ele":	// 자동차 종류  
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.TYPE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
					
				case "size_ele":	// 자동차 크기 소형  
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.SIZE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				
				case "cylinder_ele":	// 실린더 갯수  
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.CYLINDER_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
					
				case "gear_type_ele":	// 기어 타입  
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.GEAR_TYPE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				
				case "gear_count_ele":	// 기어단수 
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.GEAR_COUNT_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
					
				case "door_ele":	// 도어 갯수 
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.DOOR_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				
				case "inhalation_type_ele":	// 과급방식 
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.INHALATION_TYPE_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
				
				case "driven_system_ele":	// 바퀴 굴림 종류
					
					try 
					{
						jsonReturn = OtherELE.getJsonObj(CarELE.DRIVEN_SYSTEM_ELE);
					} 
					catch(Exception ex001){}
					
					break;	
			}
		} 
		catch (Exception e) 
		{
		}
		out.print(jsonReturn.toString());
	}
}
