<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>

		<!--<link rel="stylesheet" href="css/mode2.css" />-->
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/layer/layer/layer.js"></script>
		<style>
			.td-width {
				width: 30%;
			}
		</style>
		<script>
		<%if(request.getAttribute("msg")!=null){
			String msg=request.getAttribute("msg").toString();
			if("reload".equals(msg)){%>
				window.parent.location='<%=request.getContextPath()%>/login.jsp';
		<%	}else{%>
			alert('<%=msg%>');
		<%}
		}
		%>
			function c() {
				var updatediv = document.getElementById("update");
				updatediv.style.display = "none";
				parent.document.documentElement.scrollTop = parent.document.body.scrollTop = 0;
				document.getElementById("studentbody").style.opacity = 1;
				document.getElementById("studentbody").style.filter = "alpha(opacity = 100)";
				document.getElementById("studentbody").style.backgroundColor = "#fff";
			}
			function doSubmit() {
				if(textA()&&textB()){
					$('#frm1').submit();
				}
			}
			function textA(){
				var t1=document.getElementById("passwordB").value.toLowerCase();
				var t2=document.getElementById("ri1");
				var t=/^\w{6,20}$/;
				if(t.test(t1)){
					t2.innerHTML="√";
					return true;
				}
				else{
					t2.innerHTML="您的密码需要6到20位";
					return false;
				}
				
			}
			function textB(){
				var t1=document.getElementById("passwordB").value.toLowerCase();
				var t2=document.getElementById("passwordc").value.toLowerCase();
				var t3=document.getElementById("ri");
				if(t1!=t2){
					t3.innerHTML="×";
					return false;
				}
				else{
					t3.innerHTML="√";
					return true;
				}
			}
		</script>
	</head>

	<body>
		<form id="frm1" class="suqiu-form" style="position:absolute;top:0px;left:10%;width: 80%;" action="<%=request.getContextPath() %>/user/update" method="post">
			<div class="templatemo-content-container" id="update" >
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table class='tableBorder table  table-striped table-bordered templatemo-user-table' align=center>
							<!--<img src="img/closediv.png" onclick="c()" style="float:right;height:40px;width:40px" />-->

							<tr>
								<th class='tableHeaderText green-bg white-text' colspan=2 height=25>修改密码</th>
								<tr>
									<td height=23 colspan="2" class='forumRow'>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								
								<tr>
									<td height="10">&nbsp;</td>
								</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td class="td-width" >原密码</td>
								<td class='forumRow'>
									<div class="form-group">
										<input name='passwordA' type="password" id='a_title' class="form-control">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td-width" >新的密码</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='nongshi_tixing_neirong' type='text' id='miaoshu' class="form-control">-->
											<input name='passwordB' class="form-control" id="passwordB" type="password" onblur="textA()"><span id="ri1"></span>
									</div>
								</td>

							</tr>
							<tr>
								<td class="td-width" >请重新输入您的密码</td>
								<td class='forumRow'>
									<div class="form-group">
										<!--<input name='nongshi_tixing_neirong' type='text' id='miaoshu' class="form-control">-->
											<input name='passwordc' id="passwordc" class="form-control" type="password" onblur="textB()"><span id="ri"></span>
									</div>
								</td>

							</tr>
							<tr>
								<td height="50" colspan=2 class='forumRow'>
									<div align="center">
										<div class="form-group">
											<input onclick="doSubmit()" class="templatemo-blue-button" type="button" value="提交">
											<input class="templatemo-blue-button" type="button" value="取消" onclick="javascript:window.location.href ='welcome.html';">
										</div>
									</div>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</form>
</body>
</html>