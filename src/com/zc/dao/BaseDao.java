package com.zc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BaseDao {
	public boolean update(String sql,Object...args);
	public <T>List<T> getBeanList(Class<T> clazz,String sql,Object...args) throws Exception;
	public List<Map<String,Object>> getMapList(String sql,Object...args) throws SQLException;
}
