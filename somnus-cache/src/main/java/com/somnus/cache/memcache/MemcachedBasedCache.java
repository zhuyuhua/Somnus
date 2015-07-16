/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 */
package com.somnus.cache.memcache;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.cache.Cache;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 基于Memcached的缓存实现
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 */
public class MemcachedBasedCache implements Cache {

	private static Logger logger = LoggerFactory.getLogger(MemcachedBasedCache.class);

	private MemCachedClient mcc;

	private String[] servers;

	private int initConn = 100;

	private int minConn = 100;

	private int maxConn = 250;

	private int connectTimeout = 3000;

	private String poolName;

	private boolean initialized = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#get(java.lang.String)
	 */
	@Override
	public Object get(String key) {
		init();
		Object obj = mcc.get(key);
		debug("命中缓存：{}，key：{}", new Object[] { (obj != null), key });
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#get(java.lang.String[])
	 */
	@Override
	public Map<String, Object> get(String... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#put(java.lang.String, java.lang.Object,
	 * java.util.Date)
	 */
	@Override
	public void put(String key, Object value, Date expiry) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#put(java.lang.String, java.lang.Object, long)
	 */
	@Override
	public void put(String key, Object value, long living) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#remove(java.lang.String)
	 */
	@Override
	public boolean remove(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.somnus.cache.Cache#containsKey(java.lang.String)
	 */
	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public void init() {

		if (initialized) {
			return;
		}
		if (servers == null || servers.length == 0) {
			throw new IllegalStateException("Must assign memcached server address");
		}

		for (String server : servers) {
			logger.info("准备为Memcached服务器{}创建客户端...", server);
			logger.info("最小连接数为：{}", minConn);
			logger.info("最大接数为：{}", maxConn);
			logger.info("初始化连接数为：{}", initConn);
			logger.info("连接超时时间为：{}毫秒", connectTimeout);
		}
		prepareClient();
		initialized = true;

	}

	protected void prepareClient() {

		SockIOPool pool = null;
		if (StringUtils.isBlank(getPoolName())) {
			pool = SockIOPool.getInstance();
			mcc = new MemCachedClient();
		} else {
			pool = SockIOPool.getInstance(getPoolName());
			mcc = new MemCachedClient(getPoolName());
		}

		pool.setServers(servers);
		pool.setInitConn(getInitConn());
		pool.setMinConn(getMinConn());
		pool.setMaxConn(getMaxConn());
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(getConnectTimeout());

		pool.initialize();

		Map map = mcc.stats(servers);
		Set keys = map.keySet();
		if (keys.size() == 0) {
			logger.error("客户端创建遇到错误，无法创建。");
		}
		for (Object key : keys) {
			logger.info("客户端创建完成。key = 【{}】", key);
		}

	}

	/**
	 * mcc
	 *
	 * @return the mcc
	 * @since 1.0.0
	 */

	public MemCachedClient getMcc() {
		return mcc;
	}

	/**
	 * @param mcc
	 *            the mcc to set
	 */
	public void setMcc(MemCachedClient mcc) {
		this.mcc = mcc;
	}

	/**
	 * servers
	 *
	 * @return the servers
	 * @since 1.0.0
	 */

	public String[] getServers() {
		return servers;
	}

	/**
	 * @param servers
	 *            the servers to set
	 */
	public void setServers(String[] servers) {
		this.servers = servers;
	}

	/**
	 * initConn
	 *
	 * @return the initConn
	 * @since 1.0.0
	 */

	public int getInitConn() {
		return initConn;
	}

	/**
	 * @param initConn
	 *            the initConn to set
	 */
	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	/**
	 * minConn
	 *
	 * @return the minConn
	 * @since 1.0.0
	 */

	public int getMinConn() {
		return minConn;
	}

	/**
	 * @param minConn
	 *            the minConn to set
	 */
	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	/**
	 * maxConn
	 *
	 * @return the maxConn
	 * @since 1.0.0
	 */

	public int getMaxConn() {
		return maxConn;
	}

	/**
	 * @param maxConn
	 *            the maxConn to set
	 */
	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	/**
	 * connectTimeout
	 *
	 * @return the connectTimeout
	 * @since 1.0.0
	 */

	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param connectTimeout
	 *            the connectTimeout to set
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * poolName
	 *
	 * @return the poolName
	 * @since 1.0.0
	 */

	public String getPoolName() {
		return poolName;
	}

	/**
	 * @param poolName
	 *            the poolName to set
	 */
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	/**
	 * initialized
	 *
	 * @return the initialized
	 * @since 1.0.0
	 */

	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized
	 *            the initialized to set
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

}
