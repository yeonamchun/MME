package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.JDBCSessionFactory;
import com.dao.MemberDAO;
import com.dao.MgmtDAO;
import com.dto.MgmtDTO;

public class MgmtService 
{
	public List<MgmtDTO> getMgmtpage(String manager_code) 
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		List<MgmtDTO> result = null;
		
		try
		{
			MgmtDAO dao = new MgmtDAO();
			
			result = dao.getMgmtpage(session, manager_code);
		
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

}
