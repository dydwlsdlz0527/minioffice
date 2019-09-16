package com.minioffice.service;

import java.util.List;

import com.minioffice.dao.EmployeeListDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Employee;
import com.minioffice.vo.PageBean;

public class EmployeeListService {
	private EmployeeListDAO dao;
	public EmployeeListService() {
		dao = new EmployeeListDAO();
	}
	public PageBean<Employee> employeeList() throws NotFoundException{
		return employeeList(1);
	}
	public PageBean<Employee> employeeList(int currentPage) 
	                    throws NotFoundException{
		int cntPerPage = 10; //한페이지별 보여줄 목록수 
		int startRow = ((currentPage-1) * cntPerPage)+1;
		int endRow = currentPage * cntPerPage;
		List<Employee> list = dao.select(startRow, endRow);
		
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		int cntPerPageGroup = 4;//페이지그룹에서 보여줄 페이지수	
		int startPage = 
				((currentPage-1) /cntPerPageGroup)*cntPerPageGroup + 1 ; 
	    int endPage = startPage + cntPerPageGroup -1;
	    if(endPage > maxPage) {
	    	endPage = maxPage;
	    }
		PageBean<Employee> pb = 
				new PageBean<>();
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		pb.setCurrentPage(currentPage);//현재페이지
		pb.setCntPerPage(cntPerPage);//페이지별 목록수
		pb.setList(list); //목록
		pb.setTotalCnt(totalCnt); //총건수
		pb.setMaxPage(maxPage);	//최대페이지수
		return pb;
		
	}	
	public static void main(String[] args) throws NotFoundException {		
		int cntPerPage = 4; //한페이지별 보여줄 목록수 
		int cntPerPageGroup = 5;//페이지그룹에서 보여줄 페이지수	
		int maxPage = 10;
		for(int currentPage=1; currentPage<=20; currentPage++) {
			int startPage = ((currentPage-1) /cntPerPageGroup)*cntPerPageGroup + 1 ;
			int endPage = startPage + cntPerPageGroup -1;
		    if(endPage > maxPage) {
		    	endPage = maxPage;
		    }
			System.out.println(currentPage+"=" + startPage + ":" + endPage);
		}
		
//		EmployeeListService e = new EmployeeListService();
//		
//		System.out.println(e.employeeList(5));
		
	}
	

//	public Employee boardDetail(int no) throws NotFoundException{
//		return dao.selectByBoardNo(no);
//	}

}
