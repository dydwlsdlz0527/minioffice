package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Department;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Rank;

public class LoginDAO {

	//로그인
		public Employee login(String id) throws NotFoundException{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@localhost:1521:jurung";
				String user = "miniuser";
				String password = "1234";
				con = DriverManager.getConnection(url, user, password);
				
				String selectIdSQL = "SELECT *\n" + 
						"FROM employee NATURAL JOIN department NATURAL JOIN EMP_RANK\n" + 
						"WHERE emp_id =?";
				pstmt = con.prepareStatement(selectIdSQL);
				pstmt.setString(1, id);
				
				rs  = pstmt.executeQuery();
				if(rs.next()) {
					Employee e = new Employee();					
					e.setEmp_id(rs.getString("emp_id"));
					e.setEmp_pw(rs.getString("emp_pw"));
					e.setEmp_no(rs.getString("emp_no"));
					e.setEmp_name(rs.getString("emp_name"));
					Department d = new Department();					
					d.setDept_name(rs.getString("dept_name"));
					e.setDept(d);
					Rank r = new Rank();
					r.setRank_name(rs.getString("rank_name"));
					e.setRank(r);
					return e;
				}
				throw new NotFoundException("아이디가 존재하지 않습니다.");
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
	
	
}
