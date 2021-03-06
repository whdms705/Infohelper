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

							<li class="active" value="myNews"><a>Clip 기사</a></li>
							<li class="" value="update"><a>개인정보 변경</a></li>
							<li class="" value="breakaway"><a>회원 탈퇴</a></li>


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





						<c:if test="${category=='myNews'}">
							<c:forEach var="info" items="${infoList}">
								<div class="myrssinfo">
								<form action="newsInsert.do" method="post">
									<h4 class="rssinfo_header">
										<span class="rssUrl" onclick="window.open('${info.news_url}')">${info.news_title}</span>
									</h4>
									<br> <input type="hidden" value="${category}"
										name="category" /> <input type="hidden" value="${mid}"
										name="member_id" /> <input type="hidden"
										value="${info.news_title}" name="news_title" /> <input
										type="hidden" value="${info.news_url}" name="news_url" />

									<p class="rssinfo_date">${info.news_date}</p>
									<input type="hidden" value="${info.news_date}" name="news_date" />
									<textarea style="overflow: hidden" class="rssinfo_description"
										rows="7" cols="67">${info.news_description}</textarea>
									<input type="hidden" value="${info.news_description}"
										name="news_description" />
									<p class="rssinfo_save">
										<input type="submit" class="save" value="del">
									</p>

								</form>
							</div>


							</c:forEach>
						</c:if>





						<c:if test="${category=='update'}">
							<c:set var="member" value="${member}" scope="request" />

							<form class="form-horizontal" action="myUpdate.do" method="POST">

								<div class="form-group form-group-sm">
									<label class="col-sm-2 control-label" for="formGroupInputSmall">학번</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="studentNum"
											id="formGroupInputSmall" value="${member.studentNum}"
											readonly> <input type="hidden" value="${category}"
											name="category" />
									</div>

								</div>
								<br>

								<div class="form-group form-group-sm">
									<label class="col-sm-2 control-label" for="formGroupInputSmall">사용자명</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="name"
											id="formGroupInputSmall" value="${member.name}">
									</div>

								</div>
								<br>

								<div class="form-group form-group-sm">
									<label class="col-sm-2 control-label" for="formGroupInputSmall">이메일</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="email"
											id="formGroupInputSmall" value="${member.email}">
									</div>

								</div>
								<br>

								<div class="form-group form-group-sm">
									<label class="col-sm-2 control-label" for="formGroupInputSmall">비밀번호</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="password"
											id="formGroupInputSmall" value="${member.password}">
									</div>

								</div>
								<br>
								<div class="text-center">
									<input type="submit" class="button-update"
										style="margin-top: 40px;" onclick="myUpdate()" value="변경하기">
								</div>




							</form>


						</c:if>

						<c:if test="${category=='breakaway'}">
							<p>JobHelp을 다시 사용할 일이 없어 계정을 없애고 싶으시면 계정 삭제를 처리해드리겠습니다.<br> 삭제된
								계정은 다시 복구할 수 없고 계정의 게시물이나 정보는 완전히 삭제된다는 점을 기억해 주세요.</p><br>
								
								<p> 그래도 계정을 삭제하려면 "회원 탈퇴"를 클릭하세요..</p>
							<div class="text-center">
							<form action="memberDelete.do" method="POST">
							    <input type="hidden" value="${member.studentNum}"/> 
								<input type="submit" class="button-update"
									style="margin-top: 40px;" onclick="breakaway()" value="탈퇴하기">
						    </form>
							</div>

						</c:if>




					</div>
				</div>
			</div>
		</div>




	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>