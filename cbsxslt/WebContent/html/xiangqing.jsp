<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset=utf-8>
<meta name=viewport content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/xiangqing.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<title>会议详情</title>
<style type="text/css">
body,table,td,th{
	margin:0px;
	padding:0px;
}
table{
	width:800px;
	background-color:#f9f9f9;
	margin-left:auto;
	margin-right:auto;
	margin-top:30px;
	/* font-family: SimHei; */
}
/* tr:HOVER{
	color:#333;
	cursor: pointer;
} */
th{
	text-align:center;
	/* border-bottom:1px solid #09c; */
	height: 60px;
	width: 500px;
	color: #F96;
	font-size: 30px;
	font-weight: 400;
}
td {
	padding-left:10px;
	/* border-bottom:1px solid #09c; */
	height: 40px;
	font-size:15px;
}
</style>
</head>
<body style="background:url(<%=request.getContextPath()%>/img/hui.png)repeat;">
	<!-- 回到首部的猫链接 -->
	<a href="top"></a>
	<!-- 条条 -->
	<div class="din"
		style="height: 50px; font-size: 18px; line-height: 50px;">
		<a href="<%=request.getContextPath()%>/all/index" class="a1">首页/</a>
		<a href="<%=request.getContextPath()%>/all/moreMeeting" class="a1">学术论坛会议/</a>
		<a href="<%=request.getContextPath()%>/all/queryPa" class="a1">学术社区/</a>
	</div>
	<div class="whole">
		<table   border="1"  bordercolor="#09c">
			<tr>
				<th colspan="2">会议内容详情</th>
			</tr>
			<tr>
				<td>中文主题</td>
				<td>${meetingDetail.zhongwen_name }</td>
			</tr>
			<tr>
				<td>英文主题</td>
				<td>${meetingDetail.yingwen_name }</td>
			</tr>
			<tr>
				<td>召开时间</td>
				<td>${fn:substring(meetingDetail.zhaokai_time,0,16) }&nbsp;&nbsp;———&nbsp;&nbsp;${fn:substring(meetingDetail.jieshu_time,0,16) }</td>
			</tr>
			<tr>
				<td>会议内容</td>
				<td><div>${meetingDetail.huiyi_neirong }</div></td>
			</tr>
			<tr>
				<td>会议摘要</td>
				<td><div>${meetingDetail.huiyi_zhaiyao }</div></td>
			</tr>
			<tr>
				<td>召开地点</td>
				<td><div>${meetingDetail.huiyi_didian }</div></td>
			</tr>
			<tr>
				<td>会议状态</td>
				<td>
				<c:if test="${meetingDetail.huiyi_zhuangtai==0 }">
					未召开
				</c:if>
				<c:if test="${meetingDetail.huiyi_zhuangtai==1 }">
					已结束
				</c:if>
				<c:if test="${meetingDetail.huiyi_zhuangtai==2 }">
					已过期
				</c:if>
				</td>
			</tr>
			<tr>
				<td>关键词</td>
				<td>
				<c:forEach var="gjc" items="${meetingDetail.huiyi_guanjianci }" varStatus="status">
				<c:if test="${status.index>0 }">
				、
				</c:if>
				${gjc }
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>主要参加单位</td>
				<td>
					<c:forEach var="zbf" items="${meetingDetail.huiyi_zhubanfang }" varStatus="status">
						<c:if test="${status.index>0 }">
						、
						</c:if>
						<a style="display: inline;" target="blank" href="${zbf.jigou_wangzhi }" title="${zbf.jigou_beizhu }">${zbf.jigou_name }</a>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>协办方</td>
				<td>
				<c:forEach var="xbf" items="${meetingDetail.huiyi_xiebanfang }" varStatus="status">
					<c:if test="${status.index>0 }">
					、
					</c:if>
					<a style="display: inline;" target="blank" href="${xbf.jigou_wangzhi }" title="${xbf.jigou_beizhu }">${xbf.jigou_name }</a>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>来办方</td>
				<td>
				<c:forEach var="lbf" items="${meetingDetail.huiyi_laibanfang }" varStatus="status">
					<c:if test="${status.index>0 }">
					、
					</c:if>
					<a style="display: inline;" target="blank" href="${lbf.jigou_wangzhi }" title="${lbf.jigou_beizhu }">${lbf.jigou_name }</a>
				</c:forEach>
				</td>
			</tr>
		</table>
		<c:if test="${meetingDetail.huiyi_zhuangtai == 0 }">
		<div onclick="javascript:location.href='<%=request.getContextPath()%>/participant/setInfo?id=${meetingDetail.huiyi_id}'"
			style="cursor: pointer;text-align: center;width:800px;margin-left:auto;margin-right:auto;">
				<h3>去报名</h3>
		</div>
		</c:if>
	</div>
</body>
</html>