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
package com.somnus.utils.velocity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.velocity.VelocityContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.utils.file.FileUtil;

/**
 * VelocityUtil 测试类
 * 
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class VelocityUtilTest {

	private static Logger logger = LoggerFactory.getLogger(VelocityUtilTest.class);

	public static String CLASS_PATH = Thread.class.getResource("/").getPath();

	public static String velocity = CLASS_PATH+"velocity/";
	
	@Test
	public void testVmToFile() throws IOException {

		VelocityContext context = new VelocityContext();
		context.put("name", "testname");

		String destFilePath = velocity+"test.file";
		File destFile = new File(destFilePath);
		
		if (destFile.exists()) {
			destFile.delete();
		}
		assertFalse(destFile.exists());

		 VelocityUtil.vmToFile(context, "velocity/test.vm", destFilePath);
		 assertTrue(destFile.exists());
		 assertEquals("testname",FileUtil.getFileContent(destFile));

//		destFile.delete();
	}
	
	public static void main(String[] args) {
		ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("velocity/test.vm");
		assertNotNull("inputstream is null",in);
		
	}

}
