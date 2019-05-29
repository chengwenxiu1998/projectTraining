package com.timebank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="discuss_reply")
public class DiscussReply {
	private Integer id;
	private Integer uId;
	private String replyContent;
	private String replyTime;
	//与discuss的关系是多对一（discuss)
	private Discuss discuss;
	public DiscussReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscussReply(Integer uId, String replyContent, String replyTime) {
		super();
		this.uId = uId;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="u_id_reply")
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	@Column(name="dr_reply_content")
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	@Column(name="dr_reply_time")
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	@ManyToOne
	@JoinColumn(name="d_id")
	public Discuss getDiscuss() {
		return discuss;
	}
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}
	@Override
	public String toString() {
		return "DiscussReply [id=" + id + ", uId=" + uId + ", replyContent=" + replyContent + ", replyTime=" + replyTime
				+ ", discuss=" + discuss + "]";
	}
	
	
	 
	

}
