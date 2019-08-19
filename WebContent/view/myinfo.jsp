<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
$(function() {
	$(".btn_logout").click(function(){
		var url = $(this).attr('href');		
		$.ajax({
			url: url,
			method: 'GET',
			success: function(data){
				location.href="${contextPath}/index.jsp";
			}
		});
	});
});
</script>

<section class="my_info">
  <ul class="ctrl">
    <li class="notification">
      <a href="#" class="btn_noti"><i class="fas fa-bell"></i></a>
    </li>
    <li class="config">
      <a href="${contextPath}/view/admin/adminhome.jsp" class="btn_config"><i class="fas fa-cog"></i></a>
    </li>
    <li class="logout">
      <a href="${contextPath}/logout" class="btn_logout">
        <i class="fas fa-power-off"></i>
      </a>
    </li>
    <li class="photo">
      <a href="#">
        <!-- 로그인한사람의 프로필사진을 불러와야함. 미완성 -->
        <img
          class="profile_photo"
          src="${contextPath}/images/default_image.jpg"
        />
        <!--  -->
      </a>
    </li>
  </ul>
</section>