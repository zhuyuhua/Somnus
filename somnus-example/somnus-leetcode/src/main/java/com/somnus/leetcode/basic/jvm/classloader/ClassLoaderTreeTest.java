package com.somnus.leetcode.basic.jvm.classloader;

/**
 * 查看ClassLoader树
 * 
 * @author joe
 *
 */
public class ClassLoaderTreeTest {

	public static void main(String[] args) {
		ClassLoader classLoader = new ClassLoaderTreeTest().getClass()
				.getClassLoader();
		while (classLoader != null) {
			System.out.println(classLoader.toString());
			classLoader = classLoader.getParent();
		}

	}
}
