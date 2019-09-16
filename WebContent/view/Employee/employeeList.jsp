<%@page import="com.minioffice.vo.PageBean"%>
<%@page import="com.minioffice.vo.Employee"%>
<%@page import="com.minioffice.vo.Rank"%>
<%@page import="com.minioffice.vo.Department"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>    
<%String contextPath = request.getContextPath();%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <!-- home 화면에 필요한 icon과 스타일 불러오기 -->
  <script src="https://kit.fontawesome.com/6ea03f5ac0.js"></script>
<style>
table.emp_chart tr td {
    height: 50px;
    line-height: 1.5;
    width: 550px;
    vertical-align: middle;
    padding: 6px;
    text-align: center;
}
div.content_tb table th, div.content_tb table td {
    border: 0;
    border-bottom: 1px solid #e9e9e9;
}

element.style {
    width: 10px;
}
.emp_choice{
	width : 10px;
}

#rank_select{
	width : 100px;
	height : 20px;
	text-align: center;
}

tableheader{
height : 50px;

}


select {

    -webkit-appearance: none;  /* 네이티브 외형 감추기 */
    -moz-appearance: none;
    appearance: none;
    border-radius: 5px;
}

.action_button{
	align-content: center;
}

.save_btn{
	align-content: center;
}
.cancel_btn{
	align-content: center;
}

div.content_tb div.tool_bar {
    padding: 10px 5px;
    padding-top: 10px;
    padding-right: 5px;
    padding-bottom: 10px;
    padding-left: 5px;
}
div.tool_bar {
    min-height: 30px;
    padding: 10px 25px;
    padding-top: 10px;
    padding-right: 25px;
    padding-bottom: 10px;
    padding-left: 25px;
    overflow: hidden;
    overflow-x: hidden;
    overflow-y: hidden;
}

.dataTables_paginate paging_full_numbers > span > a{
	cursor: pointer;
}

#layoutTitle{
	font-size : 30px;
	font-weight: bolder;
}

