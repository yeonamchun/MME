package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.JDBCSessionFactory;
import com.dao.MemberDAO;
import com.dto.UserDTO;

public class MemberService
{
	MemberDAO dao = new MemberDAO();

	public int memberJoin(UserDTO uDTO)
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		int result = 0;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			
			result = dao.memberJoin(session, uDTO);
			
			if(result > 0)
			{
				session.commit();
			}
			else
			{
				session.rollback();
			}
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public UserDTO checkUserid(String user_id)
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		UserDTO result = null;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			result = dao.checkUserid(session, user_id);
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public UserDTO checkUseralias(String user_alias)
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		UserDTO result = null;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			result = dao.checkUseralias(session, user_alias);
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public UserDTO login(HashMap<String, String> param) 
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		UserDTO result = null;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			result = dao.login(session, param);
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public int memberUpdate(UserDTO uDTO) {
		SqlSession session = JDBCSessionFactory.getSession();
		
		int result = 0;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			result = dao.memberUpdate(session, uDTO);
			
			if(result > 0)
			{
				session.commit();
			}
			else
			{
				session.rollback();
			}
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public UserDTO getSession(String _Userid) {
		SqlSession session = JDBCSessionFactory.getSession();
		
		UserDTO result = null;
		
		try
		{
			MemberDAO dao = new MemberDAO();
			result = dao.getSession(session, _Userid);
		}
		finally
		{
			session.close();
		}
		
		return result;
	}
	
	
}
