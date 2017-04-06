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
    
    <title>My JSP 'header.jsp' starting page</title>
    
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
  
    <nav class="navbar navbar-default navbar-fixed-top"  role="navigation">
			<div id="header">
				<div class="header-hight-width ">
						<a href="/CSHT/homePage" style="position:relative;top:-5px;left:100px;">
							<h1 > 
								<span class="mainTones-green">八一农大2货网</span><br/> 
								<small class="mainTones-yellow font-bolder">&nbsp;&nbsp; 农大人自己的网站</small>
							</h1>
						</a>
						<form class="form-horizontal" id="search_form" style="position:absolute ;top:20px;left:450px;" 
											role="form" action="/CSHT/homePage">
							<span class="input-group-btn">
								<input type="text" name="forwordSearch" id="header_search" class="form-control input-lg" 
									style="width: 300px;float:left ;border-color: #33CC99;" placeholder="搜个什么要买的东东吧！" />  
		            		  
		               			<button class="btn btn-info btn-search mainTones-bg-green input-lg font-bolder" 
		               							onclick="goods_search()">搜 索</button>
		            		</span>  
						</form>
						
					<div class="logined_div">	
						<c:if test="${user == null }">
						
							 <button type="button"   class="btn btn-lg mainTones-bg-white font-bolder">
							 <a href="" id="model_login" data-toggle="modal" class="a-hovor mainTones-green">登录</a>
							 </button> 
							 <button type="button" style=""  class="btn btn-lg mainTones-bg-white font-bolder">
							 <a href="" id="model_register" data-toggle="modal" class="a-hovor mainTones-green">注册</a>
							 </button>
								
						</c:if>
						<c:if test="${user != null }">				
							<div class="dropdown">
								<div class="header-dropdown">
									<button type="button" class="btn btn-default btn-lg mainTones-bg-white font-bolder mainTones-green">
										<span class="glyphicon glyphicon-user"></span>
										&nbsp;&nbsp;
										<span class="mainTones-yellow">${user.nickName }</span>
									</button>
									
									 <ul class="header-dropdown-menu">
						                <a href="#" class="mainTones-green"><li class="btn">个人中心</li></a>
										<a href="#" class="mainTones-green"><li class="btn">我的收藏</li></a>
										<a href="/CSHT/user/logOut" class="mainTones-green"><li class="btn">退出登录</li></a>
						              </ul>
						           </div>   
							 </div>
						</c:if>
					</div>
						
				</div>
			</div>	
			<div class="level-gray" style="height: 1px; width: auto;"></div>	
		</div>	
	</nav>

  </body>
</html>
