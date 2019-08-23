<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.board{
background-color: #e9ebf2;
width : 100%;
}
.board_head{
display:flex;
margin-left: 30px;
padding-top: 20px;
}

.inline{
display: inline;}

.board_contentbox{
display:inline;
margin-left: auto;
margin-right: auto;
}

.board_head_type{
position: relative;
margin-left:800px;
}

.board_subject_style{
margin-left:30px;
margin-right: 54px;
font-size: 12px;
}
.board_attach_style{
margin-left:30px;
margin-right: 30px;
font-size: 12px;
}

.post{
position:relative;
margin-left:773px;
}
.subject{
width:1100px;
}
</style>
<script>

$(function(){
	
	
	

	$main = $("#board_head_type_main");
	$sub = $("#board_head_type_sub");
	
	$main.change(function(){
		
		$sub.empty();
		$sub.append('<option>항목선택</option>');
		
		switch($main.val()){
		
		case 'A' :
		$sub.append('<option value="a" name="boardType">전사 공지</option>');
		$sub.append('<option value="b" name="boardType">전사게시판</option>');
		break;
		
		case 'B' :
		$sub.append('<option value="c" name="boardType">부서 공지</option>');
		$sub.append('<option value="d" name="boardType">부서게시판</option>');
		break;
		
		
		}
	});
	//게시판 등록
	var $writeButtonObj = $("#writeButton");
	var $boardDataObj = $("#boardData");
	$writeButtonObj.click(function(elClickedObj){
		 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);

		 // 에디터의 내용에 대한 값 검증은 이곳에서
		 // document.getElementById("ir1").value를 이용해서 처리한다.

		 try {
		     elClickedObj.form.submit();
		 } catch(e) {}
		    $.ajax({
			  url:'${contextPath}/miniOffice/boardwrite',
			  method:'GET',
			  data:$boardDataObj.serialize() +"&type="+$("#board_head_type_sub option:selected").val(),
			  success:function(data){
				  
					$(".body_content").empty();
					$(".body_content").html(data);

			}
		});
		return false;
	});
});		

</script>

<form id="boardData" enctype="multipart/form-data" >
<div class = "board">
  <div class="board_head">	<!-- 사진, 게시판 유형 -->
	<div class = "board_head_profile inline"><!-- 프로필 -->
	  <img src="${pageContext.request.contextPath }/images/profile/${sessionScope.emp_no}.jpg" style="width:80px; height:80px"><br>
	  <span>${sessionScope.emp_name }</span>
	  <input type="hidden" name="emp_no" value="${sessionScope.emp_no }">
	  <span style="color:silver;">${sessionScope.rank_name }</span>
	</div>

	<div class = "board_head_type">
	  <select id="board_head_type_main">
	    <option value='0'>게시판선택</option>
	    <option value='A'>전사게시판</option>
	    <option value='B'>부서게시판</option>
	  </select>
      <span>></span>
	  <select id="board_head_type_sub">
	    <option>항목선택</option>
	  </select>
	</div>
  </div>
  <hr>
  <div class="board_center">
	<span class="board_subject_style">제목</span>
	<input type="text" class="subject" placeholder="제목을 입력하세요" name="boardSubject">
	<br>
	<span class="board_attach_style">첨부파일</span>
	<input type="file">
	<button class="post" id="writeButton">게시하기</button>
	<br><br>
	
      <textarea name="boardContent" id="ir1" rows="30" cols="170" placeholder="입력해주세요" ></textarea>
      <script type="text/javascript">
        var oEditors = [];
        nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "ir1",
        sSkinURI: "${contextPath}/miniOffice/js/board/se2/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
        });
       </script>
  </div>
</div>
</form>
