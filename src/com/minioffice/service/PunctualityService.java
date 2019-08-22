package com.minioffice.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.minioffice.dao.PunctualityDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Board;
import com.minioffice.vo.PageBean;
import com.minioffice.vo.Punctuality;

public class PunctualityService {
	private PunctualityDAO dao;
	public PunctualityService() {
		dao = new PunctualityDAO();
	}
	
	public String work_start(Punctuality p) {
		int status = -1;
		try {
			dao.work_start(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		
		String str = jsonObj.toString();
		System.out.println(str);
		return str;
	}
	
	public String work_end(Punctuality p) {
		int status = -1;
		try {
			dao.work_end(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		
		String str = jsonObj.toString();
		System.out.println(str);
		return str;
	}
	
	public String work_type(Punctuality p) {
		int status = -1;
		try {
			dao.work_type(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		jsonObj.put("work_type", p.getWork_type());
		jsonObj.put("work_content", p.getWork_content());
		
		String str = jsonObj.toString();
		System.out.println(str);
		return str;
	}
	
	public com.minioffice.vo.PageBean<Punctuality> work_list(int currentPage) throws NotFoundException {
		int cntPerPage = 7;	//한페이지별 보여줄 목록 수
		int cntPerPageGroup = 4; // 페이지그룹에서 보여줄 페이지수
		int startRow = ((currentPage - 1) * cntPerPage) + 1;
		int endRow = currentPage * cntPerPage;
		List<Punctuality> list = dao.select(startRow, endRow);
		
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		// 페이지그룹의 시작페이지, 끝페이지 계산
		int startPage = ((currentPage-1)/cntPerPageGroup) * cntPerPageGroup + 1;
//		int endPage = (currentPage/cntPerPageGroup + 1) * cntPerPageGroup;
		int endPage = startPage + cntPerPageGroup - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageBean<Punctuality> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);	// 현재페이지
		pb.setCntPerPage(cntPerPage);	// 페이지별 목록수
		pb.setList(list);	// 목록
		pb.setTotalCnt(totalCnt);	 //총건수
		pb.setMaxPage(maxPage);	// 최대페이지수
		pb.setStartPage(startPage);	// 최대페이지수
		pb.setEndPage(endPage); // 최대페이지수
		
		return pb;
	}
}
