package com.somnus.leetcode.basic.jvm.classloader;

import java.lang.reflect.Method;


/**
 * 测试类 FileSystemClassLoader的两个不同实例来分别加载类
 * com.zhuyuhua.javautil.jvm.classloader.Sample
 * 
 * @author joe
 * 
 */
public class FileSystemClassLoaderTest {

	public static void main(String[] args) {
//		String classDataRootPath = "E:\\eclipse\\demo_workspace\\javautil\\src";
		String classDataRootPath = "C:\\Users\\Administrator\\git\\javautil\\bin";

		 FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
		 FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
		 
		 String className = "com.zhuyuhua.javautil.jvm.classloader.Sample";
		 
		 try {
			Class<?> classz1 = fscl1.findClass(className);
			Class<?> classz2 = fscl2.findClass(className);
			System.out.println(classz1.getClassLoader());
			System.out.println(classz2.getClassLoader());

			Object obj1 = classz1.newInstance();
			Object obj2 = classz2.newInstance();
			
			System.out.println(obj1);
			System.out.println(obj2);

			Method setSampleMethod = classz1.getMethod("setSample",
					Object.class);

			setSampleMethod.invoke(obj1, obj2);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	}
}
