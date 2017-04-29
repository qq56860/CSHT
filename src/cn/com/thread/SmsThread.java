package cn.com.thread;

import cn.com.service.SmsService;

public class SmsThread implements Runnable{
	
	private String phone;
	private String templateId;
	private String[] datas;
	
	public SmsThread(){}
	
	public SmsThread(String phone,String templateId,String[] datas){
		this.datas = datas;
		this.phone = phone;
		this.templateId = templateId;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("sms线程开启");
		SmsService.sendSms(phone, templateId, datas);
	}
	
}
