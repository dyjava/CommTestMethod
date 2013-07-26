package com.method.tag.page;

import java.util.Properties;

/**
 * made by dyong 
 * date 2009-4-22 ����03:24:30
 **/
public class Param {

	public static boolean isLoad = false ;
//	���ַ�ҳ��ʾ��Ϣ
	private static String FistPage = "" ;	//��ҳ
	private static String NextPage = "" ;	//��ҳ
	private static String PrePage = "" ;	//��ҳ
	private static String LastPage = "" ;	//δҳ
	private static String WordSepa = "" ;	//���ַָ���
	
//	���ַ�ҳ��ʾ��Ϣ
	private static String NumSepa = "." ;	//���ַָ���
	private static int Num = 5 ;			//���ַ�ҳ��ʾ��
	private static String Pre = "<" ;		//��ҳ
	private static String Next = ">" ;		//��ҳ
	
	/**
	 * ��ʼ������
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
			
            System.out.println("��ʼ����ϣ�");
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
