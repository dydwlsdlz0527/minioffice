<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<section class="apprSide">
	<h1 class="approval_title">
		<i class="fas fa-file-export"></i> <a href="#" class="txt">결재하기</a>
		<button id="btcollapse" class="btn btn-primary" type="button"
			data-toggle="collapse" data-target="#collapseArea"
			aria-expanded="false" aria-controls="#collapseArea">
			<i class="material-icons md-dark">expand_less</i>
		</button>
	</h1>
	<div class="collapse show" id="collapseArea">
		<ul class="approval_list">
			<li class="approval">
				<i class="fas fa-clock"></i>
				<a id="appwaitdoc" href="${contextPath}/approvalWaitBoard" class="txt">결재 대기 문서</a>
			</li>
			<li class="approval">
				<i class="fas fa-file-import"></i>
				<a href="#" class="txt">결재 수신 문서</a>
			</li>
			<li class="approval">
				<i class="fas fa-pause-circle"></i>
					<a href="#" class="txt">참조/열람 대기 문서</a></li>
			<li class="approval">
			 	<i class="fas fa-hourglass-half"></i>
			 	<a href="#" class="txt">결재 예정 문서</a></li>
		</ul>
	</div>
</section>
<section class="apprSide">
<h1 class="approval_title">
		<i class="fas fa-file-export"></i><a href="#" class="txt">개인문서함</a>
		<button id="btcollapse2" class="btn btn-primary" type="button"
			data-toggle="collapse" data-target="#collapseArea"
			aria-expanded="false" aria-controls="#collapseArea">
			<i class="material-icons md-dark">expand_less</i>
		</button>
	</h1>
	<div class="collapse show" id="collapseArea2">
		<ul class="approval_list">
			<li class="approval">
				<i class="fas fa-clock"></i>
				<a id="myappdoc" href="${contextPath}/approvalWaitBoard" class="txt">기안 문서함</a>
			</li>
			<li class="approval">
				<i class="fas fa-edit"></i>
				<a href="#" class="txt">임시 저장함</a>
			</li>
			<li class="approval">
				<i class="fas fa-pause-circle"></i>
				<a href="#" class="txt">결재 문서함</a>
			</li>
			<li class="approval">
			 	<i class="fas fa-hourglass-half"></i>
			 	<a href="#" class="txt">수신 문서함</a>
			</li>
			<li class="approval">
			 	<i class="fas fa-file-import"></i>
			 	<a href="#" class="txt">참조/열람 문서함</a>
			</li>
			<li class="approval">
			 	<i class="fas fa-ban"></i>
			 	<a href="${contextPath}/myDocCancle" class="txt">기안 반려함</a>
			</li>
			<li class="approval">
			 	<i class="fas fa-clipboard-check"></i>
			 	<a href="${contextPath}/myDocCompleted" class="txt">기안 완료함</a>
			</li>
		</ul>
	</div>
</section>
