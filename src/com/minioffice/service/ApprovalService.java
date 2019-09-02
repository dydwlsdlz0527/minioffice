package com.minioffice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minioffice.dao.ApprovalDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.DocApplicantBean;
import com.minioffice.vo.Document;

public class ApprovalService {
	
	private ApprovalDAO dao;
	
	public ApprovalService() {
		dao = new ApprovalDAO();
	}
	
	public String selectApplicant() {
		String str="";
		JSONArray jsonArr = new JSONArray();
		try {
			int num = 0;
			List<Map<String,String>> emp_list = dao.ListEmp();
			while(num!=emp_list.size()) {
				JSONObject jsonObject = new JSONObject();
				Map<String,String> map = emp_list.get(num++);
				jsonObject.putAll(map);
				jsonArr.add(jsonObject);
			}
			num=0;
			List<Map<String,String>> dept_list = dao.ListDept();
			while(num!=dept_list.size()) {
				JSONObject jsonObject = new JSONObject();
				Map<String,String> map = dept_list.get(num++);
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
	
	public String selectApplicant(String empno) {
		String str = "";
		try {
			Map<String,String> apprMap = dao.ChoiceApplicant(empno);
			JSONObject jsonObject = new JSONObject();
			jsonObject.putAll(apprMap);
			str = jsonObject.toString();
			return str;
		}catch(NotFoundException e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message","선택한 결재자가 없습니다.");
			str = jsonObject.toString();
			return str;
		}
	}

	public Document apprDocWriter(String emp_id,int docid,String pdocid) {
		String str = "";
		JSONArray jsonArr = new JSONArray();
		try {
		    Document doc = dao.apprDocWriter(emp_id, docid, pdocid);
			return doc;
		}catch(NotFoundException e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message","검색결과가 없습니다.");
			jsonArr.add(jsonObject);
			str = jsonArr.toString();
			return null;
		}
	}

	public String applicantList(String[] empnolist) {
		String str="";
		JSONArray jsonArr = new JSONArray();
		List<Map<String,String>> applist = new ArrayList<>();
		try {
			int num = 0;
			for(int i=0;i<empnolist.length;i++) {
				Map<String,String> appmap = dao.plusApplicantList(empnolist[i],i);
				applist.add(appmap);
			}
			while(num!=applist.size()) {
				JSONObject jsonObject = new JSONObject();
				Map<String,String> map = applist.get(num++);
				jsonObject.putAll(map);
				jsonArr.add(jsonObject);
			}
			str = jsonArr.toString();
			return str;
		}catch(NotFoundException e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message","선택한 결재자가 없습니다.");
			str = jsonObject.toString();
			return str;
		}
	}

	public String docapprresult(String docno, String apprno, String appresult, String apprcoment) {
		String str = "1";
		try {
			dao.UpdateApprResult(docno,apprno,appresult,apprcoment);
			return str;
		} catch (NotFoundException e) {
			e.printStackTrace();
			str = "-1";
			return str;
		}
	}
	
}
