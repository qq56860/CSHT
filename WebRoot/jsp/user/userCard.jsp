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
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
    <div>
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
    
    <div class="container" style="position:relative ;top:120px;">
		<div class="row clearfix">
			<div class="col-md-2 column">
			</div>
			<div class="col-md-8 column">
				<div class="row clearfix">
					<div class="col-md-4 column">
						<img alt="头像" src="<%=PropertyFactory.getProperty("serverPath")
						+PropertyFactory.getProperty("userImgPath") %>${reqUser.pic }.png" 
						width="180" height="180" class="img-circle"
							/>
					</div>
					<div class="col-md-8 column">
						 <address> 
						   昵称：<strong>${reqUser.nickName }</strong><br /> <br />
						   共卖出<span class="font-bolder mainTones-green" style="font-size: 30px;">&nbsp;${sellNum }&nbsp;</span>件二货<br /> <br />
						 <abbr title=""> 积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分：</abbr> ${reqUser.integral }
						 <c:if test="${user.id == reqUser.id }">
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<abbr title="">今日增加积分：</abbr> ${reqUser.dayIntegral }
						 </c:if>
						 <br /> <small class="mainTones-gray">积分规则：等级=以2为底的(积分/50)的对数，发布、求购增加5点/1次，评论增加1点/1次，每日上限50点</small> 
						 <br /> 
						 <abbr title="">最近登录：</abbr> ${reqUser.lastTime }
						 </address>
					</div>
				</div>
			</div>
			<div class="col-md-2 column">
			</div>
		</div>
	</div>
    
    
  </body>
</html>
