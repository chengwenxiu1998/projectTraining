package com.timebank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.timebank.bean.UserBean;

@Entity
@Table(name="users")
public class Users {
	private int uId;
	private String uName;
	private String uPhone;
	private byte uSex;
	private String uArea;
	private String uNickName;
	private String uImage;
	private String uPassword;
	private String uIdCard;
	private int uCoin;
	public Users() {
	
	}
	
	public Users(int uId, String uName, String uPhone, byte uSex, String uArea, String uNickName, String uImage,
			String uPassword, String uIdCard, int uCoin) {
		super();
		this.uId = uId;
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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="u_id")
	public int getuId() {
		return uId;
	}
	@Column(name="u_name")
	public String getuName() {
		return uName;
	}
	@Column(name="u_phone")
	public String getuPhone() {
		return uPhone;
	}
	@Column(name="u_sex")
	public byte getuSex() {
		return uSex;
	}
	@Column(name="u_area")
	public String getuArea() {
		return uArea;
	}
	@Column(name="u_nickname")
	public String getuNickName() {
		return uNickName;
	}
	@Column(name="u_image")
	public String getuImage() {
		return uImage;
	}
	@Column(name="u_password")
	public String getuPassword() {
		return uPassword;
	}
	@Column(name="u_id_card")
	public String getuIdCard() {
		return uIdCard;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public void setuSex(byte uSex) {
		this.uSex = uSex;
	}
	public void setuArea(String uArea) {
		this.uArea = uArea;
	}
	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}
	public void setuImage(String uImage) {
		this.uImage = uImage;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public void setuIdCard(String uIdCard) {
		this.uIdCard = uIdCard;
	}
	
	@Column(name="u_coin")
	public int getuCoin() {
		return uCoin;
	}

	public void setuCoin(int uCoin) {
		this.uCoin = uCoin;
	}

	@Override
	public String toString() {
		return "UserBean [uId=" + uId + ", uName=" + uName + ", uPhone=" + uPhone + ", uSex=" + uSex + ", uArea="
				+ uArea + ", uNickName=" + uNickName + ", uImage=" + uImage + ", uPassword=" + uPassword + ", uIdCard="
				+ uIdCard + ", uCoin=" + uCoin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uArea == null) ? 0 : uArea.hashCode());
		result = prime * result + uCoin;
		result = prime * result + uId;
		result = prime * result + ((uIdCard == null) ? 0 : uIdCard.hashCode());
		result = prime * result + ((uImage == null) ? 0 : uImage.hashCode());
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
		result = prime * result + ((uNickName == null) ? 0 : uNickName.hashCode());
		result = prime * result + ((uPassword == null) ? 0 : uPassword.hashCode());
		result = prime * result + ((uPhone == null) ? 0 : uPhone.hashCode());
		result = prime * result + uSex;
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
		Users other = (Users) obj;
		if (uArea == null) {
			if (other.uArea != null)
				return false;
		} else if (!uArea.equals(other.uArea))
			return false;
		if (uCoin != other.uCoin)
			return false;
		if (uId != other.uId)
			return false;
		if (uIdCard == null) {
			if (other.uIdCard != null)
				return false;
		} else if (!uIdCard.equals(other.uIdCard))
			return false;
		if (uImage == null) {
			if (other.uImage != null)
				return false;
		} else if (!uImage.equals(other.uImage))
			return false;
		if (uName == null) {
			if (other.uName != null)
				return false;
		} else if (!uName.equals(other.uName))
			return false;
		if (uNickName == null) {
			if (other.uNickName != null)
				return false;
		} else if (!uNickName.equals(other.uNickName))
			return false;
		if (uPassword == null) {
			if (other.uPassword != null)
				return false;
		} else if (!uPassword.equals(other.uPassword))
			return false;
		if (uPhone == null) {
			if (other.uPhone != null)
				return false;
		} else if (!uPhone.equals(other.uPhone))
			return false;
		if (uSex != other.uSex)
			return false;
		return true;
	}

	

}
