package cn.com.logic;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.domain.User;
import cn.com.mapper.UserMapper;
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
		
		log.info(account);
		
		User user = userMapper.selectByAccount(account);
		if(user == null){
			log.info("密码错误");
			return JSONObject.fromObject(ResultModel.responseFaild("帐号或密码错误")).toString();
		}
		log.info("登录方式为："+(account.equals(user.getEmail()) ? "邮箱":"手机号") );
		JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess("login success"));
		obj.put("nickname", user.getNickName());
		return obj.toString();
	}
	
	public String reg(HttpServletRequest request,HttpServletResponse response){
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
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
		
		JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess("注册成功"));
		obj.put("nickname", nickname);
		return obj.toString();
	}
	
}
