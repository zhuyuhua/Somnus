package com.somnus.activiti.syn.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somnus.activiti.entity.SomnusRole;
import com.somnus.activiti.entity.SomnusRoleUser;
import com.somnus.activiti.entity.SomnusUser;
import com.somnus.activiti.syn.AccountSynService;

/**
 * TODO
 *
 * @author ZHUYUHUA
 * @version 0.0.1
 */
@Service
@Transactional
public class AccountSynServiceImpl implements AccountSynService {

	private static Logger logger = LoggerFactory
			.getLogger(AccountSynServiceImpl.class);

	@Autowired
	private IdentityService identityService;

	/**
	 * 添加Activiti Identify的用户于组关系
	 * 
	 * @param roleIds
	 *            角色ID集合
	 * @param userId
	 *            用户ID
	 */
	private void addMembershipToIdentify(List<String> roleIds, String userId) {
		for (String roleId : roleIds) {
			logger.debug("add role to activit,userid=" + userId + ",roleId="
					+ roleId);
			identityService.createMembership(userId, roleId);
		}
	}

	/**
	 * 使用系统用户对象属性更新到Activiti User对象中
	 * 
	 * @param user
	 *            系统用户对象
	 * @param activitiUser
	 *            Activiti User
	 */
	private void cloneAndSaveActivitiUser(SomnusUser user, User activitiUser) {
		activitiUser.setFirstName(user.getUserName());
		activitiUser.setLastName(user.getUserName());
		activitiUser.setPassword(user.getPassword());
		activitiUser.setEmail(user.getEmail());
		identityService.saveUser(activitiUser);
	}

	@Override
	public void deleteAllActivitiIdentifyData() throws Exception {
		deleteAllMemerShip();
		deleteAllRole();
		deleteAllUser();

	}

	/**
	 * 删除用户和组的关系
	 */
	public void deleteAllMemerShip() {
		String sql = "delete from ACT_ID_MEMBERSHIP";
		logger.debug("deleted from activiti membership.");
	}

	/**
	 * 删除用户和组的关系
	 */
	public void deleteAllRole() {
		String sql = "delete from ACT_ID_GROUP";
		logger.debug("deleted from activiti group.");
	}

	/**
	 * 删除用户和组的关系
	 */
	public void deleteAllUser() {
		String sql = "delete from ACT_ID_USER";
		logger.debug("deleted from activiti user.");
	}

	/**
	 * 添加工作流用户以及角色
	 * 
	 * @param user
	 *            用户对象{@link User}
	 * @param roleIds
	 *            用户拥有的角色ID集合
	 */
	private void newActivitiUser(SomnusUser user, List<String> roleIds) {

		String userId = user.getId();

		// 添加用户
		saveActivitiUser(user);

		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}

	/**
	 * 添加一个用户到Activiti
	 * 
	 * @param user
	 *            用户对象
	 */
	private void saveActivitiUser(SomnusUser user) {
		User activitiUser = identityService.newUser(user.getId());
		cloneAndSaveActivitiUser(user, activitiUser);
	}

	/**
	 * 同步用户、角色数据到工作流
	 * 
	 * @throws Exception
	 */
	@Override
	public void synAllUserAndRoleToActiviti() throws Exception {

		// 清空工作流用户、角色以及关系
		deleteAllActivitiIdentifyData();

		// 复制角色数据
		synRoleToActiviti();

		// 复制用户以及关系数据
		synUserWithRoleToActiviti();

	}

	@Override
	public void synDeleteUser(String userId) {

		// 删除Activiti membership
		deleteMembershipUserId(userId);

		// 同步删除Activiti User
		identityService.deleteUser(userId);

	}

	@Override
	public void synDeleteRole(String roleId) {
		// 删除Activiti membership
		deleteMembershipByRoleId(roleId);

		// 同步删除Activiti User
		identityService.deleteGroup(roleId);
	}

