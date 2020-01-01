package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.CommentDao;
import ouc.cs.course.java.musicserver.dao.impl.CommentDaoImpl;
import ouc.cs.course.java.musicserver.model.Comment;

public class CommentService {

	private CommentDao commentDao=new CommentDaoImpl();
	public int insert(Comment com) throws SQLException {
		return commentDao.insert(com);
	}
	public CommentService() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Comment> findAll(String uuid) throws SQLException{
		return commentDao.findAll(uuid);
	}
}
