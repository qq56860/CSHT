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
    
    <title>求购发布</title>
    
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
  
  <body>
    	
   	<div>
    	<jsp:include page="/jsp/header.jsp" flush="true"/>
    </div>
    
    <!-- **************** -->
    <script src="<%=basePath %>resources/changeFrameHeight.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>resources/publish.js" type="text/javascript" charset="utf-8"></script>
	<!-- **************** -->
		
		
	<div class="container" style="position: relative;top: 200px;left:-100px;">
			<div class="row clearfix">
				<div class="col-md-3 column">
				</div>
				<div class="col-md-6 column">
					<form class="form-horizontal" id="buyPublishForm" enctype="multipart/form-data" role="form" action="/CSHT/publish/addBuy" method="post">
						
						<div class="form-group">
							 <label for="inputEmail3" class="col-sm-2 control-label">物品名称</label>
							<div class="col-sm-6">
								<input type="text" name="goodsName" class="form-control" id="goodsName" placeholder="32字以内" onkeyup="goodsNameKeyup()" autocomplete="off"/>
							</div>
							<span id="goodsNameSpan" class="reg-inputSpan" style="color: red;"></span>
						</div>
						<div class="form-group">
							 <label for="inputEmail3" class="col-sm-2 control-label">物品详情</label>
							<div class="col-sm-8">
								<textarea name="goodsContent" id="goodsContent" rows="3" cols="100%" class="form-control" style="resize:none;" placeholder="200字以内" onkeyup="goodsContentKeyup()" autocomplete="off"></textarea>
							</div>
							<span id="goodsContentSpan" style="color: red;position: absolute;left:480px;"></span>
						</div>
						<div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">交易地点</label>
							<div class="col-sm-6">
								<input type="text" name="tradePlace" class="form-control" id="tradePlace" onkeyup="tradePlaceKeyup()" autocomplete="off"/>
							</div>
							<span id="tradePlaceSpan" class="reg-inputSpan" style="color: red;"></span>
						</div>
						<div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">期望价格</label>
							<div class="col-sm-5">
								<div class="form-group">
									<label for="inputPassword3" class="col-sm-2 control-label mainTones-gray">￥</label>
									<div class="col-sm-8">
										<input type="text" name="price" class="form-control" id="price" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'');priceKeyup()" onafterpaste="this.value=this.value.replace(/[^0-9.]/g,'')" autocomplete="off"/>
									</div>
									<label for="inputPassword3" class="col-sm-2 control-label mainTones-gray">元</label>
									<span id="priceSpan" class="reg-inputSpan" style="color: red;"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">分类</label>
							<div class="col-sm-4">
								<select class="form-control" name="type"  >
								  <c:forEach items="${type }" var="type">	
								      <option value="${type.id }">${type.type }</option>
							      </c:forEach>
							    </select>
							</div>
						</div>
						
						<div class="form-group">
							 <label for="inputPassword3" class="col-sm-2 control-label">联系方式</label>
							<div class="col-sm-8">
								<input type="text" name="contact" class="form-control" id="contact" placeholder="例如---qq：xxxxxxxxx" onkeyup="contactKeyup()"  autocomplete="off"/>
							</div>
							<span id="contactSpan" style="color: red;position: absolute;left:480px;"></span>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									 <label><input type="checkbox" id="agreeRule" checked="checked" />我同意
									 	<a href="/CSHT/publish/rule" target="_blank">物品发布规则</a>
									 </label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								 <button type="button" class="btn btn-default" onclick="buySubmit()">发布</button>
							</div>
							<span id="errorSpan" class="reg-inputSpan" style="color: red;"></span>
						</div>
					</form>
				</div>
				<div class="col-md-3 column">
				</div>
			</div>
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		</div>    
	    
    	
  </body>
</html>
