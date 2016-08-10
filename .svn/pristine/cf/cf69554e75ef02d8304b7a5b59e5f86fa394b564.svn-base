<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet" type="text/css" href="assets/css/manage.css" />
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/views/nav.jsp"%>
		<c:set var="pageNum" value="${param.pageNum}" scope="request" />
		<div class="container">
			<div id="article">

				<div id="manage_content">
					<h2 class="header_text">회원 관리</h2>
					<hr>
					<div id="userManagementContainer">
						<table class="table">
							<thead>
								<tr>
									<th>학번</th>
									<th>이름</th>
									<th>이메일</th>
									<th>권한</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="member" items="${memberList}">
									<form action="userDelete.do" method="post">
									<tr>
										<td>${member.studentNum}</td>
										<td>${member.name}</td>
										<td>${member.email}</td>
										<td>${member.author}</td>

										<td><input type="hidden" value="${member.studentNum}"
											name="studentNum">
											<input type="hidden" value="${pageNum}"
											name="pageNum">
											 <input type="submit"
											class="btn btn-default studentDelete" value="삭제"></td>
									</tr>
									</form>

								</c:forEach>
							</tbody>
						</table>
						
						
						<center>
							<div id="page">
								
								<c:forEach var="paging" items="${paging}">

									${paging}
								</c:forEach>
							</div>
						</center>
					</div>


				</div>




			</div>
		</div>





	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>