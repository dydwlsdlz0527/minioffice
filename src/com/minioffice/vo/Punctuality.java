package com.minioffice.vo;

import java.util.Date;

public class Punctuality {

	private Employee emp;
	private Date work_date;
	private String work_type;
	private String work_content;
	
	public Punctuality() {
		super();
	}

	public Punctuality(Employee emp, Date work_date, String work_type, String work_content) {
		super();
		this.emp = emp;
		this.work_date = work_date;
		this.work_type = work_type;
		this.work_content = work_content;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Date getWork_date() {
		return work_date;
	}

	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getWork_content() {
		return work_content;
	}

	public void setWork_content(String work_content) {
		this.work_content = work_content;
	}
	
	
	
}
