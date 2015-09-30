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
package com.somnus.rpc.client.proxy.bean;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数
 * 
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public class Parameter {

	private static Logger logger = LoggerFactory.getLogger(Parameter.class);

	private Class<?> clazz;
	private Type type;
	private Object value;
	private ParaType paraType;
	private boolean isGeneric;
	private String simpleName;
	private Class<?> containerClass;
	private Class<?> itemClass;
	private Class<?>[] itemClass_;

	/**
	 * Creates a new instance of Parameter.
	 *
	 * @param value
	 * @param clazz
	 * @param type
	 * @throws ClassNotFoundException
	 */
	public Parameter(Object value, Class<?> clazz, Type type) throws ClassNotFoundException {
		this.setValue(value);
		this.setClazz(clazz);
		this.setType(type);
		this.setParaType(ParaType.In);
		init(value, clazz, type);
	}

	/**
	 * 
	 * Creates a new instance of Parameter.
	 *
	 * @param value
	 * @param clazz
	 * @param type
	 * @param ptype
	 * @throws ClassNotFoundException
	 */
	public Parameter(Object value, Class<?> clazz, Type type, ParaType ptype) throws ClassNotFoundException {
		this.setClazz(clazz);
		this.setType(type);
		this.setValue(value);
		this.setParaType(ptype);
		init(value, clazz, type);
	}

	/**
	 * @param value
	 *            null
	 * @param clazz
	 *            方法返回类型(ex:interface java.util.Map)
	 * @param type
	 *            方法 底层方法的正式返回类型的 Type对象 (ex:java.util.Map<java.lang.String,
	 *            java.lang.String>)
	 * @throws ClassNotFoundException
	 */
	private void init(Object value, Class<?> clazz, Type type) throws ClassNotFoundException {
		Class<?>[] itemClassO_ = new Class<?>[2];
		if (!clazz.equals(type) && !clazz.getCanonicalName().equalsIgnoreCase(type.toString())) {
			String itemName = type.toString().replaceAll(clazz.getCanonicalName(), "").replaceAll("\\<", "")
					.replaceAll("\\>", "");

			if (itemName.indexOf(",") == -1) {
				Class<?> itemCls = Class.forName(itemName);
				itemClassO_[0] = itemCls;
			} else {
				String[] itemArray = itemName.split(",");
				if (itemArray != null && itemArray.length == 2) {
					itemClassO_[0] = Class.forName(itemArray[0].replaceFirst(" ", ""));
					itemClassO_[1] = Class.forName(itemArray[1].replaceFirst(" ", ""));
				}
			}

			this.setItemClass_(itemClassO_);
			this.setContainerClass(clazz);
			this.setIsGeneric(true);

			String sn = "";
			// if (value instanceof Out) {
			// sn = itemName.substring(itemName.lastIndexOf(".") + 1);
			// } else {
				sn = clazz.getCanonicalName();
				sn = sn.substring(sn.lastIndexOf(".") + 1);

				if (itemName.indexOf(",") == -1) {
					itemName = itemName.substring(itemName.lastIndexOf(".") + 1);
					sn = sn + "<" + itemName + ">";
				} else {
					String[] genericItem = type.toString().replaceAll(clazz.getCanonicalName(), "")
							.replaceAll("\\<", "").replaceAll("\\>", "").split(",");
					sn = sn + "<" + genericItem[0].substring(genericItem[0].lastIndexOf(".") + 1) + ","
							+ genericItem[1].substring(genericItem[1].lastIndexOf(".") + 1) + ">";
				}
			// }
			this.setSimpleName(sn);
		} else {
			this.setIsGeneric(false);
			itemClassO_[0] = clazz;
			this.setItemClass_(itemClassO_);
			this.setItemClass(clazz);
			this.setSimpleName(clazz.getSimpleName());
		}
	}

	/**
	 * clazz
	 *
	 * @return the clazz
	 * @since JDK 1.6
	 */

	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz
	 *            the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * type
	 *
	 * @return the type
	 * @since JDK 1.6
	 */

	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * value
	 *
	 * @return the value
	 * @since JDK 1.6
	 */

	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * paraType
	 *
	 * @return the paraType
	 * @since JDK 1.6
	 */

	public ParaType getParaType() {
		return paraType;
	}

	/**
	 * @param paraType
	 *            the paraType to set
	 */
	public void setParaType(ParaType paraType) {
		this.paraType = paraType;
	}

	/**
	 * isGeneric
	 *
	 * @return the isGeneric
	 * @since JDK 1.6
	 */

	public boolean isGeneric() {
		return isGeneric;
	}

	/**
	 * @param isGeneric
	 *            the isGeneric to set
	 */
	public void setIsGeneric(boolean isGeneric) {
		this.isGeneric = isGeneric;
	}

	/**
	 * simpleName
	 *
	 * @return the simpleName
	 * @since JDK 1.6
	 */

	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * @param simpleName
	 *            the simpleName to set
	 */
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	/**
	 * containerClass
	 *
	 * @return the containerClass
	 * @since JDK 1.6
	 */

	public Class<?> getContainerClass() {
		return containerClass;
	}

	/**
	 * @param containerClass
	 *            the containerClass to set
	 */
	public void setContainerClass(Class<?> containerClass) {
		this.containerClass = containerClass;
	}

	/**
	 * itemClass
	 *
	 * @return the itemClass
	 * @since JDK 1.6
	 */

	public Class<?> getItemClass() {
		return itemClass;
	}

	/**
	 * @param itemClass
	 *            the itemClass to set
	 */
	public void setItemClass(Class<?> itemClass) {
		this.itemClass = itemClass;
	}

	/**
	 * itemClass_
	 *
	 * @return the itemClass_
	 * @since JDK 1.6
	 */

	public Class<?>[] getItemClass_() {
		return itemClass_;
	}

	/**
	 * @param itemClass_
	 *            the itemClass_ to set
	 */
	public void setItemClass_(Class<?>[] itemClass_) {
		this.itemClass_ = itemClass_;
	}

}
