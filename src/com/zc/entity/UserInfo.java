package com.zc.entity;

public class UserInfo {
	private int id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private double account;
	
	public UserInfo() {
		super();
	}
	public UserInfo(String username,String password,String email,String nickname,double account){
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.account = account;
	}
	public UserInfo(int id, String username, String password, String email, String nickname, double account) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", nickname=" + nickname + ", account=" + account + "]";
	}
}
