package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;

import ouc.cs.course.java.musicserver.service.CollectionsService;
import ouc.cs.course.java.musicserver.service.UserService;

@WebServlet("/deletecollection")
public class CollectionsDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserService userService = new UserService();
		CollectionsService collectionsService = new CollectionsService();

		String md5value = request.getParameter("md5");
		String uuid = request.getParameter("uuid");
		String username = (String) session.getAttribute("username");
		System.out.println(md5value);
		try {
			String userid = String.valueOf(userService.findByName(username).getId());
			System.out.println(userid);
			collectionsService.deleteCollection(md5value, uuid, userid);
			System.out.println("music has deleted from collections");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
