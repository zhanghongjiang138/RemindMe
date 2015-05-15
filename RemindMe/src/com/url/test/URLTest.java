package com.url.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTest {
	
	public static void main(String[] args) throws IOException{
		URL url=null;
		try{
//			url=new URL("http://www.baidu.com");
			url=new URL("http://3.242.164.20:8180/icam-e/html/Login-cc");
//			url=new URL("http://3.242.164.20:8180/icam-e/html/Welcome-cc-cmd");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(url!=null){
		    StringBuffer sb=new StringBuffer();
			URLConnection uc=url.openConnection();
			HttpURLConnection hc=(HttpURLConnection)uc;
			InputStream is=hc.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			char[] c=new char[1024];
		    
			while(br.read(c)!=-1){
				sb.append(c);
				System.out.print(c);
			}
//			String reg="event";
			Pattern p=Pattern.compile("function[a-z]u007B");
			Matcher m=p.matcher(sb.toString());
			System.out.println("\n"+m.find());
			System.out.println("\n"+m.group());
		}
	}
}
