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
  </div>
  <div class="body_wrap">
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
  </div>
  </div>
  <!-- wrap finish -->
</body>
</html>
