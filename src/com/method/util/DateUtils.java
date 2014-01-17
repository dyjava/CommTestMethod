package com.method.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	private static final Logger log = Logger.getLogger(DateUtils.class);
	
	/**
	 * 取当前时间
	 * @return
	 */
	public static String nowDate(){
		return (new Date()).toLocaleString();
	}
	
	public static String dateToString(Date d){
		return d.toLocaleString();
	}
	
	public static String longToDate(long l){
		return new Date(l).toLocaleString();
	}

	public static Date stringToDate(String s){
		DateFormat f = DateFormat.getDateInstance();
		try {
			return f.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	public static Date stringToTime(String s){
		DateFormat f = DateFormat.getTimeInstance();
		try {
			return f.parse(s) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	public static Date stringToDateTime(String s){
		DateFormat f = DateFormat.getDateTimeInstance();
		try {
			return f.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 日期转 星期
	 * @return
	 */
	public static int getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		Date d = new Date() ;
		
//		设置时间 年，月，日
		c.set(d.getYear()+1900,d.getMonth(),d.getDate()) ;
		System.out.println(c.getTime().toLocaleString()) ;
		
//		week 周日为1
		int week_of_day = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(week_of_day) ;
		
		System.out.println("=="+c.get(Calendar.DAY_OF_YEAR)) ;
		return week_of_day ;
	}
	
	/**
	  * 计算某年某月第几个星期的第几天
	  * @param year          年份
	  * @param month         月份
	  * @param weekOfMonth   这个月的第几周
	  * @param dayOfWeek     星期几
	  * @return
	*/
	public static String weekdatetodata(int year,int month,int weekOfMonth,int dayOfWeek){
		Calendar c = Calendar.getInstance();
//		计算出 x年 y月 1号 是星期几
		c.set(year, month-1, 1);
		
//		如果i_week_day =1 的话 实际上是周日  
		int i_week_day = c.get(Calendar.DAY_OF_WEEK);
		
		int sumDay = 0;
//		dayOfWeek+1 就是星期几（星期日 为 1）
		if(i_week_day == 1){
			sumDay = (weekOfMonth-1)*7 + dayOfWeek+1;
		}else{
			sumDay = 7-i_week_day+1 +  (weekOfMonth-1)*7 + dayOfWeek +1;
		}
//		在1号的基础上加上相应的天数
		c.set(Calendar.DATE,  sumDay);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		return sf2.format(c.getTime());
	}
	 
	/**
	 * 
	 * @param week_cha 周差 0是本周，正数加，负数减
	 * @param week_day 周几？周日为7
	 * @return
	 */
	public static String getDateByweek(int week_cha,int week_day){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()) ;
//		1周日 2周一...7周六
		int week_of_day = c.get(Calendar.DAY_OF_WEEK)-1;
		if(week_of_day==0){
			week_of_day =7 ;
		}
//		计算和当前日期的差
		int day_cha = week_cha*7 + week_day- week_of_day ;
		log.error("==="+day_cha) ;
		c.setTime(new Date(new Date().getTime()+day_cha*24*60*60*1000));
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		return sf2.format(c.getTime());
	}
	
	public static Date getDate(int year,int month,int day){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()) ;
		if(year>0){
			c.set(Calendar.YEAR,year) ;
		}
		if(month>-100){
			c.set(Calendar.MONTH,month-1) ;
		}
		if(day>-100){
			c.set(Calendar.DATE,day) ;
		}
		
		return c.getTime() ;
	}
	
	/**
	 * 文件更新时间
	 * @param path
	 * @return
	 */
	public static String getUpdateTime(String path){
		String filepath = DateUtils.class.getResource(path).getFile();
		File file = new File(filepath);
		Date date = new Date();
		if(file.exists()){
			date.setTime(file.lastModified());
			return date.toLocaleString();
		}else{
			return "";
		}
	}
	
	/**
	 * 时间格式转换
	 * @param date
	 * @return
	 */
	public static String dateFormat12(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(date);
	}
	
	/**
	 *  时间格式转换
	 * @param date
	 * @return
	 */
	public static String dateFormat24(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
	/**
	 * 时间格式转换
	 * @param date
	 * @param type 转换后的显示格式
	 * y 年
	 * M 月
	 * d 月中第几天
	 * H 小时(24制)
	 * h 小时(12制)
	 * m 分钟
	 * s 钞钟
	 * S 微钞
	 * E 星期几，如 Tuesday
	 * D 一年中的第几天
	 * w week in year 
	 * W week in month
	 * a am/pm marker
	 * k hour in day (1~24)
	 * K hour in am/pm (0~11)
	 * z time zone (Text)
	 * 如：yy-MM-dd-HH-mm-ss
	 * @return
	 */
	public static String dateFormat(Date date,String type){
		DateFormat formatter = new SimpleDateFormat(type);
		return formatter.format(date);
	}
	
	public static void main(String[] args) {
//		getDayOfWeek();
//		log.error(getDateByweek(1,2)) ;
		log.error(getDate(0,-11,1).toLocaleString()) ;
//		log.error("now:"+nowDate());
//		log.error("Update date:"+getUpdateTime("/01.txt"));
//		log.error("now:"+dateToString(new Date()));
//		log.error("now:"+longToDate(new Date().getTime()));
		
//		log.error("now:"+stringToDate("2008-06-06 02:16:51").toLocaleString());
//		log.error("now:"+stringToDateTime("2008-06-06 02:16:51").toLocaleString());
//		log.error("now:"+stringToTime("02:16:51"));
//		
////		log.error("now:"+dateFormat12(new Date()));
//		log.error("now:"+dateFormat24(new Date()));
//		log.error("now:"+dateFormat(new Date(),
//		"今天是yyyy-MM-dd 时间：HH:mm:ss E 今年的第D天，现在是aK点，全天的k点 时区：z"));
		
//		Date d = stringToDate("2008-7-1");
//		Date d2 = new Date();
//		log.error(d.toLocaleString()+"  "+d2.toLocaleString());
//		log.error((d.compareTo(d2))+"");
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//		log.error(""+Float.parseFloat(formatter.format(new Date()))/10000);
		
		
		String[] uptime = "03:00:00-08:00:00".split("-");
		String[] begin = uptime[0].split(":") ;
		String[] end = uptime[1].split(":") ;
		int hour = new Date().getHours() ;
		int beginH = Integer.parseInt(begin[0]) ;
		int endH = Integer.parseInt(end[0]) ;
		System.out.println(hour+" "+beginH+" "+endH) ;
		
	}

}
