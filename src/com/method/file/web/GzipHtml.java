package com.method.file.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

/** 
 * by dyong 2010-10-18
 */
public class GzipHtml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * HTML请求
	 * 请求头信息添加支持gzip压缩
	 * @param url
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String unGzipHtml(String url) throws HttpException, IOException{
		HttpClient http = new HttpClient() ;
		String html = "" ;
		GetMethod get = new GetMethod(url);
		get.addRequestHeader("accept-encoding", "gzip,deflate") ;
		try{
			int er = http.executeMethod(get);
			if(er==200){
				html = getResponseBodyAsString(get) ;
			}
		} finally {
			get.releaseConnection();
		}
		return html ;
	}
	/**
	 * 读取请求内容
	 * 如果返回内容是压缩内容，解压缩
	 * @param get
	 * @return
	 * @throws IOException
	 */
	private static String getResponseBodyAsString(GetMethod get) throws IOException {
		if (get.getResponseBody() != null) {
			if(get.getResponseHeader("Content-Encoding") != null
					&& get.getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip") > -1) {
				//For GZip response
				InputStream is = get.getResponseBodyAsStream();
				GZIPInputStream gzin = new GZIPInputStream(is);
				
				InputStreamReader isr = new InputStreamReader(gzin, get.getResponseCharSet());
				java.io.BufferedReader br = new java.io.BufferedReader(isr);
				StringBuffer sb = new StringBuffer();
				String tempbf;
				while ((tempbf = br.readLine()) != null) {
					sb.append(tempbf);
					sb.append("\r\n");
				}
				isr.close();
				gzin.close();
				return sb.toString();
			} else {
				return get.getResponseBodyAsString();
			}
		} else {
			return null;
		}
		
	}
	
	/**
	 * html输出
	 * 如果请求支持gzip 
	 * 输出内容gzip压缩
	 * 输出头信息写入gzip头信息
	 * @param response
	 * @param request
	 * @param content
	 * @throws IOException
	 */
	public static void gzipHtml(HttpServletResponse response,HttpServletRequest request, String content)
	throws IOException{
		byte[] data = content.getBytes("utf-8") ;
		
		String encoding = request.getHeader("Accept-Encoding");
		if(encoding !=null && encoding.indexOf("gzip")>-1){
			response.setHeader("Content-Encoding", "gzip") ;
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240) ;
			GZIPOutputStream output = null ;
			try {
				output = new GZIPOutputStream(byteOutput);
				output.write(data);
			} catch (IOException e) {
				throw new RuntimeException("G-Zip failed.", e);
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {}
				}
			}
			data = byteOutput.toByteArray();
		}
		response.setContentLength(data.length);
		ServletOutputStream os = response.getOutputStream();
		os.write(data);
		os.close();

	}
}
