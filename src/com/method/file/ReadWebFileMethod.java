package com.method.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * made by dyong 
 * date 2008-11-12 下午04:26:03
 **/
public class ReadWebFileMethod {

	private static String UserAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)";
	
	private static boolean ProxySet = false;
	private static String ProxyHost = null;
	private static String ProxyPort = null;
	private static String ProxyAuthenticator = null;
	
	private static int ReadTimeout = 10000;
	private static int ConnectTimeout = 10000;
	
	/**
	 * HttpURLConnection读取网页内容
	 * 读取URL页面内容，返回字符串。
	 * 设置UA 设置代理，设置超时和防盗链，GZIP压缩
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String readUrlContent(String url,String code) throws IOException{
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuffer result = new StringBuffer();
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
//			设置UA
			conn.setRequestProperty("User-Agent", UserAgent);

//			 设置代理服务器用户名和密码
			if (ProxySet) {
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("http.proxyHost", ProxyHost);
				System.getProperties().setProperty("http.proxyPort", ProxyPort);
				conn.setRequestProperty("Proxy-Authorization", ProxyAuthenticator);
			}
//			设置超时
			conn.setConnectTimeout(ConnectTimeout);
			conn.setReadTimeout(ReadTimeout);
//			防盗链地址 Referer
			conn.setRequestProperty("Referer",url);
//			设置gzip压缩
			conn.setRequestProperty("accept-encoding", "gzip,deflate");
			
			String encoding = conn.getContentEncoding() ;
//			gzip压缩
			if(encoding!=null && encoding.toLowerCase().indexOf("gzip") > -1){
				GZIPInputStream gzin = new GZIPInputStream(conn.getInputStream());
				
				InputStreamReader isr = new InputStreamReader(gzin, code);
				in = new java.io.BufferedReader(isr);
			} else {
//				非压缩
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(),code));
			}
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
				result.append("\n");
			}
		} catch (IOException ex) {
			StringWriter sw=new StringWriter();
			ex.printStackTrace(new PrintWriter(sw,true));
			throw new IOException(sw.toString()) ;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				conn.disconnect();
			}
		}
		return result.toString();
	}
	
	public static String readUrlContent2(String url,String code) throws IOException{
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuffer result = new StringBuffer();
		java.io.InputStream is = null;
		try {
			URL u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			is = conn.getInputStream();
			in = new BufferedReader(new InputStreamReader(is,code));
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
				result.append("\n");
			}
		} catch (IOException ex) {
			StringWriter sw=new StringWriter();
			ex.printStackTrace(new PrintWriter(sw,true));
			throw new IOException(sw.toString()) ;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				conn.disconnect();
			}
		}
		return result.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream readUrlInputStream(String url) throws IOException{
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
//		设置UA
		conn.setRequestProperty("User-Agent", UserAgent);

//		设置代理服务器用户名和密码
		if (ProxySet) {
			System.getProperties().setProperty("proxySet", "true");
			System.getProperties().setProperty("http.proxyHost", ProxyHost);
			System.getProperties().setProperty("http.proxyPort", ProxyPort);
			conn.setRequestProperty("Proxy-Authorization", ProxyAuthenticator);
		}
		
//		设置超时
		conn.setConnectTimeout(ConnectTimeout);
		conn.setReadTimeout(ReadTimeout);
		
//		防盗链地址 Referer
		conn.setRequestProperty("Referer",url);
		conn.setRequestProperty("accept-encoding", "gzip,deflate");
		
		return conn.getInputStream();
	}
	
	/**
	 * 字节流读取网页文件
	 * 读取web页面，返回字符串
	 * @param url
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public static String readWebFile(String url,String code)
	throws IOException{
		URL path = new URL(url);
		InputStream is = path.openStream() ;
		InputStreamReader isr = new InputStreamReader(is,code);   
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer() ;
		String s = "" ;
		while((s=br.readLine())!=null){
			sb.append(s).append("\n") ;
		}
		return sb.toString() ;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		readPropertiesFile("/const.properties");
		
		String url = "http://192.168.8.12:8090/RSearchBack/resultServ?key=刘德华&sort=0&pno=0&psize=6";
		url = "http://dev.yesky.com/TLimages/css/yesky-inverse1-0605.css" ;
		url = "http://www.16k.cn/book/showbooklist.aspx?page=2" ;
//		url = "http://www.zuilu.com/Skin/Default/Index.Css" ;
//		url = "http://42143.wansong.net" ;
		String web = readWebFile(url,"gbk") ;
		web = readUrlContent(url,"gbk") ;
//		web = readUrlContent2(url,"gbk") ;
		System.out.println(web) ;
		
		
	}

}
