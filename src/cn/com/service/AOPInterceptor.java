package cn.com.service;

import java.net.URLDecoder;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.User;

@Component
@Aspect
public class AOPInterceptor {
	
	protected static Logger log = Logger.getLogger(AOPInterceptor.class);
	
	@Pointcut("execution(public org.springframework.web.servlet.ModelAndView cn.com.logic.*.*(..))")
	public void aspect(){
		
	}
	
	@Around("aspect()")
	public ModelAndView before(ProceedingJoinPoint joinPoint){
		ModelAndView model = new ModelAndView();
		
	    log.info("AOP before-----"+joinPoint);
	    Object[] args = joinPoint.getArgs();
//	    System.out.println(Arrays.toString(args));
	    
	    ModelMap modelMap  = CommonService.autoLogin((HttpServletRequest)args[0], (HttpServletResponse)args[1]);
		
	    if(modelMap.containsKey("logoutMsg")){
	    	log.info(modelMap.get("logoutMsg"));
	    	model.setViewName("redirect:/homePage");
	    	model.addAllObjects(modelMap);
	    	return model;
	    }
	    
	    try {
			model = (ModelAndView) joinPoint.proceed(args);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return model;
		}
	    return model;
    }  
}
