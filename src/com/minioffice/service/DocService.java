package com.minioffice.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minioffice.dao.DocumentDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.DocType;

public class DocService {
	
	private DocumentDAO dao;
	
	public DocService() {
		dao = new DocumentDAO();
	}

	public String searchDocType() {
		String str = "";
		JSONArray jsonArr = new JSONArray();
		try {
			List<Map<String,String>> pdoc_list = dao.pselectDocType();
			int num = 0;
			while(num!=pdoc_list.size()) {
				JSONObject jsonObject = new JSONObject();
				Map<String,String> map = pdoc_list.get(num++);
				jsonObject.putAll(map);
				jsonArr.add(jsonObject);
			}
			num = 0;
			List<Map<String,String>> doc_list = dao.selectDocType();
			while(num!=doc_list.size()) {
				JSONObject jsonObject = new JSONObject();
				Map<String,String> map = doc_list.get(num++);
				jsonObject.putAll(map);
				jsonArr.add(jsonObject);
			}
			str = jsonArr.toString();
			return str;
		}catch(NotFoundException e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message","검색결과가 없습니다.");
			jsonArr.add(jsonObject);
			str = jsonArr.toString();
			return str;
		}
	}
	
	public String selectDocType(int doctype_no) {
		String str = "";
		try {
		  DocType doctype = dao.ChoiceDocType(doctype_no);
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put("doc_name", doctype.getDoc_subject());
		  str = jsonObject.toString();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}

	public String docComplete(String doctypeno, String docno, String docempno, String docempdept, String docsubject, String doccontent,
			String[] applicantArr) {
		String str = "";
		try {
			Path path = Paths.get("/docfile/"+docno+".txt");
			dao.InsertDoc(doctypeno, docno, docempno, docempdept, docsubject, doccontent);
			int size = applicantArr.length;
			for(int i=0;i<size;i++) {
				System.out.println(applicantArr[i]);
				dao.InsertDocDetail(docno,applicantArr[i],i+1,size);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("state", 1);
			str = jsonObject.toString();
			return str;
		}catch (NotFoundException e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("state", -1);
			str = jsonObject.toString();
			return str;
		}
	}
}
