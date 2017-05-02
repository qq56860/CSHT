<%@page import="java.net.URLDecoder"%>
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
    
    <title>八一农大2货网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>resources/logo.ico" media="screen">
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/list.css"/>
	
  </head>
  
  <body style="overflow-x:hidden">
  
    <div >
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
    
    
    <div>
	    <div class="row clearfix" >
	   
		    <div class="col-sm-1 column" style="position: relative;">
		    	<jsp:include page="/jsp/home/left.jsp" flush="true" />
		    </div>
		    
			<div class="col-sm-10 column  list" style="position: relative;">
			 		<iframe src="/CSHT/homePageList?search=${forwordSearch }&type=${forwordType }&sub=${forwordSub }&time_collection=${order }&=pageNum${page }" 
			 					align="middle" id="list_iframe"  
								frameborder="0" scrolling="no"
								width="1100"
								>
					</iframe>
			</div>
			
		</div> 
	</div>	
	
	<!-- footer --> 
	<div class="row clearfix" >
		<div class="col-sm-12 column header-bg-color header-border-color" 
				style="position:relative ;top:200px;height:200px;z-index: 0; "
			           >
	   		<jsp:include page="/jsp/footer.jsp" flush="true"/>
	   	</div>
   	</div>
   	
	<div class="list-publish" onmouseover="this.style.cursor='hand'" <c:if test="${user == null }"> onclick="userLogin()" </c:if>
			<c:if test="${user != null }"> onclick="javascript:window.location.href='/CSHT/publish/goods'" </c:if>
			  >
		<img alt="btn" src="<%=PropertyFactory.getProperty("serverPath")
						+PropertyFactory.getProperty("sysImg") %>publishBTN.gif" 
						/>
	</div>
	
	<div class="list-buy" onmouseover="this.style.cursor='hand'" <c:if test="${user == null }"> onclick="userLogin()" </c:if>
		<c:if test="${user != null }"> onclick="javascript:window.location.href='/CSHT/publish/buy'" </c:if>
		>
		<img alt="" src="<%=PropertyFactory.getProperty("serverPath")
						+PropertyFactory.getProperty("sysImg") %>buyBTN.gif" 
						alt='btn'
						/>
	</div>
		 
	 <div class="navbar-fixed-bottom buy_btn" >
		 <a href="/CSHT/buyPage">
			 <button type="button" class="btn btn-lg mainTones-green">
	             <span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;
	             <span class="font-bolder">求购专区</span>
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
    
   <c:if test="${not empty logoutMsg }">
    	<script type="text/javascript">
    		$(function(){
	    		alert('<%=URLDecoder.decode(request.getParameter("logoutMsg"))%>' );
	    		window.location.href="/CSHT/homePage";
    		});
    	</script>
    </c:if>
    
  </body>
  
  
</html>
