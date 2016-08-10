<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<%@ include file="/WEB-INF/views/nav.jsp"%>
		<c:set var="category" value="${param.category}" scope="request" />
		<div class="container">
			<div id="article">
				<c:set var="it" value="IT" />
				<c:set var="society" value="society" />
				<c:set var="conference" value="conference" />
				<c:set var="opendata" value="공공데이터" />

				<div id="title_header">
					<h3 class="header_text">관심 자료 보기</h3>
					<div class="small_text">
						<small>관심 있는 자료를 주제별로 볼 수 있습니다.</small>
					</div>
					<p class="rssinfo_category_list">
					<p class="rssinfo_category">category</p>
					<span id="category" class="cateselect"
						onclick="location.href='main.do?category=${it}'">${it}</span> <span
						id="category"
						onclick="location.href='main.do?category=${society}'">entertainment</span>
					<span id="category"
						onclick="location.href='main.do?category=${conference}'">${conference}</span>
					
					</p>
				</div>






				<c:choose>
					<c:when test="${category=='IT'}">
						<c:forEach var="info" items="${infoList}">

							<div class="rssinfo">
								<form action="newsInsert.do" method="post">
									<h4 class="rssinfo_header">
										<span class="rssUrl" onclick="window.open('${info.news_url}')">${info.news_title}</span>
									</h4>
									<br> <input type="hidden" value="${category}"
										name="category" /> <input type="hidden" value="${member.studentNum}"
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
										<input type="submit" class="save" value="clip">
									</p>

								</form>
							</div>


						</c:forEach>

					</c:when>


					<c:when test="${category=='society'}">
					
					<c:forEach var="info" items="${infoList}">

							<div class="rssinfo">
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
										<input type="submit" class="save" value="clip">
									</p>

								</form>
							</div>


						</c:forEach>

                    </c:when>
                    
                    
					<c:when test="${category=='conference'}">
					
					<c:forEach var="info" items="${infoList}">

							<div class="c_rssinfo">
								<form action="newsInsert.do" method="post">
									<h4 class="c_rssinfo_header">
										<span class="rssUrl" onclick="window.open('${info.news_url}')">${info.news_title}</span>
									</h4>
									<br> <input type="hidden" value="${category}"
										name="category" /> <input type="hidden" value="${mid}"
										name="member_id" /> <input type="hidden"
										value="${info.news_title}" name="news_title" /> <input
										type="hidden" value="${info.news_url}" name="news_url" />

									<p class="rssinfo_date">${info.news_date}</p>
									<input type="hidden" value="${info.news_date}" name="news_date" />
									<p class="rssinfo_save">
										<input type="submit" class="save" value="clip">
									</p>

								</form>
							</div>


						</c:forEach>

                     </c:when>

				</c:choose>

			</div>
		</div>





	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
</body>
</html>