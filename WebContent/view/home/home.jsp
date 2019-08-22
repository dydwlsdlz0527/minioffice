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
  function prevCalendar() {
	  today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
	  buildCalendar();
  }
  
  function nextCalendar() {
	  today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
	  buildCalendar();
  }
  
  function buildCalendar() {
	  var doMonth = new Date(today.getFullYear(), today.getMonth(), 1);
	  var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
	  var tbCalendar = document.getElementById("calendar");
	  var tbCalenderYM = document.getElementById("tbCalendarYM");
	  tbCalendarYM.innerHTML = today.getFullYear() + ". " + (today.getMonth() + 1);
	  while (tbCalendar.rows.length > 2) {
		tbCalendar.deleteRow($(this).parents("calendar").rows.length - 1);
	  }
	  var row = null;
	  row = tbCalendar.insertRow();
	  var cnt = 0;
	  for (i = 0; i < doMonth.getDay(); i++) {
		cell = row.insertCell();
		cnt = cnt + 1;
	  }
	  
	  for (i = 1; i < lastDate.getDate(); i++) {
		cell = row.insertCell();
		cell.innerHTML = i;
		cnt = cnt + 1;
		if (cnt % 7 == 1) {
			cell.innerHTML = "<font color = #F79DC2>" + i; 
		}
		if (cnt % 7 == 0) {
			cell.innerHTML = "<font color = #4373CE>" + i;
			row = calendar.insertRow();
		}
		if (today.getFullYear() == date.getFullYear()
				&& today.getMonth() == date.getMonth()
				&& i == date.getDate()) {
			cell.bgColor = "#B7B7B7";
		}
	}
  }
  
  $(function(){
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
  	        <div class="calendar-column"><span>0</span></div>
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
      </div>
      <!-- calendar_wrap finish -->
    </div>
    <!-- body_wrap finish -->
  </div>  
  <!-- wrap finish -->
</body>
</html>
