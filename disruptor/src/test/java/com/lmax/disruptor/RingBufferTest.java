/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.lmax.disruptor;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.support.StubEvent;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * TODO
 * 
 * @author:zhuyuhua
 * @date:2015年3月27日 下午5:41:13
 * @version 0.0.1
 */
public class RingBufferTest {

	private static Logger logger = LoggerFactory
			.getLogger(RingBufferTest.class);
	private final ExecutorService executor = Executors
			.newSingleThreadExecutor(DaemonThreadFactory.INSTANCE);
	private final RingBuffer<StubEvent> ringBuffer = RingBuffer
			.createMultiProducer(StubEvent.EVENT_FACTORY, 32);
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
	{
		ringBuffer.addGatingSequences(new NoOpEventProcessor(ringBuffer)
				.getSequence());
	}

	@Test
	public void shouldClaimAndGet() throws Exception {
		assertEquals(SingleProducerSequencer.INITIAL_CURSOR_VALUE,
				ringBuffer.getCursor());

		StubEvent expectedEvent = new StubEvent(2701);
		ringBuffer.publishEvent(StubEvent.TRANSLATOR, expectedEvent.getValue(),
				expectedEvent.getTestString());

		long sequence = sequenceBarrier.waitFor(0);
		assertEquals(0, sequence);

		StubEvent event = ringBuffer.get(sequence);
		assertEquals(expectedEvent, event);

		assertEquals(0L, ringBuffer.getCursor());
	}

}
