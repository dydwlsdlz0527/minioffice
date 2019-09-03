<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
ins{
   text-decoration: none;
}


input{
   height: 30px;
}

select{
   height: 30px;
}

.addr_input2, .addr_input3{
   width : 600px;
}
.overlap {
  background-color: rgb(0, 125, 200); /* Green */
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
}

.over1 {
  background-color: white; 
  color: black; 
  border: 2px solid rgb(0, 125, 200);
}

.over1:hover {
  background-color: rgb(0, 125, 200);
  color: white;
}

input[type=submit]{
display:none;
}
#idMsg{
   font-size: 15px;
   color: rgb(0, 125, 200);
}
#pwMsg{
   font-size: 15px;
   color:    #ff0000;
}

form {
    display: block;
    margin-top: 0em;
}

.sign_img_rank{
	
}

body {
    font-size: 12px;
    font-family: dotum,AppleGothic,arial,Helvetica,sans-serif;
    position: relative;
    overflow: auto;
}



div {
    display: block;
}

.content{
text-align: center;
}

div.content_page {
    width: 100%;
}

div.container div.header h2 {
    position: absolute;
    left: 10px;
    top: 8px;
    font: bold 15px malgun gothic,dotum;
    color: #444;
}

.action_button{
	
}

div.container div.header {
    background: #F0F0F0;
    border-bottom: 1px solid #D9D9D9;
    height: 40px;
    position: relative;
}

div.container div.content {
    padding: 20px;
}



body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, form, fieldset, p, button, input, figure {
    margin: 0;
    padding: 0;
}

table {
    border-collapse: collapse;
    width: 100%;
}

table.detail tr > td {
    padding: 12px 0px 9px 10px;
    border-bottom: 1px solid #e9e9e9;
    text-align:left 
}

img_td{
	height : 150px;
}

element.style {
    overflow: hidden;
    	height : 150px;   
}

table.detail td span {
    vertical-align: middle;
}

span.img_profile {
    display: inline-block;
    position: relative;
    border-radius: 3px;
    background: #f9f9f9;
    width: 100px;
    height: 100px;
    vertical-align: bottom;
}

span.img_profile img {
    border-radius: 3px;
    width: 100px;
}

span.img_profile img {
    border-radius: 3px;
    width: 100px;
}
a img, img {
    border: 0;
}

a img, img {
    border: 0;
}
img {
    vertical-align: top;
}

.img_profile .btn_img_upload {
    cursor: pointer;
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    cursor: pointer;
    display: block;
    background: #222;
    color: #fff;
    text-align: center;
    padding: 3px 0;
    border-radius: 0 0 3px 3px;
    filter: Alpha(Opacity=40);
    opacity: 0.4;
}

.img_profile {
    display: inline;
    line-height: 1;
    padding: 3px 0;
    border: 1px solid red;
    font-weight: normal;
    border: 0;
    background: none;
    color: #fff;
}

#img_file {
  position: absolute; z-index:1;  
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  bottom: 0px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
  background-color: transparent;
  cursor: pointer;
}
#sign_file {
  position: absolute; z-index:1;  
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  bottom: 0px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
  background-color: transparent;
  cursor: pointer;
}
.parea{
   height: 0px;
}

.buttonText, .btn_img_upload{
    cursor: pointer;
}

div.content table.detail div.sign_type_new {
    margin-bottom: 4px;
}

div.sign_type_new {
    display: inline-block;
    margin-right: -1px;
    font-size: 12px;
}

.tb_sign_type1{
	width : 30px;
}

div.content table.detail div.sign_type_new table.tb_sign_type1 tbody tr td {
    border-top: 1px solid #666;
    border-bottom: 1px solid #666;
    border-left: 1px solid #666;
    border-right: 1px solid #666;
    padding : 0px;
}
.sign_member{
width : 150px;
height : 150px;
}
#brim{
height : 0px;
}

wrap_name wrap_sign{
height : 100px;
}

.sign_img_rank .last {
	height : 20px;
}
.title{
font-size : 30px;
font-weight: bolder;
}


</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
           </script>
