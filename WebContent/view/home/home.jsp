<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%response.setHeader("Cache-Control", "no-cache");%>
<%response.addHeader("Cache-Control", "no-store");%>
<%response.setHeader("Pragma", "No-cache");%>
<%response.setDateHeader("Expires", 1L);%> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <title>home.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
  <link rel="stylesheet" href="${contextPath}/css/home/home_side.css"/>
  <link rel="stylesheet" href="${contextPath}/css/home/calendar_wrap.css"/>
  <!-- -->
  
  <script>
  var today = new Date(); //오늘 날짜. 내 컴퓨터 로컬 기준
  var date = new Date(); //today의 Date를 세어주는 역할
  function prevCalendar() { //이전 달
	  // 이전 달을 today에 값을 저장하고 달력에 today를 넣어줌.
	  // getMonth()는 현재 달을 받아 오므로 이전달을 출력하려면 -1을 해줘야함
	  today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
	  buildCalendar(); //달력 cell을 만들어 출력
  }
  
  function nextCalendar() { //다음 달
	  today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
	  buildCalendar();
  }
  
  function buildCalendar() { //현재 달 달력 만들기
	  var doMonth = new Date(today.getFullYear(), today.getMonth(), 1);
  	  // 이번 달의 첫째 날
  	  // new를 쓰는 이유: new를 쓰면 이번달의 로컬 월을 정확하게 받아온다.
  	  // new를 쓰지 않으면 getMonth()는 0~11을 반환하기 때문에 +1을 해줘야한다.
	  var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
  	  // 이번 달의 마지막 날
  	  // day에 0을 줬기 때문에 다음달 시작일(1일)을 가져오는 대신 전달 마지막일을 가져오게 된다.
	  var tbCalendar = document.getElementById("calendar");
  	  // 날짜를 찍을 테이블 변수 만듬, 일까지 다 찍힌다.
	  var tbCalenderYM = document.getElementById("tbCalendarYM");
  	  // 테이블에 정확한 날짜 찍는 변수
	  tbCalendarYM.innerHTML = today.getFullYear() + ". " + ("0" + (today.getMonth() + 1)).slice(-2);
  	  // innerHTML에 년.월 표시. slice(-2)는 end에서부터 2자리를 짤라서 표시.
  	  
  	  /* while은 이번달이 끝나면 다음달로 넘겨주는 역할 */
	  while (tbCalendar.rows.length > 2) {
	  // 열을 지워줌
	  // 기본 2열은 이미 정해져 있다.(년.월과 일~토요일부분)
		tbCalendar.deleteRow(tbCalendar.rows.length - 1);
	    //테이블의 tr갯수 만큼의 열 묶음을 한 칸씩 지워준다.
	  }
	  var row = null;
	  row = tbCalendar.insertRow();
	  // 테이블에 새로운 열 삽입
	  var cnt = 0; // cnt, 셀의 갯수를 세어주는 역할
	  // 1일이 시작되는 칸을 맞추어 줌
	  for (i = 0; i < doMonth.getDay(); i++) {
	  /* 이번달의 요일만큼 돌림 */
	  // doMonth.getDay() -> 이번달 첫날의 요일
		cell = row.insertCell(); // 열 한칸한칸 계속 만들어주는 역할
		cnt = cnt + 1; // 열의 갯수를 계속 다음으로 위치하게 해주는 역할
	  }
	  
	  /* 달력 출력 */
	  for (i = 1; i < lastDate.getDate(); i++) {
	  // 1일부터 마지막 일까지 돌림
		cell = row.insertCell();
	    if (today.getFullYear() + 1 < date.getFullYear() + 1 
	    		|| (today.getFullYear() + 1 == date.getFullYear() + 1 
	    				&& today.getMonth() + 1 < date.getMonth() + 1)) {
	    	// 지난해 이거나 지난달은 모두 td_number css 입히기
	    	cell.innerHTML =
	    		"<div class=\"td_date\">" + i + "</div>" + "<span class=\"td_number\">" + Math.floor(Math.random()*3+1) + "</span>";
		} else if(today.getFullYear() + 1 == date.getFullYear() + 1
				&& today.getMonth() + 1 == date.getMonth() + 1
				&& i < today.getDate()) {
			// 올해 이번달 오늘 이전 날짜는 td_number css 입히기
	    	cell.innerHTML =
	    		"<div class=\"td_date\">" + i + "</div>" + "<span class=\"td_number\">" + Math.floor(Math.random()*3+1) + "</span>";
		} else {
			// 오늘 이후 날짜는 td_number2 css 입히기
			cell.innerHTML =
				"<div class=\"td_date\">" + i + "</div>" + "<span class=\"td_number2\">" + Math.floor(Math.random()*3+1) + "</span>";
		}
		
		// 셀을 1부터 마지막 day까지 HTML 문법에 넣어줌
		cnt = cnt + 1;
		if (cnt % 7 == 1) { /* 일요일 계산 */
			cell.innerHTML = "<font color = #F79DC2>" + i; 
		}
		if (cnt % 7 == 0) { /* 토요일 계산 */
			cell.innerHTML = "<font color = #4373CE>" + i;
			row = calendar.insertRow();
			// 토요일 다음에 올 셀을 추가
		}
		/* 오늘의 날짜에 색 칠하기 */
		if (today.getFullYear() == date.getFullYear()
				&& today.getMonth() == date.getMonth()
				&& i == date.getDate()) {
			// 달력에 있는 년,달과 내 컴퓨터의 로컬 년,달이 같고, 일이 오늘의 일과 같으면
			cell.bgColor = "#B7B7B7"; // 셀의 배경색 칠함
		}
	  }
  }
  
  $(function(){
	  
	 $.ajax({
		  url: "${contextPath}/schedulelist",
		  type: 'post',
		  data: 'emp_no=' + ${sessionScope.emp_no},
		  success: function (data) {
			  var jsonObjArr = JSON.parse(data);
			  //console.log(jsonObjArr);	
			  var date = "";
			  var preStart = "";
			  var week = ['일', '월', '화', '수', '목', '금', '토'];
			  
			  for (var i = 0; i < jsonObjArr.length; i++) {
				var jsonObj = jsonObjArr[i];
				var dayOfWeek = week[new Date(jsonObj.fullDate).getDay()];
				if (preStart != jsonObj.start) {
					date += "<li><p class=\"list_title\"><span class=\"list_date\">" + jsonObj.start
					+ "</span> " + dayOfWeek + "</p>" + "<ul class=\"list_item\"></ul></li>";				
				}
				preStart = jsonObj.start;			
			  }
			  $(".calender_list").html(date);
			  
			  var cnt = 1;
			  var subject = "";
			  var today_scheduleCnt = 0;
			  for (var i = 0; i < jsonObjArr.length; i++) {
				  var jsonObj = jsonObjArr[i];
				  if ($(".calender_list>li:nth-child("+cnt+") .list_date").html() == jsonObj.start) {
					  subject += "<li><span class=\"item_subject\">" + jsonObj.subject + "</span></li>";
					  today_scheduleCnt += 1;
				  } else {
					  $(".calender_list>li:nth-child("+cnt+") .list_item").html(subject);
					  cnt += 1;
					  if (cnt == 2) {
						  $(".today_scheduleCnt").html(today_scheduleCnt);
					  }
					  subject = "";
					  subject += "<li><span class=\"item_subject\">" + jsonObj.subject + "</span></li>";
				  }
				  
				  if (jsonObjArr.length - 1 == i) {
					  $(".calender_list>li:nth-child("+cnt+") .list_item").html(subject);					
				  }
			  }			  
		  }
	 });
	  
	 $("#orgToggle").click(function(){		
		 if ($(".tab_wrap").css("display") == "none") {
			 $(".tab_wrap").show();
		} else {
			 $(".tab_wrap").hide();
		}
	 }); 
	 
  });
  
  </script>
  
