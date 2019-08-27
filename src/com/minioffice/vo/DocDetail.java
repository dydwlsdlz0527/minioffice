package com.minioffice.vo;

public class DocDetail {
	
	private String doc_no;
	private String emp_no;
	private String approval_step;
	private String approval_coment;
	private String approval_result;
	private String approval_date;
	private String doc_rcvdate;
	
	public DocDetail() {
		super();
	}

	public DocDetail(String doc_no, String emp_no, String approval_step, String approval_coment, String approval_result,
			String approval_date, String doc_rcvdate) {
		super();
		this.doc_no = doc_no;
		this.emp_no = emp_no;
		this.approval_step = approval_step;
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

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getApproval_step() {
		return approval_step;
	}

	public void setApproval_step(String approval_step) {
		this.approval_step = approval_step;
	}

	public String getApproval_coment() {
		return approval_coment;
	}

	public void setApproval_coment(String approval_coment) {
		this.approval_coment = approval_coment;
	}

	public String getApproval_result() {
		return approval_result;
	}

	public void setApproval_result(String approval_result) {
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
