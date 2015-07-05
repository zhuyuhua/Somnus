package com.somnus.core.basic.design.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VisitorTest {

	public static void main(String[] args) {
		List<Element> list = ObjectStruture.getList();
		for (Element e : list) {
			e.accept(new Visitor());
		}
	}
}

class ObjectStruture {
	public static List<Element> getList() {
		List<Element> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int a = random.nextInt(100);
			if (a > 50) {
				list.add(new ConcreteElement1());
			} else {
				list.add(new ConcreteElement2());
			}
		}
		return list;
	}
}
