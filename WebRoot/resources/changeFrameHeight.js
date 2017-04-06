
//iframe 自适应高度
function changeFrameHeight(iframeName){
	var iframe= document.getElementById(iframeName); 
    var height = iframe.contentWindow.document.documentElement.scrollHeight;  
    parent.scrollTo(0,0);
    iframe.height = height; 
   
}
 