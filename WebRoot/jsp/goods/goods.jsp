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
    
    <title>My JSP 'goods.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/bootstrap-theme.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/bootstrap-theme.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/gpEdit.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/header.css"/>
	
	<script src="<%=basePath %>resources/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	
	<script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/header.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body>
  
  	<div>
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
<div class="container" style="position: relative; top:150px;left:-50px;">	   
	<div class="row clearfix">
		<div class="col-md-1 column">
		</div>
		<div class="col-md-10 column">
			<jsp:include page="goodsInfo.jsp" flush="true"/>
		</div>
		<div class="col-md-1 column">
		</div>
	</div>
	
	<br/><br/><br/>
	
	<div class="row clearfix">
		<div class="col-md-1 column">
		</div>
		<div class="col-md-7 column">
			<div class="tabbable" id="tabs-464524">
				<ul class="nav nav-tabs">
					<li class="active">
						 <a href="#panel-detail" data-toggle="tab">物品详情</a>
					</li>
					<li>
						<c:if test="${user == null }">
							<a id ="comment_tab" href="javascript:alert('您尚未登录，请登录后再浏览评论')" >物品评论</a>
						</c:if>
						<c:if test="${user != null }">
							<a id ="comment_tab" href="#panel-comment" data-toggle="tab">物品评论</a>
						</c:if>
					</li>
				</ul>
				<div class="tab-content" >
					<div class="tab-pane active" id="panel-detail">
						<p>
						<div class="container">
							<h3>人家卖家说了，这是：</h3>
							<br/>
							<h1>&nbsp;&nbsp;&nbsp;&nbsp; ${goods.goodsContent }</h1>
							<br/>
							<h3>知道了不！</h3>
						</div>
						</p>
					</div>
					<div class="tab-pane" id="panel-comment">
						<p>
							<iframe src="/CSHT/goods/publish/comment?goodsid=${goods.id }" 
										align="middle" id="comment_refresh" 
										frameborder="0" scrolling="no" 
										allowTransparency="true" 
										width="1100"
										onload = "changeFrameHeight('comment_refresh')"
										>
							</iframe>
						</p>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<div class="btn btn-lg mainTones-white navbar-fixed-bottom buy_btn"  id="">
		 <a href="/CSHT/buyPage">
			 <button type="button" class="btn btn-default btn-lg mainTones-green">
	             <span class="glyphicon glyphicon-log-in "></span>&nbsp;&nbsp;
	             <span class="mainTones-green font-bolder">求购专区</span>
	         </button>
         </a>
	</div>
	
	<a href="" id="login" data-toggle="modal" class="a-hovor"></a>
    <a href="" id="register" data-toggle="modal" class="a-hovor"></a>
 	<div class="modal fade" id="login_register_forget_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
			<jsp:include page="/jsp/user/login_register_forget.jsp" flush="true"></jsp:include>
			
	</div>
</div>	
	
  </body>
</html>
