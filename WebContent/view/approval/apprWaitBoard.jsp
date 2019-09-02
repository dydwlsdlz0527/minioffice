<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="userno" value="${sessionScope.emp_no}"/>
<style>
header.content_top {
	min-height: 39px;
	padding: 10px 10px 0px 10px;
	border-bottom: 1px solid #d7d7d7;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffffff),
		to(#f3f3f3));
	background-image: -webkit-linear-gradient(270deg, #ffffff 0%, #f3f3f3 100%);
	background-image: -moz-linear-gradient(270deg, #ffffff 0%, #f3f3f3 100%);
	background-image: -o-linear-gradient(270deg, #ffffff 0%, #f3f3f3 100%);
	background-image: linear-gradient(top, #ffffff 0%, #f3f3f3 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff',
		endColorstr='#f3f3f3', GradientType=0);
}

header.content_top section.combine_search {
	position: absolute;
	top: 112px;
	right: 10px;
}

section.combine_search .c_search_wrap {
	background: #fff;
	border: 1px solid #b9b9b9;
	border-radius: 2px;
	height: 34px;
	display: flex;
	justify-content: center;
	align-items: center;
}

@media screen and (-webkit-min-device-pixel-ratio: 0)
	section.combine_search .c_search_wrap select.search_op {
	height:22px;
}

section.combine_search .c_search_wrap select.search_op {
	color: #717171;
	border-radius: 0;
	border: 0;
	border-right: 1px solid #c9c9c9;
	box-shadow: none;
	padding: 4px;
	height: 32px;
	line-height: 19px;
	box-sizing: border-box;
}

section.combine_search .c_search_wrap button.btn_c_search {
	display: inline-block;
	padding: 11px;
	vertical-align: top;
	cursor: pointer;
	box-shadow: none;
	box-shadow: none;
	color: #717171;
	border: 0;
	background: white no-repeat -483px -665px;
	width: 27px;
	height: 31px;
}

@media screen and (-webkit-min-device-pixel-ratio: 0)
	section.combine_search .c_search_wrap input.c_search {
	padding:5px;
}

section.combine_search .c_search_wrap input.c_search {
	padding: 5px 0 6px 0;
	height: 10px;
	border: 0;
	border: 0;
	background: #fff;
	text-indent: 3px;
	box-shadow: none;
	color: #717171;
	width: 150px;
	height: 30px;
}

button.btn_c_search i.material-icons {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 10px;
}

.content_page {
	z-index: 0;
}

div.content_page {
	position: relative;
}

.tool_bar {
	position: relative;
	min-height: 38px;
}

.tool_bar .critical {
	float: left;
	margin: 5px 0 0 10px;
}

.tool_bar .btn_tool {
	float: left;
	margin: 0px 2px 3px 0;
	padding: 0 8px;
	height: 26px;
	background: #f5f5f5;
	border: 1px solid #cecece;
	border-radius: 2px;
	white-space: nowrap;
}
#waitboard1 table{
	width: 100%;
}
p.data_null {
    text-align: center;
    color: #888;
    font-size: 12px;
    padding: 30px 20px 30px 0;
}
#waitboard1>.text-center{
	width: 100%;
}
.text-center li{
	display:inline-block;
}
ul.pagination-sm a {
    display: inline-block;
    padding: 0;
    height: 32px;
    min-width: 14px;
    padding: 0 3px;
    margin-right: 1px;
    background: #f5f5f5;
    border: 1px solid #dddddd;
    display: inline-block;
    border-radius: 3px;
    color: #777;
    font-family: tahoma;
    line-height: 21px;
    vertical-align: top;
}
</style>
<script>
	var $pagination = $("ul.pagination-sm>li");
	$pagination.click(function(){
		var pageNum = $(this).html();
		var pageNum = ${pb.getStartPage()-1}
		$.ajax({
			url : '${contextPath}/approvalWaitBoard',
			data : 'currentPage='+ pageNum + "&empno="+'${userno}',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
	});

	var $prevBt = $("#prev");
	$prevBt.click(function(){
		var pageNum = ${pb.getStartPage()-1}
		$.ajax({
			url : '${contextPath}/approvalWaitBoard',
			data : 'currentPage='+ pageNum + "&empno="+'${userno}',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
	});
	
	var $nextBt = $("#next");
	$nextBt.click(function(){
		var pageNum = ${pb.getEndPage()+1}
		$.ajax({
			url : '${contextPath}/approvalWaitBoard',
			data : 'currentPage='+ pageNum + "&empno="+'${userno}',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
	}); 
	
	var $DocdetailTr = $(".DocdetailTr");
	$DocdetailTr.click(function(){
		var docno = $(this).children(1).children(1).val();
		$.ajax({
			url : '${contextPath}/appWaitBoardDetail',
			data : 'docno='+docno+"&empno="+'${userno}',
			success : function(data){
				$(".body_content").empty();
				$(".body_content").html(data);
			}
		});
	});

</script>
<header class="content_top">
	<h2 style="font-size: x-large;">결재 대기 문서</h2>
	<section class="combine_search">
		<div class="c_search_wrap">
			<select class="search_op" id="searchType">
				<option value="appSearch">전자결재</option>
				<option value="totalSearch">통합검색</option>
			</select> <input class="c_search" type="text" id="search-keyword"
				name="search_keyword" placeholder="검색">
			<button type="button" class="btn_c_search" id="btn-search">
				<i class="material-icons md-dark">search</i>
			</button>
		</div>
	</section>
</header>
<div class="content_page">
	<div class="dataTables_wrapper">
		<div class="tool_bar">
			<div class="critical">
				<a id="bulkComplete" class="btn_tool" data-role="button"> <span
					class="txt">일괄 결재</span>
				</a>
			</div>
		</div>
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a
				class="nav-link active" role="tab" aria-controls="#waitboard1"
				data-toggle="tab" href="#Applicant">전체</a></li>
			<li role="presentation"><a class="nav-link" role="tab"
				aria-controls="Referrer" data-toggle="tab" href="#waitboard2">대기</a>
			</li>
			<li role="presentation"><a class="nav-link" role="tab"
				aria-controls="Receiver" data-toggle="tab" href="#waitboard3">보류</a>
			</li>
		</ul>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="waitboard1">
					<!-- 전체 결재 대기 문서 테이블  -->
					<table class="table table-hover table-condensed">
						<tr>
							<th id="header_checkbox" class="sorting_disabled" style="width:4%;">
								<input type="checkbox" id="checkedAllDeptDoc" name="checkedAllDeptDoc">
							</th>
							<th id="header_drafted_at" class="date first sorting" style="width: 11%;">
								<span class="title_sort">기안일</span>
							</th>
							<th id="header_form_name" class="doc sorting" style="width: 14%;">
								<span class="title_sort">결재양식</span>
							</th>
							<th id="header_emergency" class="doc_emergency sorting_desc" style="width: 6%;">
								<span class="title_sort">긴급</span>
							</th>
							<th id="header_title" class="subject sorting" style="width: 51%;">
								<span class="title_sort">제목</span>
							</th>
							<th id="header_attach" class="attach sorting_disabled" style="width: 6%;">
								<span class="title_sort">첨부</span>
							</th>
							<th id="header_drafter_name" class="writer sorting">
								<span class="title_sort">기안자</span>
							</th>
						</tr>
						
						<c:set var="pb" value="${requestScope.pb}"></c:set>
						<c:set var="currentPage" value="${pb.currentPage}"></c:set>
						<c:set var="maxPage" value="${pb.maxPage}"/>
						<c:set var="startPage" value="${pb.startPage}"/>
						<c:set var="endPage" value="${pb.endPage}"/>
						<c:set var="cntPageGroup" value="${pb.cntPageGroup}"/>
						<c:choose>
							<c:when test="${empty pb.list}">
								<tr>
								<td colspan="7">
									<p class="data_null">
										<span><i class="far fa-save fa-3x"></i></span>
										<span class="txt">결재할 문서가 없습니다.</span>
									</p>
								</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="b" items="${pb.list}">
								<tr class = "DocdetailTr">
									<td><input type="checkbox" name="dno" value="${b.getDocno()}"></td>
									<td>${b.getDocdate()}</td>
									<td>${b.getDoctypename()}</td>
									<td></td>
									<td>${b.getDoctitle()}</td>
									<td></td>
									<td>${b.getDocappr()}</td>
								</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
						<div class="text-center">
						<ul class="pagination-sm">
							<%-- <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-left"></i></a></li> --%>
							<c:if test="${currentPage>cntPageGroup}">
								<li class="page-item"><a class="page-link" id="prev" href="javascript:void(0);"><i class="fas fa-angle-left"></i></a></li>
							</c:if>
							<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
								<li class="page-item"><a class="page-link" href="javascript:void(0);">${status.current}</a></li>
							</c:forEach>
							<c:if test="${endPage!=maxPage}">
								<li class="page-item"><a class="page-link" id="next" href="javascript:void(0);"><i class="fas fa-angle-right"></i></a></li>
							</c:if>
							<!-- <li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-double-right"></i></a></li> -->
						</ul>
						</div>
				<div role="tabpanel" class="tab-pane" id="waitboard2">
					<div></div>
					<div></div>
				</div>
				<div role="tabpanel" class="tab-pane" id="waitboard3">
					<div></div>
					<div></div>
				</div>
			</div>
		</div>
	</div>
</div>

<%-- Doc Modal --%>
<div class="Modal fade" tabindex="-1" data-target=".body_content"
	role="dialog" id="docModal" aria-hidden="true" data-backdrop="false"></div>