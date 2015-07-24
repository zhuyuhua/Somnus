package com.somnus.leetcode.basic.design.decorator;

//具体装饰类 GPS功能扩展  --> ConcreteDecorator  
public class DecoratorGPS extends Decorator{  
  
    public DecoratorGPS(AbstractCellPhone mCellPhone) {  
        super(mCellPhone);  
    }  
  
    @Override  
    public String callNumber() {  
        return super.callNumber() +" GPS";  
    }  
  
    @Override  
    public String sendMessage() {  
        return super.sendMessage() +" GPS";  
    }  
}
