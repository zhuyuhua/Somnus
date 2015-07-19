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
package com.somnus.utils.velocity;

import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取Velocity对象
 * 
 * @author zhuyuhua
 * @since 2015年7月19日
 */
public class VelocityHelper {

	private static Logger logger = LoggerFactory.getLogger(VelocityHelper.class);

	private static VelocityEngine engine;

	public static VelocityEngine getVelocityEngine() {
		if (engine == null) {
			Properties p = new Properties();
			p.setProperty("file.resource.loader.class",ClasspathResourceLoader.class.getName());
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
			p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
			p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			engine = new VelocityEngine();
			engine.init(p);
		}
		return engine;
	}
	
	public static void main(String[] args) {
		System.out.println(ClasspathResourceLoader.class.getName());
	}
}
