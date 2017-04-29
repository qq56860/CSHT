package cn.com.thread;

import cn.com.service.EmailService;

public class emailThread implements Runnable{
	
	private String type;
	private String nickname;
	private String code;
	private String email;
	private String phone;
	private String password;
	private String publishOrBuy;
	private String URL;
	
	public emailThread(){}
	
	public emailThread(String type,String nickname,String code,String email,String phone,String password,String publishOrBuy,String URL){
		this.type = type;
		this.nickname = nickname;
		this.code = code;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.publishOrBuy = publishOrBuy;
		this.URL = URL;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("email线程开启");
		EmailService.sendMail(type, nickname, code, email, phone, password, publishOrBuy, URL);
	}
	
}
