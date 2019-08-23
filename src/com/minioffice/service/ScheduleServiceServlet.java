package com.minioffice.service;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minioffice.dao.ScheduleDao;
import com.minioffice.exception.AddException;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.PSchedule;

/**
 * Servlet implementation class ScheduleServiceServlet
 */
public class ScheduleServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ScheduleDao dao;
	private PSchedule s;
   
	static private ScheduleServiceServlet scheduleservlet = new ScheduleServiceServlet();
    
	private ScheduleServiceServlet() {
    	super();
        dao = new ScheduleDao();
        
    }
    static public ScheduleServiceServlet getInstance() {
    	return scheduleservlet;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json;charset=utf-8");
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.print(result);
		
		System.out.println("---------- serviceServlet get ----------");
		String result = scheduleshow();
		
		System.out.println("sss result : " + result);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/scheduleserviceresult.jsp");
		rd.forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---------- serviceServlet post ----------");
		String schedule_no = request.getParameter("schedule_no");
		
		System.out.println("dd" + schedule_no);
		
		String result= scheduleDelete(schedule_no);
		System.out.println("db 삭제 성공");
		
		request.setAttribute("result", result);
		System.out.println("post - result : " + result);
		
		
		String path = "/result.jsp";
		System.out.println("post - path : " + path);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}
	
	public void getEmpSchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json;charset=utf-8");
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.print(result);
		
		System.out.println("---------- serviceServlet getEmpSchedule ----------");
		
		String emp_no = request.getParameter("emp_no");
		System.out.println("get_emp_no" + emp_no);
		String result = scheduleshow(emp_no);
		
		System.out.println("sss result : " + result);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/scheduleserviceresult.jsp");
		rd.forward(request, response);
	}
	
	public void Deptscheduleshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json;charset=utf-8");
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.print(result);
		
		System.out.println("---------- serviceServlet Deptscheduleshow ----------");
		

		String result = Deptscheduleshow();
		
		System.out.println("sss result : " + result);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/scheduleserviceresult.jsp");
		rd.forward(request, response);
	}
	
	public void Comscheduleshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json;charset=utf-8");
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.print(result);
		
		System.out.println("---------- serviceServlet Comscheduleshow ----------");
		

		String result = Comscheduleshow();
		
		System.out.println("sss result : " + result);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/scheduleserviceresult.jsp");
		rd.forward(request, response);
	}
	
	
	
	public String scheduleDelete(String schedule_no)
	{
		int status = -1;
		String msg = "DB 삭제 실패";

		try
		{
			dao.deleteSchedule(schedule_no);
			msg = "DB 삭제 성공";
			status = 1;
		} catch (AddException e)
		{
			msg += e.getMessage();
			e.printStackTrace();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("msg", msg);
		String str = jsonObj.toString();

		System.out.println("ss - status : " + status);
		System.out.println("ss - str : " + str);

		return str;

	}
	
	public String scheduleshow()
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.show();

			for (Map<String, String> map : list)
			{
				String id = map.get("id");
				String title = map.get("title");
				String start = map.get("start");
				String end = map.get("end");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("sss toJSONString : " + jsonArr);
		System.out.println("sss toJSONString : " + jsonArr.toJSONString());
		System.out.println("sss toString  	 : " + jsonArr.toString());
		
		return jsonArr.toString();

	}
	
	public String Comscheduleshow()
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.Comshow();

			for (Map<String, String> map : list)
			{
				String id = map.get("id");
				String title = map.get("title");
				String start = map.get("start");
				String end = map.get("end");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("sss toJSONString : " + jsonArr);
		System.out.println("sss toJSONString : " + jsonArr.toJSONString());
		System.out.println("sss toString  	 : " + jsonArr.toString());
		
		return jsonArr.toString();

	}
	
	
	public String Deptscheduleshow()
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.Deptshow();

			for (Map<String, String> map : list)
			{
				String id = map.get("id");
				String title = map.get("title");
				String start = map.get("start");
				String end = map.get("end");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("sss toJSONString : " + jsonArr);
		System.out.println("sss toJSONString : " + jsonArr.toJSONString());
		System.out.println("sss toString  	 : " + jsonArr.toString());
		
		return jsonArr.toString();

	}

	
	public String scheduleshow(String emp_no)
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.show(emp_no);

			for (Map<String, String> map : list)
			{
				String id = map.get("id");
				String title = map.get("title");
				String start = map.get("start");
				String end = map.get("end");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("sss toJSONString : " + jsonArr);
		System.out.println("sss toJSONString : " + jsonArr.toJSONString());
		System.out.println("sss toString  	 : " + jsonArr.toString());
		
		return jsonArr.toString();

	}
	

}
