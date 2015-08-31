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

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONBoolean extends JSONSimple {

	public static final JSONBoolean TRUE = new JSONBoolean(true);
	public static final JSONBoolean FALSE = new JSONBoolean(false);

	private boolean value;

	public JSONBoolean(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "JSONBoolean(" + getLine() + ":" + getCol() + ")[" + this.value + "]";
	}

	@Override
	protected String render(boolean pretty, String indent) {
		if (pretty) {
			return indent + this.value;
		}
		return this.value + "";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		JSONBoolean that = (JSONBoolean) o;

		return this.value == that.value;
	}

	@Override
	public int hashCode() {
		return this.value ? 1 : 0;
	}

	@Override
	public Object strip() {
		return this.value ? Boolean.TRUE : Boolean.FALSE;
	}
}