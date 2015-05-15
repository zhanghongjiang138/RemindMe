package com.sendtext.test;

public class MainTest {
	public  boolean isEqual(Object o1,Object o2){
		return o1==o2;
	}
	
	public static void main(String[] args){
		MainTest mt=new MainTest();
		System.out.println(mt.isEqual(null,null)); //true
		
	}

}
