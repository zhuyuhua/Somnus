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
package com.somnus.core.hbase.store;

import com.somnus.core.hbase.store.base.HBaseStore;

/**
 * TODO
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public interface EventStore {

	<T> void putObject(T t,String tableName,String identifier);
	
	<T> T getObject(String tableName,String identifier,String className);
	
	void setHbaseStore(HBaseStore hbaseStore);
}
