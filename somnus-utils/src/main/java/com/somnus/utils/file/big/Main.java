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
package com.somnus.utils.file.big;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		TextRowDecoder decoder = new TextRowDecoder(4, comma);
		FileReader<byte[][]> reader = FileReader.create(decoder, file.listFiles());

		for (List<byte[][]> chunk : reader) {
			// do something with each chunk
		}
	}

}
