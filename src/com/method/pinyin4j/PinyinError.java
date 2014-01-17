package com.method.pinyin4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
 * by dyong 2010-8-24
 * 拼音纠错
 */
public class PinyinError {
	private static final String path = "pinyin.err" ;
	private static Hashtable<String,String> table = new Hashtable<String,String>() ;
	private static PinyinError pinyin ;
	private PinyinError(){
		try {
			InputStreamReader isr = new InputStreamReader(PinyinError.class.getResourceAsStream(path),"utf-8");   
			BufferedReader br = new BufferedReader(isr);
			
			String record = "";
			while ((record = br.readLine()) != null) {
				String pinyin = getPinYin(record.trim());
				table.put(pinyin, record) ;
			}
			isr.close();
			br.close();
		}catch(IOException e){
			e.printStackTrace() ;
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static PinyinError getInstance(){
		if(pinyin==null){
			pinyin = new PinyinError() ;
		}
		return pinyin ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String key = "肯德鸡" ;
		String r = PinyinError.getInstance().equalsErrorWord(key) ;
		System.out.println(r) ;
		
		key = "md,爱吃肯德鸡,也爱吃麦当劳。爱刘德花，更爱自己" ;
		r = PinyinError.getInstance().containErrorWord(key) ;
		System.out.println(r) ;
	}
	
	/**
	 * 词纠错，纠错词中的别字
	 * @param key
	 * @return
	 */
	public String equalsErrorWord(String key){
		try {
			String keyPinyin = getPinYin(key);
			if(table.containsKey(keyPinyin)){
				return table.get(keyPinyin) ;
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return key ;
	}
	
	/**
	 * 句子纠错，纠错句子中的拼音错误。
	 * @param key
	 * @return
	 */
	public String containErrorWord(String key){
		try {
			String pinyin = getPinYin(key);
			
			Enumeration<String> en = table.keys();
			while(en.hasMoreElements()){
				String k = en.nextElement() ;
				String v = table.get(k) ;
				int p = pinyin.indexOf(k) ;
				if(p>-1){
					int len = pinyin.substring(0,p).split("/").length ;
					key = key.substring(0,len)+v+key.substring(len+v.length()) ;
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key ; 
	}
	
	private String getPinYin(String zhongwen)
	    throws BadHanyuPinyinOutputFormatCombination {
	
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
		    String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
		    		getDefaultOutputFormat());
		    // 当转换不是中文字符时,返回null
		    if (pinYin != null) {
		    	zhongWenPinYin += pinYin[0]+"/";
		    } else {
		    	zhongWenPinYin += chars[i]+"/";
		    }
		}  
		System.out.println(zhongWenPinYin) ;
		return zhongWenPinYin;
	}
	
	/** 
	* Default Format 默认输出格式 
	*  
	* @return 
	*/  
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);// u显示格式：v u ü
		return format;
	}
}
