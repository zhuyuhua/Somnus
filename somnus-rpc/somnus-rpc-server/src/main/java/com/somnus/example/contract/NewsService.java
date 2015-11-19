package com.somnus.example.contract;

import java.util.List;

import com.somnus.example.entity.News;
import com.somnus.protocol.annotation.Operation;
import com.somnus.protocol.annotation.Service;

/**
 * 对外提供服务接口类
 *
 * @ServiceContract 标记该接口对外提供服务
 * @OperationContract 标记该方法对外暴露
 *
 * @author
 */
@Service
public interface NewsService {
	@Operation
	public News getNewsByID(int newsID) throws Exception;

	@Operation
	public List<News> getNewsByCateID() throws Exception;
}
