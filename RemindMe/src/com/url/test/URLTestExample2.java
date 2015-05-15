package com.url.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class URLTestExample2 {

		public static void main(String[] args) throws UnknownHostException,
				IOException {
			try {
				try {
					URL url = new URL("http://3.242.164.20:8180/icam-e/html/Login-cc");
					System.out.println(url.getHost());
					HttpURLConnection con = (HttpURLConnection) url
							.openConnection();
					System.out.println(con.getResponseCode());
					con.connect();
					
					
					if (con.getResponseCode() == 200){
						System.out.println("Connection established!!");
					}
				} catch (Exception exception) {
					
					exception.printStackTrace();
					System.out.println("No Connection");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

}
