package com.cwx.timebank.bean;

import java.util.Date;

public class DiscussReply {
    private Integer id;
    private Integer uId;
    private String replyContent;
    private String replyTime;
    //与discuss的关系是多对一（discuss)
    private Discuss discuss;
    public DiscussReply() {
        super();
        // TODO Auto-generated constructor stub
    }
    public DiscussReply(Integer uId, String replyContent, String replyTime) {
        super();
        this.uId = uId;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public Discuss getDiscuss() {
        return discuss;
    }
    public void setDiscuss(Discuss discuss) {
        this.discuss = discuss;
    }
    @Override
    public String toString() {
        return "DiscussReply [id=" + id + ", uId=" + uId + ", replyContent=" + replyContent + ", replyTime=" + replyTime
                + ", discuss=" + discuss + "]";
    }





}
