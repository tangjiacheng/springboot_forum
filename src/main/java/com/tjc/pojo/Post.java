package com.tjc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: TJC
 * @Date: 2020/6/16 21:36
 * @description: TODO
 */
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    // 帖子类型: 0: 提问  1: 分享  2: 讨论  3: 建议  4: 公告  5: 动态
    private Integer type;
    private String typeStr;
    private String content;
    private Boolean isUp;
    private Boolean isGood;
    private Boolean isEnd;
    private Integer coin;
    private Date createTime;
    private Integer adoptId;

    private Integer reply;
    private String view;

    private User user;

    public String getTypeStr() {
        if (type == null) return "";
        switch (type) {
            case 0 -> typeStr = "提问";
            case 1 -> typeStr = "分享";
            case 2 -> typeStr = "讨论";
            case 3 -> typeStr = "建议";
            case 4 -> typeStr = "公告";
            case 5 -> typeStr = "动态";
            default -> typeStr = "";
        }
        return typeStr;
    }

    public Integer getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(Integer adoptId) {
        this.adoptId = adoptId;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getUp() {
        return isUp;
    }

    public void setUp(Boolean up) {
        isUp = up;
    }

    public Boolean getGood() {
        return isGood;
    }

    public void setGood(Boolean good) {
        isGood = good;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", typeStr='" + typeStr + '\'' +
                ", content='" + content + '\'' +
                ", isUp=" + isUp +
                ", isGood=" + isGood +
                ", isEnd=" + isEnd +
                ", coin=" + coin +
                ", createTime=" + createTime +
                ", reply=" + reply +
                ", user=" + user +
                '}';
    }
}
