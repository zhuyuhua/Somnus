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
package com.somnus.common.bean;

import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.common.exception.InstanceBeanNotFoundException;

/**
 * TODO
 * 
 * @author zhuyuhua 2015年7月16日
 * @version 0.0.1
 */
public class InstanceBeanLocatorProvider implements InstanceBeanLocator {

	private static Logger logger = LoggerFactory.getLogger(InstanceBeanLocatorProvider.class);

	private InstanceBeanProvider instanceBeanProvider;

	public InstanceBeanLocatorProvider(InstanceBeanProvider provider) {
		this.instanceBeanProvider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aurora.common.bean.InstanceBeanLocator#getInstance(java.lang.Class)
	 */
	@Override
	public <T> T getInstance(Class<T> beanType) {
		try {
			return instanceBeanProvider.getInstance(beanType);
		} catch (InstanceBeanNotFoundException e) {
			logger.warn("InstanceBeanProvider cannot found bean of type {}", beanType);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aurora.common.bean.InstanceBeanLocator#getInstance(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public <T> T getInstance(Class<T> beanType, String beanName) {
		try {
			return instanceBeanProvider.getInstance(beanType, beanName);
		} catch (InstanceBeanNotFoundException e) {
			logger.warn("InstanceBeanProvider cannot found bean '{}' of type {}", beanName, beanType);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aurora.common.bean.InstanceBeanLocator#getInstance(java.lang.Class,
	 * java.lang.Class)
	 */
	@Override
	public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

}
