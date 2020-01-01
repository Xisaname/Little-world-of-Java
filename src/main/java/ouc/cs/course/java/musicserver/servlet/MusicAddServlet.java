package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;
import ouc.cs.course.java.musicserver.model.Rank;
import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.service.MusicSheetToMusicService;
import ouc.cs.course.java.musicserver.service.RankService;
import ouc.cs.course.java.musicserver.util.SecUtil;

/**
 * Servlet implementation class MusicAddServlet
 */
@WebServlet("/MusicAddServlet")
public class MusicAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MusicService musicService=new MusicService();
	 private RankService rankService = new RankService();
	private MusicSheetToMusicService musicSheetToMusicService=new MusicSheetToMusicService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MusicAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		SmartUpload smartUpload=new SmartUpload();
		
		smartUpload.initialize(this.getServletConfig(), request, response);
		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		_jspxFactory = JspFactory.getDefaultFactory();
		pageContext = _jspxFactory.getPageContext(this, request, response, "", true, 8192, true);
		smartUpload.initialize(pageContext);// 初始化上传操作
		if(true) {
			try {
				smartUpload.upload();
				String musheetid = smartUpload.getRequest().getParameter("sheetid");
				System.out.println("sheetid="+musheetid);
				
				System.out.println("请求servlet成功");
				SmartFile file = smartUpload.getFiles().getFile(0);
				System.out.println(file.getFileName());
				String md5value=SecUtil.md5(file.toString());
				Music mu=new Music();
				mu.setMd5value(md5value);
				mu.setName(file.getFileName());
				try {
					musicService.create(mu);
					Music mu1=musicService.findMusicByMd5value(md5value);
					MusicSheetToMusic mstm=new MusicSheetToMusic();
					mstm.setMusicId(mu1.getId());
					mstm.setMusicSheetId(Integer.valueOf(musheetid));
					musicSheetToMusicService.create(mstm);
					Rank rk = new Rank();
					rk.setName(mu1.getName());
					rk.setMd5value(mu1.getMd5value());
					rk.setTimes(0);
					rankService.insert(rk);
					System.out.println("has insert in rank!");
					smartUpload.save("C:\\Users\\12871\\eclipse-workspace3\\exp0102\\src\\main\\webapp\\img");
					//smartUpload.save("sound");
					out.write("<script>"
							+ "alert('添加成功！');"
							+ "window.location.href='"+request.getContextPath()+"/sheetmanager.jsp';"
							+ "</script>");
				} catch (SQLException e) {
					out.write("<script>"
							+ "alert('添加失败！');"
							+ "window.location.href='"+request.getContextPath()+"/sheetmanager.jsp';"
							+ "</script>");
					e.printStackTrace();
				}
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
