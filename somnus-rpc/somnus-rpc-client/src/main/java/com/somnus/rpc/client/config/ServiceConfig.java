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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.rpc.client.config.profile.ProtocolProfile;
import com.somnus.rpc.client.config.profile.SocketPoolProfile;

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
	private SocketPoolProfile SocketPool;
	private ProtocolProfile protocol;
	private List<ServerProfile> servers;
	private KeyProfile SecureKey;// 授权配置文件

	public KeyProfile getSecureKey() {
		return SecureKey;
	}

	public void setSecureKey(KeyProfile secureKey) {
		SecureKey = secureKey;
	}

	private ServiceConfig() {
	}

	public SocketPoolProfile getSocketPool() {
		return SocketPool;
	}

	public ProtocolProfile getProtocol() {
		return protocol;
	}

	public List<ServerProfile> getServers() {
		return servers;
	}

	public int getServiceid() {
		return serviceid;
	}

	public String getServicename() {
		return servicename;
	}

}
