package com.somnus.disruptor.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class Sample {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();

		// 第一个参数，是一个 EventFactory 对象，它负责创建 ValueEvent 对象，并填充到 RingBuffer 中；
		// 第二个参数，指定 RingBuffer 的大小。这个参数应该是2的幂，否则程序会抛出异常：
		// 第三个参数，就是之前创建的 ExecutorService 对象。?
		Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(
				ValueEvent.EVENT_FACTORY, 4, exec);

		// 用来处理消费者拿到的消息。
		final EventHandler<ValueEvent> handler1 = new ValueEventHandler1();

		// ---这里测试多个EventHandler，也就是多个消费者
		final EventHandler<ValueEvent> handler2 = new ValueEventHandler2();
		final EventHandler<ValueEvent> handler3 = new ValueEventHandler3();
		final EventHandler<ValueEvent> handler4 = new ValueEventHandler4();
		disruptor.handleEventsWith(handler1, handler2).then(handler3)
				.then(handler4);

		// 将 EventHandler 对象传入 Disruptor ，Disruptor 依据 EventHandler
		// 参数个数，创建相等数量消费者对象。

		// 启动
		// 每个消费者线程都有一个等待策略：以确定当无消息可消费时，消费者是阻塞还是轮询。

		// Disruptor
		// 中定义了几种不同等待策略：BlockingWaitStrategy、TimeoutBlockingWaitStrategy、SleepingWaitStrategy等。
		RingBuffer<ValueEvent> ringBuffer = disruptor.start();

		int bufferSize = ringBuffer.getBufferSize();
		System.out.println("bufferSize =  " + bufferSize);

		for (long i = 0; i < 10; i++) {
			long seq = ringBuffer.next();
			try {
				String uuid = String.valueOf("ValueEventHandler0");
				ValueEvent valueEvent = ringBuffer.get(seq);
				valueEvent.setValue(uuid);
			} finally {
				ringBuffer.publish(seq);
			}
		}

		disruptor.shutdown();
		exec.shutdown();
	}
}
