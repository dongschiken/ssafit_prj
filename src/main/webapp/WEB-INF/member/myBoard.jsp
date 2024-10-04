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

            .board-list { 
                list-style: none;
                padding: 0; 
            }

            .board-item { 
                background: #fff; 
                border: 1px solid #ddd; 
                margin-bottom: 1rem; 
                padding: 1rem; 
            }

            .board-header { 
                display: flex; 
                justify-content: space-between; 
                margin-bottom: 0.5rem; 
            }

            .board-actions { 
                margin-top: 0.5rem;
				display: flex;
				justify-content: end;
				
            }

            .board-actions button { 
                margin-right: 0.5rem; 
            }

            .no-boards { 
                text-align: center; 
                padding: 2rem; 
                background: #f9f9f9; 
            }
			
			svg {
				width: 22px;
				height: 22px;
				color: rgb(156, 156, 237);
			}
            
            #boardList > li > div.board-header > h4 > a {
            	text-decoration: none;
            	color: black;
            }
            
        </style>
    </head>
    <body>
		<script>
			document.addEventListener('DOMContentLoaded', function() {
			    const select = document.getElementById('selectTag');
				
			    select.addEventListener('change', function() {
			        const option = this.value;
			        if (option) {
			            location.href = `<%=request.getContextPath()%>/member?action=myBoard&option=\${option}`;
			        } 
			    }); 
			});
			
			function deleteBoard(element) {
				location.href = `<%=request.getContextPath()%>/member?action=boardDelete&value=\${element.value}`
            }
			
            <!--도큐멘트가 로드되고 실행시켜야한다.-->
				document.addEventListener('DOMContentLoaded', function () {
					document.body.addEventListener('click', function(event) {
					    if (event.target.classList.contains('btn.btn-light')) {
					       deleteBoard(event.target);
					    }
					});
				});
			
		</script>
        <%@ include file="../include/header.jsp" %>
        
        <div class="container">
            <header>
                <h3><strong>${sessionScope.member.memberName}</strong> 님의 작성 게시글 모음</h3>
                <label for="selectTag"></label>
				<select id="selectTag" style="width: 10rem" class="form-select" aria-label="Default select example">
                    <option ${option == 'newest' ? 'selected' : ''} value="newest">최신순</option>
                    <option ${option == 'oldest' ? 'selected' : ''} value="oldest" >과거순</option>
                </select>
            </header>
            
            <main>
				<c:choose>
					<c:when test="${not empty boardList}">
						<ul id="boardList" class="board-list">
                            <c:forEach items="${ boardList }" var="entry">
                                <li class="board-item">
                                	${ entry.value.videoUrl }
                                    <div class="board-header">
                                        <h4><a href="${pageContext.request.contextPath}/board?action=detail&id=${entry.key}">글 제목 : ${entry.value.title}</a></h4>
                                        <span>작성일 : ${entry.value.regDate}</span>
                                    </div>
                                    <p>작성 내용 : ${entry.value.content}</p>
                                    <div class="board-actions">
                                        <button type="button" value=${entry.key} onclick="deleteBoard(this);" class="btn btn-light" style="width: 100px; height: 35px;" >삭제</button>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
					</c:when>	
					<c:otherwise>
                        <div class="no-boards">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-square-text" viewBox="0 0 16 16">
							  <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1h-2.5a2 2 0 0 0-1.6.8L8 14.333 6.1 11.8a2 2 0 0 0-1.6-.8H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h2.5a1 1 0 0 1 .8.4l1.9 2.533a1 1 0 0 0 1.6 0l1.9-2.533a1 1 0 0 1 .8-.4H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z"/>
							  <path d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5M3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6m0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5"/>
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