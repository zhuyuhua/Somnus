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
package com.somnus.rpc.client.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调用返回结果
 * @author zhuyuhua
 * @version 0.0.1
 * @since 2015年9月30日
 */
public class InvokeResult<T> {

	private static Logger logger = LoggerFactory.getLogger(InvokeResult.class);

	private T Result;
    private Object[] OutPara;
    
	public InvokeResult(Object result, Object[] outPara) {
        Result = (T) result;
        OutPara = outPara;
    }
    

    public Object[] getOutPara() {
        return OutPara;
    }

    public void setOutPara(Object[] OutPara) {
        this.OutPara = OutPara;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T Result) {
        this.Result = Result;
    }
}
