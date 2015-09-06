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

import java.nio.charset.Charset;

import javax.naming.directory.NoSuchAttributeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.somnus.protocol.CompressType;
import com.somnus.protocol.SerializeType;
import com.somnus.protocol.serializer.SerializeBase;

/**
 *
 * 协议类，对应client.config.xml的<Protocol>
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class ProtocolProfile {

	private static Logger logger = LoggerFactory.getLogger(ProtocolProfile.class);

	private SerializeType serializerType;
	private SerializeBase serialize;
	public Charset Encoder;
	public byte serviceID;
	public CompressType compress;

	public ProtocolProfile(Node node) throws Exception {

		Node attrSer = node.getAttributes().getNamedItem("serialize");
		if (attrSer == null) {
			throw new ExceptionInInitializerError("Not find attrbuts:" + node.getNodeName() + "[@'serialize']");
		}
		String value = attrSer.getNodeValue().trim().toLowerCase();
		if (value.equalsIgnoreCase("binary")) {
			serializerType = SerializeType.JAVABinary;
		} else if (value.equalsIgnoreCase("json")) {
			serializerType = SerializeType.JSON;
		} else if (value.equalsIgnoreCase("xml")) {
			serializerType = SerializeType.XML;
		} else if (value.equalsIgnoreCase("gaea")) {
			serializerType = SerializeType.SomnusBinary;
		} else {
			throw new NoSuchAttributeException("Protocol not supported " + value + "!");
		}

		this.serialize = SerializeBase.getInstance(serializerType);
		attrSer = node.getAttributes().getNamedItem("encoder");
		if (attrSer == null) {
			this.Encoder = Charset.forName("UTF-8");
		} else {
			this.Encoder = Charset.forName(attrSer.getNodeValue());
		}
		this.serialize.setEncoder(this.Encoder);
		serviceID = Byte
				.parseByte(node.getParentNode().getParentNode().getAttributes().getNamedItem("id").getNodeValue());// TODO
																													// 待检验
		compress = Enum.valueOf(CompressType.class, node.getAttributes().getNamedItem("compressType").getNodeValue());
	}

	public Charset getEncoder() {
		return Encoder;
	}

	public CompressType getCompress() {
		return compress;
	}

	public SerializeBase getSerializer() {
		return serialize;
	}

	public SerializeType getSerializerType() {
		return serializerType;
	}

	public byte getServiceID() {
		return serviceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProtocolProfile [serializerType=" + serializerType + ", serialize=" + serialize + ", Encoder=" + Encoder
				+ ", serviceID=" + serviceID + ", compress=" + compress + "]";
	}

}
