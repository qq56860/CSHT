<%@page import="cn.com.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.com.util.PropertyFactory"%>
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
    
    <title></title>
    
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
	<script src="<%=basePath %>resources/header.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/search.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
  
    <nav class="navbar navbar-default navbar-fixed-top"  role="navigation">
			<div id="header">
				<div class="header-hight-width ">
					<div style="position: absolute;left:2px;">
						<a href="/CSHT/homePage">
						<img alt='logo' src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
						+PropertyFactory.getProperty("sysImg") %>logo.png" 
						width="80px" height="80px"
						/>
						</a>
					</div>
					<a href="/CSHT/homePage" style="position:absolute;top:-10px;left:100px;">
						<h1 > 
							<span class="mainTones-green">八一农大2货网</span><br/> 
							<small class="mainTones-yellow font-bolder">&nbsp;&nbsp; 农大人自己的网站</small>
						</h1>
					</a>
					<form class="form-horizontal" id="search_form" style="position:absolute ;top:20px;left:450px;" 
										role="form" action="/CSHT/homePage" >
						<div class="input-group">
							<input type="text" name="forwordSearch" id="header_search" class="form-control input-lg" 
								style="width: 300px;" placeholder="搜个什么要买的东东吧！"  onkeyup="hotsearch()"
								autocomplete="off" value="${forwordSearch }" />
							<!-- 内容展示 -->
							<div id="popDiv" style="position:absolute;border: none;">
								<table id="content_table" bgcolor="white"  >
									<tbody id="content_table_body" >
										<!-- 需要展示的内容 -->
										<!-- <tr><td>11111111111111</td></tr>  -->
										<!-- <tr><td>11111111111111</td></tr>  -->
									</tbody>
									
								</table>
							</div>
							
	               			<button class="input-group-addon btn mainTones-bg-green mainTones-white input-lg font-bolder" 
	               						style="position:absolute;width:80px;left:300px;color: white;"	
	               						onclick="goods_search()">
	               			搜 索</button>
	            		</div>  
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
									<button type="button" class="btn mainTones-bg-white" onclick="javascript:location.href='/CSHT/user/info?id=${user.id }'">
										<img alt='头像' src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
											+PropertyFactory.getProperty("userImgPath") %>${user.pic }.png" 
											class="img-circle" width="30px" height="30px"
											/>
										&nbsp;
										<span class="mainTones-yellow" style="font-size: 15px;">${user.nickName }</span>
										<img alt='lv' src="<%=PropertyFactory.getProperty("serverPath")+":"+PropertyFactory.getProperty("serverPort")
											+PropertyFactory.getProperty("sysImg") %>lv/lv
											<c:if test="${user.integral < 50 }">
											1
											</c:if>
											<c:if test="${user.integral < 25600 && user.integral >= 50}">
											<%=(int)(Math.log(((User)request.getSession().getAttribute("user")).getIntegral()/50.0)/Math.log(2.0))+1 %>
											</c:if>
											<c:if test="${user.integral >= 25600 }">
											10
											</c:if>
											.png" 
											class="img-circle" width="40px"
											/>
											<c:if test="${user.notReadNews > 0 }">
												<!-- <span class="glyphicon glyphicon-envelope"></span> -->
												<span style="position:absolute;left:40px;top:0px; color: red;font-size:10px; ">${user.notReadNews}</span>
											</c:if>
									</button>
									
									 <ul class="header-dropdown-menu">
						                <a href="/CSHT/user/info?id=${user.id }" class="mainTones-green"><li class="btn">个人中心</li></a>
										<a href="/CSHT/user/collection?id=${user.id }" class="mainTones-green"><li class="btn">我的收藏</li></a>
										<a href="/CSHT/user/publish?id=${user.id }" class="mainTones-green"><li class="btn">发布物品</li></a>
										<a href="/CSHT/user/buy?id=${user.id }" class="mainTones-green"><li class="btn">求购物品</li></a>
										<a href="/CSHT/user/news?id=${user.id }" class="mainTones-green"><li class="btn">消息中心
											<c:if test="${user.notReadNews > 0 }">
												<span style="color: red;">${user.notReadNews}条</span>
											</c:if></li>
										</a>
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
