package com.cwx.timebank.bean;

import java.util.Date;


public class CoinTrageRecord {
	private int uId;
	private Date ctrFinishTime;
	private int ctrCount;
	private byte AddOrReduce;
	public CoinTrageRecord() {
		
	}
	public CoinTrageRecord(int uId, Date ctrFinishTime, int ctrCount, byte addOrReduce) {
		this.uId = uId;
		this.ctrFinishTime = ctrFinishTime;
		this.ctrCount = ctrCount;
		AddOrReduce = addOrReduce;
	}
	

	public int getuId() {
		return uId;
	}

	public Date getCtrFinishTime() {
		return ctrFinishTime;
	}

	public int getCtrCount() {
		return ctrCount;
	}

	public byte getAddOrReduce() {
		return AddOrReduce;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setCtrFinishTime(Date ctrFinishTime) {
		this.ctrFinishTime = ctrFinishTime;
	}
	public void setCtrCount(int ctrCount) {
		this.ctrCount = ctrCount;
	}
	public void setAddOrReduce(byte addOrReduce) {
		AddOrReduce = addOrReduce;
	}
	
	@Override
	public String toString() {
		return "CoinTrageRecordBean [uId=" + uId + ", ctrFinishTime=" + ctrFinishTime + ", ctrCount=" + ctrCount
				+ ", AddOrReduce=" + AddOrReduce + "]";
	}
	
	

}
