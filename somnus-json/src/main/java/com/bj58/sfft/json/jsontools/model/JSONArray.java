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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONArray extends JSONComplex {
	private List<JSONValue> array = new ArrayList();

	@Override
	public int size() {
		return this.array.size();
	}

	public List<JSONValue> getValue() {
		return this.array;
	}

	@Override
	public String toString() {
		StringBuilder lBuf = new StringBuilder();
		lBuf.append("JSONArray(").append(getLine()).append(":").append(getCol()).append(")[");
		for (int i = 0; i < this.array.size(); i++) {
			JSONValue jsonValue = this.array.get(i);
			lBuf.append(jsonValue.toString());
			if (i < this.array.size() - 1) {
				lBuf.append(", ");
			}
		}
		lBuf.append("]");
		return lBuf.toString();
	}

	public JSONValue get(int i) {
		return this.array.get(i);
	}

	@Override
	protected String render(boolean aPretty, String aIndent) {
		StringBuilder lBuf = new StringBuilder();
		if (aPretty) {
			lBuf.append(aIndent).append("[\n");
			String lIndent = aIndent + "   ";
			for (int i = 0; i < this.array.size(); i++) {
				JSONValue jsonValue = this.array.get(i);
				lBuf.append(jsonValue.render(true, lIndent));
				if (i < this.array.size() - 1) {
					lBuf.append(",\n");
				} else {
					lBuf.append("\n");
				}
			}
			lBuf.append(aIndent).append("]");
		} else {
			lBuf.append("[");
			for (int i = 0; i < this.array.size(); i++) {
				JSONValue jsonValue = this.array.get(i);
				lBuf.append(jsonValue.render(false, ""));
				if (i < this.array.size() - 1) {
					lBuf.append(",");
				}
			}
			lBuf.append("]");
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

		JSONArray jsonArray = (JSONArray) o;

		return this.array.equals(jsonArray.array);
	}

	@Override
	public int hashCode() {
		return this.array.hashCode();
	}

	@Override
	public Object strip() {
		List lResult = new LinkedList();
		for (JSONValue lVal : this.array) {
			lResult.add(lVal.strip());
		}
		return lResult;
	}
}
