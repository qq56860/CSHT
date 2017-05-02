package cn.com.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import cn.com.domain.Code;
import cn.com.domain.GoodsBuy;
import cn.com.domain.GoodsPublish;
import cn.com.domain.User;
import cn.com.domain.UserCollection;
import cn.com.domain.UserNews;
import cn.com.mapper.CodeMapper;
import cn.com.mapper.GoodsBuyMapper;
import cn.com.mapper.GoodsPublishMapper;
import cn.com.mapper.UserCollectionMapper;
import cn.com.mapper.UserMapper;
import cn.com.mapper.UserNewsMapper;
import cn.com.service.CommonService;
import cn.com.shiro.util.UsernamePasswordUsertypeToken;
import cn.com.util.DateHelper;
import cn.com.util.IDGenerator;
import cn.com.util.PropertyFactory;
import cn.com.util.ResultModel;

@Service
public class UserLogic {
	
	private static final String String = null;

	protected static Logger log = Logger.getLogger(UserLogic.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GoodsPublishMapper goodsPublishMapper;
	@Autowired
	private CodeMapper codeMapper;
	@Autowired
	private UserCollectionMapper userCollectionMapper;
	@Autowired
	private GoodsBuyMapper goodsBuyMapper;
	@Autowired
	private UserNewsMapper userNewsMapper;
	
	public String login(HttpServletRequest request,HttpServletResponse response){
		String account = request.getParameter("account");
		String spassword = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		String password = new Sha256Hash(spassword,PropertyFactory.getProperty("SHA256Salt")).toString();
		log.info("登录用户为："+account);
		
		UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(account, password,"user");
        Subject subject = SecurityUtils.getSubject();
		try {
            if("1".equals(remember)){
            	log.info("remeber user login");
				token.setRememberMe(true);
			}
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            log.info("密码错误");
            return JSONObject.fromObject(ResultModel.responseFaild("帐号或密码错误")).toString();
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
        	log.info("用户不存在");
        	return JSONObject.fromObject(ResultModel.responseFaild("用户不存在")).toString();
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
        	log.info("错误登录次数过多，请稍候再试");
        	return JSONObject.fromObject(ResultModel.responseFaild("错误登录次数过多，请稍候再试")).toString();
        }catch (ShiroException e) {
            // 捕获错误登录过多的异常
        	log.info(e.getMessage());
        	log.info(e.getCause());
        	return JSONObject.fromObject(ResultModel.responseFaild(e.getCause().getLocalizedMessage())).toString();
        }
        
		if(subject.isAuthenticated()){
			User user = userMapper.selectByAccount(account);
			//删除超过7天的消息
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", user.getId());
			Long sevenDay = 1000*604800L;
			map.put("time", DateHelper.changTimeMillisToStr(DateHelper.parseString2Long(DateHelper.getyyyyMMddHHmmssString())-sevenDay));
			int deleNews = userNewsMapper.deleteByUseridTimeOut(map);
			log.info("删除过期消息"+deleNews);
			//查询未读信息
			int news = userNewsMapper.selectNumByUserid(user.getId());
			log.info("未读消息---"+news);
			if(news > 0){
				user.setNotReadNews(news);
				try {
					userMapper.updateByPrimaryKeySelective(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("数据库更新user错误");
					return JSONObject.fromObject(ResultModel.responseFaild("登录失败")).toString();
				}
			}
			//今日增加积分
			if( Integer.valueOf(DateHelper.getNowDate().replace("-", "")) - Integer.valueOf(user.getLastTime().substring(0,10).replace("-", "")) >= 1 ){
				log.info("今日首次登录，重置本日新增积分");
				user.setDayIntegral(0);
				try {
					userMapper.updateByPrimaryKeySelective(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("数据库更新user错误");
					return JSONObject.fromObject(ResultModel.responseFaild("登录失败")).toString();
				}
			}
	        subject.getSession().setAttribute("user", user);
	        
	        user.setLastTime(DateHelper.getyyyyMMddHHmmssString());
	        user.setLastIp(getIp(request));
	        user.setLoginNum(user.getLoginNum()+1);
	        
	        try {
				userMapper.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("数据库更新user错误");
				return JSONObject.fromObject(ResultModel.responseFaild("登录失败")).toString();
			}
	        
	        log.info("登录方式为："+(account.equals(user.getEmail()) ? "邮箱":"手机号") );
	        
	        JSONObject obj = JSONObject.fromObject(ResultModel.responseSuccess("login success"));
			obj.put("nickname", user.getNickName());
			
			return obj.toString();
		}else{
			return JSONObject.fromObject(ResultModel.responseFaild("登录失败")).toString();
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
		String code = request.getParameter("code");		
		
		String ip = getIp(request);
	    log.info("login ip is"+ip);
	    
	    Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("type", "reg");
	    Code c = codeMapper.selectByPhoneAndType(map);
	    if( (DateHelper.parseString2Long(DateHelper.getyyyyMMddHHmmssString())-DateHelper.parseString2Long(c.getTime())) > 10*60*1000  ){
	    	log.info("验证码已过期，请重新获取验证码");
	    	return JSONObject.fromObject(ResultModel.responseFaild("验证码已过期，请重新获取验证码")).toString();
	    }
	    if( !c.getCode().equals(code) ){
	    	log.info("验证码错误");
	    	return JSONObject.fromObject(ResultModel.responseFaild("验证码错误")).toString();
	    }
	    
		User user = new User();
		user.setEmail(email);
		user.setId(UUID.randomUUID().toString());
		user.setIsClose(0);
		user.setLastIp(ip);
		user.setLastTime("");
		user.setLoginNum(0);
		user.setNickName(nickname);
		String Sha256Password = new Sha256Hash(password,PropertyFactory.getProperty("SHA256Salt")).toString();
		user.setPassword(Sha256Password);
		user.setPhone(phone);
		user.setRegTime(DateHelper.getyyyyMMddHHmmssString());
		user.setStudentCardAuthentication(0);
		user.setToken(IDGenerator.getId());
		user.setIntegral(0);
		user.setPic("default");
		user.setDayIntegral(0);
		
		try {
			userMapper.insert(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库插入错误")).toString();
		}
		log.info("注册成功，删除验证码信息");
		try {
			codeMapper.deleteByPrimaryKey(c.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库删除错误")).toString();
		}
		
		request.setAttribute("password", user.getPassword());
		request.setAttribute("account", user.getPhone());
		
		log.info("返回登录");
		return JSONObject.fromObject(ResultModel.responseSuccess("注册成功，请登录")).toString();
	}
	
	public String logOut(HttpServletRequest request,HttpServletResponse response){
		
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		return "redirect:/homePage";
	}
	
	public ModelAndView myInfo(HttpServletRequest request,HttpServletResponse response){
		
		String id = request.getParameter("id");
		String errorLog = (java.lang.String) request.getSession().getAttribute("errorLog");
		if(errorLog != null){
			request.getSession().removeAttribute("errorLog");
		}
		log.info("请求查看信息id---"+id);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/user/MyInfo");
		
		User reqUser = userMapper.selectByPrimaryKey(id);
		modelMap.put("reqUser", reqUser);
		
		int sellNum = goodsPublishMapper.selectSellNumByUser(id);
		modelMap.put("sellNum", sellNum);
		
		modelMap.put("errorLog", errorLog);
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView myCollection(HttpServletRequest request,HttpServletResponse response){
		
		String id = request.getParameter("id");
		log.info("请求查看收藏信息id---"+id);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/user/MyCollection");
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		log.info("登录id为"+user.getId());
		User reqUser = userMapper.selectByPrimaryKey(id);
		modelMap.put("reqUser", reqUser);
		
		ArrayList<UserCollection> collectionList = userCollectionMapper.selectByUserid(id);
		modelMap.put("collectionList", collectionList);
		
		int sellNum = goodsPublishMapper.selectSellNumByUser(id);
		modelMap.put("sellNum", sellNum);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView myPublish(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		log.info("请求查看发布信息id---"+id);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/user/MyPublish");
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		log.info("登录id为"+user.getId());
		User reqUser = userMapper.selectByPrimaryKey(id);
		modelMap.put("reqUser", reqUser);
		
		ArrayList<GoodsPublish> publishList = goodsPublishMapper.selectUserIdNotBuyed(id);
		ArrayList<GoodsPublish> publishIsBuyedList = goodsPublishMapper.selectUserIdIsBuyed(id);
		for (GoodsPublish p : publishList) {
			String pic = p.getPic().split(",")[0];
			p.setPic(pic);
		}
		for (GoodsPublish p : publishIsBuyedList) {
			String pic = p.getPic().split(",")[0];
			p.setPic(pic);
		}
		modelMap.put("publishList", publishList);
		modelMap.put("publishIsBuyedList", publishIsBuyedList);
		
		int sellNum = goodsPublishMapper.selectSellNumByUser(id);
		modelMap.put("sellNum", sellNum);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView myBuy(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		log.info("请求查看求购信息id---"+id);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/user/MyBuy");
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		log.info("登录id为"+user.getId());
		User reqUser = userMapper.selectByPrimaryKey(id);
		modelMap.put("reqUser", reqUser);
		
		ArrayList<GoodsBuy> buyList = goodsBuyMapper.selectByUserIdNotBuy(id);
		modelMap.put("buyList", buyList);
		
		int sellNum = goodsPublishMapper.selectSellNumByUser(id);
		modelMap.put("sellNum", sellNum);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public ModelAndView myNews(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		log.info("请求查看消息id---"+id);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("/user/MyNews");
		
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		User reqUser = userMapper.selectByPrimaryKey(id);
		modelMap.put("reqUser", reqUser);
		if(!user.getId().equals(id) ){
			model.setViewName("/noValid");
			return model;
		}
		
		//未读信息
		log.info("显示信息(未读，已读)---");
		ArrayList<UserNews> newsListP = userNewsMapper.selectPublishByUserid(id);
		ArrayList<UserNews> newsListB = userNewsMapper.selectBuyByUserid(id);
		modelMap.put("newsListP", newsListP);
		modelMap.put("newsListB", newsListB);
		//将未读信息置为已读
		try {
			userNewsMapper.updateAllNotReadByUser(id);
			user.setNotReadNews(0);
			userMapper.updateByPrimaryKeySelective(user);
			request.getSession().setAttribute("user", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("更新数据库错误");
			return model;
		}
		
		
		int sellNum = goodsPublishMapper.selectSellNumByUser(id);
		modelMap.put("sellNum", sellNum);
		
		model.addAllObjects(modelMap);
		return model;
	}
	
	public String InfoRepeat(HttpServletRequest request,HttpServletResponse response){
		String key = request.getParameter("key");
		String value = request.getParameter("value");
	    log.info(key+"校验；value---"+value);
		User user = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		user = userMapper.selectUser(map);
		if("nickname".equals(key)){
			if(user != null){
				return JSONObject.fromObject(ResultModel.responseFaild("该昵称已被注册")).toString();
			}else{
				return JSONObject.fromObject(ResultModel.responseSuccess("")).toString();
			}
		}else if("email".equals(key)){
			if(user != null){
				return JSONObject.fromObject(ResultModel.responseFaild("该邮箱已被注册")).toString();
			}else{
				return JSONObject.fromObject(ResultModel.responseSuccess("")).toString();
			}
		}else if("phone".equals(key)){
			if(user != null){
				return JSONObject.fromObject(ResultModel.responseFaild("该手机号码已被注册")).toString();
			}else{
				return JSONObject.fromObject(ResultModel.responseSuccess("")).toString();
			}
		}
		return JSONObject.fromObject(ResultModel.responseFaild("注册失败，请稍后再试")).toString();
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
	
	public String findPassword(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("find-type");
		String phoneOrEmail = request.getParameter("phoneOrEmail");
		String newPassword = request.getParameter("newPassword");
		String code = request.getParameter("code");
	    log.info(type+"找回密码---"+phoneOrEmail);
	    
	    Map<String, String> map = new HashMap<String, String>();
		map.put(type, phoneOrEmail);
		map.put("type", "findPassword");
	    Code c = codeMapper.selectByPhoneAndType(map);
	    if( (DateHelper.parseString2Long(DateHelper.getyyyyMMddHHmmssString())-DateHelper.parseString2Long(c.getTime())) > 10*60*1000  ){
	    	log.info("验证码已过期，请重新获取验证码");
	    	return JSONObject.fromObject(ResultModel.responseFaild("验证码已过期，请重新获取验证码")).toString();
	    }
	    if( !c.getCode().equals(code) ){
	    	log.info("验证码错误");
	    	return JSONObject.fromObject(ResultModel.responseFaild("验证码错误")).toString();
	    }
	    
		map.put("password", newPassword);
		try {
			userMapper.updateByEmailOrPhone(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库更新错误")).toString();
		}
		log.info("更改密码成功,删除验证码信息");
		try {
			codeMapper.deleteByPrimaryKey(c.getPhone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JSONObject.fromObject(ResultModel.responseFaild("数据库删除错误")).toString();
		}
		
		return JSONObject.fromObject(ResultModel.responseSuccess("密码修改成功")).toString().toString();
	}
	
	public ModelAndView changePhone(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String phone = request.getParameter("newPhone");
		String code = request.getParameter("code");
		log.info("修改手机号id---"+id+"---code---"+code);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/user/info?id="+id);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("type", "change");
	    Code c = codeMapper.selectByPhoneAndType(map);
	    if( (DateHelper.parseString2Long(DateHelper.getyyyyMMddHHmmssString())-DateHelper.parseString2Long(c.getTime())) > 10*60*1000  ){
	    	log.info("验证码已过期，请重新获取验证码");
	    	request.getSession().setAttribute("errorLog", "验证码已过期，请重新获取验证码");
	    	return model;
	    }
	    if( !c.getCode().equals(code) ){
	    	log.info("验证码错误");
	    	request.getSession().setAttribute("errorLog", "验证码错误");
	    	return model;
	    }
	    
	    User user = userMapper.selectByPrimaryKey(id);
	    user.setId(id);
	    user.setPhone(phone);
	    try {
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据库更新手机号错误");
			request.getSession().setAttribute("errorLog", "数据库更新手机号错误");
			return model;
		}
	    
	    
	    Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(phone, user.getPassword(),"user");
	    try {
            subject.login(token);
        } catch (Exception e) {
            // 
        	log.info(e.getMessage());
        	log.info(e.getCause());
        	request.getSession().setAttribute("errorLog", "更新手机号错误");
        	return model;
        }
        
		if(subject.isAuthenticated()){
	        subject.getSession().setAttribute("user", user);
		}
	    
		
		log.info("修改手机号成功,删除code");
		try {
			codeMapper.deleteByPrimaryKey(c.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除code错误");
			return model;
		}
		
		return model;
	}
	
	public ModelAndView changeEmail(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String email = request.getParameter("newEmail");
		String code = request.getParameter("code");
		log.info("修改邮箱id---"+id+"---code---"+code);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/user/info?id="+id);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("type", "change");
	    Code c = codeMapper.selectByPhoneAndType(map);
	    if( (DateHelper.parseString2Long(DateHelper.getyyyyMMddHHmmssString())-DateHelper.parseString2Long(c.getTime())) > 10*60*1000  ){
	    	log.info("验证码已过期，请重新获取验证码");
	    	request.getSession().setAttribute("errorLog", "验证码已过期，请重新获取验证码");
	    	return model;
	    }
	    if( !c.getCode().equals(code) ){
	    	log.info("验证码错误");
	    	request.getSession().setAttribute("errorLog", "验证码错误");
	    	return model;
	    }
	    
	    User user = userMapper.selectByPrimaryKey(id);
	    user.setId(id);
	    user.setEmail(email);
	    try {
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据库更新手机号错误");
			request.getSession().setAttribute("errorLog", "数据库更新手机号错误");
			return model;
		}
	    
	    
	    Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		UsernamePasswordUsertypeToken token = new UsernamePasswordUsertypeToken(email, user.getPassword(),"user");
	    try {
            subject.login(token);
        } catch (Exception e) {
            // 
        	log.info(e.getMessage());
        	log.info(e.getCause());
        	request.getSession().setAttribute("errorLog", "更新手机号错误");
        	return model;
        }
        
		if(subject.isAuthenticated()){
	        subject.getSession().setAttribute("user", user);
		}
	    
		
		log.info("修改邮箱成功,删除code");
		try {
			codeMapper.deleteByPrimaryKey(c.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除code错误");
			return model;
		}
		
		return model;
	}
	
	
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String oldPassword = new Sha256Hash(request.getParameter("oldPassword"),PropertyFactory.getProperty("SHA256Salt")).toString();
		String newPassword = new Sha256Hash(request.getParameter("newPassword"),PropertyFactory.getProperty("SHA256Salt")).toString();
		log.info("修改邮箱id---"+id+"---newPassword---"+newPassword);
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/user/info?id="+id);
		
	    
	    User user = userMapper.selectByPrimaryKey(id);
	    if(!user.getPassword().equals(oldPassword)){
	    	log.info("原密码错误");
	    	request.getSession().setAttribute("errorLog", "原密码错误");
        	return model;
	    }
	    
	    user.setPassword(newPassword);
	    try {
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据库更新密码错误");
			request.getSession().setAttribute("errorLog", "数据库更新密码错误");
			return model;
		}
	    
	    
	    Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
	        subject.getSession().setAttribute("user", user);
		}
	    
		
		log.info("修改密码成功");
		return model;
	}
	
	public ModelAndView changePic(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		ModelMap modelMap = new ModelMap();
		model.setViewName("redirect:/homePage");
		if(!ServletFileUpload.isMultipartContent(request)){
			log.info("表单传输格式错误");
			return model;
		}
		
		String path = System.getProperty("user.dir").replace("bin", "webapps")+PropertyFactory.getProperty("userImgPath");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		((DiskFileItemFactory) factory).setRepository(new File(path));
		upload.setHeaderEncoding("UTF-8");
		
		String id = null;
		String pic = null;
	    try {
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("表单共有几个属性-----"+items.size());
			for(FileItem i:items){
	            if(i.isFormField()){
	            	log.info(i.getFieldName()+"~~~~~~~~");
	            	log.info(new String(i.getString().getBytes("ISO-8859-1"),"UTF-8"));
	            	if("id".equals(i.getFieldName())){
	            		id = new String(i.getString().getBytes("ISO-8859-1"),"UTF-8");
	            	}
	            }else{
	            	log.info("图片-----"+i.getFieldName());
	            	
            		pic = "user"+((int)(Math.random()*9000)+1000)+DateHelper.getyyyyMMddHHmmss(DateHelper.getyyyyMMddHHmmssString());
            		System.out.println("path"+pic);
            		
	            	i.write(new File(path,pic+".png"));
	            	
	            }
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		model.setViewName("redirect:/user/info?id="+id);
	    
	    User user = userMapper.selectByPrimaryKey(id);
	    String oldPic = user.getPic();
	    user.setId(id);
	    user.setPic(pic);
	    try {
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("数据库更新头像错误");
			request.getSession().setAttribute("errorLog", "数据库更新头像错误");
			return model;
		}
	    
	    
	    Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
	        subject.getSession().setAttribute("user", user);
		}
	    
		
		log.info("修改头像成功,删除原来的头像");
		CommonService.deletePic(path+oldPic+".png");
		
		return model;
	}
	
	public ModelAndView deleteCollection(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String userid = request.getParameter("userid");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/user/collection?id="+userid);
		
		User sessionUser = (User) request.getSession().getAttribute("user");
		log.info("当前登陆用户id---"+sessionUser.getId()+"---reqUser---"+userid);
		if(!sessionUser.getId().equals(userid)){
			log.info("用户不能删除非本人的收藏");
			return model;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("goodsid",goodsid);
		try {
			userCollectionMapper.deleteByUseridAndGoodsid(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除userCollection错误");
			return model;
		}
		log.info("取消收藏成功");
		
		return model;
	}
	
	public ModelAndView goodsIsBuyed(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String userid = request.getParameter("userid");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/user/publish?id="+userid);
		
		User sessionUser = (User) request.getSession().getAttribute("user");
		log.info("当前登陆用户id---"+sessionUser.getId()+"---reqUser---"+userid);
		if(!sessionUser.getId().equals(userid)){
			log.info("用户不能操作非本人的物品");
			return model;
		}
		
		GoodsPublish goods = new GoodsPublish();
		goods.setId(goodsid);
		goods.setIsBuyed(true);
		try {
			goodsPublishMapper.updateByPrimaryKeySelective(goods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("更新goodsPublish错误");
			return model;
		}
		log.info("设置已售出成功");
		
		return model;
	}
	
	public ModelAndView deletePublish(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String userid = request.getParameter("userid");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/user/publish?id="+userid);
		
		User sessionUser = (User) request.getSession().getAttribute("user");
		log.info("当前登陆用户id---"+sessionUser.getId()+"---reqUser---"+userid);
		if(!sessionUser.getId().equals(userid)){
			log.info("用户不能操作非本人的物品");
			return model;
		}
		
		try {
			goodsPublishMapper.deleteByPrimaryKey(goodsid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除goodsPublish错误");
			return model;
		}
		log.info("撤销物品发布成功");
		
		return model;
	}
	
	public ModelAndView buyIsBuy(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String userid = request.getParameter("userid");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/user/buy?id="+userid);
		
		User sessionUser = (User) request.getSession().getAttribute("user");
		log.info("当前登陆用户id---"+sessionUser.getId()+"---reqUser---"+userid);
		if(!sessionUser.getId().equals(userid)){
			log.info("用户不能操作非本人的物品");
			return model;
		}
		
		GoodsBuy goods = new GoodsBuy();
		goods.setId(goodsid);
		goods.setIsBuy(true);
		try {
			goodsBuyMapper.updateByPrimaryKeySelective(goods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("更新goodsBuy错误");
			return model;
		}
		log.info("设置已购买成功");
		
		return model;
	}
	
	public ModelAndView deleteBuy(HttpServletRequest request,HttpServletResponse response){
		String goodsid = request.getParameter("goodsid");
		String userid = request.getParameter("userid");
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/user/buy?id="+userid);
		
		User sessionUser = (User) request.getSession().getAttribute("user");
		log.info("当前登陆用户id---"+sessionUser.getId()+"---reqUser---"+userid);
		if(!sessionUser.getId().equals(userid)){
			log.info("用户不能操作非本人的物品");
			return model;
		}
		
		try {
			goodsBuyMapper.deleteByPrimaryKey(goodsid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("删除goodsBuy错误");
			return model;
		}
		log.info("撤销物品收购成功");
		
		return model;
	}
	
	
	
	public static void main(String[] args) {
//		String str = "a111111";
//		String str1 = new Sha256Hash(str, "020036").toString();
//		
//		System.out.println(str1);
		
		
		
	}
	
	
}
