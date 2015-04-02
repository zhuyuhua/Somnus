/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis.entity;


/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 上午11:50:31
 * @version 0.0.1
 */
public class User {

	private int uid;
	private String userName;
	private String password;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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


	@Override
	public String toString() {
		return "uId: " + this.uid + ", Name: " + this.userName + ",Pass: "
				+ this.password;
	}
}
