package com.minioffice.control;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.service.ScheduleService;
import com.minioffice.vo.PSchedule;


public class ScheduleConServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ScheduleService service;
	
	static private ScheduleConServlet ScheduleConServlet = new ScheduleConServlet();
	
    private ScheduleConServlet() {
    	super();
    	service = new ScheduleService();

    }
    
    static public ScheduleConServlet getInstance(){
    	
    	return ScheduleConServlet;

    }

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String schedule_subject= request.getParameter("schedule_subject"); 
		String schedule_start= request.getParameter("schedule_start"); 
		String schedule_end= request.getParameter("schedule_end");
		String schedule_content= request.getParameter("schedule_content");
		String schedule_place=request.getParameter("schedule_place");
		String schedule_type = request.getParameter("schedule_type");
		String emp_no = request.getParameter("emp_no");
		
		System.out.println("=== schedule con Post ===");
		System.out.println(schedule_subject);
		System.out.println(schedule_place);
		System.out.println(schedule_start);
		System.out.println(schedule_end);
		System.out.println(schedule_content);
		System.out.println(schedule_type);
		System.out.println(emp_no);
		
		
		PSchedule s = new PSchedule();
		
		s.setSchedule_subject(schedule_subject);
		s.setSchedule_start(schedule_start);
		s.setSchedule_end(schedule_end);
		s.setSchedule_content(schedule_content);
		s.setSchedule_place(schedule_place);
		s.setSchedule_type(schedule_type);
		s.setEmp_no(emp_no);
		
		System.out.println(s.getEmp_no());
		System.out.println(s.getSchedule_subject());
		System.out.println(s.getSchedule_start());
		System.out.println(s.getSchedule_end());
		System.out.println(s.getSchedule_content());
		System.out.println(s.getSchedule_place());
		System.out.println(s.getSchedule_type());
		
		
		String result = service.scheduleinsert(s);
		
		request.setAttribute("result", result);
		System.out.println("scs - result : " + result);
		
		
		String path = "/result.jsp";
		System.out.println("scs - path : " + path);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
//		
//		
//		s.setCalendar_title(calendar_title);
//		s.setCalendar_start_date(calendar_start_date);
//		s.setCalendar_end_date(calendar_end_date);
//		
//		String result = service.scheduleinsert(s);
//		
//		System.out.println("---------------------");
//		
//		System.out.println(s.getCalendar_title());
//		System.out.println(s.getCalendar_start_date());
//		System.out.println(s.getCalendar_end_date());
//		
//		request.setAttribute("result", result);
//		System.out.println("scs - result : " + result);
//		
//		String path = "/result.jsp";
//		System.out.println("scs - path : " + path);
//		RequestDispatcher rd = request.getRequestDispatcher(path);			
//		rd.forward(request, response);
		
		
	}

}