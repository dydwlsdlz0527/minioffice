package com.minioffice.vo;

public class PDocType {
	private String pdoctype_no;
	private String pdoctype_subejct;
	private char pdoctype_del;
	
	public PDocType() {
		super();
	}

	public PDocType(String pdoctype_no, String pdoctype_subejct, char pdoctype_del) {
		super();
		this.pdoctype_no = pdoctype_no;
		this.pdoctype_subejct = pdoctype_subejct;
		this.pdoctype_del = pdoctype_del;
	}

	public String getPdoctype_no() {
		return pdoctype_no;
	}

	public void setPdoctype_no(String pdoctype_no) {
		this.pdoctype_no = pdoctype_no;
	}

	public String getPdoctype_subejct() {
		return pdoctype_subejct;
	}

	public void setPdoctype_subejct(String pdoctype_subejct) {
		this.pdoctype_subejct = pdoctype_subejct;
	}

	public char getPdoctype_del() {
		return pdoctype_del;
	}

	public void setPdoctype_del(char pdoctype_del) {
		this.pdoctype_del = pdoctype_del;
	}
	
	
	
}
