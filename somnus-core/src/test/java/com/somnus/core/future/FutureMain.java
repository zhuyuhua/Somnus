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
package com.somnus.core.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class FutureMain {

	private static Logger logger = LoggerFactory.getLogger(FutureMain.class);

	public static void main(String[] args) {
		Client client = new Client();
		// 理解返回一个FutureData
		Data data = client.request("name");
		System.out.println("请求完毕！");

		try {

			// 处理其他业务
			// 这个过程中，真是数据RealData组装完成，重复利用等待时间
			Thread.sleep(2000);

		} catch (Exception e) {

		}

		// 真实数据
		System.out.println("数据 = " + data.getResult());

	}

}
