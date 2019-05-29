package com.cwx.timebank.bean;

import java.util.List;

public class Discuss {

    private int dId;
    private String dTopicCoutent;
    //一对一的关系
    private Tag tag;
    private User user;
    private transient List<DiscussReply> discussReplys;
    public Discuss() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Discuss(int dId, String dTopicCoutent) {
        super();
        this.dId = dId;
        this.dTopicCoutent = dTopicCoutent;
    }

    public int getdId() {
        return dId;
    }
    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdTopicCoutent() {
        return dTopicCoutent;
    }
    public void setdTopicCoutent(String dTopicCoutent) {
        this.dTopicCoutent = dTopicCoutent;
    }


    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<DiscussReply> getDiscussReplys() {
        return discussReplys;
    }
    public void setDiscussReplys(List<DiscussReply> discussReplys) {
        this.discussReplys = discussReplys;
    }
    @Override
    public String toString() {
        return "Discuss [dId=" + dId + ", dTopicCoutent=" + dTopicCoutent + "]";
    }
}
