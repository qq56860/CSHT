package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.GoodsLogic;


@Controller
@RequestMapping("/goods")
public class GoodsController {
	protected static Logger log = Logger.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsLogic goodsLogic;
	
	@RequestMapping(value = "/detail",produces= "text/plain;charset=UTF-8")
	public ModelAndView homePage(HttpServletRequest request,HttpServletResponse response){
		log.info("goods/detail");
		return goodsLogic.detail(request, response);
	}
	
}
