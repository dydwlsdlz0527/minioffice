package com.minioffice.vo;

import java.util.Date;

public class PSchedule {
	
	private int schedule_no;
	private Employee emp;
	private Date schedule_start;
	private Date schedule_end;
	private String schedule_content;
	private String schedule_place;
	private String schedule_subject;
	private char schedule_type;
	
	
	public PSchedule() {
		super();
	}

	public PSchedule(int schedule_no, Employee emp, Date schedule_start, Date schedule_end, String schedule_content,
			String schedule_place, String schedule_subject, char schedule_type) {
		super();
		this.schedule_no = schedule_no;
		this.emp = emp;
		this.schedule_start = schedule_start;
		this.schedule_end = schedule_end;
		this.schedule_content = schedule_content;
		this.schedule_place = schedule_place;
		this.schedule_subject = schedule_subject;
		this.schedule_type = schedule_type;
	}

	public int getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}

	public Employee getemp() {
		return emp;
	}

	public void setemp(Employee emp) {
		this.emp = emp;
	}

	public Date getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(Date schedule_start) {
		this.schedule_start = schedule_start;
	}

	public Date getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(Date schedule_end) {
		this.schedule_end = schedule_end;
	}

	public String getSchedule_content() {
		return schedule_content;
	}

	public void setSchedule_content(String schedule_content) {
		this.schedule_content = schedule_content;
	}

	public String getSchedule_place() {
		return schedule_place;
	}

	public void setSchedule_place(String schedule_place) {
		this.schedule_place = schedule_place;
	}

	public String getSchedule_subject() {
		return schedule_subject;
	}

	public void setSchedule_subject(String schedule_subject) {
		this.schedule_subject = schedule_subject;
	}

	public char getSchedule_type() {
		return schedule_type;
	}

	public void setSchedule_type(char schedule_type) {
		this.schedule_type = schedule_type;
	}
	
	
}
