package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import ouc.cs.course.java.musicserver.model.Collections;
import ouc.cs.course.java.musicserver.service.CollectionsService;
import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.service.MusicSheetService;
import ouc.cs.course.java.musicserver.service.UserService;

@WebServlet("/collectmusic")
public class MusicCollectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MusicSheetService musicSheetService = new MusicSheetService();
		MusicService musicService = new MusicService();
		CollectionsService collectionsService = new CollectionsService();
		String username = (String) session.getAttribute("username");
		UserService userService = new UserService();
		String md5value = request.getParameter("md5");
		String sheetname = request.getParameter("sheetname");
		
		try {
			String userid = String.valueOf(userService.findByName(username).getId());
			String uuid = musicSheetService.findBySheetName(sheetname).getUuid();
			String musicname = musicService.findMusicByMd5value(md5value).getName();
			String creatorname = musicSheetService.findBySheetName(sheetname).getCreator();
			String datecreated = musicSheetService.findBySheetName(sheetname).getDateCreated();
			Collections cl = new Collections(uuid, md5value, userid, musicname, sheetname, creatorname, datecreated);
			collectionsService.create(cl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
