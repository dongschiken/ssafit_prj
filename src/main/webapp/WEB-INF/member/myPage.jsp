<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <style type="text/css">
			    * {
			        padding: 0;
			        margin: 0;
			    }
			    
			    html, body {
			        height: 100%;
			    }
			    
			    body {
			        display: flex;
			        flex-direction: column;
			    }
			    
			    .mypage-main-container {
			        flex: 1;
			        display: flex;
			        flex-direction: column;
			        justify-content: start;
			        align-items: center;
			        padding: 20px;
					max-height: 400px;
			    }
			    
			    .mypage-header, .ssafit-pay-section {
			        width: 100%;
			       	max-width: 370px;
					margin-top: 30px;
			        margin-bottom: 20px;
			        text-align: center;
			    }
				
				.ssafit-pay-section {
					border: 1px solid gray;
					border-radius: 15px;
					
				}
				
			    .ssfit-pay-button-group {
			        display: flex;
			        justify-content: space-evenly;
			        gap: 10px;
			    }
			    
			    .ssfit-pay-button-group button {
			        flex: 1;
			        max-width: 90px;
					padding-bottom: 10px;
					margin-bottom: 10px;
			    }
				.ssafit-pay-header {
					display: flex;
					gap: 10px;
					max-width: 327px;
					margin-top: 10px;
					margin-left: 20px;
					padding-bottom: 15px;
					justify-content: space-between;
					align-items: center;
					align-content: center;
				}
				.mypage-activity-section {
					max-width: 360px;
					width: 360px;	
				}
                body > div.mypage-main-container > div.ssafit-pay-section > div.ssafit-pay-header > div:nth-child(1) > h4 {
                    margin-left: 5px;
                }
				body > div.mypage-main-container > div.ssafit-pay-section > div.ssafit-pay-header > div:nth-child(2) > a {
                    color: black;
                    text-underline-position: under;
                    
				}
				
				.mypage-activity-section > .mypage-activity-content > div > svg {
					width: 22px;
					height: 22px;
					color: rgb(156, 156, 237);
				}
				
				.mypage-activity-section > .mypage-activity-content > div {
					margin-top: 25px;
					padding: 5px;
				}
				
				.mypage-activity-section > .mypage-activity-header {
					margin-top: 10px;	
				}
				body > div.mypage-main-container > div.mypage-activity-section > div.mypage-activity-content > div > a {
					text-decoration: none;
					color: black;
					margin-left: 20px;
					font-size: 17px;
					font-weight: bold;
				}
        </style>
    </head>
    <script>

    function clickSaveBtn(element) {
        var boardId = document.getElementById("boardId");
  
        var idJson = { 'boardId': boardId };
        
	    // 서버 경로 및 AJAX 요청 설정
	    var contextPath = "<%= request.getContextPath() %>";
	    $.ajax({
	        type: "POST",
	        url: contextPath + "/save?action=save",
	        dataType: 'json',  // jQuery에서는 dataType 소문자로 씁니다.
	        data: idJson,
	        cache: false,
	        contentType: "application/json",  // 일반적으로 GET 요청에서는 contentType을 설정하지 않습니다.
	        success: function(response) {
                // 성공했을때 코드
	        },
	        error: function(xhr, status, error) {
                // 실패했을때 코드
	        }
	    });
	}
	
    </script>
    <body>
        <%@ include file="../include/header.jsp" %>
		<div class="mypage-main-container">
		    <div class="mypage-header">
		        <h3><b>${sessionScope.member.memberName}</b> 님의 마이페이지</h3>
		    </div>
		    <div class="ssafit-pay-section">
		        <div class="ssafit-pay-header">
					<div style="display: flex;">
					<svg xmlns="http://www.w3.org/2000/svg" style="margin-top:3px; color:rgb(156, 156, 237);" width="25" height="25" fill="currentColor" class="bi bi-credit-card" viewBox="0 0 16 16">
					  <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1z"/>
					  <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z"/>
					</svg>
		            <h4 style="color:rgb(156, 156, 237);">ssafit 페이</h4>
					</div>
					<div>
                    <a href="">25,000원</a>
					</div>
		        </div>
		        <div class="ssfit-pay-button-group">
		            <button class="btn btn-light">충전</button>
		            <button class="btn btn-light">계좌등록</button>
		            <button class="btn btn-light">송금</button>
		        </div>
		    </div>
			<div class="mypage-activity-section">
				<div class="mypage-activity-header"><h5><strong>나의 활동</strong></h5></div>
				<div class="mypage-activity-content">
					
                    <div>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
						  <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0m4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
						  <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9 9 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.4 10.4 0 0 1-.524 2.318l-.003.011a11 11 0 0 1-.244.637c-.079.186.074.394.273.362a22 22 0 0 0 .693-.125m.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6-3.004 6-7 6a8 8 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a11 11 0 0 0 .398-2"/>
						</svg>
					<a href="${pageContext.request.contextPath}/member?action=myReview">댓글 보기</a></div>
                    <div>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
						  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
						  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
						</svg>
					<a href="${pageContext.request.contextPath}/member?action=myBoard">작성글 보기</a></div>
                    <div>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
						  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
						  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
						</svg>
					<a href="#">팔로우 보기</a></div>
                    <div>
						<svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-chat-heart" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M2.965 12.695a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6-3.004 6-7 6a8 8 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a11 11 0 0 0 .398-2m-.8 3.108.02-.004c1.83-.363 2.948-.842 3.468-1.105A9 9 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.4 10.4 0 0 1-.524 2.318l-.003.011a11 11 0 0 1-.244.637c-.079.186.074.394.273.362a22 22 0 0 0 .693-.125M8 5.993c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132"/>
						</svg>
					<a href="${pageContext.request.contextPath}/save?action=saveList">찜목록 보기</a></div>
				</div>
			</div>
		</div>
<!--		<div class="mypage-footer-container">
			회원정보 확인
			회원정보 수정
		</div>	-->
    </body>
    </html>