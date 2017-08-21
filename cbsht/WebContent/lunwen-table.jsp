<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>lunwen-table</title>
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
			action="<%=request.getContextPath() %>/lunwen/update?id=${lw.lunwen_id}&p=${p}" method="post">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>论文</th>
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
								<td class="td-width">论文编号</td>
								<td class='forumRow'>
									<div class="form-group">
										<input readonly="readonly" name='id' type='text' id='a_title'
											class="form-control" value="${lw.lunwen_id}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">类别</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwlb" value="${lw.lunwen_leibie}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">关键词</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwgjc" value="${lw.lunwen_guanjianci}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">上传地址-（暂留）</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwsp" value="${lw.lunwen_savepath}">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">所属社区</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="sysq" items="${sysq }">
										<input type="checkbox" id="sysq${sysq.shequ_id }" name="sssq" value="${sysq.shequ_id }">${sysq.shequ_name }&nbsp;
										</c:forEach>
									</div>
								</td>
							</tr>
							<c:forEach var="sssq" items="${lw.sssq }">
							<script>
							$('#sysq'+${sssq.shequ_id}).attr("checked",true);
							</script>
							</c:forEach>
							<tr>
								<td class="td-width">论文摘要</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt"  name="lwzy"
											style="width: 100%; height: 100px;" class="ckeditor">${lw.lunwen_zhaiyao}</textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">状态</td>
								<td class='forumRow'>
									<div class="form-group">
										<select name="lwclzt">
											<option id="op0" value="0">未审核</option>
											<option id="op1" value="1">已通过</option>
											<option id="op2" value="2">已拒绝</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">论文内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt"  name="lwzy"
											style="width: 100%; height: 100px;" class="ckeditor">${lw.lunwen_neirong}</textarea>
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
		<script>
		$("#op"+${lw.lunwen_chuli_zhuangtai}).attr("selected",true);
		</script>
	</c:if>
	<c:if test="${tableType!='update' }">
	<form id="frm1" class="suqiu-form"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/lunwen/insert?p=${page}" method="post">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>论文</th>
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
								<td class="td-width">类别</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwlb">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">关键词</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwgjc">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">上传地址-（暂留）</td>
								<td class='forumRow'>
									<div class="form-group">
										<input type='text' id='a_title'
											class="form-control" name="lwsp">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">所属社区</td>
								<td class='forumRow'>
									<div class="form-group">
										<c:forEach var="sysq" items="${shequ }">
										<input type="checkbox" id="sysq${sysq.shequ_id }" name="sssq" value="${sysq.shequ_id }">${sysq.shequ_name }&nbsp;
										</c:forEach>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">论文摘要</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt"  name="lwzy"
											style="width: 100%; height: 100px;" class="ckeditor"></textarea>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">论文内容</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt"  name="lwzy"
											style="width: 100%; height: 100px;" class="ckeditor">${lw.lunwen_neirong}</textarea>
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
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/lunwen/list?p=${p}';">
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