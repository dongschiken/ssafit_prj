<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-container {
            width: 60%;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input[type="text"], .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .form-group textarea {
            height: 200px;
            resize: none;
        }
        .form-group select {
            padding-right: 40px;
            position: relative;
            background-repeat: no-repeat;
            background-position: right 10px center;
            border-radius: 5px; 
            border: 1px solid #ccc; 
            height: 25px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: rgb(201, 201, 253);
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: rgb(176, 176, 250);
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>게시글 작성</h2>
        <form action="${pageContext.request.contextPath}/board" method="post">
        	<intput type="hidden" name="action" value="write" />
            <!-- 제목 입력 -->
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
            </div>
            
            <!-- 작성자는 로그인된 회원으로 자동 설정 -->
            <div class="form-group">
                <label for="writer">작성자</label>
                <input type="text" id="writer" name="writer" value="${ sessionScope.member.memberName }" readonly>
            </div>
            
            <!-- 내용 입력 -->
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" required></textarea>
            </div>

            <!-- 운동 정보 입력 -->
            <div class="form-group">
                <label for="workout">운동 정보</label>
                <input type="text" id="workout" name="workout">
            </div>

            <!-- 제출 버튼 -->
            <div class="form-group">
                <button type="submit">작성 완료</button>
            </div>
        </form>
    </div>

</body>
</html>
