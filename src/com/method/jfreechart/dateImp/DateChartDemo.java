package com.method.jfreechart.dateImp;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import com.method.jfreechart.ChartDao;
import com.method.jfreechart.Demo;

/**
 * made by dyong 
 * date 2010-1-6 17:06:31
 **/
public class DateChartDemo extends Demo implements ChartDao{
	
	private String X ;
	private String Y ;
	
	public DateChartDemo(String title,String x,String y){
		this.title = title ;
		this.X = x ;
		this.Y = y ;
	}
	public JFreeChart createChart(List<?> data) {
		Dataset dataset = getDataSet((List<DateChartData>) data) ;
		JFreeChart chart = createChart(dataset) ;
		return chart ;
	}

	public byte[] getJpgBytes(JFreeChart chart, int width, int hight) {
		try {
			return super.getJpgBytes(chart, width, hight, 10240) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void writeToJpg(JFreeChart chart, String path) {
		try {
			super.writeToJpg(chart,path) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JFreeChart createChart(Dataset dataset){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				title, X, Y,
				(XYDataset)dataset,
				true, true, false );
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(titleFont) ;
		XYPlot xyplot = (XYPlot)chart.getPlot(); //获得 plot : XYPlot!!
		xyplot.setBackgroundPaint(Color.lightGray);	//设置背景网格颜色
		xyplot.setDomainGridlinePaint(Color.white);	//设置网格竖线颜色 
		xyplot.setRangeGridlinePaint(Color.white);	//设置网格横线颜色 
		xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));	//设置曲线图与xy轴的距离 
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		xyplot.getDomainAxis().setLabelFont(font) ;
		
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
//		xyitemrenderer.setItemLabelsVisible(true) ;
		xyitemrenderer.setItemLabelFont(font) ;
		xyitemrenderer.setBaseSeriesVisible(true ) ;
		if(xyitemrenderer instanceof XYLineAndShapeRenderer){
			XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
			xylineandshaperenderer.setShapesVisible(true); //数据点可见
			xylineandshaperenderer.setShapesFilled(false); //数据点是实心点
//			xylineandshaperenderer.setBaseShapesVisible(true) ;
		}
//		数据点数值显示设置
		xyitemrenderer.setBaseItemLabelsVisible(true) ;
		xyitemrenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xyitemrenderer.setBaseItemLabelFont(font);
		
//		数据轴设置
		ValueAxis va = xyplot.getRangeAxis() ;
		va.setLabelFont(font) ;
//		时间轴设置
		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis(); //对domain 轴上日期显示格式定义
//		dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM"));
		dateaxis.setLabelFont(font);
		dateaxis.setTickLabelFont(font) ;
		
//		图例字体
		chart.getLegend().setItemFont(font) ;
		
		return chart;
	}
	
	private Dataset getDataSet(List<DateChartData> list){
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		TimeZone timezone = TimeZone.getDefault() ;
		for(DateChartData dateChart:list){
			
//			标题和时间类
			Class clazz = dateChart.TimeClass.getClass() ;
			TimeSeries timeseries = new TimeSeries(dateChart.date_title,clazz);
			
			for(TimeData time:dateChart.data){
				RegularTimePeriod date = dateChart.TimeClass.createInstance(clazz,time.dateTime,timezone) ;
				timeseries.add(date ,time.doubleData);
				
//				timeseries.add(data.time, data.data);
			}
			timeseriescollection.addSeries(timeseries);
		}
		timeseriescollection.setDomainIsPointsInTime(false); //domain轴上的刻度点代表的是时间点而不是时间段

		return timeseriescollection;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		DateChartDemo demo = new DateChartDemo("测试用例","时间","数据") ;
		String path = "d:/date.jpg" ;
		
		ArrayList<DateChartData> list = new ArrayList<DateChartData>() ;
		DateChartData dateData = new DateChartData() ;
		dateData.date_title = "测试" ;
		dateData.TimeClass = new Month() ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-01-01"),68)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-02-01"),56.6)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-03-01"),88.6)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-04-01"),85)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-05-01"),94.6)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-06-01"),74.6)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-07-01"),85)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-08-01"),79)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-09-01"),69.6)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-11-01"),92)) ;
		dateData.data.add(new TimeData(DateFormat.getDateInstance().parse("2010-10-01"),97.7)) ;
		list.add(dateData) ;
		
		JFreeChart chart = demo.createChart(list) ;
		demo.writeToJpg(chart, path) ;
		
	}

}
