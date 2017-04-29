package cn.com.domain;

public class UserCollection {
    private String userId;

    private String goodsId;
    
    private GoodsPublish goodsPublish;
    

	public GoodsPublish getGoodsPublish() {
		return goodsPublish;
	}

	public void setGoodsPublish(GoodsPublish goodsPublish) {
		this.goodsPublish = goodsPublish;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}