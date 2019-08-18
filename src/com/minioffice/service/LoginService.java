package com.minioffice.service;

import org.json.simple.JSONObject;

import com.minioffice.dao.LoginDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Employee;

public class LoginService {
	private LoginDAO dao;
	public LoginService() {
		dao = new LoginDAO();
	}
	
	//로그인
		public String login(String id, String pw) {
			int status = -1; //id중복 실패		
			try {
				Employee e = dao.login(id);
				if(e.getEmp_pw().equals(pw)){
					status = 1;
				}
				
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
