<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${requestScope.result}"/>

<c:forEach items="${result}"  var="row">
 <tr>
    <td> ${row.work_date } </td>
    <td> ${row.work_time0 } </td>
    <td> ${row.work_time1 } </td>
    <td> ${row.work_time } </td>
    <%-- <td> ${row.work_content }</td> --%>
    <td><%-- <a href="javascript:popupOpen('${row.work_content }')"> --%>
    <a href="#" onclick="popupOpen(this, '${row.work_content }');">상세보기</a></td>
 </tr>
</c:forEach>