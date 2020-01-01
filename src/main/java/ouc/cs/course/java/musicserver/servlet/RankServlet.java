package ouc.cs.course.java.musicserver.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.Rank;
import ouc.cs.course.java.musicserver.service.RankService;

@WebServlet("/queryRank")
public class RankServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		boolean token = true;

		RankService rankService = new RankService();

		List<Rank> ranklist = null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		/**
		 * 设置响应头允许AJAX跨域访问，星号表示所有的异域请求都可以接受
		 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		String md5value = request.getParameter("md5value");
		String queryType = request.getParameter("type");
		System.out.println("md5value="+md5value);
		System.out.println("queryType="+queryType);
		if (queryType != null) {

			switch (queryType) {
			case "all":
				System.out.println("Show ranklist");
				try {

					ranklist = rankService.findAll();

				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				Writer out = response.getWriter();
				JSONObject jsonObject = new JSONObject();

				if (token) {
					jsonObject.put("ranklist", JSONArray.fromObject(ranklist));
					jsonObject.put("message", "Get collections list successfully.");
				} else {
					jsonObject.put("message", "Get collections list failed.");
				}
				out.write(jsonObject.toString());
				out.flush();
				break;

			case "update":
				System.out.println("update ranklist");
				try {
					rankService.updateByMd5value(md5value);
				} catch (SQLException e) {
					token = false;
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("default");
				break;
			}

			

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
