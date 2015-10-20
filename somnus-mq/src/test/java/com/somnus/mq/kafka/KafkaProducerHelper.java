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

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.somnus.utils.json.JSONUtils;
import com.somnus.utils.properties.PropertiesUtil;

public class KafkaProducerHelper {

	private KafkaProducer<byte[], byte[]> producer;

	public KafkaProducerHelper(String producerConfig) {
		Properties properties = PropertiesUtil.getInstance(producerConfig).getProperties();
		producer = new KafkaProducer<byte[], byte[]>(properties);
	}

	public Future<RecordMetadata> send(String topicName, final String message) {
		return send(topicName, null, message);
	}

	public Future<RecordMetadata> send(String topicName,
			final Integer partition, final String message) {

		if (topicName == null) {
			return null;
		}
		ProducerRecord<byte[], byte[]> record = new ProducerRecord<byte[], byte[]>(
				topicName, partition, null, message.getBytes());

		return producer.send(record, new Callback() {

			@Override
			public void onCompletion(RecordMetadata metadata,
					Exception exception) {
				if (exception != null) {
					System.out.println("The offset:" + metadata.offset()
							+ ",partition:" + metadata.partition() + ",topic:"
							+ metadata.topic());
				}
			}
		});
	}

	public void shutdown() {
		producer.close();
	}

}

class KafkaProducerThread extends Thread {

	private KafkaProducerHelper kafkaProducer;

	private Map<String, Object> map;

	private String topic;

	public KafkaProducerThread(KafkaProducerHelper producer,
			Map<String, Object> map, int topicIndex) {
		this.kafkaProducer = producer;
		this.map = map;
		this.topic = "test" + (topicIndex % 10);
	}

	@Override
	public void run() {
		map.put("time", System.currentTimeMillis());
		kafkaProducer.send(topic, JSONUtils.toJSONString(map));
	}

}
