package com.method.util;

import java.util.StringTokenizer;

public class StringUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		token("刘德华 张学院所 得来送 到家 分裂打发");
		
//		String s = "刘德华 张学院所 得来送 到家 分裂打发" ;
//		System.out.println(md5(s)) ;
		String ss = "http://www.3vbook.net/modules/article/articleinfo.php?id=632" ;
		String reg ="http://www.3vbook.net/modules/article/articleinfo.php\\?id=\\d+" ;
		System.out.println(ss.matches(reg)) ;
		
//		字符串转码 unicode > gbk
		System.out.println(UnicodeToGBK1("\\u9009\\u5173"));
		System.out.println(UnicodeToGBK2("31&#31181;&#32654;&#22269;&#39118;&#21619;&#20912;&#28103;&#28107;")) ;
		
	}
	
	/**
	 * 三目运算 第一个参数不为空或NULL则返回,否则返回第二个参数
	 * @param a
	 * @param b
	 * @return
	 */
	public static String caseOpera(String first,String second)
	{
		return (first != null && !first.equals(""))? first : second ;
	}
	
	/**
	 * 字符串比较
	 * @param a
	 * @param b
	 * @return 0 a=b, 1 a>b ,-1 a<b
	 */
	public static int compString(String a,String b){
		return a.compareTo(b) ;		
	}
	
	/**
	 * 字符串处理
	 * @param key
	 */
	public static void token(String key){
        StringTokenizer st = new StringTokenizer(key);
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens()){
             String token = st.nextToken();
             sb.append("+");
             sb.append(token);
             sb.append(" ");
         }
        System.out.println(sb.toString()) ;
	}
	
	/**
	 * 取MD5值
	 * @param str
	 * @return
	 */
	public static String md5(String str){
		String sb = org.apache.commons.codec.digest.DigestUtils.md5Hex(str) ;
		return sb ;
	}
	
	/**
	 * 中文字符串截段
	 * @param src
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static String subString(String src,int begin,int end)
	throws Exception{
		String str = "";
		if(begin>=src.length()){
			throw new Exception("开始位置超出字符串长度！！");
		}
		int a = realPoint(src,begin);
////
//		if(end>=src.length()){
//			throw new Exception("长度超出字符串长度！！");
//		}
		int b = realPoint(src,end);
		str = src.substring(a,b);
		return str ;
	}
	public static String subString(String src,int length)
	throws Exception{
		return subString(src,0,length);
	}
	
//	求中文字符串长度
	public static int getLength(String src){
		int len = 0;
		if(src==null || src.length()==0){
			return len;
		}
		int i = 0;
		int javalen = src.length();
		try{
			while(i<javalen){
				String s = src.substring(i,i+1);
				 if(isChinese(s)){
					 len++;
				 }
				 len++;
				 i++;
			}
		}catch(Exception e){
			System.out.println("err:"+e.getMessage());
		}
		return len ;
	}
	
	/**
	 * 判断字符str是否是中文
	 * @param str 长度为1的字符串
	 * @return
	 */
	private static boolean isChinese(String str) {
		if (str == null){
			return false;
		}
		boolean isChinese = false;
		byte b1;
		try {
			b1 = str.getBytes("ISO8859_1")[0];
			if (b1 == 63) {
				isChinese = true;
			}
			else if (b1 > 0) {
				isChinese = false;
			}
			else if (b1 < 0) { // 不可能为0，0为字符串结束符
				isChinese = true;
			}
		} catch (Exception e) {
			return false;
		}
		return isChinese;
	}
	
	/**
	 * 字符串第point个中文字符长度的实际位置
	 * @param string
	 * @param point
	 * @return
	 */
	private static int realPoint(String string,int point){
		int i = 0;
		int p = 0;
		try{
			while(p<point*2){
				String s = string.substring(i,i+1);
				 if(isChinese(s)){
					 p++;
				 }
				 p++;
				 i++;
			}
		}catch(Exception e){
			System.out.println("err:"+e.getMessage());
		}
		return i ;
	}
	
	/**
	 * Unicode 转GBK码
	 * 
	 * @param dataStr 字符串类型 \\u9009\\u5173
	 * @return
	 */
	public static String UnicodeToGBK1(String dataStr) {
		int index = 0;
		StringBuffer buffer = new StringBuffer();
		while(index<dataStr.length()) {
			if(!"\\u".equals(dataStr.substring(index,index+2))){
				buffer.append(dataStr.charAt(index));
				index++;
				continue;
			}
			String charStr = "";
			charStr = dataStr.substring(index+2,index+6);
			char letter = (char) Integer.parseInt(charStr, 16 );
			buffer.append(letter);
			index+=6;
		}
		return buffer.toString();
	}
	
	/**
	 * Unicode 转GBK码
	 * 
	 * @param s 字符串类型 &#27993;&#33756;
	 * @return
	 */
	public static String UnicodeToGBK2(String s){
		String[] k = s.split(";") ;
		String rs = "" ;
		for(int i=0;i<k.length;i++) {
			int strIndex=k[i].indexOf("&#");
			String newstr = k[i];
			if(strIndex>-1) {
				String kstr = "";
				if(strIndex>0) {
					kstr = newstr.substring(0,strIndex);
					rs+=kstr;
					newstr = newstr.substring(strIndex);
				}
				int m = Integer.parseInt(newstr.replace("&#",""));
				char c = (char)m ;
				rs+= c ;
			} else {
				rs+=k[i];
			}
		}
		return rs;
	}
	
}
