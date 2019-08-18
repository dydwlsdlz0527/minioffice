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
  <title>approvalhome.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
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
            <li class="menu-item active">
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
        <section class="my_info">
          <ul class="ctrl">
            <li class="notification">
              <a href="#" class="btn_noti"><i class="fas fa-bell"></i></a>
            </li>
            <li class="config">
              <a href="#" class="btn_config"><i class="fas fa-cog"></i></a>
            </li>
            <li class="logout">
              <a href="#" class="btn_logout"
                ><i class="fas fa-power-off"></i
              ></a>
            </li>
            <li class="photo">
              <a href="#">
                <!-- 로그인한사람의 프로필사진을 불러와야함. 미완성 -->
                <img
                  class="profile_photo"
                  src="https://pbs.twimg.com/profile_images/3394470712/66b049da64127e4ad9159684fdcdf20d.jpeg"
                />
                <!--  -->
              </a>
            </li>
          </ul>
        </section>
      </div>
    </header>
    <!-- header finish -->
    <div class="body_wrap">
      <!--left body_side -->
      <div class="body_side">
        <section class="side_title">
          <h1>
            <a href="${contextPath}/view/approval/approvalhome.jsp" class="title_home">전자결재</a>
          </h1>
        </section>
        <section class="function">
          <a href="#" class="btn_function">
            <i class="fas fa-file-medical"></i>
            <span class="txt">새 결재 진행</span>
          </a>
        </section>
        <!--결재관리 리스트-->
        <section class="apprSide">
          <h1 class="approval_title">
            <i class="fas fa-file-export"></i>
            <a href="#" class="txt">결재하기</a>
          </h1>
          <ul class="approval_list">
            <li class="approval">
              <i class="fas fa-clock"></i>
              <a href="#" class="txt">결재 대기 문서</a>
            </li>
            <li class="approval">
              <i class="fas fa-file-import"></i>
              <a href="#" class="txt">결재 수신 문서</a>
            </li>
            <li class="approval">
              <i class="fas fa-pause-circle"></i>
              <a href="#" class="txt">참조/열람 대기 문서</a>
            </li>
            <li class="approval">
              <i class="fas fa-hourglass-half"></i>
              <a href="#" class="txt">결재 예정 문서</a>
            </li>
          </ul>
        </section>
      </div>
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div class="body_content">
        <!-- 아래부터 작성하면됨 -->
      	 너가 꾸밀 영역
      	<!--  -->
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->
</body>
</html>
