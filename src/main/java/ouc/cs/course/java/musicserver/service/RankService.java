package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.dao.RankDao;
import ouc.cs.course.java.musicserver.dao.impl.RankDaoImpl;
import ouc.cs.course.java.musicserver.model.Rank;

public class RankService {

	RankDao rankdao = new RankDaoImpl();
	
	public int insert(Rank rk) throws SQLException{
		return rankdao.insert(rk);
	}
	
	public int updateByMd5value(String md5value) throws SQLException{
		return rankdao.updateByMd5value(md5value);
	}
	
	public List<Rank> findAll() throws SQLException {
		return rankdao.findAll();
	}

}
