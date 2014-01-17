package com.method.util;

/**
 * made by dyong 
 * date 2008-11-19 下午02:28:07
 **/
public class CnString {

	private String cn = new String();	//存储要处理的字符串
	private int cnLen = -1 ;
	
	public CnString(String s){
		cn = s ;
	}
	
	/**
	 * 字符串的中文长度
	 * @return
	 */
	public double length(){
		if(cnLen>-1){
			return cnLen/2.0 ;
		}
		cnLen = 0;
		char[] chs = cn.toCharArray() ;
		for(int i=0;i<chs.length;i++){
			if(isChinese(String.valueOf(chs[i]))){
				cnLen +=2 ;
			} else {
				cnLen ++ ;
			}
		}
		return cnLen/2.0 ;
	}
	
//	返回第begin位以后的字符串
	public String substring(double begin){
		int b = realPoint(begin);
		return cn.substring(b);
	}
	
//	返回第begin到end位的字符串
	public String substring(double begin,double end){
		int b = realPoint(begin);
		int e = realPoint(end);
		return cn.substring(b, e);
	}
	
//	输入实际字符的位置，返回对应的中文位置
	private double cnPoint(int point){
		if(point>=cn.length()){
			return length() ;
		}
		int re = 0 ;
		char[] chs = cn.toCharArray() ;
		for(int i=0;i<point;i++){
			if(isChinese(String.valueOf(chs[i]))){
				re +=2 ;
			} else {
				re ++ ;
			}
		}
		return re/2.0 ;
	}
	
//	输入中文字符位置，返回对应的实际字符的位置
	private int realPoint(double point){
		if(point>=length()){
			return cn.length() ;
		}
		int re = 0 ;
		char[] chs = cn.toCharArray() ;
		for(int i=0;i<chs.length;i++){
			if(re>=point*2){
				return i ;
			}
			if(isChinese(String.valueOf(chs[i]))){
				re +=2 ;
			} else {
				re ++ ;
			}
		}
		return cn.length() ;
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
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "sd刘德 华fh。1";
		CnString c = new CnString(str);
		System.out.println(c.length()) ;
//		System.out.println(c.realPoint(5)+"");
//		System.out.println(c.cnPoint(5)+"");
		System.out.println(c.substring(0.5,5)+"");
	}

}
