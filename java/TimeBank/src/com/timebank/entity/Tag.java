package com.timebank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.timebank.bean.TagBean;

@Entity
@Table(name="tag")
public class Tag {
	private int tagId;
	private String tagText;
	
	public Tag() {
		
	}

	public Tag(int tagId, String tagText) {
		
		this.tagId = tagId;
		this.tagText = tagText;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tag_id")
	public int getTagId() {
		return tagId;
	}
	@Column(name="tag_text")
	public String getTagText() {
		return tagText;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tagId;
		result = prime * result + ((tagText == null) ? 0 : tagText.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TagBean [tagId=" + tagId + ", tagText=" + tagText + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagId != other.tagId)
			return false;
		if (tagText == null) {
			if (other.tagText != null)
				return false;
		} else if (!tagText.equals(other.tagText))
			return false;
		return true;
	}
	
	
	
}
