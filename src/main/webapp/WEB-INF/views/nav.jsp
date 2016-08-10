<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<sec:authentication var="member" property="member" />
<header id="header"> <section
	class="nav-main nav-main-mini navbar" role="navigation"
	style="background-color:#2d3a45">
<div class="container" style="background-color: #2d3a45">
	<div class="nav-inner" style="background-color: #2d3a45">
		<div class="navbar-header navbar-not-login"
			style="background-color: #2d3a45">
			<a id="mainname" class="navbar-brand" href="main.do?category=IT">JOBHELP</a>
		</div>
		<nav> <c:if test="${member.author=='사용자'}">
			<ul class="nav navbar-nav nav-primary">
				<li><a href="qnaPage.do?pageNum=1">Q n A</a></li>
				<li class=""><a href="main.do?category=IT">관심 기사</a></li>
				<li><a href="introduction.do">이용방법</a></li>
			</ul>
		</c:if> <c:if test="${member.author=='관리자'}">
			<ul class="nav navbar-nav nav-primary">
				<li class=""><a href="#">관심 기사</a></li>
				<li><a href="userManage.do?pageNum=1">회원 관리</a></li>
				<li><a href="#">QnA 관리</a></li>
			</ul>
		</c:if> <sec:authorize access="authenticated">
			<span  class="navbar-nav navbar-right"> <c:if
					test="${member.author=='사용자'}">
					<a class="user" href="myPage.do?mid=${member.studentNum}&category=myNews">${member.name}&nbsp;님&nbsp;</a>
					<a href="logout.do"> 로그아웃 </a>
				</c:if> <c:if test="${member.author=='관리자'}">
					<a href="manage.do">${member.name}&nbsp;님&nbsp;</a>
					<a href="logout.do"> 로그아웃 </a>
				</c:if>
			</span>
		</sec:authorize> </nav>
	</div>
</div>
</section></header>