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
    
    <title>My JSP 'header.jsp' starting page</title>
    
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
	
	<script src="<%=basePath %>resources/gpEdit.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div id="header">
			
				<div class="header-hight">
					<div class="row clearfix">
					<div class="col-md-1 column">
					</div>
					<div class="col-md-3 column">
						<a href="#">
						<div class="page-header  para-middle">
							<h1 > 
								<label class="mainTones-green">八一农大2货网</label><br/> <small class="mainTones-yellow font-bolder">农大人自己的网站</small>
							</h1>
						</div>
						</a>
					</div>
					<div class="col-md-4 column">
						<form class="form-horizontal" style="position:absolute;top:30px;" role="form" action="javascript:void(0)">
							<input type="text" id="header_search" class="form-control input-lg" style="width: 300px; float: left;border-color: #33CC99;" placeholder="搜个什么要买的东东吧！" / >  
		            		<span class="input-group-btn" style="float: left;">  
		               			<button class="btn btn-info btn-search mainTones-bg-green input-lg font-bolder" 
		               							onclick="list_search('search','')">搜 索</button>  
		            		</span>  
						</form>
					</div>
					
					<div class="col-md-2 column">
						<iframe src="/CSHT/jsp/user/login_logined.jsp" align="middle" id="login_iframe"  
						frameborder="0" scrolling="no" 
						marginheight="0" marginwidth="0" 
						allowTransparency="true" 
						height="110"
						>
						</iframe>
					</div>
					
					
				</div>
			</div>	
			<div class="level-gray" style="height: 1px; width: auto;"></div>	
			
			
		</div>	
	</nav>
	
	
  </body>
</html>
