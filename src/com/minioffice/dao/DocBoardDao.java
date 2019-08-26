package com.minioffice.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.minioffice.exception.NotFoundException;
import com.minioffice.sql.MyConnection;
import com.minioffice.vo.Department;
import com.minioffice.vo.DocBean;
import com.minioffice.vo.DocDetail;
import com.minioffice.vo.Document;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Rank;

public class DocBoardDao {
	
	//개인당 결재대기문서 게시판 총 카운트 수
	public int appBoardcount(String empno) throws NotFoundException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String query = "SELECT COUNT(*)\r\n" + 
					"FROM DOCUMENTS D,(SELECT DISTINCT DD2.EMP_NO, DD2.DOC_NO, DD2.APPROVAL_STEP, DD2.APPROVAL_TOTALSTEP\r\n" + 
					"FROM DOC_DETAIL DD1,(SELECT EMP_NO, DOC_NO, APPROVAL_STEP, APPROVAL_TOTALSTEP\r\n" + 
					"FROM DOC_DETAIL\r\n" + 
					"WHERE EMP_NO = ?) DD2\r\n" + 
					"WHERE DD1.DOC_NO = DD2.DOC_NO\r\n" + 
					"AND (((DD2.APPROVAL_STEP-1)= DD1.APPROVAL_STEP AND DD1.APPROVAL_RESULT='1')\r\n" + 
					"OR DD2.APPROVAL_STEP='1'))DD\r\n" + 
					"WHERE D.DOC_NO = DD.DOC_NO";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public List<DocBean> appBoardselect(int startRow, int endRow, String empno) throws NotFoundException {
		List<DocBean> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String query = "SELECT FD.*\r\n" + 
					"FROM (SELECT rownum rn,  D.DOC_STARTDATE, DT.DOCTYPE_SUBJECT, D.DOC_SUBJECT,  EMP.EMP_NAME, D.DOC_NO, EMP.EMP_NO\r\n" + 
					"FROM DOCUMENTS D,(SELECT DISTINCT DD2.EMP_NO, DD2.DOC_NO, DD2.APPROVAL_STEP, DD2.APPROVAL_TOTALSTEP\r\n" + 
					"FROM DOC_DETAIL DD1,(SELECT EMP_NO, DOC_NO, APPROVAL_STEP, APPROVAL_TOTALSTEP\r\n" + 
					"FROM DOC_DETAIL\r\n" + 
					"WHERE EMP_NO = ?) DD2\r\n" + 
					"WHERE DD1.DOC_NO = DD2.DOC_NO\r\n" + 
					"AND (((DD2.APPROVAL_STEP-1)= DD1.APPROVAL_STEP AND DD1.APPROVAL_RESULT='1')\r\n" + 
					"OR DD2.APPROVAL_STEP='1'))DD, DOC_TYPE DT, EMPLOYEE EMP\r\n" + 
					"WHERE D.DOC_NO = DD.DOC_NO\r\n" + 
					"AND DT.DOCTYPE_NO = D.DOCTYPE_NO\r\n" + 
					"AND EMP.EMP_NO = D.EMP_NO) FD\r\n" + 
					"WHERE FD.RN BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DocBean doc = new DocBean();
			    doc.setDocdate(rs.getDate(2));
			    doc.setDoctypename(rs.getString(3));
			    doc.setDoctitle(rs.getString(4));
			    doc.setDocappr(rs.getString(5));
			    doc.setDocno(rs.getString(6));
			    doc.setDocapprno(rs.getString(7));
				list.add(doc);
			}
			
			return list;
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public Document SelectDocAppr(String docno) throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Document doc = new Document();
		Employee emp = new Employee();
		Department dept = new Department();
		Rank rank = new Rank();
		try {
			conn = MyConnection.getConnection();
			String query = "SELECT EMP.EMP_NAME, RK.RANK_NAME, DEPT.DEPT_NAME, TO_CHAR(DOC.DOC_STARTDATE,'DL'), DOC.DOC_NO, DOC.COPER_DEPT, DOC.DOC_SUBJECT, DOC.DOC_CONTENT\r\n" + 
					"FROM DOCUMENTS DOC, EMPLOYEE EMP, DEPARTMENT DEPT, EMP_RANK RK\r\n" + 
					"WHERE DOC.DOC_NO = ?\r\n" + 
					"AND DOC.EMP_NO = EMP.EMP_NO\r\n" + 
					"AND EMP.DEPT_NO = DEPT.DEPT_NO\r\n" + 
					"AND EMP.RANK_NO = RK.RANK_NO";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, docno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp.setEmp_name(rs.getString(1)); //사원 이름
				rank.setRank_name(rs.getString(2)); //사원 등급 이름
				dept.setDept_name(rs.getString(3)); //부서 이름
				doc.setDoc_startdate(rs.getString(4)); //기안일
				doc.setDoc_no(rs.getString(1));	//문서 번호
				doc.setDoc_subject(rs.getString(6)); //문서 제목
				doc.setDoc_content(rs.getString(7)); //문서 내용
				emp.setRank(rank);
				doc.setDept(dept);
			}
			return doc;
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}
	
	public List<DocDetail> SelectDocApprList(String docno) throws NotFoundException{
		List<DocDetail> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String query = "";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, docno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
			return list;
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}
	
//	public Document documentDetail(String empno) throws NotFoundException {
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
//		Document doc = new Document();
//		try {
//			conn = MyConnection.getConnection();
//			String query = "SELECT FD.*\r\n" + 
//					"FROM (SELECT rownum rn, D.*\r\n" + 
//					"FROM DOCUMENTS D,(SELECT DISTINCT DD2.EMP_NO, DD2.DOC_NO, DD2.APPROVAL_STEP, DD2.APPROVAL_TOTALSTEP\r\n" + 
//					"FROM DOC_DETAIL DD1,(SELECT EMP_NO, DOC_NO, APPROVAL_STEP, APPROVAL_TOTALSTEP\r\n" + 
//					"FROM DOC_DETAIL\r\n" + 
//					"WHERE EMP_NO = ?) DD2\r\n" + 
//					"WHERE DD1.DOC_NO = DD2.DOC_NO\r\n" + 
//					"AND (((DD2.APPROVAL_STEP-1)= DD1.APPROVAL_STEP AND DD1.APPROVAL_RESULT='1')\r\n" + 
//					"OR DD2.APPROVAL_STEP='1'))DD\r\n" + 
//					"WHERE D.DOC_NO = DD.DOC_NO) FD\r\n" + 
//					"WHERE FD.RN BETWEEN ? AND ?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, empno);
//			rs = pstmt.executeQuery(query);
//			if(rs.next()) {
//				Employee emp = new Employee();
//				DocType doctp = new DocType();
//				Department dept = new Department();
//				doc.setDoc_no((rs.getString(2)));
//				emp.setEmp_no(rs.getString(3));
//				doctp.setDoctype_no(rs.getInt(4));
//				doc.setDoc_subject(rs.getString(5));
//				doc.setDoc_content(rs.getString(6));
//				doc.setDoc_state(rs.getString(7));
//				doc.setDoc_modifiydate(rs.getString(8));
//				doc.setDoc_startdate(rs.getString(9));
//				
//				doc.setDept(dept);
//				doc.setEmp(emp);
//				doc.setDoctype(doctp);
//			}
//			return doc;
//		}catch (SQLException e) {
//			e.getStackTrace();
//			throw new NotFoundException(e.getMessage());
//		} finally {
//			MyConnection.close(rs, pstmt, conn);
//		}
//	}

	

}
