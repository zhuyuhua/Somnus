package com.somnus.protocol.proxy.bean;


/**
 * TODO
 * 
 * @author ZHUYUHUA129
 * @version 0.0.1
 * @since 2015年11月19日 下午3:12:55
 */
public class Out<T> {

	private T outPara;

	public void setOutPara(T t) {
		this.outPara = t;
	}

	public T getOutPara() {
		return outPara;
	}
}
