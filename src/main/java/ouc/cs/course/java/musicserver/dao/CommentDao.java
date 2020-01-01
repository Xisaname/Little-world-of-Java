package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.Comment;

public interface CommentDao {
	public int insert(Comment comment)throws SQLException;
	public List<Comment> findAll(String uuid)throws SQLException;
		
}
