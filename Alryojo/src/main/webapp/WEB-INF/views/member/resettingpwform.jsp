<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/resettingpwform.css">
<title>비밀번호 변경</title>

</head>
<body>

<div class="gw">
	<%@include file="/WEB-INF/views/layout/header.jsp" %>

	<div class="text-center mx-auto" id="newPwArea">
			
			<div class="d-flex flex-column justify-content-center">
				
					
				
				<input type="password" class="form-control my-1 mx-auto" placeholder="새 비밀번호" id="newPw">
				<div id="w_pw">
					<span class="warning fw-normal text-center invalid-feedback"></span>
				</div>
				
				
				<input type="password" id="repw" class="form-control my-1 mx-auto" placeholder="비밀번호 확인">
				<div id="w_repw">
					<span class="warning fw-normal text-center invalid-feedback"></span>
				</div>
				
				<div class="d-flex justify-content-center">
					<button type="button" class="btn btn-general" id="editPwBtn">비밀번호 수정</button>
				</div>
			</div>
			
	</div>

</div>


<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<%@include file="/WEB-INF/views/member/pageset/resettingpwformpageset.jsp" %>
</body>


</html>