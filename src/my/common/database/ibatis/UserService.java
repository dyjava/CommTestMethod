package my.common.database.ibatis;

/**
 * made by dyong 
 * date 2009-5-8 ����09:50:45
 **/
public class UserService extends Service{

	private UserDao userDao;

	public UserService() {
		userDao = (UserDao) this.daoManager.getDao(UserDao.class);
	}
	
	public User save(User user) {
		return userDao.save(user);
	}
}
