<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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

}
#setd{
	background: rgb(255, 255, 255);
    border-width: medium 1px 1px;
    border-style: none solid solid;
    border-color: currentColor black black;
    padding: 5px;
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
    width: 82%;
    padding: 10px;
}
.tool_bar{
	padding-bottom: 5px;
    border-bottom: 1px solid;
}
}
</style>
<script>
	applicantArr = new Array();
	
	var triggerfn = function(){
		var $asideArr1 = $("#collapseArea>ul.approval_list a");
		for(var i=0;i<$asideArr1.length;i++){
			var aObj = $asideArr1[i];
			console.log($(aObj).html());
			if($(aObj).html()=='결재 대기 문서'){
				$(aObj).trigger('click');
				break;
			}
		}
	}
	$("#appr_info").click(function(){
		applicantArr.splice(0);
		appr_cnt = 0;
		$("#apprInfoModal").html();
	});
	$("#approvalplz").click(function(){
		if(applicantArr.length==0){
			alert("결재자를 선택해주세요.");
			return;
		}
		var docempno = $("#left_table tr:nth-child(1) span:nth-child(1)").html();
		oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", smartEditor);
		$.ajaxSettings.traditional = true;
		$.ajax({
			url : '${contextPath}/documentComplete',
			method : 'get',
			data : 'docempno='+docempno+'&docempdept='+$("#left_table tr:nth-child(2) span").html()
					+'&docno='+$("#left_table tr:last span:last").html()
					+'&doctypeno='+$("#left_table tr:last span:nth-child(1)").html()
					+'&docsubject='+$("#ctttdsubject").val()
					+"&doccontent="+$("#smartEditor").val()
					+"&applist="+applicantArr,
			success : function(data){
				if(data.state==1){
					alert('기안서 제출 성공');
					triggerfn();
				}else{
					alert('실패');
				}
			}
		});
	});
</script>
<c:set var="doc" value="${requestScope.doc}"></c:set>
<div class="doccontents">
<header style="margin: 10px;">
	<h1 style="font-size: x-large;"><strong>${doc.getDoc_subject()}</strong></h1>
</header>
<div>
	<div class="tool_bar">
		<button class="btn btn-primary btn-sm" id="approvalplz">결재요청</button>
		<button class="btn btn-success btn-sm">임시저장</button>
		<button class="btn btn-danger btn-sm">취소</button>
		<button id="appr_info" class="btn btn-info btn-sm" data-toggle="modal" data-target="#apprInfoModal">결재정보</button>
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
							<td colspan="2" id="doctitle">${doc.getDoc_subject()}</td>
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
										<td><span>${doc.getDoc_startdate()}</span></td>
									</tr>
									<tr>
										<td>문서번호<span style="display:none;">${doc.getDoctype().getDoctype_no()}</span></td>
										<td><span>${doc.getDoc_no()}</span></td>
									</tr>
								</table>
							</td>
							<td class="table1_td" id="apprmember">
								<div class="sign_type1_inline" id="applicantmember">
									<div class="sign_tit_wrap">
										<span class="sign_tit">
												<strong>신청</strong>
										</span>
									</div>
									<div class="sign_member_wrap">
										<div class="sign_rank_wrap">
											<span class="sign_rank">${doc.getEmp().getRank().getRank_name()}</span>
										</div>
										<div class="sign_name_wrap">
											<span class="sign_name">${doc.getEmp().getEmp_name()}</span>
										</div>
										<div class="sign_date_wrap">
											<span class="sign_date"></span>
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
							<td class="ctttd"><input type="text" style="width : 80%;"></td>
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
							<td class="ctttd" colspan="3"><input id="ctttdsubject"type="text" placeholder="필수값 입니다." name="tddoctitle" style="width : 80%;"required></td>
						</tr>
						<tr>
							<td colspan="4" id="setd">
								<textarea name="smartEditor" id="smartEditor" rows="20" cols="109"></textarea>
								<script type="text/javascript">
								var oEditors=[];
								console.log($("#smartEditor"));
								nhn.husky.EZCreator.createInIFrame({		
									oAppRef: oEditors,
									elPlaceHolder : "smartEditor",
									sSkinURI : "${contextPath}/demo/SmartEditor2Skin.html",
									htParams : {
										bUseToolbar : true,
										bUseVerticalResizer : true,
										bUseModeChanger : true,
									},
									fOnAppLoad : function(){
										oEditors.getById["smartEditor"].exec("PASTE_HTML",[""]);
									},
									fCreator: "createSEditor2"
								});
	
								</script>
							</td>
						</tr>
					</table>
					</form>
					<div style="height:20px;"></div>
					<div id="editView">
					<form>
						<table>
							<tr id="attachPart">
								<th><span class="title">첨부파일</span></th>
								<td>
									<span class="wrap_btn wrap_file_upload">
										<span class="btn_file_form fileinput-button">
											<span class="button text">
												<span class="buttonText">파일 첨부</span>
											</span>
											<input type="file" name="file" title="파일 첨부" multiple accept="undefined" class="edit">
										</span>
									</span>
									<div class="wrap_attach">
										<ul class="file_wrap" id="filewWrap" >
										
										</ul>
									</div>
								</td>
							</tr>
						</table>
					</form>
					</div>
			</div>
		</section>
	</div>
</div>
<%-- Doc Modal --%>
<div class="Modal fade" tabindex="-1" data-target=".body_content" role="dialog" id="docModal" data-backdrop="false"></div>
	
</div>

<%@include file="apprInfo.jsp" %>