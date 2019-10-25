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
import com.minioffice.vo.Rank;
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
			String sql ="select b.*, e.emp_name\n" + 
					"from (select rownum rn, a.*\n" + 
					"from (select board.*\n" + 
					"from board \n" + 
					"where board_type=?\n" + 
					"order by board_no desc) a) b join employee e on b.emp_no = e.emp_no\n" + 
					"where rn between ? and ?\n" + 
					"order by board_no desc";
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
	public List<DeptBoard> deptboardlist(int startRow, int endRow, String emp_no, String type, String dept_name){
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
			/**
			 * 부서별 게시판을 조회하기 위해서 필요한 요소 : 게시판 유형, 부서부모번호, 사원번호
			 * 부서번호를 알기위해 필요한 요소 : 사원번호
			 * 부서게기판 테이블과 사원 테이블을 조인해서 사원번호를 조건으로 게시판리스트 추출
			 */
			String sql = "select dept.*\n" + 
					"from(select rownum rn, dept_board.*, dept.dept_parentno, dept.dept_name\n" + 
					"from dept_board join employee emp on dept_board.emp_no = emp.emp_no join department dept on emp.dept_no = dept.dept_no\n" + 
					"where dept.dept_parentno = (select dept_parentno from department where dept_name = ?) \n" + 
					"and dept_board_type = ?) dept\n" + 
					"where (rn between ? and ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept_name);
			pstmt.setString(2, type);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			/**
			 * dept_board_no`
			 * dept_board_subject`
			 * dept_board_content`
			 * dept_board_date`
			 * dept_board_cnt`
			 * dept_board_type`
			 * emp.emp_no`
			 */
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int dept_board_no = rs.getInt("dept_board_no");
				Employee employee = new Employee();
				String emp_no2 = rs.getString("emp_no");
				employee.setEmp_no(emp_no2);
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
			String sql = "select board.*, e.emp_name\n" + 
					"from board join employee e on board.emp_no = e.emp_no\n" + 
					"where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_No);
			rs = pstmt.executeQuery();
			rs.next();
			String board_no = rs.getString("board_no");
			Employee employee = new Employee();
			String emp_no = rs.getString("emp_no");
			employee.setEmp_no(emp_no);
			String emp_name = rs.getString("emp_name");
			employee.setEmp_name(emp_name);
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
	//부서별보드디테일
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
	public int detailboardwrite(String empno, String type, String sub, String con) {
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
			String sql = "insert into dept_board values(seq_dept_board.nextval, ?, ?, sysdate, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_subject);
			pstmt.setString(2, board_content);
			pstmt.setString(3, emp_no);
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
			String sql = "select komment.*, emp.emp_name, rank.rank_name\n" + 
					"from komment join employee emp on komment.emp_no = emp.emp_no\n" + 
					"            join emp_rank rank on emp.rank_no = rank.rank_no\n" + 
					"where komment.board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int comment_no = rs.getInt("comment_no");
				int board_no = rs.getInt("board_no");
				int parent_comment_no = rs.getInt("parent_comment_no");
				String comment_content = rs.getString("comment_content");
				Date comment_date = rs.getDate("comment_date");
				Employee employee = new Employee();
				Rank rank = new Rank();
				String emp_no = rs.getString("emp_no");
				employee.setEmp_no(emp_no);
				String emp_name = rs.getString("emp_name");
				employee.setEmp_name(emp_name);
				String rank_name = rs.getString("rank_name");
				rank.setRank_name(rank_name);
				employee.setRank(rank);
				
				Comment comment = new Comment(comment_no, board_no, parent_comment_no, comment_content, comment_date, employee);
				
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
					Employee employee = new Employee();
					Comment comment = new Comment(comment_no, board_no, parent_comment_no, comment_content, comment_date, employee);
					
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
