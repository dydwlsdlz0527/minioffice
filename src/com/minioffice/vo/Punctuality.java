package com.minioffice.vo;

import java.util.Date;

public class Punctuality {

	private Employee emp;
	private Department dept;
	private Date work_start;
	private Date work_end;
	private char work_type;
	private String work_content;
	
	public Punctuality() {
		super();
	}
	
	public Punctuality(Employee emp, Department dept, Date work_start, Date work_end, char work_type,
			String work_content) {
		super();
		this.emp = emp;
		this.dept = dept;
		this.work_start = work_start;
		this.work_end = work_end;
		this.work_type = work_type;
		this.work_content = work_content;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getWork_start() {
		return work_start;
	}

	public void setWork_start(Date work_start) {
		this.work_start = work_start;
	}

	public Date getWork_end() {
		return work_end;
	}

	public void setWork_end(Date work_end) {
		this.work_end = work_end;
	}

	public char getWork_type() {
		return work_type;
	}

	public void setWork_type(char work_type) {
		this.work_type = work_type;
	}

	public String getWork_content() {
		return work_content;
	}

	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}
	
	
	
	
}
