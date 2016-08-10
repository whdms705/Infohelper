<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>

<link rel="stylesheet" type="text/css" href="assets/css/qna.css" />
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
					<hr>
					
				<form action="qnaInsert.do" method="post">
					<div class="qnaForm">
						<input type="text" class="form-control" name="notice_title" placeholder="질문제목">
						<input type="hidden" name="member_id" value="${member.studentNum}"/>
					</div>
					<br>
				
					<div class="qnaForm">
						<textarea class="form-control" rows="10" name="notice_content">질문하고 싶은 내용을 남겨주세요 </textarea>
						<input type="submit" class="btn btn-default" value="저장하기">
					</div>
					
					
					
				</form>
				
				</div>
				   </div>

			</div>
		</div>




	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>