package com.method.jfreechart.XYImp;

import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.TimeSeriesURLGenerator;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.method.jfreechart.ChartDao;
import com.method.jfreechart.Demo;


/**
 * made by dyong 
 * date 2009-8-12 17:56:23
 **/
public class XYChartDemo extends Demo implements ChartDao{
	
	private String X ;	//X轴标题
	private String Y ;	//Y轴标题
	
	private double max_x ;
	private double min_x ;
	private double max_y ;
	private double min_y ;
	
	public XYChartDemo(String title,String x,String y){
		this.title = title ;
		this.X = x ;
		this.Y = y ;
	}

	public JFreeChart createChart(List<?> data) {
		Dataset dataset = getDataSet((List<XYChartData>) data);
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
		return null ;
	}

	public void writeToJpg(JFreeChart chart, String path) {
		try {
			super.writeToJpg(chart,path) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param dataset
	 * @return
	 */
	public JFreeChart createChart(Dataset dataset){
		XYSeriesCollection xyDataset = (XYSeriesCollection)dataset;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.UK); 
		StandardXYToolTipGenerator ttg = new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,sdf,NumberFormat.getInstance()); 

		TimeSeriesURLGenerator urlg = new TimeSeriesURLGenerator(sdf, "pie_chart.jsp", "series", "hitDate"); 
//		x轴坐标
		ValueAxis x = new NumberAxis(X);
		x.setInverted(true) ;		//排序 正序 倒序
		x.setUpperBound(max_x);		//最大值
		x.setLowerBound(min_x) ;	//设置X轴起始数值大小

		//		y轴坐标
		ValueAxis y = new NumberAxis(Y) ;//NumberAxis(Y);
		y.setUpperBound(max_y) ;	//最大值
		y.setLowerBound(min_y) ;	//y轴起始值

		
		StandardXYItemRenderer renderer = new StandardXYItemRenderer(StandardXYItemRenderer.LINES ,ttg, urlg); 
//		renderer.setShapesFilled(true); 
		renderer.setAutoPopulateSeriesFillPaint(true) ;
		
		XYPlot plot = new XYPlot(xyDataset, x, y, renderer);
		plot.setBackgroundPaint(Color.lightGray); //设定图表数据显示部分背景色
		plot.setAxisOffset(new RectangleInsets(5D,5D, 5D,5D)); //设定坐标轴与图表数据显示部分距离
		plot.setDomainGridlinePaint(Color.white); //网格线颜色
		plot.setRangeGridlinePaint(Color.white);
//		
		plot.setDomainGridlinesVisible(true) ;
		plot.setRangeZeroBaselineVisible(true) ;
		plot.setRangePannable(true) ;
		
//		数据点数值显示设置
		XYItemRenderer xyitemrenderer = plot.getRenderer();
		xyitemrenderer.setBaseItemLabelFont(font) ;
		xyitemrenderer.setBaseItemLabelsVisible(true) ;
		xyitemrenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator()) ;
		
		JFreeChart chart = new JFreeChart(title, font, plot, true); 
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(titleFont) ;

		return chart ;
	}
	
	public JFreeChart createChart2(Dataset dataset){
		
		PlotOrientation ori = PlotOrientation.VERTICAL  ;//HORIZONTAL;
		JFreeChart chart = ChartFactory.createXYAreaChart(
				title,X,Y,
				(XYDataset)dataset,
				ori ,true, true, false );
		
		XYPlot plot = chart.getXYPlot() ;
		plot.setBackgroundPaint(Color.lightGray); //设定图表数据显示部分背景色
		plot.setAxisOffset(new RectangleInsets(5D,5D, 5D,5D)); //设定坐标轴与图表数据显示部分距离
		plot.setDomainGridlinePaint(Color.white); //网格线颜色
		plot.setRangeGridlinePaint(Color.white);
		
		plot.setDomainGridlinesVisible(true) ;
		plot.setRangeZeroBaselineVisible(true) ;
		plot.setRangePannable(true) ;
		
//		数据点数值显示设置
		XYItemRenderer xyitemrenderer = plot.getRenderer();
		xyitemrenderer.setBaseItemLabelFont(font) ;
		xyitemrenderer.setBaseItemLabelsVisible(true) ;
		xyitemrenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator()) ;
		
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(titleFont) ;
		return chart ;
	}

	public Dataset getDataSet(List<XYChartData> list) {
		XYSeriesCollection xyDataset = new XYSeriesCollection();
		for(XYChartData data:list){
			XYSeries dataSeries = new XYSeries(data.title) ;
			for(Data d:data.list){
				dataSeries.add(d.x ,d.y );
//				System.out.println(d.x+":"+d.y) ;
				if(d.x>max_x){
					max_x = d.x ;
				}
				if(d.x<min_x || min_x==0){
					min_x = d.x ;
				}
				if(d.y>max_y){
					max_y = d.y ;
				}
				if(d.y<min_y || min_y==0){
					min_y = d.y ;
				}
			}
			xyDataset.addSeries(dataSeries);
		}
		max_x = max_x*1.1 ;
		max_y = max_y*1.1 ;
		return xyDataset ;
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		XYChartDemo demo = new XYChartDemo("测试曲线图","X轴坐标" ,"Y轴坐标") ;
		String path = "D:/line.jpg" ;
		
		List<XYChartData> list = new ArrayList<XYChartData>() ;
		XYChartData data = new XYChartData() ;
		data.title = "c1" ;
		for (int i = 1; i <= 10; i++) {
			Data d = new Data() ;
			d.x=10+(int)(100*Math.random());
			d.y=100+(int)(100*Math.random());
			data.list.add(d) ;
		}
		list.add(data) ;
		
		data = new XYChartData() ;
		data.title = "c2" ;
		for (int i = 1; i <= 10; i++) {
			Data d = new Data() ;
			d.x=10+(int)(100*Math.random());
			d.y=100+(int)(100*Math.random());
			data.list.add(d) ;
		}
		list.add(data) ;
		
		JFreeChart chart = demo.createChart(list);
		demo.writeToJpg(chart, path) ;
	}
	
}
