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
			
			svg {
				width: 22px;
				height: 22px;
				color: rgb(156, 156, 237);
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
                url: '<%=request.getContextPath()%>/save?action=save', // 찜 상태를 변경하는 엔드포인트
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
			            location.href = `<%=request.getContextPath()%>/save?action=saveList&option=\${option}`;
			        } 
			    }); 
			});

				document.addEventListener('DOMContentLoaded', function () {
					document.body.addEventListener('click', function(event) {
					    if (event.target.classList.contains('btn.btn-light')) {
					       deletesave(event.target);
					    }
					});
				});
		</script>
        <%@ include file="../include/header.jsp" %>
        
        <div class="container">
            <header>
                <h3><strong>${sessionScope.member.memberName}</strong> 님의 찜 목록</h3>
                <label for="selectTag"></label>
				<select id="selectTag" style="width: 10rem" class="form-select" aria-label="Default select example">
                    <option ${option == 'newest' ? 'selected' : ''} value="newest">최신순</option>
                    <option ${option == 'oldest' ? 'selected' : ''} value="oldest" >과거순</option>
                </select>
            </header>
            
            <main>
					<c:choose>
						<c:when test="${ not empty list }">
						<c:forEach items="${ list }" var="entry" >
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
						</c:forEach>
					</c:when>
					<c:otherwise>
                        <div class="no-saves">
                            <h4>찜 내역이 없습니다.</h4>
                        </div>
					</c:otherwise>
				</c:choose>
            </main>
        </div>
        </body>
    </html>