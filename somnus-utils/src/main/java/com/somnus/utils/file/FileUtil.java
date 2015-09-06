/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 *
	 * 获取文件内容
	 *
	 * @param file
	 * @return String
	 * @throws IOException
	 */
	public static String getFileContent(File file) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String temp;
		while ((temp = bufferedReader.readLine()) != null) {
			result.append(temp);
		}
		bufferedReader.close();

		return result.toString();
	}

	/**
	 * get all rar/jar/war/ear which in dir
	 *
	 * @param dirList
	 * @return
	 * @throws IOException
	 */
	public static List<String> getUniqueLibPath(String... dirs) throws IOException {

		List<String> jarList = new ArrayList<String>();
		List<String> fileNameList = new ArrayList<String>();

		for (String dir : dirs) {
			List<File> fileList = getFiles(dir, "rar", "jar", "war", "ear");
			if (fileList != null) {
				for (File file : fileList) {
					if (!fileNameList.contains(file.getName())) {
						jarList.add(file.getCanonicalPath());
						fileNameList.add(file.getName());
					}
				}
			}
		}

		return jarList;
	}

	/**
	 * 返回包含extension后缀名的文件
	 */
	public static List<File> getFiles(String dir, String... extension) {
		File f = new File(dir);
		if (!f.isDirectory()) {
			return null;
		}

		List<File> fileList = new ArrayList<File>();
		getFiles(f, fileList, extension);

		return fileList;
	}

	private static void getFiles(File f, List<File> fileList, String... extension) {
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				getFiles(files[i], fileList, extension);
			} else if (files[i].isFile()) {

				String fileName = files[i].getName().toLowerCase();
				boolean isAdd = false;
				if (extension != null) {
					for (String ext : extension) {
						if (fileName.lastIndexOf(ext) == fileName.length() - ext.length()) {
							isAdd = true;
							break;
						}
					}
				}

				if (isAdd) {
					fileList.add(files[i]);
				}
			}
		}
	}

}
