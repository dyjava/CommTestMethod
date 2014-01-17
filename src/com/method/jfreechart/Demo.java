package com.method.jfreechart;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * made by dyong 
 * date 2010-1-6 下午05:11:42
 **/
public class Demo {

	public static Font titleFont=new Font("黑体",Font.TRUETYPE_FONT, 20);
	public static Font font=new Font("宋体",Font.TRUETYPE_FONT, 12); 
	
	public String title ;	//图表标题
	
	/**
	 * 写图表对象到文件
	 * @param path
	 * @param chart
	 * @throws IOException
	 */
	protected void writeToJpg(JFreeChart chart,String path) throws IOException{
		FileOutputStream fos_jpg = null;
		try {
			fos_jpg = new FileOutputStream(path);
			ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,1000,800,null);
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {}
		}
	}
	protected byte[] getJpgBytes(JFreeChart chart,int w,int h,int buf) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream(buf);
		ChartUtilities.writeChartAsJPEG(os,1,chart,w,h,null);
		return os.toByteArray() ;
	}
}
