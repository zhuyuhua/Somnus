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
package com.somnus.rpc.client.config.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.somnus.rpc.client.ConfigConstant;
import com.somnus.utils.time.TimeSpanHelper;

/**
 *
 * 服务端连接信息，对应client.config.xml的<Loadbalance/Server/add>
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServerProfile {

	private static Logger logger = LoggerFactory.getLogger(ServerProfile.class);

	private String name; // 服务端名称
	private String host; // 服务端IP
	private int port; // 服务端端口
	private int deadTimeout;// 失效时间，单位毫秒
	private float weithtRate;// 权重，默认定义为1
	private int maxCurrrentUser;// 最大当前用户数

	public ServerProfile(Node node) {
		NamedNodeMap attributes = node.getAttributes();
		this.name = attributes.getNamedItem("name").getNodeValue();
		this.host = attributes.getNamedItem("host").getNodeValue();
		this.port = Integer.parseInt(attributes.getNamedItem("port").getNodeValue());
		Node atribute = attributes.getNamedItem("weithtRate");
		if (atribute != null) {
			this.weithtRate = Float.parseFloat(atribute.getNodeValue().toString());
		} else {
			this.weithtRate = 1;
		}
		atribute = node.getParentNode().getAttributes().getNamedItem("deadTimeout");
		if (atribute != null) {
			// 设置最小值为30s
			int dtime = TimeSpanHelper.getIntFromTimeSpan(atribute.getNodeValue().toString());
			this.deadTimeout = dtime;
		} else {
			this.deadTimeout = ConfigConstant.DEFAULT_DEAD_TIMEOUT;
		}

		this.maxCurrrentUser = Integer.parseInt(attributes.getNamedItem("maxCurrentUser").getNodeValue());
	}

	/**
	 * name
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * host
	 *
	 * @return host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * port
	 *
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * deadTimeout
	 *
	 * @return deadTimeout
	 */
	public int getDeadTimeout() {
		return deadTimeout;
	}

	/**
	 * @param deadTimeout
	 */
	public void setDeadTimeout(int deadTimeout) {
		this.deadTimeout = deadTimeout;
	}

	/**
	 * weithtRate
	 *
	 * @return weithtRate
	 */
	public float getWeithtRate() {
		return weithtRate;
	}

	/**
	 * @param weithtRate
	 */
	public void setWeithtRate(float weithtRate) {
		this.weithtRate = weithtRate;
	}

	/**
	 * maxCurrrentUser
	 *
	 * @return maxCurrrentUser
	 */
	public int getMaxCurrrentUser() {
		return maxCurrrentUser;
	}

	/**
	 * @param maxCurrrentUser
	 */
	public void setMaxCurrrentUser(int maxCurrrentUser) {
		this.maxCurrrentUser = maxCurrrentUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServerProfile [name=" + name + ", host=" + host + ", port=" + port + ", deadTimeout=" + deadTimeout
				+ ", weithtRate=" + weithtRate + ", maxCurrrentUser=" + maxCurrrentUser + "]";
	}

}