<script>
<%--아이디--%>
$(function(){
   var $idObj = $("input[name=id]");      
   var $pw2Obj = $("input[name=id]");   
   var $pwObj = $("input[name=pw]");
   var $pw2Obj = $("input[name=pw2]");
   var $nameObj = $("input[name=name]");      
   var $rank_selectObj = $("input[name=rank_select]");      
   var $dept_selectObj = $("input[name=dept_select]");      
   var $hp_numObj = $("input[name=hp_num]");      
   var $zipcode3Obj = $("input[name=zipcode3]");      
   var $sign_pwObj = $("input[name=sign_pw]");      
   var $admin_inputObj = $("input[name=admin_input]");      
   var $imgFileObj = $("input[name=img_filechk]");
   var $signFileObj = $("input[name=sign_filechk]");
   var $submitObj=$("input[type=submit]");
   var $idmsgObj=$("#idMsg");
   var $pwmsgObj=$("#pwMsg");
   var $savebtnObj=$(".save_btn");
   var $emailObj =$("#email");
   
   
   
   
   
   $idObj.focus(function(){
      $submitObj.hide();
   });
   
   <%-- 이름 선택 --%>
   var $moreNameObj=$("#more_name");
   var $enNameObj=$("#en_name_title");
   var $jpNameObj=$("#jp_name_title");
   var $zhcnNameObj=$("#zhcn_name_title");
   var $viNameObj=$("#vi_name_title");
   $moreNameObj.on("change", function(){
      switch($moreNameObj.val()){
      case 'e':
         $enNameObj.show();
         break;
      case 'j':
         $jpNameObj.show();
         break;
      case 'z':
         $zhcnNameObj.show();
         break;
      case 'v':
         $viNameObj.show();
         break;
      case '0':
         $enNameObj.hide();
         $jpNameObj.hide();
         $zhcnNameObj.hide();
         $viNameObj.hide();
      }
      return false;
   });
   

   <%--중복체크--%>
var $overlapObj=$("#overlap");
$overlapObj.click(function(){
   if($idObj.val().trim().length==0){
      $idObj.focus();
      return false;
   }
   $.ajax({
      url:'${contextPath}/overlap',
      method:'post',
      data:'id='+$idObj.val()+$emailObj.text(),
      success:function(data){
         var jsonObj=JSON.parse(data);
         if(jsonObj.status==1){
            $idmsgObj.html("이미 사용중인 아이디 입니다.");
         }else{
            $savebtnObj.show();
            $idmsgObj.html("사용 가능한 멋진 아이디네요!");
         }
      }
      
   });
   return false;
});


<%--파일 업로드 미리보기(이미지)--%>
   $imgFile = $(".buttontext");
   function readURL(input) {
      if (input.files && input.files[0]) {
            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
            reader.onload = function (e) {
               $('#img').attr('src', e.target.result);
             }      
            reader.readAsDataURL(input.files[0]);
      }
   }
   $("#img_file").change(function(){ //alert(this.value); //선택한 이미지 경로 표시 
       readURL(this);
      
});
   
   <%--파일 업로드 미리보기(결제이미지)--%>
   function signReadURL(input) {
      if (input.files && input.files[0]) {
            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
            reader.onload = function (e) {
               $('#sign_img').attr('src', e.target.result);
             }      
            reader.readAsDataURL(input.files[0]);
      }
   }
   $("#sign_file").change(function(){ //alert(this.value); //선택한 이미지 경로 표시 
      signReadURL(this);
});

   
 
   
    $("#userCreateForm").submit(function(){
    	//var formData = new FormData();
    	//formData.append("img_filechk",$img_filechk[0].files[0]);
    	//formData.append("sign_filechk",$sign_filechk[1].files[0]);
        $.ajax({
           url: '${contextPath}/save',
           method: 'post',
           data: $(this).serialize(),
           success:function(data){
              if( $pwObj.val() != $pw2Obj.val()){
                 $pwmsgObj.html("비밀번호가 일치 하지 않습니다.");
                 $pw2Obj.focus();
                 return false;
              }
              var jsonObj = JSON.parse(data);
              if(jsonObj.status == 1){ //가입성공
                 alert("가입성공");
                  location.href='${contextPath}/view/admin/adminhome.jsp'; //'${contextPath}/jq/layout.jsp';
                  console.log("plz")
              }else{
                 alert("가입실패");
              }
           }
        });
       return false;
    });
    
    
    
    /* var $imgFile = $("input[name=img_file]");
    var $signFile = $("input[name=sign_file]");
    
    //이미지파일 등록
    $imgFile.change(function(){
    	var formData = new FormData();
    	formData.append("img_file",$imgFile[0].files[0])
        $.ajax({
           url: '${contextPath}/fileupload',
           method: 'post',
           processData : false ,
           contentType : false ,
           data: formData ,
           success:function(data){
              var jsonObj = JSON.parse(data);
              if(jsonObj.status == 1){ //가입성공
                 alert("업데이트성공!");
                  //location.href='${contextPath}/view/Employee/employee.jsp'; //'${contextPath}/jq/layout.jsp';
              }else{
                 alert("업데이트실패");
              }
           }
        });
       return false;
    });
    //사인파일 등록
    $signFile.change(function(){
        $.ajax({
           url: '${contextPath}/fileupload',
           method: 'post',
           data: file,
           success:function(data){
              var jsonObj = JSON.parse(data);
              if(jsonObj.status == 1){ //가입성공
                 alert("업데이트성공!");
                  //location.href='${contextPath}/view/Employee/employee.jsp'; //'${contextPath}/jq/layout.jsp';
              }else{
                 alert("업데이트실패");
              }
           }
        });
       return false;
    }); */
    
});

