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
import com.minioffice.vo.DocType;
import com.minioffice.vo.Document;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Rank;

public class ApprovalDAO {

	public List<Map<String, String>> ListDept() throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Map<String, String>> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT DEPT_NO, DEPT_PARENTNO, DEPT_NAME\r\n" + "FROM DEPARTMENT";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new Hashtable<>();
				map.put("id", rs.getString(1));
				String rs2 = rs.getString(2);
				if (rs2 == null) {
					rs2 = "#";
				}
				map.put("parent", rs2);
				map.put("text", rs.getString(3));
				list.add(map);
			}
			if (list.size() == 0) {
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

	public List<Map<String, String>> ListEmp() throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Map<String, String>> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT EMP_NO, DEPT_NO, EMP_NAME||' '||EMP_RANK.RANK_NAME\r\n"
					+ "FROM EMPLOYEE, EMP_RANK\r\n" + "WHERE EMPLOYEE.RANK_NO = EMP_RANK.RANK_NO";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new Hashtable<>();
				map.put("id", rs.getString(1));
				String rs2 = rs.getString(2);
				if (rs2 == null) {
					rs2 = "#";
				}
				map.put("parent", rs2);
				map.put("text", rs.getString(3));
				list.add(map);
			}
			if (list.size() == 0) {
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

	public Document apprDocWriter(String emp_id, int docid, String pdocid) throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Document doc = new Document();
			Employee emp = new Employee();
			Department dept = new Department();
			DocType dt = new DocType();
			Rank rk = new Rank();
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT WINFO.EMP_NO, WINFO.EMP_NAME, WINFO.RANK_NAME, WINFO.DEPT_NAME, WINFO.WDAY, WINFO.EMP_SIGN, WINFO.EMP_SIGNPW, WINFO.DOCTYPE_NO, WINFO.DOCT || '-' || DOCS.DOCSNO, WINFO.DS\r\n" + 
					"FROM (SELECT EP.EMP_NO, EP.EMP_NAME, EP.RANK_NAME, EP.DEPT_NAME, TO_CHAR(SYSDATE,'DL') WDAY, EP.EMP_SIGN, EP.EMP_SIGNPW, DOC.DOCTYPE_NO, DOC.DOCT, DOC.DS\r\n" + 
					"FROM (SELECT EMP_NO, EMP_NAME, RANK_NAME, EMP_SIGN, EMP_SIGNPW, DEPT.DEPT_NAME\r\n" + 
					"FROM EMPLOYEE EMP, EMP_RANK ER, DEPARTMENT DEPT\r\n" + 
					"WHERE EMP_ID = ? AND ER.RANK_NO=EMP.RANK_NO AND EMP.DEPT_NO = DEPT.DEPT_NO) EP,\r\n" + 
					"(SELECT DOCTYPE_NO, PDOCTYPE_NO||DOCTYPE_NO AS DOCT, DOCTYPE_SUBJECT AS DS\r\n" + 
					"FROM DOC_TYPE\r\n" + 
					"WHERE DOCTYPE_NO=? AND PDOCTYPE_NO = ?) DOC) WINFO,\r\n" + 
					"(SELECT COUNT(DOC_NO)+1 AS DOCSNO \r\n" + 
					"FROM DOCUMENTS) DOCS";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, emp_id);
			pstmt.setInt(2, docid);
			pstmt.setString(3, pdocid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp.setEmp_no(rs.getString(1));
				emp.setEmp_name(rs.getString(2));
				rk.setRank_name(rs.getString(3));
				dept.setDept_name(rs.getString(4));
				doc.setDoc_startdate(rs.getString(5));
				// map.put("emp_signimg", "");
				// map.put("emp_signpw", "0");
				dt.setDoctype_no((Integer.parseInt(rs.getString(8))));
				doc.setDoc_no(rs.getString(9));
				doc.setDoc_subject(rs.getString(10));
				emp.setRank(rk);
				doc.setEmp(emp);
				doc.setDept(dept);
				doc.setDoctype(dt);
			}else {
				throw new NotFoundException("양식이 존재하지 않습니다.");
			}
			return doc;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public Map<String, String> ChoiceApplicant(String empno) throws NotFoundException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT EMP.EMP_NO, EMP.EMP_NAME, DEPT.DEPT_NAME\r\n" + 
					"FROM EMPLOYEE EMP, DEPARTMENT DEPT\r\n" + 
					"WHERE EMP_NO = ? AND EMP.DEPT_NO=DEPT.DEPT_NO";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			Map<String, String> map = new Hashtable<>();
			if(rs.next()) {
				map.put("empno", rs.getString(1));
				map.put("empname", rs.getString(2));
				map.put("deptname", rs.getString(3));
			}else {
				throw new NotFoundException("선택한 결재자가 존재하지 않습니다.");
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public List<Map<String, String>> applicantList(String[] empnolist) throws NotFoundException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Map<String, String>> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "";
			pstmt = conn.prepareStatement(selectSQL);
			
			rs = pstmt.executeQuery();
			Map<String, String> map = new Hashtable<>();
			if(rs.next()) {
				map.put("empno", rs.getString(1));
				map.put("empname", rs.getString(2));
				map.put("deptname", rs.getString(3));
			}else {
				throw new NotFoundException("선택한 결재자가 존재하지 않습니다.");
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public Map<String,String> plusApplicantList(String empno, int i) throws NotFoundException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Map<String,String> map = new Hashtable<String, String>();
			conn = MyConnection.getConnection();
			String selectSQL = "SELECT E.EMP_NO, E.EMP_NAME, R.RANK_NO, R.RANK_NAME\r\n" + 
					"FROM EMPLOYEE E, EMP_RANK R\r\n" + 
					"WHERE EMP_NO = ? AND E.RANK_NO = R.RANK_NO";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				map.put("empno",rs.getString(1));
				map.put("appstep",String.valueOf((i+1)));
				map.put("empname",rs.getString(2));
				map.put("emprank",rs.getString(3));
				map.put("emprankname",rs.getString(4));
			}
			return map;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException("결재자 추가 오류!");
		} finally {
			MyConnection.close(rs, pstmt, conn);
		}
	}

	public void UpdateApprResult(String docno, String apprno, String appresult, String apprcoment) throws NotFoundException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String selectSQL = "UPDATE DOC_DETAIL\r\n" + 
					"SET APPROVAL_RESULT = ? , APPROVAL_COMENT = ?, APPROVAL_DATE = SYSDATE\r\n" + 
					"WHERE DOC_NO = ? AND EMP_NO = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, appresult);
			pstmt.setString(2, apprcoment);
			pstmt.setString(3, docno);
			pstmt.setString(4, apprno);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException("결재 오류!");
		} finally {
			MyConnection.close(pstmt, conn);
		}
		
	}
}
