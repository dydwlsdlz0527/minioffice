package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minioffice.exception.AddException;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Employee;


public class EmployeeDAO {
	//아이디 중복확인
	public Employee SelectById(String id) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM employee WHERE emp_id=?";
			pstmt = con.prepareStatement(selectIdSQL);
			pstmt.setString(1, id);
			rs  = pstmt.executeQuery();
			if(rs.next()) {
				Employee e = new Employee();
				e.setEmp_id(rs.getString("emp_id"));
				e.setEmp_pw(rs.getString("emp_pw"));
				return e;
			}
			throw new NotFoundException("아이디가 존재하지 않습니다.");
		//}catch(ClassNotFoundException | SQLException e) {
		}catch(Exception e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) 
				try {
					rs.close();
				}catch(SQLException e) {}
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}		
	}
	
	public void save(Employee p) throws AddException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String insertSQL = 
			"insert into employee"
			+ "(emp_no, emp_name, emp_id, emp_pw, emp_hiredate, emp_phone,"
			+ " emp_addr, emp_sign, emp_img, emp_signpw, emp_admin, rank_no, dept_no)"
			+ " values(to_char(sysdate,'yyyymm') || seq_empno.NEXTVAL,?,?"
			+ ",?,sysdate,?,?,null,null,?,?,?,?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, p.getEmp_name());
			pstmt.setString(2, p.getEmp_id());
			pstmt.setString(3, p.getEmp_pw());
			pstmt.setString(4, p.getEmp_phone());
			pstmt.setString(5, p.getEmp_addr());
			//pstmt.setString(6, String.valueOf(p.getEmp_sign()));
			//pstmt.setString(7, String.valueOf(p.getEmp_img()));
			pstmt.setString(6, p.getEmp_signpw());
			pstmt.setString(7, String.valueOf(p.getEmp_admin()));
			pstmt.setString(8, String.valueOf(p.getRank().getRank_no()));
			//pstmt.setString(8, p.getRank().getRank_no()+"");
			pstmt.setString(9, p.getDept().getDept_no());
			
			System.out.println(p.getEmp_name()+":"+p.getEmp_id()+":"+p.getEmp_pw()+":"+p.getEmp_phone()+":"+p.getEmp_addr()+":"+p.getEmp_sign()
					+":"+p.getEmp_img()+":"+p.getEmp_signpw()+":"+p.getEmp_admin()+":"+p.getRank().getRank_no()+":"+p.getDept().getDept_no());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new AddException(e.getMessage());
		}catch(SQLException e) {
			throw new AddException(e.getMessage());
		}finally {		
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}		
	}

}

