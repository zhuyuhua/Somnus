package com.somnus.activiti.syn;

import com.somnus.activiti.entity.SomnusRole;
import com.somnus.activiti.entity.SomnusUser;

/**
 * 维护Activiti用户、角色、权限接口
 *
 * @author ZHUYUHUA
 * @version 0.0.1
 */
public interface AccountSynService {

	/**
	 * 
	 * 同步数据到Activiti Identify模块
	 */
	public void synSaveUser(SomnusUser somnusUser, String roleid);

	/**
	 * 同步数据到Activit Group模块
	 */
	public void synSaveRole(SomnusRole role);

	/**
	 * 同步用户、角色数据到工作流
	 * 
	 * @throws Exception
	 */
	public void synAllUserAndRoleToActiviti() throws Exception;

	/**
	 * 
	 * 删除工作流引擎Activiti的用户、角色以及关系
	 */
	public void deleteAllActivitiIdentifyData() throws Exception;

	/**
	 * 删除activiti用户
	 */
	public void synDeleteUser(String userId);

	/**
	 * 删除activiti group
	 */
	public void synDeleteRole(String roleId);

}
