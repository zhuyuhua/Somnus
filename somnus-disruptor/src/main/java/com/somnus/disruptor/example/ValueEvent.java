/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.disruptor.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventFactory;

/**
 * 定义 ValueEvent 类，该类作为填充 RingBuffer 的消息，生产者向该消息中填充数据（就是修改 value
 * 属性值，后文用生产消息代替），消费者从消息体中获取数据（获取 value 值，后文用消费消息代替）：
 * 
 * @author:zhuyuhua
 * @date:2015年3月25日 下午4:40:08
 * @version 0.0.1
 */
public final class ValueEvent {

	private static Logger logger = LoggerFactory.getLogger(ValueEvent.class);

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {

		@Override
		public ValueEvent newInstance() {
			return new ValueEvent();
		}
	};

}
