package com.minioffice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.exception.NotFoundException;
import com.minioffice.service.DocBoardService;
import com.minioffice.vo.DocBean;
import com.minioffice.vo.DocDetail;
import com.minioffice.vo.Document;
import com.minioffice.vo.appBoardPageBean;



public class ApprovalDocBoardController {
	
	private DocBoardService docboardservice;
	
	static private ApprovalDocBoardController controller = new ApprovalDocBoardController();
	
	private ApprovalDocBoardController() {
		docboardservice = new DocBoardService();
	}
	
	static public ApprovalDocBoardController getInstance() {
		return controller;
	}
	
	public String appWaitBoardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String currentPage = request.getParameter("currentPage");
		String empno = request.getParameter("empno");
		int intCurrentPage = 1;
		if(currentPage!=null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		appBoardPageBean<DocBean> pb = docboardservice.appWaitboardList(intCurrentPage,empno);
		request.setAttribute("pb", pb);
		request.setAttribute("status", 1);
		return "/view/approval/apprWaitBoard.jsp";
	}
	
	public String boardDocDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String docno = request.getParameter("docno");
		Document doc = docboardservice.boarddocdetail(docno);
		List<DocDetail> dd = docboardservice.boarddocdetailList(docno);
		request.setAttribute("doc", doc);
		request.setAttribute("dd", dd);
		return "/view/approval/approvalDocDetail.jsp";
	}
	
	public String myDocCompleted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String empno = request.getParameter("empno");
		List<DocBean> dblist = docboardservice.appCompletedboardList(empno);
		request.setAttribute("dblist", dblist);
		return "/view/approval/apprCompletedBoard.jsp";
	}
	
	public String myDocCancle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String empno = request.getParameter("empno");
		List<DocBean> dblist = docboardservice.appCancledboardList(empno);
		request.setAttribute("dblist", dblist);
		return "/view/approval/apprCancleBoard.jsp";
	}
	
	public String approvalmyAllBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String empno = request.getParameter("empno");
		List<DocBean> dblist = docboardservice.appMyAllboardList(empno);
		request.setAttribute("dblist", dblist);
		return "/view/approval/myapprDocAllBoard.jsp";
	}
	public String approvalExpectedBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotFoundException{
		String empno = request.getParameter("empno");
		List<DocBean> dblist = docboardservice.appExceptedboardList(empno);
		request.setAttribute("dblist", dblist);
		return "/view/approval/myExceptedBoard.jsp";
	}
}
