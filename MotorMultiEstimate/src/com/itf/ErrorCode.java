package com.itf;

import java.util.HashMap;
import java.util.Iterator;

public class ErrorCode
{
	
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
	
	/*** 토큰 만료 */
	public static final String EX0560 = "EX0560";
	
	/*** 토큰 오류 */
	public static final String EX0561 = "EX0561";
	
	/*** 아이디 일치 불가 */
	public static final String EX0562 = "EX0562";
	
	/*** 사용자 정보 저장 완료 */
	public static final String EX0540 = "EX0540";
	
	/*** 사용자 정보 저장 실패 */
	public static final String EX0541 = "EX0541";
	
	/*** 사업자 정보 저장 완료 */
	public static final String EX0542 = "EX0542";
	
	/*** 사업자 정보 저장 실패 */
	public static final String EX0543 = "EX0543";
	
	/*** 사용자 정보 없음 */
	public static final String EX0202 = "EX0202";
	
	/*** 로그 아웃 */
	public static final String EX0201 = "EX0201";
	
	
	/*** 에러코드 */
	private static final HashMap<String, String> CODE = new HashMap<String, String>()
	{
		{
			put(EX0200, "정상");
			put(EX0201, "로그 아웃");
			put(EX0202, "사용자 정보 없음");
			
			put(EX0500, "DB 오류");
			put(EX0001, "입력 param 오류");
			put(EX0100, "OPT CODE ERROR");
			put(EX0900, "Exception Error");
			
			put(EX0560, "토큰 만료");
			put(EX0561, "토큰 오류");
			put(EX0562, "아이디 일치 불가");
			
			put(EX0540, "사용자 정보 저장 완료");
			put(EX0541, "사용자 정보 저장 실패");
			
			put(EX0542, "사업자 정보 저장 완료");
			put(EX0543, "사업자 정보 저장 실패");
		}
	};
	
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