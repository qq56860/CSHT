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
      
  	$("#iframe_login").click(function(){
      	  
  		parent.$("#login",parent.document).click();
    }); 
  	
  	$("#iframe_register").click(function(){
  		parent.$("#register",parent.document).click();
    }); 
      
 });  
 
 function form_ajax(url,form_id,returnUrl){
	 //alert($("#login_form").serialize());
	  $.post(
		  url,
		  $("#"+form_id).serialize(),
	  	  function (data) //回传函数
	  	  {
//			  alert(data);
	    	  var resultJson='';
	    	  eval('resultJson=' + data + ';');
	    	  if(resultJson.statusCode == 200){
	    		  $("#login_register_forget_modal").modal("hide");
	    		 /* $("#user-info-tab").removeClass('active');
	    		  $("#user-info-tab2").addClass('active');*/
	    		  $("#login_iframe").attr('src',returnUrl);
	    	  }else{
	    		  alert(resultJson.message);
	    	  }
	  	  }
    );
}
 
 function iFrameHeight() {   
	 var ifm= window.getElementById("login_iframe");   
	 var subWeb = window.frames ? window.frames["login_iframe"].document : ifm.contentDocument;   
	 if(ifm != null && subWeb != null) {
	    ifm.height = subWeb.body.scrollHeight;
	    ifm.width = subWeb.body.scrollWidth;
	 }   
 }   
 