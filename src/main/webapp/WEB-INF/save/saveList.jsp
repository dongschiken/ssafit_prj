<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>내 찜 목록</title>
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

		.main {
			margin: 50px auto;
			text-align: center;
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
			width: 200px;
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

		.card p {
			font-size: 14px;
			color: #666;
		}

		.card button {
			margin-top: 10px;
			padding: 10px;
			border: none;
			border-radius: 5px;
			background-color: rgb(201, 201, 253);
			color: #333;
			font-weight: bold;
			cursor: pointer;
		}

		.card button:hover {
			background-color: #9999ff;
			color: white;
		}

		.sort-section {
			margin-bottom: 20px;
		}

		.sort-section select {
			padding: 10px;
			border-radius: 5px;
			font-size: 16px;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#sortSelect').on('change', function() {
				const option = $(this).val();
				location.href = `<%=request.getContextPath()%>/saveList?action=sort&option=${option}`;
			});
		});
	</script>
</head>
<body>
	<%@ include file="../include/header.jsp" %>

	<div class="main">
		<header>
			<h1>내 찜 목록</h1>
		</header>

		<div class="sort-section">
			<label for="sortSelect">정렬:</label>
			<select id="sortSelect">
				<option value="recent" ${option == 'recent' ? 'selected' : ''}>최근 찜한 순</option>
				<option value="oldest" ${option == 'oldest' ? 'selected' : ''}>과거 찜한 순</option>
			</select>
		</div>

		<div class="card-container">
			<c:choose>
				<c:when test="${not empty saveList}">
					<c:forEach items="${saveList}" var="save">
						<div class="card">
							<img src="${save.imageUrl}" alt="${save.title}">
							<h3>${save.title}</h3>
							<p>${save.description}</p>
							<button value="${save.id}" onclick="doSave(this)">찜 취소</button>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>찜한 운동이 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
