package com.minioffice.vo;

public class DocApplicantBean {
	
	private String empno;
	private char approval_step;
	private String empname;
	private char rank_no;
	private String rank_name;
	
	public DocApplicantBean() {
		super();
	}

	public DocApplicantBean(String empno, char approval_step, String empname, char rank_no, String rank_name) {
		super();
		this.empno = empno;
		this.approval_step = approval_step;
		this.empname = empname;
		this.rank_no = rank_no;
		this.rank_name = rank_name;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public char getApproval_step() {
		return approval_step;
	}

	public void setApproval_step(char approval_step) {
		this.approval_step = approval_step;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public char getRank_no() {
		return rank_no;
	}

	public void setRank_no(char rank_no) {
		this.rank_no = rank_no;
	}

	public String getRank_name() {
		return rank_name;
	}

	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}

}
