package com.cwx.timebank.bean;

public class ShaiReply {
    private Integer rid;
    private String rtime;
    private String rcontent;
    //晒晒回复与晒晒的关系 多对一
    private transient Shaishai shaishai;
    private User user;
    public ShaiReply() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ShaiReply(String rtime, String rcontent) {
        super();
        this.rtime = rtime;
        this.rcontent = rcontent;

    }

    public Integer getRid() {
        return rid;
    }
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRcontent() {
        return rcontent;
    }
    public void setRcontent(String rcontent) {
        this.rcontent = rcontent;
    }

    public Shaishai getShaishai() {
        return shaishai;
    }
    public void setShaishai(Shaishai shaishai) {
        this.shaishai = shaishai;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "ShaiReply [rid=" + rid + ", rtime=" + rtime + ", rcontent=" + rcontent +  ", user="
                + user + "]";
    }
}
