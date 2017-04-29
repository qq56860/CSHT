package cn.com.domain;

public class UserNews {
    private String id;

    private String who;

    private String time;

    private String area;

    private String goodsId;

    private String content;

    private String userId;

    private Boolean isRead;
    
    private User user;
    
    private GoodsPublish goodsPublish;
    
    private GoodsBuy goodsBuy;

    public GoodsBuy getGoodsBuy() {
		return goodsBuy;
	}

	public void setGoodsBuy(GoodsBuy goodsBuy) {
		this.goodsBuy = goodsBuy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GoodsPublish getGoodsPublish() {
		return goodsPublish;
	}

	public void setGoodsPublish(GoodsPublish goodsPublish) {
		this.goodsPublish = goodsPublish;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}