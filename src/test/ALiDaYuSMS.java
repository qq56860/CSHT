package test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class ALiDaYuSMS {
	public static void main(String[] args) {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23782379";
		String secret = "799f0d3592f0f954071085a5d07fc43b";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("id");
		req.setSmsType("normal");
		req.setSmsFreeSignName("八一农大二货网");
		req.setSmsParamString("{\"code\":\"1234\",\"time\":\"10\"}");
		req.setRecNum("17731902717");
		req.setSmsTemplateCode("SMS_63905538");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("发送验证码失败");
		}
		System.err.println("isSuccess---"+rsp.isSuccess());
		System.out.println("errorcode---"+rsp.getErrorCode());
		System.err.println("msg---"+rsp.getMsg());
		System.out.println(rsp.getBody());
	}
}
