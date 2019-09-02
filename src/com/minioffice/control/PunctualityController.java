package com.minioffice.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.minioffice.exception.NotFoundException;
import com.minioffice.service.PunctualityService;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Punctuality;

public class PunctualityController {
	private PunctualityService service;
	static private PunctualityController controller = new PunctualityController();
	private PunctualityController() {
		service = new PunctualityService();
	}
	static public PunctualityController getInstance() {
		return controller;
	}
	
	public String work_start(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emp_no = (String)session.getAttribute("emp_no");
		System.out.println(emp_no);
		
		PunctualityService service = new PunctualityService();
		
		Punctuality p = new Punctuality();
		Employee emp = new Employee();
		emp.setEmp_no(emp_no);
		p.setEmp(emp);
		String str = service.work_start(p);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
	
	public String work_end(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emp_no = (String)session.getAttribute("emp_no");
		System.out.println(emp_no);
		
		PunctualityService service = new PunctualityService();
		
		Punctuality p = new Punctuality();
		Employee emp = new Employee();
		emp.setEmp_no(emp_no);
		p.setEmp(emp);
		String str = service.work_end(p);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
	
	public String work_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emp_no = request.getParameter("emp_no");
		//String result = service.work_list(emp_no);
		
		//request.setAttribute("result", result);
		//String path = "/result.jsp";
		System.out.println("in work_list(): 1");
		try {
			List<Map<String,String>>result = service.work_list(emp_no);
			request.setAttribute("result", result);
			System.out.println("in work_list(): 2");
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "/view/punctuality/punctuality_work_list_result.jsp";
		return path;	
	}
	
	public String work_type(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emp_no = (String)session.getAttribute("emp_no");
		String work_type = request.getParameter("work_type");
		String work_content = request.getParameter("work_content");
		System.out.println(emp_no);
		
		PunctualityService service = new PunctualityService();
		
		Punctuality p = new Punctuality();
		Employee emp = new Employee();
		emp.setEmp_no(emp_no);
		p.setEmp(emp);
		p.setWork_type(work_type);
		p.setWork_content(work_content);
		String str = service.work_type(p);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
}
