package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import ouc.cs.course.java.musicserver.dao.MusicSheetDao;
import ouc.cs.course.java.musicserver.dao.impl.MusicSheetDaoImpl;
import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.model.MusicSheetToMusic;
import ouc.cs.course.java.musicserver.model.Rank;
import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.service.MusicSheetToMusicService;
import ouc.cs.course.java.musicserver.service.RankService;
import ouc.cs.course.java.musicserver.util.SecUtil;

/**
 * Servlet implementation class MusicSheetAddServlet
 */
@WebServlet("/MusicSheetAddServlet")
public class MusicSheetAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MusicSheetDao musicSheetDao=new MusicSheetDaoImpl();
    private MusicService musicService=new MusicService();
    private RankService rankService = new RankService();
    private MusicSheetToMusicService musicServiceMusicSheetToMusicService=new MusicSheetToMusicService();

    public MusicSheetAddServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getParameter("action");
		if(action.equals("add")) {
			try {
				addMusicSheet(request, response);
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void addMusicSheet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		SmartUpload smartUpload=new SmartUpload();
		smartUpload.initialize(this.getServletConfig(), request, response);
		try {
			smartUpload.upload();
			String name=smartUpload.getRequest().getParameter("sheetname");
			SmartFile file = smartUpload.getFiles().getFile(0);
			System.out.println(file.getFilePathName());
			SmartFile musicf=smartUpload.getFiles().getFile(1);
			String creator=(String)session.getAttribute("username");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dateCreated=df.format(new Date());
			String imgpath=null;
			if(file.getFileName().length()!=0){
				 //imgpath = "C:\\Users\\Lenovo\\Desktop\\sound\\" + file.getFileName();
				imgpath = "img/" + file.getFileName();
			}
			String md5value=SecUtil.md5(musicf.toString());
			Music mu=new Music(md5value, musicf.getFileName());
			MusicSheetToMusic mstm=new MusicSheetToMusic();
			try {
				musicService.create(mu);
				Music music1=musicService.findMusicByMd5value(md5value);
				mstm.setMusicId(music1.getId());
				Rank rk = new Rank();
				rk.setName(music1.getName());
				rk.setMd5value(music1.getMd5value());
				rk.setTimes(0);
				rankService.insert(rk);
				System.out.println("has insert in rank!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String userid=String.valueOf(session.getAttribute("userid"));
			String uuid=SecUtil.md5(file.toString());
			MusicSheet ms=new MusicSheet(uuid, name, userid, creator, dateCreated, imgpath);
			try {
				musicSheetDao.insert(ms);
				MusicSheet ms1=musicSheetDao.findByUuid(uuid);
				mstm.setMusicSheetId(ms1.getId());
				musicServiceMusicSheetToMusicService.create(mstm);
				smartUpload.save("C:\\Users\\12871\\eclipse-workspace3\\music.server\\src\\main\\webapp\\img");
				//smartUpload.save("./sound/");
				out.write("<script>"
						+ "alert('添加成功！');"
						+ "window.location.href='"+request.getContextPath()+"/sheetmanager.jsp';"
						+ "</script>");
			} catch (SQLException e) {
				e.printStackTrace();
				out.write("<script>"
						+ "alert('添加失败！');"
						+ "window.location.href='"+request.getContextPath()+"/sheetmanager.jsp';"
						+ "</script>");
			}
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}
}
