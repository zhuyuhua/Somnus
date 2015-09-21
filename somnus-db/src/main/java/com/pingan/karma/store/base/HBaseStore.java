/*
 * Copyright (c) 2010-2015. Karma Framework
 *
 */
package com.pingan.karma.store.base;

import java.util.List;
import java.util.Map;

import com.pingan.karma.domain.Attribute;
import com.pingan.karma.factory.HBaseFactory;

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
