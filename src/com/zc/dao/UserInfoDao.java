package com.zc.dao;

import java.util.List;

import com.zc.entity.UserInfo;

public interface UserInfoDao {

	boolean save(UserInfo user);

	boolean update(String sql, String username, String password);

	boolean update(String sql, String username, String password, String email, String nickname, double account, int id);

	UserInfo findByName(String username);

	List<UserInfo> findAll();

}
