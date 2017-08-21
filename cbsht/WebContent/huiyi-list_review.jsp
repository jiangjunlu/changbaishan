<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>huiyi-list</title>
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
	/* 	height: 500px; */
	/*  margin: 100px 230px;*/
	/*border: 1px solid black;*/
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

.search-big { /*background: cornflowerblue;*/
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
			content : 'cct-table.html'
		});
	}
</script>
</head>

<body>
	<div class="table-custome">
		<div>
			<div>
				<a href="<%=request.getContextPath()%>/huiyi/new?p=${p}">添加会议</a>
			</div>
			<div>
				<form action="<%=request.getContextPath()%>/huiyi/search?p=${page}"
					method="post">
					<input type="text" class="box inputText" name="s"
						onkeyup="selectItem(this.value, event)" id="txtKeyword"
						placeholder="请输入要搜索会议的中文或者英文名称" value="${s}"/> <input type="submit" value="查询"
						class="search-button" />
					<ul id="ulItems"></ul>
				</form>
			</div>
		</div>

		<table class="table table-striped table-hover table-bordered">
			<tr>
				<td id="td1">会议编号</td>
				<td id="td2">中文名称</td>
				<td id="td3">英文名称</td>
				<td id="td4">摘要</td>
				<td id="td6">操作</td>
				
			</tr>
			<c:forEach items="${list }" var="hy">
				<tr>
					<td>${hy.huiyi_id }</td>
					<td>${fn:substring(hy.zhongwen_name,0,8) }<c:if
							test="${fn:length(hy.zhongwen_name)>8  }">...</c:if></td>
					<td>${fn:substring(hy.yingwen_name,0,16) }<c:if
							test="${fn:length(hy.yingwen_name)>16  }">...</c:if></td>
					<td>${fn:substring(hy.huiyi_zhaiyao,0,20) }<c:if
							test="${fn:length(hy.huiyi_zhaiyao)>20  }">...</c:if></td>
					<td>
						<a href="<%=request.getContextPath()%>/huiyi/list_review_Up?id=${hy.huiyi_id }&p=${page }">
							<c:if test="${hy.huiyi_review==1}">
								不通过
							</c:if>
							<c:if test="${hy.huiyi_review==0}">
								通过
							</c:if>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<form id="ds" action="<%=request.getContextPath()%>/huiyi/list_review"
			method="post">
			<table>
				<tr>
					<td><a
						href="<%=request.getContextPath() %>/huiyi/list_review?p=${page-1}&s=${s}">上一页</a></td>
					<td>第<input id="p" type="text" name="p" value="${page}">/${pages}页<input
						type="submit" value="转到"></td>
					<td><a
						href="<%=request.getContextPath() %>/huiyi/list_review?p=${page+1}&s=${s}">下一页</a></td>
				</tr>
			</table>
		</form>

	</div>

</body>
</html>