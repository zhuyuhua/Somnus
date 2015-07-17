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

/**
 * 用于定位Bean实例
 * 
 * @author zhuyuhua 2015年7月16日
 * @version 0.0.1
 */
public interface InstanceBeanLocator {
	public abstract <T> T getInstance(Class<T> beanType);

	public abstract <T> T getInstance(Class<T> beanType, String beanName);

	public abstract <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType);
}
