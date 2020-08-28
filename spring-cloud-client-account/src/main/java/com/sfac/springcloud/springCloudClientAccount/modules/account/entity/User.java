package com.sfac.springcloud.springCloudClientAccount.modules.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户类
 * 
 * @author: HymanHu
 * @date: 2019年11月28日
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String password;
	private String userImg;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createDate;
	private String accountName;
	private boolean rememberMe;

	private List<City> cities;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
