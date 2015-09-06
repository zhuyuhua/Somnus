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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class XMLParseUtilTest {

	private static Logger logger = LoggerFactory.getLogger(XMLParseUtilTest.class);

	private static Document getDoc(String fileName) {

		String path = XMLParseUtilTest.class.getResource("/").getPath() + fileName;

		Document doc = null;
		try {
			doc = XMLParseUtil.parseDocument(path);
		} catch (MalformedURLException e) {
			System.out.println("异常：传入文件路径错误！找不到要解析的文件！");
			e.printStackTrace();
		} catch (SAXParseException e) {
			System.out.println("异常：文件格式错误！无法解析！");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 入口函数
	 *
	 * @param args
	 * @see [类、类#方法、类#成员]
	 */
	public static void main(String[] args) {

		Document doc = getDoc("bookstore.xml");
		// testNodeList(doc);

	}

	public static void testNodeList(Document doc) {
		if (null != doc) {
			NodeList bookNodeList = null;
			try {
				/**
				 * [title/@lang='en']表示选取的book节点，必须满足子节点title的lang属性为en
				 */
				bookNodeList = XMLParseUtil.selectNodes(doc, "//bookstore/book[title/@lang='en']");

			} catch (XPathExpressionException e) {
				System.out.println("异常：XPath表达式错误！");
				e.printStackTrace();
			}

			if (null != bookNodeList) {
				List<Book> bookList = new ArrayList<Book>();

				for (int i = 0; i < bookNodeList.getLength(); i++) {
					Node node = bookNodeList.item(i);
					Book book = parseBookNode(node);
					bookList.add(book);
				}

				for (Book book : bookList) {
					System.out.println(book.toString());
				}
			}
		}

	}

	/**
	 * 解析单个book节点
	 *
	 * @param util
	 * @param node
	 * @return
	 * @throws XPathExpressionException
	 * @see [类、类#方法、类#成员]
	 */
	public static Book parseBookNode(Node node) {
		String lang = "";
		String title = "";
		String author = "";
		String year = "";
		String price = "";

		try {
			title = XMLParseUtil.getNodeStringValue(node, "./title");
		} catch (XPathExpressionException e) {
			System.out.println("异常：XPath表达式错误！");
			e.printStackTrace();
		}
		try {
			lang = XMLParseUtil.getNodeStringValue(node, "./title/@lang");
		} catch (XPathExpressionException e) {
			System.out.println("异常：XPath表达式错误！");
			e.printStackTrace();
		}
		try {
			author = XMLParseUtil.getNodeStringValue(node, "./author");
		} catch (XPathExpressionException e) {
			System.out.println("异常：XPath表达式错误！");
			e.printStackTrace();
		}
		try {
			year = XMLParseUtil.getNodeStringValue(node, "./year");
		} catch (XPathExpressionException e) {
			System.out.println("异常：XPath表达式错误！");
			e.printStackTrace();
		}
		try {
			price = XMLParseUtil.getNodeStringValue(node, "./price");
		} catch (XPathExpressionException e) {
			System.out.println("异常：XPath表达式错误！");
			e.printStackTrace();
		}

		Book book = new Book(lang, title, author, year, price);
		return book;
	}
}
