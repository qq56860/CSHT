<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
    
	<div class="modal-dialog ">
		<div class="modal-content mainTones-bg-green">
			
	        <div class="tab-content">
	        	<div class="tab-pane" id="login-tab">
		            <div class="modal-header" style="height: 150px;">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="myModalLabel">用户登录</h4>
		            </div>
		            <form class="form-horizontal" role="form" id="login_form" >
			            <div class="modal-body">
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">账户</label>
										<div class="col-sm-7">
											<input type="text" name="account" class="form-control" id="username" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
										<div class="col-sm-7">
											<input type="password" name="password" class="form-control" id="password" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<div class="checkbox">
												 <label><input name="remember" type="checkbox" />Remember me</label>
											</div>
										</div>
									</div>
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="login_register" class="btn btn-default pull-left">注册</button>
				            	<button type="button" id="login_forget" class="btn btn-default pull-left">忘记密码</button>
				                <button type="button" id="loginBtn" class="btn btn-primary" onclick="form_ajax('/CSHT/user/login','login_form','/CSHT/jsp/user/login_logined.jsp')">登录</button>
				            </div>
			            </div>
		            </form>
	        	</div>
	        </div>   
	           
	        <div class="tab-content">
	            <div class="tab-pane" id="register-tab">
	        		<div class="modal-header" style="height: 150px;">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="myModalLabel">用户注册</h4>
		            </div>
		            <form class="form-horizontal" role="form" id="reg_form">
			            <div class="modal-body">
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">昵称</label>
										<div class="col-sm-7">
											<input type="text" name="nickname" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										 <div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
										<div class="col-sm-7">
											<input type="email" name="email" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">手机</label>
										<div class="col-sm-7">
											<input type="text" name="phone" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
										<div class="col-sm-7">
											<input type="text" name="password" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">确认密码</label>
										<div class="col-sm-7">
											<input type="text" name="confirm_password" class="form-control"/>
										</div>
									</div>
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="register_login" class="btn btn-default pull-left">登录</button>
				                <button type="button" class="btn btn-primary" onclick="form_ajax('/CSHT/user/reg','reg_form','/CSHT/jsp/user/login_logined.jsp')">确认注册</button>
				            </div>
			            </div>
		        	</form>
	        	</div>
	    	</div>
	    	
	    	<div class="tab-content">
	            <div class="tab-pane" id="forget-tab">
	        		<div class="modal-header" style="height: 150px;">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                <h4 class="modal-title" id="myModalLabel">密码找回</h4>
		            </div>
		            <form class="form-horizontal" role="form" id="forget_form">
			            <div class="modal-body">
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">手机</label>
										<div class="col-sm-7">
											<input type="email" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">验证码</label>
										<div class="col-sm-7">
											<input type="email" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">新密码</label>
										<div class="col-sm-7">
											<input type="email" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">确认密码</label>
										<div class="col-sm-7">
											<input type="email" class="form-control"/>
										</div>
									</div>
									
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="forget_login" class="btn btn-default pull-left">登录</button>
				                <button type="button" class="btn btn-primary">确认更改</button>
				            </div>
				        </div>
		            </form>
	            </div>	
	        </div>
	          
	            
	    </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->

  </body>
</html>
