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
package com.somnus.mq.kafka.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月20日
 */
public class KafkaBroker {

	private static Logger logger = LoggerFactory.getLogger(KafkaBroker.class);

	private String host;
	
	private String port;
	
	/**
	 * 构建MafkaBroker
	 * @param brokerId
	 * @param brokerInfoStr
	 * @return
	 * @since JDK 1.6
	 */
	public static KafkaBroker createBroker(int brokerId, String brokerInfoStr) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO
	 * @return
	 * @since JDK 1.6
	 */
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO
	 * @return
	 * @since JDK 1.6
	 */
	public String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

}
