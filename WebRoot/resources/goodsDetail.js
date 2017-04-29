
$(function (){ 	
	$("[data-toggle='popover']").popover();
	
	$("[data-toggle='popover']").on('shown.bs.popover', function () {
		recordInfo();
    });
	
});


function copytext() {
	var txt = document.getElementById("goodsURL");
	txt.select();
	document.execCommand("Copy");
} 

function recordInfo(){
	$.post(
		  "/CSHT/goods/recordInfo",
		  {
			  viewedId:$("#viewedId").val(),
			  goodsId:$("#goodsId").val(),
			  type:"publish",
		  },
	  	  function (data) //回传函数
	  	  {
			  var resultJson='';
	    	  eval('resultJson=' + data + ';');
	    	  if(resultJson.statusCode == 200){
	    		 
	    	  }else{
	    		  
	    	  }
			 
	  	  }
     );
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

