package com.timebank.entity;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name="users")
public class User {

	private Integer uId;
	private String uName;
	private String uPhone;
	private Byte uSex;
	private String uArea;
	private String uNickName;
	private String uImage;
	private String uPassword;
	private String uIdCard;
	private Integer uCoin;
	private transient List<Discuss> discuss;
//	private Set<Task> sendTaskSet = new HashSet<>();
	public User() {
		super();
	}
	
	public User(int uid) {
		this.uId = uid;
	}
	
	public User(String uName, String uPhone, Byte uSex, String uArea, String uNickName, String uImage, String uPassword,
			String uIdCard, Integer uCoin) {
		super();
		this.uName = uName;
		this.uPhone = uPhone;
		this.uSex = uSex;
		this.uArea = uArea;
		this.uNickName = uNickName;
		this.uImage = uImage;
		this.uPassword = uPassword;
		this.uIdCard = uIdCard;
		this.uCoin = uCoin;
	}
	
	public User(String petName, String phone, String password) {
		this.uPhone = phone;
		this.uNickName = petName;
		this.uPassword = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	@Column(name="u_name")
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	@Column(name="u_phone")
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	@Column(name="u_sex")
	public Byte getuSex() {
		return uSex;
	}
	public void setuSex(Byte uSex) {
		this.uSex = uSex;
	}
	@Column(name="u_area")
	public String getuArea() {
		return uArea;
	}
	public void setuArea(String uArea) {
		this.uArea = uArea;
	}
	@Column(name="u_nickname")
	public String getuNickName() {
		return uNickName;
	}
	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}
	@Column(name="u_image")
	public String getuImage() {
		return uImage;
	}
	public void setuImage(String uImage) {
		this.uImage = uImage;
	}
	@Column(name="u_password")
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	@Column(name="u_id_card")
	public String getuIdCard() {
		return uIdCard;
	}
	public void setuIdCard(String uIdCard) {
		this.uIdCard = uIdCard;
	}
	@Column(name="u_coin")
	public Integer getuCoin() {
		return uCoin;
	}
	public void setuCoin(Integer uCoin) {
		this.uCoin = uCoin;
	}
	
	
	@OneToMany(mappedBy="user",targetEntity=Discuss.class,
			cascade=CascadeType.ALL)
	public List<Discuss> getDiscuss() {
		return discuss;
	}
	public void setDiscuss(List<Discuss> discuss) {
		this.discuss = discuss;
	}
		
	@OneToMany(mappedBy="user", targetEntity=Task.class, 
	        cascade=CascadeType.ALL)
//	public Set<Task> getSendTaskSet() {
//		return sendTaskSet;
//	}
//	public void setSendTaskSet(Set<Task> sendTaskSet) {
//		this.sendTaskSet = sendTaskSet;
//	}
	
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPhone=" + uPhone + ", uSex=" + uSex + ", uArea=" + uArea
				+ ", uNickName=" + uNickName + ", uImage=" + uImage + ", uPassword=" + uPassword + ", uIdCard="
				+ uIdCard + ", uCoin=" + uCoin + "]";
	}
	
	
	
}
