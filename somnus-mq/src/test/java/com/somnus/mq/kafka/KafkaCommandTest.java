/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.admin.TopicCommand;

/**
 * 记录各种java api操作Kafka命令
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class KafkaCommandTest {

	private static Logger logger = LoggerFactory
			.getLogger(KafkaCommandTest.class);

	public static void main(String[] args) {
		createTopic();
	}

	/**
	 * 创建topic
	 */
	public static void createTopic() {

		String[] options = new String[] { "--create", "--zookeeper",
				"10.20.19.97/2181", "--partition", "1", "--topic", "topic_name" };
		TopicCommand.main(options);

	}
}
