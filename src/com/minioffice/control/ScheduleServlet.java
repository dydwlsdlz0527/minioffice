package com.minioffice.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.minioffice.service.ScheduleService;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/schedulelist")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScheduleService service;
	
    public ScheduleServlet() {
    	service = new ScheduleService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emp_no = (String)session.getAttribute("emp_no");
		
		ScheduleService service = new ScheduleService();
		String str = service.scheduleList(emp_no);
		
		String path = "/result.jsp";
		request.setAttribute("result", str);		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
