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

import cn.com.logic.PublishLogic;
import cn.com.service.CommonService;
import cn.com.util.ResultModel;


@Controller
@RequestMapping("publish")
public class PublishController {
	protected static Logger log = Logger.getLogger(PublishController.class);
	
	@Autowired
	private PublishLogic publishLogic;
	
	@RequestMapping(value = "/goods",produces= "text/plain;charset=UTF-8")
	public ModelAndView publish(HttpServletRequest request,HttpServletResponse response){
		log.info("publish/goods");
		return publishLogic.publish(request, response);
	}
	
	@RequestMapping(value = "/addPublish",produces= "text/plain;charset=UTF-8")
	public ModelAndView addPublish(HttpServletRequest request,HttpServletResponse response){
		log.info("publish/addPublish");
		return publishLogic.addPublish(request, response);
	}
	
	@RequestMapping(value = "/buy",produces= "text/plain;charset=UTF-8")
	public ModelAndView buy(HttpServletRequest request,HttpServletResponse response){
		log.info("publish/buy");
		return publishLogic.buy(request, response);
	}
	
	@RequestMapping(value = "/addBuy",produces= "text/plain;charset=UTF-8")
	public ModelAndView addBuy(HttpServletRequest request,HttpServletResponse response){
		log.info("publish/addBuy");
		return publishLogic.addBuy(request, response);
	}
	
	@RequestMapping(value = "/rule",produces= "text/plain;charset=UTF-8")
	public ModelAndView rule(HttpServletRequest request,HttpServletResponse response){
		log.info("publish/rule");
		return publishLogic.rule(request, response);
	}
	
	@RequestMapping(value = "/typeChange",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String typeChange(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/typeChange");
		
		ModelMap modelMap  = CommonService.autoLogin(request, response);
		if(modelMap.containsKey("logoutMsg")){
	    	return JSONObject.fromObject(ResultModel.responseFaild("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）")).toString();
	    }
		
		return publishLogic.typeChange(request, response);
	}
	
}
