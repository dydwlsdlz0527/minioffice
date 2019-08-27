package com.minioffice.vo;

import java.util.Date;

public class DeptBoard {

	private int dept_board_no;
	private String dept_board_subject;
	private String dept_board_content;
	private Date dept_board_date;
	private int dept_board_cnt;
	private Employee emp;
	private char dept_board_type;
	
	public DeptBoard(int dept_board_no, String dept_board_subject, String dept_board_content, Date dept_board_date,
			int dept_board_cnt, Employee emp, char dept_board_type) {
		super();
		this.dept_board_no = dept_board_no;
		this.dept_board_subject = dept_board_subject;
		this.dept_board_content = dept_board_content;
		this.dept_board_date = dept_board_date;
		this.dept_board_cnt = dept_board_cnt;
		this.emp = emp;
		this.dept_board_type = dept_board_type;
	}

	public int getDept_board_no() {
		return dept_board_no;
	}

	public void setDept_board_no(int dept_board_no) {
		this.dept_board_no = dept_board_no;
	}

	public String getDept_board_subject() {
		return dept_board_subject;
	}

	public void setDept_board_subject(String dept_board_subject) {
		this.dept_board_subject = dept_board_subject;
	}

	public String getDept_board_content() {
		return dept_board_content;
	}

	public void setDept_board_content(String dept_board_content) {
		this.dept_board_content = dept_board_content;
	}

	public Date getDept_board_date() {
		return dept_board_date;
	}

	public void setDept_board_date(Date dept_board_date) {
		this.dept_board_date = dept_board_date;
	}

	public int getDept_board_cnt() {
		return dept_board_cnt;
	}

	public void setDept_board_cnt(int dept_board_cnt) {
		this.dept_board_cnt = dept_board_cnt;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public char getDept_board_type() {
		return dept_board_type;
	}

	public void setDept_board_type(char dept_board_type) {
		this.dept_board_type = dept_board_type;
	}
	
	
	
}
