
 function list_search(search_way,search_content){
	 if(search_way == "time_collection"){
		 $("#time_collection_search").val( $("#time_collection_select option:selected").val() );
		 $("#search_form").submit();
	 }else if(search_way == "page"){
		 $("#page_search").val( search_content );
		 $("#search_form").submit();
	 }else{
		 alert("error");
	 }
	 
} 
 
 function list_detail(goodsid){
	 window.parent.location = "/CSHT/goods/detail?id="+goodsid;
} 
 
 function notLogin(){
	 window.top.window.userLogin();
 }
 
 
 function addOrRemoveCollection(good){
	 $.post(
			  "/CSHT/goods/addOrRemoveCollection",
			  {
				  goodsid:good,
			  },
		  	  function (data) //回传函数
		  	  {
		    	  var resultJson='';
		    	  eval('resultJson=' + data + ';');
		    	  if(resultJson.statusCode == 200){
		    		  if(resultJson.type == "remove"){
		    			  $("#iscollection"+good).html("+1");
			    		  $("#collectionNum"+good).html(resultJson.message);
			    		  $("#heart"+good).removeClass("glyphicon-heart");
			    		  $("#heart"+good).addClass("glyphicon-heart-empty");
		    		  }else if(resultJson.type == "add"){
		    			  $("#iscollection"+good).html("(取消收藏)");
			    		  $("#collectionNum"+good).html(resultJson.message);
			    		  $("#heart"+good).removeClass("glyphicon-heart-empty");
			    		  $("#heart"+good).addClass("glyphicon-heart");
		    		  }
		    		 
		    	  }else{
		    		  alert(resultJson.message);
		    	  }
		  	  }
	  );
 }

 
 $(function(){
	$(".add-collect").hover(function(){
		$(this).find(".add-oneCollect").show();
    },function(){
    	$(this).find(".add-oneCollect").hide();
    }); 
 });