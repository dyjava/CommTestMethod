package com.method.util;

/**
 * made by dyong 
 * date 2009-8-4 下午03:53:07
 * 钱数显示格式
 **/
public class MoneyUtil {

	private static final char[] yuan = {'元','拾','百','千','万','拾','百','千','亿','拾','百','千'} ;
	private static final char zheng = '整' ;
	private static final char jiao = '角' ;
	private static final char fen = '分' ;

	private static final char[] number = {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'} ;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(changeInt(1011)) ;
		System.out.println(doubleToString(25100.9967352)) ;
		System.out.println(doubleToString(1020.8)) ;
		System.out.println(carry(68.551585634,-4)) ;
		
		System.out.println(doubleToString(Double.parseDouble(args[0]))) ;
	}
	
	/**
	 * 小数格式转换
	 * @param money
	 * @return
	 */
	public static String doubleToString(double money){
		StringBuffer sb = new StringBuffer() ;
		money = carry(money,-3) ;
//		整数
		sb.append(changeInt((int)money)) ;
//		小数
		money = money-(int)money ;
		money = carry(money,-3) ;
//		System.out.println(money) ;
		int j = (int)(money*10) ;
		int f = (int)(money*100%10) ;
		
//		System.out.println(j+"=="+f+money*100%10) ;
		if(j==0 && f==0){
			sb.append(zheng) ;
		} else {
//			角
			if(j==0){
				sb.append(number[0]) ;
			} else {
				sb.append(String.valueOf(number[j])) ;
				sb.append(jiao) ;
			}
//			分
			if(f!=0){
				sb.append(String.valueOf(number[f])) ;
				sb.append(fen) ;
			}
		}

		return sb.toString() ;
	}
	
	/**
	 * 整数格式转换
	 * @param money
	 * @return
	 */
	public static String changeInt(int money){
		StringBuffer sb = new StringBuffer() ;
		int i=0 ;
		while(money>0){
			int a = money%10 ;
			money = money/10 ;
			if(a>0){
				sb.append(yuan[i]) ;
				sb.append(String.valueOf(number[a])) ;
			} else {
				if(i%4==0){
					sb.append(yuan[i]) ;
				}
				sb.append(String.valueOf(number[a])) ;
			}
			
			i++ ;
		}
		
		char[] result = sb.toString().toCharArray() ;
		sb = new StringBuffer() ;
		for(int j=result.length;j>0;j--){
			sb.append(result[j-1]) ;
		}
		
		return removZero(sb.toString()) ;
	}
	
	/**
	 * 处理多余的零，
	 * @param s
	 * @return
	 */
	private static String removZero(String s){
		char ling = number[0] ;
		StringBuffer sb = new StringBuffer() ;
		String zero = String.valueOf(ling) ;
		s = s.replaceAll(zero+zero,zero) ;
		char[] chars = s.toCharArray() ;

		for(int i=0;i<chars.length;i++){
			char c = chars[i] ;
			
			if(c!=ling ){	//非零字符，输出
				sb.append(c) ;
			} else{	//下一个字符为数字，输出
//				以元结尾
				char cc = chars[i+1] ;
				if(!inChars(cc,yuan)){
					sb.append(c) ;
				}
			}
		}
		return sb.toString() ;
	}
	
	/**
	 * 字符是否数字字符判断
	 * @param c
	 * @param chars
	 * @return
	 */
	private static boolean inChars(char c,char[] chars){
		for(int i=0;i<chars.length;i++){
			if(c==chars[i]){
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * 四舍五入
	 * @param number 数字
	 * @param ord 位数，整数代表整数位数，负数代表小数位数
	 * @return
	 */
	private static double carry(double number,int ord){
		if(ord==0){
			return number ;
		} else if(ord>0){
			int num = 0 ;
			int a = 1 ;
			for(int i=0;i<ord;i++){
				a = a*10 ;
			}
			
			num = (int)(number/a)*a ;
			int b = (int)(number%a)*10/a ;
			
			if(b>=5){
				num+=a ;
			}
			return num ;
		} else {
			double num ;
			ord = -ord ;
			int a = 1 ;
			for(int i=0;i<ord;i++){
				a = a*10 ;
			}
			int b = (int)(number*a*10)%10 ;
//			num = (int)(number*a)/1.0/a ;
//			System.out.println(""+num);
			if(b>=5){
				num = (int)(number*a+1)/1.0/a ;
			} else {
				num = (int)(number*a)/1.0/a ;
			}
			return num ;
		}
	}
}
