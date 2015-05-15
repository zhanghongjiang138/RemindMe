package com.sendtext.test;

public class ThreadTest extends Thread{
	private int num;
	private String name;
	
	public ThreadTest(String name){
		this.name=name;
	}
 
	public static void print(String s){
		System.out.println(s);
	}
	@Override
	public void run() {
		ThreadTest tt2=new ThreadTest("test2");
		while(true){
			
			num++;
			print(this.name+" "+num);

		}
	}
	
	public void  staticRun(int number){
		Long l0=System.currentTimeMillis();
		while(this.num<number){
			num++;
		}
		Long l1=System.currentTimeMillis();
		print(String.valueOf(l1-l0));
		
	}
	
	public static void main(String[] args){
		ThreadTest tt2=new ThreadTest("test2");
		/*	ThreadTest tt1=new ThreadTest("test1");
		tt1.start();
		tt2.start();*/

		tt2.staticRun(2000000000);
		
		

	}

}
