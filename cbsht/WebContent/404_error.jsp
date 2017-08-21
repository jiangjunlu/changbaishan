<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/error.css" />
	</head>

	<body>
		<div id="container"><img src="<%=request.getContextPath() %>/img/404.png" /> 
			 <div class="msg">:(很抱歉，您所访问的页面已过期，请尝试重新登录</div>
			<p>
				<a class="a-back" href="<%=request.getContextPath() %>/login.jsp">返回首页</a>
			</p>
		</div>
		<div id="cloud"></div>
	</body>

</html>