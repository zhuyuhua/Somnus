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
package com.somnus.rpc.client.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.somnus.rpc.client.ConfigConstant;
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
public final class ServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

	private String servicename;
	private int serviceid;
	private SocketPoolProfile socketPool;
	private ProtocolProfile protocol;
	private List<ServerProfile> servers;
	private KeyProfile keyProfile;

	/**
	 * TODO
	 *
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public static ServiceConfig getConfig(String serviceName) throws Exception {
		File f = new File(ConfigConstant.DEFAULT_CONFIG_PATH);
		if (!f.exists()) {
			throw new Exception("client.config.xml not found:" + ConfigConstant.DEFAULT_CONFIG_PATH);
		}

		ServiceConfig config = new ServiceConfig();

		Document doc = XMLParseUtil.parseDocument(ConfigConstant.DEFAULT_CONFIG_PATH);

		Node serviceNode = XMLParseUtil.selectSingleNode(doc, "//Service[@name='" + serviceName + "']");
		if (serviceNode == null) {
			throw new Exception("没有发现" + serviceName + "服务节点!");
		}
		setServiceNode(config, serviceNode);

		Node socketNode = XMLParseUtil.selectSingleNode(serviceNode, "Commmunication/SocketPool");
		if (socketNode == null) {
			throw new Exception("服务节点" + serviceName + "没有发现Commmunication/SocketPool配置!");
		}
		config.socketPool = new SocketPoolProfile(socketNode);

		Node protocolNode = XMLParseUtil.selectSingleNode(serviceNode, "Commmunication/Protocol");
		if (protocolNode == null) {
			throw new Exception("服务节点" + serviceName + "没有发现Commmunication/Protocol配置!");
		}
		config.protocol = new ProtocolProfile(protocolNode);

		Node keyNode = XMLParseUtil.selectSingleNode(serviceNode, "Secure/Key");
		config.keyProfile = new KeyProfile(keyNode);

		NodeList serverNodeList = XMLParseUtil.selectNodes(serviceNode, "Loadbalance/Server/add");
		if (serverNodeList == null || serverNodeList.getLength() == 0) {
			throw new Exception("服务节点" + serviceName + "没有发现Loadbalance/Server/add配置!");
		}
		List<ServerProfile> servers = new ArrayList<ServerProfile>();
		for (int i = 0; i < serverNodeList.getLength(); i++) {
			servers.add(new ServerProfile(serverNodeList.item(i)));
		}
		config.servers = servers;
		config.servicename = serviceName;

		return config;
	}

	/**
	 * TODO
	 *
	 * @param serviceNode
	 * @throws Exception
	 */
	private static void setServiceNode(ServiceConfig config, Node serviceNode) throws Exception {

		config.servicename = serviceNode.getAttributes().getNamedItem("name").getNodeValue();
		Node idNode = serviceNode.getAttributes().getNamedItem("id");
		if (idNode == null) {
			throw new Exception("<Service>节点的id属性为空");
		}
		config.serviceid = Integer.parseInt(idNode.getNodeValue());

	}

	/**
	 * servicename
	 *
	 * @return servicename
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * @param servicename
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	/**
	 * serviceid
	 *
	 * @return serviceid
	 */
	public int getServiceid() {
		return serviceid;
	}

	/**
	 * @param serviceid
	 */
	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
	}

	/**
	 * socketPool
	 *
	 * @return socketPool
	 */
	public SocketPoolProfile getSocketPool() {
		return socketPool;
	}

	/**
	 * @param socketPool
	 */
	public void setSocketPool(SocketPoolProfile socketPool) {
		this.socketPool = socketPool;
	}

	/**
	 * protocol
	 *
	 * @return protocol
	 */
	public ProtocolProfile getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 */
	public void setProtocol(ProtocolProfile protocol) {
		this.protocol = protocol;
	}

	/**
	 * servers
	 *
	 * @return servers
	 */
	public List<ServerProfile> getServers() {
		return servers;
	}

	/**
	 * @param servers
	 */
	public void setServers(List<ServerProfile> servers) {
		this.servers = servers;
	}

	/**
	 * keyProfile
	 *
	 * @return keyProfile
	 */
	public KeyProfile getKeyProfile() {
		return keyProfile;
	}

	/**
	 * @param keyProfile
	 */
	public void setKeyProfile(KeyProfile keyProfile) {
		this.keyProfile = keyProfile;
	}

}
