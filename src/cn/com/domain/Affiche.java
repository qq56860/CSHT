package cn.com.domain;

public class Affiche {
    private Integer id;

    private String title;

    private String content;

    private String createTime;

    private Integer adderId;

    private Boolean ifStick;

    private Boolean ifHide;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getAdderId() {
        return adderId;
    }

    public void setAdderId(Integer adderId) {
        this.adderId = adderId;
    }

    public Boolean getIfStick() {
        return ifStick;
    }

    public void setIfStick(Boolean ifStick) {
        this.ifStick = ifStick;
    }

    public Boolean getIfHide() {
        return ifHide;
    }

    public void setIfHide(Boolean ifHide) {
        this.ifHide = ifHide;
    }
}