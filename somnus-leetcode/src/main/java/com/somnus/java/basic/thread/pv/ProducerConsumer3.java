package com.somnus.java.basic.thread.pv;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列的方式
 * @author lenovo
 *
 */
public class ProducerConsumer3 {
	 // 建立一个阻塞队列
    private final LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(10);

    public ProducerConsumer3() {
    }

    public void start() {
        new Producer().start();
        new Consumer().start();
    }

    public static void main(String[] args) throws Exception {
        ProducerConsumer3 s3 = new ProducerConsumer3();
        s3.start();
    }
    
    class Producer extends Thread {
        @Override
		public void run() {
            while (true) {
                try {
                    Object o = new Object();
                    // 放入一个对象
                    queue.put(o);
                    System.out.println("Producer: " + o);
                } catch (InterruptedException e) {
                    System.out.println("producer is interrupted!");
                }
                // }
            }
        }
    }

    class Consumer extends Thread {
        @Override
		public void run() {
            while (true) {
                try {
                    // 取出一个对象
                    Object o = queue.take();
                    System.out.println("Consumer: " + o);
                } catch (InterruptedException e) {
                    System.out.println("producer is interrupted!");
                }
                // }
            }
        }
    }
}
