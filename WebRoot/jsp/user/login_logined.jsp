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
    
    <title>My JSP 'login_logined.jsp' starting page</title>
    
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
	
	<script src="<%=basePath %>resources/gpEdit.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body style="background-color: transparent; ">
    
					<c:if test="${user == null }">
						
							 <button type="button" style="position:absolute;left:-10px;top:30px"  class="btn btn-lg mainTones-bg-white font-bolder">
							 <a href="" id="iframe_login" data-toggle="modal" class="a-hovor mainTones-green">登录</a>
							 </button> 
							 <button type="button" style="position:absolute;left:80px;top:30px"  class="btn btn-lg mainTones-bg-white font-bolder">
							 <a href="" id="iframe_register" data-toggle="modal" class="a-hovor mainTones-green">注册</a>
							 </button>
								
					</c:if>
					

					<c:if test="${user != null }">				
							<div class="dropdown">
								<div class="header-dropdown">
									<button type="button" class="btn btn-default btn-lg mainTones-bg-white font-bolder mainTones-green">
										<span class="glyphicon glyphicon-user"></span>
										&nbsp;&nbsp;
										<span class="mainTones-yellow">${user.nickName }</span>
									</button>
									
									 <ul class="header-dropdown-menu"> 
						                <a href="#" class="mainTones-green"><li class="text-center">个人中心</li></a>
										<a href="#" class="mainTones-green"><li class="text-center">我的收藏</li></a>
										<a href="#" class="mainTones-green"><li class="text-center">退出登录</li></a>
						              </ul>
						           </div>   
							 </div>
					</c:if>

  </body>
</html>
