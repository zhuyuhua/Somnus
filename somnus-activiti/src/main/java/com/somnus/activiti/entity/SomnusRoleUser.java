package com.somnus.activiti.entity;

/**
 * 角色用户关系类
 *
 * @author ZHUYUHUA
 * @version 0.0.1
 */
public class SomnusRoleUser extends IdEntity {

	private static final long serialVersionUID = 5368515600768252852L;

	private SomnusUser somnusUser;

	private SomnusRole somnusRole;

	public SomnusUser getTSUser() {
		return somnusUser;
	}

	public void setTSUser(SomnusUser somnusUser) {
		this.somnusUser = somnusUser;
	}

	public SomnusRole getTSRole() {
		return this.somnusRole;
	}

	public void setTSRole(SomnusRole somnusRole) {
		this.somnusRole = somnusRole;
	}

}
