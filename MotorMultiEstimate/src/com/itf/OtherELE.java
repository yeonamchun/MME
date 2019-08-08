package com.itf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Comparator;
import org.json.simple.JSONObject;

public class OtherELE
{

	public static final HashMap<String, String> LOGIN_TYPE = new HashMap<String, String>()
	{
		{
			put("0", "일반 사용자");
			put("1", "사업자");
			put("2", "관리자");
		}
	};
	
	
	/**
	 * 관리자 권한 페이지 
	 */
	public static final HashMap<String, String> MGMT_PAGE_AUTH_ELE = new HashMap<String, String>()
	{
		{
			put("PAA", "Admin");
			put("PAB", "user_01");
			put("PAC", "user_02");
		}
	};
	
	/**
	 * 메일 정보
	 */
	public static final HashMap<String, String> MAIL_ELE = new HashMap<String, String>()
	{
		{
			put("M00", "직접입력");
			put("M01", "gmail.com");
			put("M02", "naver.com");
			put("M03", "daum.net");
		}
	};
	
	/**
	 * 지역 정보
	 */
	public static final HashMap<String, String> AREA_ELE = new HashMap<String, String>()
	{
		{
			put("N02", "서울");
			put("N51", "부산");
			put("N53", "대구");
			put("N32", "인천");
			put("N62", "광주");
			put("N42", "대전");
			put("N52", "울산");
			put("N44", "세종");
			put("N31", "경기");
			put("N33", "강원");
			put("N43", "충북");
			put("N41", "충남");
			put("N63", "전북");
			put("N61", "전남");
			put("N54", "경북");
			put("N55", "경남");
			put("N64", "제주");
		}
	};
	
	/**
	 * map 을  json 형식의  array 로 변환
	 * @param _ele HashMap<String, String>
	 * @return List
	 */
	public static List getList(HashMap<String, String> _ele)
	{
		List list = new ArrayList();
		list.add(_ele);
		return list;
	}
	
	
	public static String getJson(HashMap<String, String> _ele)
	{ 
		
		JSONObject result = new JSONObject();
	
		Iterator<String> keys = _ele.keySet().iterator();
		boolean check = true;
		
		while( keys.hasNext() )
		{
			String key = keys.next();
			String value = _ele.get(key);
			
			result.put(key, value);	
		}
		
		return result.toString();
	}
	
	public static JSONObject getJsonObj(HashMap<String, String> _ele)
	{ 
		JSONObject result = new JSONObject();
	
		Set<HashMap.Entry<String, String>> entry = _ele.entrySet();
		
		Comparator<HashMap.Entry<String, String>> comparator = new Comparator<HashMap.Entry<String, String>>() {
	        @Override
	        public int compare(HashMap.Entry<String, String> o1, HashMap.Entry<String, String> o2) {
	            String value1 = o1.getValue();
	            String value2 = o2.getValue();
	            return value1.compareTo(value2);
	        }
	    };
	    
	    SortedSet<HashMap.Entry<String, String>> sortedSet = new TreeSet(comparator);
	    sortedSet.addAll(entry);
	    
	    for(HashMap.Entry<String, String> entry1 : sortedSet ){
	    	result.put(entry1.getKey(), entry1.getValue());	
	    }
	    
		return result;
	}
	
	
	
	public static List<String> getKeys(HashMap<String, String> _ele)
	{
		return new ArrayList<String>(_ele.keySet());
	}
	
	public static List<String> getValues(HashMap<String, String> _ele)
	{
		return new ArrayList<String>(_ele.values());
	}
}
