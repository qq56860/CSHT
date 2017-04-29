package cn.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.logic.CommentLogic;
import cn.com.logic.GoodsLogic;


@Controller
@RequestMapping("/comment")
public class CommentController {
	protected static Logger log = Logger.getLogger(CommentController.class);
	
	@Autowired
	private CommentLogic commentLogic;
	
	@RequestMapping(value = "/addComment",produces= "text/plain;charset=UTF-8")
	public ModelAndView addComment(HttpServletRequest request,HttpServletResponse response){
		log.info("comment/addComment");
		return commentLogic.addComment(request, response);
	}
	
	@RequestMapping(value = "/addReply",produces= "text/plain;charset=UTF-8")
	public ModelAndView addReply(HttpServletRequest request,HttpServletResponse response){
		log.info("comment/addReply");
		return commentLogic.addReply(request, response);
	}
	
}
