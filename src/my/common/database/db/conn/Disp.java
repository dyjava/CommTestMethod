package my.common.database.db.conn;

import java.io.IOException;

import my.common.database.db.Const;
import my.common.database.db.xml.ReadXML;


public class Disp {
	private static int type ;		//��ݿ�����
	private static String dbDriver ;	//��ݿ���
	private static String dbServer ;	//��ݿ�
	private static String dbName ;		//��ݿ����
	private static String dbUser ;		//��ݿ��û���
	private static String dbPwd ;		//��ݿ�����
	private static int minConnections ;	//���ӳ���С������
	private static int maxConnections ;	//���ӳ����������
	
	public static void loadConst() {
		Const co = new Const();
		String config = co.getConfigFile();
		try {
			String text = ReadXML.readFileToString(config);
			type = Integer.parseInt(ReadXML.parseTag0(text,"dbType"));
			dbDriver = ReadXML.parseTag0(text,"dbDriver");
			dbServer = ReadXML.parseTag0(text,"dbServer");
			dbName = ReadXML.parseTag0(text,"DatabaseName");
			dbUser = ReadXML.parseTag0(text,"User");
			dbPwd = ReadXML.parseTag0(text,"Password");
			minConnections = Integer.parseInt(ReadXML.parseTag0(text,"Min"));
			maxConnections = Integer.parseInt(ReadXML.parseTag0(text,"Max"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDbDriver() {
		return dbDriver;
	}
	public String getDbPwd() {
		return dbPwd;
	}
	public String getDbServer() {
		return dbServer;
	}
	public String getDbUser() {
		return dbUser;
	}
	public int getMinConnections() {
		return minConnections;
	}

	public String getDbName() {
		return dbName;
	}

	public int getType() {
		return type;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

}
