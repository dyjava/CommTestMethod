package my.common.database.ibatis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import my.common.database.ibatis.config.SqlMapConfig;


import com.ibatis.sqlmap.client.SqlMapClient;

/** 
 * by dyong 2010-7-8
 */
public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		String out = "e:/uainfo_out.txt" ;
		SqlMapClient sqlmap = SqlMapConfig.getSqlMapClient() ;
		
		FileReader fr = new FileReader("e:/uainfo.txt");
		String record = "";
		BufferedReader br = new BufferedReader(fr);

		while ((record = br.readLine()) != null) {
			String[] ss = record.split("\t") ;
			UA ua = new UA() ;
			ua.setVendor(ss[0]) ;
			ua.setModel(ss[1]) ;
			List list = sqlmap.queryForList("selectUABYModel",ua);
			
			for(int i=0;i<5 && i<list.size();i++){
				ua = (UA) list.get(i) ;
				String txt = ua.getVendor()+"\t"+ua.getModel()+"\t"+ua.getScreen_size()+"\t"+ua.getScreen_size_char() ;
				StringWriteToFile(out,txt,false) ;
			}
//			if(ss.length==9){
//				HashMap map = new HashMap() ;
//				map.put("pv", ss[1]) ;
//				map.put("rate", ss[2]) ;
//				map.put("recognized", ss[3]) ;
//				map.put("vendor", ss[4]) ;
//				map.put("model", ss[5]) ;
//				
//				map.put("screen_size", ss[6]) ;
//				map.put("screen_size_char", ss[7]) ;
//				map.put("Agent", ss[8]) ;
//				
//				sqlmap.insert("insertUA",map) ;
//			}
		}
		fr.close();
		br.close();
		
//		List l = sqlmap.queryForList("selectAll") ;
//		l.get(0) ;
	}

	public static boolean StringWriteToFile(String filePath,String text,boolean isNewFile){
		boolean b = false ;
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filePath, !isNewFile), false);
			pw.println(text);
			pw.close();
			b = true;
		}catch (Exception e) {
			System.out.println("err:"+e.getMessage());
		}
		return b ;
	}
}
