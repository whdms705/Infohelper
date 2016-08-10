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
									<th>No.</th>
									<th>작성자</th>
									<th>제목</th>
									

								</tr>

								<c:forEach var="notice" items="${noticeList}">
									<tr>
										<td>${notice.notice_id }</td>
										<td>${notice.member_id }</td>
										<td><a href="noticeDetail.do?nid=${notice.notice_id}">${notice.notice_title }</a></td>
										
									</tr>
								</c:forEach>

							</table>
							<center>
								<div id="page">

									<c:forEach var="paging" items="${paging}">

									${paging}
								</c:forEach>
								</div>
							</center>
                             <div class="qna_btn">
							<button class="btn btn-primary" 
								onclick="location.href('qnaWrite.do')">질문하기</button>
                                </div>
                                


						
					</div>
				</div>


			</div>
		</div>




	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>