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
    
    <title>查看用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>resources/logo.ico" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/css/publish.css"/>
	
  	
  </head>
  
  <body>
    
    <div>
    	<jsp:include page="userCard.jsp" flush="true"/>
    </div>
    
    <!-- **************** -->
    <script src="<%=basePath %>resources/userInfo.js" type="text/javascript" charset="utf-8"></script>
	<!-- **************** -->
   
	
	<c:if test="${errorLog != null }">
		<script type="text/javascript">
			alert('${errorLog }');
		</script>
	</c:if>
	
	<div class="container" style="position: relative;top:130px;left:100px;">
			<div class="row clearfix">
				<div class="col-md-10 column">
					<div class="tabbable" id="tabs-640434">
						<ul class="nav nav-tabs text-center">
							<c:if test="${user.id == reqUser.id }">
								<li class="active" style="width: 20%;" >
									 <a href="#panel-myInfo" data-toggle="tab">个人中心</a>
								</li>
								<li style="width: 20%;">
									 <a href="/CSHT/user/collection?id=${reqUser.id }">我的收藏</a>
								</li >
								<li style="width: 20%;">
									 <a href="/CSHT/user/publish?id=${reqUser.id }">物品发布</a>
								</li>
								<li style="width: 20%;">
									 <a href="/CSHT/user/buy?id=${reqUser.id }">物品求购</a>
								</li>
								<li style="width: 20%;">
									 <a href="/CSHT/user/news?id=${reqUser.id }">我的消息<span style="color: red;">&nbsp;&nbsp;&nbsp;${reqUser.notReadNews }</span></a>
								</li>
							</c:if>
							<c:if test="${user.id != reqUser.id }">
								<li style="width: 33%;">
									 <a href="/CSHT/user/collection?id=${reqUser.id }">TA的收藏</a>
								</li >
								<li style="width: 34%;">
									 <a href="/CSHT/user/publish?id=${reqUser.id }">TA的发布</a>
								</li>
								<li style="width: 33%;">
									 <a href="/CSHT/user/buy?id=${reqUser.id }">TA的求购</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
				<div class="col-md-1 column">
				
				</div>
				<div class="col-md-1 column">
				
				</div>
				<c:if test="${user.id == reqUser.id }">
				<div class="col-md-9 column tab-pane active" id="panel-myInfo">
					<br/><br/>
					<table class="table">
						<thead >
							
						</thead>
						<tbody>
							<tr>
								<form id="phoneChangeForm" role="form" action="/CSHT/user/changePhone">
								<input type="hidden" name="id" value="${reqUser.id }">
								<td style="width:80px;">
									<span class="btn pull-right mainTones-bg-green mainTones-white font-bolder">
									&nbsp;&nbsp;手机&nbsp;&nbsp;</span> 
								</td>
								<td style="width:230px;">
									<div class="hidden" id="change-phoneNumber" style="position: absolute;">
									<input type="text" class="form-control" name="newPhone" id="newPhone" placeholder="请输入新手机号"
											autocomplete="off" />
									</div>
									<span class="btn">${user.phone }</span>
								</td>
								<td style="width:300px;">
									<div class="hidden" id="change-phone-validNumber" style="position: absolute;">
										<div class="form-group">
											<input type="text" name="code" class="form-control" placeholder="请输入验证码" 
												style="position:absolute;top:0px; ; width: 110px;" autocomplete="off" />
										</div> 
										<input type="button" class="btn btn-default" style="position:relative;top:-15px; left:120px;"
												onclick="getChangePhoneCode('phone')" id="phoneCodeBtn" value="获取验证码"/>
									</div>
								</td>
								<td style="width:120px;">
									<button type="submit" class="btn btn-primary hidden" style="position: absolute;"
									     id="phone-change" > 确认更改
									</button>
									<button type="button" class="btn btn-primary" 
									    data-toggle="button"  onclick="change_phone()"> 更改手机
									</button>
								</td>
								<td style="width:100px;">
									<button type="button" class="btn btn-primary hidden" 
									    data-toggle="button" id="phone-change-cancel"  onclick="change_phone_cancel()"> 取消
									</button>
								</td>
								</form>
							</tr>
							<tr>
								<form id="phoneChangeForm" role="form" action="/CSHT/user/changeEmail">
								<input type="hidden" name="id" value="${reqUser.id }">
								<td style="width: 80px;">
									<span class="btn pull-right mainTones-bg-green mainTones-white font-bolder">
									&nbsp;&nbsp;邮箱&nbsp;&nbsp;</span> 
								</td>
								<td style="width: 230px;">
									<div class="hidden" id="change-emailNumber" style="position: absolute;">
										<input type="text" class="form-control" name="newEmail" id="newEmail" placeholder="请输入新邮箱地址"
												autocomplete="off" />
									</div>
									<span class="btn">${user.email }</span>
								</td>
								<td style="width: 300px;">
									<div class="hidden" id="change-email-validNumber" style="position: absolute;">
										<div class="form-group">
											<input type="text" name="code" class="form-control" placeholder="请输入验证码" 
												style="position:absolute;top:0px; ; width: 110px;" autocomplete="off" />
										</div> 
										<input type="button" class="btn btn-default" style="position:relative;top:-15px; left:120px;"
												onclick="getChangePhoneCode('email')" id="emailCodeBtn" value="获取验证码"/>
									</div>
								</td>
								<td style="width: 120px;">
									<button type="submit" class="btn btn-primary hidden" style="position: absolute;"
									     id="email-change" > 确认更改
									</button>
									<button type="button" class="btn btn-primary" 
									    data-toggle="button"  onclick="change_email()"> 更改邮箱
									</button>
								</td>
								<td style="width: 100px;">
									<button type="button" class="btn btn-primary hidden" 
									    data-toggle="button" id="email-change-cancel"  onclick="change_email_cancel()"> 取消
									</button>
								</td>
								</form>
							</tr>
							<tr>
								<form id="changePicForm" action="/CSHT/user/changePic" enctype="multipart/form-data" method="post" >
								<td style="width: 80px;">
									<span></span> 
								</td>
								<td style="width: 230px;">
									<input type="hidden" name="id" value="${reqUser.id }">
									<button type="button" class="btn btn-primary hidden" style="position: absolute;"
									     id="confirmChangePic" onclick="picFormSubmit()" > 确认更改
									</button>
									<button type="button" class="btn btn-primary" onclick="changePic()" > 更改头像
									</button>
									<button type="button" class="btn btn-primary hidden"  id="changePic-cancel"  onclick="changePic_cancel()"> 取消
									</button>
									<div id="img" class="hidden">
									 	<div class="userPic">
										    <div class="imgnum">
										        <input name="userPic" type="file" class="filepath" />
										        <span class="userClosePic">X</span>
										        <img src="<%=PropertyFactory.getProperty("serverPath")
							+PropertyFactory.getProperty("sysImg") %>uploadPic.png" class="imgBG" />
										        <img src="" class="imgPic" />
										    </div>
										</div>
									</div>
								</td>
								</form>
								<td style="width: 300px;">
								<form id="changePasswordForm" action="/CSHT/user/changePassword" >
									<input type="hidden" name="id" value="${reqUser.id }">
									<button type="button" class="btn btn-primary hidden" style="position: absolute;"
									     id="confirmChangePassword" onclick="passwordFomrSubmit()" > 确认更改
									</button>
									<button type="button" class="btn btn-primary" onclick="changePassword()"> 更改密码
									</button>
									<button type="button" class="btn btn-primary hidden" id="changePassword-cancel"  onclick="changePassword_cancel()"> 取消
									</button>
									<div class="hidden" id="change-passwordDiv" style="position: absolute;">
										原密码：
										<input type="text" class="form-control" name="oldPassword" id="oldPassword" 
											autocomplete="off" onblur="oldPasswordOnblur()" />
										新密码：
										<input type="text" class="form-control" name="newPassword" id="newPassword" placeholder="字母开头，长度在6-18之间" 
											autocomplete="off" onblur="newPasswordOnblur()" />
										确认密码：
										<input type="text" class="form-control" id="confirmNewPassword"
											autocomplete="off" onblur="confirmNewPasswordOnblur()" />
									</div>
									</form>
									
								</td>
								<td style="width: 120px;">
									
								</td>
								<td style="width: 100px;">
									
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-1 column">
				
				</div>
				</c:if>
			</div>
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
		</div>


  </body>
</html>
