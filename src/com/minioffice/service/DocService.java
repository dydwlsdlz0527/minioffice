package com.minioffice.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

	public String docComplete(String doctypeno, String docno, String docempno, String docempdept, String docsubject, String docpath,
			String[] applicantArr) {
		String str = "";
		try {
			/*
			 * String pathtxt = "C:/docfile/"+docno+".txt"; Path path = Paths.get(pathtxt);
			 * Files.createDirectories(path.getParent());
			 * 
			 * FileChannel fileChannel = FileChannel.open(path,
			 * StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			 * 
			 * String docdata = docpath; Charset charset = Charset.defaultCharset();
			 * ByteBuffer byteBuffer = charset.encode(docdata);
			 */
			dao.InsertDoc(doctypeno, docno, docempno, docempdept, docsubject, docpath);
			int size = applicantArr.length;
			for(int i=0;i<size;i++) {
				System.out.println(applicantArr[i]);
				dao.InsertDocDetail(docno,applicantArr[i],i+1,size);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("state", 1);
			str = jsonObject.toString();
			/* fileChannel.close(); */
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
