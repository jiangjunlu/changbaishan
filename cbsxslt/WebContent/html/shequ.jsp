<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>学术社区</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<style type="text/css">
#daohang li {
	height: 30px;
	line-height: 30px;
	padding: 3px;
	width: 100%;
	text-align: center;
	font-size: 20px;
	font-family: hei;
	font-weight: bold;
	background: #f1f1f1;
}

#daohang li:HOVER {
	background: #f9f9f9;
	cursor: pointer;
}
</style>
</head>
<body
	style="background:url(<%=request.getContextPath()%>/img/hui.png)repeat;">
	<div class="din"
		style="position: fixed; top: 0px; left: 0px; width: 100%;height: 28px; font-size: 18px; line-height: 28px;">
		<a href="<%=request.getContextPath()%>/all/index" class="a1">首页/</a>
		<a href="<%=request.getContextPath()%>/all/moreMeeting" class="a1">学术论坛会议/</a>
		<a class="a1">学术社区/</a>
	</div>
	<!--正文  -->
	<div
		style="width: 800px; height: 600px; background: #f9f9f9; margin: 70px auto;">
		<!--导航  -->
		<div
			style="background: #f1f1f1;z-index: 0; overflow: auto; height: 600px; border-right: 3px #bbb solid; float: left; width: 230px; height: 100%;">
			<ul style="list-style: none" id="daohang">
				<li
					style="color: #fea281; background-color: #fbfbfb; font-family: sim-hei; font-size: 35px; font-weight: bolder; height: 50px; line-height: 50px;">社区名称</li>
				<c:forEach var="particle" items="${particles }">
					<li onclick="getTxtName('${particle.part.shequ_id }')">${particle.part.shequ_name }</li>
				</c:forEach>
			</ul>
		</div>
		<!--内容 -->
		<div  style="float: right; width: 560px; height: 600px;">
			<iframe id="context" style="width: 560px; height:600px;" name="txtname" > </iframe>
		</div>

	</div>
	<script type="text/javascript">
		function getTxtName(shequId){
			document.getElementById("context").src="<%=request.getContextPath()%>/lunwen/all?sqid="+shequId;
		}
	</script>
<c:if test="${!empty fun }">
	<script>
		if("${fun}"=="lw"){
			document.getElementById("context").src="<%=request.getContextPath()%>/lunwen/detail?lwid="+"${lwid}";
		}else if("${fun}"=="sq"){
			document.getElementById("context").src="<%=request.getContextPath()%>/lunwen/all?sqid="+"${sqid}";
		}
	</script>
</c:if>
</body>
</html>