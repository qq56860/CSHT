
$(function(){
	$('.collapse').on('shown.bs.collapse', function () {
		autoHeight();
	});
	
	$('.collapse').on('hidden.bs.collapse', function () {
		autoHeight();
	});
	
});


//iframe 自适应高度
function changeFrameHeight(iframeName){
	var iframe= document.getElementById(iframeName); 
  var height = iframe.contentWindow.document.documentElement.scrollHeight;  
  parent.scrollTo(0,0);
  
  iframe.height = height; 
 
}

function autoHeight(){ 
	var doc = document, 
	p = window; 
	while(p = p.parent){ 
		var frames = p.frames, 
		frame, 
		i = 0; 
		while(frame = frames[i++]){ 
			if(frame.document == doc){ 
				frame.frameElement.style.height = doc.body.scrollHeight + 'px'; // 这里一定要注意 火狐必须要加'px‘ 否则火狐无效 
				doc = p.document; 
				break; 
			} 
		} 
		if(p == top){ 
			break; 
		} 
	} 
} 








