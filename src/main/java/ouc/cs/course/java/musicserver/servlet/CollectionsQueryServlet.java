package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.Collections;
import ouc.cs.course.java.musicserver.service.CollectionsService;
import ouc.cs.course.java.musicserver.service.UserService;

@WebServlet("/queryCollections")
public class CollectionsQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		boolean token = true;
		CollectionsService collectionsService = new CollectionsService();
		UserService userService = new UserService();

		List<Collections> cl = null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		/**
		 * 设置响应头允许AJAX跨域访问，星号表示所有的异域请求都可以接受
		 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String queryType = request.getParameter("type");

		if (queryType != null) {

			switch (queryType) {

			case "byUserid":
				System.out.println("Show all collections by Userid");
				try {

					String userid = String.valueOf(userService.findByName(username).getId());
					cl = collectionsService.findByUserid(userid);

				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("default");
				break;
			}

			Writer out = response.getWriter();
			JSONObject jsonObject = new JSONObject();

			if (token) {
				jsonObject.put("collectionsList", JSONArray.fromObject(cl));
				jsonObject.put("message", "Get collections list successfully.");
			} else {
				jsonObject.put("message", "Get collections list failed.");
			}
			out.write(jsonObject.toString());
			out.flush();

		} else {
			Writer out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Please set query type correctly.");
			out.write(jsonObject.toString());
			out.flush();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
