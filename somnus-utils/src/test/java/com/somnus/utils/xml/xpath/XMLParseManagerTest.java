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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class XMLParseManagerTest {

	private static Logger logger = LoggerFactory
			.getLogger(XMLParseManagerTest.class);

	XMLParseManager xmlMgr;

	@Before
	public void setUp() throws Exception {
		xmlMgr = new XMLParseManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMyBook() throws ServiceException {
		String path = XMLParseManager.class.getResource("/").getPath()
				+ "bookstore.xml";
		File xmlfile = new File(path);
		Book actualBook = getBookByAuthor(xmlfile, "ChenFeng");
		Assert.assertNotNull(actualBook);

		Book expectedBook = new Book("zh", "哲学：心灵，宇宙", "ChenFeng", 2010, 96.0);
		Assert.assertEquals(expectedBook, actualBook);
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
			Document doc = xmlMgr.getDocument(file);
			if (null != doc) {
				/*
				 * [author='" + name + "'] 表示只取author为name参数值的book节点
				 */
				String title = xmlMgr.getNodeValue(doc, "//book[author='"
						+ name + "']/title");
				String lang = xmlMgr.getNodeValue(doc, "//book[author='" + name
						+ "']/title/@lang");
				String author = xmlMgr.getNodeValue(doc, "//book[author='"
						+ name + "']/author");
				String year = xmlMgr.getNodeValue(doc, "//book[author='" + name
						+ "']/year");
				String price = xmlMgr.getNodeValue(doc, "//book[author='"
						+ name + "']/price");
				myBook = new Book(lang, title, author, year, price);
			}
		} else {
			throw new ServiceException("XMLParseManager@getBookByAuthor: "
					+ "File is null!");
		}

		return myBook;
	}
}
