package com.method.staticPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * made by dyong date 2009-6-24 14:22:56
 */
public class Static {
	private static String DefaultEncodIng = "utf-8" ;
	
	public static void main(String[] ss) {
		Map<String, Object> map = new HashMap<String, Object>() ;
		map.put("id", "00000") ;
		map.put("name", "ssss") ;
		String root = "E:/java/CommTestMethod/src/" ;
		String templatePath = "/com/method/staticPage/testFtl.txt" ;
		String htmlPath = "/com/method/staticPage/testFtl.txt.html" ;
		try {
			Static.crateHTML(root, map, templatePath, htmlPath) ;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成静态页面主方法
	 * @param context ServletContext
	 * @param data 一个Map的数据结果集
	 * @param templatePath ftl模版路径
	 * @param targetHtmlPath 生成静态页面的路径
	 */
	public static void crateHTML(ServletContext context,
			Map<String, Object> data, String templatePath, String targetHtmlPath) {
		Configuration freemarkerCfg = new Configuration();
		// 加载模版
		freemarkerCfg.setServletContextForTemplateLoading(context, "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), DefaultEncodIng);
		try {
			// 指定模版路径
			Template template = freemarkerCfg
					.getTemplate(templatePath, DefaultEncodIng);
			template.setEncoding(DefaultEncodIng);
			// 静态页面路径
			String htmlPath = context.getRealPath("/") + targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), DefaultEncodIng));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成静态页面主方法
	 * @param root 跟目录
	 * @param data 数据
	 * @param templatePath 模板相对目录
	 * @param htmlPath 生成的html文件保存的目录
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void crateHTML(String root,Map<String,Object> data,String templatePath,String htmlPath)
	throws IOException,TemplateException {
		Configuration cfg = new Configuration();
		// 加载模版
		// freemarkerCfg.setServletContextForTemplateLoading(context, "/");
		cfg.setDirectoryForTemplateLoading(new File(root));
		cfg.setEncoding(Locale.getDefault(), DefaultEncodIng);

		// 指定模版路径
		Template template = cfg.getTemplate(templatePath);
		template.setEncoding(DefaultEncodIng);
		// 静态页面路径
		File htmlFile = new File(root+"/"+htmlPath);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlFile), DefaultEncodIng));
		// 处理模版
		template.process(data, out);
		out.flush();
		out.close();
	}
}
