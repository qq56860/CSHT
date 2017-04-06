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

  </head>
  
  <body>
    
	<div class="left">
	        <div class="left-position">
	          <ul class="nav">
	          	
	          	<c:forEach items="${type}" var="type">
	          		<li class="dropdown font-bolder "><a href="javascript:void(0)" onclick="list_search('type','${type.id}')" class="mainTones-green">${type.type }</a>
		          		<ul class="dropdown-menu">
		          			<c:forEach items="${sub}" var="sub">
		          				<c:if test="${sub.typeId == type.id }">
			                		<a href="javascript:void(0)" onclick="list_search('sub','${sub.id }')" class="mainTones-green"><li class="text-center">${sub.sub }</li></a>
								</c:if>
							</c:forEach>
							<a href="javascript:void(0)" onclick="list_search('type','${type.id}')" class="mainTones-green"><li class="text-center">其他</li></a>
		              	</ul>
	          		</li>
	          		
	          		
	          	</c:forEach>
	          
	          </ul>
	        </div>
		</div>
		

  </body>
</html>
