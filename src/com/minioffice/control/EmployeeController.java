package com.minioffice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.service.EmployeeService;
import com.minioffice.vo.Department;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Rank;


public class EmployeeController {
	private EmployeeService service;
	static private EmployeeController controller = new EmployeeController();
	private EmployeeController() {
		service = new EmployeeService();
	}
	static public EmployeeController getInstance() {
		return controller;
	}
	
	//ID중복확인
	public String overlap(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");		
		EmployeeService service = new EmployeeService();
		String str = service.overlap(id);
		System.out.println(str);

		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
	
		//계정 등록
		public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			System.out.println("들어옴?");
//			String directory = request.getSession().getServletContext().getRealPath("/upload/");
//	   		int maxSize = 1024 * 1024 * 100;
//	   		String encoding = "UTF-8";
//	   		
//	   		MultipartRequest multipartRequest
//	   		= new MultipartRequest(request, directory, maxSize, encoding,
//	   				new DefaultFileRenamePolicy());
//	   		String imgFile = multipartRequest.getOriginalFileName("img_filechk");	
//	   		String signFile = multipartRequest.getOriginalFileName("sign_filechk");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw2");
		String name = request.getParameter("name");
		String rank = request.getParameter("rank_select");
		String dept = request.getParameter("dept_select");
		String phone = request.getParameter("hp_num");
		//String zipcode1 = request.getParameter("zipcode1");
		//String zipcode2 = request.getParameter("zipcode2");
		String zipcode3 = request.getParameter("zipcode3");
		String signPw = request.getParameter("sign_pw");
		String admin = request.getParameter("admin_input");
		System.out.println(id+"||"+pw+"||"+name+"||"+rank+"||"+dept+"||"+phone+"||"+zipcode3+"||"+signPw+"||"+admin);		
		
		Employee e = new Employee();
		//e.setEmp_img(imgFile);
		//e.setEmp_sign(signFile);	
		e.setEmp_id(id+"@mini.com");
		e.setEmp_pw(pw);
		e.setEmp_name(name);
		e.setEmp_phone(phone);
		e.setEmp_addr(zipcode3);
		e.setEmp_signpw(signPw);
		e.setEmp_admin(admin.charAt(0));
		Rank r = new Rank();
		r.setRank_no(rank.charAt(0));
		e.setRank(r);
		Department d = new Department();
		d.setDept_no(dept);
		e.setDept(d);
		
		
		String str = service.save(e);		
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
		
}
