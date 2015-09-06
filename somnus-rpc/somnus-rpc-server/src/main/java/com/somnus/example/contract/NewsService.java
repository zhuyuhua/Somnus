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
package com.somnus.example.contract;

import java.util.List;

import com.somnus.example.entity.News;
import com.somnus.server.component.annotation.Operation;
import com.somnus.server.component.annotation.Service;

/**
 * 对外提供服务接口类
 *
 * @ServiceContract 标记该接口对外提供服务
 * @OperationContract 标记该方法对外暴露
 *
 * @author @author Service Platform Architecture Team (spat@58.com)
 */
@Service
public interface NewsService {
	@Operation
	public News getNewsByID(int newsID) throws Exception;

	@Operation
	public List<News> getNewsByCateID() throws Exception;
}
