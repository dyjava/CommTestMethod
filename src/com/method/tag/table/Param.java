package com.method.tag.table;

import java.util.Properties;

/**
 * made by dyong 
 * date 2009-4-29 下午03:15:12
 **/
public class Param {

	public static boolean isLoad = false ;
	
	public static String param = "property" ;
	
	public static String border = "0" ;
	public static String cellspacing = "0" ;
	public static String cellpadding = "0" ;
//	title
	public static String align_title = "center" ;
	public static String valign_title = "middle" ;
	public static String bgcolor_title = "" ;
//	data
	public static String align_data = "center" ;
	public static String valign_data = "middle" ;
	public static String bgcolor_data = "" ;
	
	public static void load(){
        try {
			Properties p = new Properties();
			p.load(Param.class.getResourceAsStream("table.properties"));
			
			String param = "" ;
			param = p.getProperty("BORDER");
			if(param!=null){
				border = param ;
			}
			param = p.getProperty("CELL_SPACING");
			if(param!=null){
				cellspacing = param ;
			}
			param = p.getProperty("CELL_PADDING");
			if(param!=null){
				cellpadding = param ;
			}
//			title
			param = p.getProperty("ALIGN_T");
			if(param!=null){
				align_title = param ;
			}
			param = p.getProperty("VALIGN_T");
			if(param!=null){
				valign_title = param ;
			}
			param = p.getProperty("BGCOLOR_T");
			if(param!=null){
				bgcolor_title = param ;
			}
//			data
			param = p.getProperty("ALIGN_D");
			if(param!=null){
				align_data = param ;
			}
			param = p.getProperty("VALIGN_D");
			if(param!=null){
				valign_data = param ;
			}
			param = p.getProperty("BGCOLOR_D");
			if(param!=null){
				bgcolor_data = param ;
			}
			
            System.out.println("table.properties初始化完毕！");
            isLoad = true ;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	
		
	}
}
