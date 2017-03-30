package cn.com.logic;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.domain.User;
import cn.com.mapper.UserMapper;
import cn.com.shiro.util.UsernamePasswordUsertypeToken;
import cn.com.util.DateHelper;
import cn.com.util.IDGenerator;
import cn.com.util.ResultModel;

@Service
public class UserLogic {
	
	protected static Logger log = Logger.getLogger(UserLogic.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public String login(HttpServletRequest request,HttpServletResponse response){
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		log.info("登录用户为："+account);
		
		UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(account, password,"user");
        Subject subject = SecurityUtils.getSubject();
		try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            log.info("密码错误");
            return JSONObject.fromObject(ResultModel.responseFaild("帐号或密码错误")).toString();
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
        	log.info("");
        	return JSONObject.fromObject(ResultModel.responseFaild("用户不存在")).toString();
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
        	log.info("");
        	return JSONObject.fromObject(ResultModel.responseFaild("错误登录次数过多，请稍候再试")).toString();
        }
        
		if(subject.isAuthenticated()){
			User user = userMapper.selectByAccount(account);
	        subject.getSession().setAttribute("user", user);
	        
	        log.info("登录方式为："+(account.equals(user.getEmail()) ? "邮箱":"手机号") );
	        
	        JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess("login success"));
			obj.put("nickname", user.getNickName());
			return obj.toString();
		}else{
			return JSONObject.fromObject(ResultModel.responseFaild("权限不足")).toString();
		}
		
		
				
//		User user = userMapper.selectByAccount(account);
//		if(user == null){
//			log.info("用户不存在");
//			return JSONObject.fromObject(ResultModel.responseFaild("用户不存在")).toString();
//		}
//		if(!password.equals(user.getPassword())){
//			log.info("密码错误");
//			return JSONObject.fromObject(ResultModel.responseFaild("帐号或密码错误")).toString();
//		}
		
	}
	
	public String reg(HttpServletRequest request,HttpServletResponse response){
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		
		String ip = getIp(request);
	    log.info("login ip is"+ip);
	    
		User user = new User();
		user.setEmail(email);
		user.setId(UUID.randomUUID().toString());
		user.setIsClose(0);
		user.setLastIp(ip);
		user.setLastTime(DateHelper.getyyyyMMddHHmmssString());
		user.setLoginNum(1);
		user.setNickName(nickname);
		user.setPassword(password);
		user.setPhone(phone);
		user.setRegTime(DateHelper.getyyyyMMddHHmmssString());
		user.setStudentCardAuthentication(0);
		user.setToken(IDGenerator.getId());
		
		try {
			userMapper.insert(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库插入错误")).toString();
		}
		
		request.setAttribute("password", user.getPassword());
		request.setAttribute("account", user.getPhone());
		
		log.info("注册成功，重定向到登录");
		return "redirect:/user/login?account="+user.getEmail()+"&password="+user.getPassword();
	}
	
	
	
	
	
	
	
	
	
	private String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip;
	}
	
}
