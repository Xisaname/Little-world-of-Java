package ouc.cs.course.java.musicserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.CommentDao;
import ouc.cs.course.java.musicserver.model.Comment;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

public class CommentDaoImpl implements CommentDao {

	public int insert(Comment com) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;
		String sql = "insert into comment(userid, uuid, date,username,content)values(?, ?, ?, ?, ?)";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, com.getUserid());
			ps.setString(2, com.getUuid());
			ps.setString(3, com.getDate());
			ps.setString(4, com.getUsername());
			ps.setString(5, com.getContent());

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
	public List<Comment> findAll(String uuid) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Comment ms = null;
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select id,userid, uuid, date, username, content from comment where uuid=? order by id desc";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uuid);
			rs = ps.executeQuery();
			while (rs.next()) {
				ms = new Comment();
				ms.setId(rs.getInt(1));
				ms.setUserid(rs.getInt(2));
				ms.setUuid(rs.getString(3));
				ms.setDate(rs.getString(4));
				ms.setUsername(rs.getString(5));
				ms.setContent(rs.getString(6));
				commentList.add(ms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("query all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return commentList;
	}

}
