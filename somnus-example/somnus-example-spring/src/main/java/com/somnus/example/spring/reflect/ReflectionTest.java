/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.reflect 
 * @FileName:ReflectionTest.java 
 * @Date:2014-2-12 下午2:50:38 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.somnus.example.spring.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		args = new String[] { "com.zhuyuhua.myspring.reflect.TestArguments",
				"2" };

		String str = "abc";
		Class c1 = str.getClass();
		Class c2 = String.class;
		Class c3 = Class.forName("java.lang.String");
		System.out.println(c1 == c2);// true
		System.out.println(c1 == c3);// true

		System.out.println(c1.isPrimitive());// false: String不是基本数据类型
		System.out.println(int.class.isPrimitive());// true: int是基本数据类型
		System.out.println(int.class == Integer.class);// false: 基本类与包装类不是一种类型
		// true: 常量TYPE代表包装类中基本类型的字节码
		System.out.println(int.class == Integer.TYPE);
		System.out.println(int[].class.isPrimitive());// false: 数组不是基本类型
		System.out.println(int[].class.isArray());// true 判断数组类型
		System.out.println("------------------");

		// -------------- 构造方法的反射
		System.out.println("==============Constructor==============");
		// 1，获取构造器
		Constructor<String> constructor1 = String.class
				.getConstructor(StringBuffer.class);
		// 2，使用Constructor的有参newInstance(obj) 方法创建对象,该参数是一个对象
		String str2 = constructor1.newInstance(new StringBuffer("abc"));
		System.out.println("str2=" + str2);
		System.out.println(str2.charAt(1));


		// -------------- Field 类的反射
		System.out.println("==============Field==============");
		// 1,获取public变量
		ReflectPoint rp1 = new ReflectPoint(6, 9);
		Field fy = rp1.getClass().getField("y");// getField获取指定public 变量
		// fy 不是对象上的的变量9，而是表示类上的变量“y”（对象），
		System.out.println(fy.get(rp1));// 用来获取该类对象上对应的值
		// 2, 获取私有变量 <-- 暴力反射 -->
		Field fx = rp1.getClass().getDeclaredField("x");// (1)获取私有属性，该方法返回指定已声明字段
		fx.setAccessible(true); // (2)将该域设置为可以访问，是继承父类 AccessibleObject的方法
		System.out.println(fx.get(rp1));

		changeStringValue(rp1); // 修改操作
		System.out.println(rp1);

		// Method 类的反射
		System.out.println("===============Method==============");
		// str.charAt(1); //普通方法
		Method methodCharAt = String.class.getMethod("charAt", int.class);
		System.out.println(methodCharAt.invoke(str, 1));// 反射方法
		// 静态方法不需要对象，如果不是str而是null,说明该方法是静态方法
		System.out.println(methodCharAt.invoke(str, new Object[] { 2 }));// jdk1.4的写法,一个元素代表一个参数

		// 用反射方式执行某个类中的main方法
		System.out.println("===============Main==============");
		// (1)普通方式直接调用静态方法
		TestArguments.main(new String[] { "aaa", "bbb", "ccc" });
		// (2)反射方式
		String startingClassName = "com.zhuyuhua.myspring.reflect.TestArguments";
		Method mainMethod = Class.forName(startingClassName).getMethod("main",
				String[].class);

		// jdk1.4遗留的兼容性问题：数组中每个元素分别对应被调用方法中的一个参数,所以会抛出参数个数异常
		// 解决方法：将该数组封装进一个Object对象
		mainMethod.invoke(null, new Object[] { new String[] { "aaa", "bbb",
				"ccc" } });// 相当于包一层皮
		mainMethod.invoke(null, (Object) new String[] { "xxx", "ooo", "yyy" });// 相当于声明不让编译器拆包

		// 数组的反射
		System.out.println("===============数组反射==============");
		int[] a1 = new int[] { 1, 2, 3 };
		int[] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String[] a4 = new String[] { "a", "b", "c" };
		System.out.println(a1.getClass() == a2.getClass()); // true
		System.out.println(a1.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());// 父类为Object

		Object obj1 = a1;
		// Object[] obj2 = a1; //基本数据类型的一维数组不能作为Object[]使用
		Object[] obj3 = a3; // 基本数据类型的多维数组可以作为Object或Object[]使用
		Object obj4 = a4;
		Object[] obj5 = a4; // 非基本元素类型一维数组可以作为Object或Object[]使用

		System.out.println("a1=" + a1);
		System.out.println("a4=" + a4);
		System.out.println("a1=" + Arrays.asList(a1)); // a1是int基本类型，不能作为Object[]使用，只能作为一个Object
		System.out.println("a4=" + Arrays.asList(a4)); // a4可以作为Object[]使用，符合jdk1.4的语法，自动拆分打印
		// Arrays工具类的静态方法：1.4版本: asList(Object[] a);将数组转成List集合，List集合可以直接打印
		// 1.5版本： asList(T...a) ; 向下兼容1.4 ,新增功能可以将多个参数转成List集合
		System.out.println(Arrays.asList(1, 2, 3, 4, 5));// asList方法1.5特性，可变参数

		printObject(a4);
		printObject("abc");

	}

	// 细节：Array类与Arrays类
	// java.util包中 Arrays工具类对数组元素进行操作，接收参数为（Array[] array），无法处理数组的Object形态
	// java.lang.reflect包中的Array工具类对数组对象进行操作，接收参数为（Object array）
	private static void printObject(Object obj) {
		Class clazz = obj.getClass();
		if (clazz.isArray()) {
			for (int x = 0; x < Array.getLength(obj); x++) {
				System.out.println(Array.get(obj, x));
			}
		}
		else { // 不是数组直接打印
			System.out.println(obj);
		}

	}

	// 反射的应用：修改操作
	private static void changeStringValue(Object obj) throws Exception {
		Field[] fields = obj.getClass().getFields(); // 获取该对象所属class所有public变量
		// 循环遍历，逐一操作
		for (Field field : fields) {
			if (field.getType() == String.class) { // 判断String字段
				String oldValue = (String) field.get(obj); // 获取对应的值
				String newValue = oldValue.replace('6', 'd');// String类操作
				System.out.println("oldValue=" + oldValue + ",newValue="
						+ newValue);
				field.set(obj, newValue); // 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
			}
		}
	}
}

// 打印参数
class TestArguments
{
	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
	}
}

class ReflectPoint
{
	public String x;
	public String y;

	public ReflectPoint(int i, int j) {
		this.x = i + "";
		this.y = j + "";
	}

	@Override
	public String toString() {
		return "ReflectPoint [x=" + x + ", y=" + y + "]";
	}

}
