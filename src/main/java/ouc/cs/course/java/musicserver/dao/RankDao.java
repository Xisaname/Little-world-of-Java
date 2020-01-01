package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.Rank;

public interface RankDao {

	public int insert(Rank rk) throws SQLException;
		
	public int updateByMd5value(String md5value) throws SQLException;
	
	public List<Rank> findAll() throws SQLException;
}
