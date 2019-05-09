package com.timebank.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag {
	private int tagId;
	private String tagText;
	private transient List<Discuss> discuss;
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tag(int tagId, String tagText) {
		super();
		this.tagId = tagId;
		this.tagText = tagText;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tag_id")
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	@Column(name="tag_text")
	public String getTagText() {
		return tagText;
	}
	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
	
	@OneToMany(mappedBy="tag",targetEntity=Discuss.class,
			cascade=CascadeType.ALL)
	public List<Discuss> getDiscuss() {
		return discuss;
	}
	public void setDiscuss(List<Discuss> discuss) {
		this.discuss = discuss;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagText=" + tagText + "]";
	}
	
	
	
	
	

}
