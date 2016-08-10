<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Insert title here</title>
</head>
<body>

<c:set var="member" value="${requestScope.member}"/>
<c:choose>
 <c:when test="${empty member}">
 <script>
 alert('아이디 또는 비밀번호가 맞지 않습니다');
 history.back();
 </script>
 </c:when>
 <c:otherwise>
   <c:set var="studentNum" value="${member.studentNum}" scope="session"/>
   <c:set var="name" value="${member.name}" scope="session"/>
   <c:set var="author" value="${member.author}" scope="session"/>
     <script>
     location.href="main.do?category=IT";
     </script>
 </c:otherwise>
</c:choose>



</body>
</html>