package com.somnus.utils.id;

import java.util.UUID;

public class DefaultIdentifierFactory {

	public static String uuidAll() {
		return UUID.randomUUID().toString();
	}

	public static String uuidPrefix() {

		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8);
	}

	public static String uuidSuffix() {
		String uuid = UUID.randomUUID().toString();

		int index = uuid.lastIndexOf('-');

		return uuid.substring(index + 1);
	}

	public static void main(String[] args) {
		System.out.println(uuidPrefix());
		System.out.println(uuidSuffix());
	}

	public static String uuidOfTransaction() {
		return uuidSuffix();
	}

}
