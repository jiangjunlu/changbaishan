<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jiabin-table</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/templatemo-style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jedate/jedate.js"></script>
<style>
.td-width {
	width: 30%;
}
</style>
<script>
function setImagePreview(avalue) { 
	var docObj=document.getElementById("doc"); 
	  
	var imgObjPreview=document.getElementById("preview"); 
	if(docObj.files &&docObj.files[0]) 
	{ 
	//火狐下，直接设img属性 
	imgObjPreview.style.display = 'block'; 
	imgObjPreview.style.width = '150px'; 
	imgObjPreview.style.height = '180px';  
	//imgObjPreview.src = docObj.files[0].getAsDataURL(); 
	  
	//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
	imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
	} 
	else
	{ 
	//IE下，使用滤镜 
	docObj.select(); 
	var imgSrc = document.selection.createRange().text; 
	var localImagId = document.getElementById("localImag"); 
	//必须设置初始大小 
	localImagId.style.width = "150px"; 
	localImagId.style.height = "180px"; 
	//图片异常的捕捉，防止用户修改后缀来伪造图片 
	try{ 
	localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; 
	localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc; 
	} 
	catch(e) 
	{ 
	alert("您上传的图片格式不正确，请重新选择!"); 
	return false; 
	} 
	imgObjPreview.style.display = 'none'; 
	document.selection.empty(); 
	} 
	return true; 
	} 
	function c() {
		var updatediv = document.getElementById("update");
		updatediv.style.display = "none";
		parent.document.documentElement.scrollTop = parent.document.body.scrollTop = 0;
		document.getElementById("studentbody").style.opacity = 1;
		document.getElementById("studentbody").style.filter = "alpha(opacity = 100)";
		document.getElementById("studentbody").style.backgroundColor = "#fff";
	}
	function doSubmit() {
		var jn = $("#jn").val();
		/* alert(jn); */
		if (jn == "") {
			alert("嘉宾姓名不能为空");
			return;
		}
		$('#frm1').submit();

	}
</script>
</head>
<body>
	<c:if test="${tableType=='update' }">
		<!-- 修改 -->
		<form id="frm1" class="suqiu-form" enctype="multipart/form-data"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/jiabin/update?id=${jb.jiabin_id }&p=${p}"
			method="post">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>嘉宾详细信息</th>
							</tr>
							<tr>
								<td class="td-width">嘉宾姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jn' type='text' id='jn' class="form-control"
											value="${jb.jiabin_name }">
									</div>
								</td>
							</tr>
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
								<td class="td-width">嘉宾领域</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='ly' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_lingyu }">
									</div>
								</td>
							</tr>

							<tr>
								<td class="td-width">嘉宾机构</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jg' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_jigou }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">参加活动</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='cjhd' type='text' id='a_title'
											class="form-control" value="${jb.jiabin_canyuhuodong }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾职称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zc' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_zhicheng }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾照片</td>
								<td class='forumRow'>
									<div class="form-group">
									<img alt="" id="preview" src="<%=request.getContextPath().substring(0,request.getContextPath().lastIndexOf("/"))%>/cbsxslt/jiabin_img/${jb.jiabin_zhaopian}">
										<input name='zp' type='file' id='doc'  onchange="javascript:setImagePreview();" class="form-control"
										>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾职务</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zw' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_zhiwu }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾简介</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="jj"
											style="width: 100%; height: 100px;" class="ckeditor">${jb.jiabin_jianjie}</textarea>
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
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/jiabin/list?p=${p}';">
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
		<!-- 添加 -->

		<form id="frm1" class="suqiu-form" enctype="multipart/form-data"
			style="position: absolute; top: 0px; left: 10%; width: 80%;"
			action="<%=request.getContextPath()%>/jiabin/insert?id=${jb.jiabin_id }&p=${p}"
			method="post" enctype="multipart/form-data">
			<div class="templatemo-content-container" id="update">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class='tableBorder table  table-striped table-bordered templatemo-user-table'
							align=center>

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2
									height=25>嘉宾详细信息</th>
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
								<td class="td-width">嘉宾姓名</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jn' type='text' id='jn' class="form-control"
											value="${jb.jiabin_name }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾领域</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='ly' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_lingyu }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾机构</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='jb' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_jigou }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">参与活动</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='cjhd' type='text' id='a_title'
											class="form-control" value="${jb.jiabin_canyuhuodong }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾职称</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zc' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_zhicheng }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾照片</td>
								<td class='forumRow'>
									<div class="form-group">
									<img alt="" id="preview" src="">
										<input name='zp' type='file' id='doc'  onchange="javascript:setImagePreview();" class="form-control"
										>
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾职务</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='zw' type='text' id='a_title' class="form-control"
											value="${jb.jiabin_zhiwu }">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width">嘉宾简介</td>
								<td class='forumRow'>
									<div class="form-group">
										<textarea id="txt" name="jj"
											style="width: 100%; height: 100px;" class="ckeditor">${jb.jiabin_jianjie}</textarea>
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
												onclick="javascript:window.location.href ='<%=request.getContextPath()%>/jiabin/list?p=${p}';">
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