	/**
	 * 同步所有角色数据到{@link Group}
	 */
	private void synRoleToActiviti() {

		List<SomnusRole> allRole = new ArrayList<SomnusRole>();

		for (SomnusRole role : allRole) {

			String groupId = role.getId();
			Group group = identityService.newGroup(groupId);
			group.setName(role.getRoleName());
			group.setType(role.getRoleCode());

			identityService.saveGroup(group);

		}
	}

	/**
	 * 
	 * 同步数据到Activiti Identify模块
	 */
	@Override
	public void synSaveUser(SomnusUser user, String roleIdStr) {

		UserQuery userQuery = identityService.createUserQuery();
		List<User> activitiUsers = userQuery.userId(user.getId()).list();
		List<String> roleIds = converStrToLis(roleIdStr);
		if (activitiUsers.size() == 1) {
			updateActivitiUser(user, roleIds, activitiUsers.get(0));
		} else if (activitiUsers.size() > 1) {
			String errorMsg = "发现重复用户：id=" + user.getId();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		} else {
			newActivitiUser(user, roleIds);
		}

	}

	@Override
	public void synSaveRole(SomnusRole role) {

		GroupQuery groupQuery = identityService.createGroupQuery();

		List<Group> groups = groupQuery.groupId(role.getId()).list();
		Group group;
		if (groups.size() == 1) {
			group = groups.get(0);
			group.setName(role.getRoleName());
			group.setType(role.getRoleCode());
		} else if (groups.size() > 1) {
			String errorMsg = "发现重复角色：id=" + role.getId();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		} else {
			group = identityService.newGroup(role.getId());
			group.setName(role.getRoleName());
			group.setType(role.getRoleCode());
		}
		identityService.saveGroup(group);
	}

	/**
	 * @Title:converStrToLis
	 * @param roleIdStr
	 * @return:List<String>
	 * @exception
	 * @since 1.0.0
	 */
	private List<String> converStrToLis(String roleIdStr) {
		String[] roleids = roleIdStr.split(",");
		return Arrays.asList(roleids);
	}

	public static void main(String[] args) {
		String string = null;
		List<String> list = new AccountSynServiceImpl().converStrToLis(string);
		for (String string2 : list) {
			System.out.println(string2);
		}

	}

	/**
	 * 复制用户以及关系数据
	 */
	private void synUserWithRoleToActiviti() {

		List<SomnusUser> allUser = new ArrayList<SomnusUser>();

		for (SomnusUser user : allUser) {

			String userId = user.getId();

			// 添加一个用户到Activiti
			saveActivitiUser(user);

			// 角色和用户的关系
			List<SomnusRoleUser> roleUsers = new ArrayList<SomnusRoleUser>();

			for (SomnusRoleUser tsRoleUser : roleUsers) {
				logger.debug("add membership {user: " + userId + ",role:"
						+ tsRoleUser.getTSRole().getId() + "}");
				identityService.createMembership(userId, tsRoleUser.getTSRole()
						.getId());
			}
		}
	}

	/**
	 * 更新工作流用户以及角色
	 * 
	 * @param userId
	 *            用户对象{@link User}
	 * @param roleIds
	 *            用户拥有的角色ID集合
	 * @param activitiUser
	 *            Activiti引擎的用户对象
	 */
	private void updateActivitiUser(SomnusUser user, List<String> roleIds,
			User activitiUser) {

		String userId = user.getId();

		// 更新用户主体信息
		cloneAndSaveActivitiUser(user, activitiUser);

		// 删除Activiti membership
		deleteMembershipUserId(userId);

		// 添加membership
		addMembershipToIdentify(roleIds, userId);
	}

	private void deleteMembershipUserId(String userId) {
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery()
				.groupMember(userId).list();
		for (Group group : activitiGroups) {
			identityService.deleteMembership(userId, group.getId());
		}
	}

	private void deleteMembershipByRoleId(String roleId) {
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery()
				.groupId(roleId).list();
		for (Group group : activitiGroups) {
			identityService.deleteMembership(group.getId(), roleId);
		}
	}

}
