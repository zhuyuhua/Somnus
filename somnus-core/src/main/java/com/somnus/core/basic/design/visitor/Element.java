package com.somnus.core.basic.design.visitor;

public interface Element {

	public abstract void accept(IVisitor visitor);

	public abstract void doSomething();
}
