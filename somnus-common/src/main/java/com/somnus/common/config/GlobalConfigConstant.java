/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public final class GlobalConfigConstant {

	private static Logger logger = LoggerFactory.getLogger(GlobalConfigConstant.class);

	public static String CLASS_PATH = GlobalConfigConstant.class.getResource("/").getPath();

	public static void main(String[] args) {
		System.out.println(CLASS_PATH);


	}
}
