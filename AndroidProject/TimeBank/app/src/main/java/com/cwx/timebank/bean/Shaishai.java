package com.cwx.timebank.bean;

import java.util.List;

public class Shaishai {
    private Integer sid;
    private String stext;
    private String stime;
    private Integer scount;
    private User user;
    private  List<ShaiReply> shaiReplys;
    public Shaishai() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Shaishai(String stext, String stime, Integer scount) {
        super();
        this.stext = stext;
        this.stime = stime;
        this.scount = scount;
    }

    public Integer getSid() {
        return sid;
    }
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStext() {
        return stext;
    }
    public void setStext(String stext) {
        this.stext = stext;
    }

    public String getStime() {
        return stime;
    }
    public void setStime(String stime) {
        this.stime = stime;
    }

    public Integer getScount() {
        return scount;
    }
    public void setScount(Integer scount) {
        this.scount = scount;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<ShaiReply> getShaiReplys() {
        return shaiReplys;
    }
    public void setShaiReplys(List<ShaiReply> shaiReplys) {
        this.shaiReplys = shaiReplys;
    }
    @Override
    public String toString() {
        return "Shaishai [sid=" + sid + ", stext=" + stext + ", stime=" + stime + ", scount=" + scount + ", user="
                + user + ", shaiReplys=" + shaiReplys + "]";
    }




}
