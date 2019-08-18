package com.minioffice.control;

import com.minioffice.service.ApprovalService;
import com.minioffice.service.DocService;
import com.minioffice.vo.DocType;

public class DocTypeController {

	private ApprovalService approvalservice;
	private DocService docservice;
	private DocType doctype;
	
	static private DocTypeController controller = new DocTypeController();
	
	private DocTypeController() {
		docservice = new DocService();
		doctype = new DocType();
	}
	
	static public DocTypeController getInstance() {
		return controller;
	}
	
	
	public String searchDocType() {
		
		return "";
	}
	
	
}
