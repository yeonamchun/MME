package com.dao;

import java.util.HashMap;

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

	public UserDTO login(SqlSession session, HashMap<String, String> param) {
		return session.selectOne("UserMapper.login", param);
	}

	public int memberUpdate(SqlSession session, UserDTO uDTO) {
		return session.update("UserMapper.memberUpdate", uDTO);
	}

	public UserDTO getSession(SqlSession session, String _Userid) {
		return session.selectOne("UserMapper.checkUserid",_Userid);
	}

}
