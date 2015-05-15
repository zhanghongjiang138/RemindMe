package com.url.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTestExample {
	public static void main(String[] args){
		URL url=null;
		try{
		long begintime = System.currentTimeMillis();
        
        url = new URL("https://ssousflogin.corporate.ge.com/SSOLogin/verify.fcc");
        System.out.println(url.getHost());
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.setRequestMethod("POST");
//        urlcon.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");    
//        urlcon.connect();         //获取连接
        String queryString="username=502357324&password=Zhanlf@1990s";
        urlcon.setDefaultUseCaches(false);
        urlcon.setDoOutput(true);
        urlcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlcon.setRequestProperty("Content-Length", String.valueOf(queryString.length()));
		PrintWriter out = new PrintWriter(urlcon.getOutputStream());
		out.print(queryString);//传入参数
		out.flush();
		out.close();
		urlcon.connect();//连接
		
        InputStream is = urlcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while((l=buffer.readLine())!=null){
            bs.append(l).append("/n");
        }
        System.out.println(bs.toString());
       
        //System.out.println(" content-encode："+urlcon.getContentEncoding());
        //System.out.println(" content-length："+urlcon.getContentLength());
        //System.out.println(" content-type："+urlcon.getContentType());
        //System.out.println(" date："+urlcon.getDate());
             
        System.out.println("总共执行时间为："+(System.currentTimeMillis()-begintime)+"毫秒");
     }catch(IOException e){
        System.out.println(e);
     	}
 

	}

}
