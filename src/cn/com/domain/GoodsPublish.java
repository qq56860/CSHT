package cn.com.domain;

public class GoodsPublish {
    private String id;

    private String userId;

    private Integer typeId;

    private Integer subId;

    private String goodsName;

    private String goodsContent;

    private String pic;

    private String tradePlace;

    private Float price;

    private Boolean isBargain;

    private Integer collectionNum;

    private Boolean isBuyed;

    private String contactMethod;

    private String createTime;
    
    private int visited;
    
    private Boolean isCollection;

	private User User;
    
	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}
	
    public Boolean getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Boolean isCollection) {
		this.isCollection = isCollection;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTradePlace() {
        return tradePlace;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getIsBargain() {
        return isBargain;
    }

    public void setIsBargain(Boolean isBargain) {
        this.isBargain = isBargain;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Boolean getIsBuyed() {
        return isBuyed;
    }

    public void setIsBuyed(Boolean isBuyed) {
        this.isBuyed = isBuyed;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getVisited() {
        return visited;
    }

    public void setVisited(Integer visited) {
        this.visited = visited;
    }
}