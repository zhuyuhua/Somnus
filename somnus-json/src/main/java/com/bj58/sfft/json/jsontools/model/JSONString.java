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

import com.bj58.sfft.json.jsontools.parser.impl.ParserUtil;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONString extends JSONSimple {
	private String value;

	public JSONString(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "JSONString(" + getLine() + ":" + getCol() + ")[" + ParserUtil.render(this.value, false, "") + "]";
	}

	@Override
	protected String render(boolean pretty, String indent) {
		return ParserUtil.render(this.value, pretty, indent);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		JSONString that = (JSONString) o;
		return this.value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public Object strip() {
		return this.value;
	}
}
