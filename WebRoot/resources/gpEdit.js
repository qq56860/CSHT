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
      
      
 })  
 
 function form_ajax(url,form_id){
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
	    		  $('#login_register_forget_modal').modal("hide");
	    		  $('#login_div').hide();
	    		  $('#logined_div').show();
	    		  $('#logined_span').html(resultJson.nickname);
	    	  }else{
	    		  alert(resultJson.message);
	    	  }
	  	  }
    );
}
 
 
 