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
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/list.css"/>
	
	<script src="<%=basePath %>resources/jquery-3.1.1.js" type="text/javascript" charset="utf-8"></script>
	
	
	<script src="<%=basePath %>resources/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
	
	<script src="<%=basePath %>resources/homePage_list.js" type="text/javascript" charset="utf-8"></script>
  </head>
  
  <body>
    
	<div class="row clearfix list">
		
		<div class="col-md-12 column">
			
			
			<div class="dropdown">
			    <div class="form-group">
				    <select class="form-control select-proprety  btn btn-lg mainTones-bg-green mainTones-white font-bolder"
				    		onchange="list_search('time_collection','')" id="time_collection_select">
					      <option  value="time" >最新发布</option>
					      <option  value="collection" ${time_collection == 'collection' ?"selected = 'selected'":"" } >最多收藏</option>
				    </select>
				</div>
			</div>
			
			<div class="dashed"> 
				<ul>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					
				</ul>
			</div>
			
			<br />
			
			<div class="row" style="width: 1000px;">
			
				<c:forEach items="${goodsPublish }" var="publish">
					<div class="col-md-4">
						<div class="thumbnail">
							<div class="img_div">
								<a href="javascript:void(0)" onclick="list_detail(${publish.id })">
									<img style="width: 299px;height: 299px;" alt="${publish.goodsName }" src="${publish.pic }"/>
								</a>
							</div>
							<br />
							<div class="caption">
								<a href="javascript:void(0)" onclick="list_detail(${publish.id })">
									<b class="mainTones-green">${publish.goodsName }</b>
									<small class="pull-right mainTones-yellow font-bolder">￥${publish.price }</small>
								</a>
								<p> 
									 <a class="mainTones-yellow font-bolder pull-right" href="#">
									 	<span class="glyphicon glyphicon-heart "></span>
									 	${publish.collectionNum }
									 </a>
									 
									 <br/>
									
									<span class="mainTones-gray">发布人：</span> 
									<a class="btn mainTones-gray" href="#">${publish.user.nickName }</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			
			<div class="col-md-12 pager">
				<ul class="pagination pagination-lg">
				<c:if test="${pageNum > 1 }">
					<li><a href="javascript:void(0)" onclick="list_search('page','${pageNum-1 }')"><span class="mainTones-green">&laquo;</span></a></li>
				</c:if>
				<c:if test="${pageNum > 4 }">
					<li><a href="javascript:void(0)" onclick="list_search('page','1')"><span class="mainTones-green">1</span></a></li>
				</c:if>
				<c:if test="${pageNum >= 4 }">
					<li><a href="javascript:void(0)"><span class="mainTones-green">...</span></a></li>
				</c:if>
				
				<c:forEach var="num" begin="${beginPage }" end="${endPage }" >
					<c:if test="${num == pageNum }">
						<li>
							<a href="javascript:void(0)" onclick="list_search('page',${num })">
								<span >
									${num }
								</span> 
							</a>
						</li>
					</c:if>
					<c:if test="${num != pageNum }">
						<li class="mainTones-yellow">
							<a href="javascript:void(0)" onclick="list_search('page',${num })">
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
					<li><a href="javascript:void(0)" onclick="list_search('page','${pageNumber }')"><span class="mainTones-green">${pageNumber }</span></a></li>
				</c:if>
				<c:if test="${pageNum < pageNumber }">
					<li><a href="javascript:void(0)" onclick="list_search('page','${pageNum+1 }')"><span class="mainTones-green">&raquo;</span></a></li>
				</c:if>
				</ul>
			</div>
			
				
				
			<!-- list页面隐藏域 -->		
			<form action="/CSHT/homePageList" id="search_form">
				<input type="hidden" name="search" id="search_search" value="${search }"/>
				<input type="hidden" name="type" id="type_search" value="${type }"/>
				<input type="hidden" name="sub" id="sub_search" value="${sub }"/>
				<input type="hidden" name="time_collection" id="time_collection_search" value="${time_collection }" />
				<input type="hidden" name="pageNum" id="page_search" />
			</form>
					
					
					
					
			
			</div><!-- row -->
		</div><!-- column9 -->
		
	</div>
	


  </body>
</html>
