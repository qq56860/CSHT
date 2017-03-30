<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/left.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/list.css"/>
	
	<script src="<%=basePath %>resources/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
	
	
	<script src="<%=basePath %>resources/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	
	<script src="<%=basePath %>resources/gpEdit.js" type="text/javascript" charset="utf-8"></script>
	
  </head>
  
  <body>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div id="header">
			
				<div class="header-hight">
					<div class="row clearfix">
					<div class="col-md-1 column">
					</div>
					<div class="col-md-3 column">
						<a href="#">
						<div class="page-header  para-middle">
							<h1 > 
								<label class="mainTones-green">八一农大2货网</label><br/> <small class="mainTones-yellow font-bolder">农大人自己的网站</small>
							</h1>
						</div>
						</a>
					</div>
					<div class="col-md-4 column">
						<form class="form-horizontal" style="position:absolute;top:30px;"  role="form" action="#">
							<input type="text" class="form-control input-lg" style="width: 300px; float: left;border-color: #33CC99;" placeholder="搜个什么要买的东东吧！" / >  
		            		<span class="input-group-btn" style="float: left;">  
		               			<button class="btn btn-info btn-search mainTones-bg-green input-lg font-bolder" >搜 索</button>  
		            		</span>  
						</form>
					</div>
					<div class="col-md-2 column " id="login_div">
						 <button type="button" style="position:absolute;left:-10px;top:30px"  class="btn btn-lg mainTones-bg-white font-bolder">
						 <a href="" id="login" data-toggle="modal" class="a-hovor mainTones-green">登录</a>
						 </button> 
						 <button type="button" style="position:absolute;left:80px;top:30px"  class="btn btn-lg mainTones-bg-white font-bolder">
						 <a href="" id="register" data-toggle="modal" class="a-hovor mainTones-green">注册</a>
						 </button>
					</div>
					
					
					<div id="logined_div" >
						
						<div class="dropdown">
							<button type="button" class="btn btn-default btn-lg mainTones-bg-white font-bolder mainTones-green">
								<span class="glyphicon glyphicon-user"></span>
								&nbsp;&nbsp;
								<span id="logined_span" class="mainTones-yellow"></span>
							</button>
							
							 
							 <ul class="header-dropdown-menu"> 
				                <a href="#" class="mainTones-green"><li class="text-center">个人中心</li></a>
								<a href="#" class="mainTones-green"><li class="text-center">我的收藏</li></a>
								<a href="#" class="mainTones-green"><li class="text-center">退出登录</li></a>
				              </ul>
						 </div>
					</div>
					
				</div>
			</div>	
			<div class="level-gray" style="height: 1px; width: auto;"></div>	
			
			
		</div>	
	</nav>
	<%-- <jsp:include page="left.jsp" flush="true"/> --%>
		
	
  </body>
</html>
