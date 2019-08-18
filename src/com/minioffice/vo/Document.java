package com.minioffice.vo;

import java.util.Date;

public class Document {

	private String doc_no;
	private Employee emp;
	private DocType doctype;
	private String doc_subject;
	private String doc_content;
	private String doc_state;
	private Date doc_modifiydate;
	private Date doc_startdate;
	private String doc_path;
	private Department dept;
	
	
	public Document() {
		super();
	}

	public Document(String doc_no, Employee emp, DocType doctype, String doc_subject, String doc_content,
			String doc_state, Date doc_modifiydate, Date doc_startdate, String doc_path, Department dept) {
		super();
		this.doc_no = doc_no;
		this.emp = emp;
		this.doctype = doctype;
		this.doc_subject = doc_subject;
		this.doc_content = doc_content;
		this.doc_state = doc_state;
		this.doc_modifiydate = doc_modifiydate;
		this.doc_startdate = doc_startdate;
		this.doc_path = doc_path;
		this.dept = dept;
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

	public DocType getDoctype() {
		return doctype;
	}

	public void setDoctype(DocType doctype) {
		this.doctype = doctype;
	}

	public String getDoc_subject() {
		return doc_subject;
	}

	public void setDoc_subject(String doc_subject) {
		this.doc_subject = doc_subject;
	}

	public String getDoc_content() {
		return doc_content;
	}

	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}

	public String getDoc_state() {
		return doc_state;
	}

	public void setDoc_state(String doc_state) {
		this.doc_state = doc_state;
	}

	public Date getDoc_modifiydate() {
		return doc_modifiydate;
	}

	public void setDoc_modifiydate(Date doc_modifiydate) {
		this.doc_modifiydate = doc_modifiydate;
	}

	public Date getDoc_startdate() {
		return doc_startdate;
	}

	public void setDoc_startdate(Date doc_startdate) {
		this.doc_startdate = doc_startdate;
	}

	public String getDoc_path() {
		return doc_path;
	}

	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	

	
}
