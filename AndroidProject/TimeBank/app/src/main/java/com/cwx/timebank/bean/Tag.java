package com.cwx.timebank.bean;

import java.util.List;

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

    public int getTagId() {
        return tagId;
    }
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagText() {
        return tagText;
    }
    public void setTagText(String tagText) {
        this.tagText = tagText;
    }


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
