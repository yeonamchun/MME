package com.yeoutil;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * github
 * @author yeo0419
 *
 */
public class Util
{
	
	public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_YYYYMMDD = "yyyyMMdd";
	public static final String TIME_HH_MM_SS = "HH:mm:ss";
	public static final String TIME_HHMMSS = "HHmmss";
	public static final String DATE_TIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_TIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 현재 날짜를 문자로 변경
	 * @return YYYY-MM-DD 형식으로 리턴
	 */
	public static String getNowDate(final String _format)
	{
		return new SimpleDateFormat(_format).format(new Date());
	}
	
	public static final String TIME_START_FORMAT = "00:00:00";
	public static final String TIME_END_FORMAT = "23:59:59";
	
	
	/**
	 * 시간을 하루의 시작 또는 마지막 시간
	 * @param _select Date
	 * @param _timeType  시간 표시
	 * @return Date
	 */
	public static Date getDate(Date _select, final String _timeType )
	{	
		Calendar cal = Calendar.getInstance();
		cal.setTime(_select);
	
		String dateTemp = cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH)+" "+_timeType;
		SimpleDateFormat dt = new SimpleDateFormat(DATE_TIME_YYYY_MM_DD_HH_MM_SS); 
		Date result = null;
		
		try
		{
			result = dt.parse(dateTemp);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 시간을 하루의 시작 또는 마지막 시간
	 * @param _year 년도
	 * @param _month 월
	 * @param _day 일
	 * @param _timeType 시간 표시
	 * @return Date
	 */
	public static Date getDate(String _year, String _month, String _day , final String _timeType )
	{	
		String dateTemp = _year+"-"+_month+"-"+_day+" "+_timeType;
		SimpleDateFormat dt = new SimpleDateFormat(DATE_TIME_YYYY_MM_DD_HH_MM_SS); 
		Date result = null;
		try
		{
			result = dt.parse(dateTemp);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 * 문자형 숫자를 int 로 변환
	 * @param _num 문자형 숫자
	 * @return int 숫자
	 */
	public static int toInt(String _num)
	{
		int result = 0;

		try
		{
			result = Integer.parseInt(_num);
		}
		catch(Exception ex001)
		{
			result = 0;
		}
		return result;
	}
	
	/**
	 * Object 를 문자로 변화
	 * @param _data 숫자 와 관련된 기본형
	 * @return 문자 
	 */
	public static String toString(Object _data)
	{
		return String.valueOf(_data);
	}
	
	/**
	 * 화폐 컴마 로구분
	 * @param _num 금액(숫자)
	 * @param _symbols 원화 표시 여부
	 * @return 화폐로 변경
	 */
	public static String toMoney(int _num , boolean _symbols)
	{
		DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.KOREA);
		String cs = dfs.getCurrencySymbol();
		DecimalFormat df = null;
		if(_symbols)
		{
			df = new DecimalFormat(cs + "#,##0");
		}
		else
		{
			df = new DecimalFormat("#,##0");
		}
		return df.format(_num);
	}
	
	/**
	 * session 에 있는 정보 가지고 오기
	 * @param _req HttpServletRequest
	 * @param _key KEY
	 * @return Object
	 */
	public static Object getSession(HttpServletRequest _req, String _key)
	{
		HttpSession session = _req.getSession(false);
		Object result = (Object)session.getAttribute(_key);
		
		return result;
	}
	
	/**
	 * session 에 데이터 저장 하기
	 * @param _req HttpServletRequest
	 * @param _key KEY
	 * @param _obj Object
	 * @return boolean true / false
	 */
	public static boolean setSession(HttpServletRequest _req, String _key, Object _obj)
	{
		HttpSession session = _req.getSession(false);
		
		boolean result = false;
		try
		{
			session.setAttribute(_key, _obj);
			result = true;
		}
		catch(Exception ex001)
		{
			result = false;
		}
		return result;
	}
	
	/**
	 * 참조형 데이터를 JSON 형태로 리턴
	 * @param _ele
	 * @return
	 */
	public static String getJson(HashMap<String, String> _ele)
	{ 
		StringBuilder result = new StringBuilder();
	
		result.append("{");
		Iterator<String> keys = _ele.keySet().iterator();
		boolean check = true;
		
		while( keys.hasNext() )
		{
			String key = keys.next();
			String value = _ele.get(key);
			if(check)
			{
				result.append("\""+key+"\":\""+value+"\"");
				check = false;
			}
			else
			{
				result.append(",\""+key+"\":\""+value+"\"");
			}	
		}
		
		result.append("}");
		
		return result.toString();
	}
	
	/**
	 * MAP 키 값 리턴
	 * @param HashMap<String, Object>
	 * @return ArrayList
	 */
	public static List<String> getKeys(HashMap<String, Object> _ele)
	{
		return new ArrayList<String>(_ele.keySet());
	}
	
	/**
	 * MAP 값 리턴
	 * @param HashMap<String, Object>
	 * @return ArrayList
	 */
	public static List<Object> getValues(HashMap<String, Object> _ele)
	{
		return new ArrayList<Object>(_ele.values());
	}
	
	
	
	public static final String IMG_POSITION_WIDTH = "W";
	public static final String IMG_POSITION_HEIGHT = "H";
	
	public static final String IMG_RATET_16_9 = "16:9";
	public static final String IMG_RATET_4_3 = "4:3";
	public static final String IMG_RATET_1_1 = "1:1";
	
	public static final String IMG_EXT_JPG = "jpg";
	public static final String IMG_EXT_GIF = "gif";
	public static final String IMG_EXT_PNG = "png";
	
	/**
	 * 이미지 변환
	 * @param _imgOrgName 원본 이미지 파일 이름을 포함한 이미지
	 * @param _imgTargName 출력 패스를 포함한 이미지 이름
	 * @param _width 출력 파일 가로 사이즈	
	 * @param _height  출력 파일 세로 사이즈
	 * @param _position 출력 파일 가로 기준 세로 기준
	 * @param _rate 출력 파일 비율
	 * @param _ext	출력 파일 확장자
	 * @return
	 */
	public static boolean changeImageSize(
			String _imgOrgName, String _imgTargName, 
			int _width, int _height, 
			final String _position, final String _rate, final String _ext )
	{
       
		boolean result = false;
        Image image;
       
 
        try{
            // 원본 이미지 가져오기
            image = ImageIO.read(new File(_imgOrgName));
 
            String[] rate = _rate.split(":");
            
            if(_position.equals("W")){    // 넓이기준
            	
            	_height =  (Integer.parseInt(rate[1]) * _width) / Integer.parseInt(rate[0]);
            	
            }else if(_position.equals("H")){ // 높이기준
 
            	_width =  (Integer.parseInt(rate[0]) * _height) / Integer.parseInt(rate[1]);
 
            }
 
            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
            Image resizeImage = image.getScaledInstance(_width, _height, Image.SCALE_SMOOTH);
 
            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, _ext, new File(_imgTargName));
            result = true;
        }catch (Exception e){
 
            e.printStackTrace();
 
        }
        
        return result;
        
	}
}
