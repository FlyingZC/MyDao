package com.zc.dao.impl;

import java.util.List;

import com.zc.dao.BaseDao;
import com.zc.dao.UserInfoDao;
import com.zc.entity.UserInfo;

public class UserInfoDaoImpl implements UserInfoDao {
	BaseDao baseDao=new BaseDaoImpl();
	public boolean save(UserInfo user) {
		String sql="insert into userinfo(username,password,email,nickname,account) "
 +" values(?,?,?,?,?)";
		return baseDao.update(sql, user.getUsername(),user.getPassword()
				,user.getEmail(),user.getNickname(),user.getAccount());
	}

	public boolean update(String sql, String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(String sql, String username, String password, String email, String nickname, double account,
			int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserInfo findByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
