 
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
	 }else{
		 alert("error");
	 }
	 
} 