/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.openci.git;

import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.openci.exception.GitClientRuntimeException;

/**
 * Git客户端，用于创建项目、修改项目等
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class GitClient {

	private static Logger logger = LoggerFactory.getLogger(GitClient.class);

	private String username;
	private String password;
	private String email;
	private String localRepositoryPath;// 本地仓储地址

	/**
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param email
	 *            邮箱
	 * @param localRepositoryPath
	 *            本地路径
	 */
	public GitClient(String username, String password, String email, String localRepositoryPath) {

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)
				|| StringUtils.isEmpty(localRepositoryPath)) {
			throw new GitClientRuntimeException("username, password, email, localRepositoryPath must no be empty!");
		}

		this.username = username;
		this.password = password;
		this.email = email;
		this.localRepositoryPath = localRepositoryPath;
	}

	public void pushRepositoryToRemote(String remoteRepositoryUrl) {
		if (StringUtils.isBlank(localRepositoryPath)) {
			throw new GitClientRuntimeException("project's physicalPath must bet not null!");
		}

		try {
			PushCommand pushCommand = Git.open(new File(localRepositoryPath)).push();

			pushCommand.setRemote(remoteRepositoryUrl);

			CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);

			pushCommand.setCredentialsProvider(credentialsProvider).call();

		} catch (IOException e) {
			throw new GitClientRuntimeException("git.pushRepositoryToRemote.IOException", e);
		} catch (GitAPIException e) {
			throw new GitClientRuntimeException("git.pushRepositoryToRemote.GitAPIException", e);
		}
	}

	public void add(String filePattern) {
		Git git = null;
		try {
			git = Git.open(new File(localRepositoryPath));
			git.add().addFilepattern(filePattern).call();
		} catch (IOException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.add.IOException", e);

		} catch (NoFilepatternException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.add.NoFilepatternException", e);

		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.add.GitAPIException", e);

		}

	}

	public void commit(String msg) {
		msg = StringUtils.isEmpty(msg) ? "default msg" : msg;

		try {

			Git git = Git.open(new File(localRepositoryPath));
			git.commit().setCommitter(username, email).setMessage(msg).call();

		} catch (NoMessageException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.haveToSetMessage", e);
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.WrongRepositoryStateException", e);

		} catch (IOException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.IOException", e);

		} catch (NoFilepatternException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.NoFilepatternException", e);

		} catch (UnmergedPathsException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.UnmergedPathsException", e);

		} catch (NoHeadException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.NoHeadException", e);

		} catch (ConcurrentRefUpdateException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.ConcurrentRefUpdateException", e);

		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.commit.GitAPIException", e);
		}

	}

	/**
	 * 
	 * 初始化本地仓库
	 * 
	 * @param repositoryPhysicalPath
	 * @param remoteRepositoryUrl
	 */
	public static void init(String repositoryPhysicalPath, String remoteRepositoryUrl) {
		Repository repository = null;
		InitCommand init = new InitCommand();

		if (StringUtils.isBlank(repositoryPhysicalPath)) {
			throw new GitClientRuntimeException("project's physicalPath must bet not null!");
		}

		init.setDirectory(new File(repositoryPhysicalPath));
		init.setBare(false);
		try {
			Git git = init.call();

			repository = git.getRepository();

			git.add().addFilepattern(".").call();

			StoredConfig config = repository.getConfig();

			RemoteConfig remoteConfig = new RemoteConfig(config, "origin");

			URIish uri = new URIish(remoteRepositoryUrl);

			remoteConfig.addURI(uri);
			remoteConfig.addPushURI(uri);

			remoteConfig.update(config);
			config.save();

		} catch (URISyntaxException e) {
			throw new GitClientRuntimeException("git.init.URISyntaxException", e);
		} catch (IOException e) {
			throw new GitClientRuntimeException("git.init.IOException", e);
		} catch (GitAPIException e) {
			throw new GitClientRuntimeException("git.init.GitAPIException", e);
		}
	}

	/**
	 * 判断某文件夹是否已经init
	 *
	 * @param folderPath
	 * @return
	 */
	public static boolean isInited(String folderPath) {
		assert StringUtils.isNotEmpty(folderPath);

		File folder = new File(folderPath);
		if (!folder.isDirectory()) {
			throw new GitClientRuntimeException("It must be a folder");
		}
		if (!folder.exists()) {
			throw new GitClientRuntimeException("folder not found", new FileNotFoundException());
		}

		String[] names = folder.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return ".git".equals(name) && dir.isDirectory();
			}
		});

		return names.length != 0;
	}

	public static void clone(String repositoryPath, File downToPathFile) {
		try {
			Git.cloneRepository().setURI(repositoryPath).setDirectory(downToPathFile).call();
		} catch (GitAPIException e) {
			e.printStackTrace();
			throw new GitClientRuntimeException("git.clone.GitAPIException", e);

		}
	}

}
