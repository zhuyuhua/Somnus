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
package com.somnus.rpc.client.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.protocol.entity.RequestProtocol;
import com.somnus.protocol.enumeration.CompressType;
import com.somnus.protocol.enumeration.PlatformType;
import com.somnus.protocol.enumeration.SDPType;
import com.somnus.protocol.utility.KeyValuePair;
import com.somnus.rpc.client.ConfigConstant;
import com.somnus.rpc.client.config.ServiceConfig;
import com.somnus.rpc.client.lb.ServerDispatcher;
import com.somnus.rpc.client.proxy.bean.Parameter;

/**
 *
 * 服务代理类，用于获取服务
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServiceProxy {

	private static Logger logger = LoggerFactory.getLogger(ServiceProxy.class);

	private ServerDispatcher dispatcher;
	private ServiceConfig config;
	private int sessionId = 1;
	private int requestTime = 0;// 超时重连次数
	private int ioreconnect = 0;// IO服务切换次数
	private int count = 0;
	private static final Object locker = new Object();
	private static final HashMap<String, ServiceProxy> Proxys = new HashMap<String, ServiceProxy>();

	/**
	 * @param serviceName
	 */
	public ServiceProxy(String serviceName) {

		config = ServiceConfig.getConfig(serviceName);

		dispatcher = new ServerDispatcher(config);

		requestTime = config.getSocketPool().getReconnectTime();
		int serverCount = 1;
		if (dispatcher.GetAllServer() != null && dispatcher.GetAllServer().size() > 0) {
			serverCount = dispatcher.GetAllServer().size();
		}

		ioreconnect = serverCount - 1;
		count = requestTime;

		if (ioreconnect > requestTime) {
			count = ioreconnect;
		}
	}

	public static ServiceProxy getProxy(String serviceName) throws Exception {
		ServiceProxy p = Proxys.get(serviceName.toLowerCase());
		if (p == null) {
			synchronized (locker) {
				p = Proxys.get(serviceName.toLowerCase());
				if (p == null) {
					p = new ServiceProxy(serviceName);
					Proxys.put(serviceName.toLowerCase(), p);
				}
			}
		}
		return p;
	}

	/**
	 * TODO
	 * 
	 * @param returnPara
	 * @param lookup
	 * @param methodName
	 * @param paras
	 * @return
	 * @since JDK 1.6
	 */
	public InvokeResult invoke(Parameter returnType, String typeName, String methodName, Parameter[] parameters) {
		long watcher = System.currentTimeMillis();

		List<KeyValuePair> listPara = new ArrayList<KeyValuePair>();
		for (Parameter p : parameters) {
			listPara.add(new KeyValuePair(p.getSimpleName(), p.getValue()));
		}

		RequestProtocol requestProtocol = new RequestProtocol(typeName, methodName, listPara);
		Protocol sendP = new Protocol(createSessionId(), (byte) config.getServiceid(), SDPType.Request,
				CompressType.UnCompress, config.getProtocol().getSerializerType(), PlatformType.Java, requestProtocol);

		Protocol receiveP = null;
		Server server = null;

		for (int i = 0; i <= count; i++) {
			server = dispatcher.GetServer();
			if (server == null) {
				logger.error("cannot get server");
				throw new Exception("cannot get server");
			}
			try {
				receiveP = server.request(sendP);
				break;
			} catch (IOException io) {
				if (count == 0 || i == ioreconnect) {
					throw io;
				}
				if (i < count && i < ioreconnect) {
					logger.error(server.getName() + " server has IOException,system will change normal server!");
					continue;
				}
			} catch (RebootException rb) {
				this.createReboot(server);
				if (count == 0 || i == ioreconnect) {
					throw new IOException("connect fail!");
				}
				if (i < count && i < ioreconnect) {
					logger.error(server.getName() + " server has reboot,system will change normal server!");
					continue;
				}
			} catch (TimeoutException te) {
				if (count == 0 || i == requestTime) {
					throw new TimeoutException("Receive data timeout or error!");
				}
				if (i < count && i < requestTime) {
					logger.error(server.getName() + " server has TimeoutException,system will change normal server!");
					continue;
				}
			} catch (Throwable ex) {
				logger.error("invoke other Exception", ex);
				throw ex;
			}
		}

		if (receiveP == null) {
			throw new Exception("userdatatype error!");
		}
	}

	private int createSessionId() {
		synchronized (this) {
			if (sessionId > ConfigConstant.MAX_SESSIONID) {
				sessionId = 1;
			}
			return sessionId++;
		}
	}

}
