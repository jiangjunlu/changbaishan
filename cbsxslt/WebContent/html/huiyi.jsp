<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name=viewport content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/huiyi.css">
<title>学术论坛会议</title>
<style type="text/css">
.huiyi-jianjie p{
	margin: 0px;
	padding:0px;
	display: inline;
}
</style>
</head>
<body>
	<!-- 背景 -->
	<div class="whole">

		<!-- 条条 -->
		<div class="din"
			style="position: fixed; top: 0px; left: 0px; width: 100%;height: 28px; font-size: 18px; line-height: 28px;">
			<a href="<%=request.getContextPath()%>/all/index" class="a1">首页/</a> <a
				href="#" class="a1">学术论坛会议/</a> <a
				href="<%=request.getContextPath()%>/all/queryPa" class="a1">学术社区/</a>
		</div>
		<!-- 白边 -->
		<div class="tiaotiao"></div>
		<!-- 整体 -->
		<div class="qishi" style="margin-top: 50px">
			<c:forEach var="huiyi" items="${meetingInfo }">
				<div class="every">
			
					<c:if test="${!empty huiyi.zhaokai_time && !empty huiyi.jieshu_time}">
					<!-- 举办周期 -->
					<div class="j-div1">
						<p class="j-123">${fn:substring(huiyi.zhaokai_time,0,10) }</p>
						<p class="j-123">-</p>
						<p class="j-123">${fn:substring(huiyi.jieshu_time,0,10) }</p>
					</div>
					</c:if>
					<!-- 主题 -->
					<div class="j-div2">
						<div class="huiyi-mingcheng">
							<a href="<%=request.getContextPath()%>/all/meetingDetail?id=${huiyi.huiyi_id}"> 
							<c:choose> 
						     <c:when test="${fn:length(huiyi.zhongwen_name) > 10}"> 
						      <c:out value="${fn:substring(huiyi.zhongwen_name, 0, 9)}..." /> 
						     </c:when> 
						     <c:otherwise> 
						      <c:out value="${huiyi.zhongwen_name}" /> 
						     </c:otherwise>
						    </c:choose>
								<c:if test="${(!empty huiyi.yingwen_name) && (fn:length(huiyi.zhongwen_name)+fn:length(huiyi.yingwen_name)>30)}">
								 (${fn:substring(huiyi.yingwen_name,0,19) }...)
								 </c:if>
								<c:if test="${(!empty huiyi.yingwen_name) && (fn:length(huiyi.zhongwen_name)+fn:length(huiyi.yingwen_name)<=30)}">
								 (${huiyi.yingwen_name })
								 </c:if>
							</a>
							
						</div>
						<div class="huiyi-didian">
							&nbsp;&nbsp;&nbsp;地点: <span>${fn:substring(huiyi.huiyi_didian,0,8) }</span>
						</div>
						<div class="huiyi-jianjie">
							<h3 style="display: inline;">摘要:&nbsp;&nbsp;</h3>${fn:substring(huiyi.huiyi_zhaiyao,0,30) }
						</div>

					</div>
					<!-- 注册链接 -->
					<!--暂时用状态来判断
						以后根据具体需求  改成开始时间或者结束时间
					  -->
					 <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd hh:mm:ss" var="bb"/>
				<%-- 	<c:if test="${huiyi.zhaokai_time > bb }"> --%>
					<c:if test="${huiyi.huiyi_zhuangtai == 0 }">
					<div class="j-div3"
						onclick="javascript:location.href='<%=request.getContextPath()%>/participant/setInfo?id=${huiyi.huiyi_id}'"
						style="cursor: pointer;">
						去报名
					</div>
					</c:if>
				</div>
			</c:forEach>
			<c:if test="${empty meetingInfo }">
				真是抱歉  没有符合要求的会议了~~~
			</c:if>
		</div>
	</div>
</body>
</html>