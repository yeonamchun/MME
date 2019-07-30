package com.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class JDBCSessionFactory
{
	private static SqlSessionFactory sqlSessionFactory;
	
	static
	{
		String resource = "com/config/MybatisConfig.xml";
		InputStream inputStream = null;
		
		try
		{
			inputStream = Resources.getResourceAsStream(resource);
		}
		catch(IOException e){}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * SQLsession  전달 method
	 * @return SqlSession
	 */
	public static SqlSession getSession()
	{
		return sqlSessionFactory.openSession();
	}
}
