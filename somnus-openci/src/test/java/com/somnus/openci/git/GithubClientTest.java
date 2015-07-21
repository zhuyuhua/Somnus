/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.openci.git;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class GithubClientTest {

	private static Logger logger = LoggerFactory.getLogger(GithubClientTest.class);

	private String account = "zhuyuhua";

	private String pwd = "yuin182551314";

	private String repository = "new-repository11";

	private String otherAccount = "foreverosstest1";

	@Test
	public void test() {
		GithubClient client = new GithubClient(account, pwd);

		Assert.assertTrue(client.authenticate());

	}
}
