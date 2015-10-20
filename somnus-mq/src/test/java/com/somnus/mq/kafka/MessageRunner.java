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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

/**
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class MessageRunner implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(MessageRunner.class);

	private KafkaStream<byte[], byte[]> stream;
	private int streamsIndex;

	public MessageRunner(KafkaStream<byte[], byte[]> stream, int streamsIndex) {

		this.stream = stream;
		this.streamsIndex = streamsIndex;
	}

	@Override
	public void run() {

		ConsumerIterator<byte[], byte[]> it = stream.iterator();

		Map<String, Object> map;
		System.out.println("thread Name:" + Thread.currentThread().getName());

		long t1 = System.currentTimeMillis();
		while (it.hasNext()) {

			MessageAndMetadata<byte[], byte[]> item = it.next();
			System.out.println(Thread.currentThread().getName() + " recv msg:"
					+ new String(item.message()));
			// map = JacksonUtils.readJson2Entity(new String(item.message()),
			// Map.class);
			//
			// System.out.println(Thread.currentThread().getName()
			// + " consumer cost(" + map.get("index") + "):"
			// + (System.currentTimeMillis() - t1));
			// t1 = System.currentTimeMillis();
		}
	}

}
