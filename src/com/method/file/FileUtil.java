package com.method.file;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * made by dyong 
 * date 2008-11-19 10:33:03
 **/
public class FileUtil {


	/**
	 * 获取文件最新更新时间
	 * @param filePath
	 * @return
	 */
	public static Date getUpdateTime(String filePath){
		File file = new File(filePath);
		Date date = new Date();
		if(file.exists()){
			date.setTime(file.lastModified());
//			String time = date.toLocaleString();
		}
		return date ;
	}
	public static Date getUpdateTime2(String path){
		String filePath = FileUtil.class.getResource(path).getFile();
		return getUpdateTime(filePath) ;
	}
	
	/**
	 * URL返回状态
	 * @param url
	 * @return
	 */
	@SuppressWarnings("finally")
	public static int urlConnectCode(String url){
		int state = 404 ;
		try{
			URL u = new URL(url);
		    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
		    connection.setReadTimeout(5000) ;
		    state = connection.getResponseCode();
		} catch (Exception e){
			System.out.println(e.getMessage()) ;
			state = 404 ;
		} finally {
			return state ;
		}
	}
	

	/**
	 * URL根据上级URL补全
	 * @param parentUrl
	 * @param url
	 * @return
	 */
	public static String changeUrlPath(String url,String parentUrl) {
		url=url.replace("javascript:opennew('", "").replace("')", "");
		if (!url.startsWith("http") && !url.startsWith("ftp") ) {
			try {
				if(url.startsWith("?")){
					String page = parentUrl.substring(0,parentUrl.indexOf("?"));
					url = page + url ;
				} else {
					url=new URL(new URL(parentUrl),url).toString();
					int s=url.lastIndexOf("#");
					if (s>5){
						url=url.substring(0,s);
					}
				}
			}catch(Exception e) {
				e.printStackTrace() ;
				return null;
			}
		}
		url.replaceAll("&amp;","&") ;
		return url;
	}

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String url = "t.jsp#gg" ;
		String baseUrl = "http://m.12580.com/wap/index.do?v=2" ;
		System.out.println(changeUrlPath(url,baseUrl)) ;
	}

}
