package ouc.cs.course.java.musicserver.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

@WebServlet("/GetAllDataServlet")
public class GetAllDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public GetAllDataServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*销量*/
		Integer[] salesVolume = {10,100,20,56,35,80};
		/*营业额*/
		double[] bussinessVolume = {10*10,100*8.5,20*9.5,56*9,35*9.5,80*9};
		/*横轴, 月份数据*/
		String[] months = {"1","2","3","4","5","6"};
		
		Map<String, Object> map = new HashMap<>();
		map.put("salesVolume", salesVolume);
		map.put("bussinessVolume",bussinessVolume);
		map.put("months", months);
		
		response.getWriter().println(JSON.toJSONString(map));
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
 
}
