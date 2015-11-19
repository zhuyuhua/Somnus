package com.somnus.leetcode.interview.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * "NumberManager"类 -->排号
 * 
 * 定义一个用于存储上一个客户号码的成员变量和用于存储所有等待服务的客户号码的队列集合。
 * 
 * 定义一个产生新号码的方法和获取马上要为之服务的号码的方法， 这两个方法被不同的线程操作了相同的数据，所以，要进行同步。
 * 
 * @author joe
 * 
 */
public class NumberManager {

	private Integer lastNumber = 0;

	private final List<Integer> queueNumber = new ArrayList<>();

	/**
	 * 号码排队
	 * 
	 * @return
	 */
	public synchronized Integer generateNewManager() {
		queueNumber.add(++lastNumber);
		return lastNumber;
	}

	public synchronized Integer fetchServiceNumber() {
		if (queueNumber.size() > 0) {
			return queueNumber.remove(0);
		} else {
			return null;
		}
	}
}
