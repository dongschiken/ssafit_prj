<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="./assets/join.css">
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
				crossorigin="anonymous"></script>
			<script src="./assets/join.js"></script>
        <title>Insert title here</title>
        <style>
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

            .comment-list { 
                list-style: none;
                padding: 0; 
            }

            .comment-item { 
                background: #fff; 
                border: 1px solid #ddd; 
                margin-bottom: 1rem; 
                padding: 1rem; 
            }

            .comment-header { 
                display: flex; 
                justify-content: space-between; 
                margin-bottom: 0.5rem; 
            }

            .comment-actions { 
                margin-top: 0.5rem;
				display: flex;
				justify-content: end;
				
            }

            .comment-actions button { 
                margin-right: 0.5rem; 
            }

            .no-comments { 
                text-align: center; 
                padding: 2rem; 
                background: #f9f9f9; 
            }
			
			svg {
				width: 22px;
				height: 22px;
				color: rgb(156, 156, 237);
			}
            
        </style>
    </head>
    <body>
		<script>

            function deleteReview(element) {
				location.href = `<%=request.getContextPath()%>/member?action=reviewDelete&value=\${element.value}`
            }
            
			document.addEventListener('DOMContentLoaded', function() {
			    const select = document.getElementById('selectTag');
				
			    select.addEventListener('change', function() {
			        const option = this.value;
			        if (option) {
			            location.href = `<%=request.getContextPath()%>/member?action=myReview&option=\${option}`;
			        } 
			    }); 
			});

            <!--도큐멘트가 로드되고 실행시켜야한다.-->
				document.addEventListener('DOMContentLoaded', function () {
					document.body.addEventListener('click', function(event) {
					    if (event.target.classList.contains('btn.btn-light')) {
					       deleteReview(event.target);
					    }
					});
				});
		</script>
        <%@ include file="../include/header.jsp" %>
        
        <div class="container">
            <header>
                <h3><strong>${sessionScope.member.memberName}</strong> 님의 댓글 모음</h3>
                <label for="selectTag"></label>
				<select id="selectTag" style="width: 10rem" class="form-select" aria-label="Default select example">
                    <option ${option == 'newest' ? 'selected' : ''} value="newest">최신순</option>
                    <option ${option == 'oldest' ? 'selected' : ''} value="oldest" >과거순</option>
                </select>
            </header>
            
            <main>
				<c:choose>
					<c:when test="${not empty reviewList}">
						<ul id="reviewList" class="comment-list">
                            <c:forEach items="${ reviewList }" var="review">
                                <li class="comment-item">
                                    <div class="comment-header">
                                        <h4>글 제목 : ${review.boardTitle}</h4>
                                        <span>${review.regDate}</span>
                                    </div>
                                    <p>내 댓글 : ${review.content}</p>
                                    <div class="comment-actions">
                                        <button value=${review.id} onclick="deleteReview(this);" type="button" class="btn btn-light" style="width: 100px; height: 35px;" >삭제</button>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
					</c:when>	
					<c:otherwise>
                        <div class="no-comments">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
                                <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0m4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
                                <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9 9 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.4 10.4 0 0 1-.524 2.318l-.003.011a11 11 0 0 1-.244.637c-.079.186.074.394.273.362a22 22 0 0 0 .693-.125m.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6-3.004 6-7 6a8 8 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a11 11 0 0 0 .398-2"/>
                              </svg>
                            <h4>활동 내역이 없습니다.</h4>
                            <p>커뮤니티의 영향력을 키워보세요.</p>
                        </div>
					</c:otherwise>
				</c:choose>
            </main>
        </div>
        </body>
    </html>