package com.method.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * by dyong 2010-7-28
 */
public class ShortUrlServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response) ;
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		长URL格式：IP:端口号/工程名/servlet名?参数名=参数
//		短URL格式：IP/参数。端口号80，省略。工程名设置默认工程，省略。servlet名设置/*省略。参数名省略
		String uri = request.getRequestURI() ;
		String sn = uri.substring(uri.lastIndexOf("/")+1) ;
		System.out.println(sn) ;
		
		String outStr = "" ;
		byte[] html = outStr.getBytes("GBK");
//		数据流zip压缩
		String encoding=request.getHeader("Accept-Encoding");
		if(encoding!=null && encoding.contains("gzip")){
			response.setHeader("Content-Encoding", "gzip");
			html = gzip(html);
		}
		response.setContentLength(html.length);
		ServletOutputStream os = response.getOutputStream();
		os.write(html);
		os.close();
			
	}
	public static byte[] gzip(byte[] data) {   
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);   
        GZIPOutputStream output = null;   
        try {   
            output = new GZIPOutputStream(byteOutput);   
            output.write(data);   
        } catch (IOException e) {   
           throw new RuntimeException("G-Zip failed.", e);   
       } finally {   
           if (output != null) {   
               try {   
                   output.close();   
               } catch (IOException e) {   
               }   
           }   
       }   
       return byteOutput.toByteArray();   
   }
}
