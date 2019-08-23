package com.minioffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.minioffice.exception.NotFoundException;
import com.minioffice.sql.MyConnection;
import com.minioffice.vo.Department;
import com.minioffice.vo.Department;
import com.minioffice.vo.DocType;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Document;
import com.minioffice.vo.Employee;
import com.minioffice.vo.PDocType;

public class DocumentDAO {

	public List<Map<String,String>> selectDocType() throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Map<String,String>> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT DOCTYPE_NO, PDOCTYPE_NO, DOCTYPE_SUBJECT\r\n" + 
					"FROM DOC_TYPE";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String,String> map = new Hashtable<>();
				map.put("id",String.valueOf(rs.getInt(1)));
				map.put("parent",rs.getString(2));
				map.put("text", rs.getString(3));
				list.add(map);
			}
			if(list.size()==0) {
				throw new NotFoundException("양식이 존재하지 않습니다.");
			}
			return list;
		} catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}
	
	public List<Map<String,String>> pselectDocType() throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Map<String,String>> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT PDOCTYPE_NO, PDOCTYPE_SUBJECT\r\n" + 
					"FROM PDOC_TYPE";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String,String> map = new Hashtable<>();
				map.put("id",rs.getString(1));
				map.put("parent","#");
				map.put("text", rs.getString(2));
				list.add(map);
			}
			if(list.size()==0) {
				throw new NotFoundException("양식이 존재하지 않습니다.");
			}
			return list;
		} catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}
	
	public DocType ChoiceDocType(int doctype_no) throws NotFoundException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			DocType doctype = new DocType();
			PDocType pdoctype = new PDocType();
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT *\r\n" + 
					"FROM DOC_TYPE\r\n" + 
					"WHERE DOCTYPE_NO = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, doctype_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				doctype.setDoctype_no(rs.getInt(1));
				doctype.setDoc_subject(rs.getString(2));
				pdoctype.setPdoctype_no(rs.getString(3));
				doctype.setPdoctype(pdoctype);
				doctype.setDoc_del(rs.getString(4));
			}
			return doctype;
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}
	//DB에서 문서 꺼내오기
//	public Document SelectDocCompleted(String docno, String docempno, String docempdept, String docsubject, String doccontent,
//			String[] applicantArr) throws NotFoundException{
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
//		try {
//			Document doc = new Document();
//			Employee emp = new Employee();
//			DocType doctype= new DocType();
//			Department dept = new Department();
//			
//		}catch (SQLException e) {
//			e.getStackTrace();
//			throw new NotFoundException(e.getMessage());
//		} finally {
//			MyConnection.close(rs, pstmt, conn);
//		}
//	}
	//DB에 문서 넣기
	public void InsertDoc(String doctypeno, String docno,  String docempno, String docempdept, String docsubject, String doccontent) throws NotFoundException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "INSERT INTO DOCUMENTS (DOC_NO,EMP_NO,DOCTYPE_NO,DOC_SUBJECT,DOC_CONTENT)\r\n" + 
					"VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1,docno);
			pstmt.setString(2, docempno);
			pstmt.setInt(3, Integer.parseInt(doctypeno));
			pstmt.setString(4,docsubject);
			pstmt.setString(5, doccontent);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(pstmt, conn);
		}
	}

	public void InsertDocDetail(String docno, String apprempno, int i, int total) throws NotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "INSERT INTO DOC_DETAIL (DOC_NO,EMP_NO,APPROVAL_STEP,APPROVAL_TOTALSTEP)\r\n" + 
					"VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1,docno);
			pstmt.setString(2, apprempno);
			pstmt.setInt(3, i);
			pstmt.setInt(4,total);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.getStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(pstmt, conn);
		}
	}
}
