package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;

public interface MusicSheetToMusicDao {

	public int insert(MusicSheetToMusic mstm) throws SQLException;

	public void delete(int id) throws SQLException;

	public List<Integer> findByMusicSheetId(int id) throws SQLException;

	void deleteByMusicIdAndMusicsheetId(int id, int musicsheetId) throws SQLException;

	void deleteByMusicsheetId(int musicsheetId) throws SQLException;
}
