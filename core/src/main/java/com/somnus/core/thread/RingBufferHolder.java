package com.somnus.core.thread;

public class RingBufferHolder {

	public static int max_size = 1024;

	private static Object[] ringbuffer = new Object[max_size];

	public synchronized Object get(int sequence) {
		int index = sequence % max_size;
		return ringbuffer[index];
	}

	public static synchronized void add(int sequence, Object obj) {
		int index = sequence % max_size;
		ringbuffer[index] = obj;
	}

}
