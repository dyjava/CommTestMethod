package com.method.file.excel;

import java.io.File;

import jxl.*;
import jxl.write.*;

/**
 * made by dyong 
 * date : 2008-9-5 15:40:56
 */
public class ExcelFile {

	/**
	 * 新建excel
	 * @param file
	 */
	public void createExl(String file){
		try {
			//打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(file));
			WritableSheet sheet = book.createSheet("第一页", 0);
			//生成名为"第一页的工作表",参数0表示这是第一页
			Label label = new Label(0, 0, "test");
			//在label对象的构造函数中指定单元格位置是是第一列第一行(0,0)以及单元格内容为test 
			sheet.addCell(label);
			//将定义好的单元格添加到工作表中
			/*生成一个保存数字的单元格 
			 必须使用Number的完整包路径，否则有语法歧义 
			 单元格位置是第二列，第一行，值为789.123*/
			jxl.write.Number number = new jxl.write.Number(1, 0, 123);
			sheet.addCell(number);
			//写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * 读取excel
	 * @param file 文件路径
	 * @param i 第i个工作表。
	 */
	public static void readExl(String file,int i) {
		try {
			Workbook book = Workbook.getWorkbook(new File(file));
			//获的第一个工作表对象
			Sheet sheet = book.getSheet(i);
			//得到第一列第一行的单元格
			Cell cell = sheet.getCell(0, 0);
			String result = cell.getContents();
			System.out.println(result);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void readExl(String file){
		try {
			Workbook book = Workbook.getWorkbook(new File(file));
			//获的第一个工作表对象
			Sheet[] sheets = book.getSheets();
			for(int i=0;i<sheets.length;i++){
				Sheet sheet = sheets[i];
				System.out.println("Sheet"+i);
				int col = sheet.getColumns();
				int row = sheet.getRows();
				for(int r=0;r<row;r++){
					for(int c=0;c<col;c++){
//						得到第c列第r行的单元格
						Cell cell = sheet.getCell(c, r);
						String result = cell.getContents();
						System.out.print("     "+result);
					}
					System.out.println();
				}
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 修改 
	 * @param file
	 */
	public static void UpdateExl(String file) {
		try {
			//Excel获得文件 
			Workbook wb = Workbook.getWorkbook(new File(file));
			//打开一个文件的副本,并且指定数据写回到愿文件中
			WritableWorkbook book = Workbook.createWorkbook(new File(file),wb);
			//添加一个工作表
//			WritableSheet sheet = book.createSheet("第二页", 1);
			WritableSheet sheet = book.getSheet(0);
			
			Label label = new Label(0, 0, "第二页的测试数据22");
			sheet.addCell(label);
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		String f = "e:/t.xls";
		ExcelFile ef = new ExcelFile();
//		ef.createExl(f);
		ef.UpdateExl(f);
		ef.readExl(f);
		
	}

}

/**
 * 
 * package zl.file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

// code run against the jakarta-poi-1.5.0-FINAL-20020506.jar.
public class MyExcel {

static public void main(String[] args) throws Exception {
//－－－－－－－－－－－－在xls中写入数据
FileOutputStream fos = new FileOutputStream("e:\\text.xls");
HSSFWorkbook wb = new HSSFWorkbook();
HSSFSheet s = wb.createSheet();
wb.setSheetName(0, "first sheet");
HSSFRow row = s.createRow(0);
HSSFCell cell = row.createCell((short)0,0);
HSSFRichTextString hts=new HSSFRichTextString("nihao");
cell.setCellValue(hts);
wb.write(fos);
fos.flush();
fos.close();
//－－－－－－－－－－－－从xls读出数据
wb = new HSSFWorkbook(new FileInputStream("e:\\text.xls"));
s = wb.getSheetAt(0);
HSSFRow r = s.getRow(0);
cell=r.getCell((short)0);
if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
   System.out.println(cell.getRichStringCellValue());
}
//－－－－－－－－－－－－从doc读出数据
   FileInputStream in = new FileInputStream("e:\\text.doc");
   WordExtractor extractor = new WordExtractor(in);
   String text =extractor.getText();
      // 对DOC文件进行提取
   System.out.println(text);
   in.close();
   //------------------在doc中写入  

   byte[] a=new String("看见了！").getBytes();
   ByteArrayInputStream bs = new ByteArrayInputStream(a);
   POIFSFileSystem fs = new POIFSFileSystem();
   ///////////////////////////////////
   DirectoryEntry directory = fs.getRoot();
   DocumentEntry de = directory.createDocument("WordDocument", bs);
   //以上两句代码不能省略，否则输出的是乱码
   fos = new FileOutputStream("e:\\text.doc");
   fs.writeFilesystem(fos);
   bs.close();
   fos.flush();
   fos.close();
}
}

 * 
 */
