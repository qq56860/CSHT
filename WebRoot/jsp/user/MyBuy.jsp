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
    
    <title>查看求购物品信息</title>
    
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
						<li  style="width: 20%;">
							 <a href="/CSHT/user/collection?id=${reqUser.id }">我的收藏</a>
						</li >
						<li style="width: 20%;">
							 <a href="/CSHT/user/publish?id=${reqUser.id }">物品发布</a>
						</li>
						<li class="active" style="width: 20%;">
							 <a href="#panel-myBuy" data-toggle="tab">物品求购</a>
						</li>
						<li style="width: 20%;">
							 <a href="/CSHT/user/news?id=${reqUser.id }">我的消息<span style="color: red;">&nbsp;&nbsp;&nbsp;${reqUser.notReadNews }</span></a>
						</li>
						</c:if>
						<c:if test="${user.id != reqUser.id }">
							<li style="width: 33%;">
								 <a href="/CSHT/user/collection?id=${reqUser.id }">TA的收藏</a>
							</li >
							<li style="width: 34%;">
								 <a href="/CSHT/user/publish?id=${reqUser.id }">TA的发布</a>
							</li>
							<li class="active" style="width: 33%;">
								 <a href="#panel-myBuy" data-toggle="tab">TA的求购</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-1 column">
			
			</div>
			<div class="col-md-10 column tab-pane active" id="panel-myBuy" style="top:30px;">
				<c:forEach items="${buyList }" var="buy" >
				<div class="" style="border-bottom: solid;border-top: solid;border-color: gray;border-width: 1px;">
					<h4 class="media-heading">
						<a href="/CSHT/buyPage" target="_blank">${buy.goodsName }</a>
					</h4>
					<c:if test="${user.id == reqUser.id }">
					<div class="pull-right">
						<a href="/CSHT/user/buyIsBuy?goodsid=${buy.id }&userid=${reqUser.id }"><span class="btn mainTones-green">已买到</span></a>
						<a href="/CSHT/user/deleteBuy?goodsid=${buy.id }&userid=${reqUser.id }"><span class="btn mainTones-green">撤销求购</span></a>
					</div>
					</c:if>
					<span>期望价格：￥${buy.wishPrice }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>交易地点：${buy.tradePlace }</span><br />
					<div class="mainTones-gray">
						<span>物品详情：</span>${buy.goodsContent }
					</div>
					
					<br />
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
