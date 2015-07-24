/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.somnus.example.spring.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类. 相比Spring原基类名字更短并保存了dataSource变量.
 * 
 * 子类需要定义applicationContext文件的位置, 如:
 * 
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" })
 * 
 * @author calvin
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(locations = { "classpath*:/springcfg/applicationContext.xml" })
public abstract class SpringTransactionalTestCase extends
		AbstractJUnit4SpringContextTests {

	// protected DataSource dataSource;
	//
	// @Override
	// @Autowired
	// public void setDataSource(DataSource dataSource) {
	// super.setDataSource(dataSource);
	// this.dataSource = dataSource;
	// }
}
