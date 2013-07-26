package my.common.database.ibatis.city;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import my.common.database.ibatis.config.SqlMapConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


import com.ibatis.sqlmap.client.SqlMapClient;

/** 
 * by dyong 2010-7-22
 */
public class InsertTabelMain {

	public List<String> list = new ArrayList<String>() ;
	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, BadHanyuPinyinOutputFormatCombination {
		// TODO Auto-generated method stub
		
		InsertTabelMain m = new InsertTabelMain() ;
//		m.putDatabese0() ;
		m.putDatabese() ;
	}
	public void putDatabese() throws SQLException, BadHanyuPinyinOutputFormatCombination{
		SqlMapClient sqlmap = SqlMapConfig.getSqlMapClient() ;
		City pc = new City() ;
		list.add("0") ;
		pc.setId("0") ;
		ArrayList<City> l = new ArrayList<City>() ;
		l.add(pc) ;
		Iterator<City> it  ;
		while((it = l.iterator()).hasNext()){
			pc = it.next() ;
			l.remove(pc) ;
			List<City> list = sqlmap.queryForList("selectCityByID",pc.getId()) ;
			
			if(list!=null && list.size()>0){
				for(City c:list){
					String qp = getPinYin(c.getAlias()) ;
					String jp = "" ;
					String[] ws = qp.split(" ");
					for(String w:ws){
						jp+=w.substring(0,1).toUpperCase() ;
					}
					String qp2 = getPinYin(c.getCname()) ;
					ws = qp2.split(" ");
					jp+= " " ;
					for(String w:ws){
						jp+=w.substring(0,1).toUpperCase() ;
					}
					
					qp = qp.replaceAll(" ","") ;
					c.setA_spell(qp) ;
					c.setS_spell(jp) ;
					
					c.setInstact_name(pc.getInstact_name()) ;
					c.setInstact_name(c.getCname()) ;
					
					c.setInstact_spell(pc.getInstact_spell()) ;
					c.setInstact_spell(qp) ;
					c.setInstact_spell(jp) ;
					sqlmap.insert("insertCity",c) ;
					l.add(c) ;
				}
			}
		}
	}
	
	public void putDatabese0() throws SQLException, BadHanyuPinyinOutputFormatCombination{
		SqlMapClient sqlmap = SqlMapConfig.getSqlMapClient() ;
		List<City> list = sqlmap.queryForList("selectCity") ;
		if(list!=null && list.size()>0){
			for(City c:list){
				String qp = getPinYin(c.getAlias()) ;
				String jp = "" ;
				String[] ws = qp.split(" ");
				for(String w:ws){
					jp+=w.substring(0,1).toUpperCase() ;
				}
				qp = qp.replaceAll(" ","") ;
				c.setA_spell(qp) ;
				c.setS_spell(jp) ;
				c.setInstact_name(c.getCname()) ;
				c.setInstact_spell(qp) ;
				c.setInstact_spell(jp) ;
				sqlmap.insert("insertCity",c) ;
			}
		}
	}
	
	private String getPinYin(String zhongwen)
    throws BadHanyuPinyinOutputFormatCombination {
		
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i],getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				//    	zhongWenPinYin += capitalize(pinYin[0]);
				zhongWenPinYin += pinYin[0]+" ";
			} else {
				zhongWenPinYin += chars[i];
			}
		}
		return zhongWenPinYin;
	}
	private HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	    format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
	    format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
	    format.setVCharType(HanyuPinyinVCharType.WITH_V);// u显示格式：v u ü
	    return format;
	}
	private String capitalize(String s) {
		char ch[];
	    ch = s.toCharArray();
	    if (ch[0] >= 'a' && ch[0] <= 'z') {
	        ch[0] = (char) (ch[0] - 32);
	    }
	    String newString = new String(ch);
	    return newString;
	}
}
