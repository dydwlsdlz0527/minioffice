package com.minioffice.vo;

public class Department {

	private String dept_no;
	private String dept_name;
	private String dept_parentno;
	
	public Department() {
		super();
	}

	public Department(String dept_no, String dept_name, String dept_parentno) {
		super();
		this.dept_no = dept_no;
		this.dept_name = dept_name;
		this.dept_parentno = dept_parentno;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_parentno() {
		return dept_parentno;
	}

	public void setDept_parentno(String dept_parentno) {
		this.dept_parentno = dept_parentno;
	}
	
	
	
	
}
