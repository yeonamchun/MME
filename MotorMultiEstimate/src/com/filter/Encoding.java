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
	    
	    HttpSession session = req.getSession(false);
	    
	    if ( session == null || session.getAttribute("uDTO") == null )
	    {
	    	
	    	util.log(">>>>>>>>>> [ "+req.getServletPath()+" ] Login  Session LOGOUT <<<<<<<<<<");
	    }
	    else
	    {
	    	util.log(">>>>>>>>>> [ "+req.getServletPath()+" ] Login  Session LOGIN <<<<<<<<<< "+session.getAttribute("uDTO").toString());
	    }
	    
	    chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException
	{
		
	}

}
