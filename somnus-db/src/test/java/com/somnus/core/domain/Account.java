/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.core.domain;

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
