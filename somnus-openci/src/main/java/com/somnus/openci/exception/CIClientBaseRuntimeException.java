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
package com.somnus.openci.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * CI基础异常类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class CIClientBaseRuntimeException extends RuntimeException {


	private static final long serialVersionUID = 1731558957987931215L;
	
	private static Logger logger = LoggerFactory.getLogger(CIClientBaseRuntimeException.class);

	/**
	 * @param msg
	 */
	public CIClientBaseRuntimeException(String msg) {
		super(msg);
	}

	/**
	 * @param string
	 * @param e
	 */
	public CIClientBaseRuntimeException(String msg, Throwable e) {
		super(msg, e);
	}
}
