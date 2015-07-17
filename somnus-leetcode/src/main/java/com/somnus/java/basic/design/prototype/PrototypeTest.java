package com.somnus.java.basic.design.prototype;

/**
 * Prototype：声明一个克隆自身的接口，用来约束想要克隆自己的类，要求它们都要实现这里定义的克隆方法。
 * ConcretePrototype：实现Prototype接口的类，这些类真正实现了克隆自身的功能。
 * Client：使用原型的客户端，首先要获取到原型实例对象，然后通过原型实例克隆自身来创建新的对象实例。
 * @author lenovo
 *
 */
public class PrototypeTest {
	public static void main(String[] args) {
		Prototype prototype = new ConcretePrototype1();
		
		Prototype p2 = prototype.clone();
	
		System.out.println(p2);
	}

}

interface Prototype{
	Prototype clone();
}

class ConcretePrototype1 implements Prototype {  
	  
    @Override
	public Prototype clone(){  
        //最简单的克隆  
        Prototype prototype = new ConcretePrototype1();  
        return prototype;  
    }
}
