package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.CollectionsDao;

import ouc.cs.course.java.musicserver.dao.impl.CollectionsDaoImpl;

import ouc.cs.course.java.musicserver.model.Collections;


public class CollectionsService {

	CollectionsDao collectionsDao = new CollectionsDaoImpl();

	public CollectionsService() {
	}

	public int create(Collections cl) throws SQLException{
		return collectionsDao.insert(cl);
	}

	public void deleteCollection(String md5value, String uuid, String userid) throws SQLException {
		collectionsDao.deleteCollection(md5value, uuid, userid);
	}

	public Collections findByMd5value(String md5value) throws SQLException{
		return collectionsDao.findByMd5value(md5value);
	}

	public List<Collections> findByUserid(String userid) throws SQLException{
		return collectionsDao.findByUserid(userid);
	}
	
}
