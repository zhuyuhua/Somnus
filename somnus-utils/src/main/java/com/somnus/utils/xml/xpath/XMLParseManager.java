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
package com.somnus.utils.xml.xpath;

import java.io.File;
import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class XMLParseManager {

	private static Logger logger = LoggerFactory.getLogger(XMLParseManager.class);

	private XMLParseUtil xmlParser;

	public XMLParseManager() throws ServiceException {
		xmlParser = new XMLParseUtil();
	}

	/**
	 * 初始化方法，通过文件对象初始化XML解析器和文档对象
	 *
	 * @param xmlParser
	 * @param document
	 * @param file
	 * @throws ServiceException
	 * @see [类、类#方法、类#成员]
	 */
	private Document getDocument(File file) throws ServiceException {
		Document document = null;
		try {
			document = xmlParser.parseDocument(file);
		} catch (IllegalArgumentException e) {
			throw new ServiceException("XMLParseManager@getDocument: " + "An illegal or inappropriate argument!" + e);
		} catch (SAXParseException e) {
			throw new ServiceException("XMLParseManager@getDocument: " + "XML file error, can not parse!" + e);
		} catch (SAXException e) {
			throw new ServiceException("XMLParseManager@getDocument: " + "There is a SAXException！" + e);
		} catch (IOException e) {
			throw new ServiceException("XMLParseManager@getDocument: " + "There is an IOException!" + e);
		}

		return document;
	}

	/**
	 * 获取节点的值
	 *
	 * @return nodeValue
	 * @throws ServiceException
	 * @see [类、类#方法、类#成员]
	 */
	private String getNodeValue(Node node, String xpath) throws ServiceException {
		String nodeValue = null;
		try {
			nodeValue = xmlParser.getNodeStringValue(node, xpath);
		} catch (XPathExpressionException e) {
			throw new ServiceException(
					"XMLParseManager@getNodeValue: " + "XPath expression [" + xpath + "] error！" + e);
		}
		return nodeValue;
	}

	/**
	 * 根据作者姓名获取书籍
	 *
	 * @param file
	 *            XML文件对象
	 * @param name
	 *            书籍作者姓名
	 * @return myBook
	 * @throws ServiceException
	 * @see [类、类#方法、类#成员]
	 */
	public Book getBookByAuthor(File file, String name) throws ServiceException {
		Book myBook = null;

		if (null != file) {
			Document doc = getDocument(file);
			if (null != doc) {
				/*
				 * [author='" + name + "'] 表示只取author为name参数值的book节点
				 */
				String title = getNodeValue(doc, "//book[author='" + name + "']/title");
				String lang = getNodeValue(doc, "//book[author='" + name + "']/title/@lang");
				String author = getNodeValue(doc, "//book[author='" + name + "']/author");
				String year = getNodeValue(doc, "//book[author='" + name + "']/year");
				String price = getNodeValue(doc, "//book[author='" + name + "']/price");
				myBook = new Book(lang, title, author, year, price);
			}
		} else {
			throw new ServiceException("XMLParseManager@getBookByAuthor: " + "File is null!");
		}

		return myBook;
	}
}
