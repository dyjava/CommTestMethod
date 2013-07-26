package com.method.tag.page;

import java.util.Properties;

/**
 * made by dyong 
 * date 2009-4-22 下午03:24:30
 **/
public class Param {

	public static boolean isLoad = false ;
//	文字翻页显示信息
	private static String FistPage = "" ;	//首页
	private static String NextPage = "" ;	//下页
	private static String PrePage = "" ;	//上页
	private static String LastPage = "" ;	//未页
	private static String WordSepa = "" ;	//文字分割苻
	
//	数字翻页显示信息
	private static String NumSepa = "." ;	//数字分割苻
	private static int Num = 5 ;			//数字翻页显示数
	private static String Pre = "<" ;		//上页
	private static String Next = ">" ;		//下页
	
	/**
	 * 初始化变量
	 *
	 */
	public static void load(){
        try {
			Properties p = new Properties();
			p.load(Param.class.getResourceAsStream("page.properties"));
			
			String param = "" ;
			int i = 0 ;
			param = p.getProperty("FIST_PAGE");
			if(param!=null){
				FistPage = param ;
			}
			param = p.getProperty("NEXT_PAGE");
			if(param!=null){
				NextPage = param ;
			}
			param = p.getProperty("PRE_PAGE");
			if(param!=null){
				PrePage = param ;
			}
			param = p.getProperty("LAST_PAGE");
			if(param!=null){
				LastPage = param ;
			}
			param = p.getProperty("WORD_SEPA");
			if(param!=null){
				WordSepa = param ;
			}
			
			param = p.getProperty("NUM_SEPA");
			if(param!=null){
				NumSepa = param ;
			}
			i = Integer.parseInt(p.getProperty("NUM"));
			if(param!=null){
				Num = i ;
			}
			param = p.getProperty("PRE");
			if(param!=null){
				Pre = param ;
			}
			param = p.getProperty("NEXT");
			if(param!=null){
				Next = param ;
			}
			
            System.out.println("初始化完毕！");
            isLoad = true ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] a){
		load() ;
		System.out.println(getFistPage()) ;
		System.out.println(getNumSepa()) ;
	}

	public static String getFistPage() {
		return FistPage;
	}

	public static void setFistPage(String fistPage) {
		FistPage = fistPage;
	}

	public static String getLastPage() {
		return LastPage;
	}

	public static void setLastPage(String lastPage) {
		LastPage = lastPage;
	}

	public static String getNext() {
		return Next;
	}

	public static void setNext(String next) {
		Next = next;
	}

	public static String getNextPage() {
		return NextPage;
	}

	public static void setNextPage(String nextPage) {
		NextPage = nextPage;
	}

	public static int getNum() {
		return Num;
	}

	public static void setNum(int num) {
		Num = num;
	}

	public static String getNumSepa() {
		return NumSepa;
	}

	public static void setNumSepa(String numSepa) {
		NumSepa = numSepa;
	}

	public static String getPre() {
		return Pre;
	}

	public static void setPre(String pre) {
		Pre = pre;
	}

	public static String getPrePage() {
		return PrePage;
	}

	public static void setPrePage(String prePage) {
		PrePage = prePage;
	}

	public static String getWordSepa() {
		return WordSepa;
	}

	public static void setWordSepa(String wordSepa) {
		WordSepa = wordSepa;
	}

}
