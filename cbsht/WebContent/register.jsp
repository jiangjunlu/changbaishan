<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register</title>
		<link rel="stylesheet" href="css/register.css" />
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
			function test() {
				var t1 = document.getElementById("user_yonghuming_id").value.toLowerCase();
				var t2 = document.getElementById("ln");
				var t = /^\w{6,20}$/;
				if(t.test(t1)) {
					$.ajax({
						url: '<%=request.getContextPath()%>/user/check',
						type: 'POST',
						data: {
							"username": t1
						},
						type: 'post',
						dataType: 'json',
						success: function(data) {
							if(data.msg == "exist") {
								t2.innerHTML = "X";
							} else {
								t2.innerHTML = "√";
							}
						},
						error: function(data) {
							alert("error:"+data.val);
						}
					});
				} else {
					t2.innerHTML = "最少6个数字、字母";
				}
			}

			function check2() {
				
				var t1 = document.getElementById("Password").value.toLowerCase();
				var t2 = document.getElementById("Password2").value.toLowerCase();
				var t3 = document.getElementById("ri");
				if(t1 != t2) {
					t3.innerHTML = "X";
					return false;
				} else {
					t3.innerHTML = "√";
					return true;
				}

			}

			function ch1() {
				var t1 = document.getElementById("Password").value.toLowerCase();
				var t2 = document.getElementById("ri1");
				var t = /^\w{6,20}$/;
				if(t.test(t1)) {
					t2.innerHTML = "√";
					return true;
				} else {
					t2.innerHTML = "X";
					return false;
				}
			}

			function doSubmit(){
				var t=document.getElementById("ln").innerHTML;
				if(t=="√"){
					if(ch1()&&check2()){
						document.getElementById("form1").submit();
					}else{
						alert(1);
					}
				}else{
					alert(2);
				}
			}
		</script>
	</head>

	<body>
		<div class="first">
			<div class="title">欢迎您的注册</div>
			<form id="form1" class="form1" action="<%=request.getContextPath() %>/user/insert" method="post">

				<div class="form1_list">
					<label class="label">用&nbsp;户&nbsp;名</label>
					<input type="text" class="text"  id="user_yonghuming_id" name="username" onblur="test()"/>
					<span class="span" id="ln"></span>
				</div>
				<div class="form1_list">
					<label class="label">真&nbsp;实&nbsp;姓&nbsp;名</label>
					<input type="text" class="text" name="name" />
					<!--<span class="span" id="span_email"></span>-->
				</div>
				<div class="form1_list">
					<label class="label">联&nbsp;系&nbsp;电&nbsp;话</label>
					<input type="text" class="text"  name="phone" />
				</div>
				<div class="form1_list">
					<label class="label">设&nbsp;置&nbsp;密&nbsp;码</label>
					<input type="password" class="text" id="Password" name="user_pwd"  onblur="ch1()"/>
					<span class="span" id="ri1"></span>
				</div>
				<div class="form1_list">
					<label class="label">确&nbsp;认&nbsp;密&nbsp;码</label>
					<input type="password" class="text" id="Password2" name="password" onblur="check2()" />
					<span class="span" id="ri"></span>
				</div>
				<div class="form1_list">
					<input class="button" type="button" onclick="doSubmit()" value="立即注册" />
				</div>
			</form>
		</div>
	</body>

</html>