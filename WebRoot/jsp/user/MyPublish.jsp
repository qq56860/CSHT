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
    
    <title>查看发布物品信息</title>
    
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
						<li style="width: 20%;">
							 <a href="/CSHT/user/collection?id=${reqUser.id }">我的收藏</a>
						</li >
						<li  class="active" style="width: 20%;">
							 <a href="#panel-myPublish" data-toggle="tab">物品发布</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/buy?id=${reqUser.id }">物品求购</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/news?id=${reqUser.id }">我的消息<span style="color: red;">&nbsp;&nbsp;&nbsp;${reqUser.notReadNews }</span></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-5 column tab-pane active" id="panel-myPublish" style="top: 30px;">
				<span class="btn mainTones-yellow">发布物品</span>
				<c:forEach items="${publishList }" var="publish" >
					<div class="media"  style="border-bottom: solid;border-top: solid;border-color: gray;border-width: 1px;">
						<a href="/CSHT/goods/detail?id=${publish.id }" target="_blank" class="pull-left">
						<img src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
									+PropertyFactory.getProperty("goodsImgPath") %>${publish.pic }.png" 
									class="media-object img-circle" alt='图片' width="80px" height="80px;"/></a>
						<div class="media-body">
							<h4 class="media-heading">
								<a href="/CSHT/goods/detail?id=${publish.id }" target="_blank">${publish.goodsName }</a>
							</h4>
							<span>期望价格：￥${publish.price }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>交易地点：${publish.tradePlace }</span><br />
							<div class="mainTones-gray">
								<span>物品详情：</span>${publish.goodsContent }
							</div>
							<span>收藏人数：${publish.collectionNum }</span>
							<div class="pull-right">
								<a href="/CSHT/user/goodsIsBuyed?goodsid=${publish.id }&userid=${reqUser.id }"><span class="btn mainTones-green">已售出</span></a>
								<a href="/CSHT/user/deletePublish?goodsid=${publish.id }&userid=${reqUser.id }"><span class="btn mainTones-green">撤销发布</span></a>
							</div>
							<br />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-5 column tab-pane active" id="panel-myPublish2" style="top: 30px;">
				<span class="btn mainTones-yellow">发布历史</span>
				<c:forEach items="${publishIsBuyedList }" var="publish" >
					<div class="media"  style="border-bottom: solid;border-top: solid;border-color: gray;border-width: 1px;">
						<a href="/CSHT/goods/detail?id=${publish.id }" target="_blank" class="pull-left">
						<img src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
									+PropertyFactory.getProperty("goodsImgPath") %>${publish.pic }.png" 
									class="media-object img-circle" alt='图片' width="80px" height="80px;"/></a>
						<div class="media-body">
							<h4 class="media-heading">
								${publish.goodsName }&nbsp;&nbsp;&nbsp;<small> (已售出)</small>
							</h4>
							<span>期望价格：￥${publish.price }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>交易地点：${publish.tradePlace }</span><br />
							<div class="mainTones-gray">
								<span>物品详情：</span>${publish.goodsContent }
							</div>
							<span>收藏人数：${publish.collectionNum }</span>
							<div class="pull-right">
								<a href="javascript:void(0)"><span class="btn mainTones-green">&nbsp;</span></a>
							</div>
							<br />
						</div>
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
