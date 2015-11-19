/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.somnus.protocol.entity;

import com.somnus.protocol.annotation.Member;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public class ResponseProtocol {

	@Member
	private Object result;

	@Member
	private Object[] outpara;

	public ResponseProtocol() {

	}

	public ResponseProtocol(Object result, Object[] outpara) {
		super();
		this.result = result;
		this.outpara = outpara;
	}

	public Object[] getOutpara() {
		return outpara;
	}

	public void setOutpara(Object[] outpara) {
		this.outpara = outpara;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
