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
package com.bj58.sfft.json.jsontools.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public abstract class JSONValue {

	private String streamName;
	private int line = 0;
	private int col = 0;
	private Object data = null;

	public int getLine() {
		return this.line;
	}

	public void setLineCol(int line, int col) {
		this.line = line;
		this.col = col;
	}

	public String getStreamName() {
		return this.streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public int getCol() {
		return this.col;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSimple() {
		return this instanceof JSONSimple;
	}

	public boolean isComplex() {
		return this instanceof JSONComplex;
	}

	public boolean isArray() {
		return this instanceof JSONArray;
	}

	public boolean isObject() {
		return this instanceof JSONObject;
	}

	public boolean isNumber() {
		return this instanceof JSONNumber;
	}

	public boolean isDecimal() {
		return this instanceof JSONDecimal;
	}

	public boolean isInteger() {
		return this instanceof JSONInteger;
	}

	public boolean isNull() {
		return this instanceof JSONNull;
	}

	public boolean isBoolean() {
		return this instanceof JSONBoolean;
	}

	public boolean isString() {
		return this instanceof JSONString;
	}

	public String render(boolean pretty) {
		return render(pretty, "");
	}

	protected abstract String render(boolean paramBoolean, String paramString);

	public abstract Object strip();

	public static JSONValue decorate(Object anObject) {
		if (anObject == null) {
			return new JSONNull();
		}
		if ((anObject instanceof Boolean)) {
			return anObject.equals(Boolean.TRUE) ? JSONBoolean.TRUE : JSONBoolean.FALSE;
		}
		if ((anObject instanceof BigDecimal)) {
			return new JSONDecimal((BigDecimal) anObject);
		}
		if ((anObject instanceof BigInteger)) {
			return new JSONInteger((BigInteger) anObject);
		}
		if ((anObject instanceof String)) {
			return new JSONString((String) anObject);
		}
		Iterator localIterator;
		if ((anObject instanceof List)) {
			JSONArray lArray = new JSONArray();
			for (localIterator = ((List) anObject).iterator(); localIterator.hasNext();) {
				Object lElement = localIterator.next();

				lArray.getValue().add(decorate(lElement));
			}
			return lArray;
		}
		if ((anObject instanceof Map)) {
			JSONObject lObj = new JSONObject();
			for (localIterator = ((Map) anObject).keySet().iterator(); localIterator.hasNext();) {
				Object lKey = localIterator.next();

				if ((lKey instanceof String)) {
					lObj.getValue().put((String) lKey, decorate(((Map) anObject).get(lKey)));
				} else {
					throw new IllegalArgumentException("HashMap contains a key that is not a String: " + lKey);
				}
			}
			return lObj;
		}

		throw new IllegalArgumentException("Cannot convert this object to a JSONValue: " + anObject);
	}
}
