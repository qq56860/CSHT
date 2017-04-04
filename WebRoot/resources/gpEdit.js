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
 
 function list_search(search_way,search_content){
	 if(search_way == "search" ){ 
		 $("#list_iframe").contents().find("#search_search").val( $("#header_search").val() );
		 $("#list_iframe").contents().find("#type_search").val( '' );
		 $("#list_iframe").contents().find("#sub_search").val( '' );
		 $("#list_iframe").contents().find("#time_collection_search").val( '' );
		 $("#list_iframe").contents().find("#search_form").submit();
	 }else if(search_way == "type"){
		 $("#list_iframe").contents().find("#type_search").val( search_content );
		 $("#list_iframe").contents().find("#search_search").val( '' );
		 $("#list_iframe").contents().find("#sub_search").val( '' );
		 $("#list_iframe").contents().find("#time_collection_search").val( '' );
		 $("#list_iframe").contents().find("#search_form").submit();
//		 $("#type_search").val( search_content );		 
//		 $("#search_search").val('');
//		 $("#sub_search").val('');
	 }else if(search_way == "sub"){
		 $("#list_iframe").contents().find("#sub_search").val( search_content );
		 $("#list_iframe").contents().find("#search_search").val( '' );
		 $("#list_iframe").contents().find("#type_search").val( '' );
		 $("#list_iframe").contents().find("#time_collection_search").val( '' );
		 $("#list_iframe").contents().find("#search_form").submit();
	 }else if(search_way == "time_collection"){
		 $("#time_collection_search").val( $("#time_collection_select option:selected").val() );
		 $("#search_form").submit();
	 }else if(search_way == "page"){
		 $("#page_search").val( search_content );
		 $("#search_form").submit();
	 }else{
		 alert("error");
	 }
	 
} 
 
//iframe 自适应高度
function changeFrameHeight(){
	var iframe= document.getElementById("list_iframe"); 
    var height = iframe.contentWindow.document.documentElement.scrollHeight;  
    parent.scrollTo(0,0);
    iframe.height = height; 
   
}
window.onresize=function(){  
     changeFrameHeight();  
}
 