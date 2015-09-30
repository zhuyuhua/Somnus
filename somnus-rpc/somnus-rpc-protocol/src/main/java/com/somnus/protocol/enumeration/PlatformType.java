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
package com.somnus.protocol.enumeration;

/**
 * 平台类型
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public enum PlatformType {
	Dotnet(0),
    Java(1),
    C(2);
	
	private final int num;

    public int getNum() {
        return num;
    }

    private PlatformType(int num) {
        this.num = num;
    }

    public static PlatformType getPlatformType(int num) {
        for (PlatformType type : PlatformType.values()) {
            if (type.getNum() == num) {
                return type;
            }
        }
        return null;
    }
}
