package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.Collections;

public interface CollectionsDao {

	public int insert(Collections cl) throws SQLException;

	public void deleteCollection(String md5value, String uuid, String userid) throws SQLException;

	public Collections findByMd5value(String md5value) throws SQLException;

	public List<Collections> findByUserid(String userid) throws SQLException;
}
