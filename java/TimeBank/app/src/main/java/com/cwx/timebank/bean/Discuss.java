package com.cwx.timebank.bean;

public class Discuss {

    //讨论的id
    private Integer did;
    /*//标签的id
    private Integer tid;
    //发布讨论人的id
    private Integer uid;*/
    //讨论的内容
    private String content;
    //讨论与tag一对一映射
    private Tag tag;
    //讨论与用户多对一
    private User user;


    public Discuss() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Discuss(String content) {
        super();
        this.content = content;
    }


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Discuss [did=" + did + ", content=" + content + "]";
    }






}

