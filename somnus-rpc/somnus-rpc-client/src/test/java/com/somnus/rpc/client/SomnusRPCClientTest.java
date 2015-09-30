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
package com.somnus.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.example.contract.NewsService;
import com.somnus.rpc.client.proxy.builder.ProxyFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SomnusRPCClientTest {

	private static Logger logger = LoggerFactory.getLogger(SomnusRPCClientTest.class);

	public static void main(String[] args) {
		/**
		 * 调用URL 格式:tcp://服务名//接口实现类 备注: 服务名：需要与gaea.config中的服务名一一对应
		 * 接口实现类：具体调用接口的接口实现类
		 */
		final String url = "tcp://servername/NewsService";
		NewsService newsService = ProxyFactory.create(NewsService.class, url);
		// List<News> list = newsService.getNewsByCateID();
		// for (News news : list) {
		// System.out.println("ID is " + news.getNewsID() + " title is " +
		// news.getTitle());
		// }
	}
}
