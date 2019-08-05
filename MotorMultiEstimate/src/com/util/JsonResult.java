package com.util;

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itf.ErrorCode;
import com.yeoutil.Util;

import io.jsonwebtoken.Claims;

public class JsonResult 
{
	JSONObject resultJson;
	JSONArray result_date;

	Util util;
	
	/**
	 * Json 생성자
	 */
	public JsonResult()
	{
		util = new Util(this.getClass());
		result_date = new JSONArray();
		setInit();
	}
	
	/**
	 * 초기화
	 * @return
	 */
	public boolean setInit()
	{
		boolean result = false;
		try
		{
			resultJson = null;
			resultJson = new JSONObject();
			
			resultJson.put("error_code", ErrorCode.EX0200);
			resultJson.put("error_msg", ErrorCode.getMsg(ErrorCode.EX0200));
			resultJson.put("result_status", false);
			resultJson.put("result_toknen", "");
			resultJson.put("result_count", 0);
			
			result = true;
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	} 
	
	/**
	 * 에러 코드 입력
	 * @param _key 에러코드
	 * @return	Boolean
	 */
	public boolean setError(final String _key)
	{
		boolean result = false;
		try
		{
			resultJson.remove("error_code"); 
			resultJson.put("error_code", _key);
			
			resultJson.remove("error_msg"); 
			resultJson.put("error_msg", ErrorCode.getMsg(_key));
			
			result = true;
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	}
	
	/**
	 * 상태 코드 입력
	 * @param _boolean
	 * @return Boolean
	 */
	public boolean setStatus(boolean _boolean)
	{
		boolean result = false;
		try
		{
			resultJson.remove("result_status"); 
			resultJson.put("result_status", _boolean);
			result = true;
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	}
	
	/**
	 * 사용자 토큰 정보 입력
	 * @param _token
	 * @return Boolean
	 */
	public boolean setToken(String userId, HashMap<String, String> _map)
	{
		boolean result = false;
		try
		{
			resultJson.remove("result_toknen"); 
			try 
			{
				JWT jwt = new JWT("MMT", "login");
			
				Iterator<String> keys = _map.keySet().iterator();
				while( keys.hasNext() )
				{
					String key = keys.next();
					String value = _map.get(key);
					jwt.addParam(key, value);
				}
		        resultJson.put("result_toknen", jwt.encrypt(userId, 600000));
		    
		        result = true;
		 	}
			catch (Exception e)
			{
				resultJson.remove("result_toknen"); 
				resultJson.put("result_toknen", "");
			}
			
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	}

	/**
	 * JSON data 입력
	 * @param _data
	 * @return Boolean
	 */
	public boolean addData(JSONObject _data)
	{
		boolean result = false;
		try
		{
			result_date.add(_data);
			result = true;
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	}
	
	public boolean addData(String _key, Object _obj)
	{
		boolean result = false;
		try
		{
			JSONObject temp = new JSONObject();
			temp.put(_key, _obj.toString());
			result_date.add(temp);
			result = true;
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		return result;
	}
	
	
	
	
	/**
	 * JSON data 리턴
	 * @return JSONObject
	 */
	public JSONObject getJson()
	{
		try
		{
			resultJson.remove("result_date");
			resultJson.put("result_date", result_date);
		}
		catch(Exception ex001)
		{
			util.log(ex001);
		}
		
		try
		{
			resultJson.remove("result_count");
			resultJson.put("result_count",result_date.size());
		}
		catch(Exception ex001)
		{
			util.log(ex001);
			resultJson.put("result_count",0);
		}
		
		return resultJson;
	}

	@Override
	public String toString() 
	{
		return getJson().toString();
	}
}
