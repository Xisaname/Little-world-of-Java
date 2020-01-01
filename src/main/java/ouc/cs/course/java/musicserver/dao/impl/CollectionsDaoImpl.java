package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.CollectionsDao;
import ouc.cs.course.java.musicserver.model.Collections;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class CollectionsDaoImpl implements CollectionsDao{

	@Override
	public int insert(Collections cl) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into collections (uuid, md5value, userid, musicname, sheetname, creatorname, datecreated) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cl.getUuid());
			ps.setString(2, cl.getMd5value());
			ps.setString(3, cl.getUserId());
			ps.setString(4, cl.getMusicname());
			ps.setString(5, cl.getSheetname());
			ps.setString(6, cl.getCreatorname());
			ps.setString(7, cl.getDatecreated());
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
	public void deleteCollection(String md5value, String uuid, String userid) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from collections where md5value=? and uuid=? and userid=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, md5value);
			ps.setString(2, uuid);
			ps.setString(3, userid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
		
	}

	@Override
	public Collections findByMd5value(String md5value) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collections cl = null;
		String sql = "select uuid, md5value, userid, musicname, sheetname, creatorname, datecreated from collections where md5value=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, md5value);
			rs = ps.executeQuery();
			if (rs.next()) {
				cl = new Collections();
				cl.setUuid(rs.getString(1));
				cl.setMd5value(rs.getString(2));
				cl.setUserId(rs.getString(3));
				cl.setMusicname(rs.getString(4));
				cl.setSheetname(rs.getString(5));
				cl.setCreatorname(rs.getString(6));
				cl.setDatecreated(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query by md5value failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return cl;
	}

	
	@Override
	public List<Collections> findByUserid(String userid) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collections cl = null;
		List<Collections> collections = new ArrayList<Collections>();
		String sql = "select uuid, md5value, userid, musicname, sheetname, creatorname, datecreated from collections where userid=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				cl = new Collections();
				cl.setUuid(rs.getString(1));
				cl.setMd5value(rs.getString(2));
				cl.setUserId(rs.getString(3));
				cl.setMusicname(rs.getString(4));
				cl.setSheetname(rs.getString(5));
				cl.setCreatorname(rs.getString(6));
				cl.setDatecreated(rs.getString(7));
				collections.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return collections;

	}
}
