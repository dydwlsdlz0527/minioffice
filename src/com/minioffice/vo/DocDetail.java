package com.minioffice.vo;

public class DocDetail {
	
	private String doc_no;
	private Employee emp;
	private char approval_step;
	private char approval_totalstep;
	private String approval_coment;
	private char approval_result;
	private String approval_date;
	private String doc_rcvdate;
	
	public DocDetail() {
		super();
	}

	public DocDetail(String doc_no, Employee emp, char approval_step, char approval_totalstep, String approval_coment,
			char approval_result, String approval_date, String doc_rcvdate) {
		super();
		this.doc_no = doc_no;
		this.emp = emp;
		this.approval_step = approval_step;
		this.approval_totalstep = approval_totalstep;
		this.approval_coment = approval_coment;
		this.approval_result = approval_result;
		this.approval_date = approval_date;
		this.doc_rcvdate = doc_rcvdate;
	}

	public String getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public char getApproval_step() {
		return approval_step;
	}

	public void setApproval_step(char approval_step) {
		this.approval_step = approval_step;
	}

	public char getApproval_totalstep() {
		return approval_totalstep;
	}

	public void setApproval_totalstep(char approval_totalstep) {
		this.approval_totalstep = approval_totalstep;
	}

	public String getApproval_coment() {
		return approval_coment;
	}

	public void setApproval_coment(String approval_coment) {
		this.approval_coment = approval_coment;
	}

	public char getApproval_result() {
		return approval_result;
	}

	public void setApproval_result(char approval_result) {
		this.approval_result = approval_result;
	}

	public String getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(String approval_date) {
		this.approval_date = approval_date;
	}

	public String getDoc_rcvdate() {
		return doc_rcvdate;
	}

	public void setDoc_rcvdate(String doc_rcvdate) {
		this.doc_rcvdate = doc_rcvdate;
	}

	

	
}

