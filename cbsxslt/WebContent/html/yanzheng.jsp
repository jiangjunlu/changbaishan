<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/error.css" />
	<style type="text/css">
		#val{
			color: red;
		}
	</style>
</head>

<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="container">
		<div class="msg">${resultInfo },本页面<span id="val">5</span>秒后自动关闭。</div>
	</div>
</body>
<script type="text/javascript">
	function crl(){
		var time=document.getElementById("val").innerHTML;
		document.getElementById("val").innerHTML=time-1;
		if(time==1){
			window.opener=null;window.open('','_self');window.close();
		}
	}
	setInterval('crl()',1000);
</script>
</html>