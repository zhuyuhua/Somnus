/*
 * Copyright (c) 2010-2015. somnus Framework
 *
 */
package com.somnus.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class ReplaceFileUtil {

	private static Logger logger = LoggerFactory.getLogger(ReplaceFileUtil.class);

	private static String CLASS_PATH = "D:/dev/git/Somnus";

	private static AtomicInteger atomic = new AtomicInteger(0);

	public static void printlnFile(File file, String format) throws IOException {

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File tempFile : files) {
				printlnFile(tempFile, format);
			}
		} else if (file.isFile()) {
			if (file.getName().endsWith(".java")) {
				BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

				StringBuffer strBuf = new StringBuffer();
				for (String tmp1 = null; (tmp1 = bufReader.readLine()) != null; tmp1 = null) {

					String tmp = new String(tmp1.toString().getBytes("UTF-8"));
					if (tmp.contains(format)) {
						atomic.incrementAndGet();
						System.out.println(file.getAbsolutePath());
						break;
					}

				}
			}
		}
	}

	/**
	 * 替换java文件指定字符串
	 * 
	 * @param file
	 *            文件名
	 * @param format
	 *            要替换的字符串
	 * @param replace
	 *            待替换的字符串
	 */
	public static void replace(File file, String format, String replace) throws IOException {

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File tempFile : files) {
				replace(tempFile, format, replace);
			}
		} else if (file.isFile()) {
			if (file.getName().endsWith(".java")) {
				replaceFile(file, format, replace);
			}
		}

	}

	/**
	 * @param format
	 *            需要替换的字符串
	 * @param replace
	 *            替换后的字符串
	 * @throws IOException
	 * 
	 */
	public static void replaceFile(File file, String format, String replace) throws IOException {

		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		StringBuffer strBuf = new StringBuffer();
		for (String tmp1 = null; (tmp1 = bufReader.readLine()) != null; tmp1 = null) {

			String tmp = new String(tmp1.toString().getBytes("UTF-8"));
			tmp = tmp.replaceAll(format, replace);
			strBuf.append(tmp);
			strBuf.append(System.getProperty("line.separator"));
			atomic.incrementAndGet();
		}

		bufReader.close();
		String outputStrBuf = new String(strBuf.toString().getBytes("UTF-8"));

		// del old file
		file.delete();
		file.createNewFile();
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.write(outputStrBuf.toString().toCharArray());
		printWriter.flush();
		printWriter.close();
	}

	public static void main(String[] args) throws IOException {
		File file = new File(CLASS_PATH);

		// replace(file, "@author zhuyuhua", "@author zhuyuhua");
		printlnFile(file, "@date:");

		System.out.println("atomic:" + atomic);
	}
}
