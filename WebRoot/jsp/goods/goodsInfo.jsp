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
    
    <title>My JSP 'goodsInfo.jsp' starting page</title>
    
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
    	
    	<div class="row clearfix">
    	
		<div class="col-md-7 column">
			<div class="tabbable text-center" id="tabs-826853">
				
				<div class="tab-content">
					<c:forEach items="${picList }" var="pic" varStatus="status" >
						<c:if test="${status.index == 0 }">
							<div class="tab-pane active" id="panel-${status.index }">
						</c:if>
						<c:if test="${status.index != 0 }">
							<div class="tab-pane" id="panel-${status.index }">
						</c:if>
							<p>
								<img src="${pic }" 
									style="width: 350px;height: 350px;"/>
							</p>
						</div>
					</c:forEach>
				</div>
				<div class="center">
					<ul class="nav nav-tabs ">
						<c:forEach items="${picList }" var="pic" varStatus="status">
							<c:if test="${status.index == 0 }">
								<li class="active">
							</c:if>
							<c:if test="${status.index != 0 }">
								<li class="">
							</c:if>
								 <a href="#panel-${status.index }" data-toggle="tab">
								 	<img src="${pic }" style="width: 100px;height: 100px;"/>
								 </a>
							</li>
						</c:forEach>
						
					</ul>
				</div>
				
			</div>
		</div>
		<div class="col-md-5 column">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="page-header">
						<h1 >
							<span class=""> ${goods.goodsName }</span><br />
							<small class="mainTones-yellow">
								￥1111
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<small>
									<c:if test="${goods.isBargain == false }">不讲价</c:if>
									<c:if test="${goods.isBargain == true }">可讲价</c:if>
								</small>
								<br/><br/>
								<small >浏览次数：啦啦啦不知道</small>
							</small>
						</h1>
					</div>
					<table class="table">
						<thead >
							
						</thead>
						<tbody>
							<tr>
								<td style="width: 30%;">
									<span class="pull-right mainTones-bg-green mainTones-white font-bolder">
									&nbsp;&nbsp;卖家&nbsp;&nbsp;</span> 
								</td>
								<td >
									<span>${goods.user.nickName }</span>
								</td>
							</tr>
							<tr>
								<td style="width: 30%;">
									<span class="pull-right mainTones-bg-green mainTones-white font-bolder">
									&nbsp;&nbsp;交易地点&nbsp;&nbsp;</span> 
								</td>
								<td >
									<span>${goods.tradePlace }</span>
								</td>
							</tr>
							<tr>
								<td style="width: 30%;">
									<span class="pull-right mainTones-bg-green mainTones-white font-bolder">
									&nbsp;&nbsp;发布时间&nbsp;&nbsp;</span> 
								</td>
								<td >
									<span>${goods.createTime }</span>
								</td>
							</tr>
						</tbody>
					</table>
					我想买！查看联系方式
					<br/><br/>
					<div class="btn-group dropup">
						 <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">分享</button>
						<ul class="dropdown-menu">
							<li>
								 <a href="#">该功能暂未开放</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
    	
  </body>
</html>
