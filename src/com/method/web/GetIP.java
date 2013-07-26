package com.method.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/** 
 * by dyong 2010-7-13
 */
public class GetIP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean b = isIpAddress("172.4.3.01") ;
		System.out.println(b) ;
	}
	   /**
     * ip校验
     * @param s
     * @return
     */
    public static Boolean isIpAddress(String s){
            String regex = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(s);
            return m.matches();
    }

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getClientAddress(HttpServletRequest request) {
    	String ip = request.getHeader("X-Forwarded-For");
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getHeader("Proxy-Client-IP");
    	}
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getHeader("WL-Proxy-Client-IP");
    	}
    	if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
    		ip = request.getRemoteAddr();
    	}
    	if(isIpAddress(ip)){
    		return ip;
    	}
    	return ip ;
    }
}
