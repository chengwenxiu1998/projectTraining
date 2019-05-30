package com.cwx.timebank.bean;

public class ShaiReply {
    private Integer rid;
    private String rtime;
    private String rcontent;
    private Integer uid;
    //晒晒回复与晒晒的关系 多对一
    private Shaishai shaishai;
    public ShaiReply() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ShaiReply(String rtime, String rcontent, Integer uid) {
        super();
        this.rtime = rtime;
        this.rcontent = rcontent;
        this.uid = uid;
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

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Shaishai getShaishai() {
        return shaishai;
    }
    public void setShaishai(Shaishai shaishai) {
        this.shaishai = shaishai;
    }
    @Override
    public String toString() {
        return "ShaiReply [rid=" + rid + ", rtime=" + rtime + ", rcontent=" + rcontent + ", uid=" + uid + ", shaishai="
                + shaishai + "]";
    }
}
