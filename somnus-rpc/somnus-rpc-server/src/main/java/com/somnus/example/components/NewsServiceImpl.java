package com.somnus.example.components;

import java.util.ArrayList;
import java.util.List;

import com.somnus.example.contract.NewsService;
import com.somnus.example.entity.News;
import com.somnus.protocol.annotation.ServiceImpl;

/**
 * 对外提供服务接口实现类
 *
 * @ServiceBehavior 标记该类对外提供服务，服务契约为INewsService
 *
 * 
 */
@ServiceImpl
public class NewsServiceImpl implements NewsService {

	@Override
	public News getNewsByID(int newsID) throws Exception {
		return NewsServiceImpl.getNews();
	}

	@Override
	public List<News> getNewsByCateID() throws Exception {
		List<News> list = new ArrayList<News>();
		list.add(NewsServiceImpl.getNews());
		return list;
	}

	private static News getNews() {
		News news = new News();
		news.setNewsID(58);
		news.setTitle("58同城一个神奇的网站");
		return news;
	}
}
