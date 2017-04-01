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
    
    <div class="col-sm-2">
    	<jsp:include page="left.jsp" flush="true" />
    </div>
    
    
    
	<div class="tab-content col-sm-10" >
	 	<div class="tab-pane active" id="list-tab">
	 		<jsp:include page="goods/list.jsp" flush="true" />
	 	</div>
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
