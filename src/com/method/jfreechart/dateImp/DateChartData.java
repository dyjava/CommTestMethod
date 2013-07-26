package com.method.jfreechart.dateImp;

import java.util.ArrayList;

import org.jfree.data.time.RegularTimePeriod;

/** 
 * by dyong 2010-10-20
 */
public class DateChartData {

	public ArrayList<TimeData> data = new ArrayList<TimeData>();
	public String date_title ;				//图表标题
	public RegularTimePeriod TimeClass ;	//时间类型：Month.class，同样还有Day.class Year.class 等等
	
}
