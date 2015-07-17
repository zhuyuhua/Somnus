/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.cache.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public class EhCacheConfiguration {

	private static Logger logger = LoggerFactory.getLogger(EhCacheConfiguration.class);

	private String name;
	private int maxElementsInMemory;
	private boolean overflowToDisk;
	private boolean eternal;
	private long timeToLiveSeconds;
	private long timeToIdleSeconds;

	private EhCacheConfiguration() {

	}

	public String name() {
		return name;
	}

	public int maxElementsInMemory() {
		return maxElementsInMemory;
	}

	public boolean overflowToDisk() {
		return overflowToDisk;
	}

	public boolean eternal() {
		return eternal;
	}

	public long timeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public long timeToIdleSeconds() {
		return timeToIdleSeconds;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private EhCacheConfiguration configuration;

		public Builder() {
			configuration = new EhCacheConfiguration();
		}

		public Builder name(String name) {
			configuration.name = name;
			return this;
		}

		public Builder maxElementsInMemory(int maxElementsInMemory) {
			configuration.maxElementsInMemory = maxElementsInMemory;
			return this;
		}

		public Builder overflowToDisk(boolean overflowToDisk) {
			configuration.overflowToDisk = overflowToDisk;
			return this;
		}

		public Builder eternal(boolean eternal) {
			configuration.eternal = eternal;
			return this;
		}

		public Builder timeToLiveSeconds(long timeToLiveSeconds) {
			configuration.timeToLiveSeconds = timeToLiveSeconds;
			return this;
		}

		public Builder timeToIdleSeconds(long timeToIdleSeconds) {
			configuration.timeToIdleSeconds = timeToIdleSeconds;
			return this;
		}

		public EhCacheConfiguration build() {
			if (configuration.name == null || configuration.name.isEmpty()) {
				throw new IllegalStateException("Cache name is empty!");
			}
			return configuration;
		}
	}
}
