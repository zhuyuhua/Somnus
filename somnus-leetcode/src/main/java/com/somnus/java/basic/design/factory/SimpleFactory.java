package com.somnus.java.basic.design.factory;

/**
 * 简单工厂模式
 * 
 * @author Administrator
 * 
 */
public class SimpleFactory {

	public static void main(String[] args) {
		ProductFactory factory = new SimpleFactory().new ProductFactory();
		IProduct product = factory.getProduct(1);
		product.execute();

		product = factory.getProduct(2);
		product.execute();

	}

	interface IProduct {
		void execute();
	}

	class Product1 implements IProduct {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class Product2 implements IProduct {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class ProductFactory {

		public IProduct getProduct(int type) {
			if (type == 1) {
				return new Product1();
			} else if (type == 2) {
				return new Product2();
			} else {
				return null;
			}
		}

	}
}
