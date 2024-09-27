
document.addEventListener('DOMContentLoaded', () => {
  // 요소 가져오기
let joinBtn = document.getElementsByClassName("btn btn-light");

joinBtn[1].addEventListener('click', () => {
let value = confirm("취소하시겠습니까??")
if(value) {
  location.href = location.href;
}
})

joinBtn[0].addEventListener('click', () => {

let idInput = document.getElementById('id').value;
let nameInput = document.getElementById('name').value;
let emailInput = document.getElementById('email').value;
let passwordInput = document.getElementById('password').value;
let passwordCheckInput = document.getElementById('password-check').value;
let valid = true;

// 유효성 검사  
if (idInput === '') {
  alert('아이디를 입력해 주세요.');
  valid = false;
}

if (nameInput === '') {
  alert('이름을 입력해 주세요.');
  valid = false;
}

if (emailInput === '') {
  alert('이메일을 입력해 주세요.');
  valid = false;
}

if (passwordInput === '') {
  alert('비밀번호를 입력해 주세요.');
  valid = false;
}

if (passwordCheckInput === '') {
  alert('비밀번호 확인을 입력해 주세요.');
  valid = false;
} 

if (valid) {
  alert('회원가입이 완료되었습니다!');
  location.href = `/login/login.html`;
}
})
})
