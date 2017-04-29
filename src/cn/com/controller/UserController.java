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

import cn.com.logic.UserLogic;
import cn.com.service.CommonService;
import cn.com.util.ResultModel;
@Controller
@RequestMapping("user")
public class UserController {
	protected static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserLogic userlogic;
	
	
	
	@RequestMapping(value = "/login",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String getSuggest(HttpServletRequest request,HttpServletResponse response){
		log.info("user/login");
		return userlogic.login(request, response);
	}
	
	@RequestMapping(value = "/reg",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String reg(HttpServletRequest request,HttpServletResponse response){
		log.info("user/reg");
		return userlogic.reg(request, response);
	}
	
	@RequestMapping(value = "/logOut",produces= "text/plain;charset=UTF-8")
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		log.info("user/logOut");
		return userlogic.logOut(request, response);
	}
	
	@RequestMapping(value = "/info",produces= "text/plain;charset=UTF-8")
	public ModelAndView myInfo(HttpServletRequest request,HttpServletResponse response){
		log.info("user/myInfo");
		return userlogic.myInfo(request, response);
	}
	
	@RequestMapping(value = "/collection",produces= "text/plain;charset=UTF-8")
	public ModelAndView myCollection(HttpServletRequest request,HttpServletResponse response){
		log.info("user/myCollection");
		return userlogic.myCollection(request, response);
	}
	
	@RequestMapping(value = "/publish",produces= "text/plain;charset=UTF-8")
	public ModelAndView myPublish(HttpServletRequest request,HttpServletResponse response){
		log.info("user/myPublish");
		return userlogic.myPublish(request, response);
	}
	
	@RequestMapping(value = "/buy",produces= "text/plain;charset=UTF-8")
	public ModelAndView myBuy(HttpServletRequest request,HttpServletResponse response){
		log.info("user/myBuy");
		return userlogic.myBuy(request, response);
	}
	
	@RequestMapping(value = "/news",produces= "text/plain;charset=UTF-8")
	public ModelAndView myNews(HttpServletRequest request,HttpServletResponse response){
		log.info("user/myNews");
		return userlogic.myNews(request, response);
	}
	
	@RequestMapping(value = "/InfoRepeat",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String InfoRepeat(HttpServletRequest request,HttpServletResponse response){
		log.info("user/InfoRepeat");
		
		ModelMap modelMap  = CommonService.autoLogin(request, response);
		if(modelMap.containsKey("logoutMsg")){
	    	return JSONObject.fromObject(ResultModel.responseFaild("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）")).toString();
	    }
		
		return userlogic.InfoRepeat(request, response);
	}
	
	@RequestMapping(value = "/findPassword",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String findPassword(HttpServletRequest request,HttpServletResponse response){
		log.info("user/findPassword");
		
		return userlogic.findPassword(request, response);
	}
	
	@RequestMapping(value = "/changePhone",produces= "text/plain;charset=UTF-8")
	public ModelAndView changePhone(HttpServletRequest request,HttpServletResponse response){
		log.info("user/changePhone");
		return userlogic.changePhone(request, response);
	}
	
	@RequestMapping(value = "/changeEmail",produces= "text/plain;charset=UTF-8")
	public ModelAndView changeEmail(HttpServletRequest request,HttpServletResponse response){
		log.info("user/changeEmail");
		return userlogic.changeEmail(request, response);
	}
	
	@RequestMapping(value = "/changePassword",produces= "text/plain;charset=UTF-8")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response){
		log.info("user/changePassword");
		return userlogic.changePassword(request, response);
	}
	
	@RequestMapping(value = "/changePic",produces= "text/plain;charset=UTF-8")
	public ModelAndView changePic(HttpServletRequest request,HttpServletResponse response){
		log.info("user/changePic");
		return userlogic.changePic(request, response);
	}
	
	@RequestMapping(value = "/deleteCollection",produces= "text/plain;charset=UTF-8")
	public ModelAndView deleteCollection(HttpServletRequest request,HttpServletResponse response){
		log.info("user/deleteCollection");
		return userlogic.deleteCollection(request, response);
	}
	
	@RequestMapping(value = "/goodsIsBuyed",produces= "text/plain;charset=UTF-8")
	public ModelAndView goodsIsBuyed(HttpServletRequest request,HttpServletResponse response){
		log.info("user/goodsIsBuyed");
		return userlogic.goodsIsBuyed(request, response);
	}
	
	@RequestMapping(value = "/deletePublish",produces= "text/plain;charset=UTF-8")
	public ModelAndView deletePublish(HttpServletRequest request,HttpServletResponse response){
		log.info("user/deletePublish");
		return userlogic.deletePublish(request, response);
	}
	
	@RequestMapping(value = "/buyIsBuy",produces= "text/plain;charset=UTF-8")
	public ModelAndView buyIsBuy(HttpServletRequest request,HttpServletResponse response){
		log.info("user/buyIsBuy");
		return userlogic.buyIsBuy(request, response);
	}
	
	@RequestMapping(value = "/deleteBuy",produces= "text/plain;charset=UTF-8")
	public ModelAndView deleteBuy(HttpServletRequest request,HttpServletResponse response){
		log.info("user/deleteBuy");
		return userlogic.deleteBuy(request, response);
	}
	
}
