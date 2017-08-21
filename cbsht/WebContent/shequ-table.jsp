<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>shequ-table</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
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
				$('#frm1').submit();
			}
		</script>
	</head>
	<body>
	<c:if test="${tableType=='update' }">
		<form id="frm1" class="suqiu-form"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/shequ/update?id=${sq.shequ_id}&p=${page}" method="post">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>学术社区</th>
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
								<td class="td-width">社区编号</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" name='id' type='text' id='a_title'
											class="form-control" value="${sq.shequ_id}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">社区名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control"  name="sqn" value="${sq.shequ_name}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">社区简介</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="sqjj"
											style="width: 100%; height: 100px;" class="ckeditor">${sq.shequ_jianjie}</textarea>
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
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/shequ/list?p=${p}';">
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
	<c:if test="${tableType!='update' }">
	<form id="frm1" class="suqiu-form"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/shequ/insert?p=${p}" method="post">
		<div class="templatemo-content-container" id="update">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>学术社区</th>
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
								<td class="td-width">社区名称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control"  name="sqn" value="${sq.shequ_name}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">社区简介</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="sqjj"
											style="width: 100%; height: 100px;" class="ckeditor">${sq.shequ_jianjie}</textarea>
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
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/shequ/list?p=${p}';">
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
</body>
</html>