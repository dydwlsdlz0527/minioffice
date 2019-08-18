package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Employee;

public class LoginDAO {

	//로그인
		public Employee login(String id) throws NotFoundException{
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
	
	
}
