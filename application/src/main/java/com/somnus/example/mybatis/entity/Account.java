/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.example.mybatis.entity;


/**
 * TODO
 * @author:zhuyuhua
 * @date:2015年3月26日 下午2:37:32
 * @version 0.0.1
 */
public class Account {

	private long balance;

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		System.out.println("--setBalance--");
		this.balance = balance;
	}

	public void show() {
		balance = 100;
		System.out.println(balance);
	}

	public static void main(String[] args) {
		Account account = new Account();
		account.show();
	}

}
