package com.cwx.timebank.bean;

public class Tag {
    //标签的id
    private Integer tid;
    //标签的内容
    private String text;

    private transient Discuss discuss;

    //无参构造函数
    public Tag() {
        super();
        // TODO Auto-generated constructor stub
    }


    //有参构造函数
    public Tag(String text) {
        super();
        this.text = text;
    }


    public Integer getTid() {
        return tid;
    }
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }



    public Discuss getDiscuss() {
        return discuss;
    }


    public void setDiscuss(Discuss discuss) {
        this.discuss = discuss;
    }



}
