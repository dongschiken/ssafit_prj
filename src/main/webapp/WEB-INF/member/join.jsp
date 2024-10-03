<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<html>

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="./assets/join.css">
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
				crossorigin="anonymous">
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
				crossorigin="anonymous"></script>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

				body>form>div {
					display: flex;
					flex-direction: row;
					justify-content: center;
					align-items: center;
					height: 1000px;
					border: 1px solid gray;
					box-sizing: border-box;
				}

				.main-container>.input-table {
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

				body>form>div>table>tbody>tr:nth-child(1)>th>h1 {
					color: gray;
					text-align: center;
					border: 1px solid gray;
					border-width: 0 0 1px;
					padding-bottom: 20px;
					padding-top: 30px;
				}

				body>form>div>table>tfoot>tr>td:nth-child(1)>button {
					margin-top: 15px;
				}

				body>form>div>table>tfoot>tr>td:nth-child(2)>button {
					margin-top: 15px;
				}

				input:focus {
					outline: none;
				}

				input {
					width: 330px;
					border-width: 0;
					padding-left: 10px;
				}

				address-container {
					display: flex;
					flex-direction: row;
				}

				body>form>div>table>tbody>tr:nth-child(7)>td.address {
					flex-direction: row;
				}

				body>form>div>table>tbody>tr:nth-child(7)>th>div {
					margin-top: 80px;
				}

				body>form>div>table>tfoot>tr>td>button {
					width: 230px;
					height: 50px;
				}

				body>form>div>table>tbody>tr:nth-child(4)>td>div>select {
					width: 96px;
				}

				.phone-container {
					display: flex;
				}

				#hpno2 {
					margin-left: 30px;
				}

				#hpno3 {
					margin-left: 30px;
				}

				body>form>div>table>tbody>tr:nth-child(9)>th>div {
					margin-top: 30px;
				}
			</style>
			<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
				function execDaumPostcode() {
					new daum.Postcode({
						oncomplete: function (data) {
							var roadAddr = data.roadAddress; // 도로명 주소 변수
							var extraRoadAddr = ''; // 참고 항목 변수

							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
								extraRoadAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== '' && data.apartment === 'Y') {
								extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraRoadAddr !== '') {
								extraRoadAddr = ' (' + extraRoadAddr + ')';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('postNum').value = data.zonecode;
							document.getElementById("roadAddress").value = roadAddr;
							document.getElementById("jibunAddress").value = data.jibunAddress;
						}
					}).open();

				}
				<!--도큐멘트가 로드되고 실행시켜야한다.-->
				document.addEventListener('DOMContentLoaded', function () {
					document.querySelector("#duplicate_btn").addEventListener('click', () => {
						let memberId = document.querySelector("#id").value;
						$.ajax({
							type: "GET",
							url: "${pageContext.request.contextPath}/member?action=idCheck&memberId=" + memberId,
							dataType: 'json',
							cache: false,
							success: function (response) {
								if (response.isDuplicate) {
									alert("사용 가능한 아이디 입니다.");
								} else {
									alert("이미 사용중인 아이디 입니다.");
									document.querySelector("#id").value = null;
								}
							},
							error: function (xhr, status, error) {
								// 오류 시 처리 로직
							}
						});
					});

					document.querySelector('form').addEventListener('submit', function (e) {
					if (!test()) {
						e.preventDefault(); // 폼 제출 방지
					}
				});

				function test() {
					var p1 = document.getElementById('password').value;
					var p2 = document.getElementById('password-check').value;
					var num = p1.search(/[0-9]/g);
					var eng = p1.search(/[a-z]/ig);
					var spe = p1.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
					if (p1.length == 0) {
						alert("비밀번호가 비어있습니다.");
						return false;
					} else if (p1.search(/\s/) != -1) {
						alert("비밀번호는 공백이 없어야합니다.");
						return false;
					} else if (p1 != p2) {
						alert("비밀번호불일치");
						return false;
					} else if (num < 0 || eng < 0 || spe < 0) {
						alert("비밀번호는 영문,숫자, 특수문자를 혼합하여 입력해주세요.");
						return false;
					} else {
						alert("회원가입을 축하합니다.");
						return true;
					}
				}
				});
			</script>
		</head>

		<body>
			<form action="${ pageContext.request.contextPath }/member" method="POST">
				<input type="hidden" name="action" value="join">
				<div class="main-container">
					<table class=".input-table">
						<tbody>
							<tr>
								<th colspan="2">
									<h1>회원가입</h1>
								</th>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
											<path
												d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
										</svg>
										<label for="id"></label>
										<input type="text" name="id" id="id" required placeholder="아이디" width="130px">
										<label for="duplicate_btn"></label>
										<button type="button" id="duplicate_btn" class="btn btn-light"
											style="width: 100px; height: 35px;">중복확인</button>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
											<path
												d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
										</svg>
										<input type="text" id="name" required name="name" placeholder="이름">
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row" class="vt">
									<div style="">전화번호</div>
								</th>
							</tr>
							<tr>
								<td colspan="2">
									<div class="phone-container">
										<select name="phno1" class="form-select" style="width:96px"
											aria-label="Default select example">
											<option selected value="010">010</option>
											<option value="011">011</option>
											<option value="016">016</option>
											<option value="017">017</option>
											<option value="018">018</option>
											<option value="019">019</option>
										</select>
										<input type="text" required name="phno2" id="hpno2" title="휴대폰 중간자리 입력" value=""
											class="input_text small" maxlength="4" style="width:96px;">
										<span style="margin-top: 10px" class="text">-</span>
										<input type="text" required name="phno3" id="hpno3" title="휴대폰 마지막자리 입력"
											value="" class="input_text small last" maxlength="4" style="width:96px;">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
											<path
												d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z" />
										</svg>
										<input type="email" required name="email" id="email" placeholder="이메일">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
											<path
												d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2" />
										</svg>
										<input type="password" required name="password" id="password"
											placeholder="비밀번호">
									</div>
								</td>

							</tr>
							<tr>
								<td colspan="2">
									<div>
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
											<path
												d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2" />
										</svg>
										<input type="password" required id="password-check" placeholder="비밀번호 확인">
									</div>
								</td>
							</tr>
							<tr>
								<!-- 여기에 input테이블을 통해 배송 주소를 추가해주는 작업 -->
								<!-- 도로명주소, 지번주소, 우편번호  -->
								<th scope="row" class="vt">
									<div class="full_address" style="">주소</div>
								</th>
							</tr>
							<tr>

								<td colspan="2" class="address">
									<div>
										<input type="text" required class="input_text small" name="postNum" id="postNum"
											placeholder="우편번호">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="address">
									<div>
										<input type="text" required class="input_text small" name="roadAddress"
											id="roadAddress" placeholder="도로명주소">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="address">
									<div>
										<input type="text" required class="input_text small" name="jibunAddress"
											id="jibunAddress" placeholder="지번주소">
									</div>
								</td>

							</tr>
							<tr>
								<td colspan="2" class="address">
									<div>
										<input type="text" required class="input_text small" name="detailAddress"
											id="detailAddress" placeholder="상세주소">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="address">
									<input type="button" class="btn btn-light" onclick="execDaumPostcode()"
										value="우편번호 찾기">
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td><button id="joinbtn" type="submit" class="btn btn-light"
										style="background-color: rgb(201, 201, 253)">가입</button></td>
								<td><button type="reset" class="btn btn-light">취소</button></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</form>
		</body>

		</html>