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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.bj58.sfft.json.jsontools.parser.impl.ParserUtil;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONObject extends JSONComplex {

	private HashMap<String, JSONValue> map = new LinkedHashMap();

	@Override
	public int size() {
		return this.map.size();
	}

	public HashMap<String, JSONValue> getValue() {
		return this.map;
	}

	@Override
	public String toString() {
		StringBuilder lBuf = new StringBuilder();
		lBuf.append("JSONObject(").append(getLine()).append(":").append(getCol()).append(")[");
		Iterator lIter = this.map.keySet().iterator();
		while (lIter.hasNext()) {
			String lKey = (String) lIter.next();
			lBuf.append(lKey).append(":").append(this.map.get(lKey).toString());
			if (lIter.hasNext()) {
				lBuf.append(", ");
			}
		}
		lBuf.append("]");
		return lBuf.toString();
	}

	@Override
	protected String render(boolean aPretty, String aIndent) {
		StringBuilder lBuf = new StringBuilder();
		Iterator lKeyIter = this.map.keySet().iterator();
		if (aPretty) {
			lBuf.append(aIndent).append("{\n");
			String lIndent = aIndent + "   ";
			String lIndent2 = aIndent + "      ";
			while (lKeyIter.hasNext()) {
				String lKey = (String) lKeyIter.next();
				JSONValue jsonValue = this.map.get(lKey);

				lBuf.append(lIndent).append(ParserUtil.render(lKey, false, ""));
				if (jsonValue.isSimple()) {
					lBuf.append(" : ").append(jsonValue.render(false, ""));
				} else {
					lBuf.append(" :\n").append(jsonValue.render(true, lIndent2));
				}
				if (lKeyIter.hasNext()) {
					lBuf.append(",\n");
				} else {
					lBuf.append("\n");
				}
			}
			lBuf.append(aIndent).append("}");
		} else {
			lBuf.append("{");
			while (lKeyIter.hasNext()) {
				String lKey = (String) lKeyIter.next();
				JSONValue jsonValue = this.map.get(lKey);

				lBuf.append(ParserUtil.render(lKey, false, "")).append(":").append(jsonValue.render(false));
				if (lKeyIter.hasNext()) {
					lBuf.append(",");
				}
			}
			lBuf.append(aIndent).append("}");
		}
		return lBuf.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		JSONObject that = (JSONObject) o;
		return this.map.equals(that.map);
	}

	@Override
	public int hashCode() {
		return this.map.hashCode();
	}

	public boolean containsKey(String aKey) {
		return this.map.containsKey(aKey);
	}

	public JSONValue get(String aKey) {
		return this.map.get(aKey);
	}

	@Override
	public Object strip() {
		HashMap lResult = new HashMap();
		for (String lKey : this.map.keySet()) {
			lResult.put(lKey, this.map.get(lKey).strip());
		}
		return lResult;
	}
}