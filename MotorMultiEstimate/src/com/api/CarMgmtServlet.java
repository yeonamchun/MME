package com.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.itf.OtherELE;
import com.util.StreamParam;

@WebServlet("/CarMgmt")
public class CarMgmtServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		JSONObject jsonReturn = new JSONObject() ;
		
		try
		{
			JSONObject jsonParam = StreamParam.getParam(request);
			
			switch(jsonParam.get("opt").toString())
			{
				case "select":
					
					try 
					{
						jsonReturn = jsonParam;
					} 
					catch(Exception ex001){}
					
					break;
				
			}
		}
		catch(Exception ex001){}
		
		if(jsonReturn != null)
		{
			out.println(jsonReturn.toString());
		}
		else
		{
			out.println("");
		}
	}
	
	
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
				case "insert":
					
					try 
					{
						jsonReturn = jsonParam;
					} 
					catch(Exception ex001){}
					
					break;
				
			}
		}
		catch(Exception ex001){}
		
		if(jsonReturn != null)
		{
			out.println(jsonReturn.toString());
		}
		else
		{
			out.println("");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
				case "update":
					
					try 
					{
						jsonReturn = jsonParam;
					} 
					catch(Exception ex001){}
					
					break;
				
			}
		}
		catch(Exception ex001){}
		
		if(jsonReturn != null)
		{
			out.println(jsonReturn.toString());
		}
		else
		{
			out.println("");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
				case "delete":
					
					try 
					{
						jsonReturn = jsonParam;
					} 
					catch(Exception ex001){}
					
					break;
				
			}
		}
		catch(Exception ex001){}
		
		if(jsonReturn != null)
		{
			out.println(jsonReturn.toString());
		}
		else
		{
			out.println("");
		}
	}

}




