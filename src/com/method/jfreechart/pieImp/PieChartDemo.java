package com.method.jfreechart.pieImp;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.method.jfreechart.ChartDao;
import com.method.jfreechart.Demo;

/**
 * made by dyong 
 * date 2009-8-12 14:23:44
 * 饼形图
 **/
public class PieChartDemo extends Demo implements ChartDao{

	private String dw ;	//计量单位
	public PieChartDemo(String title,String dw){
		this.title = title ;
		this.dw = dw ;
	}
	public JFreeChart createChart(List<?> data) {
		
		Dataset dataset = getDataSet((List<PieChartData>) data) ;
		JFreeChart chart = createChart(dataset) ;
		return chart ;
	}

	public byte[] getJpgBytes(JFreeChart chart, int width, int hight) {
		try {
			return super.getJpgBytes(chart, width, hight, 10240) ;
		} catch (IOException e) {
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
	
	private JFreeChart createChart(Dataset data){
		JFreeChart chart = ChartFactory.createPieChart3D(title,  // 图表标题
		(DefaultPieDataset)data, 
		true, // 是否显示图例
		false,
		true
		);
		
//		设置标题字体
		TextTitle title = chart.getTitle() ;
		title.setFont(titleFont) ;
		
//		设置底部说明
		LegendTitle legendTitle = chart.getLegend() ;
		legendTitle.setItemFont(font) ;
		
//		设置设置图表区
		PiePlot3D plot = (PiePlot3D)chart.getPlot();
//		底部说明区显示百分比
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({1}"+dw+", {2})"));
//      字体设置
		plot.setLabelFont(font);
//		图表区显示百分比
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:({1}"+dw+" {2})",
        		NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        return chart ;
	}
	
	public JFreeChart create2(Dataset data) throws IOException{
//		PieDataset data = getDataSet();
		
		PiePlot3D plot =  new PiePlot3D((PieDataset)data);
//		底部说明区显示百分比
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({1}KG, {2})"));
//		图表区显示百分比
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:({2})",
        		NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        
        JFreeChart chart = new JFreeChart(title,font, plot, true); 
        chart.getTitle().setFont(titleFont) ;
        chart.setBorderVisible(true) ;
//		设置底部说明
//		LegendTitle legendTitle = chart.getLegend() ;
//		legendTitle.setItemFont(font2) ;
		
		return chart ;
	}
	
	private DefaultPieDataset getDataSet(List<PieChartData> list) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(PieChartData data:list){
			dataset.setValue(data.title,data.data);
		}
		
		return dataset;
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PieChartDemo demo = new PieChartDemo("水果产量图","千克") ;
		String path = "D:/fruit3.jpg" ;
		
		List<PieChartData> data = new ArrayList<PieChartData>() ;
		data.add(new PieChartData("苹果",100)) ;
		data.add(new PieChartData("荔枝",500)) ;
		data.add(new PieChartData("香蕉",400)) ;
		data.add(new PieChartData("葡萄",300)) ;
		data.add(new PieChartData("梨子",200)) ;
		JFreeChart chart = demo.createChart(data) ;
		demo.writeToJpg(chart, path) ;
		
	}

}
