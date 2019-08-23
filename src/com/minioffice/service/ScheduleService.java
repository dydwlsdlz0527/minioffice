package com.minioffice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minioffice.dao.ScheduleDao;
import com.minioffice.exception.AddException;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.PSchedule;

public class ScheduleService {

	private ScheduleDao dao;

	public ScheduleService() {
		dao = new ScheduleDao();
	}

	public String scheduleinsert(PSchedule s)
	{
		int status = -1;
		String msg = "DB 저장 실패";

		try
		{
			dao.insert(s);
			msg = "DB 저장 성공";
			status = 1;
		} catch (AddException e)
		{
			msg += e.getMessage();
			e.printStackTrace();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("msg", msg);
		String str = jsonObj.toString();

		System.out.println("ss - status : " + status);
		System.out.println("ss - str : " + str);

		return str;

	}

	public String scheduleshow(String emp_no)
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.show(emp_no);

			for (Map<String, String> map : list)
			{
				String id = map.get("id");
				String title = map.get("title");
				String start = map.get("start");
				String end = map.get("end");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		} catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(jsonArr.toJSONString());
		System.out.println(jsonArr.toString());
		
		return jsonArr.toString();

	}
	
	

	
//	public static void main(String[] args)
//	{
//		ScheduleService s = new ScheduleService();
//
//		s.scheduleshow();
//		
//	}
	
	//home.jsp 화면을 위한 일정 리스트 뽑기
	public String scheduleList(String emp_no) {
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		JSONArray jsonArr = new JSONArray();
		
		try {
			list = dao.selectByEmpno(emp_no);

			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				JSONObject json = new JSONObject();
				json.put("subject", map.get("schedule_subject"));
				json.put("start", (map.get("schedule_start")).substring(6,10));
				json.put("fullDate", (map.get("schedule_start")).substring(0,10));
				jsonArr.add(json);
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		return jsonArr.toString();
	}

}