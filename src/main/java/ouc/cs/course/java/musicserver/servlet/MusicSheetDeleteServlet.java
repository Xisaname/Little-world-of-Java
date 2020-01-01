package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import ouc.cs.course.java.musicserver.service.MusicSheetService;
import ouc.cs.course.java.musicserver.service.MusicSheetToMusicService;

@WebServlet("/deletemusicsheet")
public class MusicSheetDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		MusicSheetService musicSheetService = new MusicSheetService();
		MusicSheetToMusicService musicSheetToMusicService = new MusicSheetToMusicService();
		String uuid = request.getParameter("uuid");

		try {
			System.out.println("music has deleted");
			
			int musicSheetId = musicSheetService.findByUuid(uuid).getId();
			musicSheetToMusicService.deleteByMusicsheetId(musicSheetId);
			musicSheetService.delete(uuid);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
