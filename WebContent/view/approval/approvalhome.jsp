<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%response.setHeader("Cache-Control", "no-cache");%>
<%response.addHeader("Cache-Control", "no-store");%>
<%response.setHeader("Pragma", "No-cache");%>
<%response.setDateHeader("Expires", 1L);%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userid" value="${sessionScope.emp_id}" />
<c:set var="userno" value="${sessionScope.emp_no}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>approvalhome.jsp</title>
<!-- home 화면에 필요한 icon과 스타일 불러오기 -->
<script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
<link rel="stylesheet" href="${contextPath}/css/styles.css" />
<!-- bootstrap & jquery -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- jQuery user interface -->
<script
  src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
  integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
  crossorigin="anonymous"></script>
<%-- jstree --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.8/jstree.min.js"></script>
<%-- smartEditor --%>
<script src="${contextPath}/demo/js/service/HuskyEZCreator.js"
	charset="utf-8"></script>
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
 <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
 <link rel="stylesheet" href="${contextPath}/css/styles.css" />
 <style>
#btcollapse {
	border-color: rgba(0, 0, 0, 0);
	background-color: rgba(0, 0, 0, 0);
	color: black;
	padding: 5px;
}
#btcollapse2{
	border-color: rgba(0, 0, 0, 0);
	background-color: rgba(0, 0, 0, 0);
	color: black;
	padding: 5px;
}
a, a:hover {
	color: inherit;
	text-decoration: none;
}

ul {
	margin: 0;
}

h1 {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

#jstree {
	height: 400px;
	overflow: auto;
}
.body_content{
	width: 100%;
}
</style>
<script>
	$(function() {
		
		$('#btcollapse').click(function() {
			var dropImg = $('#btcollapse i');
			if (dropImg.html() == 'expand_more') {
				dropImg.html('expand_less');
			} else {
				dropImg.html('expand_more');
			}
		});
		
		$('#btcollapse2').click(function() {
			var dropImg2 = $('#btcollapse2 i');
			if (dropImg2.html() == 'expand_more') {
				dropImg2.html('expand_less');
			} else {
				dropImg2.html('expand_more');
			}
		});

		var viewBoard = function(){
			$.ajax({
				url : '${contextPath}/approvalWaitBoard',
				method : 'GET',
				data : "empno="+'${userno}',
				success : function(data){
					$(".body_content").empty();
					$(".body_content").html(data);
				}
			});
		}
		viewBoard();
		
		var $btnewDoc = $('#btnewDoc');
		$btnewDoc.click(function() {
			$.ajax({
				url : '${contextPath}/view/approval/documentModal.jsp',
				method : 'post',
				success : function(data) {
					$('#docModal').html(data);
					$('#docModal').modal('show');
				}
			});
		});
		
		var loadBoard = function(data){
			$(".body_content").empty();
			$(".body_content").html(data);
		}
		
		var loadView = function(u, callback){
			$.ajax({
				url : u,
				method : 'GET',
				data : "empno="+'${userno}',
				success : function(data){
					callback(data);
				}
			});
		};
		
		var $asideArr1 = $("#collapseArea li.approval a");
		$asideArr1.click(function(){
			var url = $(this).attr('href');
			loadView(url,loadBoard);		
			return false;
		});

		var $asideArr2 = $("#collapseArea2 li.approval a");
		$asideArr2.click(function(){
			var url2 = $(this).attr('href');
			loadView(url2,loadBoard);
			return false;
		});
	});
</script>
</head>
<body style="overflow: auto;">
	<!-- 세션 없으면 인덱스로 -->
  <c:if test="${empty sessionScope.emp_id}">
	  <script>
		  location.href="${contextPath}/index.jsp";
	  </script>
  </c:if>
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
        <jsp:include page="/view/myinfo.jsp"/>
      </div>
    </header>
		<!-- header finish -->
		<div class="body_wrap">
			<!--left body_side -->
			<div class="body_side">
				<section class="side_title">
					<h1>
						<a href="${contextPath}/view/approval/approvalhome.jsp"
							class="title_home">전자결재</a>
					</h1>
				</section>
				<section class="function">
					<a href="javascript:void(0);" class="btn_function" id="btnewDoc">
						<i class="fas fa-file-medical"></i> <span class="txt">새 결재 진행</span>
					</a>
				</section>
				<!--결재관리 리스트-->
				<%@include file="approvalaside.jsp" %>
			</div>
			<!--left body_side finish-->
			<!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
			<div class="body_content">
				
			</div>
		</div>
		<!-- body warp finish -->
	</div>
	<!-- warp finish -->
</body>
</html>
