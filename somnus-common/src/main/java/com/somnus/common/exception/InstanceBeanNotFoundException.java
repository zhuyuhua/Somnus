/*
 * Copyright (c) 2010-2015. Aurora Framework
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
package com.somnus.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取在IoC容器中不存在的Bean实例时抛出此异常。
 * 
 * @author zhuyuhua 2015年7月16日
 * @version 0.0.1
 */
public class InstanceBeanNotFoundException extends IocException {

	private static final long serialVersionUID = 5597292902971084801L;

	private static Logger logger = LoggerFactory.getLogger(InstanceBeanNotFoundException.class);

	public InstanceBeanNotFoundException(String msg) {
		super(msg);
	}

}
