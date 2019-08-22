package com.minioffice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.minioffice.dao.BoardDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.service.BoardService;
import com.minioffice.vo.Board;
import com.minioffice.vo.Comment;
import com.minioffice.vo.PageBean;




public class BoardController {
	BoardDAO boarddao = new BoardDAO();
	BoardService service = new BoardService();
	static private BoardController controller = new BoardController();
	static public BoardController getInstance() {
		return controller;
	}	

	
	//보드리스트
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//com.my.dao에서 데이터 가져오기
		String currentPage = request.getParameter("currentPage");
		String board_type = request.getParameter("type");
		int intCurrentPage = 1;
		if(currentPage != null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		
		PageBean<Board> pb;
		try {
			pb = service.boardList(intCurrentPage, board_type);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("status", -1);
		}
		String path = null; 
		
		switch(board_type) {
		
		case "a" :
			path = "/js/board/allnotice.jsp";
			break;
		case "b" :
			path = "/js/board/freeboard.jsp";
			break;
		case "c" :
			path = "/js/board/deptnotice.jsp";
			break;
		case "d" :
			path = "/js/board/deptboard.jsp";
			break;
		}

		return path; 
		
	}
	//보드디테일, 댓글작성
	public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String board_no = request.getParameter("no");	//게시글번호로 데이터찾기
		String comment_token = request.getParameter("comment_token");
		if(comment_token!=null) {
			String emp_no = request.getParameter("emp_no");
			String comment = request.getParameter("comment");
			int commentSuccess = boarddao.commentwrite(board_no, emp_no, comment);
		}
		boarddao.views(board_no);
		Board boarddetail = boarddao.boarddetail(board_no);
		List<Comment> comment = boarddao.comment(board_no);
		request.setAttribute("Boarddetail", boarddetail);
		request.setAttribute("comment", comment);
		String path = "/js/board/boarddetail.jsp";
		return path;	
	}	
	//보드작성
	public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String emp_no = request.getParameter("emp_no");
		String board_subject = request.getParameter("boardSubject");
		String board_content = request.getParameter("boardContent");
		String board_type = request.getParameter("type");
		String path;

		int result = service.boardWriteService(emp_no, board_type, board_subject, board_content);

		if(result == 1) {
			path = "/js/board/boardwriteclear.jsp";
		}else {
			path = "/js/board/boardwritefail.jsp";
		}
		return path;
		
	}
	
	
}
