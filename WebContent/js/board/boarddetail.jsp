<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.minioffice.vo.Board" %>
<style>
.detailboard{
width:100%;
height:600px;
border-style:solid;
}

.comment_window{
	position: relative;
    display: inline-block;
}
.board_link_font{
font-size: 20px;
font-weight: bold;
}
.table_head{
width:200px;
height:50px;
vertical-align: middle;
background-color: #dee7ec;
border-bottom : 1px solid;
border-color: #d7d7d7;
}
.table_body{
width:435px;
vertical-align: middle;
border-bottom : 1px solid;
border-color: #d7d7d7;
padding-left: 20px;
}
.comment_write{
display: flex;
}
</style>
<script>
  $(function(){
	 /* 댓글작성버튼 */
	$("#comment_button").click(function(){		
		  console.log("?");
		  $.ajax({
			  url:'${pageContext.request.contextPath}/boarddetail',
			  method:'GET',
			  data:$("#comment_write").serialize(),
			  success:function(data, e){
				$(".body_content").empty();
				$(".body_content").html(data);
			  }
			  
		  });
		return false;
	  });
	  
	$(".board_link").click(function(){
		alert("aa");
		$.ajax({
			url:'${contextPath}/board',
			method:'GET',
			data:'type=a&currentPage=1',
			success:function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
		}
	});
	  
  });
  });
</script> 
<form id="comment_write">
<div class="detailboard"><!--전체 틀-->
  <c:set var="d" value="${requestScope.Boarddetail }"></c:set>
  <c:set var="e" value="${d }"></c:set>
    <!-- 상단 버튼 div -->
    
<%--         <a class="board_link board_link_font" href="#"></a>
        <span>></span>
        <span class="board_link_font">${d.board_no }</span> --%>
        <input type="hidden" name="type" value="${d.board_type }">
        <input type="hidden" name="no" value="${d.board_no }">
        <input type="hidden" name="comment_token" value="1">
		<input type="hidden" name="emp_no" value="${sessionScope.emp_no }">

    <div><!-- 메인 상단 -->
    <table>
      <tr>
        <th class="table_head">글번호</th>
        <td class="table_body">${d.board_no }</td>
        <th class="table_head">조회수</th>
        <td class="table_body">${d.board_cnt }</td>
      </tr>
      <tr>
        <th class="table_head">작성자</th>
        <td class="table_body">${d.emp.emp_name }</td>
        <th class="table_head">작성시간</th>
        <td class="table_body">${d.board_date }</td>
      </tr>
      <tr>
      <th class="table_head">제목</th>
      <td class="table_body" colspan="3">${d.board_subject }</td>
      </tr>
    </table>
    <div style="min-height: 200px; width:1000px">
    <br><br>
    <span>${d.board_content }</span>
    <br><br>
    </div>
    </div>

    <hr>
    
    <!-- 게시판 상세보기를 누르면
    	상세보기 내용과 댓글이 표시되고
    	댓글이 있다면 댓글내용이 포함됨
    	댓글 데이터는 하나의 테이블에 삽입되기 때문에
    	댓글 데이터에 게시판타입을 인식할 수 있는 타입데이터가 포함되어야하고
    	상세보기 페이지에서 타입에 대한 데이터값을 가지고있어야 한다.-->
      <div><!-- 댓글목록 -->
    <c:forEach var = "b" items="${requestScope.comment}">
      <div style="display:flex;">
      	<div style="display:inline;"><!-- 프로필 -->
	      <img src="${pageContext.request.contextPath }/images/profile/${b.emp.emp_no}.jpg" style="width:60px; height:60px"><br>
	      <span style="font-size: 10px;">${b.emp.emp_name }</span>
	      <span style="color:silver;font-size: 10px">${b.emp.rank.rank_name}</span>
	    </div>
        <div class="comment_window" style="padding-left:10px;padding-top:10px">
          <span>${b.comment_content }</span>
        </div>
      </div>  
      <hr>
    </c:forEach>
      </div>
      <div class="comment_write"><!-- 댓글쓰기 -->
        <div class="comment_profile"><!-- 프로필 -->
	      <img src="${pageContext.request.contextPath }/images/profile/${sessionScope.emp_no}.jpg" style="width:50px; height:50px"><br>
<%-- 	      <span>${sessionScope.emp_name }</span>
	      <input type="hidden" name="emp_no" value="${sessionScope.emp_no }">
	      <span style="color:silver;">${sessionScope.rank_name }</span> --%>
        </div>
        <div class="comment_window">
          <textarea name="comment" rows="3" cols="156"></textarea>
        </div>
        <button id="comment_button" >댓글 작성</button>
      </div>
  </div>
</form>
