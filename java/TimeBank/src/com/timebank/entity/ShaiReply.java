package com.timebank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shaishai_comment")
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="r_id")
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	@Column(name="c_comment_time")
	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	@Column(name="c_comment_content")
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	@Column(name="u_id_comment")
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@ManyToOne
	@JoinColumn(name="s_id")
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
