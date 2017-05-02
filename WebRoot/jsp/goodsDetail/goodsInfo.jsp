<%@page import="cn.com.domain.GoodsPublish"%>
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
    
    <title>My JSP 'goodsInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath %>resources/goodsDetail.js" type="text/javascript" charset="utf-8"></script>
	
	
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
							<div>
							<small>
								<a class="pull-right add-collect" href="javascript:void(0)" style="color:#FFC555"
								 	<c:if test="${user == null }"> onclick="userLogin()"  </c:if>
								 	<c:if test="${user != null }"> onclick="addOrRemoveCollection('${goods.id }')"  </c:if>
								 	target="_parent"
								 	>
								 	<c:if test="${goods.isCollection }">
								 		<span id="iscollection${goods.id }" class= "mainTones-green add-oneCollect" style="display: none;">(取消收藏)</span>
								 		<span id="heart${goods.id }" class="glyphicon glyphicon-heart "></span>
								 	</c:if>
								 	<c:if test="${!goods.isCollection }">
									 	<span id="iscollection${goods.id }" class="mainTones-green add-oneCollect" style="display: none;">+1</span>
									 	<span id="heart${goods.id }" class="glyphicon glyphicon-heart-empty "></span>
								 	</c:if>
								 	<span id="collectionNum${goods.id }">${goods.collectionNum }</span>							 	
								 </a>
							</small>
							</div>
							<br />
							<small class="mainTones-yellow">
								￥${goods.price }
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<small>
									<c:if test="${goods.isBargain == false }">不讲价</c:if>
									<c:if test="${goods.isBargain == true }">可讲价</c:if>
								</small>
								<br/><br/>
								<small >浏览次数：${goods.visited }</small>
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
									<c:if test="${user != null }">
									<a class="mainTones-gray" href="/CSHT/user/info?id=${goods.userId }" target="_parent"><span>
									</c:if>
									<c:if test="${user == null }">
									<a class="mainTones-gray" href="javascript:void(0)" onclick="userLogin()">
									</c:if>
									${goods.user.nickName }</span></a>
									<img alt='lv' src="<%=PropertyFactory.getProperty("serverPath")
											+PropertyFactory.getProperty("sysImg") %>lv/lv
											<c:if test="${goods.user.integral < 50 }">
											1
											</c:if>
											<c:if test="${goods.user.integral < 25600 && goods.user.integral >=50 }">
											<%=(int)(Math.log(((GoodsPublish)request.getAttribute("goods")).getUser().getIntegral()/50.0)/Math.log(2.0))+1 %>
											</c:if>
											<c:if test="${goods.user.integral >= 25600 }">
											10
											</c:if>
											.png" 
											class="img-circle" width="40px"
											/>
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
					
					<div class="btn-group dropup">
						 <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">分享</button>
						<ul class="dropdown-menu" style="width:200px;">
							<li style="width:auto;">
								<div>
									<textarea id="goodsURL" style="resize: none;width:198px; overflow:auto;word-wrap:break-word;word-break:keep-all;white-space:nowrap;" ><%=basePath+"goods/detail?id="%>${goods.id }</textarea>
									 <small class="mainTones-green" style="line-height: 40px;">把链接发给朋友吧</small>
									 <span class="btn btn-default pull-right" data-clipboard-target="goodsURL" onclick="copytext()">复制链接</span>
								 </div>
							</li>
						</ul>
					</div>
					<br/><br/><br/>
					
					
					<input type="hidden" id="viewedId" value="${goods.user.id }" />
					<input type="hidden" id="goodsId" value="${goods.id }" />
					<span class="btn mainTones-bg-green mainTones-white font-bolder"
							<c:if test="${user == null }"> onclick="userLogin()" </c:if>	
							<c:if test="${user != null }"> 
							title="提示：成功查看卖家联系方式，您的信息已被记录" data-container="body"
            				data-toggle="popover" data-placement="right"
           				 	data-content="${goods.contactMethod }"
							</c:if>
								>
									&nbsp;&nbsp;我要联系卖家&nbsp;&nbsp;
					</span>
					<small class="mainTones-gray">（查看卖家联系方式将会记录您的信息）</small>
				</div>
			</div>
		</div>
	</div>
    	
  </body>
</html>
