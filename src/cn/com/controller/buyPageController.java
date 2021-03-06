package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.HomePageLogic;
import cn.com.logic.BuyPageLogic;



@Controller
@RequestMapping("")
public class buyPageController {
	protected static Logger log = Logger.getLogger(buyPageController.class);
	
	@Autowired
	private BuyPageLogic buyPageLogic;
	
	@RequestMapping(value = "/buyPage",produces= "text/plain;charset=UTF-8")
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		log.info("buyPage");
		return buyPageLogic.buyPage(request, response);
	}
	
	
	
}
