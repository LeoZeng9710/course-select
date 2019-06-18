package com.cst.sc.service.database;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//import org.apache.log4j.Logger;
import java.util.logging.*;

public class Config{
	public SqlSessionFactory getSqlSessionFactory(){
		return ssf;
	}
	
	public static SqlSession getSqlSession() {
	    return getInstance().getSqlSessionFactory().openSession();
	}
	
	public static synchronized Config getInstance () {
	    if (instance == null) {
	      synchronized( Config.class) {
	        instance = new Config();
	      }
	    }
	    return instance;
	 }
	
	 private SqlSessionFactory ssf;
	 private static Config instance = null; 
	 
	 public Config(){
		 try {
		      String resource = "com/cst/sc/service/database/xml/Config.xml";
		      InputStream inputstream = Resources.getResourceAsStream( resource);
		      ssf = new SqlSessionFactoryBuilder().build( inputstream);
		    } catch (Exception e) {
		      Logger.getLogger(Config.class.getName());
		    }
	 }
}