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

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class TextRowDecoder implements Decoder<byte[][]> {

	private static Logger logger = LoggerFactory.getLogger(TextRowDecoder.class);

	private static final byte LF = 10;
	private final int numFields;
	private final byte delimiter;

	public TextRowDecoder(int numFields, byte delimiter) {
		this.numFields = numFields;
		this.delimiter = delimiter;
	}

	@Override
	public byte[][] decode(ByteBuffer buffer) {
		int lineStartPos = buffer.position();
		int limit = buffer.limit();
		while (buffer.hasRemaining()) {
			byte b = buffer.get();
			if (b == LF) { // reached line feed so parse line
				int lineEndPos = buffer.position();
				// set positions for one row duplication
				if (buffer.limit() < lineEndPos + 1) {
					buffer.position(lineStartPos).limit(lineEndPos);
				} else {
					buffer.position(lineStartPos).limit(lineEndPos + 1);
				}
				byte[][] entry = parseRow(buffer.duplicate());
				if (entry != null) {
					// reset main buffer
					buffer.position(lineEndPos);
					buffer.limit(limit);
					// set start after LF
					lineStartPos = lineEndPos;
				}
				return entry;
			}
		}
		buffer.position(lineStartPos);
		return null;
	}

	public byte[][] parseRow(ByteBuffer buffer) {
		int fieldStartPos = buffer.position();
		int fieldEndPos = 0;
		int fieldNumber = 0;
		byte[][] fields = new byte[numFields][];
		while (buffer.hasRemaining()) {
			byte b = buffer.get();
			if (b == delimiter || b == LF) {
				fieldEndPos = buffer.position();
				// save limit
				int limit = buffer.limit();
				// set positions for one row duplication
				buffer.position(fieldStartPos).limit(fieldEndPos);
				fields[fieldNumber] = parseField(buffer.duplicate(), fieldNumber, fieldEndPos - fieldStartPos - 1);
				fieldNumber++;
				// reset main buffer
				buffer.position(fieldEndPos);
				buffer.limit(limit);
				// set start after LF
				fieldStartPos = fieldEndPos;
			}
			if (fieldNumber == numFields) {
				return fields;
			}
		}
		return null;
	}

	private byte[] parseField(ByteBuffer buffer, int pos, int length) {
		byte[] field = new byte[length];
		for (int i = 0; i < field.length; i++) {
			field[i] = buffer.get();
		}
		return field;
	}

}
