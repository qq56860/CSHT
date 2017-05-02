package cn.com.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.util.PropertyFactory;


@Component
public class EmailService {
	
	static Logger log=Logger.getLogger(EmailService.class);
	private static String EmailSMTPHost = "smtp.aliyun.com";
	private static String EmailAccount = "bynderhuowang@aliyun.com";
	private static String EmailPassword = "qq15188901442";
	
	
	public static void sendMail(String type,String nickname,String code,String email,String phone,String password,String publishOrBuy,String URL){
		log.info(type+"邮件发送开始~~~");
		
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();                    // 参数配置
		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.host", EmailSMTPHost);          // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关
		
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);                                 // 设置为debug模式, 可以查看详细的发送 log
		
		
		try {
			
			//------------------------------------------------------------------
			MimeMessage message = null;
			if("regCode".equals(type)){
				message = regCodeEmail(session,email,code);
			}else if("findPasswordCode".equals(type)){
				message = findPasswordCodeEmail(session,email,code);
			}else if("changeEmail".equals(type)){
				message = changeEmailCodeEmail(session,email,code);
			}
			//-------------------------------------------------------
			
			
			
			// 10. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();
			
			// 11. 使用 邮箱账号 和 密码 连接邮件服务器
			//    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
			transport.connect(EmailAccount, EmailPassword);
			
			// 12. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			
			// 13. 关闭连接
			transport.close();
			log.info("邮件发送成功");
			
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//找回密码验证码
	private static MimeMessage changeEmailCodeEmail(Session session,String email,String code) throws Exception{
		// 3. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 4. From: 发件人
		message.setFrom(new InternetAddress(EmailAccount, "八一农大2货网", "UTF-8"));
		// 5. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email,"亲爱的老铁", "UTF-8"));
		// 6. Subject: 邮件主题
		message.setSubject("修改邮箱通知", "UTF-8");
		
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("亲爱的同学,您好!</br>" +
				"听说您要修改邮箱了!下面是您修改邮箱的验证码:</br>" +
				"验证码为:" +code+"</br>"+
				"此验证码有效时间为10分钟,感谢合作!"+"</br>"+
				"</br>" +
				"八一农大2货网奉上!!!若非本人操作，请无视", "text/html;charset=UTF-8");
		
		MimeMultipart mmp=new MimeMultipart();
		mmp.addBodyPart(text);
		
		//邮件内容
		message.setContent(mmp);
		// 8. 设置发件时间
		message.setSentDate(new Date());
		
		// 9. 保存设置
		message.saveChanges();
		//*************************************************************************************
		return message;
	}
	
	//找回密码验证码
	private static MimeMessage findPasswordCodeEmail(Session session,String email,String code) throws Exception{
		// 3. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 4. From: 发件人
		message.setFrom(new InternetAddress(EmailAccount, "八一农大2货网", "UTF-8"));
		// 5. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email,"亲爱的老铁", "UTF-8"));
		// 6. Subject: 邮件主题
		message.setSubject("找回密码验证码通知", "UTF-8");
		
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("亲爱的同学,您好!</br>" +
				"密码丢了不要怕!下面是您找回密码的验证码:</br>" +
				"验证码为:" +code+"</br>"+
				"此验证码有效时间为10分钟,感谢合作!"+"</br>"+
				"</br>" +
				"八一农大2货网奉上!!!若非本人操作，请无视", "text/html;charset=UTF-8");
		
		MimeMultipart mmp=new MimeMultipart();
		mmp.addBodyPart(text);
		
		//邮件内容
		message.setContent(mmp);
		// 8. 设置发件时间
		message.setSentDate(new Date());
		
		// 9. 保存设置
		message.saveChanges();
		//*************************************************************************************
		return message;
	}
	
	//注册验证码
	private static MimeMessage regCodeEmail(Session session,String email,String code) throws Exception{
		// 3. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 4. From: 发件人
		message.setFrom(new InternetAddress(EmailAccount, "八一农大2货网", "UTF-8"));
		// 5. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email,"亲爱的老铁", "UTF-8"));
		// 6. Subject: 邮件主题
		message.setSubject("注册验证码通知", "UTF-8");
		
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("亲爱的同学,您好!</br>" +
				"欢迎您注册成为八一农大2货网用户!</br>" +
				"您的验证码为:" +code+"</br>"+
				"此验证码有效时间为10分钟,感谢合作!"+"</br>"+
				"</br>" +
				"<img src='cid:QRcode'></br>" +
				"八一农大2货网奉上!!!若非本人操作，请无视", "text/html;charset=UTF-8");
		
		//创建图片“节点”
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dh = new DataHandler(new URLDataSource(new URL(PropertyFactory.getProperty("serverPath")+
							PropertyFactory.getProperty("sysImg")+"welcome.png")));
		image.setDataHandler(dh);          // 将图片数据添加到“节点”
		image.setContentID("QRcode");     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
		
		
		MimeMultipart mmp=new MimeMultipart();
		mmp.addBodyPart(text);
		mmp.addBodyPart(image);
		mmp.setSubType("related");
		
		//邮件内容
		message.setContent(mmp);
		// 8. 设置发件时间
		message.setSentDate(new Date());
		
		// 9. 保存设置
		message.saveChanges();
		//*************************************************************************************
		return message;
	}
	
	
	
	
	
	
	
	
	
	
	private static MimeMessage testEmail(Session session) throws MessagingException, MalformedURLException, UnsupportedEncodingException{
		//*****************************************************************************************
		// 3. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 4. From: 发件人
		message.setFrom(new InternetAddress(EmailAccount, "八一农大2货网", "UTF-8"));
		// 5. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("email","【"+"enterpriseName"+"】管理员", "UTF-8"));
		// 6. Subject: 邮件主题
		message.setSubject("云企通服务开通通知", "UTF-8");
		
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("尊敬的【"+"enterpriseName"+"】管理员，您好！</br>" +
				"恭喜贵单位已经开通云企通服务！</br>" +
				"管理员账号 ：" +"name"+"</br>"+
				"密码：" +"pwd"+"</br>" +
						"<img src='cid:QRcode'></br>" +
				"IPhone端下载：AppStore搜索“云企通Pro”下载；</br>" +
				"安卓端下载：应用市场搜索“云企通专业版”下载；</br>" +
				"电脑客客户端下载：" +PropertyFactory.getProperty("PCdownload")+"</br>"+
				"web自服务登录网址：" +PropertyFactory.getProperty("web_self")+"</br>"+
				"云企通官网：" +PropertyFactory.getProperty("official_website")+"</br>"+
				"云企通手机客服端操作手册：" +PropertyFactory.getProperty("mobile_manual")+"</br>"+
				"云企通电脑客户端操作手册：" +PropertyFactory.getProperty("PC_manual")+"</br>"+
				"云企通web自服务平台操作手册：" +PropertyFactory.getProperty("self_manual")+"</br>"+
				"十分感谢你的支持，有问题请随时与我们联系！客服电话：" +PropertyFactory.getProperty("service_phone")+"</br>"+
				"</br>" +
				"阿拉丁团队", "text/html;charset=UTF-8");
		
		//创建图片“节点”
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dh = new DataHandler(new URLDataSource(new URL(PropertyFactory.getProperty("IMGADDR")+PropertyFactory.getProperty("IMGPATH")+"QRcode.png")));
		image.setDataHandler(dh);          // 将图片数据添加到“节点”
		image.setContentID("QRcode");     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）
		
		
		MimeMultipart mmp=new MimeMultipart();
		mmp.addBodyPart(text);
		mmp.addBodyPart(image);
		mmp.setSubType("related");
		
		//邮件内容
		message.setContent(mmp);
		// 8. 设置发件时间
		message.setSentDate(new Date());
		
		// 9. 保存设置
		message.saveChanges();
		//*************************************************************************************
		return message;
	}
	
	public static void main(String[] args) {
		
		
	}
	
}
