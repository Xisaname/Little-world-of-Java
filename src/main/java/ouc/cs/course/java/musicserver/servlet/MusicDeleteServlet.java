package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.service.MusicSheetService;
import ouc.cs.course.java.musicserver.service.MusicSheetToMusicService;

@WebServlet("/deletemusic")
public class MusicDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		MusicService musicService = new MusicService();
		MusicSheetService musicSheetService = new MusicSheetService();
		MusicSheetToMusicService musicSheetToMusicService = new MusicSheetToMusicService();

		String md5value = request.getParameter("md5");
		String uuid = request.getParameter("uuid");
		
		if (md5value == null) {
			System.out.println("Please check parameter: md5value.");
			return;
		}

		try {
			System.out.println("music has deleted");
			int musicId = musicService.findMusicByMd5value(md5value).getId();
			int musicSheetId = musicSheetService.findByUuid(uuid).getId();
			musicSheetToMusicService.deleteByMusicIdAndMusicsheetId(musicId, musicSheetId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
