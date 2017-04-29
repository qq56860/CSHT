<%@page import="cn.com.util.PropertyFactory"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看消息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>resources/logo.ico" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
    <div>
    	<jsp:include page="userCard.jsp" flush="true"/>
    </div>
    
    <div class="container" style="position: relative;top:130px;left:100px;">
		<div class="row clearfix">
			<div class="col-md-10 column">
				<div class="tabbable" id="tabs-640434">
					<ul class="nav nav-tabs text-center">
						<li  style="width: 20%;" >
							 <a href="/CSHT/user/info?id=${reqUser.id }">个人中心</a>
						</li>
						<li  style="width: 20%;">
							 <a href="/CSHT/user/collection?id=${reqUser.id }">我的收藏</a>
						</li >
						<li style="width: 20%;">
							 <a href="/CSHT/user/publish?id=${reqUser.id }">物品发布</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/buy?id=${reqUser.id }">物品求购</a>
						</li>
						<li class="active" style="width: 20%;">
							 <a href="#panel-myNews" data-toggle="tab">我的消息</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-5 column tab-pane active" id="panel-myNews">
				<span class="btn mainTones-yellow">出售区</span>
				<c:forEach items="${newsListP }" var="newsP" >
					<div style="border-bottom: solid;border-top: solid;border-color: gray;border-width: 1px;">
						<div 
							<c:if test="${!isRead }">
								class="mainTones-green"
							</c:if>
							<c:if test="${isRead }">
								class="mainTones-gray"
							</c:if>
							>
							用户&nbsp;：&nbsp;<a href="/CSHT/user/info?id=${newsP.who }" target="_blank" ><span>${newsP.user.nickName }</span></a>
							&nbsp;&nbsp;&nbsp;在物品&nbsp;：&nbsp;
							<a href="/CSHT/goods/detail?id=${newsP.goodsId }" target="_blank">${newsP.goodsPublish.goodsName }</a>
							&nbsp;&nbsp;&nbsp;中评论<br />
							<span>${newsP.content }</span>
							<br />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-5 column tab-pane active" id="panel-myNews">
				<span class="btn mainTones-yellow">求购区</span>
				<c:forEach items="${newsListB }" var="newsb" >
					<div style="border-bottom: solid;border-top: solid;border-color: gray;border-width: 1px;">
						<div
							<c:if test="${!isRead }">
								class="mainTones-green"
							</c:if>
							<c:if test="${isRead }">
								class="mainTones-gray"
							</c:if>
							>
							用户&nbsp;：&nbsp;<a href="/CSHT/user/info?id=${newsb.who }" target="_blank" ><span>${newsb.user.nickName }</span></a>
							&nbsp;&nbsp;&nbsp;在物品&nbsp;：&nbsp;
							<a href="/CSHT/goods/detail?id=${newsb.goodsId }" target="_blank">${newsb.goodsPublish.goodsName }</a>
							&nbsp;&nbsp;&nbsp;中评论<br />
							<span class="mainTones-gray">${newsb.content }</span>
							<br />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-1 column">
			
			</div>
		</div>
	</div>
    
  </body>
</html>
