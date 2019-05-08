package com.timebank.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.timebank.bean.TaskBean;

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


	@Override
	public String toString() {
		return "TaskBean [tId=" + tId + ", uIdSend=" + uIdSend + ", uTime=" + uTime + ", tcId=" + tcId + ", tDesc="
				+ tDesc + ", tCoinCount=" + tCoinCount + ", tState=" + tState + ", uIdAccept=" + uIdAccept + ", tagId="
				+ tagId + ", tEndTime=" + tEndTime + ", tImgUrl=" + tImgUrl + ", tAcceptTime=" + tAcceptTime
				+ ", tFinishTime=" + tFinishTime + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tAcceptTime == null) ? 0 : tAcceptTime.hashCode());
		result = prime * result + tCoinCount;
		result = prime * result + ((tDesc == null) ? 0 : tDesc.hashCode());
		result = prime * result + ((tEndTime == null) ? 0 : tEndTime.hashCode());
		result = prime * result + ((tFinishTime == null) ? 0 : tFinishTime.hashCode());
		result = prime * result + tId;
		result = prime * result + ((tImgUrl == null) ? 0 : tImgUrl.hashCode());
		result = prime * result + ((tState == null) ? 0 : tState.hashCode());
		result = prime * result + tagId;
		result = prime * result + tcId;
		result = prime * result + uIdAccept;
		result = prime * result + uIdSend;
		result = prime * result + ((uTime == null) ? 0 : uTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (tAcceptTime == null) {
			if (other.tAcceptTime != null)
				return false;
		} else if (!tAcceptTime.equals(other.tAcceptTime))
			return false;
		if (tCoinCount != other.tCoinCount)
			return false;
		if (tDesc == null) {
			if (other.tDesc != null)
				return false;
		} else if (!tDesc.equals(other.tDesc))
			return false;
		if (tEndTime == null) {
			if (other.tEndTime != null)
				return false;
		} else if (!tEndTime.equals(other.tEndTime))
			return false;
		if (tFinishTime == null) {
			if (other.tFinishTime != null)
				return false;
		} else if (!tFinishTime.equals(other.tFinishTime))
			return false;
		if (tId != other.tId)
			return false;
		if (tImgUrl == null) {
			if (other.tImgUrl != null)
				return false;
		} else if (!tImgUrl.equals(other.tImgUrl))
			return false;
		if (tState == null) {
			if (other.tState != null)
				return false;
		} else if (!tState.equals(other.tState))
			return false;
		if (tagId != other.tagId)
			return false;
		if (tcId != other.tcId)
			return false;
		if (uIdAccept != other.uIdAccept)
			return false;
		if (uIdSend != other.uIdSend)
			return false;
		if (uTime == null) {
			if (other.uTime != null)
				return false;
		} else if (!uTime.equals(other.uTime))
			return false;
		return true;
	}


	public Task(int tId, int uIdSend, Date uTime, int tcId, String tDesc, int tCoinCount, String tState,
			int uIdAccept, int tagId, Date tEndTime, String tImgUrl, Date tAcceptTime, Date tFinishTime) {
		super();
		this.tId = tId;
		this.uIdSend = uIdSend;
		this.uTime = uTime;
		this.tcId = tcId;
		this.tDesc = tDesc;
		this.tCoinCount = tCoinCount;
		this.tState = tState;
		this.uIdAccept = uIdAccept;
		this.tagId = tagId;
		this.tEndTime = tEndTime;
		this.tImgUrl = tImgUrl;
		this.tAcceptTime = tAcceptTime;
		this.tFinishTime = tFinishTime;
	}
	public Task( int uIdSend, Date uTime, int tcId, String tDesc, int tCoinCount, String tState,
			int uIdAccept, int tagId, Date tEndTime, String tImgUrl, Date tAcceptTime, Date tFinishTime) {
		super();
		
		this.uIdSend = uIdSend;
		this.uTime = uTime;
		this.tcId = tcId;
		this.tDesc = tDesc;
		this.tCoinCount = tCoinCount;
		this.tState = tState;
		this.uIdAccept = uIdAccept;
		this.tagId = tagId;
		this.tEndTime = tEndTime;
		this.tImgUrl = tImgUrl;
		this.tAcceptTime = tAcceptTime;
		this.tFinishTime = tFinishTime;
	}


}
