package my.common.database.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import my.common.database.db.conn.QuerySQL;

import org.apache.log4j.Logger;


public class TestMethod {
	private static final Logger log = Logger.getLogger(TestMethod.class);
	
	public static void test() throws SQLException{
		String sql = "select * from data";
		ResultSet rs = QuerySQL.exeQuery(sql);
		while(rs.next()){
			log.error(rs.getString("showKey"));
		}
	}

	public static void main(String[] args) throws SQLException {
		Const co = new Const();
		try {
			co.load("e:/source/CommTestMethod/WebRoot/WEB-INF/db-config.xml");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		test();
	}

}
