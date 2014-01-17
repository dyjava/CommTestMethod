package com.method.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * by dyong 2010-7-19
 * 从字符串中提取日期信息
 */
@SuppressWarnings("deprecation")
public class DateExtract {
	
	private static String shuzi = "零|〇|一|二|三|四|五|六|七|八|九|十" ;
	private static String shuzi2 = "\\d|"+shuzi ;
	private static String pre = "今|明|后|去|前|昨" ;
	private static String pre2 = pre+"|上|下|上个|下个|周" ;
	
//	匹配正则
	private static String regex = ".*?((\\.|-|"+shuzi2+"|年|月|日|号|天|"+pre2+"){2,12}).*?" ;
	
//	标准输出日期格式
	private static String dateRegex = "\\d{4}(-|\\.)\\d{1,2}(-|\\.)\\d{1,2}" ;
	
	private final long oneDay = 1000*60*60*24 ;
	private Date date	= new Date() ;
	
	private int year	= date.getYear() +1900 ;
	private int month	= date.getMonth()+1 ;
	private int day		= date.getDate() ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StringBuffer s = new StringBuffer("2010年3月8号我上班，二〇一〇年三月一日辞职，六月八号转正，13号我生日，紫函三号生日，快到八月十五了。\\r\\n");
		s.append("今天天气不错，昨天下雨了，前天也挺凉快的。明天和后天都是大晴天，温度很高。\\r\\n")
		.append("去年八月十五我没回家，今年8月15一定回家，上月一号儿童节，明年3月8号我就入职一周年了。下个月31日该交房租了。\\r\\n")
		.append("本周三和下周四。 去年今天和下月18号，今天是7月18号，或7-18，也可以说是7.18，后天呢？明年三月十八 ") ;

		System.out.println(s) ;
		List<Date> l = new DateExtract().extract(s) ;
		l.size() ;
		System.out.println(s) ;
		
		DateExtract de = new DateExtract() ;
		System.out.println(de.dateFormat(de.StringToDate("2010-0-2"))) ;
		
		System.out.println(de.dateFormat(de.getDateByweek(0,2)));
	}

	public List<Date> extract(StringBuffer input){
		String input2 = input.toString().trim() ;
		List<Date> list = new ArrayList<Date>() ;
		if(input==null || input.length()==0){
			return list ;
		}
		
		if(input2.matches(regex)){
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(input2); 
			while(m.find()){
				String date = m.group(1) ;
//				System.out.println(date);
				Date d = strToDate(date) ;
				if(d!=null){
					System.out.println(date+">>>"+dateFormat(d));
					list.add(d) ;
					int start = input.indexOf(date) ;
					int end = start+date.length() ;
					input = input.replace(start, end,dateFormat(d)) ;
				}
			}
		}
		return list ;
	}
	
	private Date strToDate(String str){
		if(str.length()<2 || str.matches("\\d+")){
			return null ;
		}
		if(str.equals("今天")){
			return date ;
		}
		if(str.equals("明天")){
			return new Date(date.getTime()+oneDay) ;
		}
		if(str.equals("后天")){
			return new Date(date.getTime()+2*oneDay) ;
		}
		if(str.equals("昨天")){
			return new Date(date.getTime()-oneDay) ;
		}
		if(str.equals("前天")){
			return new Date(date.getTime()-2*oneDay) ;
		}

//		2010年10月12日   二〇一〇年七月二十一日
		if(str.matches("("+shuzi2+"){4}年("+shuzi2+"){1,2}月("+shuzi2+"){1,3}(日|号){0,1}")){
			String y = str.substring(0,str.indexOf("年")) ;
			String m = str.substring(str.indexOf("年")+1,str.indexOf("月")) ;
			String d = hanziZhuanHuan(str.substring(str.indexOf("月")+1)) ;
			str = hanziToShuZi(y)+"-"+hanziToShuZi(m)+"-"+hanziToShuZi(d) ;
			return StringToDate(str) ;
		}
//		八月十五 8月15号
		if(str.matches("("+shuzi2+"){1,2}月("+shuzi2+"){1,3}(日|号){0,1}")){
			String m = str.substring(0,str.indexOf("月")) ;
			String d = hanziZhuanHuan(str.substring(str.indexOf("月")+1)) ;
			str = year +"-"+hanziToShuZi(m)+"-"+hanziToShuZi(d) ;
			return StringToDate(str) ;
		}
//		12号 十二号
		if(str.matches("("+shuzi2+"){1,3}(日|号){1}")){
			str = year +"-"+month+"-"+hanziToShuZi(hanziZhuanHuan(str)) ;
			return StringToDate(str) ;
		}
		if(str.matches(dateRegex)){
			return StringToDate(str) ;
		}
		
//		其他汉字日期，去年，上月，上周等
		if(str.matches("("+pre2+"|年|月|日|号|天|"+shuzi2+")+")){
			if(str.contains("年")||str.contains("月")){
//				year
				String y = year+"-" ;
				int yi = str.indexOf("年")+1 ;
				if(yi>1){
					y = str.substring(0,yi) ;
					str = str.substring(yi) ;
					y = hanziToShuZi(hanziZhuanHuan(y)) ;
				}
				
//				month
				String m = month+"-" ;
				int mi = str.indexOf("月")+1 ;
				if(mi>1){
					m = str.substring(0,mi) ;
					str = str.substring(mi) ;
					m = hanziToShuZi(hanziZhuanHuan(m)) ;
				}
				
//				day
				String d = hanziToShuZi(hanziZhuanHuan(str)) ;
				if(d.length()>2){
					d = d.substring(0,2) ;
				}
				if(y.length()==5 && m.length()>1 && d.length()>0){
					return StringToDate(y+m+d) ;
				}
			} else if(str.contains("周") && !str.endsWith("周")){
				int week_cha = 0 ;
				int week = 0 ;
				if(str.contains("下周")){
					week_cha = 1 ;
				} else if(str.contains("上周")){
					week_cha = -1 ;
				}
				int p = str.indexOf("周") ;
				week = Integer.parseInt(hanziToShuZi(str.substring(p+1,p+2))) ;
				return getDateByweek(week_cha,week) ;
			}
		}

//		数字日期
		str = hanziZhuanHuan(str) ;
		if(str.matches(dateRegex)){
			str = str.replaceAll("\\.","-") ;
			return StringToDate(str) ;
		}
		if(str.matches("(\\d|-|\\.)+")){
			str = year +"-"+str.replaceAll("\\.","-") ;
			return StringToDate(str) ;
		}
		return null ;
	}
	
