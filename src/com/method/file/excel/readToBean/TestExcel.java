package com.method.file.excel.readToBean;

import java.util.List;

/**
 * made by dyong 
 * date 2008-11-26 15:52:54
 **/
public class TestExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "e:/t.xls" ;
		ReadExcToBean rb = new ReadExcToBean() ;
//		List bean = rb.readExcel(path,new ExcelBean(),0,0,0);
		List bean = rb.readExcel(path,new ExcelBean());
		System.out.println("================================");
		for(int i=1;i<bean.size();i++){
			ExcelBean exc = (ExcelBean) bean.get(i) ;
			System.out.println(exc.getName()+"	"+exc.getSex()+"	"+exc.getAge()+"	"+exc.getDate()) ;
		}
		
		
	}

}
