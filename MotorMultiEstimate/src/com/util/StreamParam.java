package com.util;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.yeoutil.Util;

public class StreamParam 
{
	static Util util = new Util(StreamParam.class);
	
	/**
	 * 스트림 형태의 데이터를 JSON 으로 리턴
	 * @param request
	 * @return
	 */
	public static final JSONObject getJson(HttpServletRequest request)
	{
		JSONObject result = null;
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		
		try 
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				jb.append(line);
			}
			result = (JSONObject)JSONValue.parse(jb.toString());
			
			util.log("Request Data JSON : "+result);
			
		}
		catch (Exception e) 
		{
			util.log(e);
			result = null;
		}
		
		return result;
	}
	
	/**
	 * 파라메타 형식의 데이터를 JSON 으로 리턴
	 * @param request
	 * @return
	 */
	public static final JSONObject getParam(HttpServletRequest request)
	{
		JSONObject result = new JSONObject();
		
		try 
		{
			Map<String, String[]> paramsMap = request.getParameterMap();

		    for (Iterator<String> it=paramsMap.keySet().iterator(); it.hasNext();) 
		    {
		        String key = it.next();
		        String[] values = paramsMap.get(key);

		        if (values == null || values.length == 0) //파라미터 값이 없는 경우
		        {
		        	result.put(key, null);
		        } 
		        else if (values.length == 1) {//파라미터가 배열이 아닌 경우
		        	result.put(key, values[0]);
		        } 
		        else //파라미터가 배열인 경우 
		        {
		        	JSONArray tempArray = new JSONArray();
		        	for(String value : values)
		        	{
		        		tempArray.add(value);
		        	}
		        	result.put(key,tempArray);
		        }
		    }
		   
			util.log("Request Data Param : "+result);
		
		}
		catch (Exception e) 
		{
			util.log(e);
			result = null;
		}
		return result;
	}
}
