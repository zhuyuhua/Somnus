package com.somnus.activiti.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 流程定义控制器
 * 
 * @author ZHUYUHUA
 * @version 0.0.1
 * @since 2015年11月18日 下午2:11:30
 */
@Component("processDefinationService")
public class ProcessDefinitionService extends BaseActivitiService {

	private static Logger logger = LoggerFactory
			.getLogger(ProcessDefinitionService.class);

	public static final String ZIP = "zip";
	public static final String BAR = "bar";

	private static final String resourceType_image = "image";
	private static final String resourceType_xml = "xml";

	/**
	 * 
	 * 查询所有流程定义
	 *
	 */
	public List<ProcessDefinition> queryProcessDefinitions() {

		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.list();

		return processDefinitionList;
	}

	/**
	 * 部署新流程
	 * 
	 */
	public void deployment(File file) {

		try {
			String fileName = file.getName();
			InputStream fileInputStream = new FileInputStream(file);

			Deployment deployment = null;

			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals(ZIP) || extension.equals(BAR)) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment()
						.addZipInputStream(zip).deploy();
			} else {
				deployment = repositoryService.createDeployment()
						.addInputStream(fileName, fileInputStream).deploy();
			}

			logger.debug("添加成功,部署的ID是" + deployment.getId());
		} catch (Exception e) {
			logger.error(
					"error on deploy process, because of file input stream", e);
		}
	}

	/**
	 * 读取资源，包括xml或者图片，通过部署ID
	 * 
	 * @param processDefinitionId
	 *            流程定义
	 * @param resourceType
	 *            资源类型(xml|image)
	 * @return
	 * @throws Exception
	 */
	public OutputStream loadByDeployment(String processDefinitionId,
			String resourceType) throws Exception {

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = null;
		if (resourceType.equals(resourceType_image)) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals(resourceType_xml)) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		OutputStream out = new ByteArrayOutputStream();
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			out.write(b, 0, len);

		}
		return out;
	}

	/**
	 * 删除部署的流程，级联删除流程实例(默认)
	 * 
	 * @param deploymentId
	 *            流程部署ID
	 */
	public void deleteDeployment(String deploymentId) {
		logger.debug("delete process by deploymentId=" + deploymentId);
		repositoryService.deleteDeployment(deploymentId, true);

	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 */
	public void deleteDeployment(String deploymentId, boolean cascade) {
		logger.debug("delete process by deploymentId=" + deploymentId);
		repositoryService.deleteDeployment(deploymentId, cascade);

	}

	/**
	 * 分页查询流程定义
	 */
	public void datagrid(int pageIndex, int pageSize) throws Exception {

		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.listPage(pageIndex, pageSize);

		double total = Double.valueOf(processDefinitionQuery.count())
				.intValue();

	}
}
