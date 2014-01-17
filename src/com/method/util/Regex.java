package com.method.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	/**
	 * 正则表达式
	 * Email	验证是否是email
	 * Url		url路径
	 */
	public static String Email = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	public static String Url = "http://([^/]+)";
	
	public static Pattern getPattern(String reg){
		return Pattern.compile(reg) ;
	}
	
	public static Matcher getMatcher(String reg,String souce){
		Pattern p = Pattern.compile(reg) ;
		return p.matcher(souce) ;
	}
	public static Matcher getMatcher(Pattern p,String souce){
		return p.matcher(souce) ;
	}
	
	/**
	 * 检测sorce字符串中是否匹配reg正则
	 * @param reg
	 * @param souce
	 * @return
	 */
	public static boolean isContain(String reg,String souce){
		Pattern p = Pattern.compile(reg) ;
		Matcher m = p.matcher(souce) ;
//		matches 方法尝试将整个输入序列与该模式匹配。 
//		lookingAt 尝试将输入序列从头开始与该模式匹配。 
//		find 方法扫描输入序列以查找与该模式匹配的下一个子序列。
		return m.matches();
	}
	public static boolean isContain(Matcher m,String souce){
		return m.matches();
	}
	
	/**
	 * 匹配reg正则，并且替换匹配到的字符串
	 * @param lines
	 * @param reg	正则式
	 * @param target 替换的字符串
	 * @return
	 */
	public static String replaceString(String lines,String reg,String target){
		Pattern p = Pattern.compile(reg) ;
		Matcher m = p.matcher(lines) ;
		return m.replaceAll(target) ;
//		return Pattern.compile(reg).matcher(lines).replaceAll(target); 
	}
	public static String replaceString(String lines,Pattern p,String target){
		Matcher m = p.matcher(lines) ;
		return m.replaceAll(target) ;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		String email = "dyong525@163.com" ;
		System.out.println(isContain(Email,email));
		System.out.println(isContain(Url,"http://162.net"));
		String lines = "Billy tried very hard ，Sally tried very very hard ，Timmy tried very very very hard，Johnny tried very very very very hard";
		System.out.println(replaceString(lines,"(very )+","really "));
	}

}
