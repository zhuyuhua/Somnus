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

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.somnus.rpc.client.config.profile.KeyProfile;
import com.somnus.rpc.client.config.profile.ProtocolProfile;
import com.somnus.rpc.client.config.profile.ServerProfile;
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
	XMLParseUtil parseUtil = null;
	Document doc = null;
	Node serviceNode = null;

	@Before
	public void Before() throws Exception {

		Document doc = XMLParseUtil.parseDocument(client_config);
		// <Service>
		serviceNode = XMLParseUtil.selectSingleNode(doc, "//Service[@name='servername']");
	}

	@Test
	public void testSocketPoolProfile() throws Exception {

		// <SocketPool />
		Node socketNode = XMLParseUtil.selectSingleNode(serviceNode, "Commmunication/SocketPool");

		SocketPoolProfile poolProfile = new SocketPoolProfile(socketNode);
		System.out.println(poolProfile);
	}

	@Test
	public void testProtocolProfile() throws Exception {

		// <Protocol />
		Node protocolNode = XMLParseUtil.selectSingleNode(serviceNode, "Commmunication/Protocol");

		ProtocolProfile profile = new ProtocolProfile(protocolNode);

		System.out.println(profile);

	}

	@Test
	public void testServerProfile() throws Exception {

		NodeList servers = XMLParseUtil.selectNodes(serviceNode, "Loadbalance/Server/add");

		ServerProfile profile;
		for (int i = 0; i < servers.getLength(); i++) {
			profile = new ServerProfile(servers.item(i));
			System.out.println(profile);
		}

	}

	@Test
	public void testKeyProfile() throws Exception {
		Node keyNode = parseUtil.selectSingleNode(serviceNode, "Secure/Key");
		KeyProfile profile = new KeyProfile(keyNode);
		System.out.println(profile);
	}

}
