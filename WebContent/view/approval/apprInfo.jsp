<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
/*모달  */
.Modal {
	text-align: center;
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

@media screen and (min-width: 768px) {
	.Modal:before {
		display: inline-block;
		vertical-align: middle;
		content: " ";
		height: 100%;
	}
}

.modal-body {
	display: inline-block;
	text-align: left;
	vertical-align: middle;
	margin: 0;
	padding: 0;
}

.modal-dialog {
	display: inline-block;
	vertical-align: middle;
}

.modal-header {
	min-width: 800px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
}

#tab_left {
	display: block;
	float: left;
	width: 230px;
	border: 1px solid #b5b5b5;
}

#searchImgBT {
	background-color: rgba(0, 0, 0, 0);
	border: 0px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#tab_left {
	margin-left: 5px;
	min-height: 400px
}

#search_wrap form {
	display: flex;
	justify-content: center;
	align-items: center;
}

#sidetab_item {
	height: 366px;
}

#tab_jstree {
	overflow: auto;
	max-height: 336px;
}

thead {
	display: table-header-group;
	vertical-align: middle;
	border-color: inherit;
}

table.type_normal thead th {
	background-color: #ededed;
	border-top: 1px solid #e6e6e6;
	height: 28px;
	border-bottom: 1px solid #a2a2a2;
	color: #555;
	text-align: left;
}

div.tit_type3 {
	padding: 0 7px;
	height: 25px;
	line-height: 27px;
	border-bottom: 1px solid #E6E6E6;
	background: #fafafa;
	text-align: center;
}

span.txt {
	font-weight: bold;
	color: #999;
}

#tab_right {
	margin-left: 240px;
	border: 1px solid #b5b5b5;
}

table {
	display: table;
	border-collapse: separate;
	border-spacing: 2px;
	border-color: grey;
	border-collapse: collapse;
	width: 100%;
	padding: 0;
}

table.type_normal {
	width: 100%;
	table-layout: auto;
}

th {
	display: table-cell;
	vertical-align: inherit;
	font-weight: bold;
}

table.type_normal thead tr th:first-child, table.type_normal tbody tr td:first-child
	{
	text-align: left !important;
	padding-left: 10px !important;
}

table.type_normal thead th {
	background-color: #ededed;
	border-top: 1px solid #e6e6e6;
	height: 28px;
	border-bottom: 1px solid #a2a2a2;
	color: #555;
	text-align: left;
}

table.type_normal .btn {
	width: 38px;
}

table.type_normal thead tr th:first-child, table.type_normal tbody tr td:first-child
	{
	text-align: left !important;
	padding-left: 12px !important;
	padding-right: 0 !important;
}

table.tb_approval_line thead tr th {
	padding: 0 5px;
}

table.type_normal th {
	white-space: nowrap;
	display: table-cell;
	vertical-align: inherit;
	font-weight: bold;
}

.type_normal th, .type_normal td {
	text-align: left;
}

div.list_approval_line div.btn_langth {
	position: absolute;
	top: 0;
	left: 0;
	width: 34px;
	height: 100%;
	background: #fafafa;
	cursor: pointer;
	border-right: 1px solid #dedede;
	padding: 0;
	display: flex;
    justify-content: center;
    align-items: center;
}

div.list_approval_line div.tb_approval_line_wrap {
	margin: 0 0 0 34px;
}

table.type_normal {
	width: 100%;
	table-layout: auto;
}

div.layer_normal, table.type_normal {
	border: 1px solid #d9d9d9;
}

table.tb_approval_line .kind {
	width: 70px;
	text-align: left;
}

table.tb_approval_line .name table.tb_approval_line .company {
	width: 110px;
	text-align: left;
}

div.layer_approval_line table.tb_approval_line .depart {
	width: auto;
}

table.tb_approval_line .state {
	width: 80px;
	text-align: left;
}

table.tb_approval_line .func {
	width: 30px;
	text-align: center;
}

div.layer_approval_line, div.content, div.set_data, div.list_approval_line_wrap
	{
	overflow-x: hidden;
	overflow-y: scroll;
}

div.list_approval_line {
	position: relative;
	border-bottom: 1px solid #E4E4E4;
}

