package com.method.util;

import java.util.Stack;

/** 
 * 进位转换函数
 * 任意进制数据间的转换
 * by dyong 2010-7-28
 */
public class CarryTransfer {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CarryTransfer ct = new CarryTransfer() ;
//		int ten = 39 ;
//		System.out.println(ct.tran_10_2(ten)) ;
//		System.out.println(ct.tran_10_8(ten)) ;
//		System.out.println(ct.tran_10_16(ten)) ;
//		System.out.println(ct.tran_10_r(ten,36)) ;
//		
//		System.out.println(ct.tran_2_10("10110")) ;
//		System.out.println(ct.tran_8_10("21")) ;
//		System.out.println(ct.tran_16_10("c")) ;
//		System.out.println(ct.tran_r_10("v",36)) ;
		
		System.out.println(num.length) ;
		for(int i=100000;i<1000000;i++){
//			System.out.println(i+"	2>"+ct.tran_10_2(i)+"	8>"+ct.tran_10_8(i)+"	16>"+ct.tran_10_16(i)+"	36>"+ct.tran_10_r(i,36)) ;
			System.out.println(i+"	2>"+ct.tran_10_m(i,2)+"	8>"+ct.tran_10_m(i,8)+"	16>"+ct.tran_10_m(i,16)+"	36>"+ct.tran_10_m(i,36)+"	64>"+ct.tran_10_m(i,64)+"	94>"+ct.tran_10_m(i,90)) ;
//			String d = ct.tran_10_m(i,94) ;
//			System.out.println(d+"	"+ct.tran_m_10(d, 94)) ;
		}
	}

	public String tran_10_2(int ten){
		return Integer.toBinaryString(ten) ;
	}
	public String tran_10_8(int ten){
		return Integer.toOctalString(ten) ;
	}
	public String tran_10_16(int ten){
		return Integer.toHexString(ten) ;
	}
	public String tran_10_36(int ten){
		return Integer.toString(ten, 36) ;
	}
	public String tran_10_r(int ten,int radix){
//		radix最大值是36，即 0-9 a-z 表示
		return Integer.toString(ten, radix) ;
	}
	public int tran_2_10(String in){
		return Integer.valueOf(in,2) ;
	}
	public int tran_8_10(String in){
		return Integer.valueOf(in,8) ;
	}
	public int tran_16_10(String in){
		return Integer.valueOf(in,16) ;
	}
	public int tran_36_10(String in){
		return Integer.parseInt(in,36) ;
	}
	public int tran_r_10(String in,int radix){
//		radix最大值是36
		return Integer.parseInt(in,radix) ;
	}
	
	/**
	 * 
	 */
	private static final char[] num = {
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'-','*','_','=','+',
		',','.','<','>','?','(',')','[',']','{','}',
		'~','`','!','@','#','$','%','^','&',';',':',
		'/','\\','|','\'','\"'
		} ;
	/**
	 * 十进制和其他进制的转换，最大和num数组长度一致。
	 * @param m
	 * @param ten
	 * @return
	 * @throws Exception 
	 */
	public String tran_10_m(int ten,int m) throws Exception{
		int yu = 0 ;
		Stack<Object> st = new Stack<Object>() ;
		do{
			yu = ten %m ;
			ten = ten/m ;
			if(num.length>yu){
				st.add(num[yu]) ;
			} else {
				throw new Exception("transfer carry is out of num length :"+num.length+"!") ;
			}
		} while(ten>0) ;
		
		return getString(st) ;
	}
	
	public int tran_m_10(String in,int m){
//		if(m<=36){
//			return this.tran_r_10(in, m) ;
//		}
		
		int re = 0 ;
		char[] ch = in.toCharArray() ;
		for(char c:ch){
			int i = 0 ;
			for(;i<num.length;i++){
				if(c==num[i]){
					break ;
				}
			}
			re = re *m+i  ;
		}
		return re ;
	}
	
	private String getString(Stack<Object> st){
		StringBuffer buf = new StringBuffer() ;
		while(!st.empty()){
			buf.append(st.pop()) ;
		}
		return buf.toString() ;
	}
}
