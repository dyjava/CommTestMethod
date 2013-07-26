package com.method.file.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * HttpClient的应用范例
 * @author diyong
 *
 */
public class HttpClientTest {

	private HttpClient client ;
	private static HttpClientTest hct = new HttpClientTest();
	public static HttpClientTest getInstance(){
		return hct ;
	}
	private HttpClientTest(){
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		client = new HttpClient(connectionManager);
		HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(15000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(20000);
		//每个连接端口最大连接数
		managerParams.setDefaultMaxConnectionsPerHost(5);  //very important!! 
		//总的最大连接数。是上一个参数的整数倍。
		managerParams.setMaxTotalConnections(10);  //very important!! 
//		client.getHostConfiguration().setProxy(proxyHost, proxyPort) ;//设置代理
	}
	
	/**
	 * get方法获取网页内容
	 * @param url
	 * @param code 网页编码
	 * @param bzip 是否支持压缩
	 * @return
	 */
	public String getUrlContent(String url,String code,boolean bzip){
		GetMethod get = null ;
		StringBuffer html = new StringBuffer() ;
		int statusCode=0 ;
		try {
			get = new GetMethod(url);
			if(bzip){
				get.setRequestHeader("Accept-Encoding","gzip, deflate"); 
			}
			get.setRequestHeader("Referer",url) ;//设置referer
			get.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2)") ;//设置UA
			get.setFollowRedirects(true);	//支持重定向
			
			//支持cookie
			client.getParams().setCookiePolicy(CookiePolicy.RFC_2109);//RFC_2109是支持较普遍的一个，还有其他cookie协议  
			HttpState initialState = new HttpState();
			Cookie cookie=new Cookie();
			cookie.setDomain("m.12580.com");
			cookie.setPath("/");
			cookie.setName("telno");
			cookie.setValue("13527686543");
			initialState.addCookie(cookie);
			client.setState(initialState);
			
			statusCode = client.executeMethod(get);
			if (statusCode == HttpStatus.SC_OK) {
				//应用NIO通道读取。
				ReadableByteChannel rbc ;
//				gzip压缩
				if(bzip && get.getResponseHeader("Content-Encoding")!=null && 
						get.getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip")>-1){
					GZIPInputStream gzin = new GZIPInputStream(get.getResponseBodyAsStream());
					rbc = Channels.newChannel(gzin);
					System.out.println("gzip") ;
				} else {
//					非压缩
					rbc = Channels.newChannel(get.getResponseBodyAsStream());
				}
				//缓冲区大小10k
				ByteBuffer bs = ByteBuffer.allocate(10240);
				@SuppressWarnings("unused")
				int g;
				while((g = rbc.read(bs))>-1){
					// flip方法让缓冲区可以将新读入的数据写入另一个通道  
					bs.flip();
					html.append(new String(bs.array(),code)) ;
					// clear方法重设缓冲区，使它可以接受读入的数据  
					bs.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {
			get.releaseConnection();
		}
		return html.toString() ;
	
	}
	
	/**
	 * Post方法获取网页内容
	 * @param url
	 * @param map
	 * @param code
	 * @return
	 */
	public String postUrlContent(String url,Map<String,String> map,String code){
		PostMethod post = new PostMethod(url);
		
		for(String key:map.keySet()){
			post.addParameter(key, map.get(key));
		}
		String response = "";
		try {
			int statusCode = client.executeMethod(post);
			if (statusCode == HttpStatus.SC_OK) {
				response = new String(post.getResponseBody(),code);
			}
		} catch (HttpException e) {
			post.abort();
			e.printStackTrace();
		} catch (IOException e) {
			post.abort();
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return response ;
	}
	
	/**
	 * 连接状态参数检测。
	 * @param url
	 * @return
	 */
	public boolean urlProbe(String url){
		HeadMethod head = new HeadMethod(url) ;
		try {
			int statusCode = client.executeMethod(head) ;
			if (statusCode  == HttpStatus.SC_OK 
					|| statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {
				return true ;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}
	public Header[] urlHeader(String url){
		HeadMethod head = new HeadMethod(url) ;
		try {
			client.executeMethod(head) ;
			return head.getRequestHeaders();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://www.iteye.com/topic/834447" ;
//		url = "http://m.12580.com/w/redirect.php?atype=1&u=http://m.12580.com/tc/?key=%E5%8C%BA%E5%8F%B7&page=&pos=3&v=2&pid=u01&m=1314758568000_387&vd=Tk9LSUE%3D&md=RTcx&ci=10000000&rtm=1315362905918&lsh=awu5v4yb3za&lrt=1315448411642&lrs=203b8d96&u=http://baike.baidu.com/view/103379.html" ;
		url = "http://www.baidu.com/s?bs=java+nio&f=8&rsv_bp=2&wd=java+nio+%B6%C1%C8%A1URL%C4%DA%C8%DD" ;
//		url = "http://172.16.33.157:8180/ConformSearch/conform.search?q=%E5%8C%97%E4%BA%AC%E5%88%B0%E4%B8%8A%E6%B5%B7%E5%88%97%E8%BD%A6&vt=1&pt=1&pid=u01&uid=0&lpi=10&lci=00&pn=1&ps=5&m=&ip=&p=&ua=12580";
//		String c = HttpClientTest.getInstance().getUrlContent(url, "utf-8", true) ;
//		System.out.println(c) ;
		
		Header[] hs = HttpClientTest.getInstance().urlHeader(url);
		for(Header h:hs){
			System.out.println(h.getName()+"====="+h.getValue()) ;
		}
	}

}
