<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="userno" value="${sessionScope.emp_no}"/>
<style>
#left_table tr>td:nth-child(1){
	background: rgb(221, 221, 221);
    padding: 5px;
    border: 1px solid black;
    border-image: none;
    height: 18px;
    text-align: center;
    color: rgb(0, 0, 0);
    font-size: 12px;
    font-weight: bold;
    vertical-align: middle;
}
#left_table tr>td:nth-child(2){
	background: rgb(255, 255, 255);
    padding: 5px;
    border: 1px solid black;
    border-image: none;
    text-align: left;
    color: rgb(0, 0, 0);
    font-size: 12px;
    font-weight: normal;
    vertical-align: middle;
}
centertable>tr>td:nth-child(1){
    background: rgb(221, 221, 221);
    padding: 5px;
    border: 1px solid black;
    border-image: none;
    height: 25px;
    text-align: center;
    color: rgb(0, 0, 0);
    font-size: 14px;
    font-weight: bold;
    vertical-align: middle;
}
tool_bar>button:not(:nth-child(1)){
    display: inline-block;
    letter-spacing: -1px;
    padding-top: 7px;
    vertical-align: top;
    line-height: 1;
}
button{
	background-color : white;
}
#doctitle{ 
	background: white;
    padding: 0px !important;
    border: 0px currentColor;
    border-image: none;
    height: 90px;
    text-align: center;
    color: black;
    width: 800px;
    font-size: 36px;
    font-weight: bold;
    vertical-align: middle;
}

#table1{
    border: 0px solid rgb(0, 0, 0);
    border-image: none;
    width: 800px;
    font-family: malgun gothic,dotum,arial,tahoma;
    margin-top: 0px;
    margin-bottom: 0px;
    border-collapse: collapse;
}

.cttsub {
	background: rgb(221, 221, 221);
    padding: 5px;
    border: 1px solid black !important;
    border-image: none;
    height: 25px;
    text-align: center;
    color: rgb(0, 0, 0);
    font-size: 14px;
    font-weight: bold;
    vertical-align: middle;
}
.ctttd{
	background: rgb(255, 255, 255);
    padding: 5px;
    border: 1px solid black !important;
    border-image: none;
    text-align: left;
    color: rgb(0, 0, 0);
    font-size: 14px;
    font-weight: normal;
    vertical-align: middle;
}
.table1_td {
    background: white;
    padding: 0px !important;
    border: currentColor;
    border-image: none;
    text-align: right;
    color: black;
    font-size: 12px;
    font-weight: normal;
    vertical-align: top;
}
#centertable {
	border: 0px solid rgb(0, 0, 0);
    border-image: none;
    width: 800px;
    font-family: malgun gothic,dotum,arial,tahoma;
    margin-top: 10px;
    border-collapse: collapse;
    margin-top: 0px;
    margin-bottom: 0px;
}
.sign_type1_inline{
	display: table;
    width: 100px;
    right: 0px;
    float: right;
    height: 120px;
    border : 1px solid black;
    margin-left: 1px;
}
.sign_tit_wrap{
	border-right : 1px solid black;
	display: table-cell;
    vertical-align: middle;
    width: 20px;
    padding: 0;
    text-align: center;
    word-break: break-all;
    white-space: normal;
    height: 120px;
    color: black;
}
.sign_member_wrap{
	vertical-align: top;
    background: #fff;
    display: table-cell;
    float: right;
    border-left: 0;
    height: 120px;
}
.sign_rank_wrap{
    display: table-cell;
    vertical-align: middle;
    text-align: center;
    width: 80px;
    height: 20px;
}
.sign_name_wrap{
    height: 75px;
    display: table-row;
    vertical-align: middle;
    text-align: center;
    width: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-top: 1px solid black;
    border-bottom: 1px solid black;
}
.sign_date_wrap{
	text-align: center;
    margin-top: 6px;
}
#setd{
	background: rgb(255, 255, 255);
    border-width: medium 1px 1px;
    border-style: none solid solid;
    border-color: currentColor black black;
    padding: 10px;
    height: 350px;
    text-align: left;
    color: rgb(0, 0, 0);
    font-size: 14px;
    font-weight: normal;
    vertical-align: top;
}
.wrap_file_upload input {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
    width: 100%;
    height: 100%;
}
input.edit {
    background: #fffde5;
}
#attachPart>th {
	padding-left: 13px;
    height: 26px;
    width: 14px;
    text-align: left;
    vertical-align: top;
    text-align: left;
    position: relative;
    top: 11px;
}
.btn_file_form {
    display: inline-block;
    cursor: pointer;
    padding: 4px 8px 3px;
    color: #656565;
    font-weight: bold;
    font-size: 12px;
    line-height: 18px;
    border: 1px solid #cecece;
    border-radius: 2px;
    background: #f3f3f3;
}
#attachPart>td{
    height: 0px;
    width: 140px;
    text-align: left;
    vertical-align: top;
    padding : 0px;
    position: relative;
    top: 9px;
}
input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    cursor: pointer;
    opacity: 0;
    width: 100%;
    height: 100%;
    -webkit-writing-mode: horizontal-tb !important;
}
.btn_file_form {
	position: relative;
    cursor: pointer;
}
.button {
	cursor: pointer;
}
ul.file_wrap li {
    position: relative;
    line-height: 1;
    margin-top: 0;
    padding-top: 5px;
}
ul.file_wrap, ul.img_wrap {
    margin-top: 5px;
    list-style-type: none !important;
}

