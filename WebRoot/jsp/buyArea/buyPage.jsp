
<%@page import="cn.com.util.PropertyFactory"%>
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
	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>resources/logo.ico" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body style="overflow-x:hidden">
   	<div>
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
    
    <!-- ******************** -->
    <script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=basePath %>resources/buyPage.js" type="text/javascript" charset="utf-8"></script>
    <!-- ******************** -->
    
    <div class="list-publish" onmouseover="this.style.cursor='hand'" <c:if test="${user == null }"> onclick="userLogin()" </c:if>
			<c:if test="${user != null }"> onclick="javascript:window.location.href='/CSHT/publish/goods'" </c:if>
			  >
		<img alt="" src="<%=PropertyFactory.getProperty("serverPath")
						+PropertyFactory.getProperty("sysImg") %>publishBTN.gif" 
						alt='btn'
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
    
    <div class="container" style="position: relative ;top:150px;left:-100px;">
    	<div class="row clearfix">
			<div class="col-md-2 column">
			</div>
			<div class="col-md-8 column">
				<form action="/CSHT/buyPage" id="buySearchForm">
		    		<div class="input-group" style="left:245px;">
		    			<span class="input-group-addon btn btn-primary" onclick="buySearch('key','')">搜索</span>
						<input name="buySearch" type="text" class="form-control input-lg" style="width: 300px;"
								placeholder="输入关键字进行求购信息搜索" value="${buySearch }" autocomplete="off" />
						<input type="hidden" name="page" id="page" value="${pageNum }"/>
					</div>
				</form>
				<br/>
				
				<c:forEach items="${buyList }" var="buy">
					<div class="media" >
						 <c:if test="${user != null }">
						 <a href="/CSHT/user/info?id=${buy.user.id }" target="_blank" class="pull-left">
						 </c:if>
						 <c:if test="${user == null }">
						 <a href="javascript:void(0)" onclick="userLogin()" class="pull-left">
						 </c:if>
						 <img src="<%=PropertyFactory.getProperty("serverPath")
									+PropertyFactory.getProperty("userImgPath") %>${buy.user.pic }.png" 
									class="media-object img-circle" alt='头像' width="130px" height="130px;"/>
						
						</a>
						<div class="media-body">
							<h4 class="media-heading font-bolder">
								${buy.goodsName }
							</h4> 
							<span class="mainTones-green">期望价格：</span><span class="mainTones-yellow">￥${buy.wishPrice }</span> &nbsp;&nbsp;&nbsp;
							<span class="mainTones-green">交易地点：</span><span class="mainTones-yellow">${buy.tradePlace }</span> <br />
							<div class="mainTones-gray" style="height: 40px;">
								${buy.goodsContent }
							</div>
							<span> 
							<c:if test="${user != null }">
							<a href="/CSHT/user/info?id=${buy.user.id }" target="_blank">
							</c:if>
							<c:if test="${user == null }">
							<a href="javascript:void(0)" onclick="userLogin()">
							</c:if>
							${buy.user.nickName }</a></span>&nbsp;&nbsp;&nbsp;
							
							
							<input type="hidden" id="viewedId" value="${buy.user.id }" />
							<input type="hidden" id="goodsId" value="${buy.id }" />
							<span class="btn mainTones-bg-green mainTones-white font-bolder"
								<c:if test="${user == null }"> onclick="userLogin()" </c:if>	
								<c:if test="${user != null }"> 
								title="提示：成功查看卖家联系方式，您的信息已被记录" data-container="body"
	            				data-toggle="popover" data-placement="right"
	           				 	data-content="${buy.contactMethod }"
								</c:if>
									>
										&nbsp;&nbsp;我要联系卖家&nbsp;&nbsp;
							</span>
							<small class="mainTones-gray">（查看卖家联系方式将会记录您的信息）</small>
							
							
							
							<c:if test="${user == null }">
								<a href="javascript:void(0)" onclick="userLogin()" class="pull-right">
							</c:if>		
							<c:if test="${user != null }">
								<a data-toggle="collapse" data-parent="#accordion" 
							   		href="#collapse-${buy.id }" class="pull-right">
							</c:if>	
								评论
							</a>	
							
						</div>
					</div>
					<c:if test="${user != null }">
						<div id="collapse-${buy.id }" class="panel-collapse collapse">
							<p>
								<iframe src="/CSHT/goods/buy/commentList?goodsid=${buy.id }" 
											align="middle" id="iframe-${buy.id }" 
											frameborder="0" scrolling="no" 
											allowTransparency="true" 
											width="1100"
											>
								</iframe>
							</p>
						</div>
					</c:if>
				</c:forEach>
			</div>
			
			<div class="col-md-2 column">
			</div>
			
			<div class="col-md-12 pager">
				<ul class="pagination pagination-lg">
				<c:if test="${pageNum > 1 }">
					<li><a href="javascript:void(0)" onclick="buySearch('page','${pageNum-1 }')"><span class="mainTones-green">&laquo;</span></a></li>
				</c:if>
				<c:if test="${pageNum > 4 }">
					<li><a href="javascript:void(0)" onclick="buySearch('page','1')"><span class="mainTones-green">1</span></a></li>
				</c:if>
				<c:if test="${pageNum >= 4 }">
					<li><a href="javascript:void(0)"><span class="mainTones-green">...</span></a></li>
				</c:if>
				
				<c:forEach var="num" begin="${beginPage }" end="${endPage }" >
					<c:if test="${num == pageNum }">
						<li>
							<a href="javascript:void(0)" onclick="buySearch('page',${num })">
								<span >
									${num }
								</span> 
							</a>
						</li>
					</c:if>
					<c:if test="${num != pageNum }">
						<li class="mainTones-yellow">
							<a href="javascript:void(0)" onclick="buySearch('page',${num })">
								<span class="mainTones-yellow">
									${num }
								</span> 
							</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${pageNum+3 <= pageNumber && pageNumber >= 6 }">
					<li><a><span class="mainTones-yellow">.....</span></a></li>
				</c:if>
				
				<c:if test="${pageNum+3 < pageNumber && pageNumber > 6 }">
					<li><a href="javascript:void(0)" onclick="buySearch('page','${pageNumber }')"><span class="mainTones-green">${pageNumber }</span></a></li>
				</c:if>
				<c:if test="${pageNum < pageNumber }">
					<li><a href="javascript:void(0)" onclick="buySearch('page','${pageNum+1 }')"><span class="mainTones-green">&raquo;</span></a></li>
				</c:if>
				</ul>
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
    
    <a href="" id="login" data-toggle="modal" class="a-hovor"></a>
    <a href="" id="register" data-toggle="modal" class="a-hovor"></a>
 	<div class="modal fade" id="login_register_forget_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
			<jsp:include page="/jsp/user/login_register_forget.jsp" flush="true"></jsp:include>
			
	</div>
    
    	
  </body>
</html>
