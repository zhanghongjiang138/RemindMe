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
         System.out.println("��������="+this.name);
         System.out.println("���յ�����="+this.age);
    }
    public static void main(String[] args) {
    	ConstructerTest tt=new ConstructerTest("",0); //��㴫��ȥ�Ĳ���
       tt.print();
    }
    
}