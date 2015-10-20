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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.shell.CopyCommands.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.core.hbase.domain.Attribute;
import com.somnus.core.hbase.factory.HBaseFactory;

/**
 * TODO
 * 
 * @author ZHANGDONGXUAN727
 * @version 0.0.1
 * @param <T>
 */
public class DefaultHBaseStore implements HBaseStore {

	private static Logger logger = LoggerFactory
			.getLogger(DefaultHBaseStore.class);

	private HBaseFactory factory;

	@Override
	public void put2Family(String tableName, String key, String familyName,
			List<Attribute> attrs) {

		HTableInterface hTable = factory.getHTable(tableName);
		Put put = new Put(Bytes.toBytes(key));
		try {
			for (int i = 0; i < attrs.size(); i++) {
				Attribute attribute = attrs.get(i);
				byte[] family = Bytes.toBytes(familyName);
				byte[] qualifier = Bytes.toBytes(attribute.getName());
				byte[] value = Bytes.toBytes(attribute.getValue());
				put.add(family, qualifier, value);
			}

			hTable.put(put);
			logger.debug("put success");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);

		}

	}

	@Override
	public Map<String, Attribute> getAttrsByFamily(String tableName,
			String rowKey, String familyName) {

		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		try {
			Get get = new Get(Bytes.toBytes(rowKey));
			get.addFamily(Bytes.toBytes(familyName));
			HTableInterface hTable = factory.getHTable(tableName);
			Result result = hTable.get(get);
			for (KeyValue kv : result.list()) {
				String name = Bytes.toString(kv.getQualifier());
				String jsonValue = Bytes.toString(kv.getValue());
				long timestamp = kv.getTimestamp();
				Attribute attribute = new Attribute(name, jsonValue, timestamp);
				attributes.put(name, attribute);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}

		return attributes;
	}

	@Override
	public Attribute getNewValue(String tableName, String rowKey,
			String familyName, String column) {
		Attribute  attribute=null;
		try {
			Get get = new Get(Bytes.toBytes(rowKey));
			byte[] familyByte = Bytes.toBytes(familyName);
			byte[] columnByte = Bytes.toBytes(column);
			get.addColumn(familyByte, columnByte);
			HTableInterface hTable = factory.getHTable(tableName);
			Result result = hTable.get(get);
			KeyValue keyValue = result.getColumnLatest(familyByte, columnByte);

			String name = Bytes.toString(keyValue.getQualifier());
			String value = Bytes.toString(keyValue.getValue());
			logger.info(name);
			logger.info(value);
			attribute=new Attribute(name, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
		return attribute;
	}

	@Override
	public List<Attribute> getRecentValues(String tableName, String rowKey,String familyName,
			String column, int maxCount) {
		 List<Attribute> attributes=new ArrayList<Attribute>();
		try {
			Get get = new Get(Bytes.toBytes(rowKey));
			byte[] familyByte = Bytes.toBytes(familyName);
			byte[] columnByte = Bytes.toBytes(column);
			get.addColumn(familyByte, columnByte);
			get.setMaxVersions(maxCount);
			HTableInterface hTable = factory.getHTable(tableName);
			Result result = hTable.get(get);
			
			Attribute  attribute=null;
			for(KeyValue keyValue : result.list()){
				String name = Bytes.toString(keyValue.getQualifier());
				String value = Bytes.toString(keyValue.getValue());
				logger.info(name);
				logger.info(value);
				attribute=new Attribute(name, value,keyValue.getTimestamp());
				attributes.add(attribute);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
		
		return attributes;
	}

	/**
	 * @param factory
	 *            the factory to set
	 */
	@Override
	public void setFactory(HBaseFactory factory) {
		this.factory = factory;
	}

	@Override
	public void put2Column(String tableName, String rowKey, String value) {
		// TODO Auto-generated method stub

	}

	public List<Attribute> getRowsCells(String tableName, String rowKey,
			String familyName, String column, int maxCount) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		try {
			Get get = new Get(Bytes.toBytes(rowKey));
			byte[] familyByte = Bytes.toBytes(familyName);
			byte[] columnByte = Bytes.toBytes(column);
			Scan scan = new Scan();
			scan.addColumn(familyByte, columnByte);
			scan.setMaxVersions(maxCount);

			HTableInterface hTable = factory.getHTable(tableName);
			ResultScanner resultScanner = hTable.getScanner(scan);
			Attribute attribute = null;
			for (Result result : resultScanner) {
				for (KeyValue keyValue : result.list()) {
					String name = Bytes.toString(keyValue.getQualifier());
					String value = Bytes.toString(keyValue.getValue());
					String row = Bytes.toString(keyValue.getRow());
					logger.info(row);
					logger.info(name);
					logger.info(value);
					attribute = new Attribute(name, value,
							keyValue.getTimestamp());
					attributes.add(attribute);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
		return attributes;
	}
	
	@Override
	public List<Attribute> getAllValues(String tableName, String rowKey,
			String column) {
		// TODO Auto-generated method stub
		return null;
	}

}
