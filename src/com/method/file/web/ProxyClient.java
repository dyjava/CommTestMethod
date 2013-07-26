package com.method.file.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

public class ProxyClient {
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	public static Logger log = Logger.getLogger(ProxyClient.class);

	public static String downPage(String url, String temp, Proxy p, int seconds) {
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHostConfiguration()
					.setProxy(p.getHost(), p.getPort());
			Credentials defaultcreds = new UsernamePasswordCredentials(p
					.getUser(), p.getPassword());
			httpClient.getState().setProxyCredentials(
					new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
					defaultcreds);
			GetMethod getMethod = new GetMethod(url);
			getMethod.setFollowRedirects(true);
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				FileOutputStream fos = new FileOutputStream(temp);
				String cs = getMethod.getResponseCharSet();
				String rescontent = "";
				if (cs.equalsIgnoreCase("UTF-8")) {
					rescontent = getMethod.getResponseBodyAsString();
				} else {
					rescontent = new String(getMethod.getResponseBodyAsString()
							.getBytes(cs), "GBK");
				}
				fos.write(rescontent.getBytes());
				fos.close();
				Thread.sleep(seconds);
				return SUCCESS;
			}
		} catch (HttpException e) {
			log.error(e);
			return FAILED;
		} catch (IOException e) {
			log.error(e);
			return FAILED;
		} catch (InterruptedException e) {
			log.error(e);
			return FAILED;
		}
		return FAILED;

	}

	public static String downPage(String url, String temp, int seconds) {
		try {
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(url);
			getMethod.setFollowRedirects(true);
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				File file = new File(temp.substring(0,temp.lastIndexOf('/')));
				if(!file.exists()){
					file.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(temp);
				String cs = getMethod.getResponseCharSet();
				String rescontent = "";
				if (cs.equalsIgnoreCase("UTF-8")) {
					rescontent = getMethod.getResponseBodyAsString();
				} else {
					rescontent = new String(getMethod.getResponseBodyAsString()
							.getBytes(cs), "GBK");
				}
				fos.write(rescontent.getBytes());
				fos.close();
				Thread.sleep(seconds);
				return SUCCESS;
			}
		} catch (HttpException e) {
			log.error(e);
			return FAILED;
		} catch (IOException e) {
			log.error(e);
			return FAILED;
		} catch (InterruptedException e) {
			log.error(e);
			return FAILED;
		}
		return FAILED;

	}

	public static String autoDownPage(String url, String temp, int seconds) {
		List<Proxy> proxys = getProxy();
		String status = downPage(url, temp, seconds * 1000);
		if (status.equals(SUCCESS)) {
			System.out.println("SUCCESS:"+url);
		} else {
			int r = (int)(Math.random()*proxys.size());
			Proxy p = proxys.get(r);
			status = downPage(url, temp, p, seconds * 1000);
			System.out.println("PROXY SUCCESS:"+url);
		}
		return status;
	}

	private static List<Proxy> getProxy() {
		ArrayList<Proxy> list = new ArrayList<Proxy>() ;
		Proxy p = new Proxy() ;
		p.setHost("119.31.187.19") ;
		p.setPort(80) ;
		p.setUser("") ;
		p.setPassword("") ;
		list.add(p) ;
		
		p = new Proxy() ;
		p.setHost("24.25.26.85") ;
		p.setPort(80) ;
		p.setUser("") ;
		p.setPassword("") ;
		list.add(p) ;
		return list ;
	}

	public static void main(String[] args) throws HttpException, IOException,
			InterruptedException {
		Proxy p = new Proxy();
		p.setHost("119.31.187.19");
		p.setPort(80);
		p.setUser("");
		p.setPassword("");
		ProxyClient pc = new ProxyClient();
		pc.downPage("http://mobile.958shop.com/", "d:/temp.html", p, 10000);

	}
}
