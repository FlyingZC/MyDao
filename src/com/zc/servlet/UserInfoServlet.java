package com.zc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zc.entity.UserInfo;
import com.zc.service.UserService;

public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService s=new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if("regist".equals(method)){
			this.regist(request,response);
		}
		if("login".equals(method)){
			this.regist(request,response);
		}
		if("regist".equals(method)){
			this.login(request,response);		
		}
		if("showAccount".equals(method)){
			this.showAccount(request,response);
		}
		if("transforAccount".equals(method)){
			this.transforAccount(request,response);
		}
		if("logout".equals(method)){
			this.logout(request,response);
		}
		if("showAlUser".equals(method)){
			this.showAlUser(request,response);
		}
	}
	private void showAlUser(HttpServletRequest request, HttpServletResponse response) {
		//s.showAccount(user);
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		//s.logout();
	}
	private void transforAccount(HttpServletRequest request, HttpServletResponse response) {

	}
	private void showAccount(HttpServletRequest request, HttpServletResponse response) {
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) {
		
	}
	private void regist(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String nickname=request.getParameter("nickname");
		String _account=request.getParameter("account");
		Double account=Double.parseDouble(_account);
		UserInfo user=new UserInfo(username,password,email,nickname,account);
		s.regist(user);
	}

}
