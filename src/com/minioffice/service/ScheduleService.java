package com.minioffice.service;

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

	public String scheduleshow()
	{
		JSONArray jsonArr = new JSONArray();
		try
		{
			List<Map<String, String>> list = dao.show();

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

}