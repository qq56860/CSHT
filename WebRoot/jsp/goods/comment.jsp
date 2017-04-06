<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	
  </head>
  
  <body>
  
  <div class="container pull-left">
    <div class="row clearfix ">
		<div class="col-md-9 column">
			<form role="form">
				<div class="form-group">
				   <label for="name">发表评论</label>
				   <br/>
				   <textarea class="form-control" rows="3" ></textarea>
				</div>
			</form>
		</div>
		<div class="col-md-3 column">
		</div>
		<div class="col-md-9 column">
			<div class="media">
			<a href="#" class="pull-left"><img src="1" class="media-object" alt='头像' /></a>
				<div class="media-body">
					<h4 class="media-heading">
						用户名：：：：：
					</h4> 评论内容--------------------------------------
					<span class="pull-right">回复</span>
					<div class="media">
						<a href="#" class="pull-left"><img src="2" class="media-object" alt='头像' /></a>
						<div class="media-body">
							<h4 class="media-heading">
								回复者：
							</h4><small>回复内容</small>
							<small><span class="pull-right">回复</span></small>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
  </div>	

  </body>
</html>
