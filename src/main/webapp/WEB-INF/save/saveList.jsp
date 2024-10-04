<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./assets/join.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script src="./assets/join.js"></script>
<title>Insert title here</title>
<style>
/*
body {
	line-height: 1.6;
	margin: 0;
	padding: 0;
}

.container {
	padding-top: 40px;
	width: 80%;
	margin: auto;
	overflow: hidden;
}

header {
	background: #f4f4f4;
	padding: 1rem;
}

main {
	padding: 2rem 0;
}

.save-list {
	list-style: none;
	padding: 0;
}

.save-item {
	background: #fff;
	border: 1px solid #ddd;
	margin-bottom: 1rem;
	padding: 1rem;
}

.save-header {
	display: flex;
	justify-content: space-between;
	margin-bottom: 0.5rem;
}

.save-actions {
	margin-top: 0.5rem;
	display: flex;
	justify-content: end;
}

.save-actions button {
	margin-right: 0.5rem;
}

.no-saves {
	text-align: center;
	padding: 2rem;
	background: #f9f9f9;
}
  */
body {
	margin: 0;
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	color: #333;
}

.container {
	padding-top: 40px;
	width: 80%;
	margin: auto;
	overflow: hidden;
}

header {
	background: #f4f4f4;
	padding: 1rem;
}

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
	width: 250px; /* 카드의 너비 */
	padding: 20px;
	text-align: center;
	transition: transform 0.15s ease, box-shadow 0.15s ease;
}

.card img {
	width: 100%;
	border-radius: 5px;
}

.card:hover {
	transform: scale(1.05);
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.card h3 {
	font-size: 20px;
	margin: 10px 0;
}

.card table {
	width: 100%;
	margin: 10px 0;
}

.card td {
	text-align: left;
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    // 찜 상태 변경 함수
    	function doSave(element) {
            var boardId = element.value;  // 운동 id 추출
            var jsonData = JSON.stringify({'boardId' : boardId});
            
            // AJAX 요청 보내기
            $.ajax({
                url : '<%=request.getContextPath()%>/save?action=save',
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
	<script>

            function deletesave(element) {
				location.href = `<%=request.getContextPath()%>/save?action=saveList&value=\${element.value}`
            }
            
			document.addEventListener('DOMContentLoaded', function() {
			    const select = document.getElementById('selectTag');
				
			    select.addEventListener('change', function() {
			        const option = this.value;
			        if (option) {
			            location.href = `<%=request.getContextPath()%>
		/save?action=saveList&option=\${option}`;
												}
											});
						});

		document.addEventListener('DOMContentLoaded', function() {
			document.body.addEventListener('click', function(event) {
				if (event.target.classList.contains('btn.btn-light')) {
					deletesave(event.target);
				}
			});
		});
	</script>
	<%@ include file="../include/header.jsp"%>

	<div class="container">
		<header>
			<h3>
				<strong>${sessionScope.member.memberName}</strong> 님의 찜 목록
			</h3>
			<label for="selectTag"></label> <select id="selectTag"
				style="width: 10rem" class="form-select"
				aria-label="Default select example">
				<option ${option == 'newest' ? 'selected' : ''} value="newest">최신순</option>
				<option ${option == 'oldest' ? 'selected' : ''} value="oldest">과거순</option>
			</select>
		</header>

		<main>
			<section class="video-section">
				<div class="card-container">
					<%-- <c:choose>
						<c:when test="${ not empty saveList }">
							<c:forEach items="${ saveList }" var="save">
								<div class="card">
									${save.board.videoUrl}
									<h3>${ save.board.title }</h3>
									<table>
										<tr>
											<td><strong>운동 종류 :</strong></td>
											<td>${ save.board.workOutName }</td>
										</tr>
										<tr>
											<td><strong>조회수 :</strong></td>
											<td>${ save.board.viewCnt }</td>
										</tr>
									</table>
									<button onclick="doDetail(this)" value="${ save.board.id }"
										class="start-btn workout-btn">운동가기</button>
									<button class="start-btn save-btn" value="${ save.board.id }"
										onclick="doSave(this)">찜</button>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="no-saves">찜한 운동이 없습니다.</div>
						</c:otherwise>
					</c:choose> --%>
					<c:choose>
						<c:when test="${ not empty saveList }">
						<c:forEach items="${ saveList }" var="save" >
						<div class="card">
							${save.board.videoUrl}
							<h3>${ save.board.title }</h3>
							<table>
								<tr>
									<td><strong>운동 종류 :</strong></td>
									<td>${ save.board.workOutName }</td>
								</tr>
								<tr>
									<td><strong>조회수 :</strong></td>
									<td>${ save.board.viewCnt }</td>
								</tr>
							</table>
							<button onclick="doDetail(this)" value="${ save.board.id }" class="start-btn workout-btn">운동가기</button>	
							<c:choose>
							    <c:when test="${not empty saveList}">
							        <c:set var="buttonRendered" value="false" />
							        <c:forEach items="${saveList}" var="save" varStatus="status">
							            <c:if test="${not buttonRendered}">
							                <c:choose>
							                    <c:when test="${save.boardId == save.board.id}">
							                        <button class="start-btn save-btn" value="${save.board.id}" onclick="doSave(this)">찜 취소</button>
							                        <c:set var="buttonRendered" value="true" />
							                    </c:when>
							                    <c:when test="${status.last}">
							                        <button class="start-btn save-btn" value="${save.board.id}" onclick="doSave(this)">찜</button>
							                        <c:set var="buttonRendered" value="true" />
							                    </c:when>
							                </c:choose>
							            </c:if>
							        </c:forEach>
							    </c:when>
							    <c:otherwise>
							        <button class="start-btn save-btn" value="${save.board.id}" onclick="doSave(this)">찜</button>
							    </c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
					</c:when>
				</c:choose>
				</div>
			</section>
		</main>
	</div>
</body>
</html>