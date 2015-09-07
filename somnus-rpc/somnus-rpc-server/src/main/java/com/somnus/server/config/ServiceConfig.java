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
package com.somnus.server.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.somnus.server.common.ConfigConstant;

/**
 *
 * 服务配置类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

	private static Map<String, String> property = null;

	public Map<String, String> getProp() {
		return property;
	}

	private ServiceConfig() {
		property = new HashMap<String, String>();
	}

	public void set(String key, String value) {
		property.put(key, value);
	}

	public String getString(String name) {
		return property.get(name);
	}

	public int getInt(String name) throws Exception {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			throw new Exception("the property (" + name + ") is null");
		}
		return Integer.parseInt(value);
	}

	public boolean getBoolean(String name) throws Exception {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			throw new Exception("the property (" + name + ") is null");
		}
		return Boolean.parseBoolean(value);
	}

	public List<String> getList(String name, String split) {
		String value = property.get(name);
		if (value == null || value.equalsIgnoreCase("")) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		String[] values = value.split(split);
		for (String v : values) {
			list.add(v);
		}
		return list;
	}

	/**
	 * get service config from paths
	 *
	 * @param paths
	 * @return
	 * @throws Exception
	 */
	public static ServiceConfig getServiceConfig(String... paths) throws Exception {

		ServiceConfig instance = new ServiceConfig();

		// <property>
		// <name>name</name>
		// <value>value</value>
		// </property>
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression exprProperty = xpath.compile("//configuration/property");
		XPathExpression exprName = xpath.compile("name");
		XPathExpression exprValue = xpath.compile("value");

		for (String p : paths) {
			File fServiceConfig = new File(p);
			if (!fServiceConfig.exists()) {
				continue;
			}

			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(p);

			NodeList propertyNodes = (NodeList) exprProperty.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < propertyNodes.getLength(); i++) {
				Node node = propertyNodes.item(i);
				Node nameNode = (Node) exprName.evaluate(node, XPathConstants.NODE);
				Node valueNode = (Node) exprValue.evaluate(node, XPathConstants.NODE);

				Node append = valueNode.getAttributes().getNamedItem("append");
				if (append != null && append.getNodeValue() != null && append.getNodeValue().equalsIgnoreCase("true")) {
					String key = nameNode.getTextContent();
					String value = property.get(nameNode.getTextContent());
					if (value != null) {
						value += "," + valueNode.getTextContent();
					} else {
						value = valueNode.getTextContent();
					}
					property.put(key, value);
				} else {
					property.put(nameNode.getTextContent(), valueNode.getTextContent());
				}
			}
		}

		return instance;
	}

	public static void main(String[] args) throws Exception {
		ServiceConfig config = getServiceConfig(ConfigConstant.SERVER_CONFIG_XML);

		Map<String, String> map = config.getProp();
		Set<String> set = map.keySet();
		for (String key : set) {
			System.out.println("key:" + key + ",value:" + map.get(key));
		}

	}

}
