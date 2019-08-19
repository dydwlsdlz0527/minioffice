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
  <title>boardhome.jsp</title>
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
            <li class="menu-item active">
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
        <section class="side_title">
          <h1>
            <a href="${contextPath}/view/board/boardhome.jsp" class="title_home">게시판</a>
          </h1>
        </section>
        <section class="function">
          <a href="#" class="btn_function">
            <i class="fas fa-file-alt"></i>
            <span class="txt">글쓰기</span>
          </a>
        </section>
        <!--게시판-->
        <section class="companySide">
          <h1 class="company">
            <i class="fas fa-building"></i>
            <a href="#" class="txt">전사게시판</a>
          </h1>
          <ul class="board_list">
            <li class="board">
              <i class="fas fa-list-alt"></i>
              <a href="#" class="txt">전사 공지</a>
            </li>
            <li class="board">
              <i class="fas fa-list-alt"></i>
              <a href="#" class="txt">전사게시판</a>
            </li>
          </ul>
        </section>
        <section class="deptSide">
          <h1 class="org">
            <i class="fas fa-sitemap"></i>
            <a href="#" class="txt">부서게시판</a>
          </h1>
          <ul class="board_list">
            <li class="board">
              <i class="far fa-list-alt"></i>
              <a href="#" class="txt">부서 공지</a>
            </li>
            <li class="board">
              <i class="far fa-list-alt"></i>
              <a href="#" class="txt">부서게시판</a>
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
