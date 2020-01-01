package ouc.cs.course.java.musicserver.service;

import java.sql.SQLException;

import ouc.cs.course.java.musicserver.dao.MusicSheetToMusicDao;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetToMusicDaoImpl;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;

public class MusicSheetToMusicService {

	MusicSheetToMusicDao mstmDao = new MusicSheetToMusicDaoImpl();

	public MusicSheetToMusicService() {
	}

	public int create(MusicSheetToMusic mstm) throws SQLException {
		return mstmDao.insert(mstm);
	}
	
	public void deleteByMusicIdAndMusicsheetId(int musicId, int musicsheetId) throws SQLException {
		mstmDao.deleteByMusicIdAndMusicsheetId(musicId, musicsheetId);
	}
	
	public void deleteByMusicsheetId(int musicsheetId) throws SQLException {
		mstmDao.deleteByMusicsheetId(musicsheetId);
	}
}
