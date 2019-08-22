package com.minioffice.control;

import java.io.IOException;

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
import com.minioffice.vo.PageBean;
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
		String currentPage = request.getParameter("currentPage");
		int intCurrentPage = 1;
		if(currentPage != null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		try {
			PageBean<Punctuality> p = service.work_list(intCurrentPage);	
			request.setAttribute("pb", p);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}
		
		String path = "/result.jsp";
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