table.tb_approval_line p.data_null {
    display: inline-block;
    width: 300px;
    margin: 0 auto;
}
div.layer_approval_line p.data_null {
    padding: 10px 0;
}
p.data_null {
    text-align: center;
    color: #888;
    font-size: 12px;
    padding: 30px 20px 30px 0;
}
dl, font, p, ol {
    line-height: 150%;
}
.func:hover{
    cursor: pointer;
    background-color : 'gray';
}
</style>
<script>
	$("#tab_jstree").jstree({
		'core' : {
			'data' : {
				'url' : '${contextPath}/SelectApplicant',
				'data' : function(node) {
					return {
						'id' : node.id
					};
				}
			}
		},
		"state" :{"opened":true},
		"search":{
			"case_insensitive" : true,
			"ajax" : {
				"url" : '${contextPath}/SelectApplicant'
			}
		},
		"plugins" : ["themes","search","changed","state","types"]
	});
	
	$("#tab_jstree").bind('select_node.jstree',function(event,data){
		id = data.instance.get_node(data.selected).id;
		$("#searchMember").val('');
		var $node = $('.jstree-clicked').attr("position","absolute");
		$node.addClass("ui-draggable");
		$node.draggable({revert:true,helper:"clone"});
		$(".tb_approval_line_wrap").droppable({
			drop: function(event,ui){
				var selectTable = $(this);
				for(var i=0;i<applicantArr.length;i++){
					if(applicantArr[i]==id){
						alert("이미 존재하는 결재자입니다!!");
						return;
					}
				};
				$.ajax({
					url : '${contextPath}/searchApplicant',
					method : 'post',
					data : 'empno='+id,
					success : function(data){
						empno = data.empno;
						empname = data.empname;
						deptname = data.deptname;
						applicantArr[appr_cnt++]=empno;
						str = '<tr class="last activity appr-activity inactive ui-droppable">';
						str += '<td class="kind"><span>&nbsp;&nbsp;결재</span></td>'
						          +'<td class="name">'+empname+'</td>'
						          +'<td class="depart">'+deptname+'</td>'
						          +'<td class="state"><span>예정</span></td>'
						          +'<td style="display:none">'+empno+'</td>'
						          +'<td class="func"><button type="button" class="trDel"><span title="삭제"><i class="fas fa-trash-alt"></i></span></button></td></tr>'
						if(selectTable.find(".data_null").length){
							selectTable.find(".appr-activity-table").html(str);
						}else{
							selectTable.find(".appr-activity-table").append(str);
						}
					}
			    });			
			}
		});
	});
	$(document).on("click",".trDel",function(){
		var deltrtable = $(this).parents('.appr-activity-table');
		var deltrlength = deltrtable.children('tr').length;
		console.log($(this).parents().prev().html());
		var index = applicantArr.indexOf($(this).parents().prev().html());
		if(index>-1){
			applicantArr.splice(index,1);
		}
		$(this).parents('tr').remove('');
		--deltrlength;
		if(deltrlength==0){
			var str = '<tr class="last activity appr-activity inactive ui-droppable"><td colspan="5">'
					+'<p class="data_null null_activity_p"><span><i class="far fa-save fa-3x"></i></span>'
					+'<span class="txt">드래그하여 결재선을 추가할 수 있습니다.</span>'
					+'</p></td></tr>';
			deltrtable.html(str)
		}
	});
	
	var searchNode = false;
	$("#searchMember").keyup(function(){
		if(searchNode){ clearTimeout(searchNode);}
		var searchNodeStr = $("#searchMember").val();
		searchNode = setTimeout(function(){
			$("#tab_jstree").jstree(true).search(searchNodeStr);
		},250);
	});
	
	$("#tab_jstree").on("changed.jstree",function(e,data){
		if(data.selected.length){
			$(data.selected).each(function(idx){
				var node = data.instance.get_node(data.selected[idx]).text;
				
			});
		}
	});
	/* 결재자정보에서 확인 버튼 클릭시 */
	$("#ApplicantSendBT").click(function(){
		jQuery.ajaxSettings.traditional = true;
		$('#applicantmember').prevAll().remove('');
		$.ajax({
			url : '${contextPath}/ApplicantSend',
			method : 'get',
			data : {'applist' : applicantArr},
			success : function(data){
				for(var i=0;i<data.length;i++){
					var jsonObj = data[i];
					var str = '<div class="sign_type1_inline">'
						+'<div class="sign_tit_wrap"><span class="sign_tit"><strong>승인</strong></span></div>'
						+'<div class="sign_member_wrap"><div class="sign_rank_wrap">'
						+'<span class="sign_rankno" style="display:none;">'+jsonObj.emprank+'</span>'
						+'<span class="sign_rank">'+jsonObj.emprankname+'</span></div><div class="sign_name_wrap">'
						+'<span class="sign_appstep" style="display:none;">'+jsonObj.appstep+'</span>'
						+'<span class="sign_empno" style="display:none;">'+jsonObj.empno+'</span>'
						+'<span class="sign_name">'+jsonObj.empname+'</span></div><div class="sign_date_wrap">'
						+'<span class="sign_date"></span></div></div>';
					$(str).insertBefore("#applicantmember");
				}
			}
		});
		var str2 = '<tr class="last activity appr-activity inactive ui-droppable"><td colspan="5">'
			+'<p class="data_null null_activity_p"><span><i class="far fa-save fa-3x"></i></span>'
			+'<span class="txt">드래그하여 결재선을 추가할 수 있습니다.</span>'
			+'</p></td></tr>';
		$('#applicantTb1').html(str2);
		$("#apprInfoModal").modal('hide');
	});
