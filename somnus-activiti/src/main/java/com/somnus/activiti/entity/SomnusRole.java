package com.somnus.activiti.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author ZHUYUHUA
 * @version 0.0.1
 */
public class SomnusRole extends IdEntity {

	private static final long serialVersionUID = -2248807556220375720L;

	private static Logger logger = LoggerFactory.getLogger(SomnusRole.class);

	private String roleName;// 角色名称
	private String roleCode;// 角色编码

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
