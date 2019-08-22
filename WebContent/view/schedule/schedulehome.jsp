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
  <title>schedulehome.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
  <!-- -->
<link href='fullcalendar/packages/core/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/daygrid/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/timegrid/main.css' rel='stylesheet' />
<link href='fullcalendar/packages/timeline/main.css' rel='stylesheet' />


<script src='fullcalendar/packages/core/main.js'></script>
<script src='fullcalendar/packages/daygrid/main.js'></script>
<script src='fullcalendar/packages/interaction/main.js'></script>
<script src='fullcalendar/packages/timegrid/main.js'></script>
<script src='fullcalendar/packages/timeline/main.js'></script>
<script src='fullcalendar/packages/google-calendar/main.js'></script>
  
 <script type="text/javascript" src="jquery.bpopup.min.js"></script>
  
 <script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'interaction', 'dayGrid', 'timeGrid', 'googleCalendar' ], // an array of strings!
			// locale: 'ko',
			selectable : true,
			selectHelper : true,
			
			editable : true, // enable draggable events
			aspectRatio : 1.8,
			scrollTime : '00:00', // undo default 6am scrollTime
			header : {
				left : 'today prev,next',
				center : 'title',
				right : 'timeGridWeek, dayGridMonth'
			},

			defaultView : 'dayGridMonth', // 달력형식으로 보여주기
			datesAboveResources:true,
			googleCalendarApiKey : "AIzaSyARBfuXNjBGq3KsEO4RaKQzHXz60-fR3qA",      
			
				// Google API KEY

	            // 예제소스에 적힌 구글캘린더 API 키는 FullCalendar 예제에 있는 API키를 그대로 사용한 것이다.

	        eventSources : [
	                // 대한민국의 공휴일
	                {
	                    googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
	                    className : "koHolidays",
	                    color : "#FF0000",
	                    textColor : "#FFFFFF",
	                    
	                    
	                    
	                }  ,
	                {
	    				url : '${contextPath}/scheduleservice', // url로 일정 받음
	    				//color: 'red',				// 일정 색상 지정
	    				
	    				error : function() {
	    					$('#script-warning').show();
	    				},
	    				
	    			/* 	success : function(data) {
	    					
	    					
	    					$.each(data, function(idx, val) {
	    						console.log(idx + " " + val.start);
	    					});
	    					
	    					
	    				 var result = data.jaon;
	    					
	    					alert("성공 result : " + result);
	    					
	    					alert("성공" + JSON.stringify(data));
	    					
	    					console.log("data.id" + JSON.stringify(data)); 
	    					
	    				}  */
	    				
	                }

	                ],
	                
		 /* 	events : {
				url : '${pageContext.request.contextPath}/scheduleservice', // url로 일정 받음
				//color: 'red',				// 일정 색상 지정
				
				error : function() {
					$('#script-warning').show();
				}
			},  
		
			*/
			select : function(arg) { // 범위 선택
				console.log('select callback', arg.startStr, arg.endStr,
						arg.resource ? arg.resource.id : '(no resource)');
				addSchedule2(arg.startStr, arg.endStr); // 일정 추가
				
				alert("select");

			},

			eventClick : function(info) { // 일정 이벤트 클릭시 확인

				//console.log("setdate = " + setDate(info.event.start));
				var start = setDate(info.event.start);
				var end = setDate(info.event.end);
				var groupId = info.event.groupId;
				
				switch(groupId)
				{
				case "1": groupId="개인일정"; break;
				case "2": groupId="부서일정"; break;
				case "3": groupId="전체일정"; break;
				}
				
				var title_place_content = info.event.title.split(" - ");
				var title = title_place_content[0];
				var content = title_place_content[1]; 
				var place = title_place_content[2];
				
				/* alert('ID: ' + info.event.id);
				alert('Event: ' + info.event.title);
				alert('start: ' + info.event.start);
				alert('end: ' + info.event.end);
				alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
				alert('View: ' + info.view.type); */

				showClickEvent(info.event.id, info.event.title, start, end, groupId, content, place); // b팝업으로 일정 내용을 보여줌
				// change the border color just for fun
				info.el.style.borderColor = 'red'; // 눌러본 이벤트 바깥 색상 지정
			}

		/* ,
		 dateClick: function(arg) {		// 날짜만 선택
		   console.log(
		     'dateClick',
		     arg.date,
		     arg.resource ? arg.resource.id : '(no resource)'
		   );
		   addSchedule();			// 일정 추가
		   alert("dateclick");
		 }
		 */
		});

		calendar.render();
		
		function setDate(date)					// 날짜 포멧 설정
		{
			var str = calendar.formatDate(date, {
			    
			    year: 'numeric',
			    month: '2-digit',
			    day: '2-digit'
			    
			  }
			
			);
			
			console.log(str);
	
			return str;
		}

	});

	function addSchedule(calendar) { // 일정관리 눌럿을때 일정 삽입구문

		alert("일정관리 눌림");
		var htmlContents = "";

		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 명칭</div><div style='width:60%; float:right'><input type='text' id='schedule_subject' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 장소</div><div style='width:60%; float:right'><input type='text' id='schedule_place' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 구분</div><div style='width:60%; float:right'><select name='schedule_type'><option value =''>선택</option><option value ='1'>개인일정</option><option value ='2'>부서일정</option><option value ='3'>전체일정</option></select></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_start' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_end' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 내용</div><div style='width:60%; float:right'><input type='text' id='schedule_content' style='height:200px value='' > </div></div>";

		htmlContents += "<div style = 'width:100%; text-align:center; height:30px; margin-bottom:15px'><button onclick=javascript:saveSchedule();>저장</button>  <button onclick=javascript:closeMessage('winAlert');>취소</button></div>";
		openPopup("일정 등록", htmlContents, 400);
	}

	function addSchedule2(startStr, endStr) { // 일정관리 기간을 눌럿을때 일정 삽입구문

		alert("일정관리 눌림");
		var htmlContents = "";

		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 명칭</div><div style='width:60%; float:right'><input type='text' id='schedule_subject' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 장소</div><div style='width:60%; float:right'><input type='text' id='schedule_place' value='' > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 구분</div><div style='width:60%; float:right'><select name='schedule_type'><option value =''>선택</option><option value ='1'>개인일정</option><option value ='2'>부서일정</option><option value ='3'>전체일정</option></select></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_start' value= " + startStr + "> </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_end' value= "+ endStr + "> </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 내용</div><div style='width:60%; float:right'><input type='text' id='schedule_content' style='height:200px value='' > </div></div>";

		htmlContents += "<div style = 'width:100%; text-align:center; height:30px; margin-bottom:15px'><button onclick=javascript:saveSchedule();>저장</button>  <button onclick=javascript:closeMessage('winAlert');>취소</button></div>";
		
		openPopup("일정 등록", htmlContents, 400);
	}

	function showClickEvent(id, title, start, end, groupId, content, place) { // 일정 이벤트 클릭시 일정확인

		alert("일정 확인");
		var htmlContents = "";

		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 번호</div><div style='width:60%; float:right'><input type='text' id='schedule_no' readonly='readonly' value= " + id + " ></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 명칭</div><div style='width:60%; float:right'><input type='text' id='schedule_subject' readonly='readonly' value= " + title + " ></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 장소</div><div style='width:60%; float:right'><input type='text' id='schedule_place' readonly='readonly' value= " + place + " > </div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>일정 구분</div><div style='width:60%; float:right'><input type='text' id='schedule_subject' readonly='readonly' value= " + groupId + " ></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_start' readonly='readonly' value= " + start + "></div></div>";
		htmlContents += "<div style = 'width:100%; height:30px'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='width:60%; float:right'><input type='text' id='schedule_end' readonly='readonly' value= "+ end + "></div></div>";
		htmlContents += "<div style = 'width:100%; height:120px'><div style='width:30%; float:left; padding-left:30px'>일정 내용</div><div style='width:60%; float:right'> <input type='text' id='schedule_content' style='height:100px' readonly='readonly' value="+ content +" > </div></div>";
		htmlContents += "<div style = 'width:100%; height:120px'><div style='width:30%; float:left; padding-left:30px'>일정 내용</div><div style='width:60%; float:right'> <textarea rows='5' cols='30' readonly='readonly' style= 'resize:none'>" + content+" </textarea></div></div>";
		htmlContents += "<div style = 'width:100%; text-align:center; height:30px; margin-bottom:15px'><div><button onclick=javascript:closeMessage('winAlert')>확인</button>   <button onclick=javascript:deleteSchedule()>삭제</button> </div></div>";
		
		
		
		
		
		openPopup("일정 확인", htmlContents, 400); // 팝업창 열기
	}

	function openPopup(subject, contents, widths) { // 팝업창
		$("#alert_subject").html(subject); // 팝업창 title
		$("#alert_contents").html(contents); // 팝업창 html 내용
		openMessage("winAlert", widths);
		alert("openPopup");
	}

	function openMessage(IDS, widths) {
		$('#' + IDS).css("width", widths + "px");
		$('#' + IDS).bPopup();
	}

	function closeMessage(IDS) { // b팝업 닫기
		$('#' + IDS).bPopup().close();
	}
	
	function deleteSchedule()
	{
		var schedule_no = $('#schedule_no').val();
		//var schedule_ = $('#schedule_no').val();
		alert(schedule_no);
		
		$.ajax({
			
			url:'${contextPath}/scheduleservicedel',
			method:'post',
			data:"schedule_no="+schedule_no,
			success:function(data)
			{
				console.log("con_data : " + data);

				var jsonObj = JSON.parse(data);

				console.log(" delete - con_jsonObj : " + jsonObj);
				console.log(" delete - con_jsonObj.status : " + jsonObj.status);

				if (jsonObj.status == "1") {

					alert("정상 삭제 되었습니다.");
					closeMessage("winAlert");
				

					//	$('#calendar').fullCalendar('refetchEvents'); // 새로고침임

				} else {
					alert("삭제 실패 되었습니다.");
				}
				
			}
		
		});
		
		window.location.reload(); // 새로고침임
	}
	
	
	

	function saveSchedule() { // 일정 버튼 & 일정 클릭 시 데이터 저장

		var schedule_subject = $('#schedule_subject').val(); // 제목
		var schedule_place = $('#schedule_place').val(); // 장소
		var schedule_type = $("select[name=schedule_type]").val(); // 일정 타입
		var schedule_start = $('#schedule_start').val(); // 시작 일정
		var schedule_end = $('#schedule_end').val(); // 마감 일정
		var schedule_content = $('#schedule_content').val(); // 일정 내용

		console.log(schedule_type);

		//var calendar_title = $('#calendar_title').val();
		//var calendar_start_date = $('#calendar_start_date').val();
		//var calendar_end_date = $('#calendar_end_date').val();

		//alert(calendar_title + calendar_start_date + calendar_end_date);

		if (!schedule_subject) {
			alert("일정 입력 해");
			return false;

		}
		if (!schedule_place) {
			alert("장소 입력 해");
			return false;

		}

		if (!schedule_type) {
			alert("구분 선택 해");
			return false;

		}

		if (!schedule_start) {
			alert("시작 날짜 입력 해 ");
			return false;

		}

		if (!schedule_end) {
			alert("마감 날짜 입력 해");
			return false;

		}

		if (!schedule_content) {
			alert("일정 내용 입력 해");
			return false;

		}

		alert(schedule_subject + " " + schedule_place + " " + schedule_start
				+ " " + schedule_end + " " + schedule_content);

		$.ajax({

			url : '${contextPath}/schedulecon', // 서브렛에 넘겨줘야함
			method : 'post',
			data : 	'schedule_subject=' + schedule_subject + 
					'&schedule_start='	+ schedule_start + 
					'&schedule_type=' 	+ schedule_type + 
					'&schedule_end=' 	+ schedule_end + 
					'&schedule_content='+ schedule_content + 
					'&schedule_place=' 	+ schedule_place,

			success : function(data) {
				console.log("con_data : " + data);

				var jsonObj = JSON.parse(data);

				console.log("con_jsonObj : " + jsonObj);
				console.log("con_jsonObj.status : " + jsonObj.status);

				if (jsonObj.status == "1") {

					closeMessage("winAlert");
					//	alert("정상 저장되었습니다.");

					//	$('#calendar').fullCalendar('refetchEvents'); // 새로고침임

				} else {
					alert("저장 실패 되었습니다.");
				}
			}

		});
		
		window.location.reload(); // 새로고침임

	}
	
	/* function showtable(key)
	{
		$.ajax({
			
			url : '${pageContext.request.contextPath}/scheduleservice', // 서브렛에 넘겨줘야함
			method : 'post',
			data : 'key='+key,

			success : function(data) 
			{
				console.log("data : " + data);
				//JSONArray jsonArr = new JSONArray(data);
				//var jsonObj = JSON.parse(data);

				console.log("con_jsonObj : " + jsonArr);
				
				
			}
			
		});
		
	} */
	
	
	
	
