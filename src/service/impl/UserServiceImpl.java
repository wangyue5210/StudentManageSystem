package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao=new UserDaoImpl();

	@Override
	public boolean login(String username, String password) {
		int count=userDao.login(username,password);
		boolean flag=true;
		
		if (count!=1) {
			flag=false;
		}
		return flag;
	}
}
