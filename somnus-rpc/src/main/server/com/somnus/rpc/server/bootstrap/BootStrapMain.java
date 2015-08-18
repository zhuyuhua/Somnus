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
package com.somnus.rpc.server.bootstrap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.somnus.rpc.server.context.ServiceConfig;
import com.somnus.utils.system.SystemUtil;

/**
 *
 * Somnus RPC框架服务端启动类
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class BootStrapMain {

	private static Logger logger = LoggerFactory.getLogger(BootStrapMain.class);
	
	private static String CLASS_PATH = BootStrapMain.class.getResource("/").getPath();
	
	public static void main(String[] args) throws Exception {
		
		if (args.length<1) {
			args = new String[]{"-Dsomnus.service.name=somnus-rpc-demo"};
		}
		
		String userDir  = SystemUtil.getUserDir();
		String rootPath = userDir+File.separator+".."+File.separator;
		logger.debug("rootPath:"+rootPath+",userDir:"+userDir);
		String serviceName = null;//服务名称
		Map<String, String>argsMap = new HashMap<String,String>();
		
		//解析传入参数
		for(int i=0; i<args.length; i++) {
			if(args[i].startsWith("-D")) {
				String[] aryArg = args[i].split("=");
				if(aryArg.length == 2) {
					if(aryArg[0].equalsIgnoreCase("-Dsomnus.service.name")) {
						serviceName = aryArg[1];
					}
					argsMap.put(aryArg[0].replaceFirst("-D", ""), aryArg[1]);
				}
			}
		}
		
		if(serviceName == null){
			throw new Exception("no service name, please set it like -Dsomnus.service.name=serviceName");
		}
		
		//配置文件
		String deployFolderPath = userDir+"/deploy";
		String confFolderPath = userDir+"/conf";
		
		//默认配置
		String defaultRpcConfig = confFolderPath+"/default_rpc_config.xml";
		//用户自定义配置文件，
		String constomerRpcConfig = CLASS_PATH+"config/somnus_rpc_config.xml";
		
		logger.info("+++++++++++++++++++++ staring +++++++++++++++++++++\n");
		
		logger.info("user.dir: " + userDir);
		logger.info("rootPath: " + rootPath);
		logger.info("default default_rpc_config.xml: " + defaultRpcConfig);
		logger.info("service default_rpc_config.xml: " + constomerRpcConfig);
		
		//加载service配置
		logger.info("load service config...");
		ServiceConfig sc = ServiceConfig.getServiceConfig(defaultRpcConfig, constomerRpcConfig);
	}
}
