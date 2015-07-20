/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.openci.git;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * github客户端
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class GithubClient {

	private static Logger logger = LoggerFactory.getLogger(GithubClient.class);

	private GitHub github = null;

	private String username;

	public GithubClient(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new GitClientRuntimeException("username or password must not bet empty");
		}

		try {
			this.username = username;
			github = GitHub.connectUsingPassword(username, password);
		} catch (IOException e) {
			// 因为目前只是设置参数，github未实际连接，所以，不需要处理
			throw new GitClientRuntimeException("Unknown exception", e);
		}
	}

	/**
	 * 认证
	 */
	public boolean authenticate() {
		try {
			return github.isCredentialValid();
		} catch (IOException e) {
			return false;
		}
	}
}
