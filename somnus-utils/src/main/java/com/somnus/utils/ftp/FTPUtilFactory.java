/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.utils.ftp;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class FTPUtilFactory {

	private static Logger logger = LoggerFactory.getLogger(FTPUtilFactory.class);

	private static String username;

	private static String password;

	private static String serverIp;

	private static int port;

	private static String encoding;

	private static String tmpDir;

	private static int retryCount;

	private static String mode;

	public static FtpUtil getFtpUtil(String properties) {
		initConfig(properties);
		FtpUtil ftpUtil = new FtpUtil(serverIp, port, username, password, encoding, retryCount, mode);
		ftpUtil.initTmpDir(tmpDir);
		return ftpUtil;
	}

	private static void initConfig(String properties) {
		InputStream in = null;
		try {
			URL propertyFileURL = Thread.currentThread().getContextClassLoader().getResource(properties);
			Properties prop = new Properties();
			in = propertyFileURL.openStream();
			prop.load(in);
			username = prop.getProperty("ftp.username");
			password = prop.getProperty("ftp.password");
			serverIp = prop.getProperty("ftp.serverIp");
			port = Integer.parseInt(prop.getProperty("ftp.port"));
			encoding = prop.getProperty("ftp.encoding");
			tmpDir = prop.getProperty("ftp.tmpDir");
			if (tmpDir == null || "".equals(tmpDir)) {

			}
			try {
				retryCount = Integer.parseInt(prop.getProperty("ftp.retry.count"));
			} catch (Exception e) {
				retryCount = 3;
			}
			mode = prop.getProperty("ftp.mode");
			if (tmpDir != null)
				initTmpDir();
		} catch (Exception e) {
			System.out.println("===Properties Error======");
			System.out.println(properties);
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void initTmpDir() {
		// 创建临时目录
		File tmpDirFile = new File(tmpDir);
		if (!tmpDirFile.exists())
			tmpDirFile.mkdirs();
	}
}
