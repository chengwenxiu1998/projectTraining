package com.timebank.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="task")
public class Task {
	private int tId;
	private int uIdSend;
	private Date uTime;
	private int tcId;
	private String  tDesc;
	private int tCoinCount;
	private String tState;
	private int uIdAccept;
	private int tagId;
	private Date tEndTime;
	private String tImgUrl;
	private Date tAcceptTime;
	private Date tFinishTime;
//	private transient User user;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="t_id")
	public int gettId() {
		return tId;
	}


	public void settId(int tId) {
		this.tId = tId;
	}


	@Column(name="u_id_send")
	public int getuIdSend() {
		return uIdSend;
	}


	public void setuIdSend(int uIdSend) {
		this.uIdSend = uIdSend;
	}

	@Column(name="u_time")
	public Date getuTime() {
		return uTime;
	}


	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	@Column(name="tc_id")
	public int getTcId() {
		return tcId;
	}


	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	@Column(name="t_desc")
	public String gettDesc() {
		return tDesc;
	}


	public void settDesc(String tDesc) {
		this.tDesc = tDesc;
	}

	@Column(name="t_coin_count")
	public int gettCoinCount() {
		return tCoinCount;
	}


	public void settCoinCount(int tCoinCount) {
		this.tCoinCount = tCoinCount;
	}

	@Column(name="t_state")
	public String gettState() {
		return tState;
	}


	public void settState(String tState) {
		this.tState = tState;
	}

	@Column(name="u_id_accept")
	public int getuIdAccept() {
		return uIdAccept;
	}


	public void setuIdAccept(int uIdAccept) {
		this.uIdAccept = uIdAccept;
	}

	@Column(name="tag_id")
	public int getTagId() {
		return tagId;
	}


	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	@Column(name="t_endtime")
	public Date gettEndTime() {
		return tEndTime;
	}


	public void settEndTime(Date tEndTime) {
		this.tEndTime = tEndTime;
	}

	@Column(name="t_imgurl")
	public String gettImgUrl() {
		return tImgUrl;
	}


	public void settImgUrl(String tImgUrl) {
		this.tImgUrl = tImgUrl;
	}


	@Column(name="t_accept_time")
	public Date gettAcceptTime() {
		return tAcceptTime;
	}


	public void settAcceptTime(Date tAcceptTime) {
		this.tAcceptTime = tAcceptTime;
	}

	@Column(name="t_finish_time")
	public Date gettFinishTime() {
		return tFinishTime;
	}


	public void settFinishTime(Date tFinishTime) {
		this.tFinishTime = tFinishTime;
	}

	
//	
//	@ManyToOne
//	@JoinColumn(name="u_id_send")
//	public User getUser() {
//		return user;
//	}
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//

	@Override
	public String toString() {
		return "Task [tId=" + tId + ", uTime=" + uTime + ", tcId=" + tcId + ", tDesc=" + tDesc + ", tCoinCount="
				+ tCoinCount + ", tState=" + tState + ", uIdAccept=" + uIdAccept + ", tagId=" + tagId + ", tEndTime="
				+ tEndTime + ", tImgUrl=" + tImgUrl + ", tAcceptTime=" + tAcceptTime + ", tFinishTime=" + tFinishTime
				+ "]";
	}


	




}
