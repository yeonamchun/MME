package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.UserDTO;

public class MemberDAO
{
	public int memberJoin(SqlSession session, UserDTO uDTO)
	{
		return session.insert("UserMapper.memberJoin", uDTO);
	}

	public UserDTO checkUserid(SqlSession session, String user_id)
	{
		return session.selectOne("UserMapper.checkUserid", user_id);
	}

	public UserDTO checkUseralias(SqlSession session, String user_alias)
	{
		return session.selectOne("UserMapper.checkUseralias", user_alias);
	}

}
