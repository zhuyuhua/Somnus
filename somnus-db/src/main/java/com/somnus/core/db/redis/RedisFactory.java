package com.somnus.core.db.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.somnus.utils.conf.SystemConfigUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {

	private static Map<String, JedisPool> jedisPools = new HashMap<String, JedisPool>();

	public static JedisPool initJedisPool(String jedisName) {
		JedisPool jedisPool = jedisPools.get(jedisName);
		if (jedisPool == null) {
			String host = SystemConfigUtil.getProperty(jedisName + ".redis.host");
			int port = SystemConfigUtil.getIntProperty(jedisName + ".redis.port");
			jedisPool = newJeisPool(host, port);
			jedisPools.put(jedisName, jedisPool);
		}
		return jedisPool;
	}

	public static Jedis getJedisInstance(String jedisName) {
		JedisPool jedisPool = jedisPools.get(jedisName);
		if (jedisPool == null) {
			jedisPool = initJedisPool(jedisName);
		}

		Jedis jedis = null;
		for (int i = 0; i < 10; i++) {
			try {
				jedis = jedisPool.getResource();

				break;
			} catch (Exception e) {
				jedisPool.returnBrokenResource(jedis);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
				}
			}
		}
		return jedis;
	}

	public static boolean release(String poolName, Jedis jedis) {
		JedisPool jedisPool = jedisPools.get(poolName);
		if (jedisPool != null && jedis != null) {
			try {
				jedisPool.returnResource(jedis);
			} catch (Exception e) {
				jedisPool.returnBrokenResource(jedis);
			}
			return true;
		}
		return false;
	}

	public static void destroy() {
		for (Iterator<JedisPool> itors = jedisPools.values().iterator(); itors.hasNext();) {
			try {
				JedisPool jedisPool = itors.next();
				jedisPool.destroy();
			} finally {
			}
		}
	}

	private static JedisPool newJeisPool(String host, int port) {

		JedisPoolConfig jpCfg = new JedisPoolConfig();
		// jpCfg.setMaxIdle(50);
		// jpCfg.setMaxWaitMillis(5000);
		jpCfg.setTestOnBorrow(true);
		return new JedisPool(jpCfg, host, port, 30000);
	}

}
