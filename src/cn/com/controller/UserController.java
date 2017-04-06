package cn.com.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.logic.UserLogic;

@Controller
@RequestMapping("user")
public class UserController {
	protected static Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserLogic userlogic;
	
	
	
	@RequestMapping(value = "/login",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String getSuggest(HttpServletRequest request,HttpServletResponse response){
		log.info("login");
		return userlogic.login(request, response);
	}
	
	@RequestMapping(value = "/reg",produces= "text/plain;charset=UTF-8")
	public String reg(HttpServletRequest request,HttpServletResponse response){
		log.info("reg");
		return userlogic.reg(request, response);
	}
	
	@RequestMapping(value = "/logOut",produces= "text/plain;charset=UTF-8")
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		log.info("logOut");
		return userlogic.logOut(request, response);
	}
	
	
}
