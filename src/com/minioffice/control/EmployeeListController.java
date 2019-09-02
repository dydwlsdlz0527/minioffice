package com.minioffice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.exception.NotFoundException;
import com.minioffice.service.EmployeeListService;
import com.minioffice.service.EmployeeService;
import com.minioffice.vo.Employee;
import com.minioffice.vo.PageBean;

public class EmployeeListController {
	
	//private EmployeeListService service;
//	public BoardController() {
//		service = new BoardService();
//	}
	
	private EmployeeListService service;
	static private EmployeeListController controller = new EmployeeListController();
	private EmployeeListController() {
		service = new EmployeeListService();
	}
	static public EmployeeListController getInstance() {
		return controller;
	}
	
	
	public String employeeList(HttpServletRequest request, 
			HttpServletResponse response)  throws ServletException, IOException{
		String currentPage = request.getParameter("currentPage");
		int intCurrentPage = 1;
		if(currentPage != null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		try {
			PageBean<Employee> pb = service.employeeList(intCurrentPage);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}		
		
		
		String path ="/view/Employee/employeeList.jsp";
		return path;
	}
	
	public static void main(String[] args) {
		
	}
	
}
