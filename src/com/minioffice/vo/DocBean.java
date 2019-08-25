package com.minioffice.vo;

import java.util.Date;

public class DocBean {

	private Date docdate;
	private String doctypename;
	private String doctitle;
	private String docappr;
	private String docapprno;
	private String docno;
	
	public DocBean() {
		super();
	}

	public DocBean(Date docdate, String doctypename, String doctitle, String docappr, String docapprno,
			String docno) {
		super();
		this.docdate = docdate;
		this.doctypename = doctypename;
		this.doctitle = doctitle;
		this.docappr = docappr;
		this.docapprno = docapprno;
		this.docno = docno;
	}

	public Date getDocdate() {
		return docdate;
	}

	public void setDocdate(Date docdate) {
		this.docdate = docdate;
	}

	public String getDoctypename() {
		return doctypename;
	}

	public void setDoctypename(String doctypename) {
		this.doctypename = doctypename;
	}

	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public String getDocappr() {
		return docappr;
	}

	public void setDocappr(String docappr) {
		this.docappr = docappr;
	}

	public String getDocapprno() {
		return docapprno;
	}

	public void setDocapprno(String docapprno) {
		this.docapprno = docapprno;
	}

	public String getDocno() {
		return docno;
	}

	public void setDocno(String docno) {
		this.docno = docno;
	}

	

}