div.sign_type_new {
    display: inline-block;
    margin-right: -1px;
    font-size: 12px;
}
.doccontents{
	margin: 1%;
    border: 1px solid black;
    width: 823px;
    padding: 10px;
}
.tool_bar{
	padding-bottom: 5px;
    border-bottom: 1px solid;
}
#detailContents{
	height: auto;
    width: 100%;
}
@media (max-width: 1419px)
section.aside_wrapper {
    border-top: 1px solid #ccc!important;
    margin: 30px 0;
}
.article_reply div.reply_wrap ul.reply {
    margin: 0;
    border: 0;
}
ul.reply {
    border: 1px solid #d6d6d6;
    border-radius: 5px;
    width: 100%;
    padding: 2px;
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
}
.article_reply div.reply_wrap ul.reply>li {
    width: 100%;
    table-layout: fixed;
    border-spacing: 0;
    box-sizing: border-box;
}
.article_reply div.reply_wrap ul.reply>li {
    margin: 0px 0;
    padding: 10px 10px 10px 20px;
    border-bottom: 1px solid #f3f3f3;
}
ul.reply>li {
    position: relative;
    margin: 5px 0 10px;
    overflow: hidden;
    border: 1px solid lightgray;
    margin-bottom: 1px;
}
ul.reply li div.msg_wrap {
    position: relative;
    margin-left: 40px;
    min-height: 32px;
}
ul.reply li div.msg_wrap div.info span.department, .reply_create div.msg_wrap div.info span.department {
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #999;
}
ul.reply li span.name {
    margin-bottom: 2px;
}
ul.reply li span.name, .reply_create span.name {
    font-weight: bold;
}
ul.reply li span {
    display: inline-block;
}
span {
    vertical-align: baseline;
}
.article_reply div.reply_wrap ul.reply>li {
    width: 100%;
    table-layout: fixed;
    border-spacing: 0;
    box-sizing: border-box;
}
div.reply_wrap ul.reply li span.date, div.reply_wrap .reply_create span.date {
    display: inline;
    color: #999;
}
ul.reply li div.msg_wrap div.info .doc .status {
    color: #999;
}
span.part {
    color: #d7d7d7;
    margin: 0 2px;
}
ul.reply li span.photo {
    position: absolute;
    left: 10px;
    top: 10px;
    width: 32px!important;
    height: 32px!important;
}
.article_reply div.reply_wrap ul.reply>li span.photo {
    left: 20px;
}
ul.reply li span.photo img, .reply_create span.photo img {
    width: 100%;
    height: 100%;
    border-radius: 2px;
}
a {
    text-decoration: none;
}
ul, ul li {
    padding: 0;
    list-type: none;
}
ul.reply li span {
    display: inline-block;
}
.msg_wrap{
	display: flex;
    float: left;
}
.coment{
	height: 50px;
    border-left: 1px solid lightgray;
    align-items: center;
    display: flex;
    padding-left: 5px;
}
}
</style>
<script>
	$("#detailContents").append("${doc.getDoc_content()}");
	
	$("#DoapprOk").click(function(){
		$.ajax({
			url : '${contextPath}/DocApprResult',
			method : 'post',
			data : 'docno=' +'${doc.getDoc_no()}'+"&apprno="+'${userno}'
			        +'&appresult='+$("#apprselect").val()+'&apprcoment='+$("#apprcomenttxt").val(),
			success : function(data){
				console.log(data);
			}
		});
	});
	
