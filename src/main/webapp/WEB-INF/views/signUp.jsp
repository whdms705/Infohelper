<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script>
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.
		}
	}).open();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/login.css" />
<title>Hello1</title>

</head>
<body>


	<div class='container'>
		<div class="wrapper">
			<div class="image"></div>
			<div class="join">
				<form name="signupForm" id="signupForm" method="post">
					<div class="input">
						<input type="text" class="box" placeholder="이름" name="name"
							required>
					</div>
					<div class="input">
						<input type="number" class="box" placeholder="학번"
							name="studentNum" required>
					</div>
					<div class="input">
						<input type="password" class="box" placeholder="비밀번호"
							name="password" required
							pattern="(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,12}">
					</div>
					<div class="input">
						<input type="password" class="box" placeholder="비밀번호 확인"
							name="passwordConfirm" required>
					</div>
					<div class="input">
						<input type="email" class="box" placeholder="이메일" name="email"
							required>
					</div>

					<div class="input">
						<input type="button" class="btn-primary box"
							onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					</div>
					<div class="input">
						<input type="text" name="post" class="box" id="sample4_postcode"
							placeholder="우편번호" required>
					</div>

					<div class="input">
						<input type="text" name="roadAddress" class="box"
							id="sample4_roadAddress" placeholder="도로명주소" required>
					</div>
					<div class="input">
						<input type="text" name="jibunAddress" class="box"
							id="sample4_jibunAddress" placeholder="지번주소" required>
					</div>
					

					<a href="#"><div class="login_btn">
							<input type="submit" class="login_btn" value="등록하기">
						</div></a>
					<div class="option">
						<a href="index.jsp"><div class="back">돌아가기</div></a>
					</div>
			</div>
		</div>

	</div>

	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		function sample4_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
							alert(fullRoadAddr);
							var extraRoadAddr = ''; // 도로명 조합형 주소 변수

							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraRoadAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraRoadAddr += (extraRoadAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraRoadAddr !== '') {
								extraRoadAddr = ' (' + extraRoadAddr + ')';
							}
							// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
							if (fullRoadAddr !== '') {
								fullRoadAddr += extraRoadAddr;
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
							document.getElementById('sample4_roadAddress').value = fullRoadAddr;
							document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

							// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
							if (data.autoRoadAddress) {
								//예상되는 도로명 주소에 조합형 주소를 추가한다.
								var expRoadAddr = data.autoRoadAddress
										+ extraRoadAddr;
								document.getElementById('guide').innerHTML = '(예상 도로명 주소 : '
										+ expRoadAddr + ')';

							} else if (data.autoJibunAddress) {
								var expJibunAddr = data.autoJibunAddress;
								document.getElementById('guide').innerHTML = '(예상 지번 주소 : '
										+ expJibunAddr + ')';

							} else {
								document.getElementById('guide').innerHTML = '';
							}
						}
					}).open();
		}
	</script>



</body>
</html>