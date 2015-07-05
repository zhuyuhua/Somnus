package com.somnus.core.basic.design.visitor;

public class Visitor implements IVisitor {

	@Override
	public void visit(Element e1) {
		e1.doSomething();
	}

}
