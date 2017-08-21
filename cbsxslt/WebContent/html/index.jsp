<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset=utf-8>
<meta name=viewport content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/academicBBSparticipants.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/index.js"></script>
<title>长白山学术论坛会议</title>
<style type="text/css">
	h5{
		color: red;
	}
</style>
</head>
<c:if test="${empty meetingInfo && empty guestInfo}">
	<script type="text/javascript">
			window.onload=function (){
				location.href="<%=request.getContextPath()%>/all/index";
		};
	</script>
</c:if>
<body>
	<!-- 回到首部的猫链接 -->
	<a href="top"></a>
	<!-- 背景图层 -->
	<div class="video-moban">
		<%-- <video autoplay loop src="<%=request.getContextPath()%>/file.mp4"
			class="video">
		</video> --%>
		
		<img alt="图片无法显示" src="<%=request.getContextPath()%>/img/background-img.jpg" class="video">
	</div>
	<!-- 条条 -->
	<div class="din"
		style="position: fixed; top: 0px; left: 0px; width: 100%;height: 50px; font-size:18px; line-height: 45px;">
		<a href="#" class="a1">首页/</a>
		<a href="<%=request.getContextPath()%>/all/moreMeeting" class="a1">学术论坛会议/</a>
		<a href="<%=request.getContextPath()%>/all/queryPa" class="a1">学术社区/</a>
		<a href="javascript:void(0)"  class="a1">院校机构/</a>
		<a href="javascript:void(0)"  class="a1">产白学者/</a>
		<a href="javascript:void(0)"  class="a1">长白名师/</a>
		<a href="javascript:void(0)"  class="a1">高端人才/</a>
		
		<a href="http://localhost:8080/cbsht/login.jsp" class="a1">登录/</a>
		<a href="http://localhost:8080/cbsht/register.jsp" class="a1" >注册</a>
	</div>
	<div class="tiaotiao"></div>
	<!-- part-one -->
	<div class="b2">
		<p class="one-p1">长白山</p>
		<div class="one-div1">长白山学术论坛欢迎您的莅临</div>
		<p class="one-p2">学术论坛</p>
	</div>
	<!-- part-two -->
	<div class="b3">
		<span class="tow-span1">学术论坛会议</span>
		<!-- 搜索框 -->
		<div class="search" id="fieldsearch">
			<form action="<%=request.getContextPath()%>/search/searchGo"
				method="post">
				<input type="text" class="box inputText" name="s"
					onkeyup="selectItem(this.value, event)" id="txtKeyword"
					placeholder="请输入要搜索的主题" /> <input type="submit" value="GO!"
					class="search-button" />
				<ul id="ulItems"></ul>
			</form>
		</div>
		<!-- start学术论坛会议主题部分 -->
		<div class="two-waibu">
			<!-- 控制会议主题部分左右的按钮 -->
			<div>
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev"> <img
					src="<%=request.getContextPath()%>/img/icon_left.png" alt=""
					class="img1">
				</a> <a class="right carousel-control" href="#myCarousel"
					data-slide="next"> <img
					src="<%=request.getContextPath()%>/img/icon_right.png" alt=""
					class="img2">
				</a>
			</div>

			<!-- 左右滑动的内容部分-->
			<div class="carousel slide" id="myCarousel" data-ride="carousel"
				data-interval="false" data-pause="hover" data-wrap="false">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators" id="myCarousel-css">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<c:forEach items="${meetingInfo[0] }" var="huiyi">
							<div class="teo">
								<div class="two-1">
									<a
										href="<%=request.getContextPath()%>/all/meetingDetail?id=${huiyi.huiyi_id}"
										class="two-a1"> <c:if test="${empty huiyi.zhongwen_name}">
											<c:if test="${empty huiyi.yingwen_name && empty huiyi.zhongwen_name }">
													暂无主题
												</c:if>
											<c:if test="${!empty huiyi.yingwen_name && empty huiyi.zhongwen_name}">
													${huiyi.yingwen_name}
												</c:if>
										</c:if> <c:if test="${!empty huiyi.zhongwen_name}">
												${huiyi.zhongwen_name }
											</c:if>
									</a>
								</div>
								<div class="two-2">
									<ul class="two-ul">
										<li class="two-p">
											<h5>时间</h5>
											<p>${fn:substring(huiyi.zhaokai_time,0,10) }<br>-<br>${fn:substring(huiyi.jieshu_time,0,10) }
											<c:if test="${empty huiyi.zhaokai_time }">
												暂未发布
											</c:if>
											</p>
										</li>
										<li class="two-p">
											<h5>地点</h5>
											<p>${huiyi.huiyi_didian }<c:if test="${empty huiyi.huiyi_didian }">
												暂未发布
											</c:if>
											</p>
										</li>
										<li class="two-p">
											<h5>举办单位</h5>
											<p>${huiyi.huiyi_didian }<c:if test="${empty huiyi.huiyi_didian }">
												暂未发布
											</c:if></p>
										</li>
										<li class="two-p">
											<h5>内容简介</h5>
											<p class="p4">${fn:substring(huiyi.huiyi_zhaiyao,0,20) }<c:if test="${empty huiyi.huiyi_zhaiyao }">
												暂无内容发布
											</c:if></p>
										</li>
									</ul>
									<a
										href="<%=request.getContextPath()%>/participant/setInfo?id=${huiyi.huiyi_id}"
										class="two-mma">快速注册</a>
								</div>
							</div>
						</c:forEach>
					</div>
					<c:forEach items="${meetingInfo }" var="hys" begin="1">
						<div class="item">
							<c:forEach items="${hys }" var="huiyi">
								<div class="teo">
									<div class="two-1">
										<a
											href="<%=request.getContextPath()%>/all/meetingDetail?id=${huiyi.huiyi_id}"
											class="two-a1">${huiyi.zhongwen_name}<br>${huiyi.yingwen_name
											}
										</a>
									</div>
									<div class="two-2">
										<ul class="two-ul">
											<li class="two-p">
												<h5>时间</h5>
												<p>${fn:substring(huiyi.zhaokai_time,0,10) }<br>-<br>${fn:substring(huiyi.jieshu_time,0,10) }<c:if test="${empty huiyi.zhaokai_time }">
												暂未发布
											</c:if></p>
											</li>
											<li class="two-p">
												<h5>地点</h5>
												<p>${huiyi.huiyi_didian }<c:if test="${empty huiyi.huiyi_didian }">
												暂未发布</c:if>
												</p>
											</li>
											<li class="two-p">
												<h5>举办单位</h5>
												<p>${huiyi.huiyi_didian }
												<c:if test="${empty huiyi.huiyi_didian }">
												暂未发布
												</c:if>
												</p>
											</li>
											<li class="two-p">
												<h5>内容简介</h5>
												<p class="p4">${fn:substring(huiyi.huiyi_zhaiyao,0,30) }<c:if test="${empty huiyi.huiyi_zhaiyao }">
												暂无内容发布
												</c:if>
												</p>
											</li>
										</ul>
										<a href="<%=request.getContextPath()%>/participant/setInfo?id=${huiyi.huiyi_id}"
											class="two-mma">快速注册</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
			<!--会议内容 结束  -->
		</div>
		<!--会议内容、左右箭头 结束  -->
		<div class="two-span2">
			<a href="<%=request.getContextPath()%>/all/moreMeeting">更多会议</a>
		</div>
	</div>
	<!-- end学术论坛会议部分 -->
	<!-- 学术论坛参与者  白色背景的div -->
	<div>
		<div id="academic-div"></div>
		<div id="academic-divs">
			<!--字体的背景div  -->
			<div class="academic-div1">
				<h1 class="academic-h1">学术论坛参与者</h1>
			</div>
			<!-- 图片文字的div1 -->
			<!-- start论坛会议的主要参加者名单部分 -->
			<div class="academic-div2">
				<div class="carousel slide" id="myCarousel1" data-ride="carousel"
					data-interval="5000" data-pause="hover" data-wrap="true">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators" id="myCarousel-css1">
						<li data-target="#myCarousel1" data-slide-to="0" class="active"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<ul class="imglist">
								<c:forEach items="${guestInfo[0] }" var="jiabin">
									<li><img src="../jiabin_img/${jiabin.jiabin_zhaopian }" /> <span>
											<b class="textname">${jiabin.jiabin_name }</b> <b
											class="textjob">${jiabin.jiabin_zhiwu }</b>
									</span></li>
								</c:forEach>
							</ul>
						</div>
						<c:forEach items="${guestInfo }" begin="1" var="jbs">
							<div class="item">
								<ul class="imglist">
									<c:forEach items="${jbs }" var="jiabin">
										<li><img src="../jiabin_img/${jiabin.jiabin_zhaopian }" /> <span>
												<b class="textname">${jiabin.jiabin_name }</b> <b
												class="textjob">${jiabin.jiabin_zhiwu }</b>
										</span></li>
									</c:forEach>
								</ul>
							</div>
						</c:forEach>
					</div>

				</div>
				<!-- 包含轮播按钮  结束 -->
			</div>
			<!-- 学术论坛参与者部分结束 -->
		</div>
	</div>

	<!-- end论坛会议的主要参加者名单部分 -->
	<!-- part4 -->
	<div class="sjap-zong">
		<div class="sjap-div1">
			<h1 class="sjap-h1" style="margin-top: 80px;">
				<a href="<%=request.getContextPath()%>/all/queryPa">学术社区</a>
			</h1>
		</div>
		<div class="sjap-div2">

			<div class="carousel slide" id="myCarousel2" data-ride="carousel"
				data-interval="10000" data-pause="hover" data-wrap="true">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators" id="myCarousel-css2">
					<li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<!--左侧的时间安排-->
						<c:if test="${!empty particle[0][1] }">
						<div class="sjap-left">
							<div class="text-up">
								<a class="left-text-up"
									href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${particle[0][1].part.shequ_id}">${particle[0][1].part.shequ_name}</a>
							</div>
							<div class="text-down">
								<c:forEach var="article" items="${particle[0][1].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>
											${fn:substring(article.lunwen_createtime,0,10) }
										</li>
									</ul>
								</c:forEach>
							</div>
						</div>
						</c:if>
						<!--右侧的时间安排-->
						<c:if test="${!empty particle[0][2]}">
						<div class="sjap-right">
							<div class="text-up">
								<a class="left-text-up"
									href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${particle[0][2].part.shequ_id}">${particle[0][2].part.shequ_name}</a>
							</div>
							<div class="text-down">
								<c:forEach var="article" items="${particle[0][2].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>
											${fn:substring(article.lunwen_createtime,0,10) }
										</li>
									</ul>
								</c:forEach>
							</div>
						</div>
						</c:if>
						<!--中间的时间安排-->
						<c:if test="${!empty particle[0][0]}">
						<div class="sjap-middle">
							<div class="text-up">
								<a class="left-text-up"
									href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${particle[0][0].part.shequ_id}">${particle[0][0].part.shequ_name}</a>
							</div>
							<div class="text-down">
								<c:forEach var="article" items="${particle[0][0].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>
											${fn:substring(article.lunwen_createtime,0,10) }
										</li>
									</ul>
								</c:forEach>
							</div>
						</div>
						</c:if>
					</div>
					<!-- 第二页开始 -->
					<c:forEach items="${particle }" begin="1" var="pas">
						<div class="item">
							<!--左侧的时间安排-->
							<c:if test="${!empty pas[1]}">
							<div class="sjap-left">
								<div class="text-up">
									<a class="left-text-up"
										href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${pas[1].part.shequ_id}">${pas[1].part.shequ_name}</a>
								</div>
								<div class="text-down">
									<c:forEach var="article" items="${pas[1].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>
											${fn:substring(article.lunwen_createtime,0,16) }
										</li>
									</ul>
									</c:forEach>
								</div>
							</div>
							</c:if>
							<!--右侧的时间安排-->
							<c:if test="${!empty pas[2] }">
							<div class="sjap-right">
								<div class="text-up">
									<a class="left-text-up"
										href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${pas[2].part.shequ_id}">${pas[2].part.shequ_name}</a>
								</div>
								<div class="text-down">
									<c:forEach var="article" items="${pas[2].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>${fn:substring(article.lunwen_createtime,0,10) }</li>
									</ul>
									</c:forEach>
								</div>
							</div>
							</c:if>
							<!--中间的时间安排-->
							<c:if test="${!empty pas[0] }">
							<div class="sjap-middle">
								<div class="text-up">
									<a class="left-text-up"
										href="<%=request.getContextPath() %>/all/queryPa?fun=sq&sqid=${pas[0].part.shequ_id}">${pas[0].part.shequ_name}</a>
								</div>
								<div class="text-down">
									<c:forEach var="article" items="${pas[0].articles }">
									<ul class="txtlist">
										<li class="left-text-down">
											<a
												href="<%=request.getContextPath()%>/all/queryPa?lwid=${article.lunwen_id }&fun=lw"
												>${article.lunwen_guanjianci }
												</a>
										</li>
										<li>${fn:substring(article.lunwen_createtime,0,10) }</li>
									</ul>
									</c:forEach>
								</div>
							</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
				<a href="#myCarousel2" class="left carousel-control"
					data-slide="prev"><img
					src="<%=request.getContextPath()%>/img/icon_left.png" alt=""
					class="yingyong-img"></a> <a href="#myCarousel2"
					class="right carousel-control" data-slide="next"><img
					src="<%=request.getContextPath()%>/img/icon_right.png" alt=""
					class="yingyong-img"></a>
			</div>

		</div>
	</div>
	<!--其他-->
	<a class="two-span3"
		href="<%=request.getContextPath()%>/all/queryPa">更多社区&gt;</a>
	<!-- part-five -->
	<div class="zhubanfang">

		<div class="five-div1" style="margin-top: 50px;">
			<p class="five-span1">主办方</p>
			<p class="five-span" style="font-size: 20px; margin-top: 10px">长白山学术论坛</p>
			<div class="five-div2">
				<c:forEach items="${agencyInfo }" step="3" varStatus="status"
					begin="0" end="6">
					<div class="five-div3">
						<c:forEach var="jigou" items="${agencyInfo }"
							begin="${status.index}" end="${status.index+2}">
							<a href="${jigou.jigou_wangzhi }" target="_blank"> <img
								src="../jigou_icon/${jigou.jigou_tubiao }" alt="${jigou.jigou_name }"
								class="five-img">
							</a>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 版权所有 -->
	<div class="banquan">
		<span class="jishiyu">版权所有</span>
	</div>
	<a href="#top" id="return-top" class="return-top"
		style="visibility: hidden;"><img
		src="<%=request.getContextPath()%>/img/rote.jpg"
		style="width: 40px; height: 30px;" alt="回到首页" />top</a>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/addLoadEvent.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/listenScrollTop.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/searchdeal.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/ScrollTop.js"></script>
	<c:forEach items="${meetingInfo }" begin="1" varStatus="status">
		<script>
			document.getElementById("myCarousel-css").innerHTML += "<li data-target='#myCarousel' data-slide-to='"+${status.index}+"'></li>";
		</script>
	</c:forEach>
	<c:forEach items="${guestInfo }" begin="1" varStatus="status">
		<script>
			document.getElementById("myCarousel-css1").innerHTML += "<li data-target='#myCarousel1' data-slide-to='"+${status.index}+"'></li>";
		</script>
	</c:forEach>
	<c:forEach items="${particle }" begin="1" varStatus="status">
		<script>
			document.getElementById("myCarousel-css2").innerHTML += "<li data-target='#myCarousel2' data-slide-to='"+${status.index}+"'></li>";
		</script>
	</c:forEach>
</body>
</html>