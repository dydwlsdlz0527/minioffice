package com.minioffice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Punctuality;

public class PunctualityDAO {
	
	public List<com.minioffice.vo.Punctuality> work_list2(String empno) throws NotFoundException{
		List<com.minioffice.vo.Punctuality> list = new ArrayList<>();
		String sql = "select * from punctuality WHERE emp_no=? order by work_date asc";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, empno);
			rs  = pstmt.executeQuery();
			while(rs.next()) { 
				/*
				 * EMP_NO       NOT NULL VARCHAR2(30)  
WORK_DATE    NOT NULL DATE          
WORK_TYPE             CHAR(1)       
WORK_CONTENT     
				 */
				
				Date work_date = rs.getDate("WORK_DATE");
				String work_type = rs.getString("WORK_TYPE");
				String work_content = rs.getString("WORK_CONTENT");
				Punctuality p = new Punctuality();
				p.setWork_date(work_date);
				p.setWork_type(work_type);
				p.setWork_content(work_content);
				list.add(p);
			}
			if(list.size() == 0) {
				throw new NotFoundException("근태결과가 없습니다.");
			}
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();		
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
		System.out.println("in dao work_list2() size:" + list.size());
		return list;
	}
	public List<Map<String,String>> work_list(String empno) throws NotFoundException{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			String selectDoroSQL = "select a.* from(               \r\n" + 
					"select p.emp_no emp_no, p.work_date w_date,to_char(work_date,'yy') || '년' || to_char(work_date,'mm') || '월' || to_char(work_date,'dd') || '일' as work_date, TO_CHAR(p.work_date, 'hh:MI:ss') as work_time,p.work_type work_type, p.work_content work_content, \r\n" + 
					"        case \r\n" + 
					"        when work_type = '0' then work_date\r\n" + 
					"        end as work_start,\r\n" + 
					"        case \r\n" + 
					"        when work_type = '1' then work_date\r\n" + 
					"        end as work_end\r\n" + 
					"from punctuality p\r\n" + 
					"where emp_no = ?\r\n" + 
					"order by work_time) a";
			pstmt = con.prepareStatement(selectDoroSQL);
			pstmt.setString(1, empno);
			rs  = pstmt.executeQuery();
			while(rs.next()) { 
				HashMap<String,String> map = new HashMap<>();
				String emp_no = rs.getString("emp_no");
				String w_date = rs.getString("w_date");
				String work_date = rs.getString("work_date");
				String work_time = rs.getString("work_time");
				String work_type = rs.getString("work_type");
				String work_content = rs.getString("work_content");
				map.put("emp_no", emp_no);
				map.put("w_date", w_date);
				map.put("work_date", work_date);
				map.put("work_time", work_time);
				map.put("work_type", work_type);
				map.put("work_content", work_content);
				list.add(map);
			}
			if(list.size() == 0) {
				throw new NotFoundException("근태결과가 없습니다.");
			}
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();		
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
