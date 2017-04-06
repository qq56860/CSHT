package cn.com.domain;

public class GoodsBuy {
    private String id;

    private String userId;

    private Integer typeId;

    private Integer subId;

    private String goodsName;

    private String goodsContent;

    private String tradePlace;

    private Float wishPrice;

    private Boolean isBuy;

    private Boolean contactMe;

    private String createTime;

    private String contactPhone;

    private String contactQq;

    private String contactWeChat;

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

    public String getTradePlace() {
        return tradePlace;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public Float getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(Float wishPrice) {
        this.wishPrice = wishPrice;
    }

    public Boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Boolean isBuy) {
        this.isBuy = isBuy;
    }

    public Boolean getContactMe() {
        return contactMe;
    }

    public void setContactMe(Boolean contactMe) {
        this.contactMe = contactMe;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactQq() {
        return contactQq;
    }

    public void setContactQq(String contactQq) {
        this.contactQq = contactQq;
    }

    public String getContactWeChat() {
        return contactWeChat;
    }

    public void setContactWeChat(String contactWeChat) {
        this.contactWeChat = contactWeChat;
    }
}