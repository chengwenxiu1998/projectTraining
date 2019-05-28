package com.timebank.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="discuss")
public class Discuss {
	private int dId;
	private String dTopicCoutent;
	//一对一的关系
	private Tag tag;
	private User user;
	public Discuss() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Discuss(int dId, String dTopicCoutent) {
		super();
		this.dId = dId;
		this.dTopicCoutent = dTopicCoutent;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="d_id")
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	@Column(name="d_topic_content")
	public String getdTopicCoutent() {
		return dTopicCoutent;
	}
	public void setdTopicCoutent(String dTopicCoutent) {
		this.dTopicCoutent = dTopicCoutent;
	}
	
	@ManyToOne
	@JoinColumn(name="tag_id")
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	@ManyToOne
	@JoinColumn(name="u_id_louzhu")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Discuss [dId=" + dId + ", dTopicCoutent=" + dTopicCoutent + "]";
	}
	
	

}
