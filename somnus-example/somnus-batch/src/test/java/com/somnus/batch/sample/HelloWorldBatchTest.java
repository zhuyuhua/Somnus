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
package com.somnus.batch.sample;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

import com.somnus.batch.SpringBaseTestCase;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
public class HelloWorldBatchTest extends SpringBaseTestCase {

	private static Logger logger = LoggerFactory
			.getLogger(HelloWorldBatchTest.class);

	@Test
	public void test() {

		JobLauncher launcher = applicationContext.getBean(JobLauncher.class);
		Job job = (Job) applicationContext.getBean("helloWorldJob");

		try {
			/* 运行Job */
			JobExecution result = launcher.run(job, new JobParameters());
			/* 处理结束，控制台打印处理结果 */

			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
