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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class KafkaConsumerHelper extends Thread {

	private static Logger logger = LoggerFactory
			.getLogger(KafkaConsumerHelper.class);

	private List<String> topics; // topic列表
	private int threadNumPerTopic; // 每个topic的线程数
	private int totalThreadNum;// 线程总数

	private ConsumerConnector consumerConnector;// 负责和Kafka服务端联通
	private ExecutorService executorService;// 运行所有线程

	/**
	 * @param topics
	 *            订阅topic
	 * @param threadNum
	 *            处理每个topic的线程数
	 */
	public KafkaConsumerHelper(List<String> topics, int threadNumPerTopic,
			ConsumerConfig consumerConfig) {

		// 连通kafka
		consumerConnector = Consumer
				.createJavaConsumerConnector(consumerConfig);
		this.topics = topics;
		this.threadNumPerTopic = threadNumPerTopic;
		this.totalThreadNum = threadNumPerTopic * topics.size();
	}

	@Override
	public void run() {
		startup();
	}

	public void startup() {

		Map<String, Integer> topicAndThread = new HashMap<String, Integer>();

		for (String topic : topics) {
			// topic1->5,topic2->3
			topicAndThread.put(topic, threadNumPerTopic);// 每个topic配置threadNumPerTopic个线程

			logger.debug("message topic is:{}", topic);
		}

		// 根据map或者所有主题对应的消息流
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector
				.createMessageStreams(topicAndThread);

		executorService = Executors.newFixedThreadPool(totalThreadNum);// topic数*每个topic的处理线程数

		for (String topic : topics) {

			// 获取某个topic的消息流
			List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

			// 根据消息流的大小，创建相同的线程处理
			int streamSize = streams.size(); // == threadNumPerTopic
			System.out.println("stream size :" + streamSize);
			for (int i = 0; i < streamSize; i++) {
				final KafkaStream<byte[], byte[]> stream = streams.get(i);
				// TODO
				executorService.submit(new MessageRunner(stream, i));
			}

		}
	}

	public void shutdown() {

		if (consumerConnector != null) {
			consumerConnector.shutdown();
		}
		if (executorService != null) {
			executorService.shutdown();
		}
		try {
			if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				logger.error("Timed out waiting for consumer threads to shut down, exiting uncleanly");
			}
		} catch (InterruptedException e) {
			logger.error("Interrupted during shutdown, exiting uncleanly");
		}
	}

}
