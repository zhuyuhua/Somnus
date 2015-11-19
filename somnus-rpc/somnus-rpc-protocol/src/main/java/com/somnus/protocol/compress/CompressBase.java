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
package com.somnus.protocol.compress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.protocol.enumeration.CompressType;

/**
 * 压缩基础类
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public abstract class CompressBase {

	private static Logger logger = LoggerFactory.getLogger(CompressBase.class);

	private static CompressBase sevenZip = new SevenZip();
	private static CompressBase unCompress = new UnCompress();

	public static CompressBase getInstance(CompressType ct) throws Exception {
		if (ct == CompressType.UnCompress) {
			return unCompress;
		} else if (ct == CompressType.SevenZip) {
			return sevenZip;
		}

		throw new Exception("末知的压缩格式");
	}

	public abstract byte[] unzip(byte[] buffer) throws Exception;

	public abstract byte[] zip(byte[] buffer) throws Exception;

}
