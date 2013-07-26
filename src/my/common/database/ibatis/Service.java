package my.common.database.ibatis;

import my.common.database.ibatis.config.DaoConfig;

import org.apache.log4j.Logger;


import com.ibatis.dao.client.DaoManager;

/**
 * made by dyong 
 * date 2009-5-8 ����09:43:20
 **/
public class Service {
	protected DaoManager daoManager = DaoConfig.getDaoManager();
	protected Logger log = Logger.getLogger(this.getClass());
}
