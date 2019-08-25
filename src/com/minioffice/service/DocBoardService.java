package com.minioffice.service;

import java.util.List;

import com.minioffice.dao.DocBoardDao;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.DocBean;
import com.minioffice.vo.Document;
import com.minioffice.vo.appBoardPageBean;

public class DocBoardService {

	private DocBoardDao dao;
	
	public DocBoardService() {
		dao = new DocBoardDao();
	}

	public appBoardPageBean<DocBean> appWaitboardList(int currentPage, String empno){
		try {
			int cntPerPage = 5;
			int cntPageGroup = 3; //페이지 그룹에서 보여줄 페이지 수
			int startRow = (currentPage-1)*cntPerPage+1;
			int endRow = currentPage*cntPerPage;
			List<DocBean> list = dao.appBoardselect(startRow, endRow, empno);
			int totalCnt = dao.appBoardcount(empno);
			int maxPage = (int)Math.ceil((float)totalCnt/cntPerPage);
			int startPage = ((int)Math.floor((currentPage-1)/cntPageGroup))*cntPageGroup+1;
			int endPage = (int)Math.ceil(((float)currentPage/cntPageGroup))*cntPageGroup;
			if(endPage>maxPage) {
				endPage = maxPage;
			}
			appBoardPageBean<DocBean> pb = new appBoardPageBean<>();
			pb.setCntPerPage(cntPerPage);
			pb.setList(list);
			pb.setMaxPage(maxPage);
			pb.setCurrentPage(currentPage);
			pb.setStartPage(startPage);
			pb.setEndPage(endPage);
			pb.setCntPageGroup(cntPageGroup);
			return pb;
		}catch(NotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public Document boarddocdetail(String docno, String empno) {
		Document doc = dao.SelectDoc(docno);
		return null;
	}
}
