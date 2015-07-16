/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.cache;

/**
 * 表明指定name在缓存配置文件中不存在
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class UnExistsedCacheNameException extends RuntimeException {

	public UnExistsedCacheNameException() {
		super();
	}

	public UnExistsedCacheNameException(String message) {
		super(message);
	}

	public UnExistsedCacheNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnExistsedCacheNameException(Throwable cause) {
		super(cause);
	}
}
