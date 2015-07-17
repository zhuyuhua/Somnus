package com.somnus.java.basic.design.builder;

import java.util.Random;

public class ConcreteBuilder implements Builder {

	private Product resultProduct;

	public Product getResultProduct() {
		return resultProduct;
	}

	public ConcreteBuilder() {
		this.resultProduct = new Product();
	}

	@Override
	public void buildPart() {
		resultProduct.setProductName("productName");

	}

	@Override
	public void buildMain() {
		resultProduct.setProductId(new Random().nextInt(1000));
	}

}
