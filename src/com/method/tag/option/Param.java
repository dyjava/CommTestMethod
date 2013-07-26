package com.method.tag.option;

import java.util.Properties;

/**
 * made by dyong 
 * date 2009-4-23 下午04:05:48
 **/
public class Param {
	
	public static boolean isLoad = false ;
	
	private static String noSelectName ;
	private static String noSelectValue ;

	/**
	 * 初始化变量
	 *
	 */
	public static void load(){
        try {
			Properties p = new Properties();
			p.load(Param.class.getResourceAsStream("option.properties"));
			
			String param = "" ;
//			int i = 0 ;
			param = p.getProperty("NoSelectName");
			if(param!=null){
				noSelectName = param ;
			}
			param = p.getProperty("NoSelectValue");
			if(param!=null){
				noSelectValue = param ;
			}
			
            System.out.println("初始化完毕！");
            isLoad = true ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static String getNoSelectName() {
		return noSelectName;
	}

	public static void setNoSelectName(String noSelectName) {
		Param.noSelectName = noSelectName;
	}

	public static String getNoSelectValue() {
		return noSelectValue;
	}

	public static void setNoSelectValue(String noSelectValue) {
		Param.noSelectValue = noSelectValue;
	}

	
}
