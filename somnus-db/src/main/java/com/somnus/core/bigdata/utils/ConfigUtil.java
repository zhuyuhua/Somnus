package com.somnus.core.bigdata.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class ConfigUtil {

	private final static String HBASE_CONFIG_PATH="/config/hbase.properties";
	private final static String HADOOP_CONFIG_PATH="/config/hadoop.properties";
	private  final static String HADOOP_HOME="hadoop.home.dir";
	public static Configuration getHBConfig() throws IOException {
		Configuration config = HBaseConfiguration.create();
		InputStream inputStream=null;
		try {
			Properties properties=new Properties();
			 inputStream = ConfigUtil.class.getResourceAsStream(HBASE_CONFIG_PATH);
			properties.load(inputStream);

			Set<Object> keySet = properties.keySet();
			Object[] keys = keySet.toArray();
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
		}finally{
			if(inputStream!=null){
				inputStream.close();
			}
		}
		
		
		return config;
	}
	
	
	
	public static void setHadoopConfig() throws IOException{
		Configuration config = HBaseConfiguration.create();
		InputStream inputStream=null;
		try {
			Properties properties=new Properties();
			 inputStream = ConfigUtil.class.getResourceAsStream(HADOOP_CONFIG_PATH);
			properties.load(inputStream);
			String path = properties.get(HADOOP_HOME).toString();
			System.setProperty(HADOOP_HOME, path);
		
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				inputStream.close();
			}
		}
	}
	
	
	private static boolean checkArgument(Object key,Object value){
		boolean validate=true;
		if(key.equals("")||value.equals("")){
			 validate=false;
		}
		
		return validate;
	}
	
	
	
}
