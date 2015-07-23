package com.somnus.mq.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Consumer;

/**
 * 
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class KafkaConsumerTest {

	private static Logger logger = LoggerFactory.getLogger("testReceive");
	private String consumerConfig = GlobalDocConstant.CLASS_RESOURCES_CONFIG_PATH + "kafka.consumer.test.properties";

	private List<String> topics; // topic
	private int threadNum; // 每个topic线程数

	private int totalThreadNum;// 线程总数

	private ConsumerConnector consumerConnector;// 负责和Kafka服务端联通
	private ExecutorService executorService;// 运行所有线程

	/**
	 * @param topics
	 *            订阅topic
	 * @param threadNum
	 *            处理每个topic的线程数
	 */
	public KafkaConsumerTest(List<String> topics, int threadNum) {

		// 连通kafka
		consumerConnector = Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.topics = topics;
		this.threadNum = threadNum;
		this.totalThreadNum = threadNum * topics.size();
	}

	private ConsumerConfig createConsumerConfig() {
		Properties properties = PropertiesReader.read(consumerConfig);
		PropertiesUtil.printlnProperties(properties);
		return new ConsumerConfig(properties);
	}

	public void startup() {

		Map<String, Integer> topicAndThread = new HashMap<String, Integer>();

		for (String topic : topics) {
			topicAndThread.put(topic, threadNum);// 每个topic配置threadNum个线程
			logger.debug("message topic is:{}", topic);
		}

		// 根据map或者所有主题对应的消息流
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector
				.createMessageStreams(topicAndThread);

		//
		executorService = Executors.newFixedThreadPool(totalThreadNum);

		for (String topic : topics) {

			// 获取某个主题的消息流
			List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
			logger.debug("streams size:{}", streams.size());

			// 根据消息流的大小，创建相同的线程处理
			for (int i = 0; i < streams.size(); i++) {
				final KafkaStream<byte[], byte[]> stream = streams.get(i);
				executorService.execute(new MessageRunner(stream, i));
			}
		}
	}

	public void shutdown() {
		if (consumerConnector != null)
			consumerConnector.shutdown();
		if (executorService != null)
			executorService.shutdown();
		try {
			if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				logger.error("Timed out waiting for consumer threads to shut down, exiting uncleanly");
			}
		} catch (InterruptedException e) {
			logger.error("Interrupted during shutdown, exiting uncleanly");
		}
	}

	class MessageRunner implements Runnable {
		private KafkaStream<byte[], byte[]> stream;
		int index;

		MessageRunner(KafkaStream<byte[], byte[]> stream, int i) {
			this.stream = stream;
			this.index = i;
		}

		@Override
		public void run() {
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while (it.hasNext()) {
				MessageAndMetadata<byte[], byte[]> item = it.next();

				String message = new String(item.message());
				Long time = Long.valueOf(message);
				System.out.println(System.currentTimeMillis() - time);
				// System.out.println(message + " from the index is " + index);
			}
		}
	}

	public static void main(String[] args) {

		logger.debug(KafkaConsumerTest.class.getName());

		List<String> topics = new ArrayList<String>();
		for (int i = 0; i < 500; i++) {
			topics.add("test-topic-" + i);
		}
		KafkaConsumerTest consumer = new KafkaConsumerTest(topics, 1);
		consumer.startup();
	}
}
