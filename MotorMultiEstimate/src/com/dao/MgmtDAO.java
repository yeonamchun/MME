package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.MgmtDTO;

public class MgmtDAO 
{

	public List<MgmtDTO> getMgmtpage(SqlSession session, String manager_code) {
		
		return session.selectList("MgmtMapper.getMgmtpage", manager_code);
	}

}