</head>
<body>
  <div class="wrap">
    <!-- header -->
    <header class="header">
      <div class="header_columns">
        <i class="fab fa-modx"></i>
        <h1 class="header-logo">Mini Office</h1>
      </div>
      <div class="header_columns">
        <nav class="menu-container">
          <ul class="header-menu">
            <li class="menu-item active">
              <a class="menu-link" href="${contextPath}/view/home/home.jsp">
                <span class="menu">홈</span>
              </a>
            </li>
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/approval/approvalhome.jsp">
                <span class="menu">전자결재</span>
              </a>
            </li>
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/mail/mailhome.jsp">
                <span class="menu">메일</span>
              </a>
            </li>
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/board/boardhome.jsp">
                <span class="menu">게시판</span>
              </a>
            </li>
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/schedule/schedulehome.jsp">
                <span class="menu">캘린더</span>
              </a>
            </li>
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/punctuality/punctualityhome.jsp">
                <span class="menu">근태관리</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="header_columns">
		<jsp:include page="/view/myinfo.jsp"/>
      </div>
    </header>
    <!-- header finish -->
    <!-- body_wrap -->
    <div class="body_wrap">
      <!-- home_side -->
      <div class="home_side">
  	    <div class="profile_wrap">
  	      <div class="profile">
  	        <div class="profile-column">
  	          <img
  	           src="${contextPath}/images/profile/${sessionScope.emp_no}.jpg"
  	           onerror="this.src='${contextPath}/images/profile/default_image.jpg'">
  	        </div>
  	        <div class="profile-column">
    	      <div class="profile-info">
  	            <span class="name">${sessionScope.emp_name}</span>
  	            <span>${sessionScope.dept_name}</span>
  	            <span>${sessionScope.rank_name}</span>
  	          </div>
  	        </div>  		    	    
  	      </div>
  	      <div class="summary-approval">
    	    <div class="approval-column"><span>결재할 문서</span></div>
  	        <div class="approval-column"><span>0</span></div>
  	      </div>
  	      <div class="summary-calendar">
  	        <div class="calendar-column"><span>오늘의 일정</span></div>
  	        <div class="calendar-column"><span class="today_scheduleCnt">0</span></div>
  	      </div>
  	    </div>
  	    <div class="organogram">
  	      <div class="search_wrap">
  	        <div class="search-column">
  	          <form name="orgSearch" onsubmit="return false;">
  	            <input class="search" type="text" placeholder="이름/아이디/부서/직위/직책">
  	          </form>
  	        </div>
  	        <div class="search-column">
  	          <i id="orgToggle" class="fas fa-chevron-down"></i>
  	        </div>
  	      </div>
  	      <div class="tab_wrap">
    	    <div class="content_tab_wrap">
    	      <div class="jstree">여기에 조직도 트리</div>
  	        </div>
  	      </div>
  	    </div>
      </div>
      <!-- home_side finish -->
      <!-- calendar_wrap -->
      <div class="calendar_wrap">
        <div class="layer_calendar">
          <table id="calendar">
            <tr>
              <td><i onclick="prevCalendar()" class="fas fa-chevron-left"></i></td>
              <td align="center" id="tbCalendarYM" colspan="5"></td>
              <td><i onclick="nextCalendar()" class="fas fa-chevron-right"></i></td>
            </tr>
            <tr>
              <td><font color = "#F79DC2">일</font></td>
              <td>월</td>
              <td>화</td>
              <td>수</td>
              <td>목</td>
              <td>금</td>
              <td><font color = "#4373CE">토</font></td>
            </tr>
          </table>
          <script type="text/javascript">
          	buildCalendar();
          </script>
        </div>
        <div class="calender_header">
          <span class="claender_title">캘린더</span>
        </div>
        <ul class="calender_list">
          <!-- 오늘의 일정 구조 예제. 화면 불러오면서 새로 고쳐짐 -->
          <li>
            <p class="list_title">
              <span class="list_date">8.22 목</span>
            </p>
            <ul class="list_item">
	          <li>
	            <span class="item_subject">팀장회의</span>
	          </li>
	          <li>
	            <span class="item_subject">스킬업특강</span>
	          </li>
            </ul>
          </li>
          <li>
            <p class="list_title">
              <span class="list_date">8.23 금</span>
            </p>
            <ul class="list_item">
	          <li>
	            <span class="item_subject">팀장회의</span>
	          </li>
            </ul>
          </li>
          <li>
            <p class="list_title">
              <span class="list_date">8.24 토</span>
            </p>
            <ul class="list_item">
              <li>
	            <p class="list_desc">등록된 일정이 없습니다.</p>
	          </li>
            </ul>
          </li>
          <!-- 예제 끝 -->
        </ul>
      </div>
      <!-- calendar_wrap finish -->
    </div>
    <!-- body_wrap finish -->
  </div>  
  <!-- wrap finish -->
</body>
</html>
