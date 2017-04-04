<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'homePage.jsp' starting page</title>
    
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
    	<jsp:include page="header.jsp" flush="true"/>
    </div>
    
    <div class="col-sm-1">
    	<jsp:include page="left.jsp" flush="true" />
    </div>
    
    
    
	<div class="col-sm-11" >
	 	
	 		<iframe src="/CSHT/homePageList" align="middle" id="list_iframe"  
						frameborder="0" scrolling="no" 
						allowTransparency="true" 
						width="1200"
						onload = "changeFrameHeight()"
						>
			</iframe>
	 	
	</div>
	 
	 <div class="btn btn-lg mainTones-white navbar-fixed-bottom buy_btn"  id="">
		 <a href="#">
			 <button type="button" class="btn btn-default btn-lg mainTones-green">
	             <span class="glyphicon glyphicon-log-in "></span>&nbsp;&nbsp;
	             <span class="mainTones-green font-bolder">求购专区</span>
	         </button>
         </a>
	</div>
	 
	 
	  
    <%-- <div>
    	<jsp:include page="goods/list.jsp" flush="true" />
    </div> --%>
    <a href="" id="login" data-toggle="modal" class="a-hovor"></a>
    <a href="" id="register" data-toggle="modal" class="a-hovor"></a>
 	<div class="modal fade" id="login_register_forget_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
			<jsp:include page="/jsp/user/login_register_forget.jsp" flush="true"></jsp:include>
			
	</div>
    
  </body>
  
  
</html>
