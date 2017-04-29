//登录注册模态框
$(function(){
	
	 $("#login").click(function(){
	   	  $('#login_register_forget_modal').modal("show");
	   	  $('#register-tab').removeClass('active');
	   	  $('#forget-tab').removeClass('active');
	   	  $('#login-tab').addClass('active');
     });
     
     $("#register").click(function(){ 
	   	  $('#login_register_forget_modal').modal("show");
	   	  $('#login-tab').removeClass('active');
	   	  $('#forget-tab').removeClass('active');
	   	  $('#register-tab').addClass('active');
     });  
     
     $("#register_login").click(function(){ 
	   	  $('#login-tab').addClass('active');
	   	  $('#register-tab').removeClass('active');
	   	  $('#forget-tab').removeClass('active');
     }); 
     
     $("#login_register").click(function(){ 
	   	  $('#register-tab').addClass('active');
	   	  $('#login-tab').removeClass('active');
	   	  $('#forget-tab').removeClass('active');
     }); 
     
     $("#login_forget").click(function(){ 
	   	  $('#forget-tab').addClass('active');
	   	  $('#login-tab').removeClass('active');
	   	  $('#register-tab').removeClass('active');
     }); 
     
     $("#forget_login").click(function(){ 
	   	  $('#login-tab').addClass('active');
	   	  $('#forget-tab').removeClass('active');
	   	  $('#register-tab').removeClass('active');
     }); 
     
     //login_logined.jsp  的iframe中的元素时间触发父页面事件
     
   	$("#model_login").click(function(){
   		$("#login").click();
     }); 
   	
   	$("#model_register").click(function(){
   		$("#register").click();
     }); 
     
   	
 });
function userLogin(){
	$("#login").click();
}

//登录注册ajax
function form_ajax(url,form_id,type){
	 if(type == "login"){
		 loginAccountOnblur();
		 loginPasswordOnblur();
		 loginCodeOnkeyup();
		 if(!loginAccountFlag){
			 $("#errorSpan").html("请输入正确用户帐号");
			 return;
		 }
		 if(!loginPasswordFlag){
			 $("#errorSpan").html("请输入以字母开头，长度在6-18之间的密码");
			 return;
		 }
		 if(!loginCodeFlag){
			 $("#errorSpan").html("验证码错误");
			 return;
		 }
	 }
	 if(type == "reg"){
		 nicknameOnblur();
	 	 emailOnblur();
	 	 phoneOnblur();
	 	 passwordOnblur();
	 	 confirm_passwordOnblur();
	 	 codeOnblur();
		 if(!nicknameFlag){
			 $("#codeSpan").html("昵称不正确");
			 return;
		 }
		 if(!emailFlag){
			 $("#codeSpan").html("邮箱不正确");
			 return;
		 }
		 if(!phoneFlag){
			 $("#codeSpan").html("手机号码不正确");
			 return;
		 }
		 if(!passwordFlag){
			 $("#codeSpan").html("密码格式不正确");
			 return;
		 }
		 if(!confirm_passwordFlag){
			 $("#codeSpan").html("两次密码不一致");
			 return;
		 }
		 if(!codeFlag){
			 $("#codeSpan").html("验证码错误");
			 return;
		 }
		 
	 }
	 if(type == "find"){
		 phoneOrEmailOnblur();
		 find_codeOnblur();
		 newPasswordOnblur();
		 newConfirm_passwordOnblur();
		 if(!phoneOrEmailFlag){
			 $("#find-codeSpan").html("号码输入不正确");
			 return;
		 }
		 if(!newPasswordFlag){
			 $("#find-codeSpan").html("密码格式不正确");
			 return;
		 }
		 if(!newConfirm_passwordFlag){
			 $("#find-codeSpan").html("两次密码不一致");
			 return;
		 }
		 if(!findCodeFlag){
			 $("#find-codeSpan").html("验证码输入不正确");
			 return;
		 }
	 }
	  $.post(
		  url,
		  $("#"+form_id).serialize(),
	  	  function (data) //回传函数
	  	  {
//			  alert(data);
	    	  var resultJson='';
	    	  eval('resultJson=' + data + ';');
	    	  if(resultJson.statusCode == 200){
	    		  if( type == "find" || type == "reg" ){
	    			  
    			   	  $('#login_register_forget_modal').modal("show");
    			   	  $('#register-tab').removeClass('active');
    			   	  $('#forget-tab').removeClass('active');
    			   	  $('#login-tab').addClass('active');
    			   	  $("#errorSpan").html(resultJson.message);
	    		  }else{
	    			  history.go(0);
	    		  }
	    	  }else{
	    		  if( type == "find" ){
	    			  $("#find-codeSpan").html(resultJson.message);
	    		  }else if( type == "reg" ){
	    			  $("#codeSpan").html(resultJson.message);
	    		  }else{
	    			  $("#errorSpan").html(resultJson.message);
	    		  }
	    	  }
	  	  }
     );
}

