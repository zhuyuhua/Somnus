/*
 * Copyright (c) 2010-2015. Somnus Framework
 * The Somnus Framework licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.somnus.example.spring.aop.business.annotation;

import org.springframework.stereotype.Component;

@Component(value = "annotationBusiness")
public class AnnotationBusiness {

	/**
	 * 切入点
	 * 
	 * @throws Exception
	 */
	public String delete(String obj) {
		System.out.println("======调用delete切入点：" + obj + "说：你敢删除我！===========");
		return obj + "：瞄～";
	}

	public String add(String obj) {
		// System.out.println("=========这个方法不能被切。。。========");
		System.out.println("======调用add切入点：" + obj + ",返回值=" + obj + "：瞄～ 嘿嘿！");
		return obj + "：瞄～ 嘿嘿！";
	}

	public String modify(String obj) {
		// System.out.println("=========这个也设置加入切吧============");
		System.out.println("======调用modify切入点：" + obj);
		return obj + "：瞄改瞄啊！";
	}
}
