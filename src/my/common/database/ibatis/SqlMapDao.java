package my.common.database.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

/**
 * made by dyong 
 * date 2009-5-8 ����09:35:03
 **/
public class SqlMapDao extends SqlMapDaoTemplate{

	public SqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}
}
