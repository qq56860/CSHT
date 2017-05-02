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
    
    <title>查看收藏物品信息</title>
    
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
						<c:if test="${user.id == reqUser.id }">
						<li  style="width: 20%;" >
							 <a href="/CSHT/user/info?id=${reqUser.id }">个人中心</a>
						</li>
						<li class="active" style="width: 20%;">
							 <a href="#panel-myCollection" data-toggle="tab">我的收藏</a>
						</li >
						<li style="width: 20%;">
							 <a href="/CSHT/user/publish?id=${reqUser.id }">物品发布</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/buy?id=${reqUser.id }">物品求购</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/news">我的消息<span style="color: red;">&nbsp;&nbsp;&nbsp;${reqUser.notReadNews }</span></a>
						</li>
						</c:if>
						<c:if test="${user.id != reqUser.id }">
							<li class="active" style="width: 33%;">
								 <a href="#panel-myCollection" data-toggle="tab">TA的收藏</a>
							</li >
							<li style="width: 34%;">
								 <a href="/CSHT/user/publish?id=${reqUser.id }">TA的发布</a>
							</li>
							<li style="width: 33%;">
								 <a href="/CSHT/user/buy?id=${reqUser.id }">TA的求购</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-1 column">
			
			</div>
			
			
			<div id="panel-myCollection" style="position:relative; top:20px;">
				<c:forEach items="${collectionList }" var="collection">
				<div class="col-md-10 column tab-pane active" style="border-top:solid;border-bottom:solid; border-color:gray;border-width: 1px;" >
					<h4 class="media-heading font-bolder">
						<c:if test="${!collection.goodsPublish.isBuyed }">
							<a href="/CSHT/goods/detail?id=${collection.goodsPublish.id }" target="_blank">${collection.goodsPublish.goodsName }</a>
						</c:if>	
						<c:if test="${collection.goodsPublish.isBuyed }">
							${collection.goodsPublish.goodsName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(已售出)
						</c:if>	
					</h4> 
						
					<c:if test="${user.id == reqUser.id }">
						<a class="pull-right" href="/CSHT/user/deleteCollection?goodsid=${collection.goodsPublish.id }&userid=${reqUser.id }">取消收藏</a>
					</c:if>	
					
					<span class="mainTones-green">期望价格：</span><span class="mainTones-yellow">￥${collection.goodsPublish.price }</span> &nbsp;&nbsp;&nbsp;
					<span class="mainTones-green">交易地点：</span><span class="mainTones-yellow">${collection.goodsPublish.tradePlace }</span> <br />
					<div class="mainTones-gray" style="height: 30px;">
						${collection.goodsPublish.goodsContent }
					</div>
					<br/>
				</div>
				</c:forEach>
			</div>
			
			<div class="col-md-1 column">
			
			</div>
		</div>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	</div>
    
  </body>
</html>
