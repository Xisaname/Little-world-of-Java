package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.RankDao;
import ouc.cs.course.java.musicserver.model.Rank;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class RankDaoImpl implements RankDao {

	@Override
	public int insert(Rank rk) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into rank(name, md5value, times)values(?, ?, ?)";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, rk.getName());
			ps.setString(2, rk.getMd5value());
			ps.setInt(3, rk.getTimes());
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

	@Override
	public int updateByMd5value(String md5value) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "update rank set times = times + 1 where md5value=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, md5value);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by md5value failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
		return autoIncKey;
	}

	@Override
	public List<Rank> findAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Rank rk = null;
		List<Rank> rankList = new ArrayList<Rank>();
		String sql = "select name, md5value, times from rank order by times desc limit 0,7;";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				rk = new Rank();
				rk.setName(rs.getString(1));
				rk.setMd5value(rs.getString(2));
				rk.setTimes(rs.getInt(3));
				rankList.add(rk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return rankList;
	}
	
	
}
