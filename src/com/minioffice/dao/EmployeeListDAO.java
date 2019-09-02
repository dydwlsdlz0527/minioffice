package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Department;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Rank;


public class EmployeeListDAO {

	public int count() throws NotFoundException{
		Connection con = null;		PreparedStatement pstmt = null;		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String selectCountSQL = "SELECT COUNT(*) FROM employee";
			pstmt = con.prepareStatement(selectCountSQL);			
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);			
		}catch(ClassNotFoundException| SQLException e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {			
					con.close();
				}catch(SQLException e) {}
			}
		}
	}
	
	public List<Employee> select(int startRow, int endRow) throws NotFoundException{
		List<Employee> list = new ArrayList<Employee>();
		Connection con = null;		PreparedStatement pstmt = null;		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectSQL = "SELECT * \r\n" + 
					"FROM employee natural join department natural join emp_rank \r\n" + 
					"WHERE rownum between ? and ?\r\n" + 
					"order by rank_no";
			pstmt = con.prepareStatement(selectSQL);//SQL구문 송신	
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();//SQL구문 실행
			while(rs.next()) {
				String emp_no = rs.getString("emp_no");
				String emp_name = rs.getString("emp_name");
				String emp_id = rs.getString("emp_id");
				String emp_pw = rs.getString("emp_pw");
				Date emp_hiredate = rs.getDate("emp_hiredate");
				String emp_phone = rs.getString("emp_phone");
				String emp_addr = rs.getString("emp_addr");
				String emp_signpw = rs.getString("emp_signpw");
				char emp_admin = rs.getString("emp_admin").charAt(0);
				Rank rank = new Rank();
				String rank_name = rs.getString("rank_name");
				rank.setRank_name(rank_name);
				String dept_name = rs.getString("dept_name");
				Department dept = new Department();
				dept.setDept_name(dept_name);			
				Employee e = 
				new Employee(emp_no, emp_name, emp_id, emp_pw, emp_hiredate, emp_phone,
						emp_addr, emp_signpw, emp_admin, rank, dept);
				list.add(e);
			}
			if(list.size() == 0) {
				throw new NotFoundException("게시목록이 없습니다.");
			}
		}catch(ClassNotFoundException| SQLException e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {			
					con.close();
				}catch(SQLException e) {}
			}
		}
		return list;
	}

//	public static void main(String[] args) {
//		EmployeeListDAO dao = new EmployeeListDAO();
//		try {
//			System.out.println(dao.count()); //7
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
}
