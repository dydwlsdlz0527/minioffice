<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userid" value="${sessionScope.emp_id}"/>
<%-- new Document Modal --%>
<style>
.modal-left-body {
	float: left;
}
.modal-right-body {
	float: left;
}

.modal-left-body {
	width: 45%;
}

.modal-right-body {
	width: 45%;
}

#searchInput {
	width: 90%;
}

#btImgSearch {
	background-color: rgba(0, 0, 0, 0);
	border: 0px;
	display: flex;
    justify-content: center;
    align-items: center;
}
.search {
	display: flex;
}
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
.modal-header{
	min-width : 800px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
}

</style>

<script>
	$(".close").click(function(){
		$('#docModal').modal('hide');
	});
	$("#cancelbtn").click(function(){
		$('#docModal').modal('hide');	
	});
/* 확인버튼 클릭시 */
	var $btsuccess = $("#btsuccess");
	var loadnewDoc = function(winfo){
		$.ajax({
			url : '${contextPath}/view/approval/approvalDoc.jsp',
			data : winfo,
			success : function(data){
				console.log(data);
				$(".body_content").html(data);
			}
		});
	};
	$btsuccess.click(function(){
		console.log('${userid}');
		$.ajax({
			url : '${contextPath}/ApprDocWriterInfo',
			method : 'post',
			data : 'id='+"${userid}&docid="+docid+"&pdocid="+pdocid,
			success : function(data){
				$("#searchInput").html('');
				$('#docModal').modal('hide');
				$(".body_content").html('');
				$(".body_content").html(data);
			}
		});
		//$(".body_content").load('approvalDoc.jsp').fadeIn("slow");
		
	});
	
	/*  jstree() 실행 */
	$("#jstree").jstree({
		'core':{
			'data' : {
					'url' : '${contextPath}/SearchDocType',
					'data' : function(node){
						return {'id' : node.id,
							};
					}
			}
		},
		"search":{
			"case_insensitive" : true,
			"ajax" : {
				"url" : '${contextPath}/SearchDocType'
			}
		},
		"state" :{"opened":true},
		"plugins" : ["themes","search","wholerow","changed","state","types"]
	});
	
	$("#jstree").on("changed.jstree",function(e,data){
		if(data.selected.length){
			$(data.selected).each(function(idx){
				var node = data.instance.get_node(data.selected[idx]).text;
			});
		}
	});
	
	$("#jstree").bind('select_node.jstree',function(event,data){
		docid = data.instance.get_node(data.selected).id;
		pdocid = ($("#jstree").jstree(true).get_node(docid) ).parent;
		if(pdocid!="#"){
			$.ajax({
				url : '${contextPath}/SelectDocType',
				data : 'docno='+docid,
				success : function(data){
					$("#doc_name").html(data.doc_name);
				}
			});
		}
	});
	
	var searchNode = false;
	$("#searchInput").keyup(function(){
		if(searchNode){ clearTimeout(searchNode);}
		var searchNodeStr = $("#searchInput").val();
		searchNode = setTimeout(function(){
			$("#jstree").jstree(true).search(searchNodeStr);
		},250);
	});
	
</script>
<div class="modal-dialog modal-lg" role="document" id="arrpdocModal">
	<div class="modal-content">
		<div class="modal-header">
			<h5 style="display: inline;">결재양식 선택</h5>
			<button type="button" class="close" data-dismiss="Modal" aria-label="Close" data-target="#arrpdocModal"><span aria-hidden="true">&times;</span></button>
		</div>
		<div class="modal-body">
			<div class="modal-left-body">
				<form>
					<div class="search">
						<input id="searchInput" class="search" maxlength="30" type="text"
							placeholder="양식제목" autocomplete="off">
						<button type="button" id="btImgSearch">
							<i class="material-icons md-dark">search</i>
						</button>
					</div>
				</form>
				<div id="jstree" class="tree">
					
				</div>
			</div>
			<div class="modal-right-body">
				<div><p class="bg-success"><h5>상세정보</h5></div>
				<form>
					<fieldset class="table-responsive">
						<table class="table">
							<tr>
							<th><span id="form_title" data-formid="688" class="title">제목</span></th>
							<td id="doc_name"></td>
							</tr>
							<tr>
							 <th><span class="title">전사문서함</span></th>
							 <td></td>
							</tr>
							<tr>
							<th><span class="title">보존연한</span></th>
							<td>5년</td>
							</tr>
							<tr>
							<th><span class="title">기안부서</span></th>
							<td>
							<select id="draftDeptId">
								<option value="0">영업본부</option>
								<option value="1">일반사용자분리</option>
							</select>
							</td>
							</tr>
							<tr>
							<th><span class="title">부서문서함</span></th>
							<td>
							<select id="deptFolderId">
							<option>미지정</option>
					        </select></td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-success" id="btsuccess">확인</button>
			<button type="button" class="btn btn-danger" id="cancelbtn" data-dismiss="Modal">취소</button>
		</div>
	</div>
</div>