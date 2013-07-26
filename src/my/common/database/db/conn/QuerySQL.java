package my.common.database.db.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import my.common.database.db.Const;

import org.apache.log4j.Logger;


public class QuerySQL {
	private static final Logger log = Logger.getLogger(QuerySQL.class);
	private static Connection conn = null;
	
	public static int getConnCount(){
		return Connections.getConnCount();
	}
	public static boolean executeSQL(String sql){
		Connection Conn = null ;
		try {
			Conn = Connections.getConn();
			Statement stm = Conn.createStatement();
			return stm.execute(sql) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(Conn!=null){
				Connections.relConn(Conn);
			}
		}
		return false;
	}
	
//	��ѯ
	public static ResultSet exeQuery(String sql){
		Connection Conn = null ;
		try {
			Conn = Connections.getConn();
			Statement stm = Conn.createStatement();
			return stm.executeQuery(sql) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(Conn!=null){
				Connections.relConn(Conn);
			}
		}
		return null;
	}
	
//	����
	public static int exeUpdate(String sql){
		Connection Conn = null ;
		try {
			Conn = Connections.getConn();
			Statement stm = conn.createStatement();
			return stm.executeUpdate(sql) ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(Conn!=null){
				Connections.relConn(Conn);
			}
		}
		return 0;
	}
	
	public static Connection getConnection(){
		try {
			return Connections.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Statement getStatement(){
		try {
			if(conn == null){
				conn = Connections.getConn();
			}
			return conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void dispose(Connection conn){
		Connections.relConn(conn);
	}
	
	public static void main(String[] args) throws SQLException {
		Const co = new Const();
		try {
			co.load("D:/source/CommTestMethod/WebRoot/WEB-INF/db-config.xml");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String sql = "select * from data";
		ResultSet rs = exeQuery(sql);
		while(rs.next()){
//			rs.getString("id");
			log.error(rs.getString("showKey"));
		}
	}

}
