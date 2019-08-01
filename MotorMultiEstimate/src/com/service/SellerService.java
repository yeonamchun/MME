package com.service;

import org.apache.ibatis.session.SqlSession;

import com.config.JDBCSessionFactory;
import com.dao.SellerDAO;
import com.dto.SellerDTO;
import com.dto.UserDTO;

public class SellerService
{

	public int sellerJoin(UserDTO uDTO, SellerDTO sDTO)
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		int result = 0;
		
		try
		{
			SellerDAO dao = new SellerDAO();
			
			result = dao.sellerJoin(session, uDTO, sDTO);
			
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

	public SellerDTO checkSellernum(String seller_num)
	{
		SqlSession session = JDBCSessionFactory.getSession();
		
		SellerDTO result = null;
		
		try
		{
			SellerDAO dao = new SellerDAO();
			
			result = dao.checkSellernum(session, seller_num);
			
		}
		finally
		{
			session.close();
		}
		
		return result;
	}

	public int sellerUpdate(UserDTO uDTO, SellerDTO sDTO) {
		SqlSession session = JDBCSessionFactory.getSession();
		
		int result = 0;
		
		try
		{
			SellerDAO dao = new SellerDAO();
			
			result = dao.sellerUpdate(session, uDTO, sDTO);
			
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

}
