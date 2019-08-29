package com.minioffice.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.service.ApprovalService;
import com.minioffice.vo.DocApplicantBean;
import com.minioffice.vo.Document;
import com.minioffice.vo.Employee;


public class ApprovalController {
	private ApprovalService approvalservice;
	private Employee employee;
	
	static private ApprovalController controller = new ApprovalController();
	
	private ApprovalController() {
		approvalservice = new ApprovalService();
		employee = new Employee();
	}
	
	static public ApprovalController getInstance() {
		return controller;
	}
	
	public String selectApplicant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String str = approvalservice.selectApplicant();
		request.setAttribute("result", str);
		return "/jsonresult";
	}
	
	public String apprDocWriterInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String emp_id = request.getParameter("id");
		int docid = Integer.parseInt(request.getParameter("docid"));
		String pdocid = request.getParameter("pdocid");
		Document doc = approvalservice.apprDocWriter(emp_id,docid,pdocid);
		request.setAttribute("doc", doc);
		return "/view/approval/approvalDoc.jsp";
	}
	
	public String searchApplicant (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String emp_no = request.getParameter("empno");
		String str = approvalservice.selectApplicant(emp_no);
		request.setAttribute("result", str);
		return "/jsonresult";
	}
	
	public String applicantSend (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String[] empnolist = request.getParameterValues("applist");
		String str = approvalservice.applicantList(empnolist);
		request.setAttribute("result", str);
		return "/jsonresult";
	}
	
	public String docApprResult (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String docno = request.getParameter("docno");
		String apprno = request.getParameter("apprno");
		String appresult = request.getParameter("appresult");
		String apprcoment = request.getParameter("apprcoment");
		String str = approvalservice.docapprresult(docno,apprno,appresult,apprcoment);
		System.out.println("str="+str);
		request.setAttribute("result", str);
		return "/jsonresult";
	}
}
