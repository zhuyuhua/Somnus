package com.somnus.core.bigdata.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HBConfigUtil {

	public static Configuration getConfiguration() {
		Configuration config = HBaseConfiguration.create();
		try {
			Properties properties=new Properties();
			InputStream inputStream = HBConfigUtil.class.getResourceAsStream("/config/hbase.properties");
			properties.load(inputStream);
			//������е�key
			Set<Object> keySet = properties.keySet();
			Object[] keys = keySet.toArray();
			//������е�ֵ
			Collection<Object> valueCollec = properties.values();
			Object[] values = valueCollec.toArray();
			
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i].toString();
				String value = values[i].toString();
				
				if(checkArgument(key,value)){
					config.set(key, value);
				}
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return config;
	}
	
	
	private static boolean checkArgument(Object key,Object value){
		boolean validate=true;
		if(key.equals("")||value.equals("")){
			 validate=false;
		}
		
		return validate;
	}
	
	
	
}
