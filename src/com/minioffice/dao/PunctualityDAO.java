package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minioffice.exception.NotFoundException;

public class PunctualityDAO {

	public String start_work(String id) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String insert_start_work_SQL = "insert into punctuality values (?, 0, sysdate, null, 1, null)";
			pstmt = con.prepareStatement(insert_start_work_SQL);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate(insert_start_work_SQL);
			throw new NotFoundException("출근시간이 정상 등록되지 않았습니다.");
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
