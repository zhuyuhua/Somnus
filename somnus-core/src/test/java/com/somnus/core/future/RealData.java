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
package com.somnus.core.future;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月28日
 */
public class RealData implements Data {

	protected String result;

	public RealData(String para) {
		// 构造比较慢
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
			result = sb.toString();
		}
	}

	@Override
	public String getResult() {

		return result;
	}
}
