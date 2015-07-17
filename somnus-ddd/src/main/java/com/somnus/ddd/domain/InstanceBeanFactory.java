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
package com.somnus.ddd.domain;

import java.util.ArrayList;
import java.util.List;

import com.somnus.common.bean.InstanceBeanLocator;
import com.somnus.common.exception.InstanceBeanNotFoundException;

/**
 * TODO
 * 
 * @author zhuyuhua 2015年7月15日
 * @version 0.0.1
 */
public class InstanceBeanFactory {

	private static List<InstanceBeanLocator> instanceBeanLocators = new ArrayList<>();

	/**
	 * 根据类型和名称获取对象实例。
	 * 
	 * @return EntityRepository
	 * @throws InstanceBeanNotFoundException
	 * @since 0.0.1
	 */
	public static <T> T getInstance(Class<T> beanType, String beanName) {

		for (InstanceBeanLocator locator : instanceBeanLocators) {
			T instance = locator.getInstance(beanType, beanName);
			if (instance != null) {
				return instance;
			}
		}
		throw new InstanceBeanNotFoundException(
				"No bean with beanType:" + beanType + " and beanName:" + beanName + " in IoC container!");
	}

}
