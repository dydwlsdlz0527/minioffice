<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.minioffice.dao.BoardDAO" %>
<%@ page import= "com.minioffice.control.BoardController" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script> -->
<style>
th{
  border-spacing: 0px;
  border-collapse: separate;
  padding-top : 5px;
  padding-bottom: 5px;
}

.boardlist:hover{
background-color: #d1ffea;
}

td{
  border-spacing: 0px;
  border-collapse: separate;
  border-bottom : 1px solid;
  border-color: #d7d7d7;
  padding-top: 5px;
  padding-bottom: 5px;
}

.underline{
text-decoration:underline;
font-weight: bold; 

}
span{
cursor: pointer;
}
#pageGroup{
text-align: center;
}
</style>
<script>
$(function(){
	var $spanArr = $("#pageGroup > span");
	$spanArr.click(function(){
		var pageNum = $(this).html();
		$.ajax({
			url : '${pageContext.request.contextPath}/board',
			data : 'currentPage='+ pageNum+'&type=d',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
	});
	
 	var $prevBt = $("#prev");
	$prevBt.click(function(){
		var pageNum = ${pb.getStartPage()-1}
		$.ajax({
			url : '${pageContext.request.contextPath}/board',
			data : 'currentPage='+ pageNum+'&type=d',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
		
		
	}); 
	var $nextBt = $("#next");
	$nextBt.click(function(){
		var pageNum = ${pb.getEndPage()+1}
		$.ajax({
			url : '${pageContext.request.contextPath}/board',
			data : 'currentPage='+ pageNum+'&type=d',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
		
	}); 
	var $selectListObj = $(".boardlist");
	$selectListObj.click(function(){

		$.ajax({
			url:'${pageContext.request.contextPath}/boarddetail',
			method:'GET',
			data:'no='+$(this).children().html()+'&bord=1',
			success: function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
				$(".board_link").html("부서게시판");
				
				
			}
		});
	});
});
</script>

<div class="board_main_top">
  <table class="boardtable">
    <tr class="tablerow">
      <th class="th1 boardlist_tr">번호</th>
      <th class="th2 boardlist_tr">제목</th>
      <th class="th3 boardlist_tr">작성자</th>
      <th class="th4 boardlist_tr">작성일</th>
      <th class="th5">조회</th>
    </tr>
    <c:forEach var = "b" items="${requestScope.pb.list}">
      <tr class="boardlist">		
        <td>${b.dept_board_no }</td>
        <td>${b.dept_board_subject }</td>
        <td>${b.emp.emp_name }</td>
        <td>${b.dept_board_date }</td>
        <td>${b.dept_board_cnt }</td>
      </tr>
    </c:forEach>
  </table>
  <div id = "pageGroup">
    <c:if test="${requestScope.pb.startPage != 1}">
      <button id = "prev">[PREV]</button>
    </c:if>
    <c:forEach var = "i" begin="${requestScope.pb.startPage}" end="${requestScope.pb.endPage}">
      <c:choose>
      <c:when test="${i==requestScope.pb.currentPage}">
       [<span class = "underline">${i}</span>]&nbsp;&nbsp;
      </c:when>
	  <c:otherwise>
	   [<span>${i}</span>]&nbsp;&nbsp;
	  </c:otherwise>
	  </c:choose>
    </c:forEach>
    <c:if test="${requestScope.pb.endPage!=requestScope.pb.maxPage}">
      <button id = "next">[NEXT]</button>
    </c:if>
  </div> 
</div>
