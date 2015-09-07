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
package com.somnus.server.exception;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.server.common.ErrorState;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ServiceFrameException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(ServiceFrameException.class);

	private ErrorState state;

	private String errorMsg;

	private String fromIP;

	private String toIP;

	private Object sdp;

	/**
	 * create exception
	 *
	 * @param errorMsg
	 * @param fromIP
	 * @param toIP
	 * @param mb
	 * @param state
	 * @param cause
	 */
	public ServiceFrameException(String errorMsg, String fromIP, String toIP, Object sdp, ErrorState state,
			Throwable cause) {
		super(errorMsg, cause);
		this.setState(state);
		this.setErrorMsg(errorMsg);
		this.setFromIP(fromIP);
		this.setToIP(toIP);
		this.setSdp(sdp);
	}

	public ServiceFrameException(String errorMsg, ErrorState state, Throwable cause) {
		this(errorMsg, "", "", null, state, cause);
	}

	public ServiceFrameException(String errorMsg, ErrorState state) {
		this(errorMsg, "", "", null, state, null);
	}

	public ServiceFrameException(ErrorState state, Throwable cause) {
		this("", "", "", null, state, cause);
	}

	public ServiceFrameException(ErrorState state) {
		this("", "", "", null, state, null);
	}

	@Override
	public void printStackTrace() {
		System.out.println("-------------------------begin-----------------------------");
		System.out.println("fromIP:" + this.getFromIP());
		System.out.println("toIP:" + this.getToIP());
		System.out.println("state:" + this.getState().toString());
		System.out.println("errorMsg:" + this.getErrorMsg());
		System.out.println("MessageBodyBase:");

		// if(sdp.getClass() == RequestProtocol.class) {
		// RequestProtocol request = (RequestProtocol)this.sdp;
		// System.out.println("Server.Lookup:" + request.getLookup());
		// System.out.println("Server.MethodName:" + request.getMethodName());
		// System.out.println("Server.ParaKVList:");
		// for(KeyValuePair kv : request.getParaKVList()){
		// System.out.println("key:" + kv.getKey() + "---value:"+kv.getValue());
		// }
		// }
		super.printStackTrace();
		System.out.println("--------------------------end------------------------------");
	}

	public void setState(ErrorState state) {
		this.state = state;
	}

	public ErrorState getState() {
		return state;
	}

	public String getFromIP() {
		return fromIP;
	}

	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}

	public String getToIP() {
		return toIP;
	}

	public void setToIP(String toIP) {
		this.toIP = toIP;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Object getSdp() {
		return sdp;
	}

	public void setSdp(Object sdp) {
		this.sdp = sdp;
	}
}
