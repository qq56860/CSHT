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
    
    <title>My JSP 'homePage.jsp' starting page</title>
    
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/left.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/list.css"/>
	
	<script src="<%=basePath %>resources/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/header.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/homePage.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body>
  
    <div>
    	<jsp:include page="header.jsp" flush="true"/>
    </div>
    <div class="container pull-left">
    <div class="row clearfix">
    
	    <div class="col-sm-1 column">
	    	<jsp:include page="left.jsp" flush="true" />
	    </div>
	    
		<div class="col-sm-10 column" >
		 	
		 		<iframe src="/CSHT/homePageList?search=${forwordSearch }&type=${forwordType }&sub=${forwordSub }" align="middle" id="list_iframe"  
							frameborder="0" scrolling="no" 
							allowTransparency="true" 
							width="1200"
							onload = "changeFrameHeight('list_iframe')"
							>
				</iframe>
		 	
		</div>
	</div> 
	</div>	 
	
	<div class="btn btn-lg mainTones-bg-yellow mainTones-white list-publish"
			<c:if test="${user == null }"> onclick="userLogin()" </c:if>
			<c:if test="${user != null }"> onclick="javascript:window.location.href='/CSHT/jsp/publish/sell.jsp'" </c:if>
			  >
		<span>我要发布</span>
	</div>
	
	<div class="btn btn-lg mainTones-bg-yellow mainTones-white list-buy"
		<c:if test="${user == null }"> onclick="userLogin()" </c:if>
		<c:if test="${user != null }"> onclick="javascript:window.location.href='/CSHT/jsp/publish/buy.jsp'" </c:if>
		>
		<span>我要求购</span>
	</div>
		 
	 <div class="btn btn-lg mainTones-white navbar-fixed-bottom buy_btn"  id="">
		 <a href="/CSHT/buyPage">
			 <button type="button" class="btn btn-default btn-lg mainTones-green">
	             <span class="glyphicon glyphicon-log-in "></span>&nbsp;&nbsp;
	             <span class="mainTones-green font-bolder">求购专区</span>
	         </button>
         </a>
	</div>
	 
	
    <%-- <div>
    	<jsp:include page="goods/list.jsp" flush="true" />
    </div> --%>
    <a href="" id="login" data-toggle="modal" class="a-hovor"></a>
    <a href="" id="register" data-toggle="modal" class="a-hovor"></a>
 	<div class="modal fade" id="login_register_forget_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
			<jsp:include page="/jsp/user/login_register_forget.jsp" flush="true"></jsp:include>
			
	</div>
    
  </body>
  
  
</html>
