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

import java.math.BigInteger;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class JSONInteger extends JSONNumber {

	private BigInteger value;

	public JSONInteger(BigInteger value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public BigInteger getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "JSONInteger(" + getLine() + ":" + getCol() + ")[" + this.value.toString() + "]";
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

		JSONInteger that = (JSONInteger) o;
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