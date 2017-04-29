//求购发布
var goodsNameFlag = false;
var goodsContentFlag = false;
var tradePlaceFlag = false;
var priceFlag = false;
var contactFlag = false;
function buySubmit(){
	goodsNameKeyup();
	goodsContentKeyup();
	tradePlaceKeyup();
	priceKeyup();
	contactKeyup();
	if(!goodsNameFlag){
		$("#errorSpan").html("请输入正确物品名称");
		return;
	}
	if(!goodsContentFlag){
		$("#errorSpan").html("请输入正确物品描述");
		return;
	}
	if(!tradePlaceFlag){
		$("#errorSpan").html("请输入正确交易地点");
		return;
	}
	if(!priceFlag){
		$("#errorSpan").html("请输入正确价格");
		return;
	}
	if(!contactFlag){
		$("#errorSpan").html("请输入正确联系方式");
		return;
	}
	if(document.getElementById("agreeRule").value != "on"){
		$("#errorSpan").html("发布物品需同意《物品发布规则》");
		return;
	}
	document.getElementById("buyPublishForm").submit();
}

function goodsNameKeyup(){
	var goodsName= document.getElementById("goodsName").value;  
	var patten= new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9#.%*!@\[\]{};:'",！￥【】｛｝；：‘’“”，。\-\+]{1,32}$/);
	if(patten.test(goodsName))
	{
		goodsNameFlag = true;
		$("#goodsNameSpan").html("");
	}else{ 
		goodsNameFlag = false;
		$("#goodsNameSpan").html("请输入正确物品名称");
	};
};

function goodsContentKeyup(){
	var goodsContent= document.getElementById("goodsContent").value;  
	var patten= new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9#.%*!@\[\]{};:'",！￥【】｛｝；：‘’“”，。\-\+]{1,200}$/);
	if(patten.test(goodsContent))
	{
		goodsContentFlag = true;
		$("#goodsContentSpan").html("");
	}else{ 
		goodsContentFlag = false;
		$("#goodsContentSpan").html("请输入正确物品描述");
	};
};

function tradePlaceKeyup(){
	var tradePlace= document.getElementById("tradePlace").value;  
	var patten= new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9#.%*!@\[\]{};:'",！￥【】｛｝；：‘’“”，。\-\+]{1,100}$/);
	if(patten.test(tradePlace))
	{
		tradePlaceFlag = true;
		$("#tradePlaceSpan").html("");
	}else{ 
		tradePlaceFlag = false;
		$("#tradePlaceSpan").html("请输入正确交易地点");
	};
};

function priceKeyup(){
	var price= document.getElementById("price").value;  
	var patten= new RegExp(/^[0-9.]{1,10}$/);
	if(patten.test(price))
	{
		priceFlag = true;
		$("#priceSpan").html("");
	}else{ 
		priceFlag = false;
		$("#priceSpan").html("请输入正确价格");
	};
};

