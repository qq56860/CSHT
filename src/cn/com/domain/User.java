package cn.com.domain;

import java.util.List;

public class User {
    private String id;

    private String token;

    private String phone;

    private String email;

    private String nickName;

    private String password;

    private String pic;

    private Integer integral;

    private Integer dayIntegral;

    private String regTime;

    private Integer loginNum;

    private String lastTime;

    private String lastIp;

    private Integer studentCardAuthentication;

    private Integer isClose;
	
	private Integer notReadNews;
    
    private List<GoodsPublish> publishList;
    

    public List<GoodsPublish> getPublishList() {
		return publishList;
	}

	public void setPublishList(List<GoodsPublish> publishList) {
		this.publishList = publishList;
	}
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getDayIntegral() {
        return dayIntegral;
    }

    public void setDayIntegral(Integer dayIntegral) {
        this.dayIntegral = dayIntegral;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Integer getStudentCardAuthentication() {
        return studentCardAuthentication;
    }

    public void setStudentCardAuthentication(Integer studentCardAuthentication) {
        this.studentCardAuthentication = studentCardAuthentication;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public Integer getNotReadNews() {
        return notReadNews;
    }

    public void setNotReadNews(Integer notReadNews) {
        this.notReadNews = notReadNews;
    }
}