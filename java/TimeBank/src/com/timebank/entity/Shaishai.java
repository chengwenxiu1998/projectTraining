package com.timebank.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="shaishai")
public class Shaishai {
	private Integer sid;
	private String stext;
	private String stime;
	private Integer scount;
	private User user;
	private List<ShaiReply> shaiReplys;
	public Shaishai() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shaishai(String stext, String stime, Integer scount) {
		super();
		this.stext = stext;
		this.stime = stime;
		this.scount = scount;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="s_id")
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	@Column(name="s_text")
	public String getStext() {
		return stext;
	}
	public void setStext(String stext) {
		this.stext = stext;
	}
	@Column(name="s_time")
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	@Column(name="s_count")
	public Integer getScount() {
		return scount;
	}
	public void setScount(Integer scount) {
		this.scount = scount;
	}
	@ManyToOne
	@JoinColumn(name="u_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(mappedBy="shaishai",targetEntity=ShaiReply.class,
			cascade=CascadeType.ALL)
	public List<ShaiReply> getShaiReplys() {
		return shaiReplys;
	}
	public void setShaiReplys(List<ShaiReply> shaiReplys) {
		this.shaiReplys = shaiReplys;
	}
	@Override
	public String toString() {
		return "Shaishai [sid=" + sid + ", stext=" + stext + ", stime=" + stime + ", scount=" + scount + ", user="
				+ user + "]";
	}
	
	
	

}
