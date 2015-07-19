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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 
	 * 获取文件内容
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

}
