<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.board{

width : 100%;
}
.board_color{
background-color: #f3f3f3;
}
.board_subject{
padding-bottom: 20px;
}
.board_head{
display:flex;
padding-left: 30px;
padding-top: 20px;
padding-bottom: 20px;
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
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="jquery.form.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

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
	/* var $writeButtonObj = $("#writeButton");*/
	var $boardDataObj = $("#boardData");
	 $("#writeButton").click(function(elClickedObj){
		 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		 //var form = $('form')[0];
		//var formData = new FormData(form);
		 // 에디터의 내용에 대한 값 검증은 이곳에서
		 // document.getElementById("ir1").value를 이용해서 처리한다.

/* 		    $.ajax({
			  url:'${contextPath}/miniOffice/boardwrite',
			  processData : false,
			  contentType: false,
			  method:'POST',
			  data:formData,
			  success:function(data){
					$(".body_content").empty();
					$(".body_content").html(data);

			}
		}); */
	    try {
			     elClickedObj.form.submit();
			 } catch(e) {}
			    $.ajax({
				  url:'${contextPath}/miniOffice/boardwrite',
				  method:'GET',
				  data : $boardDataObj.serialize() +"&type="+$("#board_head_type_sub option:selected").val(),
				  success:function(data){
						$(".body_content").empty();
						$(".body_content").html(data);

				}
			}); 

		return false;
	});  


});		

</script>

<form id="boardData">
<div class = "board">
  <div class="board_head board_color">	<!-- 사진, 게시판 유형 -->
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
  <div class="board_center">
    <div class="board_subject board_color">
	<span class="board_subject_style">제목</span>
	<input type="text" class="subject" placeholder="제목을 입력하세요" name="boardSubject">
	<br>
	<br>
	<span class="board_attach_style">첨부파일</span>
	<input type="file" name="file">
	<button class="post" id="writeButton">게시하기</button>
	</div>
      <textarea name="boardContent" id="ir1" rows="30" cols="175" placeholder="입력해주세요" ></textarea>
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
