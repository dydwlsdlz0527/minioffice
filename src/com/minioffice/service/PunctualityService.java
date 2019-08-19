package com.minioffice.service;

import org.json.simple.JSONObject;

import com.minioffice.dao.PunctualityDAO;
import com.minioffice.exception.NotFoundException;

public class PunctualityService {
	private PunctualityDAO dao;
	public PunctualityService() {
		dao = new PunctualityDAO();
	}
	
	public String start_work(String id, String deptno) {
		int status = -1;
		try {
			String p = dao.start_work(id, deptno);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("id", id);
		
		String str = jsonObj.toString();
		return str;
	}
}
