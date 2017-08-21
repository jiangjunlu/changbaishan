<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/layer/layer/layer.js"></script>
<style>
.table-custome {
	position: relative;
	width: 100%;
	overflow: hidden;
}

#ds #p {
	width: 30px;
	text-align: center;
}

.table {
	position: relative;
	top: 15px;
}

.table th, .table td {
	text-align: center;
	height: 38px;
}

tr:hover {
	background-color: aliceblue;
}

a:hover {
	cursor: pointer;
}

.search-big {
	height: 50px;
	width: 600px;
	position: absolute;
	right: 10px;
}

.search {
	position: absolute;
	top: 10px;
	right: 10px;
}

.search input:first-child {
	width: 360px;
}

.search input {
	height: 30px;
	font-size: 14px;
}
</style>
<script>
	function openhtml() {
		layer.open({
			type : 2,
			title : false,
			closeBtn : 1, //不显示关闭按钮
			shade : [ 0 ],
			offset : [ '10px', '' ],
			//					maxmin: true, //开启最大化最小化按钮
			area : [ '893px', '600px' ],
			content : 'zcfg-table.html'
		});
	}
</script>
</head>

<body>
	<div class="table-custome">
		<div>
			<div>
				<form action="<%=request.getContextPath()%>/user/search?p=${page}"
					method="post">
					<input type="text" class="box inputText" name="s"
						onkeyup="selectItem(this.value, event)" id="txtKeyword"
						placeholder="请输入要搜索的用户" value="${s }" /> <input type="submit"
						value="查询" class="search-button" />
					<ul id="ulItems"></ul>
				</form>
			</div>
		</div>

		<table class="table table-striped table-hover table-bordered">
			<tr>
				<th>用户编号</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>电话</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${users }" var="us">
				<tr>
					<td>${us.userid }</td>
					<td>${us.username }</td>
					<td>${us.name }</td>
					<td>${us.phone }</td>
					<td><a href="<%=request.getContextPath()%>/user/del?id=${us.userid }&p=${page }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function doSubmit(index) {
			if (index == -1) {
				var va = document.getElementById('nowpage').value;
				if (va == "") {
					va = 1;
				}
				document.getElementById('nowpage2').value = va;
			} else {
				document.getElementById('nowpage2').value = index;
			}
			document.getElementById('ds').submit();
		}
	</script>
	<form id="ds" action="<%=request.getContextPath()%>/user/list?s=${s}"
		method="post">
		<table>
			<tr>
				<td><a
					href="<%=request.getContextPath() %>/user/list?p=${page-1}&s=${s}">上一页</a></td>
				<td>第<input id="p" type="text" name="p" value="${page}">/${pages}页<input
					type="submit" value="转到"></td>
				<td><a
					href="<%=request.getContextPath() %>/user/list?p=${page+1}&s=${s}">下一页</a></td>
			</tr>
		</table>
	</form>
</body>
</html>