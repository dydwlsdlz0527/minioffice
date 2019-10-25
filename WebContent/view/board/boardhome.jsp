<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%response.setHeader("Cache-Control", "no-cache");%>
<%response.addHeader("Cache-Control", "no-store");%>
<%response.setHeader("Pragma", "No-cache");%>
<%response.setDateHeader("Expires", 1L);%> 
<%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>boardhome.jsp</title>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
  <link rel="stylesheet" href="${contextPath}/css/board/board.css"/>
  <!-- -->
</head>
<style>
.body_head{
width:100%;
height:63px;
background-color: #f3f3f3;
border-bottom-style: solid;
border-bottom-width: 1px;
border-color: #b8b9c0;
padding: 17px 15px;
font-size: 20px;
border-bottom: 1px solid #b8b9c0;
box-shadow: 0 0 2px rgba(37, 37, 37, 0.2);
}
.body_head>span{
padding-left:20px;
}
.selectbox{
display:inline-block;
padding: 2px;

}

.body_head{
display: flex;
justify-content: space-between;
align-items: center;
}

.body_head-column:nth-child(2) {
display: flex;
justify-content: space-evenly;
align-items: center;
}
.li_style{
list-style: square;
}
.board_info{
width : 98%;
height: 100px;
background-color: #f3f3f3;
margin-left: auto;
margin-right: auto;
margin-top : 10px;
margin-bottom : 10px;
border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px; 
}
.board_info_ul{
padding-top: 23px;
padding-left: 30px;

}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>

<script type="text/javascript" src="${contextPath }/js/board/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	$(function(){
		
		
		
		
 		var $write_bt = $("#bt")
		$write_bt.click(function(){
		
			$.ajax({
				
				url:'${contextPath}/js/board/boardwrite.jsp',
				method:'GET',
				success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
					$(".body_head-column>span:first-child").html("게시판글쓰기");
				}
			}); 
		}); 

		var $allnotice = $("#allnotice");
		$allnotice.click(function(){
			$.ajax({
				url:'${contextPath}/board',
				method:'GET',
				data:'type=a&currentPage=1',
				success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
					$(".body_head-column>span:first-child").html("전사공지");
				}
			});
			
		}); 
		
		var $freeboard = $("#freeboard");
		$freeboard.click(function(){
			$.ajax({
				url:'${contextPath}/board',
				method:'GET',
				data:'type=b&currentPage=1',
				success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
					$(".body_head-column>span:first-child").html("전사게시판");
				}
			});
			
		});
		
		var $deptnotice = $("#deptnotice");
		$deptnotice.click(function(){
			$.ajax({
				url:'${contextPath}/deptboard',
				method:'GET',
				data:'type=c&currentPage=1',
				success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
					$(".body_head-column>span:first-child").html("부서공지");
				}
			});
			
		});
		
		var $deptboard = $("#deptboard");
		$deptboard.click(function(){
			$.ajax({
				url:'${contextPath}/deptboard',
				method:'GET',
				data:'type=d&currentPage=1',
				success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
					$(".body_head-column>span:first-child").html("부서게시판");
				}
			});
			
		});

	});
</script>
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
        <section class="function" id="bt">
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
            <li class="board" id="allnotice">
              <i class="fas fa-list-alt"></i>
              <a href="#" class="txt">전사 공지</a>
            </li>
            <li class="board" id="freeboard">
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
            <li class="board" id="deptnotice">
              <i class="far fa-list-alt"></i>
              <a href="#" class="txt">부서 공지</a>
            </li>
            <li class="board" id="deptboard">
              <i class="far fa-list-alt"></i>
              <a href="#" class="txt">부서게시판</a>
            </li>
          </ul>
        </section>
      </div>
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div style="width:100%; background-color: white">
        <div class="body_head">
          <div class="body_head-column">
          	<span>게시판 홈</span>
          </div>
          <div class="body_head-column">
          	<select class="selectbox">
            	<option>게시판선택</option>
            	<option>전사공지</option>
            	<option>전사게시판</option>
            	<option>부서공지</option>
            	<option>부서게시판</option>
          	</select>          
          	<input type="text" placeholder="검색">
          	<span class="search_button mouse"></span>
          </div>
        </div>
        <div class="body_content">
        <!-- 아래부터 작성하면됨 -->	
          <div id="home_notice">
          <!-- 최신공지글 --><!-- 넘기면 부서공지 -->
          
          </div>
          
          <div id="home_board">
          <!-- 최신전사--><!-- 넘기면 부서게시글 -->
          </div>
      	 
      	<!--  -->
        </div>
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->	
</body>
</html>
