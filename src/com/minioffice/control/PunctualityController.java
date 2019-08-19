package com.minioffice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.minioffice.service.PunctualityService;

public class PunctualityController {
	private PunctualityService service;
	static private PunctualityController controller = new PunctualityController();
	private PunctualityController() {
		service = new PunctualityService();
	}
	static public PunctualityController getInstance() {
		return controller;
	}
	
	public String start_work(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		PunctualityService service = new PunctualityService();
		String str = service.start_work(id);
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
}
