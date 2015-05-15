package com.sendtext.test;

public class ConstructerTest{  
	
    String name;
    int age;
    public ConstructerTest (){ 
        this.age=21;
   }     
    public ConstructerTest(String name,int age){
        this();
        this.name="Mick";
    }     
     private void print(){
         System.out.println("最终名字="+this.name);
         System.out.println("最终的年龄="+this.age);
    }
    public static void main(String[] args) {
    	ConstructerTest tt=new ConstructerTest("",0); //随便传进去的参数
       tt.print();
    }
    
}