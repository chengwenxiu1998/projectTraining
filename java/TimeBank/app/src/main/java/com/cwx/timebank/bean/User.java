package com.cwx.timebank.bean;

import java.util.List;

public class User {
    private Integer uid;
    private String uname;
    private String uphone;
    private Byte sex;
    private String area;
    private String nickname;
    private String image;
    private String password;
    private String idcard;
    private Integer coin;
    private transient List<Discuss> discuss;


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }


    public User(String uname, String uphone, Byte sex, String area, String nickname, String image, String password,
                String idcard, Integer coin) {
        super();
        this.uname = uname;
        this.uphone = uphone;
        this.sex = sex;
        this.area = area;
        this.nickname = nickname;
        this.image = image;
        this.password = password;
        this.idcard = idcard;
        this.coin = coin;

    }

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }
    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public Byte getSex() {
        return sex;
    }
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }


    public Integer getCoin() {
        return coin;
    }


    public void setCoin(Integer coin) {
        this.coin = coin;
    }


    public List<Discuss> getDiscuss() {
        return discuss;
    }
    public void setDiscuss(List<Discuss> discuss) {
        this.discuss = discuss;
    }
    @Override
    public String toString() {
        return "User [uid=" + uid + ", uname=" + uname + ", uphone=" + uphone + ", sex=" + sex + ", area=" + area
                + ", nickname=" + nickname + ", image=" + image + ", password=" + password + ", idcard=" + idcard
                + ", coin=" + coin + "]";
    }


}
