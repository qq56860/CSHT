<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
	<div class="left">
	        <div class="left-position">
	          <ul class="nav">
	            <li class="font-bolder "><a href="#" class="mainTones-green">代步工具</a></li>
	            <li class="font-bolder"><a href="#" class="mainTones-green">手机</a></li>
	            <li class="dropdown font-bolder"> <a href="#menu3" class="mainTones-green">电脑</a>
	              <ul class="dropdown-menu">
	                <a href="#" class="mainTones-green"><li class="text-center">联想</li></a>
					<a href="#"><li class="text-center">Menu7</li></a>
					<a href="#"><li class="text-center">Menu7</li></a>
	              </ul>
	            </li> 
	            <li class="font-bolder"><a href="#" class="mainTones-green ">Menu4</a></li>
	            <li class="font-bolder"><a href="#" class="mainTones-green ">Menu5</a></li>
	            <li class="font-bolder"><a href="#" class="mainTones-green ">Menu6</a></li>
	          </ul>
	        </div>
		</div>
		

  </body>
</html>
