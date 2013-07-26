package com.method.pinyin4j;

import java.util.ArrayList;
import java.util.HashSet;

import org.apache.log4j.Logger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
 * by dyong 2010-6-23
 */
public class Pinyin {
	private static Logger log = Logger.getLogger(Pinyin.class.getName());
	private static String split = "\t" ;
	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		// TODO Auto-generated method stub
		
		String str = "尛	犇	骉	垚	壵	羴	猋	麤	鱻	赑	刕	掱	劦	毳	矗	飝	厽	叒	孨	歮	皛	畾	嚞	舙	灥	飍	馫	飝	厵	靐	龘" ;
//		str = "觋" ;
//		str = "大夫" ;
		str = "北京的天气最近很反常，春天了还这么冷，下了几次雪了，这几天居然刮起浮尘" ;
		getPinYin(str +" 中国绿") ;
	}

	/** 
	 * 获取拼音 
	 *  
	 * @param zhongwen 
	 * @return 
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */  
	public static String getPinYin(String zhongwen)
	        throws BadHanyuPinyinOutputFormatCombination {
		log.info("Input ZhongWen=	" + zhongwen);
		
	    String zhongWenPinYin = "";
	    char[] chars = zhongwen.toCharArray();
	    
	    for (int i = 0; i < chars.length; i++) {
	        String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],
	        		getDefaultOutputFormat());
	        // 当转换不是中文字符时,返回null
	        if (pinYin != null && pinYin.length>0) {
		        ArrayList<String> pyList = quChong(pinYin) ; 
	        	if(pyList.size()==1){
//		        	zhongWenPinYin += capitalize(pinYin[0]);
	        		zhongWenPinYin += pyList.get(0)+split;
	        	} else {
	        		zhongWenPinYin +="(" ;
	        		for(int p=0;p<pyList.size();p++){
	        			zhongWenPinYin += pyList.get(p)+",";
	        		}
	        		zhongWenPinYin +=")"+split ;
	        	}
	        } else {
	        	zhongWenPinYin += chars[i];
	        }
	    }  
	
	    log.info("Output PinYin=	" + zhongWenPinYin);
	    return zhongWenPinYin;
	}
	
	private static ArrayList<String> quChong(String[] py) {
		HashSet<String> set = new HashSet<String>() ;
		ArrayList<String> list = new ArrayList<String>() ;
		for(int i=0;i<py.length;i++){
			if(!set.contains(py[i])) {
				set.add(py[i]) ;
				list.add(py[i]) ;
			}
		}
		return list;
	}

	/** 
	 * Default Format 默认输出格式 
	 *  
	 * @return 
	 */  
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
	    format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);// 没有音调数字
	    format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);// u显示格式：v u ü
	    return format;
	}
	
	/** 
	 * Capitalize 首字母大写 
	 *  
	 * @param s 
	 * @return 
	 */  
	public static String capitalize(String s) {
		char ch[];
	    ch = s.toCharArray();
	    if (ch[0] >= 'a' && ch[0] <= 'z') {
	        ch[0] = (char) (ch[0] - 32);
	    }
	    String newString = new String(ch);
	    return newString;
	}  
}
