
 function change_phone(){
	 $("#change-phoneNumber").removeClass("hidden");
	 $("#change-phone-validNumber").removeClass("hidden");
	 $("#phone-change").removeClass("hidden");
	 $("#phone-change-cancel").removeClass("hidden");
 }
 
 function change_phone_cancel(){
	 $("#change-phoneNumber").addClass("hidden");
	 $("#change-phone-validNumber").addClass("hidden");
	 $("#phone-change").addClass("hidden");
	 $("#phone-change-cancel").addClass("hidden");
 }
 
 function getChangePhoneCode(type){
	 if(type == "phone"){
		 var value = document.getElementById("newPhone").value;
		 var patten= new RegExp(/^(1[3|4|5|7|8][0-9]\d{8})$/);
		 if(!patten.test(value)){
			 alert("请输入正确手机号");
			 return false;
		 }
	 }
	 if(type == "email"){
		 var value = document.getElementById("newEmail").value;
		 var patten= new RegExp(/^([0-9|A-z|_]{1,17}[@][0-9|A-z]{1,3}.(com))$/);
		 if(!patten.test(value)){
			 alert("请输入正确邮箱地址");
			 return false;
		 }
	 }
	 $.post(
		  "/CSHT/code/getChangeCode",
		  {
			  type:type,
			  phone:document.getElementById("newPhone").value,
			  email:document.getElementById("newEmail").value,
		  },
	  	  function (data) //回传函数
	  	  {
			  var resultJson='';
	    	  eval('resultJson=' + data + ';');
	    	  if(resultJson.statusCode == 200){
	    		  if(type == "phone"){
	    			  infosettime(document.getElementById("phoneCodeBtn"),300);
	    		  }
	    		  if(type == "email"){
	    			  infosettime(document.getElementById("emailCodeBtn"),300);
	    		  }
			  }else{
				alert(resultJson.message);  
			  }
	  	  }
     );
 }
 
//获取验证码300秒倒计时
	function infosettime(val,time) {
		if (time == 0) { 
			val.removeAttribute("disabled");    
			val.value="获取验证码"; 
			time = 300; 
		} else { 
			val.setAttribute("disabled", true); 
			val.value="重新发送(" + time + ")"; 
		} 
		setTimeout(function(){ 
			settime(val,time--);
		},1000);
	} 
 
	function change_email(){
		 $("#change-emailNumber").removeClass("hidden");
		 $("#change-email-validNumber").removeClass("hidden");
		 $("#email-change").removeClass("hidden");
		 $("#email-change-cancel").removeClass("hidden");
	 }
	
	function change_email_cancel(){
		 $("#change-emailNumber").addClass("hidden");
		 $("#change-email-validNumber").addClass("hidden");
		 $("#email-change").addClass("hidden");
		 $("#email-change-cancel").addClass("hidden");
	 }
	
	//修改密码
	function changePassword(){
		 $("#confirmChangePassword").removeClass("hidden");
		 $("#changePassword-cancel").removeClass("hidden");
		 $("#change-passwordDiv").removeClass("hidden");
	 }
	function changePassword_cancel(){
		 $("#confirmChangePassword").addClass("hidden");
		 $("#changePassword-cancel").addClass("hidden");
		 $("#change-passwordDiv").addClass("hidden");
	 }
	
	var oldPasswordFlag = false;
	function oldPasswordOnblur(){
		var oldPassword= document.getElementById("oldPassword").value;  
		var patten= new RegExp(/^[A-z]\w{5,17}$/);
		if(patten.test(oldPassword)){
			oldPasswordFlag = true;
		}
	}
	
	var newPasswordFlag = false;
	function newPasswordOnblur(){
		var newPassword= document.getElementById("newPassword").value;  
		var patten= new RegExp(/^[A-z]\w{5,17}$/);
		if(patten.test(newPassword)){
			newPasswordFlag = true;
		}
	}
	
	var confirmNewPasswordFlag = false;
	function confirmNewPasswordOnblur(){
		var confirmNewPassword= document.getElementById("confirmNewPassword").value;  
		var newPassword= document.getElementById("newPassword").value;
		if(confirmNewPassword == confirmNewPassword){
			confirmNewPasswordFlag = true;
		}
	}
	
	function passwordFomrSubmit(){
		oldPasswordOnblur();
		newPasswordOnblur();
		confirmNewPasswordOnblur();
		if(!oldPasswordFlag){
			alert("原密码输入不正确");
			return false;
		}
		if(!newPasswordFlag){
			alert("新密码格式不正确");
			return false;
		}
		if(!confirmNewPasswordFlag){
			alert("两次密码不一致");
			return false;
		}
		document.getElementById("changePasswordForm").submit();
	}
	
	
	var userPicFlag = false;
	$(function() {
	    $("#img").on("change",".filepath",function() {
	        var srcs = getObjectURL(this.files[0]);
	        var imgsrc = $(this).next().next().attr("src");
	        
	    	//判断文件类型
	    	var fileName = $(this).val();
	    	var suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
	    	var patten= new RegExp(/^(.PNG)|(.JPG)|(.JEPG)|(.BMP)$/);
	    	if(!patten.test(suffix)){
	    		alert("头像图片格式支持：.PNG，.JPG，.JEPG，.BMP");
	    		userPicFlag = false;
	    		return false;
	    	}
	    	//图片大小限制
	    	var filePic = this.files[0];
	    	if(filePic.size > 1024*1024*2 ){
	    		alert("头像图片大小：不超过2M");
	    		userPicFlag = false;
	    		return false;
	    	}
	        
	        $(this).next().show();
	        $(this).next().next().hide();   //this指的是input
	        $(this).next().next().next().attr("src",srcs);
	        
	        
	        $(".userClosePic").on("click",function() {
	        	$(this).hide();
	        	$(this).next().show();   //this指的是input
	        	$(this).next().next().attr("src","");
	        	userPicFlag = false;
	        });
	        
	        userPicFlag = true;
	    });
	});

	function getObjectURL(file) {
	    var url = null;
	    if (window.createObjectURL != undefined) {
	        url = window.createObjectURL(file);
	    } else if (window.URL != undefined) {
	        url = window.URL.createObjectURL(file);
	    } else if (window.webkitURL != undefined) {
	        url = window.webkitURL.createObjectURL(file);
	    }
	    return url;
	};	
	
	
	//修改头像
	function changePic(){
		 $("#confirmChangePic").removeClass("hidden");
		 $("#changePic-cancel").removeClass("hidden");
		 $("#img").removeClass("hidden");
	 }
	function changePic_cancel(){
		 $("#confirmChangePic").addClass("hidden");
		 $("#changePic-cancel").addClass("hidden");
		 $("#img").addClass("hidden");
	 }
	
	function picFormSubmit(){
		if(!userPicFlag){
			alert("换头像怎么也得传一张图片吧！！！");
			return false;
		}
		document.getElementById("changePicForm").submit();
	}
	
	