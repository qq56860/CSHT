package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.HomePageLogic;



@Controller
@RequestMapping("")
public class HomePageController {
	protected static Logger log = Logger.getLogger(HomePageController.class);
	
	@Autowired
	private HomePageLogic homePageLogic;
	
	@RequestMapping(value = "/homePage",produces= "text/plain;charset=UTF-8")
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		log.info("homePage");
		return homePageLogic.homePage(request, response);
	}
	
	@RequestMapping(value = "/homePageList",produces= "text/plain;charset=UTF-8")
	public ModelAndView homePageList(HttpServletRequest request,HttpServletResponse response){
		log.info("homePageList");
		return homePageLogic.homePageList(request, response);
	}
	
}
