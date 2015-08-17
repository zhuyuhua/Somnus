/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
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
 * @author zhuyuhua
 * @version 0.0.1
 */

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(locations = { "classpath*:/springcfg/applicationContext.xml" })
public abstract class SpringTransactionalTestCase extends AbstractJUnit4SpringContextTests {

	// protected DataSource dataSource;
	//
	// @Override
	// @Autowired
	// public void setDataSource(DataSource dataSource) {
	// super.setDataSource(dataSource);
	// this.dataSource = dataSource;
	// }
}
