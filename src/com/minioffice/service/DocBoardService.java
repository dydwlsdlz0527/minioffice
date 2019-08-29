package com.minioffice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.minioffice.dao.DocBoardDao;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.DocBean;
import com.minioffice.vo.DocDetail;
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

	public Document boarddocdetail(String docno) {
		try {
			Document doc = dao.SelectDocAppr(docno);
			String filePath = doc.getDoc_content();
//			Path path = Paths.get(filePath);
//			FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
//			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
//			Charset charset = Charset.defaultCharset();
//			String data = "";
//			int byteCount;
//			while(true) {
//				byteCount = fileChannel.read(byteBuffer);
//				if(byteCount==-1) break;
//				byteBuffer.flip();
//				data += charset.decode(byteBuffer).toString();
//				byteBuffer.clear();
//			}
//			fileChannel.close();
//			doc.setDoc_content(data);
			File file = new File(filePath);
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			String data = "";
			while((line=bufReader.readLine())!=null) {
				data += line;
			}
			doc.setDoc_content(data);
			return doc;
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DocDetail> boarddocdetailList(String docno) {
		try {
			List<DocDetail> list = dao.SelectDocApprList(docno);
			return list;
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
