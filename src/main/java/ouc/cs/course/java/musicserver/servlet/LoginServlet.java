package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ouc.cs.course.java.musicserver.service.UserService;
import ouc.cs.course.java.musicserver.util.SecUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();  
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			response.sendRedirect("/index.jsp");
		} else {
			response.sendRedirect("/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String md5 = SecUtil.md5(password);
		try {
			if (userService.findOne(username, md5).getPassword()!= null) {
				request.getSession().setAttribute("username", username);
				out.write("<script>"
						+ "alert('登录成功！');"
						+ "window.location.href='"+request.getContextPath()+"/index.jsp';"
						+ "</script>");
			} else {
				out.write("<script>"
						+ "alert('用户不存在，请重试！');"
						+ "window.location.href='"+request.getContextPath()+"/login.jsp';"
						+ "</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}