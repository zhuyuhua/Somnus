package com.somnus.core.basic.design.factory;

/**
 * 抽象工厂模式
 * 
 * @author Administrator
 * 
 */
public class AbstractFactory {

	public static void main(String[] args) {
		AbstractFactory abstractFactory = new AbstractFactory();

		IFactory a1b1 = abstractFactory.new ConcreateA1B1Factory();
		a1b1.createProductA().execute();
		a1b1.createProductB().execute();
		System.out.println("******************");

		IFactory a1b2 = abstractFactory.new ConcreateA1B2Factory();
		a1b2.createProductA().execute();
		a1b2.createProductB().execute();
		System.out.println("******************");

		IFactory a2b1 = abstractFactory.new ConcreateA2B1Factory();
		a2b1.createProductA().execute();
		a2b1.createProductB().execute();
		System.out.println("******************");

		IFactory a2b2 = abstractFactory.new ConcreateA2B2Factory();
		a2b2.createProductA().execute();
		a2b2.createProductB().execute();

	}

	interface IProductA {
		void execute();
	}

	interface IProductB {
		void execute();
	}

	class ProductA1 implements IProductA {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class ProductA2 implements IProductA {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class ProductB1 implements IProductB {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	class ProductB2 implements IProductB {
		@Override
		public void execute() {
			System.out.println(this.getClass().getName());
		}
	}

	interface IFactory {
		IProductA createProductA();

		IProductB createProductB();
	}

	class ConcreateA1B1Factory implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductA1();
		}

		@Override
		public IProductB createProductB() {
			return new ProductB1();
		}
	}

	class ConcreateA1B2Factory implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductA1();
		}

		@Override
		public IProductB createProductB() {
			return new ProductB2();
		}
	}

	class ConcreateA2B1Factory implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductA2();
		}

		@Override
		public IProductB createProductB() {
			return new ProductB1();
		}
	}

	class ConcreateA2B2Factory implements IFactory {

		@Override
		public IProductA createProductA() {
			return new ProductA2();
		}

		@Override
		public IProductB createProductB() {
			return new ProductB2();
		}
	}

}
