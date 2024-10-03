<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
* {
	margin: 0px;
	padding: 0px;
}
header {
    background-color: rgb(201, 201, 253);
    padding: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

header .logo {
    font-size: 24px;
    font-weight: bold;
}

header nav ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    gap: 15px;
}

header nav ul li {
    display: inline;
}

header nav ul li a {
    text-decoration: none;
    color: #333;
    font-weight: bold;
}

header nav ul li a:hover {
    color: #000;
}
</style>
	<header>
		<div class="logo"><a href="<%= request.getContextPath()%>/main?action=main">SSAFit</a></div>
		<nav>
			<ul>
			<c:choose>
				<c:when test="${ not empty sessionScope.member }">
					<li>
						<a href="${pageContext.request.contextPath}/member?action=myPage"><strong>${ sessionScope.member.memberName }</strong>님 환영합니다.</a>
					<li>
					<li>
						<a href="${pageContext.request.contextPath}/member?action=myPage">마이페이지</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/member?action=logoutForm">로그아웃</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="${pageContext.request.contextPath}/member?action=loginForm">로그인</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/member?action=joinForm">회원가입</a>
					</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</nav>
	</header>