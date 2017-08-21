<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>注册页面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/register.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
</head>
<c:if test="${empty meetingInfo }">
<script type="text/javascript">
alert('没有选择会议！');
	window.location.href="<%=request.getContextPath()%>/allInfo/readIndexInfo";
</script>
</c:if>
<c:if test="${!empty resultInfo }">
	<script type="text/javascript">
		if("${resultInfo}"=="success"){
		alert("感谢您注册 长白山学术论坛，系统给您发送了一封带有注册地址的邮件，快去登录邮箱获取注册链接进行下一步注册吧");
		 window.location.href="<%=request.getContextPath()%>/all/meetingDetail?id="+"${meetingInfo.huiyi_id}";
		}else{
			alert("注册时出错");
		};
	</script>
</c:if>
<body>
  <div class="container">  
  <form id="contact" action="<%=request.getContextPath()%>/participant/register" method="post">
    <fieldset>
    <h3>注册会议</h3><h5>${meetingInfo.zhongwen_name }(${meetingInfo.huiyi_id })</h5>
    <h5>${meetingInfo.yingwen_name }</h5>
    <h4>请填写个人信息</h4>
    <input type="hidden" value="${meetingInfo.huiyi_id }" name="huiyiId"> 
    <input type="hidden" value="${meetingInfo.zhaokai_time }" name="beginDate"> 
    <input type="hidden" value="${meetingInfo.jieshu_time }" name="endDate"> 
    <p>姓名：</p>
    <input  name="renyuanName" placeholder="" type="text" required autofocus>
    <p>电话：</p>
      <input  name="renyuanDianhua" placeholder="" type="tel" required>
    <p>日程：开始日期</p>
      <input name="beginDate" id="startdate" class="Wdate" type="Wdate" />   
    <p>日程：结束日期</p>
      <input name="endDate" id="enddate" class="Wdate" type="Wdate" />   
    <p>Email：</p>
      <input  name="renyuanEmail" placeholder="" type="email" required>   
    <p>备注：</p>
      <textarea  name="renyuanBeizhu" placeholder="" required></textarea>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">点击注册</button>
    </fieldset>
  </form>
</div>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/addLoadEvent.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/kongjianarea/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var start1 ="${fn:substring(meetingInfo.zhaokai_time,0,10)}";
	var start2 ="${fn:substring(meetingInfo.jieshu_time,0,10)}";
	var end1 ="${fn:substring(meetingInfo.zhaokai_time,0,10)}";
	var end2 = "${fn:substring(meetingInfo.jieshu_time,0,10)}";
	
	$("#startdate").click(function(){
		WdatePicker({minDate:start1,maxDate:start2});
	});
	$("#enddate").click(function(){
		if($("#startdate").val()!=""){
			end1=$("#startdate").val();
		}
		WdatePicker({minDate:end1,maxDate:end2});
	});
});
</script>
</html>