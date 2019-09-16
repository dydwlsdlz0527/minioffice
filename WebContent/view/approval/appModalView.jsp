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
	min-width: 300px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
}
.appModalB, #apprselect{
    display: flex;
    align-items: center;
}
#apprselect {
	width: 73%;
    padding-left: 27%;
}
</style>
<script>

</script>
<div class="Modal fade" aria-hidden="true" tabindex="-1" data-target=".body_content" role="dialog" id="appModal" data-backdrop="false">
	<div class="modal-dialog modal-sm" role="document" id="DoDocAppr">
		<div class="modal-content">
			<div class="modal-header">
				<h5 style="display: inline;">결재하기</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" data-target="#appModal"><span aria-hidden="true">&times;</span></button>
			</div>
			<div class="modal-body">
				<div class="appModalB" style="height:45px;">
				<span style="margin: 0 7px 0 7px;"><h5>결재선택</h5></span>
				<select id="apprselect" class="form-control">
					<option value="1">결재</option>
					<option value="2">대기</option>
					<option value="-1">반려</option>
				</select>
				</div>
				<div style="padding-left: 2px;">
					<textarea id="apprcomenttxt" rows="10" cols="34" placeholder="코멘트를 남겨주세요"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" id="DoapprOk">결재</button>
				<button type="button" class="btn btn-danger" id="DoapprCancel" data-dismiss="modal" data-target="#appModal">취소</button>
			</div>
		</div>
	</div>
</div>