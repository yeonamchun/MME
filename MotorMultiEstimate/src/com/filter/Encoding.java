package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.itf.ErrorCode;
import com.util.JWT;
import com.yeoutil.Util;

/**
 * Servlet Filter implementation class Encoding
 */
public class Encoding implements Filter {
   
	public void destroy()
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		Util util = new Util(this.getClass());
		
		request.setCharacterEncoding("utf-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse)response ;
	    res.setHeader("Access-Control-Allow-Origin", "*");	
	    
	    String toknen = req.getParameter("toknen");
	    
	    if(toknen != null)
		{
	    	try
	    	{
	    		JSONObject json = JWT.getJson(toknen);
	    		
	    		if(json != null)
				{
					request.setAttribute("mmtLogin", true);
					util.log("request.getAttribute(\"mmtLogin\")  true");
				}
				else
				{
					request.setAttribute("mmtLogin", false);
					util.log("request.getAttribute(\"mmtLogin\")  false");
				}
	    	}
	    	catch(Exception ex001) {}
		}
	    else
	    {
	    	request.setAttribute("mmtLogin", false);
	    }
	    
	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException
	{	
	}

}
