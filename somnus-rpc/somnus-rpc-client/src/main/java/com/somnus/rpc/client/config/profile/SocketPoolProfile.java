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

import org.w3c.dom.Node;

import com.somnus.rpc.client.ClientConfigConstant;
import com.somnus.utils.time.TimeSpanHelper;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class SocketPoolProfile {

	private int minPoolSize;
	private int maxPoolSize;
	private int sendTimeout;
	private int receiveTimeout;
	private int waitTimeout;
	private boolean nagle;
	private int shrinkInterval;
	private int bufferSize;
	private int connectionTimeout = 3000;
	private int maxPakageSize;
	private boolean _protected;
	private int reconnectTime = 0;

	private int recvBufferSize;
	private int sendBufferSize;

	public SocketPoolProfile(Node node) {

		this.minPoolSize = getNodeValueForInt(node, "minPoolSize");
		this.maxPoolSize = getNodeValueForInt(node, "maxPoolSize");
		this.sendTimeout = TimeSpanHelper.getIntFromTimeSpan(getNodeValue(node, "sendTimeout"));
		this.receiveTimeout = TimeSpanHelper.getIntFromTimeMsSpan(getNodeValue(node, "receiveTimeout"));
		this.waitTimeout = TimeSpanHelper.getIntFromTimeSpan(getNodeValue(node, "waitTimeout"));
		this.nagle = Boolean.parseBoolean(getNodeValue(node, "nagle"));
		this.shrinkInterval = TimeSpanHelper.getIntFromTimeSpan(getNodeValue(node, "autoShrink"));
		this.bufferSize = getNodeValueForInt(node, "bufferSize");

		if (bufferSize < ClientConfigConstant.DEFAULT_BUFFER_SIZE) {
			bufferSize = ClientConfigConstant.DEFAULT_BUFFER_SIZE;
		}

		Node nProtected = node.getAttributes().getNamedItem("protected");
		if (nProtected == null) {
			this._protected = ClientConfigConstant.DEFAULT_PROTECTED;
		} else {
			this._protected = Boolean.parseBoolean(nProtected.getNodeValue());
		}

		Node nPackage = node.getAttributes().getNamedItem("maxPakageSize");
		if (nPackage == null) {
			this.maxPakageSize = ClientConfigConstant.DEFAULT_MAX_PAKAGE_SIZE;
		} else {
			this.maxPakageSize = Integer.parseInt(nPackage.getNodeValue());
		}

		Node nReconnectTime = node.getAttributes().getNamedItem("reconnectTime");
		if (nReconnectTime != null) {
			this.reconnectTime = Integer.parseInt(nReconnectTime.getNodeValue());
			if (reconnectTime < 0) {
				reconnectTime = 0;
			}
		}

		this.recvBufferSize = 1024 * 1024 * 8;
		this.sendBufferSize = 1024 * 1024 * 8;
	}

	private int getNodeValueForInt(Node node, String nameItem) {
		return Integer.parseInt(node.getAttributes().getNamedItem(nameItem).getNodeValue());
	}

	private String getNodeValue(Node node, String nameItem) {
		return node.getAttributes().getNamedItem(nameItem).getNodeValue();
	}

	/**
	 * minPoolSize
	 *
	 * @return minPoolSize
	 */
	public int getMinPoolSize() {
		return minPoolSize;
	}

	/**
	 * @param minPoolSize
	 */
	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	/**
	 * maxPoolSize
	 *
	 * @return maxPoolSize
	 */
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * @param maxPoolSize
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	/**
	 * sendTimeout
	 *
	 * @return sendTimeout
	 */
	public int getSendTimeout() {
		return sendTimeout;
	}

	/**
	 * @param sendTimeout
	 */
	public void setSendTimeout(int sendTimeout) {
		this.sendTimeout = sendTimeout;
	}

	/**
	 * receiveTimeout
	 *
	 * @return receiveTimeout
	 */
	public int getReceiveTimeout() {
		return receiveTimeout;
	}

	/**
	 * @param receiveTimeout
	 */
	public void setReceiveTimeout(int receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	/**
	 * waitTimeout
	 *
	 * @return waitTimeout
	 */
	public int getWaitTimeout() {
		return waitTimeout;
	}

	/**
	 * @param waitTimeout
	 */
	public void setWaitTimeout(int waitTimeout) {
		this.waitTimeout = waitTimeout;
	}

	/**
	 * nagle
	 *
	 * @return nagle
	 */
	public boolean isNagle() {
		return nagle;
	}

	/**
	 * @param nagle
	 */
	public void setNagle(boolean nagle) {
		this.nagle = nagle;
	}

	/**
	 * shrinkInterval
	 *
	 * @return shrinkInterval
	 */
	public int getShrinkInterval() {
		return shrinkInterval;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SocketPoolProfile [minPoolSize=" + minPoolSize + ", maxPoolSize=" + maxPoolSize + ", sendTimeout="
				+ sendTimeout + ", receiveTimeout=" + receiveTimeout + ", waitTimeout=" + waitTimeout + ", nagle="
				+ nagle + ", shrinkInterval=" + shrinkInterval + ", bufferSize=" + bufferSize + ", connectionTimeout="
				+ connectionTimeout + ", maxPakageSize=" + maxPakageSize + ", _protected=" + _protected
				+ ", reconnectTime=" + reconnectTime + ", recvBufferSize=" + recvBufferSize + ", sendBufferSize="
				+ sendBufferSize + "]";
	}

	/**
	 * @param shrinkInterval
	 */
	public void setShrinkInterval(int shrinkInterval) {
		this.shrinkInterval = shrinkInterval;
	}

	/**
	 * bufferSize
	 *
	 * @return bufferSize
	 */
	public int getBufferSize() {
		return bufferSize;
	}

	/**
	 * @param bufferSize
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * connectionTimeout
	 *
	 * @return connectionTimeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * maxPakageSize
	 *
	 * @return maxPakageSize
	 */
	public int getMaxPakageSize() {
		return maxPakageSize;
	}

	/**
	 * @param maxPakageSize
	 */
	public void setMaxPakageSize(int maxPakageSize) {
		this.maxPakageSize = maxPakageSize;
	}

	/**
	 * _protected
	 *
	 * @return _protected
	 */
	public boolean is_protected() {
		return _protected;
	}

	/**
	 * @param _protected
	 */
	public void set_protected(boolean _protected) {
		this._protected = _protected;
	}

	/**
	 * reconnectTime
	 *
	 * @return reconnectTime
	 */
	public int getReconnectTime() {
		return reconnectTime;
	}

	/**
	 * @param reconnectTime
	 */
	public void setReconnectTime(int reconnectTime) {
		this.reconnectTime = reconnectTime;
	}

	/**
	 * recvBufferSize
	 *
	 * @return recvBufferSize
	 */
	public int getRecvBufferSize() {
		return recvBufferSize;
	}

	/**
	 * @param recvBufferSize
	 */
	public void setRecvBufferSize(int recvBufferSize) {
		this.recvBufferSize = recvBufferSize;
	}

	/**
	 * sendBufferSize
	 *
	 * @return sendBufferSize
	 */
	public int getSendBufferSize() {
		return sendBufferSize;
	}

	/**
	 * @param sendBufferSize
	 */
	public void setSendBufferSize(int sendBufferSize) {
		this.sendBufferSize = sendBufferSize;
	}

}
