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
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/left.css"/>
	
  </head>
  
  <body style="z-index:1;">
    
	<div class="left">
	        <div class="left-position">
	          <ul class="nav">
	          	
	          	<c:forEach items="${type}" var="type">
	          		<li class="dropdown font-bolder text-center">
		          		<a href="/CSHT/homePage?forwordType=${type.id }" class="mainTones-green">
		          			<span class="">${type.type }</span> 
		          		</a>
		          		<ul class="dropdown-menu dropdown-menu-position">
		          			<c:forEach items="${sub}" var="sub">
		          				<c:if test="${sub.typeId == type.id }">
			                		<a href="/CSHT/homePage?forwordSub=${sub.id }">
			                			<li class="text-center">
			                			<span class="btn btn-default btn-sm mainTones-green">${sub.sub }</span> 
			                			</li>
			                		</a>
								</c:if>
							</c:forEach>
		              	</ul>
	          		</li>
	          		
	          		
	          	</c:forEach>
	          
	          </ul>
	        </div>
		</div>
		

  </body>
</html>
