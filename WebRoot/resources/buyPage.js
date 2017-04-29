$(function (){ 	
	$("[data-toggle='popover']").popover();
	
	$("[data-toggle='popover']").on('shown.bs.popover', function () {
		recordInfo();
    });
	
});


function recordInfo(){
	$.post(
		  "/CSHT/goods/recordInfo",
		  {
			  viewedId:$("#viewedId").val(),
			  goodsId:$("#goodsId").val(),
			  type:"buy",
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


function buySearch(type,pageNum) {
	if(type == "key"){
		$("#page").val("");
	}else if(type == "page"){
		$("#page").val(pageNum);
	}
	$("#buySearchForm").submit();
} 


