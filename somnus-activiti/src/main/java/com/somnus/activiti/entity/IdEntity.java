package com.somnus.activiti.entity;

import java.io.Serializable;


/**
 * TODO
 *
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public abstract class IdEntity implements Serializable{

	private static final long serialVersionUID = 1236226180602179283L;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
