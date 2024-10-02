<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>운동영상 리뷰 상세화면</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
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

        .comments-section {
            margin-top: 30px;
            border-top: 1px solid #eaeaea;
            padding-top: 15px;
        }
        
        .comment-list {
            margin-top: 30px;
        }

        .comment-box textarea {
            width: 100%;
            height: 100px;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 10px;
        }

        .comment-item {
            border-bottom: 1px solid #eaeaea;
            padding-bottom: 10px;
            margin-bottom: 10px;
        }

        .comment-author {
            font-weight: bold;
        }

        .comment-date {
            font-size: 12px;
            color: #606060;
        }

        .comment-content {
            margin-top: 5px;
        }

        #submitComment {
            background-color: rgb(176, 176, 250);
            border-color: rgb(176, 176, 250);
        }
    </style>
</head>

<body>
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
                <div class="video-meta">
                    조회수 ${board.viewCnt} • ${board.regDate} • 작성자: ${board.writer}
                </div>

                <!-- Video Description -->
                <div class="video-description">
                    <p>${board.content}</p>
                </div>

                <!-- Comments Section -->
                <div class="comments-section">
                    <h5>댓글</h5>

                    <!-- Comment Form -->
                    <div class="comment-box">
                        <textarea id="commentContent" placeholder="댓글을 작성해 주세요"></textarea>
                        <button class="btn btn-primary" id="submitComment">댓글 등록</button>
                    </div>

                    <!-- Comment List -->
                    <div class="comment-list">
                        <div class="comment-item">
                            <div class="comment-author">b</div>
                            <div class="comment-date">2024-08-31</div>
                            <div class="comment-content">정말 좋은 영상입니다. 저도 효과를 봤어요!</div>
                        </div>
                        <div class="comment-item">
                            <div class="comment-author">c</div>
                            <div class="comment-date">2024-09-01</div>
                            <div class="comment-content">운동 효과 정말 대단하네요. 추천합니다!</div>
                        </div>
                        <!-- More comments -->
                    </div>
                </div>
            </div>

            <!-- Related Videos Section -->
            <div class="col-md-4">
                <h5>관련 영상</h5>
                </div>
            </div>
        </div>
    </div>

    <script>
        // JavaScript for comment submission (this can be replaced by server-side logic)
        const submitCommentBtn = document.querySelector('#submitComment');
        submitCommentBtn.addEventListener('click', function () {
            const commentContent = document.querySelector('#commentContent').value;
            if (commentContent.trim() === "") {
                alert("댓글 내용을 입력해 주세요.");
                return;
            }

            // Simple client-side comment insertion (replace with server-side logic)
            const commentList = document.querySelector('.comment-list');
            const newComment = document.createElement('div');
            newComment.classList.add('comment-item');
            newComment.innerHTML = `
                <div class="comment-author">익명</div>
                <div class="comment-date">방금 전</div>
                <div class="comment-content">${commentContent}</div>
            `;
            commentList.appendChild(newComment);

            // Clear the textarea
            document.querySelector('#commentContent').value = "";
        });
    </script>
</body>

</html>