
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
 