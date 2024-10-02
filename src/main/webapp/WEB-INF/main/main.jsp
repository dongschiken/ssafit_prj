<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SSAFit</title>
<style>
body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    color: #333;
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

main {
    text-align: center;
    margin-top: 50px;
}

.intro h1 {
    font-size: 36px;
    color: #333;
}

.start-btn {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: rgb(201, 201, 253);
    color: #333;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
}

.start-btn:hover {
    background-color: #9999ff;
    color: white;
}

/* 추천 영상 카드 스타일 */
.video-section {
    margin-top: 50px;
}

.card-container {
    display: flex;
    justify-content: center;
    gap: 20px;
    flex-wrap: wrap;
    padding: 20px;
}

.card {
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 250px;
    padding: 20px;
    text-align: center;
}

.card img {
    width: 100%;
    border-radius: 5px;
}

.card h3 {
    font-size: 20px;
    margin: 10px 0;
}

.card p {
    font-size: 14px;
    color: #666;
}

.card > iframe {
	width: 230px;
	height: 170px;
	border-radius: 5px;
}

</style>
<!-- <link rel="stylesheet" href="/WEB-INF/main/styles.css"> -->
<script src="script.js" defer></script>
</head>
<body>
	<!-- 헤더 영역 -->
	<%@ include file="../include/header.jsp"%>

	<!-- 메인 섹션 -->
	<main>
		<section class="intro">
			<h1>SSAFit과 함께 운동할까요?</h1>
			<a href="<%= request.getContextPath() %>/member?action=routineForm" class="start-btn">운동하러 가기</a>
			<a href="<%= request.getContextPath() %>/member?action=list" class="start-btn">운동 게시판 이동하기</a>
		</section>

		<!-- 추천 운동 영상 카드 -->
		<section class="video-section">
			<h2>추천 운동 영상</h2>
			<div class="card-container">
				<%-- 여기서는 DB에서 가져온 운동 영상 데이터를 JSP로 출력 --%>
				<!-- 반복적으로 카드 추가 -->
				<div class="card">
					<iframe src="https://www.youtube.com/embed/ilRZoulqWnM?si=cPLwtVbmTU2pYO2-" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
					<h3>운동 이름1</h3>
					<table>
						<tr>
							<td><strong>운동 종목:</strong></td>
							<td>요가</td>							
						</tr>
						<tr>
						<td><strong>운동 부위:</strong></td>
						<td>전신</td>
						</tr>
					</table>
					<a href="workout.jsp" class="start-btn">운동가기</a>
					<a class="start-btn" href="<%= request.getContextPath() %>/main?action=save">찜</a>	
				</div>
				<div class="card">
					<iframe src="https://www.youtube.com/embed/MQPclBNzo6w?si=eBgRargRFgoH0Q52" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
					<h3>운동 이름2</h3>
					<table>
						<tr>
							<td><strong>운동 종목:</strong></td>
							<td>요가</td>							
						</tr>
						<tr>
						<td><strong>운동 부위:</strong></td>
						<td>전신</td>
						</tr>
					</table>
					<a href="workout.jsp" class="start-btn">운동가기</a>
					<a href="workout.jsp" class="start-btn">찜</a>	
				</div>
				<div class="card">
					<iframe src="https://www.youtube.com/embed/EbGh3ME7NLI?si=naMKgttZj6-5Cize" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
					<h3>운동 이름3</h3>
					<table>
						<tr>
							<td><strong>운동 종목:</strong></td>
							<td>요가</td>							
						</tr>
						<tr>
						<td><strong>운동 부위:</strong></td>
						<td>전신</td>
						</tr>
					</table>
					<a href="workout.jsp" class="start-btn">운동가기</a>
					<a href="workout.jsp" class="start-btn">찜</a>
				</div>
			</div>
		</section>
	</main>
</body>
</html>
