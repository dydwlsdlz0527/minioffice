<%@page import="com.minioffice.vo.Punctuality"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%response.setHeader("Cache-Control", "no-cache");%>
<%response.addHeader("Cache-Control", "no-store");%>
<%response.setHeader("Pragma", "No-cache");%>
<%response.setDateHeader("Expires", 1L);%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 (E)");
	
	Punctuality p = new Punctuality();
	
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>punctualityhome.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
  <!-- -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="${contextPath}/css/punctuality/jquery-ui.min.css" type="text/css">
	<script type="text/javascript" src="${contextPath}/js/punctuality/jquery-ui.min.js"></script>
<style>
.modal-body > table > tbody > tr > td {
	border: 1px solid;
}

a,a:hover {
	color : inherit;
	text-decoration: none;
}   
   
ul {
	margin: 0;
}
   
.header_columns .header-logo {
	font-size: 24px;
	margin:0;
}
</style> 
<script>
  function printClock() {
	    
	    var clock = document.getElementById("clock");            // 출력할 장소 선택
	    var currentDate = new Date();                                     // 현재시간
	    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
	    var amPm = 'AM'; // 초기값 AM
	    var currentHours = addZeros(currentDate.getHours(),2); 
	    var currentMinute = addZeros(currentDate.getMinutes() ,2);
	    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
	    
	    if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
	    	amPm = 'PM';
	    	currentHours = addZeros(currentHours - 12,2);
	    }

	    if(currentSeconds >= 50){// 50초 이상일 때 색을 변환해 준다.
	       currentSeconds = '<span style="color:#de1951;">'+currentSeconds+'</span>'
	    }
	    clock.innerHTML = currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:30px;'>"+ amPm+"</span>"; //날짜를 출력해 줌
	    
	    setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
	}

	function addZeros(num, digit) { // 자릿수 맞춰주기
		  var zero = '';
		  num = num.toString();
		  if (num.length < digit) {
		    for (i = 0; i < digit - num.length; i++) {
		      zero += '0';
		    }
		  }
		  return zero + num;
	}

$(function(){
	$("#work_start").click(function(){
		console.log("<%=session.getAttribute("emp_no")%>");
		$("#work_start").prop("disabled", true);
		$("#work_end").show();
	});

	$("#work_end").click(function(){
		$("#work_start").prop("disabled", false);
	});

	$("#myButtons1").click(function(){
	    alert('AJAX로 처리하고 정상 응답이면.. hide 해 준다.');
			$('#myModal').modal('hide');
	});
	
	$("#work_start").click(function(){
		$.ajax({
			url: '${contextPath}/work_start',
			type: 'post',
			data: 'emp_no=' + <%=session.getAttribute("emp_no")%>,
			success:function(data) {
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.emp_no + "번 출근";
				if(jsonObj.status == 1) {
					msg += "성공";
					alert(msg);
				} else {
					msg += "실패";
					alert(msg);
				}
			}
		});
		return false;
	});
	
	$("#work_end").click(function(){
		$.ajax({
			url: '${contextPath}/work_end',
			type: 'post',
			data: 'emp_no=' + <%=session.getAttribute("emp_no")%>,
			success:function(data) {
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.emp_no + "번 퇴근";
				if(jsonObj.status == 1) {
					msg += "성공";
					alert(msg);
				} else {
					msg += "실패";
					alert(msg);
				}
			}
		});
		return false;
	});
	
	$("#work_type_save").click(function(){
		$.ajax({
			url: '${contextPath}/work_type',
			type: 'post',
			data: 'emp_no=' + <%=session.getAttribute("emp_no")%> + 
					'&work_type=' + $("select[name=work_type]").val() + 
					'&work_content=' + $("textarea[name=work_content]").val(),
			success: function(data){
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.emp_no + "번 상태등록";
				if(jsonObj.status == 1) {
					msg += "성공";
					alert(msg);
					$("#myModal").modal("hide");
				} else {
					msg += "실패";
					alert(msg);
				}
			}
		});
	});
});
	

</script>
</head>
<body onload="printClock()">
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
            <li class="menu-item">
              <a class="menu-link" href="${contextPath}/view/schedule/schedulehome.jsp">
                <span class="menu">캘린더</span>
              </a>
            </li>
            <li class="menu-item active">
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
            <a href="${contextPath}/view/punctuality/punctualityhome.jsp" class="title_home">근태관리</a>
          </h1>
        </section>
        
        <!-- 근태관리 메인화면 들어갈 부분 -->
        <section>
        	<div class="newapproval_write" style="width:100%; display:block; text-align:center; font-size:20px;">
			<%= sf.format(nowTime) %><br>
				<%-- <p style="color:#666; text-align:center; font-size: 42px; color:#5e5e5e;" id="clock"></p>--%>
				<div style="border:1px solid #dedede; width:248px; height:95px; line-height:90px; color:#666;font-size:30px; text-align:center;" id="clock">
				</div>
				<button id="work_start" type="button" class="btn btn-primary">출근하기</button>
				<button id="work_end" type="button" class="btn btn-primary">퇴근하기</button><br>
				<button id="work_type" class="btn btn btn-success" data-toggle="modal" data-target="#myModal" style="margin-top: 10px;">상태등록</button>
			</div>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog"><!--  큰창:<div class="modal-dialog modal-lg"> 작은창 :<div class="modal-dialog modal-sm">  -->
			      <div class="modal-content">
			         <div class="modal-header">
			         <h4 class="modal-title" id="myModalLabel">업무상태 등록</h4>
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			         </div>
			         <div class="modal-body">
			         	<table>
			         		<tr>
			         			<td>날짜</td>
			         			<td><%= sf.format(nowTime) %></td>
			         		</tr>
			         		<tr>
			         			<td>상태</td>
			         			<td>
			         				<select name="work_type">
					         			<option value="0">출근</option>
					         			<option value="1">퇴근</option>
					         			<option value="2">외근</option>
					         			<option value="3">출장</option>
					         			<option value="4">휴가</option>
			         				</select>
			         			</td>
			         		</tr>
			         		<tr>
			         			<td>내용</td>
			         			<td><textarea name="work_content" cols="50" rows="8" placeholder="내용을 입력하세요."></textarea></td>
			         		</tr>
			         	</table>
			         </div>
			         <div class="modal-footer">
			            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			            <button type="button" class="btn btn-primary" id="work_type_save">저장</button>
			         </div>
			      </div> 
			   </div> 
			</div>
        </section>
        <!--  -->
        
        <!--근태관리 리스트부분 세션남겨둠. 필요할시 section 안에 리스트작성-->
        <section>
         
        </section>
        <!--  -->
        
      </div>
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div class="body_content">
        <!-- 아래부터 작성하면됨 -->
      	 너가 꾸밀 영역 
      	 <div><%=session.getAttribute("emp_no") %></div>
      	 <div>
      	 	<table>
      	 		<colgroup>
      	 		<col width="30%">
      	 		<col width="30%">
      	 		<col width="30%">
      	 		<col width="30%">
      	 		</colgroup>
      	 		<tr>
      	 			<th>일자</th>
      	 			<th>날짜</th>
      	 			<th>타입</th>
      	 			<th>내용</th>
      	 		</tr>
      	 		<tr>
      	 			<td>19/08/21</td>
      	 			<td>08:50</td>
      	 			<td>출근</td>
      	 			<td>정상</td>
      	 		</tr>
      	 	</table>
      	 </div>
      	 <%-- <div>${sessionScope.emp_no}</div> --%>
      	<!--  -->
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->
</body>
</html>
