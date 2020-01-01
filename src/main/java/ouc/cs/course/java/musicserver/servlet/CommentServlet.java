package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.Comment;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.service.CommentService;
import ouc.cs.course.java.musicserver.service.UserService;

/**
 * Servlet implementation class CommentAddServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		CommentService commentService = new CommentService();
		UserService userService = new UserService();

		List<Comment> comlist = null;
		response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		/**
		 * 设置响应头允许AJAX跨域访问，星号表示所有的异域请求都可以接受
		 */
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		String username = (String) session.getAttribute("username");

		try {
			userService.findByName(username).getId();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String uuid = request.getParameter("uuid");
		System.out.println("uuid="+uuid);
		String queryType = request.getParameter("type");
		System.out.println("queryType="+queryType);
		if (queryType != null) {
			
			switch (queryType) {
			case "all":
				try {
					comlist = commentService.findAll(uuid);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Writer out1 = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				
					jsonObject.put("comlist", JSONArray.fromObject(comlist));
					
				out1.write(jsonObject.toString());
				out1.flush();
				break;
			case "add":
				
				System.out.println(username);
				new MusicSheet();				
		        try {
		        	int userid = userService.findByName(username).getId();
		        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String date=df.format(new Date());
			        String content = request.getParameter("content");
			        System.out.println(content);
			        Comment com=new Comment(userid, uuid, date, username, content);
					commentService.insert(com);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("default");
				break;
			}
		}  else {
			
			Writer out1 = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Please set query type correctly.");
			out1.write(jsonObject.toString());
			out1.flush();
		}
		
	}
	/*protected void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		SmartUpload smartUpload=new SmartUpload();
		smartUpload.initialize(this.getServletConfig(), request, response);
		try {
			smartUpload.upload();
		} catch (SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String username=String.valueOf(session.getAttribute("username"));
		System.out.println(username);
		MusicSheet ms=new MusicSheet();
		String uuid="1";
		ms=muss.findByUuid(uuid);
		int userid=Integer.valueOf(String.valueOf(session.getAttribute("userid")));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=df.format(new Date());
		String content=smartUpload.getRequest().getParameter("content");
		Comment com=new Comment(userid, uuid, date, username, content);
		try {
			int result=comse.insert(com);
			if(result>0) {
				out.write("<script>"
						+ "alert('添加成功！');"
						+ "window.location.href='"+request.getContextPath()+"/index.jsp';"
						+ "</script>");
			}
			else {
				out.write("<script>"
						+ "alert('添加失败！');"
						+ "window.location.href='"+request.getContextPath()+"/index.jsp';"
						+ "</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
