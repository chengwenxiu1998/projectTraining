package com.timebank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="coin_trage_record")
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	public int getuId() {
		return uId;
	}
	@Column(name="ctr_finish_time")
	public Date getCtrFinishTime() {
		return ctrFinishTime;
	}
	@Column(name="ctr_count")
	public int getCtrCount() {
		return ctrCount;
	}
	@Column(name="ctr_is_add_or_reduce")
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
