package com.itf;

import java.util.HashMap;

public class CarELE
{
	
	/**
	 * 제품 제조 회사 
	 */
	public static final HashMap<String, String> MADE_IN_TYPE_ELE = new HashMap<String, String>()
	{
		{
			put("M1", "국내");
			put("M2", "수입");
		}
	};
	
	/**
	 * 제품 제조 회사 
	 */
	public static final HashMap<String, String> BRAND_ELE = new HashMap<String, String>()
	{
		{
			put("B01", "현대자동차");
			put("B02", "기아자동차");
			put("B03", "쌍용자동차");
		}
	};

	/**
	 * 엔진종류 
	 */
	public static final HashMap<String, String> ENGINE_ELE = new HashMap<String, String>()
	{
		{
			put("EA", "내연기관(가솔린)");
			put("EB", "내연기관(디젤)");
			put("EC", "하이브리드(가솔린)");
			put("ED", "전기(베터리)");
			put("EE", "전기(수소)");
		}
	};
	
	/**
	 * 자동차 종류 
	 */
	public static final HashMap<String, String> TYPE_ELE = new HashMap<String, String>()
	{
		{
			put("TA", "승용");
			put("TB", "SUV");
			put("TC", "MPV");
			put("TD", "소형화물");
		}
	};

	/**
	 * 자동차 크기 소형
	 */
	public static final HashMap<String, String> SIZE_ELE = new HashMap<String, String>()
	{
		{
			put("SA", "소형");
			put("SB", "중중형");
			put("SC", "중형");
			put("SD", "대형");
		}
	};
	
	/**
	 * 실린더 갯수 
	 */
	public static final HashMap<String, String> CYLINDER_ELE = new HashMap<String, String>()
	{
		{
			put("CA", "3기통");
			put("CB", "4기통");
			put("CC", "6기통");
			put("CD", "8시통");
			put("CE", "12기통");
		}
	};
	
	/**
	 * 기어 타입 
	 */
	public static final HashMap<String, String> GEAR_TYPE_ELE = new HashMap<String, String>()
	{
		{
			put("GT1", "수동");
			put("GT2", "자동");
		}
	};
	
	/**
	 * 기어단수 
	 */
	public static final HashMap<String, String> GEAR_COUNT_ELE = new HashMap<String, String>()
	{
		{
			put("G04", "4 단");
			put("G05", "5 단");
			put("G06", "6 단");
			put("G07", "7 단");
			put("G08", "8 단");
			put("G09", "9 단");
			put("G0A", "10 단");
		}
	};
	
	/**
	 * 도어 갯수 
	 */
	public static final HashMap<String, String> DOOR_ELE = new HashMap<String, String>()
	{
		{
			put("D2", "2 개");
			put("D3", "3 개");
			put("D4", "4 개");
			put("D5", "5 개");
		}
	};
	
	/**
	 * 과급방식
	 */
	public static final HashMap<String, String> INHALATION_TYPE_ELE = new HashMap<String, String>()
	{
		{
			put("IT1", "자연흡기");
			put("IT2", "터보");
			put("IT3", "트윈터보");
			put("IT4", "트리블터보");
		}
	};

	/**
	 * 바퀴 굴림 종류
	 */
	public static final HashMap<String, String> DRIVEN_SYSTEM_ELE = new HashMap<String, String>()
	{
		{
			put("DS1", "전륜");
			put("DS2", "후륜");
			put("DS3", "상시4륜(AWD)");
			put("DS4", "파트타임4륜(4WD)");
		}
	};

	/**
	 * 신차 중고차 
	 */
	public static final HashMap<String, String> NEW_CAR_ELE = new HashMap<String, String>()
	{
		{
			put("NC1", "신차");
			put("NC2", "중고차");
			put("NC3", "신차/중고차");
		}
	};	
}