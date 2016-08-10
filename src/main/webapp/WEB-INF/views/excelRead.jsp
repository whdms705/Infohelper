<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>
<link rel="stylesheet" type="text/css" href="assets/css/myPage.css" />
<link rel="stylesheet" type="text/css" href="assets/css/introduction.css" />
<script type="text/javascript" src="assets/js/myPage.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/views/nav.jsp"%>

		<sec:authentication var="studentNum" property="principal" />
		

		<div class="container">
			<div id="article">
			

				<div class="content">
					<div class="content-inner" style="padding-top: 15px;">
						
							<h3>Q n A</h3>
							<div><a href="excelview.do">엑셀 다운</a> </div>
						

						<hr>


							<table class="table">
								<tr>
									<th>id</th>
									<th>작성자</th>
									<th>제목</th>
									<th>내용</th>
									

								</tr>

								<c:forEach var="notice" items="${noticeList}">
									<tr>
										<td>${notice.notice_id}</td>
										<td>${notice.member_id }</td>
										<td>${notice.notice_title }</td>
										<td>${notice.notice_content }</td>
									</tr>
								</c:forEach>

							</table>
							
                       

						
					</div>
				</div>


			</div>
		</div>




	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>