</script>
<c:set var="doc" value="${requestScope.doc}"></c:set>
<div class="doccontents">
<header style="margin: 10px;">
	<h1 style="font-size: x-large;"><strong>${doc.getDoctype().getDoc_subject()}</strong></h1>
</header>
<div>
	<div class="tool_bar">
		<button class="btn btn-success btn-sm" data-toggle="modal" data-target="#appModal" id="doappr">결재하기</button>
		<button class="btn btn-danger btn-sm">반려하기</button>		
	</div>
	<div class="wrap_container">
		<%-- 기안 문서 --%>
		<section class="content_wrapper">
			<div>
				<form id="document_content">
					<table class="table" id="table1">
						<colgroup>
							<col width="310">
							<col width="490">
						</colgroup>
						<tr>
							<td colspan="2" id="doctitle">${doc.getDoctype().getDoc_subject()}</td>
						</tr>
						<tr>
							<td class="table1_td">
								<table class="table" id="left_table">
									<colgroup>
										<col width="90">
										<col width="220">
									</colgroup>
									<tr>
										<td>기안자<span style="display:none;">${doc.getEmp().getEmp_no()}</span></td>
										<td><span>${doc.getEmp().getEmp_name()}</span><span> ${doc.getEmp().getRank().getRank_name()}</span></td>
									</tr>
									<tr>
										<td>소속</td>
										<td><span>${doc.getDept().getDept_name()}</span></td>
									</tr>
									<tr>
										<td>기안일</td>
										<fmt:parseDate value="${doc.getDoc_startdate()}" var="dateFmt" pattern="yyyyMMdd"></fmt:parseDate>
										<td><span></span><fmt:formatDate value="${dateFmt}" pattern="yyyy년 MM월dd일 E요일"></fmt:formatDate></td>
									</tr>
									<tr>
										<td>문서번호<span style="display:none;">${doc.getDoctype().getDoctype_no()}</span></td>
										<td><span>${doc.getDoc_no()}</span></td>
									</tr>
								</table>
							</td>
							<td class="table1_td" id="apprmember">
								<c:set var="aplist" value="${requestScope.dd}"></c:set>
								<c:if test="${!empty aplist}">
									<c:forEach var="ap" items="${aplist}">
										<div class="sign_type1_inline">
										<div class="sign_tit_wrap">
											<span class="sign_tit"><strong>승인</strong></span>
										</div>
										<div class="sign_member_wrap">
											<div class="sign_rank_wrap">
												<span class="sign_rankno" style="display:none;">${ap.getEmp().getRank().getRank_no()}</span>
												<span class="sign_empno" style="display:none;">${ap.getEmp().getEmp_no()}</span>
												<span class="sign_name">${ap.getEmp().getEmp_name()}</span>
												<span class="sign_rank">${ap.getEmp().getRank().getRank_name()}</span>
											</div>
											<div class="sign_name_wrap">
												<span class="sign_appstep" style="display:none;">${ap.getApproval_step()}</span>
												<c:choose>
													<c:when test="${ap.getApproval_result() eq '0'}">
														
													</c:when>
													<c:when test="${ap.getApproval_result()=='1'}">
														<img src="${contextPath}/images/approval/appOk.png" title="승인">
													</c:when>
													<c:when test="${ap.getApproval_result()=='2'}">
														<img src="${contextPath}/images/approval/appWait.png" title="대기">
													</c:when>
													<c:otherwise>
														<img src="${contextPath}/images/approval/appNo.png" title="반려">
													</c:otherwise>
												</c:choose>
											</div>
											<div class="sign_date_wrap">
												
												<fmt:parseDate value="${ap.getApproval_date()}" var="dateFmt2" pattern="yyyy-mm-ddhh:mi:ss"></fmt:parseDate>
												<span class="sign_date"><fmt:formatDate value="${dateFmt2}" pattern="yy/MM/dd"/></span>
											</div>
										</div>
										</div>
									</c:forEach>
								</c:if>
								<div class="sign_type1_inline" id="applicantmember">
									<div class="sign_tit_wrap">
										<span class="sign_tit">
												<strong>신청</strong>
										</span>
									</div>
									<div class="sign_member_wrap">
										<div class="sign_rank_wrap">
											<span class="sign_rank"><span>${doc.getEmp().getEmp_name()}</span>&nbsp;<span>${doc.getEmp().getRank().getRank_name()}</span></span>
										</div>
										<div class="sign_name_wrap">
											<span class="sign_name"><img src="${contextPath}/images/approval/appOk.png"></span>
										</div>
										<div class="sign_date_wrap">
											<span class="sign_date"><fmt:formatDate value="${dateFmt}" pattern="yy/MM/dd"></fmt:formatDate></span>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<table class="table" id="centertable">
						<colgroup>
							<col width="120">
							<col width="230">
							<col width="120">
							<col width="330">
						</colgroup>
						<tr>
							<td class="cttsub">협조부서</td>
							<td class="ctttd"></td>
						</tr>
						<tr>
							<td class="cttsub">합의</td>
							<td class="ctttd" colspan="3">
								<span id="agreementWrap">
									<span class="sign_member">
										<span class="part">|</span>
										<span class="name"></span>
									</span>
								</span>
							</td>
						</tr>
						<tr>
							<td class="cttsub">제목</td>
							<td class="ctttd" colspan="3"><span>${doc.getDoc_subject()}</span></td>
						</tr>
						<tr>
							<td colspan="4" id="setd">
								<div id="detailContents"></div>

							</td>
						</tr>
					</table>
					</form>
			</div>
		</section>
		<section class="aside_wrapper article_reply">
			<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a
						class="nav-link active" role="tab" aria-controls="Applicant"
						data-toggle="tab" href="#AppComent">코멘트</a></li>
					<li role="presentation"><a class="nav-link" role="tab"
						aria-controls="Referrer" data-toggle="tab" href="#Referrer">문서정보</a>
					</li>
					<li role="presentation"><a class="nav-link" role="tab"
						aria-controls="Receiver" data-toggle="tab" href="#Receiver">열람기록</a>
					</li>
			</ul>
			<div class="doc-meta-container reply_wrap" role="tabpanel" id="AppComent">
				<ul class="reply" id="apprflow">
					<li>
						<span class="photo">
							<a>
								<img alt="초상화" src="${contextPath}/images/profile/2001081.jpg">
							</a>
						</span>
						<div class="msg_wrap">
							<div class="info">
								<a>
									<span class="name">한성준 부장</span>
								</a>
									<span class="department">경영지원본부</span>
								<div class="doc">
									<span class="status">기안 상신</span>
									<span class="part">|</span>
									<span class="date">2018-03-05(월) 15:41</span>
								</div>
							</div>
							<div class="coment">
								<span>다시 작성하세요.</span>
							</div>
						</div>
					</li>
					<li>
						<span class="photo">
							<a>
								<img alt="초상화" src="${contextPath}/images/profile/2019039.jpg">
							</a>
						</span>
						<div class="msg_wrap">
							<div class="info">
								<a>
									<span class="name">마동석 사원</span>
								</a>
									<span class="department">인사팀</span>
								<div class="doc">
									<span class="status">기안 상신</span>
									<span class="part">|</span>
									<span class="date">2018-03-08(월) 16:41</span>
								</div>
							</div>
							<div class="coment">
								<span>좋아요.</span>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</section>
	</div>
</div>
<%-- Doc Modal --%>
<div class="Modal fade" tabindex="-1" data-target=".body_content" role="dialog" id="docModal" data-backdrop="false"></div>
<%@include file="appModalView.jsp" %>
</div>