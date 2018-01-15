package com.zc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	//基础数据源,连接池
	/*
	 * commons-dbcp-1.4.jar 连接池的实现
	   commons-pool-1.5.jar 连接池实现的依赖库
	 * */
	private static BasicDataSource ds;
	private static Properties pros;
	
	private static String driverClass;
	private static String url;
	private static String user;
	private static String psw;
	static{
		/*
		 * java.util.Properties
		 * 用来读取.properties文件,并解析其中的每一行内容
		 * 然后以key-value格式保存在当前Properties实例中
		 * */
		pros=new Properties();
		try {
			pros.load(new FileInputStream(new File("oracle.properties")));
			driverClass=pros.getProperty("driverClass");
			url=pros.getProperty("url");
			user=pros.getProperty("user");
			psw=pros.getProperty("psw");
			//初始化连接池
			ds=new BasicDataSource();
			//将jdbc建立连接所需要的信息设置到连接池中
			ds.setDriverClassName(driverClass);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(psw);
			
			int maxActive=Integer.parseInt(pros.getProperty("maxwait"));
			int maxWait=Integer.parseInt(pros.getProperty("maxwait"));
			//设置最大连接数
			ds.setMaxActive(maxActive);
			//设置最大等待时间
			ds.setMaxWait(maxWait);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		/*
		 * 连接池提供的方法
		 * Connection getConnection()
		 * 该方法可以返回一个连接池中可用连接
		 * 这是一个阻塞方法,当连接池中有空闲连接可以使用时会立刻返回,若当前连接池没有可用连接时,会进入阻塞
		 * 阻塞时间由创建连接池时通过setMaxWait设置的时间为准
		 * 在等待期间若有空闲连接则立即返回,当超出最大等待时间仍没有可用连接时,该方法会抛出超时异常
		 * 
		 * 如果等待时间5s内有其他连接返回,则返回连接,否则抛出超时异常
		 * */
		Connection conn=ds.getConnection();
		conn.setAutoCommit(true);
		return conn;
	}
	
	public static void releaseDB(ResultSet rs,PreparedStatement psmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(psmt!=null){
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				/*
				 * 若该连接是通过连接池获取的,那么调用这个连接的close方法并不是与数据库断开连接,而是将该连接返还给连接池
				 * */
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
