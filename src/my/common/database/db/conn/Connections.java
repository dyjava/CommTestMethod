package my.common.database.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import my.common.database.db.Const;

import org.apache.log4j.Logger;


public class Connections {
	private static final Logger log = Logger.getLogger(Connections.class);
	private static Stack stack;
	private static int count = 0;
	private static Disp disp;
	
	protected static int getConnCount(){
		return stack.size();
	}
	
	public static Connection getConn()
	throws Exception{
		while(true){
//			Connections cons = new Connections();synchronized
			if(stack.isEmpty() && count<disp.getMaxConnections()){
//			����С�����������,�½�һ������.
				Connection conn = Connections.createConn();
//				stack.push(conn);
				count++;
				return conn;
			}
			if(!stack.isEmpty()){
				return (Connection) stack.pop();
			}else{
//				�ȴ����������ͷ�.
				Thread.sleep(5*1000);
				continue ;
			}
		}
	}
	
	public static void relConn(Connection conn){
		if(conn!=null){
			stack.push(conn);
		}
	}
	
	/**
	 * ��ʼ�����ӳ�
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void InitConn() throws ClassNotFoundException,
		SQLException, InstantiationException, IllegalAccessException{
		stack = new Stack();
		disp = new Disp();
		Disp.loadConst();
//		log.error("===MinConnections==="+disp.getMinConnections());
		for(int i=0;i<disp.getMinConnections();i++){
			Connection conn = createConn();
			stack.push(conn);
			count = i;
//			log.error("count:"+count);
		}
		
	}
	
	/**
	 * ��ݿ�����
	 * @param disp
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static Connection createConn()
	throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		Connection conn = null ;
		int type = disp.getType();
		String dbDriver = disp.getDbDriver();	//��ݿ���
		String usr = disp.getDbUser();			//��ݿ��û���
		String pwd = disp.getDbPwd();			//��ݿ�����
		String server = disp.getDbServer();		//��ݿ�����ַ
		String dbName = disp.getDbName();		//��ݿ���
		String url = "";						//��ݿ����ӵ�ַ
//		log.error("dbDriver:"+dbDriver);
		
//		log.error("user:"+usr);
//		log.error("pwd:"+pwd);
//		log.error("server:"+server);
//		log.error("dbName:"+dbName);
		
		if(type==1){
//			SQLServer
			Class.forName(dbDriver).newInstance();
			url = server+";DatabaseName="+dbName;
//			log.error("url:"+url);
			conn = DriverManager.getConnection(url,usr,pwd); 
//			log.error("=========SQLServer Connection is OK============");
		} else if(type==2){
		//mysql
			Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
			url ="jdbc:mysql://localhost:3306/myhome?user=root&password=123" ;
			conn= DriverManager.getConnection(url); 
		}
//		//Oracl
//		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
//		url="jdbc:oracle:thin:@localhost:1521:orcl"; 
//		//orclΪ�����ݿ��SID 
//		conn= DriverManager.getConnection(url,usr,pwd); 

//		DB2
//		Class.forName("com.ibm.db2.JDBC.app.DB2Driver ").newInstance(); 
//		String url="jdbc:db2://localhost:5000/sample"; 
//		 //sampleΪ�����ݿ��� 
//		conn= DriverManager.getConnection(url,usr,pwd); 

//		//sybase
//		Class.forName("com.sybase.JDBC.SybDriver").newInstance(); 
//		 String url =" jdbc:sybase:Tds:localhost:5007/tsdata"; 
//		 //tsdataΪ�����ݿ��� 
//		 Properties sysProps = System.getProperties(); 
//		 SysProps.put("user","userid"); 
//		 SysProps.put("password","user_password"); 
//		 Connection conn= DriverManager.getConnection(url, SysProps); 

//		//mysql
//		Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
//		 String url ="JDBC:mysql://localhost/softforum?user=soft&password=soft1234&useUnicode=true&characterEncoding=8859_1" 
//		 //testDBΪ�����ݿ��� 
//		 conn= DriverManager.getConnection(url); 
		 
		 return conn ;
	}
	
	public static void main(String[] args){
		Const co = new Const();
		try {
			co.load("D:/source/CommTestMethod/WebRoot/WEB-INF/db-config.xml");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
//		Connections c = new Connections();
//		try {
//			c.InitConn();
			log.error("====size:"+stack.size());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
