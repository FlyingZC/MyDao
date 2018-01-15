package com.zc.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zc.dao.BaseDao;
import com.zc.util.DBUtils;

public class BaseDaoImpl implements BaseDao {
	private Connection conn;
	/**
	 * @param sql 要执行的sql语句
	 * @param args 占位符
	 * @return 是否执行成功
	 */
	public boolean update(String sql,Object...args){
		PreparedStatement psmt=null;
		try {
			conn=DBUtils.getConnection();
			psmt=conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				psmt.setObject(i+1,args[i]);
			}
			if(psmt.executeUpdate()>0){//括号里千万别加sql
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.releaseDB(null, psmt, conn);
		}
		return false;
	}
	
	/**
	 * 查询返回一个List<Bean>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws InstantiationException 
	 */
	public <T>List<T> getBeanList(Class<T> clazz,String sql,Object...args) throws Exception{
		Connection conn=DBUtils.getConnection();
		PreparedStatement psmt=conn.prepareStatement(sql);
		for(int i=0;i<args.length;i++){
			psmt.setObject(i+1,args[i]);
		}
		ResultSet rs=psmt.executeQuery();
		List<T> results=new ArrayList<T>();
		T bean=null;
		List<String> labels=getColumnLabels(rs);
		while(rs.next()){
			bean=clazz.newInstance();
			for(int i=0;i<labels.size();i++){
				String colLab=labels.get(i);
				//该列的值
				Object val=rs.getObject(colLab);
				Field field=clazz.getDeclaredField(colLab);
				field.setAccessible(true);
				//如果是int
				if(field.getType()==int.class){
					if(val==null)//放置字段为null
						val=0;
					//Object类型转为Integer需要先转为String
					int newVal=Integer.parseInt(String.valueOf(val));//转型
					field.set(bean, newVal);
				}else{
					field.set(bean,val);
				}
			}
			results.add(bean);
		}
		return results;
	}
	
	/**
	 * 查询返回一个List<Map<列别名,值>>
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getMapList(String sql,Object...args) throws SQLException{
		Connection conn=DBUtils.getConnection();
		PreparedStatement psmt=conn.prepareStatement(sql);
		for(int i=0;i<args.length;i++){
			psmt.setObject(i+1, args[i]);
		}
		ResultSet rs=psmt.executeQuery();
		//获取列的别名
		List<String> labels=getColumnLabels(rs);
		//存储所有结果集内容
		List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
		//存储一行
		Map<String,Object> rowVals=null;
		while(rs.next()){
			rowVals=new HashMap<String,Object>();
			for(int i=0;i<labels.size();i++){
				String colLab=labels.get(i);
				rowVals.put(colLab,rs.getObject(colLab));
			}
			//每一行结束,将map放入List中
			results.add(rowVals);
		}
		return results;
	}

	private List<String> getColumnLabels(ResultSet rs) throws SQLException {
		List<String> labels=new ArrayList<String>();
		ResultSetMetaData meta=rs.getMetaData();
		for(int i=0;i<meta.getColumnCount();i++){
			labels.add(meta.getColumnLabel(i+1).toLowerCase());//列下标从1开始
		}
		return labels;
	}
}




