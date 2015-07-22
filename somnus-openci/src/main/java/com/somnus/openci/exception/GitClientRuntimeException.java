/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.openci.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class GitClientRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 9094674164922576403L;

	private static Logger logger = LoggerFactory.getLogger(GitClientRuntimeException.class);

	public GitClientRuntimeException() {
		super();
	}

	public GitClientRuntimeException(Throwable cause) {
		super(cause);
	}

	public GitClientRuntimeException(String message) {
		super(message);
	}

	public GitClientRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
