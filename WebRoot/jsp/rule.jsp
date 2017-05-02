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
    
    <title>物品发布规则</title>
    
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
   		
   	<div >
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
   	
   	<div class="container" style="position: relative;top: 150px;left:-100px;">
   		
		<div class="row clearfix">
			
			<div class="col-md-3 column">
				<img src="<%=PropertyFactory.getProperty("serverPath")
					+PropertyFactory.getProperty("sysImg") %>rule.png" 
					alt='rule' class="pull-right" style="position: relative;top:-50px;"
					/>
			</div>
			<div class="col-md-8 column">
				<ul class="list-group">
					<c:forEach items="${ruleList }" var="rule" varStatus="i">
					    <li class="list-group-item">${i.count }、${rule.rule }</li>
				    </c:forEach>
				</ul>
			</div>
			<div class="col-md-1 column">
			</div>
		</div>
	</div>
   	
   	
  </body>
</html>
