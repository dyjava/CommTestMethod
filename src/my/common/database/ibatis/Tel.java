package my.common.database.ibatis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import my.common.database.ibatis.config.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

/** 
 * 根据手机号查询归属地
 * 手机号归属地入库
 * by dyong 2010-11-11
 */
public class Tel {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		test() ;
	}
	public static void test() throws SQLException{
		Tel tel = new Tel() ;
		String path = "E:/data/telout2/part-00000" ;
		ArrayList<String> data = tel.getData(path) ;
		for(String s:data){
			String[] a = s.split("\t") ;
			String[] cs = a[0].trim().split("-") ;
			String[] ts = a[1].trim().split(",") ;
			for(String t:ts){
				t = t.replace("[","").replace("]","").trim() ;
				tel.insert(cs[0], cs[1], t) ;
			}
			System.out.println(a[0]) ;
		}
		
	}
	private ArrayList<String> getData(String path){
		ArrayList<String> list = new ArrayList<String>() ;
		try {
			FileReader fr = new FileReader(path);
			String record = "";
			BufferedReader br = new BufferedReader(fr);

			while ((record = br.readLine()) != null) {
				list.add(record.trim());
			}
			fr.close();
			br.close();
		}catch(Exception e){
			e.printStackTrace() ;
		}
		return list ;
	}
	private void insert(String prov,String city,String tel) throws SQLException{
		SqlMapClient sqlmap = SqlMapConfig.getSqlMapClient() ;
		HashMap<String,String> map = new HashMap<String,String>() ;
		map.put("prov", prov) ;
		map.put("city", city) ;
		map.put("tel", tel) ;
		sqlmap.insert("insertTel",map) ;
	}

}
