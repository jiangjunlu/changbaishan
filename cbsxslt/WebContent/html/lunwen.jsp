<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body,table,td,div,th,ul,li{
	margin:0px;
	padding:0px;
}
ul{
	list-style: none;
	width:100%;
}
li{
	width:100%;
	height: 50px;
}
#name{
	text-align: center;
}
#leibie1{
	text-align: right;
}
#guanjianci{
	 font-weight:bold;
	 font-family: 微软雅黑;
	 font-size: 16px;
}
#zhaiyao{
	 font-weight:bold;
	 font-family: 微软雅黑;
	  font-size: 16px;
}
#zhengwen{
	 font-weight:bold;
	 font-family: 微软雅黑;
	  font-size: 16px;
}
</style>
</head>
<body>
	<div>
		<ul>
			<li id="name">
				<h1>${lunwen.lunwen_name }</h1>
			</li>
			<li id="leibie1">
				<h3>类别:${lunwen.lunwen_leibie }</h3>
			</li>
			<li id="guanjianci">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关键词:${lunwen.lunwen_guanjianci }</p>
			</li>
			<li >
				<p id="zhaiyao">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;摘要:${lunwen.lunwen_zhaiyao }</p>
				<div >${lumwen.lunwen_zhaiyao}</div>
			</li>
			<li id="zhengwen">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正文:</p>
			</li>
		</ul>
	</div>
	<div>
		&nbsp;&nbsp;${lunwen.lunwen_neirong }
	</div>
</body>
</html>