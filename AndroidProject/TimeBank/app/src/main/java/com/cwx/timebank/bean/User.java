package com.cwx.timebank.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Integer uId;
    private String uName;
    private String uPhone;
    private Byte uSex;
    private String uArea;
    private String uNickName;
    private String uImage;
    private String uPassword;
    private String uIdCard;
    private Integer uCoin;
    private transient List<Discuss> discuss;
    private transient List<Shaishai> shaishai;
    private transient List<ShaiReply> shaiReply;


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String uName, String uPhone, Byte uSex, String uArea, String uNickName, String uImage, String uPassword,
                String uIdCard, Integer uCoin) {
        super();
        this.uName = uName;
        this.uPhone = uPhone;
        this.uSex = uSex;
        this.uArea = uArea;
        this.uNickName = uNickName;
        this.uImage = uImage;
        this.uPassword = uPassword;
        this.uIdCard = uIdCard;
        this.uCoin = uCoin;
    }

    public Integer getuId() {
        return uId;
    }
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }
    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPhone() {
        return uPhone;
    }
    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Byte getuSex() {
        return uSex;
    }
    public void setuSex(Byte uSex) {
        this.uSex = uSex;
    }

    public String getuArea() {
        return uArea;
    }
    public void setuArea(String uArea) {
        this.uArea = uArea;
    }

    public String getuNickName() {
        return uNickName;
    }
    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }

    public String getuImage() {
        return uImage;
    }
    public void setuImage(String uImage) {
        this.uImage = uImage;
    }

    public String getuPassword() {
        return uPassword;
    }
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuIdCard() {
        return uIdCard;
    }
    public void setuIdCard(String uIdCard) {
        this.uIdCard = uIdCard;
    }

    public Integer getuCoin() {
        return uCoin;
    }
    public void setuCoin(Integer uCoin) {
        this.uCoin = uCoin;
    }



    public List<Discuss> getDiscuss() {
        return discuss;
    }
    public void setDiscuss(List<Discuss> discuss) {
        this.discuss = discuss;
    }



    public List<Shaishai> getShaishai() {
        return shaishai;
    }
    public void setShaishai(List<Shaishai> shaishai) {
        this.shaishai = shaishai;
    }

    public List<ShaiReply> getShaiReply() {
        return shaiReply;
    }
    public void setShaiReply(List<ShaiReply> shaiReply) {
        this.shaiReply = shaiReply;
    }



    @Override
    public String toString() {
        return "User [uId=" + uId + ", uName=" + uName + ", uPhone=" + uPhone + ", uSex=" + uSex + ", uArea=" + uArea
                + ", uNickName=" + uNickName + ", uImage=" + uImage + ", uPassword=" + uPassword + ", uIdCard="
                + uIdCard + ", uCoin=" + uCoin + "]";
    }




}