.emp_board_content_top{
	width : 700px;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script>
$(function(){
    var $spanArr = $("div.pageGroup > span");
    $spanArr.click(function(){
    	var pageNum = $(this).html(); //1,2,3,4,5
    	$.ajax({
    		url: "<%=contextPath%>/employeeList",
    		data:'currentPage='+pageNum,
    		success:function(data){
    		    $("section").empty();
    		    $("section").html(data);    		
    		}    		
    	});//end ajax    	
    });//end click
    
    var $rankSelect = $("input[name=rank_select]");
    $rankSelect.on("change", function(){
        switch($rankSelect.val()){
        case '0':
           $rankName.text();
           break;
        case '1':
        	$rankName.text();
           break;
        case '2':
           $zhcnNameObj.show();
           break;
        case '3':
           $viNameObj.show();
           break;
        case '4':
           $enNameObj.hide();
           $jpNameObj.hide();
           $zhcnNameObj.hide();
           $viNameObj.hide();
        }
        return false;
     });
    
    
    
    
});//end $(function(){

</script> 
  <link rel="stylesheet" href="${contextPath}/css/styles.css" />
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
        <nav class="side_menu">
          <ul>
          	<li class="info">
              <i class="fas fa-users"></i>
          	  <a href="${pageContext.request.contextPath}/employeeList">계정 목록</a>
          	</li>
          	<li class="info">
          	  <i class="fas fa-user-plus"></i>
          	  <a href="${pageContext.request.contextPath}/view/Employee/employee.jsp">계정 추가</a>
          	</li>
          </ul>
        </nav>        
      </div> 
      <!--left body_side finish-->
      <!-- body_content는 너만의 영역. 알아서 화면 내보내기-->
      <div class="body_content">
        <!-- 아래부터 작성하면됨 -->
        <%
int status = (Integer)request.getAttribute("status");
if(status != 1){
%>게시물 목록이 없습니다 
<%}%>
<%	PageBean pb = (PageBean)request.getAttribute("pb");
	int currentPage = pb.getCurrentPage();
	int maxPage = pb.getMaxPage();
	List<Employee> list = pb.getList(); %> 
	
 <header class="emp_board_content_top">
      <h1>
         <span class="title" id="layoutTitle">계정 목록</span>
      </h1>
      <hr>
      <div id="dataTables_wrapper container">
         <div class="content_tb over_visi" role ="grid">
         <div id="account_list_wrapper">
         <table class="emp_chart" id="account_list" style="width:100% margin-bottom:0px;">
         <thead>
         	<tr class= "tableheader"role="row">
         	<th class="emp_choice" role="columheader" rowspan="1" colspan="1" aria-label>
         		<input type="checkbox" id="checkedAll">
         	</th>
         	<th class="name title sorting_asc" role="columheader" tabindex="0" aria-controls="account_list" rowspan="1" colspan="1" style="width:213px;" aria-label= "이름:activate to sort column ascending">
         		<span class="title_sort">이름<ins class="ic"></ins><span class="selected"></span></span>
         	</th>
                           <th class = "rank_select_title"><select id="rank_select" name="rank_select">
                                 <option value="0">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp::직급::</option>
                                 <option value="1">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp이사</option>
                                 <option value="2">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp사장</option>
                                 <option value="3">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp전무</option>
                                 <option value="4">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp부장</option>
                                 <option value="5">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp차장</option>
                                 <option value="6">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp과장</option>
                                 <option value="7">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp대리</option>
                                 <option value="8">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp주임</option>
                                 <option value="9">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp사원</option>
                           </select></th>
         	<th class="email title sorting_asc" role="columnheader" tabindex="0" aria-controls="account_list" rowspan="1" colspan="1" style="width: 198px;" aria-label="
                                        이메일  : activate to sort column ascending">
            <span class="title_sort">이메일<ins class="ic"></ins><span class="selected"></span></span>
            </th>
            <th><select id="dept_select" name="dept_select">
                                 <option value="0000">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp::부서::</option>
                                 <option value="C001">&nbsp&nbsp&nbsp&nbsp경영업본부</option>
                                 <option value="C002">&nbsp&nbsp경영지원본부</option>
                                 <option value="C003">시스템운영본부</option>
                                 <option value="C004">&nbsp&nbsp&nbsp서비스본부</option>
                                 <option value="D001">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp영업팀</option>
                                 <option value="D002">&nbsp&nbsp&nbsp&nbsp&nbsp마케팅팀</option>
                                 <option value="D003">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp재경팀</option>
                                 <option value="D004">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp총무팀</option>
                                 <option value="D005">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp인사팀</option>
                                 <option value="D006">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp보안팀</option>
                                 <option value="D007">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp품질팀</option>
                                 <option value="D008">&nbsp&nbsp&nbsp자산운영팀</option>
                                 <option value="D009">&nbsp&nbsp인터넷사업팀</option>
                                 <option value="D010">&nbsp&nbsp커머스사업팀</option>
                           </select></th></tr>
                           <tr>
                           
                         <%for(Employee b: list){%> 
<td> <input type="checkbox" id="check_one"></td>
<td class="emp_name"><%=b.getEmp_name()%></td>

<td class="rank_name"><%=b.getRank().getRank_name()%></td>

<td class="emp_id"><%=b.getEmp_id()%></td>

<td class="dept_name"><%=b.getDept().getDept_name()%></td><tr></tr>

<%}//end for %>
         </thead>
         <tbody>
			<tr>
			</tr>
         </tbody>
         </table>
         </div>
         </div>
 	     </div>
 	     <div class="tool_bar"> <div class="critical custom_bottom"></div>
 	     <div class="dataTables_length">
 	     <div class="dataTables_paginate paging_full_numbers" id="account_list_paginate">
 	     <a tabindex="0" class="first paginate_button" title="맨앞" id="account_list_first" data-bypass="true"></a>
 	     <a tabindex="0" class="previous paginate_button" title="이전" id="account_list_previous" data-bypass="true"></a>
 	     <span><a tabindex="0" class="paginate_button" data-bypass="true">1</a>
 	     <a tabindex="0" class="paginate_button" data-bypass="true">2</a>
 	     <a tabindex="0" class="paginate_button" data-bypass="true">3</a>
 	     <a tabindex="0" class="paginate_active" data-bypass="true">4</a></span>
 	     <a tabindex="0" class="next paginate_button paginate_button_disabled" title="다음" id="account_list_next" data-bypass="true"></a>
 	     <a tabindex="0" class="last paginate_button paginate_button_disabled" title="맨뒤" id="account_list_last" data-bypass="true"></a></div></div>
   </div>
   
   </header>
      	<!--  -->
      </div>
    </div>
    <!-- body warp finish -->
  </div>
  <!-- warp finish -->
</body>
</html>