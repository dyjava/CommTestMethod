package com.method.jfreechart.barImp;

import java.awt.Color;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import com.method.jfreechart.ChartDao;
import com.method.jfreechart.Demo;

/**
 * made by dyong 
 * date 2009-8-12 下午01:37:33
 * 柱状图
 **/
public class BarChartDemo extends Demo implements ChartDao{

	private String x ;	//x轴标题
	private String y ;	//y轴标题
	
	public BarChartDemo(String title,String x,String y){
		this.title = title ;
		this.x = x ;
		this.y = y ;
	}

	@SuppressWarnings("unchecked")
	public JFreeChart createChart(List<?> data) {
		Dataset dataset = getDataSet((List<BarChartData>) data) ;
		JFreeChart chart = createChart(dataset) ;
		return chart;
	}
	
	public byte[] getJpgBytes(JFreeChart chart,int width,int hight) {
		try {
			return super.getJpgBytes(chart, width, hight,10240) ;
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
			e.printStackTrace();
		}
	}
	
	/**
	 * 画图
	 * @param dataset
	 * @return
	 */
	private JFreeChart createChart(Dataset dataset){
		JFreeChart chart = ChartFactory.createBarChart3D(
							title, // 图表标题
							x, // 目录轴的显示标签
							y, // 数值轴的显示标签
							(DefaultCategoryDataset)dataset, // 数据集
							PlotOrientation.VERTICAL, //HORIZONTAL,// 图表方向：水平、垂直
							true, 	// 是否显示图例(对于简单的柱状图必须是false)
							false, 	// 是否生成工具
							false 	// 是否生成URL链接
							);
		
//		标题字体
		TextTitle title = chart.getTitle() ;
		title.setFont(titleFont) ;
		
//		设置图表区
        CategoryPlot plot = chart.getCategoryPlot();// 获得图表区域对象 
        // 设置图表的纵轴和横轴 
        CategoryAxis domainAxis = plot.getDomainAxis(); 
        domainAxis.setLowerMargin(0.1);// 设置距离图片左端距离此时为10% 
        domainAxis.setUpperMargin(0.1);// 设置距离图片右端距离此时为百分之10 
        domainAxis.setCategoryLabelPositionOffset(20);// 图表横轴与标签的距离(10像素) 
        domainAxis.setCategoryMargin(0.2);// 横轴标签之间的距离20% 
        domainAxis.setLabelFont(font);// 应报与实报对照 标签 
        domainAxis.setTickLabelFont(font); 

        // 设定柱子的属性 
        ValueAxis rangeAxis = plot.getRangeAxis(); 
        rangeAxis.setLabelFont(font); // 人数标签字体 
        rangeAxis.setUpperMargin(0.1);// 设置最高的一个柱与图片顶端的距离(最高柱的10%) 

        // 设置图表的颜色 
        BarRenderer3D renderer = new BarRenderer3D(); 
        renderer.setBaseOutlinePaint(Color.red); 
        renderer.setSeriesPaint(0, new Color(0,255, 255));// 计划柱子的颜色为青色 
        renderer.setSeriesOutlinePaint(0, Color.BLACK);// 边框为黑色 
        renderer.setSeriesPaint(1, new Color(0, 255, 0));// 实报柱子的颜色为绿色 
        renderer.setSeriesOutlinePaint(1, Color.red);// 边框为红色 
        renderer.setItemMargin(0.1);// 组内柱子间隔为组宽的10% 
        renderer.setBaseLegendTextFont(font);// 计划实报 
        // 显示每个柱的数值，并修改该数值的字体属性 
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setItemLabelFont(font);
        renderer.setItemLabelPaint(Color.black);
        renderer.setItemLabelsVisible(true); 
        plot.setRenderer(renderer);
        
        // 设置纵横坐标的显示位置 
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);// 学校显示在下端(柱子竖直)或左侧(柱子水平) 
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); // 人数显示在下端(柱子水平)或左侧(柱子竖直) 
//		
        return chart ;
	}
	
	/**
	 * 数据封装
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private Dataset getDataSet(List<BarChartData> list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(BarChartData d:list){
			dataset.addValue(d.data,d.bar_row,d.bar_col) ;
		}
		return dataset ;
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BarChartDemo demo= new BarChartDemo("水果产量图","水果","产量(KG)") ;
		
		List<BarChartData> list = new ArrayList<BarChartData>() ;
		BarChartData data = new BarChartData(100, "北京", "苹果") ;
		list.add(data) ;
		data = new BarChartData(100, "上海", "苹果") ;
		list.add(data) ;
		data = new BarChartData(100, "广州", "苹果") ;
		list.add(data) ;
		data = new BarChartData(200, "北京", "梨子") ;
		list.add(data) ;
		data = new BarChartData(200, "上海", "梨子") ;
		list.add(data) ;
		data = new BarChartData(200, "广州", "梨子") ;
		list.add(data) ;
		data = new BarChartData(300, "北京", "葡萄") ;
		list.add(data) ;
		
		Dataset dataset = demo.getDataSet(list);
		JFreeChart chart = demo.createChart(dataset) ;
		
		demo.writeToJpg(chart, "D:/fruit2.jpg") ;
	}
	
}
