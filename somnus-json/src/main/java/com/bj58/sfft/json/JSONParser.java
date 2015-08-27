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
package com.bj58.sfft.json;

import java.io.InputStream;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.parser.JSONLexer;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年8月27日
 */
public class JSONParser {

	private static Logger logger = LoggerFactory.getLogger(JSONParser.class);
	private JSONParserAntlr parser;
	private String streamName = "[unknown]";

	public JSONParser(InputStream aStream, String aStreamName) {
		JSONLexer lexer = new JSONLexer(aStream);
		this.parser = new JSONParserAntlr(lexer);
		if (aStreamName != null)
			this.streamName = aStreamName;
	}

	public JSONParser(InputStream aStream) {
		this(aStream, null);
	}

	public JSONParser(Reader aReader, String aStreamName) {
		JSONLexer lexer = new JSONLexer(aReader);
		this.parser = new JSONParserAntlr(lexer);
		if (aStreamName != null)
			this.streamName = aStreamName;
	}

	public JSONParser(Reader aReader) {
		this(aReader, null);
	}

	public JSONValue nextValue() throws TokenStreamException, RecognitionException {
		return this.parser.value(this.streamName);
	}
}
