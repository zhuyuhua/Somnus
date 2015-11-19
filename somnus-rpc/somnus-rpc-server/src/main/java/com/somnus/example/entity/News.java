package com.somnus.example.entity;

import com.somnus.protocol.annotation.Member;
import com.somnus.protocol.annotation.SomnusSerializable;

/**
 * 实体类
 *
 * @GaeaSerializable 标记当前类为需要序列化的类
 * @GaeaMember 标记该字段为需要序列化字段
 *
 * @author @author Service Platform Architecture Team (spat@58.com)
 */

@SomnusSerializable
public class News {

	@Member
	private int newsID;

	@Member
	private String title;

	public int getNewsID() {
		return newsID;
	}

	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
