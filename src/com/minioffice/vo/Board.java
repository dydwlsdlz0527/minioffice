package com.minioffice.vo;

import java.util.Date;

public class Board {

	private String board_no;
	private Employee emp;
	private String board_subject;
	private String board_content;
	private Date board_date;
	private int board_cnt;
	private char board_type;
	
	
	
	public Board() {
		super();
	}

	public Board(String board_no, Employee emp, String board_subject, String board_content, Date board_date,
			int board_cnt, char board_type) {
		super();
		this.board_no = board_no;
		this.emp = emp;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_date = board_date;
		this.board_cnt = board_cnt;
		this.board_type = board_type;
	}

	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public int getBoard_cnt() {
		return board_cnt;
	}

	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}

	public char getBoard_type() {
		return board_type;
	}

	public void setBoard_type(char board_type) {
		this.board_type = board_type;
	}
	
	
	
	
}
