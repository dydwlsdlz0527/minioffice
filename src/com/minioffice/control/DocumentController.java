package com.minioffice.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minioffice.service.DocService;
import com.minioffice.vo.DocType;

public class DocumentController {

	private DocService docservice;
	private DocType doctype;
	
	static private DocumentController controller = new DocumentController();
	
	private DocumentController() {
		docservice = new DocService();
		doctype = new DocType();
	}
	
	static public DocumentController getInstance() {
		return controller;
	}
	
	
	public String searchDocType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String str = docservice.searchDocType();
		request.setAttribute("result", str);
		return "/jsonresult";
	}
	
	public void jsonResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1.응답 형식 지정하기, MIME값 활용 
		response.setContentType("application/json;charset=utf-8");
		//2.응답 출력 스트림 얻기.
		PrintWriter out = response.getWriter();
		//3.응답하기
		String str = (String)request.getAttribute("result");
		out.write(str);
	}
	
	public String selectDocType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int docno = Integer.parseInt(request.getParameter("docno"));
		String str = docservice.selectDocType(docno);
		request.setAttribute("result", str);
		return "/jsonresult";
	}
	
	public String documentComplete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String docno = request.getParameter("docno");
		String doctypeno = request.getParameter("doctypeno");
		String docempno = request.getParameter("docempno");
		String docempdept = request.getParameter("docempdept");
		String docsubject = request.getParameter("docsubject");
		String doccontent = request.getParameter("doccontent").replace("\"", "\'");
		String[] applicantArr = request.getParameterValues("applist");
		String str2 = Arrays.toString(applicantArr);
		String str3 = str2.substring(1, str2.length()-1);
		StringTokenizer st = new StringTokenizer(str3,",");
		String[] array = new String[st.countTokens()];
		
		for(int i=0;i<array.length;i++) {
			array[i] = st.nextToken();
		}
		String str = docservice.docComplete(doctypeno, docno, docempno,docempdept,docsubject,doccontent,array);
		request.setAttribute("result", str);
		//System.out.println(docno+"||"+docempno+"||"+docempdept+"||"+docsubject+"||"+doccontent+"||"+applicantArr[0]);
		return "/jsonresult";
	}

}