</script>
<div class="Modal fade" role="dialog" id="apprInfoModal"
	data-backdrop="false" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h5 style="display: inline;">결재 정보</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close" data-target="#apprInfoModal">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a
						class="nav-link active" role="tab" aria-controls="Applicant"
						data-toggle="tab" href="#Applicant">결재자</a></li>
					<li role="presentation"><a class="nav-link" role="tab"
						aria-controls="Referrer" data-toggle="tab" href="#Referrer">참조자</a>
					</li>
					<li role="presentation"><a class="nav-link" role="tab"
						aria-controls="Receiver" data-toggle="tab" href="#Receiver">수신자</a>
					</li>
				</ul>
				<%-- Tab panes --%>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="Applicant">
						<div style="margin-top: 4px;">
							<div id="tab_left">
								<div class="sidetab_title">
									<span>조직도</span>
								</div>
								<div class="sidetab_item">
									<div id="search_wrap">
										<form onsubmit="return false;">
											<input id="searchMember" class="search" type="text" placeholder="이름/아이디/부서/직위"
												style="width: 200px;">
											<button id="searchImgBT">
												<i class="material-icons md-dark">search</i>
											</button>
										</form>
									</div>
									<div id="tab_jstree" class="tree">
									
									</div>
								</div>
							</div>
							<div id="tab_right">
								<table class="type_normal tb_approval_line">
									<thead>
										<tr>
											<th class="btn"></th>
											<th class="kind">타입</th>
											<th class="name">이름</th>
											<th class="depart">부서</th>
											<th class="state">상태</th>
											<th class="func"><span><i
													class="fas fa-trash-alt"></i></span></th>
											<th class="blank"></th>
										</tr>
									</thead>
								</table>
								<div id="activity_groups" class="list_approval_line_wrap">
									<div class="activity_group ui-sortable" data-index="0">
										<div class="tit_type3">
											<span class="txt">신청</span>
										</div>
										<div class="list_approval_line">
											<div class="btn_langth add_activity" title="추가" data-id>
												<span class="plusspan"><i
													class="fas fa-angle-double-right"></i></span>
											</div>
											<div class="tb_approval_line_wrap">
												<table
													class="appr-activity-table type_normal tb_approval_line">
													<tr>
														<td class="kind">기안</td>
														<td class="name">${doc.getEmp().getEmp_name()}</td>
														<td class="depart">${doc.getDept().getDept_name()}</td>
														<td class="state"></td>
														<td class="func"></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="activity_group ui-sortable" data-index="1">
										<%-- <div class="approval_line"> --%>
										<div class="tit_type3">
											<span class="txt">승인</span>
										</div>
										<div class="list_approval_line">
											<div class="btn_langth add_activity" title="추가" data-id>
												<span class="plusspan"><i
													class="fas fa-angle-double-right"></i></span>
											</div>
											<div class="tb_approval_line_wrap" >
												<table class="appr-activity-table type_normal tb_approval_line ui-sortable" id="applicantTb1">
													<tr class="last activity appr-activity inactive ui-droppable">
														<td colspan="5">
															<p class="data_null null_activity_p">
																<span><i class="far fa-save fa-3x"></i></span>
																<span class="txt">드래그하여 결재선을 추가할 수 있습니다.</span>
															</p>
														</td>
														<%-- drop을 하면 td태그가 사라지고 결재자 정보가 생성된다. --%>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="activity_group ui-sortable" data-index="2">
										<%-- <div class="approval_line"> --%>
										<div class="tit_type3">
											<span class="txt">수신</span>
										</div>
										<div class="list_approval_line">
											<div class="btn_langth add_activity" title="추가" data-id>
												<span class="plusspan"><i
													class="fas fa-angle-double-right"></i></span>
											</div>
											<div class="tb_approval_line_wrap">
												<table class="appr-activity-table type_normal tb_approval_line">
													<tr class="last activity appr-activity inactive ui-droppable">
														<td colspan="5">
															<p class="data_null null_activity_p">
																<span><i class="far fa-save fa-3x"></i></span>
																<span class="txt">드래그하여 결재선을 추가할 수 있습니다.</span>
															</p>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="Referrer">
							<div></div>
							<div></div>
						</div>
						<div role="tabpanel" class="tab-pane" id="Receiver">
							<div></div>
							<div></div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" id="ApplicantSendBT">확인</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			</div>
		</div>

	</div>
</div>