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
  <title>admin.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
  <script>
  var loadEmpList = function(data){
	  $("section").empty();
	  $("section").html();
  }
  
  var loadInfoMenu = function(u, callback){
	  $.ajax({
		  url : u,
		  method: 'get',
		  success : function(data){
			  callback(data);
		  }
	  });
	  return false;
  };
  
  $(function(){	
		var $menuArr = $("header>nav>ul>li>a");
		$menuArr.click(function(){		
		  var url = $(this).attr('href');
		  switch(url){
		  case '${pageContext.request.contextPath}/employeeList':
			  loadMenu(url, loadBoardList);
			  break;
		  case '${pageContext.request.contextPath}/jq/display.html':
			  loadMenu(url, function(data){
				$("section").empty();
				$("section").html(data);
			  });
		  }
		  return false; //기본이벤트핸들러 막기, //이벤트전달 중지
		});
	});
  
  </script>
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
  <!-- -->
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
        <nav class="side_menu">
          <ul>
          	<li class="info">
              <i class="fas fa-users"></i>
          	  <a href="${pageContext.request.contextPath}/employeeList">계정 목록</a>
          	</li>
          	<li class="info">
          	  <i class="fas fa-user-plus"></i>
          	  <a href="${pageContext.request.contextPath}/view/Employee/employee.jsp">계정 추가</a>
          	</li>
          </ul>
        </nav>        
      </div> 
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div class="body_content">
        <!-- 아래부터 작성하면됨 -->
      	<jsp:include page="/view/Employee/employee.jsp"/>
      	<!--  -->
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->
</body>
</html>
