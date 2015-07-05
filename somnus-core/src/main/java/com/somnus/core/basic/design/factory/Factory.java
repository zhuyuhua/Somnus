package com.somnus.core.basic.design.factory;

/**
 * 工厂模式
 * 
 * @author Administrator
 * 
 */
public class Factory {

	public static void main(String[] args) {

		Factory f = new Factory();

		IFactory factory = f.new ConcreateFactoryA();
		factory.getProduct().execute();

		factory = f.new ConcreateFactoryB();
		factory.getProduct().execute();

	}

	interface IProduct {
		void execute();
	}

	interface IFactory {
		IProduct getProduct();
	}

	class ConcreateFactoryA implements IFactory {
		@Override
		public IProduct getProduct() {
			return new ProductA();
		}
	}

	class ConcreateFactoryB implements IFactory {
		@Override
		public IProduct getProduct() {
			return new ProductB();
		}
	}

	class ProductA implements IProduct {

		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class ProductB implements IProduct {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

}
