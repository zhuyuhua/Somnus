package com.somnus.java.basic.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器
 * 
 * @author joe
 * 
 */
public class FileSystemClassLoader extends ClassLoader {

	private final String rootDir;

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException(name);
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		System.out.println("path:" + path);
		try {
			@SuppressWarnings("resource")
			InputStream in = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 1024*4;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = in.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 获取类的全路径
	private String classNameToPath(String className) {
		return rootDir + File.separatorChar
				+ className.replace('.', File.separatorChar) + ".class";
				
	}
}
