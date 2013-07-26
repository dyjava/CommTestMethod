package my.common.database.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

public class DaoConfig {

	private static final DaoManager daoManager ;
	static {
		try {
			String resource = "cn/db/ibatis/config/dao.xml";
			Reader reader = Resources.getResourceAsReader(resource);
//			Reader reader = new BufferedReader(new InputStreamReader(DaoConfig.class.getResourceAsStream(resource),"utf-8")) ;

			daoManager = DaoManagerBuilder.buildDaoManager(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("can't initialize DaoConfig.class Cause:", e);
		}
	}

	public static DaoManager getDaoManager() {
		return daoManager;
	}
}
