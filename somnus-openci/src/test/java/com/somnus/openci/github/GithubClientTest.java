/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.openci.github;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Test;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.git.GitClient;

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
	public void test_authenticate() {
		GithubClient client = new GithubClient(account, pwd);

		assert client.authenticate();
	}

	@Test
	public void testAll() throws Exception {

		GithubClient client = new GithubClient(account, pwd);

		assert client.authenticate();

		client.createRepository(repository, "description");

		assert client.isRepositoryExist(repository);

		assert client.isAccountExist(otherAccount);

		assert("https://github.com/foreverosstest/" + repository + ".git")
				.equals(client.getHttpTransportUrl(repository));

		client.addCollaborators(repository, otherAccount);

		Field githubField = GithubClient.class.getDeclaredField("github");
		githubField.setAccessible(true);

		GitHub github = (GitHub) githubField.get(client);
		assert github.getRepository(client.getRepositoryFullName(repository)).getCollaboratorNames()
				.contains(otherAccount);

		client.removeCollaborators(repository, otherAccount);
		assert!github.getRepository(client.getRepositoryFullName(repository)).getCollaboratorNames()
				.contains(otherAccount);

		GitClient gitClient = new GitClient(account, pwd, "foreverosstest@163.com",
				GithubClientTest.class.getResource("/ProjectTest").getFile());

		gitClient.pushRepositoryToRemote(client.getHttpTransportUrl(repository));

		String downToPath = GithubClientTest.class.getResource("/").getFile();

		File downToPathFile = new File(downToPath);

		assert!new File(downToPathFile, "/" + repository).exists();

		gitClient.clone(client.getHttpTransportUrl(repository), downToPathFile);

		assert new File(downToPathFile, "/" + repository).exists();

		client.removeRepository(repository);
		assert!client.isRepositoryExist(repository);


	}
}
