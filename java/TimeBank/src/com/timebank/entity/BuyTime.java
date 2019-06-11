package com.timebank.entity;

import java.util.Date;

public class BuyTime {
	private int tId;
	private int uIdSend;
    private String uNickName;
    private String uImage;
    private Date uTime;
    private String  tDesc;
    private int tCoinCount;
    private String tagText;
    private String tState;
    private int uIdAccept;
    private Date tEndtime;
    private String tImageUrl;
 
    public BuyTime() {
    }


    public BuyTime(int tId, int uIdSend,String uNickName, String uImage, Date uTime, String tDesc, int tCoinCount,
			String tagText, String tState, int uIdAccept, Date tEndtime, String tImageUrl) {
		super();
		this.uIdSend = uIdSend;
		this.tId = tId;
		this.uNickName = uNickName;
		this.uImage = uImage;
		this.uTime = uTime;
		this.tDesc = tDesc;
		this.tCoinCount = tCoinCount;
		this.tagText = tagText;
		this.tState = tState;
		this.uIdAccept = uIdAccept;
		this.tEndtime = tEndtime;
		this.tImageUrl = tImageUrl;
	}



	public int getuIdSend() {
		return uIdSend;
	}


	public void setuIdSend(int uIdSend) {
		this.uIdSend = uIdSend;
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

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String gettDesc() {
        return tDesc;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public int gettCoinCount() {
        return tCoinCount;
    }

    public void settCoinCount(int tCoinCount) {
        this.tCoinCount = tCoinCount;
    }

    public String gettState() { return tState; }

    public void settState(String tState) { this.tState = tState; }

    public int gettId() { return tId; }

    public void settId(int tId) { this.tId = tId; }

    public int getuIdAccept() { return uIdAccept; }

    public void setuIdAccept(int uIdAccept) { this.uIdAccept = uIdAccept; }

    public String gettImageUrl() {
        return tImageUrl;
    }

    public void settImageUrl(String tImageUrl) {
        this.tImageUrl = tImageUrl;
    }

    public Date gettEndtime() {
        return tEndtime;
    }

    public void settEndtime(Date tEndtime) {
        this.tEndtime = tEndtime;
    }


	@Override
	public String toString() {
		return "BuyTime [tId=" + tId + ", uIdSend=" + uIdSend + ", uNickName=" + uNickName + ", uImage=" + uImage
				+ ", uTime=" + uTime + ", tDesc=" + tDesc + ", tCoinCount=" + tCoinCount + ", tagText=" + tagText
				+ ", tState=" + tState + ", uIdAccept=" + uIdAccept + ", tEndtime=" + tEndtime + ", tImageUrl="
				+ tImageUrl + "]";
	}
    
    
}
