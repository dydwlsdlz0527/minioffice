package com.minioffice.vo;

public class DocType {

	private int doctype_no;
	private String doc_subject;
	private PDocType pdoctype;
	private String doc_del;
	
	public DocType() {
		super();
	}

	public DocType(int doctype_no, String doc_subject, PDocType pdoctype, String doc_del) {
		super();
		this.doctype_no = doctype_no;
		this.doc_subject = doc_subject;
		this.pdoctype = pdoctype;
		this.doc_del = doc_del;
	}
	

	public String getDoc_del() {
		return doc_del;
	}

	public void setDoc_del(String doc_del) {
		this.doc_del = doc_del;
	}

	public int getDoctype_no() {
		return doctype_no;
	}

	public void setDoctype_no(int doctype_no) {
		this.doctype_no = doctype_no;
	}

	public String getDoc_subject() {
		return doc_subject;
	}

	public void setDoc_subject(String doc_subject) {
		this.doc_subject = doc_subject;
	}

	public PDocType getPdoctype() {
		return pdoctype;
	}

	public void setPdoctype(PDocType pdoctype) {
		this.pdoctype = pdoctype;
	}
	
	
	
	
}
