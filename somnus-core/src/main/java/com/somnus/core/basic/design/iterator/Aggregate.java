package com.somnus.core.basic.design.iterator;

public interface Aggregate {
	
	void add(Object object);
	void remove(Object object);
	Iterator iterator();

}
