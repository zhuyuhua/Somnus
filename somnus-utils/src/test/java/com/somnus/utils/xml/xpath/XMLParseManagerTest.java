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

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class XMLParseManagerTest {

	private static Logger logger = LoggerFactory.getLogger(XMLParseManagerTest.class);

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
		String path = XMLParseManager.class.getResource("/").getPath() + "bookstore.xml";
		File xmlfile = new File(path);
		Book actualBook = xmlMgr.getBookByAuthor(xmlfile, "ChenFeng");
		Assert.assertNotNull(actualBook);

		Book expectedBook = new Book("zh", "哲学：心灵，宇宙", "ChenFeng", 2010, 96.0);
		Assert.assertEquals(expectedBook, actualBook);
	}
}