</script>
  
  
</head>
<body>
	<%-- <c:if test="${empty sessionScope.loginInfo}">
		<script>
			location.href="${contextPath}/index.jsp";
		</script>
	</c:if> --%>
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
            <li class="menu-item">
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
            <li class="menu-item active">
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
    <div class="body_wrap">
      <!--left body_side -->
      <div class="body_side">
        <section class="side_title">
          <h1>
            <a href="${contextPath}/view/schedule/schedulehome.jsp" class="title_home">캘린더</a>
          </h1>
        </section>
        <section class="function" onclick="javascript:addSchedule();">
          <a href="#" class="btn_function">
            <i class="fas fa-calendar-plus"></i>
            <span class="txt">일정등록</span>
          </a>
        </section>
        
        <!--일정등록 아래부분 세션남겨둠. 필요할시 section 안에 리스트작성-->
        <section>
         
        </section>
        <!--  -->
        
      </div>
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div class="body_content">
        <!-- 아래부터 작성하면됨 -->
      	
      	
   <!--   <div style="max-width: 900px; margin: 0 auto;">
		<div style="float: left; padding-left: 5px">
			<button class ="fc-button-primary" onclick="javascript:showtable(3)" >전체</button>
		</div>
		
		<div style="float: left; padding-left: 5px">
			<button class ="fc-button-primary" onclick="javascript:showtable(2)" >부서</button>
		</div>
		
		<div style="float: left; padding-left: 5px">
			<button class ="fc-button-primary" onclick="javascript:showtable(1)" >개인</button>
		</div>
		
	</div> -->
	

<!-- 	<div style="max-width: 900px; margin: 0 auto; height: 40px">
		<div style="float: right">
			<button class ="fc-button-primary" onclick="javascript:addSchedule();" >일정등록</button>
		</div>
	</div> -->

	
	<div id='calendar' style="max-width: 900px; margin: 0 auto;"></div>

	<div class="box box-success"
		style="width: 500px; display: none; background-color: white"
		id="winAlert">
		<div class="box-header with-border"
			style="background-color: white; padding-left: 15px">
			<h3 class="box-title" id="alert_subject"
				style="background-color: white"></h3>
		</div>
		<div class="box-body" id="alert_contents"
			style="font-size: 15px; background-color: white"></div>
	</div>
      	
      	
      	<!--  -->
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->
</body>
</html>
