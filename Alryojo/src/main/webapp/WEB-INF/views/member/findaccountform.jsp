<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/findaccount.css">
<title>회원정보 찾기</title>
</head>
<body>	

	<div class="gw">
		<%@ include file="/WEB-INF/views/layout/header.jsp" %>
		
		<div id="findaccountarea">
			
			<h1 class="banner">비밀번호 찾기</h1>
		
			<div class="my-4">
				<input type="text" class="form-control" placeholder="이메일" id="email">
				<div id="fb" class="warning invalid-feedback">
					  
				</div>
			</div>
			<div class="descript">가입 시 등록하신 이메일을 입력해주세요</div>
			
			
			
			<div class="d-flex justify-content-center my-5">
				<button type="button" class="btn btn-general" id="findMe">확인</button>
			</div>		
		
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
<%@ include file="/WEB-INF/views/member/pageset/findaccountformpageset.jsp" %>
</body>

</html>