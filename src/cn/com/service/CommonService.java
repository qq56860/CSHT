package cn.com.service;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.User;
import cn.com.mapper.UserMapper;
import cn.com.mapper.UserNewsMapper;
import cn.com.util.DateHelper;


@Component
public class CommonService {
	static Logger log=Logger.getLogger(CommonService.class);
	
	private static UserMapper userMapper;
	@Autowired(required = true)
	private void setUserMapper(UserMapper userMapper) {
		CommonService.userMapper = userMapper;
	}
	
	private static UserNewsMapper userNewsMapper;
	@Autowired(required = true)
	private void setUserNewsMapper(UserNewsMapper userNewsMapper) {
		CommonService.userNewsMapper = userNewsMapper;
	}
	
	//自动登录
	public static ModelMap autoLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		
		User autoUser = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		Subject subject = SecurityUtils.getSubject();
		if(autoUser == null ){
			if(subject.isRemembered()){
				String[] str = subject.getPrincipal().toString().split(",");
				String account = str[0];
				log.info("account-----"+account);
				String token = str[1];
				log.info("token-----"+token);
				User user = userMapper.selectByAccount(account);
				if(!user.getToken().equals(token)){//token不一致
					log.info("token不一致");
					subject.logout();
					modelMap.put("logoutMsg", URLEncoder.encode("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）"));
					return modelMap;
				}
				
				log.info("此用户已被remember---");
				if (subject.isAuthenticated()) {    
		            return modelMap;   
		        }
				
				int news = userNewsMapper.selectNumByUserid(user.getId());
				log.info("未读消息---"+news);
				if(news > 0){
					user.setNotReadNews(news);
					try {
						userMapper.updateByPrimaryKeySelective(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return modelMap;
					}
					
				}
		        subject.getSession().setAttribute("user", user);
		        
			}else{
				log.info("用户为登录，且未选择rememberMe");
			}
		
		}else{
			String[] str = subject.getPrincipal().toString().split(",");
			String account = str[0];
			log.info("account-----"+account);
			String token = str[1];
			log.info("token-----"+token);
			User user = userMapper.selectByAccount(account);
			if(!user.getToken().equals(token)){//token不一致
				log.info("token不一致");
				subject.logout();
				modelMap.put("logoutMsg", URLEncoder.encode("您已在其他设备登录（提醒：若非本人操作，请尽快修改密码）"));
				return modelMap;
			}
			
			int news = userNewsMapper.selectNumByUserid(autoUser.getId());
			if(news > 0){
				autoUser.setNotReadNews(news);
				try {
					userMapper.updateByPrimaryKeySelective(autoUser);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return modelMap;
				}
				subject.getSession().setAttribute("user", autoUser);
			}
			
		}
		
		return modelMap;
	}
	
	public static void addIntegral(User user,int integral){
		int dayIntegral = 0;
		if( Integer.valueOf(DateHelper.getNowDate().replace("-", "")) - Integer.valueOf(user.getLastTime().substring(0,10).replace("-", "")) >= 1 ){
			
		}else{
			dayIntegral = user.getDayIntegral();
		}
		
		if(dayIntegral < 50){
			if( (dayIntegral + integral) < 50 ){
				user.setDayIntegral(dayIntegral + integral);
				log.info("本次积分增加---"+integral);
			}else{
				user.setDayIntegral(50);
				integral = 50 - dayIntegral;
				log.info("添加积分已达上限，本次积分增加---"+integral);
			}
			
			user.setIntegral(user.getIntegral()+integral);
			
			try {
				userMapper.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("数据库更新user错误");
			}
			
		}else{
			log.info("当天积分已加到最大上限 50 分");
		}
	}
	
	public static void deletePic(String path){
		File file = new File(path); 
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete(); 
	    }
	}
	
	
	
}
