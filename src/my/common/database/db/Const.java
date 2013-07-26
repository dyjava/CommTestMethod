package my.common.database.db;

import java.sql.SQLException;

import my.common.database.db.conn.Connections;
import my.common.database.db.conn.Disp;

import org.apache.log4j.Logger;


public class Const {
	private static final Logger log = Logger.getLogger(Const.class);
	private static String configFile ;	//�����ļ�·��
	
	
	public void load(String file) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
		this.setConfigFile(file);
		log.debug(file);
//		Disp.loadConst();
		Connections conns = new Connections();
		conns.InitConn();
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String config) {
		configFile = config;
	}
	
}
