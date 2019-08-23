package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minioffice.exception.AddException;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.PSchedule;



public class ScheduleDao {
	
	
	public void deleteSchedule(String schedule_no) throws AddException
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);

			String insertSQL = "delete from PERSONAL_SCHEDULE where schedule_no = ?";
			pstmt = con.prepareStatement(insertSQL);
			
			System.out.println("insertSQL : " + insertSQL);
			pstmt.setString(1, schedule_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e)
		{
			throw new AddException(e.getMessage());
		} catch (SQLException e)
		{
			throw new AddException(e.getMessage());
		} finally
		{
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
		}
		
		
	}
	
	public void insert(PSchedule s) throws AddException
	{

		Connection con = null;
		PreparedStatement pstmt = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);

			String insertSQL = "INSERT INTO PERSONAL_SCHEDULE VALUES (seq_pSchedule.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertSQL);
			
			System.out.println("insertSQL : " + insertSQL);
			
			//pstmt.setString(2, s.getSchedule_emp_no());
			pstmt.setString(1, s.getEmp_no());
			pstmt.setString(2, s.getSchedule_start());
			pstmt.setString(3, s.getSchedule_end());
			pstmt.setString(4, s.getSchedule_content());
			pstmt.setString(5, s.getSchedule_place());
			pstmt.setString(6, s.getSchedule_subject());
			pstmt.setString(7, s.getSchedule_type());
			//pstmt.setString(8, s.getSchedule_type());
			
		
			pstmt.executeUpdate();

			System.out.println("--------Dao--------");
			System.out.println(s.getSchedule_subject());
			System.out.println(s.getSchedule_start());
			System.out.println(s.getSchedule_end());
			System.out.println(s.getSchedule_content());
			System.out.println(s.getSchedule_place());
			System.out.println(s.getSchedule_type());

		} catch (ClassNotFoundException e)
		{
			throw new AddException(e.getMessage());
		} catch (SQLException e)
		{
			throw new AddException(e.getMessage());
		} finally
		{
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
		}

	}
	public List<Map<String,String>> show() throws NotFoundException			
	{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM PERSONAL_SCHEDULE";
			pstmt = con.prepareStatement(selectIdSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				HashMap<String,String> map = new HashMap<>();
				String schedule_no = Integer.toString(rs.getInt("schedule_no"));
				String schedule_subject = rs.getString("schedule_subject");
				String schedule_start = rs.getString("schedule_start");
				String schedule_end = rs.getString("schedule_end");
				String schedule_type = rs.getString("schedule_type");
				String schedule_content = rs.getString("schedule_content");
				String schedule_place = rs.getString("schedule_place");
				String emp_no = rs.getString("emp_no");
				
				map.put("id", schedule_type);
				map.put("title",schedule_no +" - "+schedule_subject+" - "+ schedule_place +" - "+schedule_content );
				map.put("start", schedule_start);
				map.put("end", schedule_end);
				map.put("content", schedule_content);
				map.put("emp_no", emp_no);
				//map.put("groupId", schedule_type);	
				
				switch(schedule_type)
				{
				case "1": break;
				case "2":
					map.put("color", "green");
					break;
				case "3":
					map.put("color", "yellow");
					break;
					
				}
				
				
				list.add(map);
				
				System.out.println("------------show-----------------");
				System.out.println(rs.getString("schedule_no"));
				System.out.println(rs.getString("schedule_subject"));
				System.out.println(rs.getString("schedule_start"));
				System.out.println(rs.getString("schedule_end"));
			}
			if(list.size() == 0) {
				throw new NotFoundException("검색결과가 없습니다.");
			}
		} catch (Exception e)
		{
			throw new NotFoundException(e.getMessage());
		} finally
		{
			if (rs != null)
				try
				{
					rs.close();
				} catch (SQLException e)
				{
				}
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
			
		}
		
		System.out.println("list : " + list);
		return list;
		
	}
	public List<Map<String,String>> Comshow() throws NotFoundException			
	{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM PERSONAL_SCHEDULE where schedule_type = 3";
			pstmt = con.prepareStatement(selectIdSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				HashMap<String,String> map = new HashMap<>();
				String schedule_no = Integer.toString(rs.getInt("schedule_no"));
				String schedule_subject = rs.getString("schedule_subject");
				String schedule_start = rs.getString("schedule_start");
				String schedule_end = rs.getString("schedule_end");
				String schedule_type = rs.getString("schedule_type");
				String schedule_content = rs.getString("schedule_content");
				String schedule_place = rs.getString("schedule_place");
				String emp_no = rs.getString("emp_no");
				
				map.put("id", schedule_type);
				map.put("title",schedule_no +" - "+schedule_subject+" - "+ schedule_place +" - "+schedule_content );
				map.put("start", schedule_start);
				map.put("end", schedule_end);
				map.put("content", schedule_content);
				map.put("emp_no", emp_no);
				//map.put("groupId", schedule_type);	
				
				switch(schedule_type)
				{
				case "1": break;
				case "2":
					map.put("color", "green");
					break;
				case "3":
					map.put("color", "yellow");
					break;
					
				}
				
				
				list.add(map);
				
				System.out.println("------------show-----------------");
				System.out.println(rs.getString("schedule_no"));
				System.out.println(rs.getString("schedule_subject"));
				System.out.println(rs.getString("schedule_start"));
				System.out.println(rs.getString("schedule_end"));
			}
			if(list.size() == 0) {
				throw new NotFoundException("검색결과가 없습니다.");
			}
		} catch (Exception e)
		{
			throw new NotFoundException(e.getMessage());
		} finally
		{
			if (rs != null)
				try
				{
					rs.close();
				} catch (SQLException e)
				{
				}
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
			
		}
		
		System.out.println("list : " + list);
		return list;
		
	}
	
	public List<Map<String,String>> Deptshow() throws NotFoundException			
	{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM PERSONAL_SCHEDULE where schedule_type = 2";
			pstmt = con.prepareStatement(selectIdSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				HashMap<String,String> map = new HashMap<>();
				String schedule_no = Integer.toString(rs.getInt("schedule_no"));
				String schedule_subject = rs.getString("schedule_subject");
				String schedule_start = rs.getString("schedule_start");
				String schedule_end = rs.getString("schedule_end");
				String schedule_type = rs.getString("schedule_type");
				String schedule_content = rs.getString("schedule_content");
				String schedule_place = rs.getString("schedule_place");
				String emp_no = rs.getString("emp_no");
				
				map.put("id", schedule_type);
				map.put("title",schedule_no +" - "+schedule_subject+" - "+ schedule_place +" - "+schedule_content );
				map.put("start", schedule_start);
				map.put("end", schedule_end);
				map.put("content", schedule_content);
				map.put("emp_no", emp_no);
				//map.put("groupId", schedule_type);	
				
				switch(schedule_type)
				{
				case "1": break;
				case "2":
					map.put("color", "green");
					break;
				case "3":
					map.put("color", "yellow");
					break;
					
				}
				
				
				list.add(map);
				
				System.out.println("------------show-----------------");
				System.out.println(rs.getString("schedule_no"));
				System.out.println(rs.getString("schedule_subject"));
				System.out.println(rs.getString("schedule_start"));
				System.out.println(rs.getString("schedule_end"));
			}
			if(list.size() == 0) {
				throw new NotFoundException("검색결과가 없습니다.");
			}
		} catch (Exception e)
		{
			throw new NotFoundException(e.getMessage());
		} finally
		{
			if (rs != null)
				try
				{
					rs.close();
				} catch (SQLException e)
				{
				}
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
			
		}
		
		System.out.println("list : " + list);
		return list;
		
	}
	
	
	

	public List<Map<String,String>> show(String emp_nno) throws NotFoundException			
	{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM PERSONAL_SCHEDULE where emp_no = ?";
			pstmt = con.prepareStatement(selectIdSQL);
			
			pstmt.setString(1, emp_nno);

			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				HashMap<String,String> map = new HashMap<>();
				String schedule_no = Integer.toString(rs.getInt("schedule_no"));
				String schedule_subject = rs.getString("schedule_subject");
				String schedule_start = rs.getString("schedule_start");
				String schedule_end = rs.getString("schedule_end");
				String schedule_type = rs.getString("schedule_type");
				String schedule_content = rs.getString("schedule_content");
				String schedule_place = rs.getString("schedule_place");
				String emp_no = rs.getString("emp_no");
				
				map.put("id", schedule_type);
				map.put("title",schedule_no +" - "+schedule_subject+" - "+ schedule_place +" - "+schedule_content );
				map.put("start", schedule_start);
				map.put("end", schedule_end);
				map.put("content", schedule_content);
				map.put("emp_no", emp_no);
				//map.put("groupId", schedule_type);	
				
				switch(schedule_type)
				{
				case "1": break;
				case "2":
					map.put("color", "green");
					break;
				case "3":
					map.put("color", "yellow");
					break;
					
				}
				
				
				list.add(map);
				
				System.out.println("------------show-----------------");
				System.out.println(rs.getString("schedule_no"));
				System.out.println(rs.getString("schedule_subject"));
				System.out.println(rs.getString("schedule_start"));
				System.out.println(rs.getString("schedule_end"));
			}
			if(list.size() == 0) {
				throw new NotFoundException("검색결과가 없습니다.");
			}
		} catch (Exception e)
		{
			throw new NotFoundException(e.getMessage());
		} finally
		{
			if (rs != null)
				try
				{
					rs.close();
				} catch (SQLException e)
				{
				}
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
				}
			if (con != null)
				try
				{
					con.close();
				} catch (SQLException e)
				{
				}
			
		}
		
		System.out.println("list : " + list);
		return list;
		
	}

	
//	public static void main(String[] args)
//	{
//		ScheduleDao d = new ScheduleDao();
//		try
//		{
//			d.show();
//		} catch (NotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	

}