package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.SellerDTO;
import com.dto.UserDTO;

public class SellerDAO
{

	public int sellerJoin(SqlSession session, UserDTO uDTO, SellerDTO sDTO)
	{
		int num = session.insert("SellerMapper.sellerJoin", sDTO);
		if(num > 0)
		{
			num = session.insert("UserMapper.memberJoin", uDTO);
		}
		return num;
	}

	public SellerDTO checkSellernum(SqlSession session, String seller_num)
	{
		// TODO Auto-generated method stub
		return session.selectOne("SellerMapper.checkSellernum", seller_num);
	}
	
	public int sellerUpdate(SqlSession session, UserDTO uDTO, SellerDTO sDTO) {
		
		int num = session.insert("SellerMapper.sellerUpdate", sDTO);
		if(num > 0)
		{
			num = session.insert("UserMapper.memberUpdate", uDTO);
		}
		return num;
	}

}
