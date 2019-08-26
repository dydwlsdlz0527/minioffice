package com.minioffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.minioffice.vo.Board;
import com.minioffice.vo.Comment;
import com.minioffice.vo.Department;
import com.minioffice.vo.DeptBoard;
import com.minioffice.vo.Employee;
import com.minioffice.exception.NotFoundException;


public class BoardDAO {
	//보드리스트
	public List<Board> boardlist(int startRow, int endRow, String type) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		List<Board> list = new ArrayList<Board>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select board.*, employee.emp_name\r\n" + 
					"from( select rownum rn, board.*\r\n" + 
					"from board\r\n" + 
					"where board_type = ?\r\n" + 
					"order by board_no desc) board, employee\r\n" + 
					"where (rn between ? and ?)\r\n" + 
					"and board.emp_no = employee.emp_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				Employee employee = new Employee();
				String emp_name = rs.getString("emp_name");
				employee.setEmp_name(emp_name);
				String board_subject = rs.getString("board_subject");
				String board_content = rs.getString("board_content");
				Date board_date = rs.getDate("board_date");
				int board_cnt = rs.getInt("board_cnt");
				String board_type_s = rs.getString("board_type");
				char board_type = board_type_s.charAt(0);
				
				
				Board board = new Board(board_no, employee, board_subject, board_content, board_date, board_cnt, board_type);
				list.add(board);
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public List<DeptBoard> deptboardlist(int startRow, int endRow, String emp_no, String type){
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		List<DeptBoard> list = new ArrayList<DeptBoard>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select deptboard.*, e.dept_no\r\n" + 
					"from (select rownum rn, dept_board.*\r\n" + 
					"from dept_board\r\n" + 
					"where dept_board_type = ?\r\n" + 
					"order by dept_board_no desc) deptboard, employee e\r\n" + 
					"where (rn between ? and ?)\r\n" + 
					"and deptboard.emp_no = e.emp_no\r\n" + 
					"and e.emp_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setString(4, emp_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int dept_board_no = rs.getInt("dept_board_no");
				Employee employee = new Employee();
				Department department = new Department();
				String emp_no2 = rs.getString("emp_no");
				employee.setEmp_name(emp_no2);
				String dept_no = rs.getString("dept_board_no");
				department.setDept_no(dept_no);
				employee.setDept(department);
				String dept_board_subject = rs.getString("dept_board_subject");
				String dept_board_content = rs.getString("dept_board_content");
				Date dept_board_date = rs.getDate("dept_board_date");
				int dept_board_cnt = rs.getInt("dept_board_cnt");
				String board_type_s = rs.getString("dept_board_type");
				char dept_board_type = board_type_s.charAt(0);
				
				DeptBoard deptboard = new DeptBoard(dept_board_no, dept_board_subject, dept_board_content, dept_board_date, dept_board_cnt, employee, dept_board_type);
				list.add(deptboard);
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	//보드리스트카운트
	public int count(String type) throws NotFoundException {

		ResultSet rs = null; // sql 구문 담김
		PreparedStatement pstmt = null; // sql 송신
		Connection conn = null; // 테이블을 가져올
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String selectSQL = "select count(*) from board where board_type = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1); // 별칭 혹은 위치값
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
			// TODO Auto-generated catch block
		} finally {
			if (rs != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//board 조회수
	public void views(String board_no) {
		//ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "update board set board_cnt = board_cnt + 1 where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			pstmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	//eptboard 조회수
	public void views2(String board_no) {
		//ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "update dept_board set dept_board_cnt = dept_board_cnt + 1 where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			pstmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	//보드 디테일
	public Board boarddetail(String no) {
		Board board = null;
		int board_No = Integer.parseInt(no);
		List<Board> list = new ArrayList<Board>();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_No);
			rs = pstmt.executeQuery();
			rs.next();
			String board_no = rs.getString("board_no");
			Employee employee = new Employee();
			String emp_no = rs.getString("emp_no");
			employee.setEmp_no(emp_no);
			String board_subject = rs.getString("board_subject");
			String board_content = rs.getString("board_content");
			Date board_date = rs.getDate("board_date");
			int board_cnt = rs.getInt("board_cnt");
			String board_type_s = rs.getString("board_type");
			char board_type = board_type_s.charAt(0);
			board = new Board(board_no, employee, board_subject, board_content, board_date, board_cnt, board_type);
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public DeptBoard deptboarddetail(String no) {
		DeptBoard deptboard = null;
		int board_No = Integer.parseInt(no);
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from dept_board where dept_board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_No);
			rs = pstmt.executeQuery();
			rs.next();
			int dept_board_no = rs.getInt("dept_board_no");
			Employee employee = new Employee();
			String emp_no = rs.getString("emp_no");
			employee.setEmp_no(emp_no);
			String dept_board_subject = rs.getString("dept_board_subject");
			String dept_board_content = rs.getString("dept_board_content");
			Date dept_board_date = rs.getDate("dept_board_date");
			int dept_board_cnt = rs.getInt("dept_board_cnt");
			String board_type_s = rs.getString("dept_board_type");
			char dept_board_type = board_type_s.charAt(0);
			deptboard = new DeptBoard(dept_board_no, dept_board_subject, dept_board_content, dept_board_date, dept_board_cnt, employee, dept_board_type);
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deptboard;
	}
	//게시판글쓰기
	public int boardwrite(String empno, String type, String sub, String con) {
		String emp_no = empno;
		String board_type = type;
		String board_subject = sub;
		String board_content = con;
		
		int respon = 0;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO board values (seq_board.nextval, ?, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_no);
			pstmt.setString(2, board_subject);
			pstmt.setString(3, board_content);
			pstmt.setString(4, board_type);
			rs = pstmt.executeQuery();

			respon = 1;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return respon;
	}
	//전사게시판 댓글가져오기
	public List<Comment> comment(String no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select* from Komment where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int comment_no = rs.getInt("comment_no");
				int board_no = rs.getInt("board_no");
				int parent_comment_no = rs.getInt("parent_comment_no");
				String comment_content = rs.getString("comment_content");
				Date comment_date = rs.getDate("comment_date");
				
				Comment comment = new Comment(comment_no, board_no, parent_comment_no, comment_content, comment_date);
				
				list.add(comment);
			}
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	//부서게시판 댓글가져오기
		public List<Comment> comment2(String no) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Comment> list = new ArrayList<Comment>();
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "miniuser";
			String password = "1234";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
				String sql = "select* from Komment2 where dept_board_no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					int comment_no = rs.getInt("comment2_no");
					int board_no = rs.getInt("dept_board_no");
					int parent_comment_no = rs.getInt("parent_comment2_no");
					String comment_content = rs.getString("comment2_content");
					Date comment_date = rs.getDate("comment2_date");
					
					Comment comment = new Comment(comment_no, board_no, parent_comment_no, comment_content, comment_date);
					
					list.add(comment);
				}
				

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return list;
		}
	//댓글쓰기
	public int commentwrite (String board_no, String emp_no, String comment) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "miniuser";
		String password = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "insert into Komment values(seq_comment.nextval, ?, 0, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			pstmt.setString(2, comment);
			pstmt.setString(3, emp_no);
			rs = pstmt.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		return 0;
	}
	
}
