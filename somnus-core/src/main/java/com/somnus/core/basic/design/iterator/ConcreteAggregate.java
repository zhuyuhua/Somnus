package com.somnus.core.basic.design.iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteAggregate implements Aggregate {

	  private final List list = new ArrayList();  
	  
	    @Override
		public void add(Object obj) {  
	        list.add(obj);  
	    }  
	  
	    @Override
		public Iterator iterator() {  
	        return new ConcreteIterator(list);  
	    }  
	  
	    @Override
		public void remove(Object obj) {  
	        list.remove(obj);  
	    }  
	
}
