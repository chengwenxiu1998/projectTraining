package com.timebank.entity;

public class UpdateTask {
	private int tId;
	private int uId;
	public UpdateTask() {
	
	}
	public UpdateTask(int tId, int uId) {
		super();
		this.tId = tId;
		this.uId = uId;
	}
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	
}
