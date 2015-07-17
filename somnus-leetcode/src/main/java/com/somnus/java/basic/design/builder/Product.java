package com.somnus.java.basic.design.builder;

public class Product {

	private int productId;
	private String productName;

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + "]";
	}
}
