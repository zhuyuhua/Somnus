/*
 *  Copyright Beijing 58 Information Technology Co.,Ltd.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.somnus.example.components;

import java.util.List;
import java.util.ArrayList;

import com.bj58.spat.gaea.server.contract.annotation.ServiceBehavior;
import com.somnus.example.contract.INewsService;
import com.somnus.example.entity.News;

/**
 * 对外提供服务接口实现类
 * 
 * @ServiceBehavior 标记该类对外提供服务，服务契约为INewsService
 * 
 * @author @author Service Platform Architecture Team (spat@58.com)
 */
@ServiceBehavior
public class NewsService implements INewsService {

	@Override
	public News getNewsByID(int newsID) throws Exception {
		return NewsService.getNews();
	}

	@Override
	public List<News> getNewsByCateID() throws Exception {
		List<News> list = new ArrayList<News>();
		list.add(NewsService.getNews());
		return list;
	}

	private static News getNews() {
		News news = new News();
		news.setNewsID(58);
		news.setTitle("58同城一个神奇的网站");
		return news;
	}
}
