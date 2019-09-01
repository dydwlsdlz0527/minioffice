package com.minioffice.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.minioffice.dao.BoardDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Board;
import com.minioffice.vo.DeptBoard;
import com.minioffice.vo.PageBean;

public class BoardService {
	BoardDAO boarddao = new BoardDAO();
	
	public int boardWriteService(String emp_no, String board_type, String board_subject, String board_content) {
		int status = -1;
		
		int result = boarddao.boardwrite(emp_no, board_type, board_subject, board_content);
		if(result == 1) {
			status = 1;
		}

		return status;
	}
	
	public com.minioffice.vo.PageBean<Board> boardList(int currentPage, String boardType, String emp_no)throws NotFoundException {
		int cntPerPage = 15;	//페이지당 보여줄 목록
		int startRow = 1+ (currentPage-1)*cntPerPage;	//첫 행
		int endRow = currentPage*cntPerPage;			//마지막 행
		int cntPerPageGroup = 3;						//화면에 보여줄 페이지수
		String type = boardType;
		List<Board> list = null;
		list = boarddao.boardlist(startRow,endRow, type);
		int startPage;									//시작 페이지
		int endPage;									//끝 페이지
		int totalCnt = boarddao.count(type);
		startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		endPage = startPage+cntPerPageGroup-1;
		int maxPage = (int)Math.ceil((float)totalCnt/cntPerPage);
		if(endPage > maxPage) {
			endPage = maxPage;
			
		}
		PageBean<Board> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);	//현재페이지
		pb.setCntPerPage(cntPerPage);	//페이지별 목록수
		pb.setList(list);//목록
		pb.setTotalCnt(totalCnt);		//총건수
		pb.setMaxPage(maxPage);			//최대페이지수
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		return pb;
		}
	
	public com.minioffice.vo.PageBean<DeptBoard> deptboardList(int currentPage, String boardType, String emp_no)throws NotFoundException {
		int cntPerPage = 15;	//페이지당 보여줄 목록
		int startRow = 1+ (currentPage-1)*cntPerPage;	//첫 행
		int endRow = currentPage*cntPerPage;			//마지막 행
		int cntPerPageGroup = 3;						//홯면에 보여줄 페이지수
		String type = boardType;
		List<DeptBoard> list = null;
		list = boarddao.deptboardlist(startRow,endRow, emp_no, type);
		int startPage;									//시작 페이지
		int endPage;									//끝 페이지
		int totalCnt = boarddao.count(type);
		startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		endPage = startPage+cntPerPageGroup-1;
		int maxPage = (int)Math.ceil((float)totalCnt/cntPerPage);
		if(endPage > maxPage) {
			endPage = maxPage;
			
		}
		PageBean<DeptBoard> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);	//현재페이지
		pb.setCntPerPage(cntPerPage);	//페이지별 목록수
		pb.setList(list);
		pb.setTotalCnt(totalCnt);		//총건수
		pb.setMaxPage(maxPage);			//최대페이지수
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		return pb;
		}
}
