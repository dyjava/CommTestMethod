package my.common.database.ibatis;

import com.ibatis.dao.client.DaoManager;

/**
 * made by dyong 
 * date 2009-5-8 ����09:33:59
 **/
public class UserDaoImpl extends SqlMapDao implements UserDao{

	public UserDaoImpl(DaoManager daoManager) {
		super(daoManager);
	}

	public User save(User user) {
//		int r = this.update("updateUser", user);
//		if(r==0){
			this.insert("saveUser", user);
//		}
		return user;
	}

}
