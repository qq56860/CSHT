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
//登录注册ajax
function form_ajax(url,form_id,returnUrl){
	 //alert($("#login_form").serialize());
	var path = window.location.pathname;
	  $.post(
		  url,
		  $("#"+form_id).serialize(),
	  	  function (data) //回传函数
	  	  {
//			  alert(data);
	    	  var resultJson='';
	    	  eval('resultJson=' + data + ';');
	    	  if(resultJson.statusCode == 200){
	    		  window.location.reload();  
	    	  }else{
	    		  alert(resultJson.message);
	    	  }
	  	  }
   );
}

function goods_search(){
	$("#search_form").submit();
}

function notLoginAlert(){
	alert('您尚未登录');
}

function userLogin(){
	$('#login_register_forget_modal').modal("show");
 	  $('#register-tab').removeClass('active');
 	  $('#forget-tab').removeClass('active');
 	  $('#login-tab').addClass('active');
}
