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
    
    <title>My JSP 'comment.jsp' starting page</title>
    
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
  
  <body onload="autoHeight()">
  
  <div class="container pull-left">
    <div class="row clearfix ">
		<div class="col-md-9 column">
			<form role="form" action="/CSHT/comment/addComment">
				<div class="form-group">
						<br/>
						<button type="submit" class="btn btn-default mainTones-border-gray">发表评论</button>
					   	<br/>
					   	<textarea class="form-control" name="content" rows="3" style="resize: none;"></textarea>
					   	<input type="hidden" name="publishOrBuy" value="${publishOrBuy }" />
					   	<input type="hidden" name="goodsid" value="${goodsid }" />
				</div>
			</form>
		</div>
		<div class="col-md-3 column">
		</div>
		<div class="col-md-9 column">
			
			<c:forEach items="${commentList }" var="comment">
				<div class="media">
					<a href="/CSHT/user/info?id=${comment.user.id }" target="_blank" class="pull-left"  >
						<img src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
						+PropertyFactory.getProperty("userImgPath") %>${comment.user.pic }.png" 
						class="media-object img-circle" alt='头像' 
						width="50px" height="50px"
						/>
					</a>
					<div class="mainTones-border-gray" style="left:100px;width:690px; z-index:1;border: none;">
						<a href="/CSHT/user/info?id=${comment.user.id }" target="_blank">${comment.user.nickName }</a> 
						<span class="mainTones-gray">time:${comment.time }</span> 
						<br/>
						<a href="javascript:void(0)" id="comment-${comment.id }" data-toggle="collapse"
							onclick="addComment('add-comment-${comment.id }','iframe-${goodsid }')" alt="回复" title="回复">
						${comment.content }</a> 
					</div>
					
					<div style="position:relative ;left:50px;width:640px; ">	
						<iframe src="/CSHT/goods/publish/replyList?commentid=${comment.id }&commentUserid=${comment.user.id }&commentNickName=${comment.user.nickName }&publishOrBuy=${publishOrBuy }" 
										align="middle" id="iframe${comment.id }" 
										frameborder="0" scrolling="no" 
										width="100%" height="0%"
										>
						</iframe>
					</div>
					
					<div class="panel-collapse collapse" id="add-comment-${comment.id }">
						<form action="/CSHT/comment/addReply">
							<textarea class="form-control" name="content" rows="3" style="resize:none;"></textarea>
							<button type="submit"  class="btn btn-default pull-right">回复</button>
							<input type="hidden" name="publishOrBuy" value="${publishOrBuy }" />
					   		<input type="hidden" name="commentid" value="${comment.id }" />
					   		<input type="hidden" name="goodsid" value="${goodsid }" />
					   		<input type="hidden" id="replayid" name="replayid" />
						</form>
						<br/>
					</div>
					
					
				</div>
				
			</c:forEach>
				
			
		</div>
	</div>
  </div>	

  </body>
</html>