function contactKeyup(){
	var contact= document.getElementById("contact").value;  
	var patten= new RegExp(/^[\u4e00-\u9fa5_a-zA-Z0-9#.%*!@\[\]{};:'",！￥【】｛｝；：‘’“”，。\-\+]{1,100}$/);
	if(patten.test(contact))
	{
		contactFlag = true;
		$("#contactSpan").html("");
	}else{ 
		contactFlag = false;
		$("#contactSpan").html("请输入正确联系方式");
	};
};

//发布物品
function typeChange(){
	var type= document.getElementById("type").value;
	$.post(
		  "/CSHT/publish/typeChange",
		  {
			  type:type,
		  },
	  	  function (data) //回传函数
	  	  {
			  var resultJson = eval("("+data+")");
			  document.getElementById("sub").options.length=0;
			  for(var i = 0; i < resultJson.length; i++) { //循环后台传过来的Json数组  
				     var data = resultJson[i];
//				     alert(data.id);
//				     alert(data.sub);
				     document.getElementById("sub").options.add(new Option(data.sub,data.id));
			  }
	  	  }
     );
};

function picChange(){
	
}

function publishSubmit(){
	goodsNameKeyup();
	goodsContentKeyup();
	tradePlaceKeyup();
	priceKeyup();
	contactKeyup();
	if(!picFlag){
		$("#errorSpan").html("怎么着也得上传一张图片让别人看看啊！");
		return;
	}
	if(!goodsNameFlag){
		$("#errorSpan").html("请输入正确物品名称");
		return;
	}
	if(!goodsContentFlag){
		$("#errorSpan").html("请输入正确物品描述");
		return;
	}
	if(!tradePlaceFlag){
		$("#errorSpan").html("请输入正确交易地点");
		return;
	}
	if(!priceFlag){
		$("#errorSpan").html("请输入正确价格");
		return;
	}
	if(!contactFlag){
		$("#errorSpan").html("请输入正确联系方式");
		return;
	}
	if(document.getElementById("agreeRule").value != "on"){
		$("#errorSpan").html("发布物品需同意《物品发布规则》");
		return;
	}
	document.getElementById("publishForm").submit();
}

//预览图片
var picFlag = false;

$(function() {
    $("#img").on("change",".filepath",function() {
        var length = $('.imgbox').length;
//        alert(length);
        var srcs = getObjectURL(this.files[0]);   //获取路径
//        alert(srcs);
        var imgsrc = $(this).next().next().attr("src");
//        alert(imgsrc);
        
    	//判断文件类型
    	var fileName = $(this).val();
//    	alert(fileName);
    	var suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
//    	alert(suffix);
    	var patten= new RegExp(/^(.PNG)|(.JPG)|(.JEPG)|(.BMP)$/);
    	if(!patten.test(suffix)){
    		$("#picSpan").html("图片文件格式不正确");
    		return false;
    	}
    	$("#picSpan").html("");
    	//图片大小限制
    	var filePic = this.files[0];
//    	alert("图片大小"+filePic.size);
    	if(filePic.size > 1024*1024*2 ){
    		$("#picSpan").html("图片文件过大");
    		return false;
    	}
        
        $(this).parent().parent().attr("class","imgbox");
        $(this).attr("name","pic"+length);
        $(this).next().show();
        $(this).next().next().hide();   //this指的是input
        $(this).next().next().next().attr("src",srcs);
        
        var defaultImg='<div class="imgboxDefault">'+
				        '<div class="imgnum">'+
				        '<input type="file" class="filepath"/>'+
				        '<span class="closePic">X</span>'+
				        '<img src="'+imgsrc+'" class="imgBG" />'+
				        '<img src="" class="imgPic" />'+
				        '</div>'+
				        '</div>';
        
        $(".closePic").on("click",function() {
        	if($('.imgbox').length==4){
        		$(this).parent().parent().after(defaultImg);
        	}
        	$(this).parent().parent().remove();
        });
        
        if(length < 3){
        	$(this).parent().parent().after(defaultImg);
        }
        
        if($('.imgbox').length <= 0){
        	picFlag = false;
        }else{
        	picFlag = true;
        };
//        var htmlImg='<div class="imgbox">'+
//                '<div class="imgnum">'+
//                '<input type="file" class="filepath" name="pic'+length+'" />'+
//                '<span class="closePic">X</span>'+
//                '<img src="'+imgsrc+'" class="imgBG" />'+
//                '<img src="'+srcs+'" class="imgPic" />'+
//                '</div>'+
//                '</div>';
//        
//        $(this).parent().parent().before(htmlImg);
//        $(this).parent().parent().prev().find(".filepath").value = $(this).value;
//        $(this).val('');    //必须制空
//        $(this).parent().parent().prev().find(".imgBG").hide();   //this指的是input
//        $(this).parent().parent().prev().find('.closePic').show();
//        
//        $(".closePic").on("click",function() {
//            $(this).hide();     //this指的是span
//            $(this).nextAll(".imgPic").hide();
//            $(this).nextAll(".imgBG").show();
//            
//            if($('.imgbox').length==4){
//            	 var defaultImg='<div class="imgboxDefault">'+
//                 '<div class="imgnum">'+
//                 '<input type="file" class="filepath"/>'+
//                 '<span class="closePic">X</span>'+
//                 '<img src="'+imgsrc+'" class="imgBG" />'+
//                 '<img src="" class="imgPic" />'+
//                 '</div>'+
//                 '</div>';
//            	 $(this).parent().parent().before(htmlImg);
//            }
//            $(this).parent().parent().remove();
//        });
//        
//        if(length >= 3){
//        	$(this).parent().parent().remove();
//        }
        
    });
});

function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) {
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) {
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) {
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
};
