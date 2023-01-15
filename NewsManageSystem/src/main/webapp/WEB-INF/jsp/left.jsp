<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>left.jsp</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
	    console.log(1111);
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</head>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>管理菜单</div>
	<c:choose>
		<c:when test="${sessionScope.login_user.roleId==1}">
			<dl class="leftmenu">
				<dd>
					<div class="title">
						<span><img src="images/leftico04.png" /></span>用户管理
					</div>
					<ul class="menuson">
						<li><cite style="margin-left: -4px;"></cite><a href="${pageContext.request.contextPath}/findUserList.action"
							target="rightFrame">用户列表</a><i></i></li>
						<li><cite></cite><a href="${pageContext.request.contextPath}/toAddUser.action"
							target="rightFrame">添加用户</a><i></i></li>
					</ul>
				</dd>
				<dd>
					<div class="title">
						<span><img src="images/leftico02.png" /></span>新闻类别管理
					</div>
					<ul class="menuson">
						<li><cite></cite><a href="${pageContext.request.contextPath}/findCategoryList.action"
							target="rightFrame">新闻类别列表</a><i></i></li>
					</ul>
				</dd>
			</dl>
		</c:when>
		<c:when test="${sessionScope.login_user.roleId==2}">
			<dl class="leftmenu">
				<dd>
					<div class="title">
						<span><img src="images/leftico01.png" /></span>新闻管理
					</div>
					<ul class="menuson">
						<li><cite style="margin-left: -4px;"></cite><a href="${pageContext.request.contextPath}/findNewsByPage.action"
							target="rightFrame">新闻列表</a><i></i></li>
						<li><cite></cite><a href="${pageContext.request.contextPath}/toAddNews.action"
							target="rightFrame">发布新闻</a><i></i></li>
					</ul>
				</dd>
			</dl>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</body>
</html>