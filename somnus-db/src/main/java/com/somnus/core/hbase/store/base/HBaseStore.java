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
package com.somnus.core.hbase.store.base;

import java.util.List;
import java.util.Map;

import com.somnus.core.hbase.domain.Attribute;
import com.somnus.core.hbase.factory.HBaseFactory;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 */
public interface HBaseStore {

	/**
	 *��ֵ��������
	 */
	void put2Family(String tableName, String rowKey, String familyName,List<Attribute> attrs);
	/**
	 *��ֵ������
	 */
	void put2Column(String tableName, String rowKey,String value);
	/**
	 * ��ȡ��Ӧ���������е�ֵ
	 */
	Map<String, Attribute> getAttrsByFamily(String tableName, String rowKey,String familyName);

	/**
	 * ��ȡĳ�е�����ֵ
	 */
	Attribute getNewValue(String tableName, String rowKey, String familyName,String column);

	/**
	 * ��ȡĳ�е�����ֵ
	 * @param maxCount ���ȡ����ֵ
	 * @return
	 *
	 */
	List<Attribute> getRecentValues(String tableName, String rowKey,String familyName,
			String column, int maxCount);

	/**
	 * ��ȡĳ�����еİ汾��ֵ
	 */
	List<Attribute> getAllValues(String tableName, String rowKey,String column);

	
	
	
	void setFactory(HBaseFactory factory);
}
