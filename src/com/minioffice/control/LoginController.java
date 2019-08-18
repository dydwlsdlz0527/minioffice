package com.minioffice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.minioffice.service.LoginService;

public class LoginController {
	private LoginService service;
	static private LoginController controller = new LoginController();
	private LoginController() {
		service = new LoginService();
	}
	static public LoginController getInstance() {
		return controller;
	}
	
	//로그인
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String id = request.getParameter("id");		
		String pw = request.getParameter("pwd");		
		
		LoginService service = new LoginService();
		String str = service.login(id, pw);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status")==1) {	//로그인성공!
				session.setAttribute("loginInfo", id);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
}
