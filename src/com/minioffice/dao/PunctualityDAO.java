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
import com.minioffice.vo.Board;
import com.minioffice.vo.Employee;
import com.minioffice.vo.Punctuality;

public class PunctualityDAO {
	
//	public void work_list(Punctuality p) throws NotFoundException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "miniuser";
//			String password = "1234";
//			con = DriverManager.getConnection(url, user, password);
//			
//			String insert_work_start_SQL = "select * from punctuality where emp_no = ?";
//			pstmt = con.prepareStatement(insert_work_start_SQL);
//			pstmt.setString(1, p.getEmp().getEmp_no());
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				String emp_no = rs.getString("emp_no");
//				Date work_date = rs.getDate("work_date");
//				String work_type = rs.getString("work_type");
//				String work_content = rs.getString("work_content");
//				
//				System.out.println(emp_no + " - " + work_date + " - " + work_type + " - " + work_content);
//			}
//			
//			//throw new NotFoundException("출근시간이 정상 등록되지 않았습니다.");
//		//}catch(ClassNotFoundException | SQLException e) {
//		}catch(Exception e) {
//			throw new NotFoundException(e.getMessage());
//		}finally {
//			if(rs != null) 
//				try {
//					rs.close();
//				}catch(SQLException e) {}
//			if(pstmt != null) 
//				try {
//				pstmt.close();
//				}catch(SQLException e) {}
//			if(con != null) 
//				try {
//					con.close();
//				}catch(SQLException e) {}
//		}
//	}
	
	public List<Punctuality> select(int startRow, int endRow) throws NotFoundException {
		List<Punctuality> list = new ArrayList<Punctuality>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "miniuser";
			String DB_PW = "1234";
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			String selectSQL = "SELECT * FROM \r\n" + 
					"(SELECT ROWNUM rn, punctuality.*\r\n" + 
					"FROM punctuality\r\n" +
					"ORDER work_date asc) a\r\n" + 
					"WHERE a.rn BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(selectSQL);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Punctuality p = new Punctuality();
				Employee e = new Employee();
				e.setEmp_no(rs.getString("emp_no"));
				p.setEmp(e);
				p.setWork_date(rs.getDate("work_date"));
				p.setWork_type(rs.getString("work_type"));
				p.setWork_content(rs.getString("work_content"));
				
				list.add(p);
			}
			if(list.size() == 0) {
				throw new NotFoundException("게시목록이 없습니다.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public int count() throws NotFoundException {
		// select count(*) from board;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "miniuser";
			String DB_PW = "1234";
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			String selectCountSQL = "select count(*) from punctuality";
			pstmt = conn.prepareStatement(selectCountSQL);
			
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void work_start(Punctuality p) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String insert_work_start_SQL = "insert into punctuality values (?, sysdate, '0', '출근')";
			pstmt = con.prepareStatement(insert_work_start_SQL);
			pstmt.setString(1, p.getEmp().getEmp_no());
			
			pstmt.executeUpdate();
			//throw new NotFoundException("출근시간이 정상 등록되지 않았습니다.");
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
	
	public void work_end(Punctuality p) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String update_work_end_SQL = "insert into punctuality values(?, sysdate, '1', '퇴근')";
			pstmt = con.prepareStatement(update_work_end_SQL);
			pstmt.setString(1, p.getEmp().getEmp_no());
			
			pstmt.executeUpdate();
			//throw new NotFoundException("출근시간이 정상 등록되지 않았습니다.");
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
	
	public void work_type(Punctuality p) throws NotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String update_work_type_SQL ="insert into punctuality values (?, sysdate, ?, ?)";
			
			pstmt = con.prepareStatement(update_work_type_SQL);
			pstmt.setString(1, p.getEmp().getEmp_no());
			pstmt.setString(2, p.getWork_type());
			pstmt.setString(3, p.getWork_content());
			
			pstmt.executeUpdate();
			//throw new NotFoundException("출근시간이 정상 등록되지 않았습니다.");
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
