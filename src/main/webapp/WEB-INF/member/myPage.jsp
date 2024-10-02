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
        
            .mypage-main-continer {
                margin-top: 100px;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: rgb(220, 220, 232);
                flex-direction: column;
            }

            * {
                padding: 0;
                margin: 0;
            }

            html,
            body {
                height: 100%;
            }
			.mypage-h2-my-ssafit {
				justify-content: center;
			}

        </style>
    </head>

    <body>
        <%@ include file="../include/header.jsp" %>
        	<h2 class="mypage-h2-my-ssafit"> my ssafit </h2>
            <div class="mypage-main-continer">
                <table class=".input-table">
                    <tbody>
                        <tr>
                            <th colspan="2">
                                <h1><b>${sessionScope.member.memberName}</b> 님의 my page</h1>
                            </th>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path
                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                    </svg> 
                                    <label for="id"></label>
                                    <input type="text" name="id" id="id" placeholder="아이디" width="130px">
                                    <label for="duplicate_btn"></label>
                                    <button type="button" id="duplicate_btn" class="btn btn-light"
                                        style="width: 100px; height: 40px;">중복확인</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path
                                            d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325" />
                                    </svg>
                                    <input type="text" id="name" name="name" placeholder="이름">
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
                                    <input type="text" name="phno2" id="hpno2" title="휴대폰 중간자리 입력" value=""
                                        class="input_text small" maxlength="4" style="width:96px;">
                                    <span style="margin-top: 10px" class="text">-</span>
                                    <input type="text" name="phno3" id="hpno3" title="휴대폰 마지막자리 입력" value=""
                                        class="input_text small last" maxlength="4" style="width:96px;">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-envelope" viewBox="0 0 16 16">
                                        <path
                                            d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z" />
                                    </svg>
                                    <input type="email" name="email" id="email" placeholder="이메일">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-lock-fill" viewBox="0 0 16 16">
                                        <path
                                            d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2" />
                                    </svg>
                                    <input type="password" name="password" id="password" placeholder="비밀번호">
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="2">
                                <div>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-lock-fill" viewBox="0 0 16 16">
                                        <path
                                            d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2m3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2" />
                                    </svg>
                                    <input type="password" id="password-check" placeholder="비밀번호 확인">
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
                                    <input type="text" class="input_text small" name="postNum" id="postNum"
                                        placeholder="우편번호">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="address">
                                <div>
                                    <input type="text" class="input_text small" name="roadAddress" id="roadAddress"
                                        placeholder="도로명주소">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="address">
                                <div>
                                    <input type="text" class="input_text small" name="jibunAddress" id="jibunAddress"
                                        placeholder="지번주소">
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="2" class="address">
                                <div>
                                    <input type="text" class="input_text small" name="detailAddress" id="detailAddress"
                                        placeholder="상세주소">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="address">
                                <input type="button" class="btn btn-light" onclick="execDaumPostcode()" value="우편번호 찾기">
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td><button id="joinbtn" type="submit" class="btn btn-light"
                                    style="width: 180px; height: 50px; background-color: rgb(201, 201, 253)">가입</button>
                            </td>
                            <td><button type="reset" class="btn btn-light"
                                    style="width: 180px; height: 50px;">취소</button></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
    </body>

    </html>