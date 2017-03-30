<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

  </head>
  
  <body>
    
	<div class="row clearfix list">
		
		<div class="col-md-12 column">
			
			
			<div class="dropdown">
			    <div class="form-group">
				    <select class="form-control select-proprety  btn btn-lg mainTones-bg-green mainTones-white font-bolder">
				      <option  value="">最新发布</option>
				      <option  value="">最多收藏</option>
				    </select>
				</div>
			
			<div class="dashed"> 
				<ul>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					
				</ul>
			</div>
			
			<div class="btn btn-lg mainTones-white navbar-fixed-bottom" id="">
				 <a href="#" class="pull-right">
					 <button type="button" class="btn btn-default btn-lg mainTones-green">
			             <span class="glyphicon glyphicon-log-in "></span>&nbsp;&nbsp;
			             <span class="mainTones-green font-bolder">求购专区</span>
			         </button>
		         </a>
			</div>
			
			<div class="btn btn-lg mainTones-bg-yellow mainTones-white list-publish">
				<span>我要发布</span>
			</div>
			
			<div class="btn btn-lg mainTones-bg-yellow mainTones-white list-buy">
				<span>我要求购</span>
			</div>
			
			<br />
			
			
			
			<div class=""></div>
			
			<div class="row" style="width: 1000px;">
				<div class="col-md-4">
					
					<div class="thumbnail">
						<a href="#">
							<img alt="300x200" src="v3/default4.jpg" />
						</a>
						<div class="caption">
							<a href="#">
								<b>奥迪牌嘎嘎自行车</b>
								<small class="pull-right">999</small>
							</a>
							<p>
								a
							</p>
							<p> 
								 <a class="btn" href="#">高一三班班草</a>
								 
								 <a class="btn pull-right" href="#">165收藏</a>
							</p>
						</div>
					</div>
					
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="300x200" src="v3/default5.jpg" />
						<div class="caption">
							<h3>
								Thumbnail label
							</h3>
							<p>
								b
							</p>
							<p>
								 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="300x200" src="v3/default6.jpg" />
						<div class="caption">
							<h3>
								Thumbnail label
							</h3>
							<p>
								c
							</p>
							<p>
								 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="300x200" src="v3/default6.jpg" />
						<div class="caption">
							<h3>
								Thumbnail label
							</h3>
							<p>
								c
							</p>
							<p>
								 <a class="btn btn-primary" href="#">Action</a> <a class="btn" href="#">Action</a>
							</p>
						</div>
					</div>
				</div>
			
			</div><!-- row -->
		</div><!-- column9 -->
		
	</div>
	


  </body>
</html>
