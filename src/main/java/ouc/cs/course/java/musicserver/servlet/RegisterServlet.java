package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ouc.cs.course.java.musicserver.dao.UserDao;
import ouc.cs.course.java.musicserver.dao.impl.UserDaoImpl;
import ouc.cs.course.java.musicserver.model.User;
import ouc.cs.course.java.musicserver.util.SecUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 解决中文响应乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!password.equals(password2)) {
			out.write("<script>"
					+ "alert('两次密码输入不一致！');"
					+ "window.location.href='"+request.getContextPath()+"/register.jsp';"
					+ "</script>");
			//response.sendRedirect("register.html");
		}

		User user = new User();

		user.setName(username);
		user.setPassword(password);
		user.setPassMd5value(SecUtil.md5(password));

		UserDao userDao = new UserDaoImpl();

		try {
			userDao.insert(user);
			out.write("<script>"
					+ "alert('恭喜你，注册成功！');"
					+ "window.location.href='"+request.getContextPath()+"/login.jsp';"
					+ "</script>");
			//response.sendRedirect("user/login.html");
		} catch (SQLException e) {
			e.printStackTrace();
			out.write("<script>"
					+ "alert('注册失败！');"
					+ "window.location.href='"+request.getContextPath()+"/register.jsp';"
					+ "</script>");
			//response.sendRedirect("user/login.html");
		}
	}
}