package com.somnus.activiti.service;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author ZHUYUHUA
 * @version 0.0.1
 */
@Component("processInstance")
public class ProcessInstanceService extends BaseActivitiService {

	private static Logger logger = LoggerFactory
			.getLogger(ProcessInstanceService.class);

	/**
	 * 查找所有流程实例
	 */
	public List<ProcessInstance> queryProcessInstances() {

		List<ProcessInstance> processInstanceList = runtimeService
				.createProcessInstanceQuery().list();

		for (ProcessInstance processInstance : processInstanceList) {
			logger.debug(ToStringBuilder.reflectionToString(processInstance));
		}

		return processInstanceList;
	}

}
