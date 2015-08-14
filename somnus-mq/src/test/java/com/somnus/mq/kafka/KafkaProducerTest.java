package com.somnus.mq.kafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.common.config.GlobalConfigConstant;
import com.somnus.utils.properties.PropertiesUtil;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducerTest {
	private static Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);
	private Producer<String, String> producer;

	private String kafkaProducerConfig = GlobalConfigConstant.CLASS_PATH + "kafka.producer.test.properties";

	public KafkaProducerTest() throws Exception {

		PropertiesUtil properties = PropertiesUtil.getInstance(kafkaProducerConfig);
		ProducerConfig config = new ProducerConfig(properties.getProperties());
		producer = new Producer<String, String>(config);
	}

	public void send(String topicName, String message) {
		if (topicName == null || message == null) {
			return;
		}
		KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(topicName, message);

		producer.send(keyedMessage);
	}

	public void send(String topicName, Collection<String> messages) {
		if (topicName == null || messages == null) {
			return;
		}
		if (messages.isEmpty()) {
			return;
		}
		List<KeyedMessage<String, String>> keyedMessages = new ArrayList<KeyedMessage<String, String>>();
		for (String entry : messages) {
			KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(topicName, entry);
			keyedMessages.add(keyedMessage);
		}
		producer.send(keyedMessages);
	}

	public void shutdown() {
		producer.close();
	}

	public static void main(String[] args) {
		KafkaProducerTest producer = null;
		try {
			producer = new KafkaProducerTest();

			// while (true) {
			Long start = System.currentTimeMillis();

			for (int i = 0; i < 10; i++) {
				producer.send("test-topic-" + i, System.currentTimeMillis() + "");
			}

			System.out.println(System.currentTimeMillis() - start);

			// Thread.sleep(1000);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				producer.shutdown();
			}
		}
	}
}
