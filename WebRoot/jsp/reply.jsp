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
    
    <title>My JSP 'reply.jsp' starting page</title>
    
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
	
	<script src="<%=basePath %>resources/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/addCommentReply.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body onload="autoHeight()" >
  
  
  <div class="" style="position:absolute ; top:10px;width:100% ;">
    <c:forEach items="${replyList }" var="reply">
		<div class="media mainTones-border-gray" style="border: none;">
			<a href="/CSHT/user/info?id=${reply.user.id }" target="_blank" class="pull-left">
				<img src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
						+PropertyFactory.getProperty("userImgPath") %>${reply.user.pic }.png " 
						class="media-object img-circle" alt='头像' 
						width="40px" height="40px"
						/>
			</a>
			<div style="position: absolute;left:50px;width:100%;">
				
					<small> 
					<a href="/CSHT/user/info?id=${reply.user.id }" target="_blank"> ${reply.user.nickName }</a>&nbsp;&nbsp; 
					<small> 回复</small> &nbsp;&nbsp;
					
						<c:if test="${reply.replayId == null || reply.replayId == '' }"><a href="/CSHT/user/info?id=${commentUserid }" target="_blank">${commentNickName }</a></c:if>
						<c:if test="${reply.replayId != null || reply.replayId != '' }">
							<c:forEach items="${replyList }" var="r">
								<c:if test="${r.id == reply.replayId }">
									<a href="/CSHT/user/info?id=${r.user.id }" target="_blank">
									${r.user.nickName }</a>
								</c:if>
							</c:forEach>
						</c:if>
					 
					<span class="mainTones-gray">time:${reply.replayTime }</span>
					</small>
					<br/>
					<small>
					<a href="javascript:void(0)" data-toggle="collapse" alt="回复" title="回复" onclick="addReply( '${reply.id }','comment-${commentid}')">
					${reply.replay }</a></small>
				
			</div>
			
		</div>
		
	</c:forEach>
 </div>
	
	
  </body>
</html>
