/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public class Attribute {

	private static Logger logger = LoggerFactory.getLogger(Attribute.class);
	private String name;
	private String value;
	private long timeStamp;
	public Attribute() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	

	public Attribute(String name, String value, long timeStamp) {
		this.name = name;
		this.value = value;
		this.timeStamp = timeStamp;
	}


	/**
	 * timeStamp
	 *
	 * @return  the timeStamp
	 * @since   1.0.0
	 */
	
	public long getTimeStamp() {
		return timeStamp;
	}


	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}


	/**
	 * logger
	 *
	 * @return  the logger
	 * @since   1.0.0
	 */
	
	public static Logger getLogger() {
		return logger;
	}
	/**
	 * @param logger the logger to set
	 */
	public static void setLogger(Logger logger) {
		Attribute.logger = logger;
	}
	/**
	 * name
	 *
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * value
	 *
	 * @return  the value
	 * @since   1.0.0
	 */
	
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/* (non-Javadoc)
	 * @see java.lang.String#toString()
	 */
	@Override
	public String toString() {
		return "Attribute [name=" + name + ", value=" + value + "]";
	}
	
}
