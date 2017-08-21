<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css" />
		<style type="text/css">
		.head_titile h3{
			margin-top:10px;
		    top:10px;
			font:"黑体";
		}
		</style>
	</head>

	<body>
		<div class="head">
			<div class="head_titile">
			 <h3>
				欢迎登录   长白山学术论坛后台
			 </h3>
			</div>
		</div>
		<div class="container">
			<form class="container_right" action="<%=request.getContextPath() %>/user/login" method="post">
				<div class="title">账户登录</div>
				<div class="form_listone">
					<label>用户名：</label>
					<input type="text" name="username" placeholder="请输入用户名" />
				</div>
				<div class="form_listtwo">
					<label>密码：</label>
					<input type="password" name="password"/>
				</div>
				<div class="wangji">
					<a class="aone">忘记登录密码</a>
					<a class="atwo" href="<%=request.getContextPath() %>/register.jsp">免费注册</a>
				</div>
				<div class="denglu">
					<input type="submit" value="登录" />
				</div>
				<span>${msg }</span>
			</form>
		</div>
	</body>

</html>