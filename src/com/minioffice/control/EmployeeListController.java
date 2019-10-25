package com.minioffice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.exception.NotFoundException;
import com.minioffice.service.EmployeeListService;
import com.minioffice.service.EmployeeService;
import com.minioffice.vo.Department;
import com.minioffice.vo.Employee;
import com.minioffice.vo.PageBean;
import com.minioffice.vo.Rank;

public class EmployeeListController {

	// private EmployeeListService service;
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

	// DB 계정목록 불러오기
	public String employeeList(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String currentPage = request.getParameter("currentPage");

		int intCurrentPage = 1;

		if (currentPage != null) {

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

		String path = "/view/Employee/employeeList.jsp";
		return path;
	}

	// 직급선택시 검색
	public String RankSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rank_Select = request.getParameter("RankSelect");
		int intrankSelect = Integer.parseInt(rank_Select);
		int intCurrentPage = 1;

		if (intrankSelect == 0) {
			try {
				PageBean<Employee> pb = service.employeeList(intCurrentPage);
				request.setAttribute("pb", pb);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			PageBean<Employee> pb = service.RankSelect(intrankSelect);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}

		String path = "/view/Employee/employeeList.jsp";
		return path;
	}

	// 부서 선택 검색
	public String DeptSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dept_Select = request.getParameter("DeptSelect");
		int intCurrentPage = 1;
		
		if (dept_Select == "0000") {
			try {
				PageBean<Employee> pb = service.employeeList(intCurrentPage);
				request.setAttribute("pb", pb);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			PageBean<Employee> pb = service.DeptSelect(dept_Select);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}

		String path = "/view/Employee/employeeList.jsp";
		return path;
	}

}