function goods_search(){
	$("#search_form").submit();
}

	//登录正则匹配-----------------------------
	var loginAccountFlag = false;
	var loginPasswordFlag = false;
	var loginCodeFlag = false;
	//帐号
	function loginAccountOnblur(){
		var account= document.getElementById("loginAccount").value;  
		var patten= new RegExp(/^(1[3|4|5|7|8][0-9]\d{8})|([0-9|A-z|_]{1,17}[@][0-9|A-z]{1,3}.(com))$/);
		if(patten.test(account))
		{
			loginAccountFlag = true;
			$("#errorSpan").html("");
		}else{ 
			loginAccountFlag = false;
			$("#errorSpan").html("请输入正确用户帐号");
		}
	}
	//密码
	function loginPasswordOnblur(){
		var password = document.getElementById("loginPassword").value;  
		var patten= new RegExp(/^[A-z]\w{5,17}$/);
		if(patten.test(password))
		{
			loginPasswordFlag = true;
			$("#errorSpan").html("");
		}else{ 
			loginPasswordFlag = false;
			$("#errorSpan").html("请输入以字母开头，长度在6-18之间的密码");
		}
	}
	//验证码
	
	function loginCodeOnkeyup(){
		$("#loginCodeFrame").attr("src","/CSHT/jsp/util/loginCode.jsp");
		var imageCode = $("#loginCodeFrame").contents().find("#loginCode").val().toLowerCase();
		var loginCode = document.getElementById("loginCode").value.toLowerCase();
		if(loginCode.length == 4 ){
			if(loginCode == imageCode){
				loginCodeFlag = true;
				$("#loginCodeWRONG").addClass("hidden");
				$("#loginCodeOK").removeClass("hidden");
			}else{
				loginCodeFlag = false;
				$("#loginCodeOK").addClass("hidden");
				$("#loginCodeWRONG").removeClass("hidden");
			}
		}else if(loginCode.length > 4){
			loginCodeFlag = false;
			$("#loginCodeOK").addClass("hidden");
			$("#loginCodeWRONG").removeClass("hidden");
		}
		keyLogin();
	}
	
	//回车键登录
	function keyLogin(){
		if (event.keyCode == 13)  //回车键的键值为13
			document.getElementById("loginBtn").click(); //调用登录按钮的登录事件
	} 
	
	
	//注册正则匹配--------------------------------------------
	var nicknameFlag = false;
	var emailFlag = false;
	var phoneFlag = false;
	var passwordFlag = false;
	var confirm_passwordFlag = false;
	var codeFlag = false;
	//昵称
	function nicknameOnblur(){
		var nickname = document.getElementById("nickname").value;  
		var patten= new RegExp(/^([\u4e00-\u9fa5|\w]{2,16})$/);
		if(patten.test(nickname))
		{
			$("#nicknameSpan").html("");
			reg_ajax("nickname",nickname);
			
		}else{ 
			nicknameFlag = false;
			$("#nicknameSpan").html("请输入2-8位由合法字符组成的昵称");
			$("#nicknameOK").addClass("hidden");
		}
	}
	
	//邮箱
	function emailOnblur(){
		var email = document.getElementById("email").value;  
		var patten= new RegExp(/^([0-9|A-z|_]{1,17}[@][0-9|A-z]{1,3}.(com))$/);
		if(patten.test(email))
		{
			$("#emailSpan").html("");
			reg_ajax("email",email);
			
		}else{ 
			emailFlag = false;
			$("#emailSpan").html("请输入正确格式的邮箱地址");
			$("#emailOK").addClass("hidden");
		}
	}
	//手机号码
	function phoneOnblur(){
		var phone = document.getElementById("phone").value;  
		var patten= new RegExp(/^(1[3|4|5|7|8][0-9]\d{8})$/);
		if(patten.test(phone))
		{
			$("#phoneSpan").html("");
			reg_ajax("phone",phone);
			
		}else{ 
			phoneFlag = false;
			$("#phoneSpan").html("请输入正确的手机号码");
			$("#phoneOK").addClass("hidden");
		}
	}
	//密码
	function passwordOnblur(){
		var password = document.getElementById("password").value;  
		var patten= new RegExp(/^[A-z]\w{5,17}$/);
		if(patten.test(password))
		{
			$("#passwordSpan").html("");
			passwordFlag = true;
			$("#passwordOK").removeClass("hidden");
		}else{ 
			passwordFlag = false;
			$("#passwordSpan").html("请输入以字母开头，长度在6-18之间的密码");
			$("#passwordOK").addClass("hidden");
		}
	}
	//密码
	function confirm_passwordOnblur(){
		var confirm_password = document.getElementById("confirm_password").value;  
		var password = document.getElementById("password").value; 
		if(confirm_password == password)
		{
			$("#confirm_passwordSpan").html("");
			confirm_passwordFlag = true;
			$("#confirm_passwordOK").removeClass("hidden");
		}else{ 
			confirm_passwordFlag = false;
			$("#confirm_passwordSpan").html("两次密码不一致");
			$("#confirm_passwordOK").addClass("hidden");
		}
	}
	//验证码
	function codeOnblur(){
		var code = document.getElementById("code").value;  
		var patten= new RegExp(/^\w{4}$/);
		if(patten.test(code))
		{
			$("#codeSpan").html("");
			codeFlag = true;
			$("#codeOK").removeClass("hidden");
		}else{ 
			codeFlag = false;
			$("#codeSpan").html("请输入收到的4位验证码");
			$("#codeOK").addClass("hidden");
		}
	}
	//注册信息ajax
	function reg_ajax(key,value){
		  $.post(
			  "/CSHT/user/InfoRepeat",
			  {
				  key:key,
				  value:value,
			  },
		  	  function (data) //回传函数
		  	  {
		    	  var resultJson='';
		    	  eval('resultJson=' + data + ';');
		    	  if(resultJson.statusCode == 200){
		    		  if(key == "nickname"){
		    			  	nicknameFlag = true;
			  				$("#nicknameOK").removeClass("hidden");
		    		  }
		    		  if(key == "email"){
		    			  	emailFlag = true;
			  				$("#emailOK").removeClass("hidden");
		    		  }
		    		  if(key == "phone"){
		    			  phoneFlag = true;
			  				$("#phoneOK").removeClass("hidden");
		    		  }
		    	  }else{
		    		  if(key == "nickname"){
		    			  	nicknameFlag = false;
							$("#nicknameSpan").html(resultJson.message);
							$("#nicknameOK").addClass("hidden");
		    		  }
		    		  if(key == "email"){
		    			  	emailFlag = false;
							$("#emailSpan").html(resultJson.message);
							$("#emailOK").addClass("hidden");
		    		  }
		    		  if(key == "phone"){
		    			  	phoneFlag = false;
							$("#phoneSpan").html(resultJson.message);
							$("#phoneOK").addClass("hidden");
		    		  }
		    	  }
		  	  }
	     );
	}
	
	//获取验证码
	function getCode() {
		 nicknameOnblur();
	 	 emailOnblur();
	 	 phoneOnblur();
	 	 passwordOnblur();
	 	 confirm_passwordOnblur();
		 if(!nicknameFlag){
			 $("#codeSpan").html("昵称不正确");
			 return;
		 }
		 if(!emailFlag){
			 $("#codeSpan").html("邮箱不正确");
			 return;
		 }
		 if(!phoneFlag){
			 $("#codeSpan").html("手机号码不正确");
			 return;
		 }
		 if(!passwordFlag){
			 $("#codeSpan").html("密码格式不正确");
			 return;
		 }
		 if(!confirm_passwordFlag){
			 $("#codeSpan").html("两次密码不一致");
			 return;
		 }
		$.post(
			  "/CSHT/code/getCode",
			  {
				  phone:document.getElementById("phone").value,
				  email:document.getElementById("email").value,
			  },
		  	  function (data) //回传函数
		  	  {
				  $("#codeSpan").html("验证码已发送成功，请在10分钟内输入验证码");
				  settime(document.getElementById("code-btn"));
		  	  }
	     );
	}
	//获取验证码300秒倒计时
	var countdown=300; 
	function settime(val) {
		if (countdown == 0) { 
			val.removeAttribute("disabled");    
			val.value="免费获取验证码"; 
			countdown = 300; 
		} else { 
			val.setAttribute("disabled", true); 
			val.value="重新发送(" + countdown + ")"; 
			countdown--; 
		} 
		setTimeout(function(){ 
			settime(val);
		},1000);
	} 

	//密码找回
	//验证码
	var phoneOrEmailFlag = false;
	var findCodeFlag = false;
	var newPasswordFlag = false;
	var newConfirm_passwordFlag = false;
	//帐号
	function phoneOrEmailOnblur(){
		var type = document.getElementById("find-type").value;
		if( type == "email" ){
			var phoneOrEmail= document.getElementById("phoneOrEmail").value;  
			var patten= new RegExp(/^([0-9|A-z|_]{1,17}[@][0-9|A-z]{1,3}.(com))$/);
			if(patten.test(phoneOrEmail))
			{
				$("#phoneOrEmailSpan").html("");
				phoneOrEmailFlag = true;
				$("#phoneOrEmailOK").removeClass("hidden");
			}else{ 
				phoneOrEmailFlag = false;
				$("#phoneOrEmailSpan").html("请输入正确的邮箱地址");
				$("#phoneOrEmailOK").addClass("hidden");
			}
		}
		if( type == "phone" ){
			var phoneOrEmail= document.getElementById("phoneOrEmail").value;  
			var patten= new RegExp(/^(1[3|4|5|7|8][0-9]\d{8})$/);
			if(patten.test(phoneOrEmail))
			{
				$("#phoneOrEmailSpan").html("");
				phoneOrEmailFlag = true;
				$("#phoneOrEmailOK").removeClass("hidden");
			}else{ 
				phoneOrEmailFlag = false;
				$("#phoneOrEmailSpan").html("请输入正确的手机号码");
				$("#phoneOrEmailOK").addClass("hidden");
			}
		}
	}
	//验证码
	function find_codeOnblur(){
		var findCode = document.getElementById("find-code").value;  
		var patten= new RegExp(/^\w{4}$/);
		if(patten.test(findCode))
		{
			$("#find-codeSpan").html("");
			findCodeFlag = true;
			$("#find-codeOK").removeClass("hidden");
		}else{ 
			findCodeFlag = false;
			$("#find-codeSpan").html("请输入收到的4位验证码");
			$("#find-codeOK").addClass("hidden");
		}
	}
	//密码
	function newPasswordOnblur(){
		var newPassword = document.getElementById("newPassword").value;  
		var patten= new RegExp(/^[A-z]\w{5,17}$/);
		if(patten.test(newPassword))
		{
			$("#newPasswordSpan").html("");
			newPasswordFlag = true;
			$("#newPasswordOK").removeClass("hidden");
		}else{ 
			newPasswordFlag = false;
			$("#newPasswordSpan").html("请输入以字母开头，长度在6-18之间的密码");
			$("#newPasswordOK").addClass("hidden");
		}
	}
	//密码
	function newConfirm_passwordOnblur(){
		var newConfirm_password = document.getElementById("newConfirm_password").value;  
		var newPassword = document.getElementById("newPassword").value; 
		if(newConfirm_password == newPassword)
		{
			$("#newConfirm_passwordSpan").html("");
			newConfirm_passwordFlag = true;
			$("#newConfirm_passwordOK").removeClass("hidden");
		}else{ 
			newConfirm_passwordFlag = false;
			$("#newConfirm_passwordSpan").html("两次密码不一致");
			$("#newConfirm_passwordOK").addClass("hidden");
		}
	}
	//获取验证码
	function find_getCode() {
		phoneOrEmailOnblur();
		newPasswordOnblur();
		newConfirm_passwordOnblur();
		 if(!phoneOrEmailFlag){
			 $("#find-codeSpan").html("号码输入不正确");
			 return;
		 }
		 if(!newPasswordFlag){
			 $("#find-codeSpan").html("密码输入不正确");
			 return;
		 }
		 if(!newConfirm_passwordFlag){
			 $("#find-codeSpan").html("两次输入密码不一致");
			 return;
		 }
		$.post(
			  "/CSHT/code/getFindCode",
			  {
				  type:document.getElementById("find-type").value,
				  phoneOrEmail:document.getElementById("phoneOrEmail").value,
			  },
		  	  function (data) //回传函数
		  	  {
				  var resultJson='';
		    	  eval('resultJson=' + data + ';');
		    	  if(resultJson.statusCode == 200){
		    		  $("#find-codeSpan").html("验证码已发送成功，请在10分钟内输入验证码");
				  	  settime(document.getElementById("find-code-btn"));
		    	  }else{
		    		  $("#find-codeSpan").html(resultJson.message);
		    	  }
				 
		  	  }
	     );
	}
	//登录验证码
	function changeVerifyCode(){  
	    document.getElementById("validateCodeImg").src="/CSHT/code/getVerifyCode?t="+Math.random();
	    refreshCodeIfrme();
	} 
	
	
	
	