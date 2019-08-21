package com.minioffice.service;

import org.json.simple.JSONObject;

import com.minioffice.dao.EmployeeDAO;
import com.minioffice.exception.AddException;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Employee;


public class EmployeeService {
	private EmployeeDAO dao;
	public EmployeeService() {
		dao = new EmployeeDAO();
	}
	
	
	//아이디 중복체크
	public String overlap(String id) {
		int status = -1; //id중복 실패		
		try {
			dao.SelectById(id);
			status = 1;//id중복 성공
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}

	
	//가입하기(저장하기)
	public String save(Employee p) {
		int status = -1; //가입실패
		try {
			dao.save(p);
			status = 1;	//가입성공
		} catch (AddException e) {
			e.printStackTrace();
		}
		//String str = "{\"status\":" + status +"}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}

	
}

