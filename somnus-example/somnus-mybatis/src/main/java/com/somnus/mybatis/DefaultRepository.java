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
package com.somnus.mybatis;

import org.springframework.stereotype.Repository;

import com.somnus.mybatis.domain.User;

/**
 * TODO
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年10月29日
 */
@Repository
public class DefaultRepository extends MybatisBaseDao{

	 public User getUserById(User user) {   
	     return (User) getSqlSession().selectOne("com.somnus.batch.mybatis.domain.User.getUser", user);  
	  }   
	 public void saveUser(User user) {   
	    getSqlSession().insert("com.somnus.batch.mybatis.domain.User.saveUser", user);  
	  }  
}
