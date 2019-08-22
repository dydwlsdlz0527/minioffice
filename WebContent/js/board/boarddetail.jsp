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
.board_name{
display: inline;
margin-left: 800px;
}
.comment_profile2{
    width: 90px;
    height: 100px;
    border: 1px solid;
    position: relative;
    display: inline-block;
}
.comment_profile{
}
.comment_window{
	position: relative;
    display: inline-block;
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
    <div><!-- 상단 버튼 div -->
      <button>새글쓰기</button>
      <button>답글쓰기</button>
      <div class="board_name">
        <a class="board_link" href="#"></a>
        <span>></span>
        <span>${d.board_no }</span>
        <input type="hidden" name="type" value="${d.board_type }">
        <input type="text" name="no" value="${d.board_no }">
        <input type="hidden" name="comment_token" value="1">
      </div>
    </div>
    <hr>
    <div><!-- 메인 상단 -->
      <span>${d.board_subject}</span>
      <br>
      <span>${d.emp.emp_no }</span> 
      <br>
      <span>${d.board_date }</span>
    </div>
    <br>
    <br>
    <div><!-- 메인 하단 -->
      <p>${d.board_content }</p>
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
      <div>
      	<div class="comment_profile2"><!-- 프로필 -->
	      <img src="${pageContext.request.contextPath }/images/profile/${sessionScope.emp_no}.jpg" style="width:80px; height:80px"><br>
	      <span>${sessionScope.emp_name }</span>
	      <span style="color:silver;">${sessionScope.rank_name }</span>
        </div>
        <div class="comment_window">
          <span>${b.comment_content }</span>
        </div>
      </div>  
<%--    <td style="width:50px">${b.comment_no}</td>
        <td style="width:300px">${b.board_no }</td>
        <td style="width:100px">${b.parent_comment_no }</td>
        <td style="width:100px">${b.comment_content }</td>
        <td style="width:50px">${b.comment_date }</td> --%>
      <hr>
    </c:forEach>
      </div>
      <div><!-- 댓글쓰기 -->
        <div class="comment_profile"><!-- 프로필 -->
	      <img src="${pageContext.request.contextPath }/images/profile/${sessionScope.emp_no}.jpg" style="width:80px; height:80px"><br>
	      <span>${sessionScope.emp_name }</span>
	      <input type="hidden" name="emp_no" value="${sessionScope.emp_no }">
	      <span style="color:silver;">${sessionScope.rank_name }</span>
        </div>
        <div class="comment_window">
          <textarea name="comment" rows="6" cols="158"></textarea>
        </div>
        <button id="comment_button" style="margin-left: 1170px;" >댓글 작성</button>
      </div>
  </div>
</form>
