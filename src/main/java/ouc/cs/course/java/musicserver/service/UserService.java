package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.UserDao;
import ouc.cs.course.java.musicserver.dao.impl.UserDaoImpl;
import ouc.cs.course.java.musicserver.model.User;

public class UserService {
	
	UserDao userDao = new UserDaoImpl();

	public User findOne(String username, String passMd5Value) throws SQLException {
		return userDao.findOne(username, passMd5Value);
	}
	public int insert(User user) throws SQLException{
		return userDao.insert(user);
	}
	public int update(User user)throws SQLException{
		return userDao.update(user);
	}
	public int deleteById(int id)throws SQLException{
		return userDao.deleteById(id);
	}
	public User findById(int id) throws SQLException{
		return userDao.findById(id);
	}
	public User findByName(String name) throws SQLException{
		return userDao.findByName(name);
	}
	public List<User> findAll() throws SQLException{
		return userDao.findAll();
	}
}
