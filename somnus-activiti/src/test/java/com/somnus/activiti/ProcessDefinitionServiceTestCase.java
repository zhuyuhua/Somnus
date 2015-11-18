package com.somnus.activiti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 * @since 2015年11月18日 下午7:16:18
 */
public class ProcessDefinitionServiceTestCase extends BaseActivitiTestCase {

	private static Logger logger = LoggerFactory
			.getLogger(ProcessDefinitionServiceTestCase.class);

	static String filePath = ProcessDefinitionServiceTestCase.class
			.getResource("/").getPath() + "activiti/deployments/leave.bar";

}
