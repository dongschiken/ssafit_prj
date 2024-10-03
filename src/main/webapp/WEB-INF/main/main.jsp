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

.card>iframe {
	width: 230px;
	height: 170px;
	border-radius: 5px;
}

.start-btn {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: rgb(201, 201, 253);
	color: #333;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
}

.start-btn:hover {
	background-color: #9999ff;
	color: white;
}

.start-btn:focus {
	outline: none;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
}
</style>
<!-- <link rel="stylesheet" href="/WEB-INF/main/styles.css"> -->
<!-- 제이쿼리 라이브러리 포함 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    // 찜 상태 변경 함수
    	function doSave(element) {
            var boardId = element.value;  // 운동 id 추출
            var jsonData = JSON.stringify({'boardId' : boardId});
            
            // AJAX 요청 보내기
            $.ajax({
                url: '<%=request.getContextPath()%>
	/save?action=save', // 찜 상태를 변경하는 엔드포인트
			type : 'POST',
			data : jsonData, // 서버로 전송할 데이터 (운동 id)
			dataType : 'json',
			contentType : 'application/json',
			success : function(response) {
				if (response.saved) { // 응답에서 저장 여부 확인
					alert("찜 목록에 추가됨");
					$(button).text('찜 취소'); // 버튼 텍스트 변경
				} else {
					alert('찜 목록에서 삭제됨');
					$(button).text('찜'); // 버튼 텍스트 변경
				}
			},
			error : function(xhr, status, error) {
				alert('찜 상태 변경 중 오류가 발생했습니다.');
			}
		});
	}
</script>
</head>
<body>
	<!-- 헤더 영역 -->
	<%@ include file="../include/header.jsp"%>

	<!-- 메인 섹션 -->
	<main>
		<section class="intro">
			<h1>SSAFit과 함께 운동할까요?</h1>
			<a href="<%=request.getContextPath()%>/member?action=routineForm"
				class="start-btn">운동하러 가기</a> <a
				href="<%=request.getContextPath()%>/member?action=list"
				class="start-btn">운동 게시판 이동하기</a>
		</section>

		<!-- 추천 운동 영상 카드 -->
		<section class="video-section">
			
			<h2></h2>
			<div class="card-container">
				<%-- 여기서는 DB에서 가져온 운동 영상 데이터를 JSP로 출력 --%>
				<!-- 반복적으로 카드 추가 -->
				<c:choose>
						<c:when test="${ not empty list }">
						<c:foreach items="${ list }" var="entry" >
						<div class="card">
							${entry.value.videoUrl}
							<h3>${ entry.value.title }</h3>
							<table>
								<tr>
									<td><strong>${ entry.value.workOut }:</strong></td>
									<td>${ entry.value.workOut }</td>
								</tr>
								<tr>
									<td><strong>${ entry.value.workOut }:</strong></td>
									<td>전신</td>
								</tr>
							</table>
							<button class="start-btn workout-btn">운동가기</button>
							<button class="start-btn save-btn" value=3 onclick="doSave(this)">찜</button>
						</div>
						</c:foreach>
					</c:when>
				</c:choose>
			</div>
		</section>
	</main>
</body>
</html>