</script>
</head>
<body>
   <header class="emp_content_top">
      <h1>
         <span class="title" id="layoutTitle">계정 목록 > 계정 추가</span>
      </h1>
      <hr>
      <div id="layoutContent" class="content_page">
         <div>
            <form id="userCreateForm">
               <div class="container">
                  <div class="header">
                     <h2>기본정보</h2>
                  </div>
                  <div class="content">
                     <table class="detail">
                     <colgroup>
                        <col width="130px">
                        <col width="*">
                     </colgroup>
                        <tr>
                           <th><span class="img_title">사진</span></th>
                           <td class="img_td"><span class="img_profile">
                                 <span class="btn_img_upload"> <span
                                    class="btn_img_file" style="text-align: center;"> <span
                                       class="button_text"> <label for="img_file" class="buttonText"
                                          style="font-size: 12px">사진올리기 </label>
                                    </span> <input type="file" name="img_filechk" id="img_file" title="파일첨부" multiple
                                       accept="undefined"></span>
                              </span><img src='${contextPath}/images/Employee/icon-2426371_960_720.jpg' width="100px" height="100px"
                                    id="img">
                           </span><br><br> <div class="parea">※ 사진은 150×150사이즈로 자동 적용됩니다.</div><br></td>
                        </tr>
                        
                        
                        <!-- 이름  -->
                        <tr>
                           <th><span class="name_title"> <ins
                                    class="name_asterisk">*</ins> 이름 (한글)
                           </span></th>
                           <td><input class="name_input" type="text" name="name"
                              id="ko_name" placeholder="이름 입력">
                              <select id="more_name">
                                 <option value="0">항목추가</option>
                                 <option value="e">이름(영문)</option>
                                 <option value="j">이름(일문)</option>
                                 <option value="z">이름(중국)</option>
                                 <option value="v">이름(베트남어)</option>
                           </select>
                           </td>
                        </tr>

                        <!-- 영문이름  -->
                        <tr id="en_name_title" style="display: none">
                           <th><span class="en_title">이름 (영문)</span></th>
                           <td><input class="en_name_input" id="en_name"
                              name="enName" type="text"></td>
                        </tr>

                        <!-- 일본이름  -->
                        <tr id="jp_name_title" style="display: none">
                           <th><span class="jp_title">이름 (일문)</span></th>
                           <td><input class="jp_name_input" id="jp_name"
                              name="jpName" type="text"></td>
                        </tr>

                        <!-- 중국이름  -->
                        <tr id="zhcn_name_title" style="display: none">
                           <th><span class="zhcn_title">이름 (중국어)</span></th>
                           <td><input class="zhcn_name_input" id="zhcn_name"
                              name="zhcnName" type="text"></td>
                        </tr>

                        <!-- 베트남이름  -->
                        <tr id="vi_name_title" style="display: none">
                           <th><span class="vi_title">이름 (베트남어)</span></th>
                           <td><input class="vi_name_input" id="vi_name"
                              name="viName" type="text"></td>
                        </tr>


                        <!-- 아이디  -->
                        <tr>
                           <th><span class="id_title"> <ins
                                    class="id_asterisk">*</ins> 아이디
                           </span></th>
                           <td><input class="id_input" type="text" name="id"
                              id="loginId" style="text-trancsform: lowercase"
                              placeholder="아이디 입력"> <span id="email" value = "@mini.com">@mini.com</span>
                              <button type="button" class="overlap over1" id="overlap" >중복확인</button>
                              <span class="id_err_box" id="idMsg"></span>
                              </td>
                              
                        </tr>

                        <!-- 비밀번호  -->
                        <tr>

                           <th><span class="pw_title"> <ins
                                    class="pw_asterisk">*</ins> 비밀번호
                           </span></th>

                           <td><input class="pw_input" type="password" name="pw"
                              id="loginPw" placeholder="비밀번호 입력"></td>

                        </tr>
                        <!-- 비밀번호확인  -->
                        <tr>

                           <th><span class="pw2_title"> <ins
                                    class="pw2_asterisk">*</ins> 비밀번호확인
                           </span></th>

                           <td><input class="pw2_input" type="password" name="pw2"
                              id="loginPw2" placeholder="비밀번호 입력">
                              <span class="pw_err_box" id="pwMsg"></span>
                              </td>
                        </tr>

                        <!-- 직급 -->
                        <tr>
                           <th><span class="rank_title"><ins
                                    class="rank_asterisk">*</ins>직급</span></th>
                           <td><select id="rank_select" name="rank_select">
                                 <option value="1">이사</option>
                                 <option value="2">사장</option>
                                 <option value="3">전무</option>
                                 <option value="4">부장</option>
                                 <option value="5">차장</option>
                                 <option value="6">과장</option>
                                 <option value="7">대리</option>
                                 <option value="8">주임</option>
                                 <option value="9">사원</option>
                           </select></td>
                        </tr>

                        <!-- 부서이름 -->
                        <tr>
                           <th><span class="dept_title"><ins
                                    class="rank_asterisk">*</ins>부서이름</span></th>
                           <td><select id="dept_select" name="dept_select">
                                 <option value="C001">영업본부</option>
                                 <option value="C002">경영지원본부</option>
                                 <option value="C003">시스템운영본부</option>
                                 <option value="C004">서비스본부</option>
                                 <option value="D001">영업팀</option>
                                 <option value="D002">마케팅팀</option>
                                 <option value="D003">재경팀</option>
                                 <option value="D004">총무팀</option>
                                 <option value="D005">인사팀</option>
                                 <option value="D006">보안팀</option>
                                 <option value="D007">품질팀</option>
                                 <option value="D008">자산운영팀</option>
                                 <option value="D009">인터넷사업팀</option>
                                 <option value="D010">커머스사업팀</option>
                           </select></td>
                        </tr>


                        <!-- 연락처 -->
                        <tr>
                           <th><span class="hp_num_title"><ins
                                    class="hp_asterisk">*</ins>휴대전화</span></th>
                           <td><input class="hp_num_input" type="text"
                              name="hp_num" id="hpNum" placeholder="전화번호 입력"></td>
                        </tr>

                        <!-- 주소-->
                        <tr>
                           <th><span class="addr"><ins
                                    class="addr_asterisk">*</ins>주소</span></th>
                           <!-- <td><input class="addr_input1" type="number" readonly
                              name="zipcode1">
                              <button class="addr_search" type="button" id="addrSearch">우편번호검색</button>
                           </td> -->
                        <!-- <tr>
                           <td><input class="addr_input2" type="text" readonly
                              name="zipcode2"></td>
                        </tr> -->
                      
                           <td><input class="addr_input3" name= "zipcode3" type="text" placeholder="주소 입력"></td>
                        

                        <!-- 서명이미지 -->
                        <tr>
                           <th><span class="img_sign_title">서명이미지</span></th>
                           <td><div class="img_sign">
                                 <table class="tb_img_sign">
                                    <tr>
                                       <td>
                                       <div class="sign_type1 sign_type_new">
                                            <table class="tb_sign_type1">
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <table class="sign_member">
                                                                <tbody>
                                                                    <tr><td class = sign_img_rank"><span class="sign_rank">직위</span></td></tr>
                                                                    <tr>
                                                                        <td class="wrap_name wrap_sign">
                                                                            <span class="sign_stamp stamp_approved"><img src="${contextPath}/images/Employee/stamp_approved.png" id="approval_sign_img"></span>
                                                                            <span class="sign_name">이름</span><tr id = "brim"></tr>
                                                                      </td>
                                                                    </tr>
                                                                    <tr><td class="last"><span class="sign_date">결재일</span></td></tr>
                                                                </tbody>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                          <br>
                                       </td>
                                    </tr>
                                 </table>
                              </div> <span class="btn_sign_img"> <span
                                 class="btn_sign_img_file" style="text-align: center;">
                                    <span class="button_text"> <label for="sign_file" class="buttonText"
                                          style="font-size: 12px">사진올리기 </label>
                                  <input type="file" name="sign_filechk" id="sign_file" title="서명파일첨부" multiple
                                    accept="undefined"></span>
                              </span>
                           </span> </td>
                        </tr>



               <!-- 결제비밀번호 -->
                        <tr>

                           <th><span class="sign_pw_title"><ins
                                    class="sign_pw_asterisk">*</ins> 결제비밀번호
                           </span></th>

                           <td><input class="sign_pw_input" type="password" name="sign_pw"
                              id="signPw" placeholder="결제비밀번호 입력"></td>

                        </tr>

               <!-- 관리자권한 -->
                        <tr>

                           <th><span class="admin_title"> 권리자권한설정</span></th>

                           <td><select id="admin_input" name="admin_input">
                                 <option value="N">N</option>
                                 <option value="Y">Y</option>
                           </select>
                           

                        </tr>
                     </table>
                  </div>
               </div>
               
               
               
            
<!-- 액션버튼 -->
               <div class="action_button">
                  <input type="submit" class="save_btn" value="저장" > 
                  <input type="button" class="cancel_btn" value="취소">
               </div>
               </form>
         </div>

      </div>
   </header>
</body>
</html>