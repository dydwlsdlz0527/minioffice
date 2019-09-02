
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.board{
display:inline;
font-size: 20px;
font-family: Geogia;

}
.mouse{
cursor:pointer;}
/* .detailboardlist{
display : none;
} */
.writebuttonspace{
margin-left : auto;
margin-right : auto;
}

.writebutton{
width:150px;
height:50px;	
font-family: "나눔고딕","Nanum Gothic","NanumGothicWebFont","Dotum","돋움","Helvetica","Apple SD Gothic Neo","sans-serif";
}
ul{
list-style : none;}
</style>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script> -->
<script>

	
	var boardlistObj = document.querySelector(".boardlist");
	var detailboardlistObj = document.querySelector(".detailboardlist");
	boardlistObj.addEventListener("click", function(){
		if(detailboardlistObj.style.display =='none'){
			detailboardlistObj.style.display = 'block';
		}else {
			detailboardlistObj.style.display = 'none';
		}
	});
	var testbtObj = document.querySelector(".testbt");
	testbtObj.addEventListener("click", function(){
		alert(detailboardlistObj.style.display);
	});
	
 	$("#free").click(function(){

		$.ajax({
			url:'http://localhost:8080/miniOffice2/board', 
			method:'GET',
			success:function(data){
				$(".right").empty();
				$(".right").html(data);
			}
		})
		
	});
	
	$("#dept").click(function(){

		$.ajax({
			url:'http://localhost:8080/miniOffice2/js/board/deptboard.jsp', 
			method:'GET',
			success:function(data){
				$(".right").empty();
				$(".right").html(data);
			}
		})
		
	}); 

	

</script>
<div style="background-color: #edeff5;; width:300px; height: 500px">
  <section>
    <span class="board">게시판</span>
  </section>
  <hr>
  <section>
    <div class="writebuttonspace">
      <button class="writebutton" >글쓰기</button>
    </div>
  </section>
  <hr>
  <section>
  <div>
    <span class="boardlist mouse">게시판목록</span>
  </div>
  <div class="detailboardlist" style="display:none">  
    <ul>
      <li id="free" class='mouse'>자유게시판</li>
      <li id="dept" class='mouse'>부서게시판</li>
    </ul>
  </div>
  </section>
</div>
<button class = "testbt">테스트용버튼</button>