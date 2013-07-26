package com.method.jfreechart.barImp;

import java.util.List;

import org.jfree.data.time.RegularTimePeriod;

/** 
 * 数据封装bean
 * by dyong 2010-10-20
 */
public class BarChartData {

	public double data ;	//数据
	
//	barchar data 
	public String bar_row ;
	public String bar_col = "";
	
	public BarChartData(double data,String row,String col){
		this.bar_row = row ;
		this.bar_col = col ;
		this.data = data ;
	}
}
