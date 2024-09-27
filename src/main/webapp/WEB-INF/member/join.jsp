<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./assets/join.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <script src="./assets/join.js"></script>
  <title>Document</title>
  <style type="text/css">
  	* {
    padding: 0;
    margin: 0;
	}
	
	html,
	body {
	    height: 100%;
	}
	
	body > form > div {
	    display: flex;
	    flex-direction: row;
	    justify-content: center;
	    align-items: center;
	    height: 100%;
	    border: 1px solid gray;
	    box-sizing: border-box;
	}

	.main-container > .input-table {
	    width: 300px;
	    border: 1px solid gray;
	}
	
	body>form>div>table>tbody>tr>td>div {
	    border: 1px solid gray;
	    border-width: 0 0 1px;
	    margin-bottom: 14px;
	    color: gray;
	    margin-top: 14px;
	    padding-bottom: 10px;
	}
	
	body>div>table>tbody>tr:nth-child(1)>th>h1 {
	    color: gray;
	    text-align: center;
	    border: 1px solid gray;
	    border-width: 0 0 1px;
	    padding-bottom: 20px;
	
	}
	
	body>div>table>tfoot>tr>td:nth-child(1)>button {
	    margin-top: 15px;
	}
	
	body>div>table>tfoot>tr>td:nth-child(2)>button {
	    margin-top: 15px;
	}
	
	input:focus {
	    outline: none;
	}
	
	input {
	    border-width: 0;
	    padding-left: 10px;
	}
  </style>
</head>
<body>
<form action="${ pageContext.request.contextPath }/member" method="POST">
<input type="hidden" name="action" value="join">
<div class="main-container">
  <table class=".input-table">
    <tbody>
      <tr>
        <th colspan="2"><h1>회원가입</h1></th>
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
              <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
            </svg>
            <input type="text" id="id" placeholder="아이디"></td>
          </div>
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
              <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325"/>
            </svg>
            <input type="text" id="name" placeholder="이름"></td>
          </div>
      
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
              <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
            </svg>
            <input type="email" id="email" placeholder="이메일"></td>
          </div>
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
              <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2"/>
            </svg>
            <input type="password" id="password" placeholder="비밀번호"></td>
          </div>
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
              <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2"/>
            </svg>
            <input type="password" id="password-check" placeholder="비밀번호 확인"></td>
          </div>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td><button id="joinbtn" type="submit" class="btn btn-light" style="width: 180px; height: 50px; background-color: rgb(201, 201, 253)">가입</button></td>
        <td><button type="reset" class="btn btn-light" style="width: 180px; height: 50px;" >취소</button></td>
      </tr>
    </tfoot>
  </table>
</div>
</form>
</body>
</html>
