package cn.com.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.domain.Code;
import cn.com.domain.User;
import cn.com.mapper.CodeMapper;
import cn.com.mapper.UserMapper;
import cn.com.thread.SmsThread;
import cn.com.thread.emailThread;
import cn.com.util.DateHelper;
import cn.com.util.PropertyFactory;
import cn.com.util.ResultModel;
import cn.com.util.imageCodeUtil;

@Service
public class CodeLogic {
	
	protected static Logger log = Logger.getLogger(CodeLogic.class);
	
	@Autowired
	private CodeMapper codeMapper;
	@Autowired
	private UserMapper userMapper;
	
	public String getCode(HttpServletRequest request,HttpServletResponse response){
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		String code =  String.valueOf((int)(Math.random()*9000)+1000);
		Code c = new Code();
		c.setId(UUID.randomUUID().toString());
		c.setCode(code);
		c.setEmail(email);
		c.setPhone(phone);
		c.setTime(DateHelper.getyyyyMMddHHmmssString());
		c.setType("reg");
		log.info("-----验证码是-----"+code);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", c.getPhone());
		map.put("type", c.getType());
		Code baseC = codeMapper.selectByPhoneAndType(map);
		if(baseC != null){
			log.info("db中存在数据，删除后插入");
			codeMapper.deleteByPrimaryKey(baseC.getId());
		}
		try {
			codeMapper.insert(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("插入code表错误");
		}
		
		String[] datas = new String[]
				{code,PropertyFactory.getProperty("codeTimeOut")};
		SmsThread sms = new SmsThread(phone,"1",datas);
		//第一个参数：电话号或电话数组，用“,”分割
		//第二个参数是模版id
		//第三个参数是变量数组
		Thread smsTread = new Thread(sms);
		smsTread.run();
		
		emailThread emailT = new emailThread("regCode",null,code,email,phone,null,null,null);
		Thread emailThread = new Thread(emailT);
		emailThread.run();
		
		return null;
	}
	
	public String getFindCode(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		String phoneOrEmail = request.getParameter("phoneOrEmail");
		log.info(type+"-----"+phoneOrEmail);
		User user = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put(type, phoneOrEmail);
		user = userMapper.selectUser(map);
		if(user == null){
			log.info("用户不存在");
			return JSONObject.fromObject(ResultModel.responseFaild("用户不存在")).toString();
		}
		
		String code =  String.valueOf((int)(Math.random()*9000)+1000);
		Code c = new Code();
		c.setId(UUID.randomUUID().toString());
		c.setCode(code);
		c.setEmail(user.getEmail());
		c.setPhone(user.getPhone());
		c.setTime(DateHelper.getyyyyMMddHHmmssString());
		c.setType("findPassword");
		log.info("-----验证码是-----"+code);
		
		Map<String, String> codemap = new HashMap<String, String>();
		codemap.put("phone", c.getPhone());
		codemap.put("type", c.getType());
		Code baseC = codeMapper.selectByPhoneAndType(codemap);
		if(baseC != null){
			log.info("db中存在数据，删除后插入");
			codeMapper.deleteByPrimaryKey(baseC.getId());
		}
		try {
			codeMapper.insert(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("插入code表错误");
		}
		
		if("email".equals(type)){
			emailThread emailT = new emailThread("findPasswordCode",null,code,phoneOrEmail,null,null,null,null);
			Thread emailThread = new Thread(emailT);
			emailThread.run();
		}else if("phone".equals(type)){
			String[] datas = new String[]
						{code,PropertyFactory.getProperty("codeTimeOut")};
			SmsThread sms = new SmsThread(phoneOrEmail,"1",datas);
			//第一个参数：电话号或电话数组，用“,”分割
			//第二个参数是模版id
			//第三个参数是变量数组
			Thread smsTread = new Thread(sms);
			smsTread.run();
		}
		
		return JSONObject.fromObject(ResultModel.responseSuccess("getFindCode success")).toString();
	}
	
	public String getChangeCode(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		log.info("type---"+type);
		
		Map<String, String> map = new HashMap<String, String>();
		User user = null;
		if("phone".equals(type)){
			map.put("phone", phone);
			user =userMapper.selectUser(map);
		}else if("email".equals(type)){
			map.put("email", email);
			user =userMapper.selectUser(map);
		}
		if(user != null){
			log.info(type+"已被注册");
			return JSONObject.fromObject(ResultModel.responseFaild(type+"已被注册")).toString();
		}
//		
//		User user = (User) request.getSession().getAttribute("user");
//		
		
		String code =  String.valueOf((int)(Math.random()*9000)+1000);
		Code c = new Code();
		c.setId(UUID.randomUUID().toString());
		c.setCode(code);
		if("phone".equals(type)){
			c.setEmail("");
			c.setPhone(phone);
		}else if("email".equals(type)){
			c.setEmail(email);
			c.setPhone("");
		}
		c.setTime(DateHelper.getyyyyMMddHHmmssString());
		c.setType("change");
		log.info("-----验证码是-----"+code);
		
		Map<String, String> codemap = new HashMap<String, String>();
		if("phone".equals(type)){
			codemap.put("phone", c.getPhone());
			codemap.put("type", c.getType());
		}else if("email".equals(type)){
			codemap.put("email", c.getEmail());
			codemap.put("type", c.getType());
		}
		Code baseC = codeMapper.selectByPhoneAndType(codemap);
		if(baseC != null){
			log.info("db中存在数据，删除后插入");
			codeMapper.deleteByPrimaryKey(baseC.getId());
		}
		try {
			codeMapper.insert(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("插入code表错误");
		}
		
		if("email".equals(type)){
			emailThread emailT = new emailThread("changeEmail",null,code,email,null,null,null,null);
			Thread emailThread = new Thread(emailT);
			emailThread.run();
		}else if("phone".equals(type)){
			String[] datas = new String[]
						{code,PropertyFactory.getProperty("codeTimeOut")};
			SmsThread sms = new SmsThread(phone,"1",datas);
			//第一个参数：电话号或电话数组，用“,”分割
			//第二个参数是模版id
			//第三个参数是变量数组
			Thread smsTread = new Thread(sms);
			smsTread.run();
		}
		
		return JSONObject.fromObject(ResultModel.responseSuccess("getChangeCode success")).toString();
	}
	
	
	public String getVerifyCode(HttpServletRequest request,HttpServletResponse response){
		imageCodeUtil.createImage();
		//验证码图片
        BufferedImage image = imageCodeUtil.getImg();
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
			ImageIO.write(image, "JPEG",os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("传输验证码图片错误");
			return null;
		}  
        //验证码内容
        String imageCode = imageCodeUtil.getText();
        log.info("code---"+imageCode);
        request.getSession().setAttribute("imageCode", imageCode); 
        log.info("获取图片验证码成功");
        return null;
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println();
	}
	
	
}
