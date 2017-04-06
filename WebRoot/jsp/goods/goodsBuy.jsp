<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'goodsBuy.jsp' starting page</title>
    
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
	
	<script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/header.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body>
    	<div>
	    	<jsp:include page="/jsp/header.jsp" flush="true"/>
	    </div>
    
    <div class="container" style="position: relative;top:150px;">
			<div class="row clearfix">
				<div class="col-md-2 column">
				</div>
				<div class="col-md-8 column">
					<div class="media">
						 <a href="#" class="pull-left"><img src="" class="media-object img-circle" alt='头像' width="130px" height="130px;"/></a>
						<div class="media-body">
							<h4 class="media-heading">
								球狗狗求购
							</h4> 
							<span>as打发打发</span><br />
							<span>as打发打发</span><br />
							<span>as打发打发</span><br />
							<span>我是谁</span><br />
							
							<c:if test="${user == null }">
								<a href="javascript:void(0)" onclick="notLoginAlert()" class="pull-right">
							</c:if>		
							<c:if test="${user != null }">
								<a data-toggle="collapse" data-parent="#accordion" 
							   href="#collapseOne" class="pull-right">
							</c:if>	
								评论
							</a>	
							
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse">
						<div class="panel-body">
							<iframe src="/CSHT/goods/buy/comment" width="800px" height="300px"></iframe>
						</div>
					</div>
					
				</div>
				<div class="col-md-2 column">
				</div>
				
				
			</div>
			
		</div>
    
    
    <a href="" id="login" data-toggle="modal" class="a-hovor"></a>
    <a href="" id="register" data-toggle="modal" class="a-hovor"></a>
 	<div class="modal fade" id="login_register_forget_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
			<jsp:include page="/jsp/user/login_register_forget.jsp" flush="true"></jsp:include>
			
	</div>
    
    	
  </body>
</html>
