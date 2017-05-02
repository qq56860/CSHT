package cn.com.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


@Component
public class ALiDaYuSMSService {
	static Logger log=Logger.getLogger(ALiDaYuSMSService.class);
	
	private static String url = "http://gw.api.taobao.com/router/rest";
	private static String appkey = "23782379";
	private static String secret = "799f0d3592f0f954071085a5d07fc43b";
	
	public static void sendSms(String phone,String templateId,String datas){
		log.info("短信发送开始~~~");
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(phone);
		req.setSmsType("normal");
		req.setSmsFreeSignName("八一农大二货网");
		req.setSmsParamString(datas);
		req.setRecNum(phone);
		req.setSmsTemplateCode(templateId);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("发送验证码失败");
		}
		log.info("isSuccess---"+rsp.isSuccess());
		log.info("errorcode---"+rsp.getErrorCode());
		log.info("msg---"+rsp.getMsg());
		log.info(rsp.getBody());
	}
	
}
