<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%response.setHeader("Cache-Control", "no-cache");%>
<%response.addHeader("Cache-Control", "no-store");%>
<%response.setHeader("Pragma", "No-cache");%>
<%response.setDateHeader("Expires", 1L);%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>index.jsp</title>
<style>
/* Carousel 100% Fullscreen */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

.carousel, .carousel-item, .active {
	height: 100%;
}

.carousel-inner {
	height: 100%;
}

.carousel-caption {
	z-index: 0;
	top: 40%;
}

.carousel .carousel-item {
	background-color: #777;
}

.carousel .carousel-inner .bg {
	background-repeat: no-repeat;
	background-size: cover;
}

.carousel .carousel-inner .bg1 {
	background-image: url('${contextPath}/images/login/login1.jpeg');
	background-position: center top;
}

.carousel .carousel-inner .bg2 {
	background-image: url('${contextPath}/images/login/login4.jpg');
	background-position: center center;
}

.carousel .carousel-inner .bg3 {
	background-image: url('${contextPath}/images/login/login5.jpg');
	background-position: center bottom;
}
.carousel .carousel-inner .bg4 {
	background-image: url('${contextPath}/images/login/login6.jpg');
	background-position: center bottom;
}

/* modal start */
/* Full-width input fields */
 input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
/* Set a style for all buttons */
button {
	background-color: #3897f0;
	color: #fff;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}
/* Center the image and position the close button */
.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
	position: relative;
}

img.avatar {
	width: 60%;
}

#id01>.modal-content>div.container {
	padding: 16px;
	width: 100%;
}

span.psw {
	float: right;
	padding-top: 16px;
}
/* The Modal (background) */
.modal {
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
	padding-top: 60px;
}
/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 40%; /* Could be more or less, depending on screen size */
}
/* The Close Button (x) */
.close {
	position: absolute;
	right: 25px;
	top: 0;
	color: #000;
	font-size: 35px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: red;
	cursor: pointer;
}
/* Add Zoom Animation */
.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
<script>
$(function() {
	$("#myCarousel").carousel({
		interval:2000
	});
	var $modal = $("#id01");
	
	$("#loginbt").click(function(){
		$modal.css('display','block')
		$('input[name=id]').val('');
		$('input[name=pwd]').val('');
	});
	
	$('.imgcontainer>span').click(function(){
		$modal.css('display','none');
	});	
	
	
	var $idObj = $('input[name=id]'); //input객체중 name이 id인 dom트리에서 찾기
	var itemValue = localStorage.getItem('id');
	var $chkObj = $('input[type=checkbox]');
	$idObj.val(itemValue);
	
	//로그인 정보
	$('form').submit(function(){
		$cObj = $("input[name=remember]");
	    if($cObj.prop("checked")){ //선택
	       var id = $("input[name=id]").val();
	       localStorage.setItem("savedId", id);	    	
	    }else{ //해제
	       localStorage.removeItem("savedId");
	    }	    
		//로그인 성공시 코드 작성
	    $.ajax({
	          url:	'${contextPath}/login',
	          method : 'post',
	          data: $('form').serialize(),
	          success: function(data){
	        	  var jsonObj = JSON.parse(data);
	        	  var msg = jsonObj.emp_name + "님 로그인에 ";
	              if(jsonObj.status == 1){
	            	  msg += "성공 하셨습니다!";
	            	  alert(msg);
	            	  location.href='${contextPath}/view/home/home.jsp';/* "${contextPath}/jq/layout.jsp"; */
	              }else {
	            	  msg += "실패 하였습니다...";
	            	  alert(msg);
	              }
	          }
	        });
	        return false;                           
		});
});
</script>
</head>
<body>
	<%-- 세션의 attr이 존재하면 home.jsp로 넘어감--%>
	<%-- <c:if test="${!empty sessionScope.loginInfo}">
		<script>
			location.href="home.jsp";
		</script>
	</c:if> --%>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>
		
		<div class="carousel-inner">
			<div class="carousel-item bg bg1 active"></div>
			<div class="carousel-item bg bg2"></div>
			<div class="carousel-item bg bg3"></div>
			<div class="carousel-item bg bg4"></div>
		</div>	
		<a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
    		<span class="carousel-control-prev-icon"></span>
  		</a>
  		<a class="carousel-control-next" href="#myCarousel" data-slide="next">
    		<span class="carousel-control-next-icon"></span>
  		</a>
	</div>
	<div class="container" id="back-text">
		<div class="carousel-caption">
			<h1>Welcome Mini Office</h1>
			<p>Mint office is a groupware which gives you Simple, Fast, Effective
				way of business.<br> Reducing your worktime and improving your
				skills with us.
			</p>
			<p>
				<a class="btn btn-lg btn-primary" href="#" role="button" id="loginbt">Login</a>
			</p>
		</div>
	</div>
</body>
<div id="id01" class="modal">
	<form class="modal-content animate" action="/action_page.php">
		<div class="imgcontainer">
			<span class="close" title="Close Modal">&times;</span>
			<img src="" alt="Avatar" class="avatar">
		</div>
		<div class="container">
			<label for="uname"><b>아이디</b></label>
			<input type="text" placeholder="Enter Username" name="id" autocomplete="off"  style="ime-mode:inactive" required>
			<label for="psw"><b>비밀번호</b></label>
			<input type="password" placeholder="Enter Password" name="pwd" autocomplete="off"  required>
			<button type="submit" id="login_submit">로그인</button>
			<label><input type="checkbox" checked="checked" name="remember">계정 저장하기</label>
		</div>
	</form>
</div>
</html>