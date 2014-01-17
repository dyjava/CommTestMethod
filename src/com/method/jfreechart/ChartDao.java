package com.method.jfreechart;

import java.util.List;

import org.jfree.chart.JFreeChart;

/**
 * made by dyong 
 * date 2010-1-6 05:18:44
 **/
public interface ChartDao {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public JFreeChart createChart(List<?> data) ;
	
	/**
	 * 输出到本地图片
	 * @param chart
	 * @param path
	 */
	public void writeToJpg(JFreeChart chart,String path) ;
	
	/**
	 * 生成图片字符流
	 * @param chart
	 * @return
	 */
	public byte[] getJpgBytes(JFreeChart chart,int width,int hight) ;

}
