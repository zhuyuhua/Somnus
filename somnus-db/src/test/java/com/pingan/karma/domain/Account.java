/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class Account {


	private int id;
	private String name;
	private Short age;
	private Double balance;
	private Double credit;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * logger
	 *
	 * @return the logger
	 * @since 1.0.0
	 */



	/**
	 * id
	 *
	 * @return the id
	 * @since 1.0.0
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * name
	 *
	 * @return the name
	 * @since 1.0.0
	 */

	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * age
	 *
	 * @return the age
	 * @since 1.0.0
	 */

	public Short getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Short age) {
		this.age = age;
	}

	/**
	 * balance
	 *
	 * @return the balance
	 * @since 1.0.0
	 */

	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/**
	 * credit
	 *
	 * @return the credit
	 * @since 1.0.0
	 */

	public Double getCredit() {
		return credit;
	}

	/**
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(Double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", age=" + age
				+ ", balance=" + balance + ", credit=" + credit + "]";
	}

}
