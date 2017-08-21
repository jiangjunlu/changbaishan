<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>huiyi-table</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jedate/jedate.js"></script>
		<style>
			.td-width {
				width: 30%;
			}
		</style>
		<script>
			function c() {
				var updatediv = document.getElementById("update");
				updatediv.style.display = "none";
				parent.document.documentElement.scrollTop = parent.document.body.scrollTop = 0;
				document.getElementById("studentbody").style.opacity = 1;
				document.getElementById("studentbody").style.filter = "alpha(opacity = 100)";
				document.getElementById("studentbody").style.backgroundColor = "#fff";
			}
			function doSubmit() {
				var zwn=$("#zwn").val();
				var ywn=$("#ywn").val();
				if(zwn==""&&ywn==""){
					alert("会议中文名称、英文名称，至少填写一个");
					return ;
				}
				var ztime=$("#zkt").val();
				var jtime=$("#jst").val();
				var time_format=/\d{4}-\d{2}-\d{2}\s+\d{2}:\d{2}:\d{2}/;
				if(!time_format.test(ztime)){
					alert("召开时间格式不正确");
					return;
				}
				if(!time_format.test(jtime)){
					alert("结束时间格式不正确");
					return;
				}
				if(ztime>jtime){
					alert("会议时间冲突！");
					return;
				}
				$('#frm1').submit();
			}
		</script>
	</head>
	<body>
	<c:if test="${tableType=='update' }">
		<form id="frm1" class="suqiu-form"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/huiyi/update?id=${hy.huiyi_id }&p=${p}&userid=${user.userid}" method="post">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>会议详细信息</th>
							<tr>
								<td height=23 colspan="2" class='forumRow'>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="10">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议编号</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='id' type='text' id='a_title'
											class="form-control" value="${hy.huiyi_id }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">中文名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input id="zwn" name='zwn' type='text' id='a_title'
											class="form-control" value="${hy.zhongwen_name }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">英文名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input id="ywn" name='ywn' type='text' id='a_title'
											class="form-control" value="${hy.yingwen_name }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">召开时间</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" id="zkt" name='zkt' type='text' id='a_title'
											class="form-control" value="${hy.zhaokai_time }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">结束时间</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" id="jst" name='jst' type='text' id='a_title'
											class="form-control" value="${hy.jieshu_time }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议地点</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='hydd' type='text' id='a_title'
											class="form-control" value="${hy.huiyi_didian }">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width">关键词</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='hygjc' type='text' id='a_title'
											class="form-control" value="${hy.huiyi_guanjianci }">
									</div>
								</td>
							</tr>
							
							<tr>
								<td class="td-width">状态</td>
								<td class='forumRow'>
									<div class="form-group">
										<select name="hyzt">
											<option id="op0" value="0">未召开</option>
											<option id="op1" value="1">已召开</option>
											<option id="op2" value="2">已过期</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">主办方</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="zbf${jg.jigou_id }" name="hyzbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">协办方</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="xbf${jg.jigou_id }" name="hyxbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">主要参加单位</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="lbf${jg.jigou_id }" name="hylbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							
							<c:forEach var="zbf" items="${hy.huiyi_zhubanfang }">
							<script>
							$('#zbf'+${zbf.jigou_id}).attr("checked",true);
							</script>
							</c:forEach>
							<c:forEach var="xbf" items="${hy.huiyi_xiebanfang }">
							<script>
							$('#xbf'+${xbf.jigou_id}).attr("checked",true);
							</script>
							</c:forEach>
							<c:forEach var="lbf" items="${hy.huiyi_laibanfang }">
							<script>
							$('#lbf'+${lbf.jigou_id}).attr("checked",true);
							</script>
							</c:forEach>
							<tr>
								<td class="td-width">会议摘要</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="hyzy"
											style="width: 100%; height: 100px;" class="ckeditor">${hy.huiyi_zhaiyao }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="hynr"
											style="width: 100%; height: 100px;" class="ckeditor">${hy.huiyi_neirong }</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button"
												type="button" value="提交"> <input
												class="templatemo-blue-button" type="button" value="取消"
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/huiyi/list?p=${p}';">
										</div>
									</div>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</form>
		<script>
		$("#op"+${hy.huiyi_zhuangtai}).attr("selected",true);
		</script>
	</c:if>
	<c:if test="${tableType!='update' }">
	<form id="frm1" class="suqiu-form"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/huiyi/insert?id=${hy.huiyi_id }&p=${p}&userid=${user.userid}" method="post">
		<div class="templatemo-content-container" id="update">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>会议详细信息</th>
							<tr>
								<td height=23 colspan="2" class='forumRow'>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="10">&nbsp;</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="td-width">中文名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zwn' type='text' id='a_title'
											class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">英文名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='ywn' type='text' id='a_title'
											class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">召开时间</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" id="zkt" name='zkt' type='text' id='a_title'
											class="form-control" >
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">结束时间</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" id="jst" name='jst' type='text' id='a_title'
											class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议地点</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='hydd' type='text' id='a_title'
											class="form-control" value="${hy.huiyi_didian }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">关键词</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='hygjc' type='text' id='a_title'
											class="form-control" value="${hy.huiyi_guanjianci }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">主办方</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="zbf${jg.jigou_id }" name="hyzbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">协办方</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="xbf${jg.jigou_id }" name="hyxbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">主要参加单位</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="jg" items="${jigou }">
										<input type="checkbox" id="lbf${jg.jigou_id }" name="hylbf" value="${jg.jigou_id }">${jg.jigou_name }、
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议摘要</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="hyzy"
											style="width: 100%; height: 100px;" class="ckeditor"></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">会议内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="hynr"
											style="width: 100%; height: 100px;" class="ckeditor"></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button"
												type="button" value="提交"> <input
												class="templatemo-blue-button" type="button" value="取消"
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/huiyi/list?p=${p}';">
										</div>
									</div>
								</td>
							</tr>
						</table>

				</div>
			</div>
		</div>
	</form>
	</c:if>
	<script>
	
	jeDate({
		dateCell:"#zkt",
		format:"YYYY-MM-DD hh:mm:ss",
		isinitVal:true,
		isTime:true, //isClear:false,
		minDate:"2000-01-01 00:00:00",
		maxDate:"2050-12-30 23:59:59",
		okfun:function(val){alert(val+"不合法");document.getElementById("zkt").value="2017-01-01 00:00:00";}
	});
    jeDate({
		dateCell:"#jst",
		format:"YYYY-MM-DD hh:mm:ss",
		isinitVal:true,
		isTime:true, //isClear:false,
		minDate:"2000-01-01 00:00:00",
		maxDate:"2050-12-30 23:59:59",
		okfun:function(val){alert(val+"不合法");document.getElementById("jst").value="2017-01-01 00:00:00";}
	});
	</script>
</body>
</html>