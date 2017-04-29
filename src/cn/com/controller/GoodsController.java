package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.GoodsLogic;
import cn.com.service.CommonService;
import cn.com.util.ResultModel;


@Controller
@RequestMapping("goods")
public class GoodsController {
	protected static Logger log = Logger.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsLogic goodsLogic;
	
	@RequestMapping(value = "/detail",produces= "text/plain;charset=UTF-8")
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/detail");
		return goodsLogic.detail(request, response);
	}
	
	@RequestMapping(value = "/publish/commentList",produces= "text/plain;charset=UTF-8")
	public ModelAndView publishComment(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/publish/comment");
		return goodsLogic.publishComment(request, response);
	}
	
	@RequestMapping(value = "/publish/replyList",produces= "text/plain;charset=UTF-8")
	public ModelAndView publishReply(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/publish/reply");
		return goodsLogic.publishReply(request, response);
	}
	
	@RequestMapping(value = "/buy/commentList",produces= "text/plain;charset=UTF-8")
	public ModelAndView buyComment(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/buy/comment");
		return goodsLogic.buyComment(request, response);
	}
	
	@RequestMapping(value = "/addOrRemoveCollection",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String addOrRemoveCollection(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/addOrRemoveCollection");
		
		ModelMap modelMap  = CommonService.autoLogin(request, response);
		if(modelMap.containsKey("logoutMsg")){
	    	return JSONObject.fromObject(ResultModel.responseFaild("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）")).toString();
	    }
		
		return goodsLogic.addOrRemoveCollection(request, response);
	}
	
	@RequestMapping(value = "/hotSearch",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String hotSearch(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/hotSearch");
		
		ModelMap modelMap  = CommonService.autoLogin(request, response);
		if(modelMap.containsKey("logoutMsg")){
	    	return JSONObject.fromObject(ResultModel.responseFaild("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）")).toString();
	    }
		
		return goodsLogic.hotSearch(request, response);
	}
	
	@RequestMapping(value = "/recordInfo",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String recordInfo(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/recordInfo");
		
		ModelMap modelMap  = CommonService.autoLogin(request, response);
		if(modelMap.containsKey("logoutMsg")){
	    	return JSONObject.fromObject(ResultModel.responseFaild("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）")).toString();
	    }
		
		return goodsLogic.recordInfo(request, response);
	}
	
	
	
}
