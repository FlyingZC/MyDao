package com.zc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.zc.dao.UserInfoDao;
import com.zc.dao.impl.UserInfoDaoImpl;
import com.zc.entity.UserInfo;
import com.zc.util.DBUtils;

/**
 * 用户管理系统
 * 功能:
 * 1.注册新用户
 * 2.用户登录
 * 3.修改用户信息
 * 4.查看用户余额
 * 5.显示所有用户信息(不含有用户密码信息)
 * 6.注销
 * @author zc
 *
 */
public class UserService {
	private UserInfo currentUser;
	private UserInfoDao dao;
	public UserService(){
		dao=new UserInfoDaoImpl();
	}
	
	/**
	 *转账 
	 * @throws SQLException 
	 */
	private void transforAccount(){
	
	}
	
	//更新转账两人账户
	private void updateAccounts(double money,String toUser) {
		//try {
			
		/*	//
			String otherAccountSql="select account from userinfo where username=?";
			PreparedStatement psmt0 = conn.prepareStatement(otherAccountSql);
			psmt0.setString(1, toUser);
			ResultSet rs=psmt0.executeQuery();
			double otherAccount=0;//他人账户余额
			while(rs.next()){
				otherAccount=rs.getDouble("account");
			}
			
			String currSql="update userinfo set account=? where username=?";
			PreparedStatement psmt1 = conn.prepareStatement(currSql);
			psmt1.setDouble(1, currentUser.getAccount()-money);
			psmt1.setString(2, currentUser.getUsername());
			if(psmt1.executeUpdate()<=0){
				conn.rollback();
				System.out.println("转账失败");
				return;
			}
			
			String otherSql="update userinfo set account=? where username=?";
			PreparedStatement psmt2 = conn.prepareStatement(otherSql);
			psmt2.setDouble(1,otherAccount+money);
			psmt2.setString(2,toUser);
			psmt2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("转账失败!!");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}*/
	}
	boolean findUserByName(String userName) {
		/*String sql="select id,username,password,email,nickname,account "
				+" from userinfo where username=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, userName);
			ResultSet rs=psmt.executeQuery();
			if(rs==null){
				System.out.println("该用户不存在");
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return false;
	}
	/**
	 * 注册
	 */
	public boolean regist(UserInfo user){
		return dao.save(user);
	}
	
	
	
	
	/**
	 * 登录
	 */
	public void login(){
	/*	System.out.println("欢迎进入登录页面,请进行登录操作()");
		//UserInfo user=scanLogin();
		String sql="select username,password from userinfo "
				+ "where username=? and password=?";
		boolean result=dao.update(sql,user.getUsername(),user.getPassword());
		if(!result){
			System.out.println("登录失败");
			return;
		}
		if(currentUser==null){
			currentUser=new UserInfo();
		}
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(user.getPassword());
		findInfo(currentUser);//保存当前用户所有信息
		System.out.println("当前用户为:"+currentUser);*/
	}
	
	
	
	/**
	 * 修改当前用户信息(需要登录后才可操作)
	 */
	public void update(){
		if(currentUser==null){
			System.out.println("请先登录再进行操作");
			return;
		}
		//scanUpdate();//接收输入更新并处理
		
		System.out.println("修改后的信息:"+currentUser);
		
		String sql="update userinfo set username=?"
				+",password=?,email=?,nickname=? , account=? where id=?";
		boolean result=dao.update(sql,currentUser.getUsername(), currentUser.getPassword(),
				currentUser.getEmail(),currentUser.getNickname(),currentUser.getAccount(),currentUser.getId());
		if(result){
			System.out.println("修改成功");
		}else{
			System.out.println("修改失败");
		}
		
	}
	

	
	/**
	 * 查看当前登录用户的账户余额(需要登录后才可操作)
	 */
	public void showAccount(UserInfo user){
		if(currentUser==null){
			System.out.println("请先登录");
			return;
		}
		System.out.println("您账户当前余额为:"+currentUser.getAccount());
	}
	
	/**
	 * 查询本人所有信息
	 */
	public void findInfo(UserInfo user){
		currentUser=dao.findByName(user.getUsername());
	}
	
	/**2
	 * 显示所有用户信息.仅显示所用用户名,邮箱,昵称,金额
	 */
	public void showAlUser(){
		List<UserInfo> users=dao.findAll();
		for(UserInfo u:users){
			System.out.println(u);
		}
	}
	
	/**
	 * 注销(登录后才可操作)
	 */
	public void logout(){
		if(currentUser==null){
			System.out.println("请先登录");
			return;
		}
		currentUser=null;
	}
	
}
