function hotsearch(){
	//xmlHttp
	var xmlHttp;
	//首先要获得用户输入
	var content = document.getElementById("header_search");
	if(content.value=="") {
		//如果元素是空，也可清空一下
		clearContent();
		return;
	}
//	alert(content.value);
	//然后给服务器发送数据
	//需要一个xmlHttp对象
	xmlHttp=createXmlHttp();
//	alert(xmlHttp);
	//var url="search?keyword="+escape(content.value);
	var url="/CSHT/goods/hotSearch?keyword="+content.value;
	//true表示在调用send()方法后会继续执行
	xmlHttp.open("GET",url,true);
	//xmlHttp绑定的回调方法，这个回调方法会在xmlHttp状态改变时被调用
	//xmlhttp状态0-4，我们只关心4.完成之后再回调才有意义
	xmlHttp.onreadystatechange=callback;
	xmlHttp.send(null);
	
	
	
	function callback() {
		
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				//获得数据
				var result=xmlHttp.responseText;
				//解析数据
				var json=eval("("+result+")");
				//获得数据后就可以动态的展示数据到输入框的下面
//				alert(json);
				setContent(json);
			}
		}
	}
	
	
	
}

function createXmlHttp() {
	var xmlHttp;
	if(window.XMLHttpRequest) {
		xmlHttp=new XMLHttpRequest();
	}
	if(window.ActiveXObject) {
		xmlHttp=new ActiveXObject("MircoSoft.XMLHTTP");
		if(!xmlHttp) {
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
	}
	return xmlHttp;
}

//设置展示数据
function setContent(contents) {
	
	//设置数据前先清空
	clearContent();
	//设置显示位置
	setLocation();
	
	var size=contents.length;
	
	
	for(var i=0; i<size; i++) {
		
		var nextNode=contents[i].searchText;
		var tr=document.createElement("tr");
		var td=document.createElement("td");
		td.setAttribute("border", "0");
		td.setAttribute("bgcolor", "white");
		td.setAttribute("class", "input-lg");
		td.onmouseover=function() {
			this.setAttribute("bgcolor", "#CCCCCC");
		};
		td.onmouseout=function(){
			this.setAttribute("bgcolor", "white");
		};
		var text=document.createTextNode(nextNode);
		td.onclick=function(){
			$("#header_search").val(this.innerText);
			$("#search_form").submit();
		};
		
		td.appendChild(text);
		tr.appendChild(td);
		document.getElementById("content_table_body").appendChild(tr);

	}
	
}

//请空之前的数据
function clearContent(){
	var contentTableBody=document.getElementById("content_table_body");
	var size=contentTableBody.childNodes.length;
	for(var i=size-1; i>=0; i--){
		contentTableBody.removeChild(contentTableBody.childNodes[i]);
	}
	document.getElementById("popDiv").style.border="none";
}

//当输入框失去焦点时
function keywordblur() {
	clearContent();
}

function setLocation() {
	var content=document.getElementById("header_search");
	var width=content.offsetWidth;
	var left=content["offsetLeft"];
	var top=content["offsetTop"]+content.offsetHeight;
	var popDiv=document.getElementById("popDiv");
	popDiv.style.left=left+"px";
	popDiv.style.top=top+"px";
	popDiv.style.width=width+"px";
	document.getElementById("content_table").style.width=width+"px";
}


