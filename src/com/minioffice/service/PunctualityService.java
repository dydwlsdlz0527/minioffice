package com.minioffice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minioffice.dao.PunctualityDAO;
import com.minioffice.exception.NotFoundException;
import com.minioffice.vo.Punctuality;

public class PunctualityService {
	private PunctualityDAO dao;
	public PunctualityService() {
		dao = new PunctualityDAO();
	}
	
	public String work_start(Punctuality p) {
		int status = -1;
		try {
			dao.work_start(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		
		String str = jsonObj.toString();
		//System.out.println(str);
		return str;
	}
	
	public String work_end(Punctuality p) {
		int status = -1;
		try {
			dao.work_end(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		
		String str = jsonObj.toString();
		//System.out.println(str);
		return str;
	}
	
	public String work_type(Punctuality p) {
		int status = -1;
		try {
			dao.work_type(p);
			status = 1;
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("emp_no", p.getEmp().getEmp_no());
		jsonObj.put("work_type", p.getWork_type());
		jsonObj.put("work_content", p.getWork_content());
		
		String str = jsonObj.toString();
		//System.out.println(str);
		return str;
	}
	
//	public String work_list(String empno) {
////		
//		JSONArray jsonArr = new JSONArray();
//		try {
//			List<Map<String,String>> list = dao.work_list(empno);
//			
//			for(Map<String,String> map: list) {
//				String emp_no = map.get("emp_no");
//				String w_date = map.get("w_date");
//				String work_date = map.get("work_date");
//				String work_time = map.get("work_time");
//				String work_type = map.get("work_type");
//				String work_content = map.get("work_content");
//				JSONObject jsonObj = new JSONObject();
//				jsonObj.putAll(map);
//				jsonArr.add(jsonObj);
//			}
//		}catch(NotFoundException e) {
//			e.printStackTrace();			
//		}
//		System.out.println(jsonArr.toJSONString());
//		System.out.println(jsonArr.toString());
//		return jsonArr.toString();
//	}
	
	
	public List<Map<String,String>> work_list(String empno) throws NotFoundException {
//		
		List<Map<String, String>> result = new ArrayList<>();
		JSONArray jsonArr = new JSONArray();
		List<Punctuality> list = dao.work_list2(empno);
		String work_date=null;
		String work_type=null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
		Date[] timeArr = new Date[2];
		String work_content = "";
		int i=0;
		while(i < list.size()) {
			Punctuality p= list.get(i);
			
			String  new_work_date = sdf.format(p.getWork_date()); //년월일
			String new_work_type = p.getWork_type();
			String new_work_content = p.getWork_content();
			
			if(i != 0 && !new_work_date.equals(work_date)) { //날이 바뀔 경우	
				//System.out.println("in if i=" + i + "new_work_date=" + new_work_date + ", work_date=" + work_date);
				boolean flag = true; //출퇴근여부
				Map<String, String> map = new HashMap<>();
				map.put("work_date", work_date); //날짜
				if(timeArr[0] == null) {
					map.put("work_time0", "");
					flag = false;
				}else {
					map.put("work_time0", sdf1.format(timeArr[0])); //출근시간
				}
				if(timeArr[1]== null) {
					map.put("work_time1","");
					flag = false;
				}else {
					map.put("work_time1", sdf1.format(timeArr[1])); //퇴근시간
				}
				if(flag == true) {
					long time = (timeArr[1].getTime() -timeArr[0].getTime())/1000; //총근무시간
					long hour = time / (60 * 60); // 시간
					long minute = (time % (60 * 60)) / 60; // 분
					long second = (time % (60 * 60)) % 60; // 초
					
					if(hour < 10) {
						map.put("work_time", "0" + hour + ":" + minute + ":" + second);//총근무시간
					}
					if(minute < 10) {
						map.put("work_time", hour + ":" + "0" + minute + ":" + second);//총근무시간
					}
					if(second < 10) {
						map.put("work_time", hour + ":" + minute + ":" + "0" + second);//총근무시간
					}
				}else {
					map.put("work_time", "");
				}

				map.put("work_content", work_content);//상세정보
				
				result.add(map);
				
				work_content = "";//상세정보
				
				timeArr[0] = null;
				timeArr[1] = null;
			}

			work_date = new_work_date; //날짜
			if(new_work_type.equals("1")) { //퇴근
				timeArr[1] = p.getWork_date();
			}else if(new_work_type.equals("0")) { //출근
				timeArr[0] = p.getWork_date();
			}else {
				String type = "";
			
				if(new_work_type.equals("2")) {
					type = "외근";
				}else if(new_work_type.equals("3")) {
					type = "출장";
				}else if(new_work_type.equals("4")) {
					type = "휴가";
				}
				work_content += type + "(" + sdf1.format(p.getWork_date()) +")-" + p.getWork_content() + ", ";
			}
			i++;
		}
		boolean flag = true; //출퇴근여부
		Map<String, String> map = new HashMap<>();
		map.put("work_date", work_date); //날짜
		if(timeArr[0] == null) {
			map.put("work_time0", "");
			flag = false;
			
		}else {
			map.put("work_time0", sdf1.format(timeArr[0])); //출근시간
			
		}
		if(timeArr[1]== null) {
			map.put("work_time1","");
			flag = false;
		}else {
			map.put("work_time1", sdf1.format(timeArr[1])); //퇴근시간
			
		}
		if(flag == true) {
			long time = (timeArr[1].getTime() -timeArr[0].getTime())/1000; //총근무시간
			long hour = time / (60 * 60); // 시간
			long minute = (time % (60 * 60)) / 60; // 분
			long second = (time % (60 * 60)) % 60; // 초
			
			if(hour < 10) {
				map.put("work_time", "0" + hour + ":" + minute + ":" + second);//총근무시간
			}
			if(minute < 10) {
				map.put("work_time", hour + ":" + "0" + minute + ":" + second);//총근무시간
			}
			if(second < 10) {
				map.put("work_time", hour + ":" + minute + ":" + "0" + second);//총근무시간
			}
		}else {
			map.put("work_time", "");
		}

		map.put("work_content", work_content);//상세정보
		
		result.add(map);
		//------------------------------
		
		
		//System.out.println(result);
		return result;
	}
}
