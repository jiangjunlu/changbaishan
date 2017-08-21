<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body,table,td,th{
	margin:0px;
	padding:0px;
}
table{
	font-family: SimHei;
}
tr:HOVER{
	color:#333;
	cursor: pointer;
}
th{
	border-bottom:2px solid #ddd;
	height: 60px;
	width: 500px;
}
td {
	text-align:center;
	border-bottom:2px solid #eee;
	height: 40px;
}
</style>
</head>
<body>
	<table cellspacing="0">
		<tr>
			<th>主题</th>
			<th>类别</th>
			<th>关键词</th>
		</tr>
		<c:forEach items="${lunwen }" var="lunwen">
		<tr onclick="getDetail('${lunwen.lunwen_id}')">
			<td>${lunwen.lunwen_name }</td>
			<td>${lunwen.lunwen_leibie }</td>
			<td>${lunwen.lunwen_guanjianci }</td>
		</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function getDetail(lunwenId){
			window.location.href="<%=request.getContextPath()%>/lunwen/detail?lwid="+lunwenId;
		}
	</script>
</body>
</html>