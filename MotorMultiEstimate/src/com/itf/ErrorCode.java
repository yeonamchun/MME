package com.itf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ErrorCode
{
	/**
	 * 에러코드
	 */
	private static final HashMap<String, String> CODE = new HashMap<String, String>()
	{
		{
			put(EX0200, "정상");
			put(EX0500, "DB 오류");
			put(EX0001, "입력 param 오류");
			put(EX0100, "OPT CODE ERROR");
			put(EX0900, "Exception Error");
		}
	};
	
	/*** 에러없음 */
	public static final String EX0200 = "EX0200";
	/*** DB 오류 */
	public static final String EX0500 = "EX0500";
	/*** 입력 param 오류 */
	public static final String EX0001 = "EX0001";
	/*** OPT CODE ERROR */
	public static final String EX0100 = "EX0100";
	
	/*** Exception error */
	public static final String EX0900 = "EX0900";
	
	
	/**
	 * 키에 따른 값 리턴
	 * @param _key 키값
	 * @return value
	 */
	public static String getMsg(final String _key)
	{
		String result = "";
		
		Iterator<String> keys = CODE.keySet().iterator();

		while( keys.hasNext() )
		{
			String key = keys.next();
			String value = CODE.get(key);
			if(_key.equals(key))
			{
				result = value;
				break;
			}
		}
		
		return result;
	}
}