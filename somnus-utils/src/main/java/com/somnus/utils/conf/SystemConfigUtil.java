package com.somnus.utils.conf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemConfigUtil {

	private static Properties configs;

	static {
		configs = new Properties();

		try {
			configs.load(SystemConfigUtil.class.getClassLoader()
					.getResourceAsStream("system-config.properties"));
		} catch (IOException e) {
			try {
				configs.load(new BufferedInputStream(new FileInputStream(System
						.getProperty("user.dir") + "/system-config.properties")));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private SystemConfigUtil() {
	}

	public static String getProperty(String key) {
		return configs.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {

		String value = getProperty(key);

		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir") + File.separatorChar
				+ "system-config.properties");

		System.out.println(configs.get("msg.cycle.push.redis.host"));

		System.out.println(300 / (0.41 * 12));
	}

	public static int getIntProperty(String key) {
		return getIntProperty(key, 0);
	}

	public static int getIntProperty(String key, int defaultValue) {
		String value = SystemConfigUtil.getProperty(key);

		if (value == null) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

}
