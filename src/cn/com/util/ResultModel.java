package cn.com.util;

public class ResultModel {

	String statusCode;
	String message;
	String navTabId;
	String rel;
	String callbackType;
	String forwardUrl;
	
	public static ResultModel responseSuccess( String message){
		ResultModel rm = new ResultModel();
		rm.setStatusCode("200");
		rm.setMessage(message);
		rm.setCallbackType("closeCurrent");
		return rm;
	}
	
	public static ResultModel responseFaild( String message){
		ResultModel rm = new ResultModel();
		rm.setStatusCode("300");
		rm.setMessage(message);
		return rm;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
}
