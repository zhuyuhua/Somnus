/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.somnus.ddd.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua 2015年7月16日
 * @version 0.0.1
 */
public class CriteriaQuery {

	private static Logger logger = LoggerFactory.getLogger(CriteriaQuery.class);

	private EntityRepository repository;

	public <T> List<T> list() {
		return repository.find(this);
	}

}
