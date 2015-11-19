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
package com.pingan.mybatis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.somnus.mybatis.DefaultComponent;
import com.somnus.mybatis.DefaultRepository;
import com.somnus.mybatis.DefaultService;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
public class TestService extends SpringBaseTestCase {

	private static final Logger logger = LogManager
			.getLogger(DefaultService.class);

	@Autowired
	DefaultService defaultService;

	@Autowired
	DefaultComponent defaultComponent;

	@Autowired
	DefaultRepository defaultRepository;

	@Test
	public void testService() {
		logger.debug(defaultComponent);
		logger.debug(defaultService);
		logger.debug(defaultRepository);
	}

	// @Test
	// public void testSaveUser() {
	// User user = new User();
	// SecureRandom random = new SecureRandom();
	// user.setId(random.nextInt());
	// user.setName("name:101");
	// defaultRepository.saveUser(user);
	//
	// logger.debug(defaultRepository.getUserById(user));
	// }

}
