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

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象实体类，是所有领域实体的基类。
 * 
 * @author zhuyuhua 2015年7月15日
 * @version 0.0.1
 */
@MappedSuperclass
public abstract class SomnusBaseEntity implements Entity {

	private static final long serialVersionUID = 2373234990095403553L;

	private static Logger logger = LoggerFactory
			.getLogger(SomnusBaseEntity.class);

	private static EntityRepository repository;

	private static String REPOSITORY = "repository";

	/**
	 * 获取仓储对象实例。如果尚未拥有仓储实例则通过InstanceFactory向IoC容器获取一个。
	 * 
	 * @return 仓储对象实例
	 */
	public static EntityRepository getRepository() {
		if (repository == null) {
			repository = InstanceBeanFactory.getInstance(
					EntityRepository.class, REPOSITORY);
		}
		return repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aurora.common.domain.Entity#getId()
	 */
	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aurora.common.domain.Entity#existed()
	 */
	@Override
	public boolean existed() {
		Object id = getId();
		if (id == null) {
			return false;
		}
		if (id instanceof Number && ((Number) id).intValue() == 0) {
			return false;
		}
		return getRepository().exists(getClass(), getId());
	}

}
