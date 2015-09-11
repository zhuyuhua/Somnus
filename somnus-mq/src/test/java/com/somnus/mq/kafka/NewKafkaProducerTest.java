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

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.common.config.GlobalConfigConstant;
import com.somnus.utils.properties.PropertiesUtil;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月11日
 */
public class NewKafkaProducerTest {

	private static Logger logger = LoggerFactory.getLogger(NewKafkaProducerTest.class);

	private KafkaProducer<byte[], byte[]> producer;

	private String kafkaProducerConfig = GlobalConfigConstant.CLASS_PATH
			+ "kafka/kafka.newproducer.properties";

	private NewKafkaProducerTest() {
		PropertiesUtil properties = PropertiesUtil.getInstance(kafkaProducerConfig);
		producer = new KafkaProducer<byte[], byte[]>(properties.getProperties());
	}
	
	public void send(String topicName, String message) {
		if (topicName == null) {
			return;
		}
		logger.debug(message);
		ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(
				topicName, message.getBytes());
		producer.send(record);
	}

	/**
	 * 在producer进程退出前调用可以保证buffer中的消息被发送
	 */
	void close() {
		producer.close();
	}
	
	public static void main(String[] args) {
		NewKafkaProducerTest producer = null;
		try {
			producer = new NewKafkaProducerTest();

			// while (true) {
			Long start = System.currentTimeMillis();

			for (int i = 0; i < 10; i++) {
				producer.send("test", System.currentTimeMillis() + "-" + i);
			}

			System.out.println(System.currentTimeMillis() - start);

			// Thread.sleep(1000);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				producer.close();
			}
		}
	}
	
}
