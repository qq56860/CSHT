function addReply(replyid,commentCollapse){
	
	parent.$("#replayid",parent.document).val(replyid);
	parent.$("#"+commentCollapse,parent.document).click();
}

function addComment( collapseid ,iframeid ){
	autoHeight();
	$("#"+collapseid).collapse('toggle');
	autoHeight();
}


