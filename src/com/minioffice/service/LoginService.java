package com.minioffice.service;

import org.json.simple.JSONObject;

import com.minioffice.dao.LoginDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Department;
import com.minioffice.vo.Employee;

public class LoginService {
	private LoginDAO dao;
	public LoginService() {
		dao = new LoginDAO();
	}
	
	//로그인
		public String login(String id, String pw) {
			int status = -1; //id중복 실패
			JSONObject jsonObj = new JSONObject();
			
			try {
				Employee e = dao.login(id);
				if(e.getEmp_pw().equals(pw)){
					status = 1;
				}				
				jsonObj.put("status", status);
				jsonObj.put("emp_id", e.getEmp_id());
				jsonObj.put("emp_no", e.getEmp_no());
				jsonObj.put("emp_name", e.getEmp_name());
				jsonObj.put("dept_name", e.getDept().getDept_name());
				jsonObj.put("rank_name", e.getRank().getRank_name());				
			} catch (NotFoundException e) {
				e.printStackTrace();
			}			
			
			String str = jsonObj.toString();
			System.out.println(str);
			return str;
		}
}
