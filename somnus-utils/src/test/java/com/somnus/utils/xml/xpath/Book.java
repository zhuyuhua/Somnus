/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.utils.xml.xpath;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class Book {

	private String lang;

	private String title;

	private String author;

	private int year;

	private double price;

	public Book() {
	}

	public Book(String lang, String title, String author, String year, String price) {
		this.lang = lang;
		this.title = title;
		this.author = author;
		this.year = Integer.valueOf(year);
		this.price = Double.valueOf(price);
	}

	public Book(String lang, String title, String author, int year, double price) {
		this.lang = lang;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = Integer.valueOf(year);
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = Double.valueOf(price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object object) {
		Book b = (Book) object;
		if (this.lang.equals(b.getLang()) && this.title.equals(b.getTitle()) && this.author.equals(b.getAuthor())
				&& this.year == b.getYear() && this.price == b.price) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("");
		buf.append("Book{\n").append("  lang=").append(lang).append("\n  title=").append(title).append("\n  author=")
				.append(author).append("\n  year=").append(year).append("\n  price=").append(price).append("\n}");
		return buf.toString();
	}
}
