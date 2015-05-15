package com.sendtext.test;

import java.util.UUID;

public class UUIDTEST {
	public  static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String string = uuid.toString();
		StringBuffer temp = new StringBuffer();
		temp.append(string.substring(0, 8));
		temp.append(string.substring(9, 13));
		temp.append(string.substring(14, 18));
		temp.append(string.substring(19, 23));
		temp.append(string.substring(24));
		return temp.toString();
	}
	
	public static void main(String[] args){
		System.out.println(getUUID());
		System.out.println(getUUID().length());
	}

}
