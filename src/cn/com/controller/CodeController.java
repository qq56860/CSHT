package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.CodeLogic;
import cn.com.logic.UserLogic;
@Controller
@RequestMapping("code")
public class CodeController {
	protected static Logger log = Logger.getLogger(CodeController.class);
	
	@Autowired
	private CodeLogic codeLogic;
	
	@RequestMapping(value = "/getCode",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCode(HttpServletRequest request,HttpServletResponse response){
		log.info("code/getCode");
		return codeLogic.getCode(request, response);
	}
	@RequestMapping(value = "/getFindCode",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String getFindCode(HttpServletRequest request,HttpServletResponse response){
		log.info("code/getFindCode");
		return codeLogic.getFindCode(request, response);
	}
	
	@RequestMapping(value = "/getChangeCode",produces= "text/plain;charset=UTF-8")
	@ResponseBody
	public String getChangeCode(HttpServletRequest request,HttpServletResponse response){
		log.info("code/getChangeCode");
		return codeLogic.getChangeCode(request, response);
	}
	
	
	@RequestMapping(value = "/getVerifyCode",produces= "text/plain;charset=UTF-8")
	public String getVerifyCode(HttpServletRequest request,HttpServletResponse response){
		log.info("code/getVerifyCode");
		return codeLogic.getVerifyCode(request, response);
	}
	
}
