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

public class KafkaProducerTest {

	private static String producerConfig = GlobalDocConstant.CLASS_RESOURCES_CONFIG_PATH
			+ "producer1.test.properties";

	public static void main(String[] args) throws Exception {

		KafkaProducerHelper helper = new KafkaProducerHelper(producerConfig);
		// Thread.sleep(5000);
		System.out.println("----start send----");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {

			String message = i + "";
			helper.send("topic-part3", message);
			// RecordMetadata metadata = future.get();Future<RecordMetadata>
			// future =
			// System.out.println(metadata);
			// Thread.sleep(1000);

		}
		System.out.println("cost:" + (System.currentTimeMillis() - start));
		System.out.println("----end send ----");
		// helper.shutdown();

	}
}
