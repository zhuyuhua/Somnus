package com.somnus.activiti;

import static org.junit.Assert.assertNotNull;

import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 * @since 2015年11月18日 下午7:07:17
 */
public class ProcessEngineTestCase extends BaseActivitiTestCase {

	private static Logger logger = LoggerFactory
			.getLogger(ProcessEngineTestCase.class);

	@Autowired
	private ProcessEngineFactoryBean engine;

	// @Test
	public void testProcessEngines() {
		assertNotNull(engine);
	}
}
