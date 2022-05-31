<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/quitform.css">
<title>회원탈퇴</title>
</head>
<body>

<div class="gw">

	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	
	
	
	
	<div class="d-flex flex-column mx-auto my-auto justify-content-center" id="quitform">
		<h1 class="banner">회원 탈퇴</h1>
		
		
		<ul class="my-auto">
			<li class="my-3">사용하고 계신 아이디 (<span class="myid fw-bold"><sec:authentication property="principal.username"/></span>) 는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</li>
			<li class="my-3">탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</li>
			<li class="my-3">탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아있습니다.</li>
			<li class="my-3">탈퇴 후에는 아이디 <span class="myid fw-bold mx-1"><sec:authentication property="principal.username"/></span> 로 다시 가입할 수 없으며<br> 
							  아이디와 데이터는 복구할 수 없습니다.<br>
							  게시한형 서비에서 남아있는 게시글은 탈퇴 후 삭제할 수 없습니다<br>
							  또한, 알려드림 아이디를 사용해 다른 서비스에 로그인 할 수 없게 됩니다.	</li>
		</ul>
		
		
		<div class="d-flex">
			<label for="chkquit">
				<img src="${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg" id="chkicon" />
			</label>
			<input type="checkbox" class="d-none" id="chkquit">
			<span class="mx-2">안내사항을 모두 확인하였으며 이에 동의합니다</span>
		</div>
		<div class="d-flex justify-content-center my-5">			
			<button type="button" class="btn btn-danger" id="quitbtn">회원 탈퇴</button>
		</div>
		
		
		
		
		
	
	</div>





</div>



<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<%@include file="/WEB-INF/views/member/pageset/quitformpageset.jsp" %>
</body>

</html>