//	汉字转数字
	
//	汉字转数字
	private String hanziToShuZi(String hz){
		if(hz.length()==1){
//			一|二|三|四|五|六|七|八|九|十
			return hanziToshuzi(hz) ;
		} else if(hz.length()==3){
//			二十一
			hz = hz.replaceAll("十","") ;
			return hanziToshuzi(hz) ;
		} else if(hz.length()==2){
//			十四
			if(hz.startsWith("十")){
				hz = hz.replaceAll("十","1") ;
			} else {
//				二十
				hz = hz.replaceAll("十","0") ;
			}
			return hanziToshuzi(hz) ;
		}
		return hanziToshuzi(hz) ;
	}
	private String hanziToshuzi(String hz){
		hz = hz.replaceAll("〇","0").replaceAll("零","0") ;
		hz = hz.replaceAll("一","1").replaceAll("二","2").replaceAll("三","3").replaceAll("四","4").replaceAll("五","5") ;
		hz = hz.replaceAll("六","6").replaceAll("七","7").replaceAll("八","8").replaceAll("九","9").replaceAll("十","10") ;
		
		return hz ;
	}

	private String hanziZhuanHuan(String hz){
		hz = hz
		.replace("今年",String.valueOf(year)+"-")
		.replace("去年",String.valueOf(year-1)+"-")
		.replace("前年",String.valueOf(year-2)+"-")
		.replace("明年",String.valueOf(year+1)+"-")
		.replace("后年",String.valueOf(year+2)+"-")
		.replace("上月",String.valueOf(month-1)+"-")
		.replace("下月",String.valueOf(month+1)+"-")
		.replace("上个月",String.valueOf(month-1)+"-")
		.replace("下个月",String.valueOf(month+1)+"-")
		.replace("今天",String.valueOf(day))
		.replace("明天",String.valueOf(day+1))
		.replace("后天",String.valueOf(day+2))
		.replace("昨天",String.valueOf(day-1))
		.replace("前天",String.valueOf(day-2));
		return hz.replaceAll("年","-").replaceAll("月","-").replaceAll("日","").replaceAll("号","") ;
	}
	
	
//	字符串转日期
	private Date StringToDate(String s){
		if(s.startsWith("-")){
			return null ;
		}
		if(s.split("-").length==2){
			s = year+"-"+s ;
		}
		if(s.split("-").length!=3){
			return null ;
		}
		DateFormat f = DateFormat.getDateInstance();
		try {
			return f.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
//	日期转字符串
	private String dateFormat(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

//	根据周取日期
	private Date getDateByweek(int week_cha,int week_day){
		Calendar c = Calendar.getInstance();
		c.set(year,month-1,day) ;
//		1周日 2周一...7周六
		int week_of_day = c.get(Calendar.DAY_OF_WEEK)-1;
		if(week_of_day==0){
			week_of_day =7 ;
		}
//		计算和当前日期的差
		int day_cha = week_cha*7 + week_day- week_of_day ;
		return new Date(date.getTime()+day_cha*oneDay) ;
	}
}
