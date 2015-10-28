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

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @param <T>
 * @since 0.0.1
 */
public class FileReader<T> implements Iterable<List<T>> {

	private static Logger logger = LoggerFactory.getLogger(FileReader.class);

	private static final long CHUNK_SIZE = 4096;
	private final Decoder<T> decoder;
	private Iterator<File> files;

	private FileReader(Decoder<T> decoder, File... files) {
		this(decoder, Arrays.asList(files));
	}

	private FileReader(Decoder<T> decoder, List<File> files) {
		this.files = files.iterator();
		this.decoder = decoder;
	}

	public static <T> FileReader<T> create(Decoder<T> decoder, List<File> files) {
		return new FileReader<T>(decoder, files);
	}

	public static <T> FileReader<T> create(Decoder<T> decoder, File... files) {
		return new FileReader<T>(decoder, files);
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new Iterator<List<T>>() {
			private List<T> entries;
			private long chunkPos = 0;
			private MappedByteBuffer buffer;
			private FileChannel channel;

			@Override
			public boolean hasNext() {
				if (buffer == null || !buffer.hasRemaining()) {
					buffer = nextBuffer(chunkPos);
					if (buffer == null) {
						return false;
					}
				}
				T result = null;
				while ((result = decoder.decode(buffer)) != null) {
					if (entries == null) {
						entries = new ArrayList<T>();
					}
					entries.add(result);
				}
				// set next MappedByteBuffer chunk
				chunkPos += buffer.position();
				buffer = null;
				if (entries != null) {
					return true;
				} else {
					Closeables.closeQuietly(channel);

					return false;
				}
			}

			private MappedByteBuffer nextBuffer(long position) {
				try {
					if (channel == null || channel.size() == position) {
						if (channel != null) {
							Closeables.closeQuietly(channel);
							channel = null;
						}
						if (files.hasNext()) {
							File file = files.next();
							channel = new RandomAccessFile(file, "r").getChannel();
							chunkPos = 0;
							position = 0;
						} else {
							return null;
						}
					}
					long chunkSize = CHUNK_SIZE;
					if (channel.size() - position < chunkSize) {
						chunkSize = channel.size() - position;
					}
					return channel.map(FileChannel.MapMode.READ_ONLY, chunkPos, chunkSize);
				} catch (IOException e) {
					Closeables.closeQuietly(channel);
					throw new RuntimeException(e);
				}
			}

			@Override
			public List<T> next() {
				List<T> res = entries;
				entries = null;
				return res;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
