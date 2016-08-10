<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/myPage.css" />
<script type="text/javascript" src="assets/js/myPage.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/views/nav.jsp"%>

		<sec:authentication var="studentNum" property="principal" />
		<c:set var="category" value="${param.category}" scope="request" />

		<div class="container">
			<div id="article">
				<div class="sidebar">

					<div class="sidebar-nav">
						<ul class="nav-color">

							<li class="active" value="userManage"><a>회원 관리</a></li>
							<li class="" value=""><a>추가기능</a></li>
						


						</ul>
					</div>
				</div>

				<div class="content">
					<div class="content-inner" style="padding-top: 15px;">
						<section> <c:if test="${category=='myNews'}">
							<h3>${member.name}님의clip기사</h3>
						</c:if> <c:if test="${category=='update'}">
							<h3>${member.name}님의개인정보</h3>
						</c:if> <c:if test="${category=='breakaway'}">
							<h3>${member.name}님의회원탈퇴</h3>
						</c:if> </section>

						<hr>










						




					</div>
				</div>
			</div>
		</div>




	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>