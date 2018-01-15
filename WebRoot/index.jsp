<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<div>欢迎使用用户管理系统</div>
	<c:if test="${user==null}">
		<div>
			<a href="regist.jsp">注册新用户</a>
			<a href="UserInfoServlet?method=login">用户登录</a>
		</div>
	</c:if>
	<c:if test="${user!=null}">
		<div>
			<a href="UserInfoServlet?method=regist">修改用户信息</a>
		</div>
		<div>
			<a href="UserInfoServlet?method=showAccount">查看用户余额</a>
		</div>
		<div>
		<div>
			<a href="UserInfoServlet?method=transforAccount">转账</a>
		</div>
		<div>
			<a href="UserInfoServlet?method=logout">注销</a>
		</div>
	</c:if>
		<a href="UserInfoServlet?method=showAlUser">显示所有用户信息</a>
	</div>
  </body>
</html>
