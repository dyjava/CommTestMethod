package my.common.database.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {

	private static final SqlMapClient sqlmapclient ;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("my/common/database/ibatis/config/sql-map-config.xml");
			sqlmapclient= SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("can't initialize SqlMapConfig.class Cause:", e);
		}
	}

	public static SqlMapClient getSqlMapClient() {
		return sqlmapclient;
	}
}
