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
package com.somnus.rpc.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.somnus.rpc.client.config.profile.SocketPoolProfile;
import com.somnus.utils.xml.xpath.XMLParseUtil;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SocketConfigTest {

	private static Logger logger = LoggerFactory.getLogger(SocketConfigTest.class);

	private static String client_config = SocketConfigTest.class.getResource("/").getPath() + "client.config.xml";

	@Test
	public void testSocketPoolProfile() throws Exception {

		XMLParseUtil parseUtil = new XMLParseUtil();
		Document doc = parseUtil.parseDocument(client_config);

		// <service>
		Node serviceNode = parseUtil.selectSingleNode(doc, "//Service[@name='servername']");

		// <SocketPool />
		Node socketNode = parseUtil.selectSingleNode(serviceNode, "Commmunication/SocketPool");

		SocketPoolProfile poolProfile = new SocketPoolProfile(socketNode);
		System.out.println(poolProfile);
	}

}
