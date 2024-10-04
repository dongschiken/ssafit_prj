<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>운동영상 리뷰 상세화면</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<style>
body {
	background-color: #f9f9f9;
}

.container {
	margin-top: 20px;
}

.video-section {
	margin-bottom: 20px;
}

.video-title {
	font-size: 24px;
	font-weight: bold;
	margin-top: 15px;
}

.video-meta {
	color: #606060;
	font-size: 14px;
	margin-bottom: 15px;
}

.video-description {
	border-top: 1px solid #eaeaea;
	padding-top: 10px;
	margin-bottom: 30px;
}

.related-videos {
	border-left: 1px solid #eaeaea;
	padding-left: 15px;
}

.related-video-item {
	display: flex;
	margin-bottom: 10px;
}

.related-video-thumbnail {
	width: 120px;
	height: 80px;
	margin-right: 10px;
}

.related-video-title {
	font-size: 14px;
	font-weight: bold;
	color: #000;
	margin-bottom: 5px;
}

.related-video-meta {
	font-size: 12px;
	color: #606060;
}

.reviews-section {
	margin-top: 30px;
	border-top: 1px solid #eaeaea;
	padding-top: 15px;
}

.review-list {
	margin-top: 30px;
}

.review-box textarea {
	width: 100%;
	height: 100px;
	border-radius: 10px;
	padding: 10px;
	margin-bottom: 10px;
}

.review-item {
	border-bottom: 1px solid #eaeaea;
	padding-bottom: 10px;
	margin-bottom: 10px;
}

.review-author {
	font-weight: bold;
}

.review-date {
	font-size: 12px;
	color: #606060;
}

.review-content {
	margin-top: 5px;
}

#submitreview {
	background-color: rgb(176, 176, 250);
	border-color: rgb(176, 176, 250);
}
</style>
</head>

<body>
	<%@ include file="../include/header.jsp"%>
	<div class="container">
		<div class="row">
			<!-- Main Video Section -->
			<div class="col-md-8">
				<!-- Video Section -->
				<div class="video-section">
					<iframe width="100%" height="450px"
						src="https://www.youtube.com/embed/50WCSpZtdmA?si=6NS965XyETmZ0vKu"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
				</div>

				<!-- Video Title and Meta Information -->
				<div class="video-title">${board.title}</div>
				<div class="video-meta">조회수 ${board.viewCnt} •
					${board.regDate} • 작성자: ${board.writer}</div>

				<!-- Video Description -->
				<div class="video-description">
					<p>${board.content}</p>
				</div>

				<!-- reviews Section -->
				<div class="reviews-section">
					<h5>댓글</h5>

					<!-- review Form -->
					<div class="review-box">
						<form action="${pageContext.request.contextPath}/review"
							method="post">
							<input type="hidden" name="action" value="write"> <input
								type="hidden" name="id" value="${board.id}">
							<textarea id="review" name="content" rows="5" cols="50" required></textarea>
							<button class="btn btn-primary" id="submitreview">댓글 등록</button>
						</form>
					</div>

					<!-- review List -->
					<c:forEach items="${reviews}" var="entry">
						<div class="review-list">
							<div class="review-item">
								<div class="review-author">${entry.value.memberName}</div>
								<div class="review-date">${entry.value.regDate}</div>
								<div class="review-content">${entry.value.content}</div>
							</div>
						</div>
					</c:forEach>
					<!-- More reviews -->
				</div>
			</div>
		</div>
	</div>

	<script>
        // JavaScript for review submission (this can be replaced by server-side logic)
        const submitreviewBtn = document.querySelector('#submitreview');
        submitreviewBtn.addEventListener('click', function () {
            const reviewContent = document.querySelector('#reviewContent').value;
            if (reviewContent.trim() === "") {
                alert("댓글 내용을 입력해 주세요.");
                return;
            }

            // Simple client-side review insertion (replace with server-side logic)
            const reviewList = document.querySelector('.review-list');
            const newreview = document.createElement('div');
            newreview.classList.add('review-item');
            newreview.innerHTML = `
                <div class="review-author">익명</div>
                <div class="review-date">방금 전</div>
                <div class="review-content">${reviewContent}</div>
            `;
            reviewList.appendChild(newreview);

            // Clear the textarea
            document.querySelector('#reviewContent').value = "";
        });
    </script>
</body>

</html>