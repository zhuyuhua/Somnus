package com.somnus.leetcode.basic.jvm.classloader;

public class Sample {

	private Sample instance;
	
	public void setSample(Object instance){
		System.out.println("in setSample func");
		this.setInstance((Sample) instance);
	}

	public Sample getInstance() {
		return instance;
	}

	public void setInstance(Sample instance) {
		this.instance = instance;
	}
}