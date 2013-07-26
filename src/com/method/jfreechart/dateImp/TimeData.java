package com.method.jfreechart.dateImp;

import java.util.Date;

import org.jfree.data.time.RegularTimePeriod;

/** 
 * by dyong 2010-10-20
 */
public class TimeData {

//	public RegularTimePeriod time ;	//时间，类型：Month.class，同样还有Day.class Year.class 等等
	public Date dateTime ;				//时间
	public double doubleData ;			//数据
	
	public TimeData(){}
	public TimeData(Date time,double data){
		this.doubleData = data ;
		this.dateTime = time ;
	}
}
