package com.somnus.core.basic.design.mediator;

public class MediatorTest {
	
	public static void main(String[] args) {
		
		ColleagueA collA = new ColleagueA();
		ColleagueB collB = new ColleagueB();
		
		Mediator mediator = new Mediator(collA, collB);
		
		System.out.println("==========通过设置A影响B==========");  
        collA.changeNumber(1000, mediator);  
        System.out.println("collA的number值为："+collA.getNumber());  
        System.out.println("collB的number值为A的100倍："+collB.getNumber());  
  
        System.out.println("==========通过设置B影响A==========");
        collB.changeNumber(1000, mediator);
        System.out.println("collB的number值为："+collB.getNumber());
        System.out.println("collA的number值为B的10倍："+collA.getNumber());
        
	}

}
