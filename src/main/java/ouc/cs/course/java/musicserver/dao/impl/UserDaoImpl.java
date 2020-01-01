package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.UserDao;
import ouc.cs.course.java.musicserver.model.User;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class UserDaoImpl implements UserDao {

	
	public static void main(String[] args) {
		
		UserDao ud = new UserDaoImpl();
		try {
			User user=ud.findOne("9","c4ca4238a0b923820dcc509a6f75849b");
			System.out.println(user.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into user (username, password, md5) values (?, ?, ?)";
		
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPassMd5value());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
		return autoIncKey;
	}


	public int deleteById(int id) {
		int result=0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="delete from user where id=?";
		conn = DatabaseUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(User user) {
		int result=0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="update user set name=?,password=?,md5=? where id=?";
		conn = DatabaseUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPassMd5value());
			ps.setInt(4, user.getId());
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public List<User> findAll() throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		List<User> userList = new ArrayList<User>();
		String sql = "select name from user";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString(1));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return userList;

	}


	@Override
	public User findById(int id) throws SQLException {
		User user=new User();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select * from user where id=?";
		ResultSet rs = null;
		conn = DatabaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			user.setId(Integer.valueOf(rs.getString(1)));
			user.setName(rs.getString(1));
			user.setPassword(rs.getString(2));
			user.setPassMd5value(rs.getString(3));
		}
		return user;
	}


	@Override
	public User findByName(String name) throws SQLException {
		User user=new User();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select * from user where username=?";
		ResultSet rs = null;
		conn = DatabaseUtil.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			user.setId(Integer.valueOf(rs.getString(1)));
			user.setName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setPassMd5value(rs.getString(4));
		}
		return user;
	}


	@Override
	public User findOne(String name, String passMd5Value) throws SQLException {
		String sql="select * from user where username=? and md5=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=new User();
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, passMd5Value);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setId(Integer.valueOf(rs.getString(1)));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPassMd5value(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return user;
	}
}
