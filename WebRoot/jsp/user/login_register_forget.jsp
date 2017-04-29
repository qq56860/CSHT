<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.com.util.PropertyFactory"%>
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
		<div class="modal-content" style="background-image:url('<%=basePath %>resources/img/bg-green.png');">
			
	        <div class="tab-content">
	        	<div class="tab-pane" id="login-tab">
		            <div class="modal-header" style="height: 150px; background-image:url('<%=basePath %>resources/img/login.png');">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                
		            </div>
		            <form class="form-horizontal" role="form" id="login_form" >
			            <div class="modal-body" >
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">账户</label>
										<div class="col-sm-7">
											<input type="text" name="account" class="form-control" id="loginAccount" 
													placeholder="请输入手机号或邮箱" onblur="loginAccountOnblur()" autocomplete="off"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
										<div class="col-sm-7">
											<input type="password" name="password" class="form-control" id="loginPassword" 
													placeholder="6-18位密码" onblur="loginPasswordOnblur()" autocomplete="off"/>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										 <label for="inputEmail3" class="col-sm-2 control-label">验证码</label>
										<div class="col-sm-3">
											<input type="text" class="form-control" id="loginCode" 
													placeholder="图片验证码" onkeyup="loginCodeOnkeyup()" autocomplete="off"/>
										</div>
										<iframe src="/CSHT/jsp/util/loginCode.jsp" id="loginCodeFrame" width="0" height="0" ></iframe>
										<div class="col-sm-3">
											<a href="javascript:void(0)" onclick="changeVerifyCode()">
											<img src="/CSHT/code/getVerifyCode" border=0 id="validateCodeImg" style="height: 35px; width:80px" />
										<!-- 	<script type="text/javascript">
												refreshCodeIfrme();
											</script> -->
											<br/>看不清？换一张</a>
										</div>
										<div class="col-sm-1">
											<div class="mainTones-yellow col-sm-1 hidden" id="loginCodeOK" ><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;left:-180px;"></span></div>
											<div class="col-sm-1 hidden" id="loginCodeWRONG" style="color: red;"><span class="glyphicon glyphicon-remove" style="position: relative;top:10px;left:-180px;"></span></div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-1"></div>
										<div class="col-sm-5">
											<div class="checkbox">
												 <label><input name="remember" type="checkbox" value="1" />Remember me(7天免登录)</label>
											</div>
										</div>
										<div class="col-sm-1"></div>
										<div class="col-sm-5">
											<span style="color: red;" id="errorSpan"></span>
										</div>
									</div>
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="login_register" class="btn btn-default pull-left">注册</button>
				            	<button type="button" id="login_forget" class="btn btn-default pull-left">忘记密码</button>
				                <button type="button" id="loginBtn" class="btn btn-primary" onclick="form_ajax('/CSHT/user/login','login_form','login')">登录</button>
				            </div>
			            </div>
		            </form>
	        	</div>
	        </div>   
	           
	        <div class="tab-content">
	            <div class="tab-pane" id="register-tab">
	        		<div class="modal-header" style="height: 150px; background-image:url('<%=basePath %>resources/img/reg.png');">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            </div>
		            <form class="form-horizontal" role="form" id="reg_form">
			            <div class="modal-body" >
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">昵称</label>
										<div class="col-sm-6">
											<input type="text" name="nickname" class="form-control"  id="nickname"
												placeholder="请输入2-8位昵称" onblur="nicknameOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="nicknameOK" ><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="nicknameSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
										<div class="col-sm-6">
											<input type="email" name="email" class="form-control" id="email"
												placeholder="请输入邮箱地址" onblur="emailOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="emailOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="emailSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">手机</label>
										<div class="col-sm-6">
											<input type="text" name="phone" class="form-control"  id="phone"
												placeholder="请输入手机号码" onblur="phoneOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="phoneOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="phoneSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">密码</label>
										<div class="col-sm-6">
											<input type="text" name="password" class="form-control"  id="password"
												placeholder="请输入以英文字母开头的6-18位密码" onblur="passwordOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="passwordOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="passwordSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">确认密码</label>
										<div class="col-sm-6">
											<input type="text" name="confirm_password" class="form-control"  id="confirm_password"
												placeholder="再次确认密码" onblur="confirm_passwordOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="confirm_passwordOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="confirm_passwordSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">
										 	<input type="button" id="code-btn" class="btn btn-info" value="获取验证码" onclick="getCode();"  style="position:relative;top:-7px;left:18px; " />
										 </label>
										<div class="col-sm-4">
											<input type="text" name="code" class="form-control"  id="code"
												placeholder="请输入4位验证码" onblur="codeOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="codeOK" ><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="codeSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="register_login" class="btn btn-default pull-left">登录</button>
				                <button type="button" class="btn btn-primary" onclick="form_ajax('/CSHT/user/reg','reg_form','reg')">确认注册</button>
				            </div>
			            </div>
		        	</form>
	        	</div>
	    	</div>
	    	
	    	<div class="tab-content">
	            <div class="tab-pane" id="forget-tab">
	        		<div class="modal-header" style="height: 150px;background-image:url('<%=basePath %>resources/img/forget.png');">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		                
		            </div>
		            <form class="form-horizontal" role="form" id="forget_form" >
			            <div class="modal-body">
							<div class="row-fluid text-center">
								<div class="span12">
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<div class="form-group" style="width: 105px;position:absolute ;top:-1px;left: 63px;">
												<select class="form-control" name="find-type" id="find-type" onchange="phoneOrEmailOnblur()" >
													<option value="email">邮件找回</option>
													<option value="phone">短信找回</option>
												</select>
											</div>
										</div>
										<div class="col-sm-6">
											<input type="text" name="phoneOrEmail" class="form-control" id="phoneOrEmail"
												placeholder="请输入对应号码" onblur="phoneOrEmailOnblur()" autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="phoneOrEmailOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="phoneOrEmailSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">新密码</label>
										<div class="col-sm-6">
											<input type="text" name="newPassword" class="form-control"  id="newPassword"
												placeholder="请输入以英文字母开头的6-18位密码" onblur="newPasswordOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="newPasswordOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="newPasswordSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">确认密码</label>
										<div class="col-sm-6">
											<input type="text" class="form-control"  id="newConfirm_password"
												placeholder="再次确认密码" onblur="newConfirm_passwordOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="newConfirm_passwordOK"><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="newConfirm_passwordSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
									<div class="form-group">
										 <label for="inputEmail3" class="col-sm-3 control-label">
										 	<input type="button" id="find-code-btn" class="btn btn-info" value="获取验证码" onclick="find_getCode();"  style="position:relative;top:-7px;left:18px; " />
										 </label>
										<div class="col-sm-4">
											<input type="text" name="code" class="form-control"  id="find-code"
												placeholder="请输入4位验证码" onblur="find_codeOnblur() " autocomplete="off"/>
										</div>
										<div class="mainTones-yellow col-sm-1 hidden" id="find-codeOK" ><span class="glyphicon glyphicon-ok" style="position: relative;top:10px;"></span></div>
										<span id="find-codeSpan" class="reg-inputSpan" style="color: red;"></span>
									</div>
								</div>
							</div>
				            <div class="modal-footer">
				            	<button type="button" id="forget_login" class="btn btn-default pull-left">登录</button>
				                <button type="button" class="btn btn-primary" onclick="form_ajax('/CSHT/user/findPassword','forget_form','find')">确认更改</button>
				            </div>
				        </div>
		            </form>
	            </div>	
	        </div>
	          
	            
	    </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->

  </body>
</html>
