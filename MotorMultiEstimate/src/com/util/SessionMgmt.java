package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dto.MgmtDTO;
import com.dto.SellerDTO;
import com.dto.UserDTO;
import com.service.MemberService;
import com.service.SellerService;

public class SessionMgmt
{
	public final HttpServletRequest request;
	public final HttpSession session;
	
	public SessionMgmt(HttpServletRequest request)
	{
		this.request = request;
		session = this.request.getSession(false);
	}
	
	/**
	 * 사용자 정보 session 이 있는지 체크 해서 리턴 한다.
	 * @return UserDTO
	 */
	public UserDTO getUdto()
	{
		UserDTO temp = null;
		try
		{
			temp = (UserDTO)session.getAttribute("uDTO");
		}
		catch(Exception ex001)
		{
			temp = null;
		}
		return temp;
	}
	
	/**
	 * 사용자 아이디를 이용하여 사용자 정보를 찾고 httpsession 에 SET 한다.
	 * @param _Userid
	 * @return boolean
	 */
	public boolean setUdto()
	{
		boolean result = false;
		
		try
		{
			UserDTO udtoTemp = getUdto();
			if(udtoTemp != null)
			{
				MemberService service = new MemberService();
				UserDTO udto = service.getSession(udtoTemp.getUser_id());
				udto.setUser_pw(null);
				if(udto != null)
				{
					session.setAttribute("uDTO", udto);
					result = true;
				}
			}
		}
		catch(Exception ex001)
		{
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 사용자 아이디를 이용하여 사용자 정보를 찾고 httpsession 에 SET 한다.
	 * @param _Userid
	 * @return boolean
	 */
	public boolean setUdto(String _Userid)
	{
		boolean result = false;
		
		try
		{
			MemberService service = new MemberService();
			UserDTO udto = service.getSession(_Userid);
			udto.setUser_pw(null);
			if(udto != null)
			{
				session.setAttribute("uDTO", udto);
				result = true;
			}
		}
		catch(Exception ex001)
		{
			result = false;
		}
		
		return result;
	}
	
	/**
	 * seller session 이 있는지 체크 해서 정보를 리턴 한다.
	 * @return SellerDTO
	 */
	public SellerDTO getSdto()
	{
		SellerDTO temp = null;
		try
		{
			temp = (SellerDTO)session.getAttribute("sDTO");
		}
		catch(Exception ex001)
		{
			temp = null;
		}
		return temp;
	}
	
	
	public boolean setSdto()
	{
		boolean result = false;
		
		try
		{
			SellerDTO udtotemp = getSdto();
			if(udtotemp != null)
			{
				SellerService service = new SellerService();
				SellerDTO udto = service.checkSellernum(udtotemp.getSeller_num());
				if(udto != null)
				{
					session.setAttribute("sDTO", udto);
					result = true;
				}
			}
		}
		catch(Exception ex001)
		{
			result = false;
		}
		
		return result;
	}
	
	
	/**
	 * 판매자 번호를 이용하여 정보를 받아온후 session 에 저장 한다.
	 * @param _sellerNum
	 * @return
	 */
	public boolean setSdto(String _sellerNum)
	{
		boolean result = false;
		
		try
		{
			SellerService service = new SellerService();
			SellerDTO udto = service.checkSellernum(_sellerNum);
			if(udto != null)
			{
				session.setAttribute("sDTO", udto);
				result = true;
			}
		}
		catch(Exception ex001)
		{
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 관리자  session 정보가 있는지 체크 해서 리턴 한다.
	 * @return MgmtDTO
	 */
	public MgmtDTO getMdto()
	{
		MgmtDTO temp = null;
		try
		{
			temp = (MgmtDTO)session.getAttribute("mDTO");
		}
		catch(Exception ex001)
		{
			temp = null;
		}
		return temp;
	}
	
	
}
