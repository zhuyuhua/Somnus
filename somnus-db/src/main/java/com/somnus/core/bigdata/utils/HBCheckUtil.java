package com.somnus.core.bigdata.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBCheckUtil {
	
	private static Logger logger= LoggerFactory.getLogger(HBCheckUtil.class);

	public static void checkArgument(Object argument){
		if(isNotBlank(argument)){
			logger.error(argument+ " is null error Argument");
		}
	}
	
	public static void checkArgument(boolean exit,String msg, String tableName) throws IOException{
		if(!exit){
			msg=String.format(msg, tableName);
			IllegalStateException exception=new IllegalStateException(msg);
			logger.error(msg, exception);
			throw exception;
		}
	}
	
	
	/**
	 * �Ƿ�Ϊ��ֵ,����fasle�����ǿ�ֵ
	 * ����true�����ǿ�ֵ
	 * @param argument
	 * @return
	 */
	public static boolean isNotBlank(Object argument){
		boolean validate=true;
		if(argument==null){
			validate=false;
		}
		return validate;
	}
	
	public static Object checkNotNull(Object object){
		if(object==null){
			logger.error("object is null");;
		}
		return object;
	}
	
}
