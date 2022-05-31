<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>
<style>
#adminMainwrap {
	margin-top: 100px;
}

#adminMainwrap .managebtn {
	width: 120px;
	height: 120px;
	border-radius: 50%;
	border: 2px solid #FFD601;
}

.managetab {
	text-align: center;
	justify-content: center;
}

@media(max-width: 500px){
	 #adminMainwrap{
		flex-direction: column !important;
	} 
} 
</style>
<title>Insert title here</title>
</head>
<body>
	<!-- 데이터가 잘 들어왔는지 테스트 -->
	<!-- 
<h1>관리자 페이지에 들어왔습니다!!!!</h1>

<h3>아이디 : <sec:authentication property="principal.username"/></h3>
<h3>비밀번호 : <sec:authentication property="principal.password"/></h3> 
<h3>권한 : <sec:authentication property="principal.authorities"/></h3>

-- User 를 상속받아 만든 허가증에 든 추가 정보들
 
<h3>사진 파일명 : <sec:authentication property="principal.photo"/></h3> 
<h3>이름 : <sec:authentication property="principal.name"/></h3> 
<h3>프로필</h3>
<img src="${pageContext.request.contextPath}/resources/files/member/<sec:authentication property="principal.photo"/>">

 -->

	
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>


	<!-- 관리자 메인 페이지 내용 -->

	<div
		class="container d-flex flex-row flex-wrap justify-content-around gw"
		id="adminMainwrap">
		
			<div class="d-flex flex-column flex-wrap managetab">
				<a href="${pageContext.request.contextPath}/admin/member/list">
				<img src="${pageContext.request.contextPath}/resources/files/server/icons/adminmember.svg" class="managebtn"/></a>
				<div class="text-center">회원관리</div>
			</div>
		
		
			<div class="d-flex flex-column flex-wrap managetab">
				<a href="${pageContext.request.contextPath}/admin/categorymanage">
				<img src="${pageContext.request.contextPath}/resources/files/server/icons/category.png" class="managebtn"></a>
				<a class="mx" href="${pageContext.request.contextPath}/admin/categorymanage" >분야관리</a>
			</div>
			<div class="d-flex flex-column flex-wrap managetab">
				<a href="${pageContext.request.contextPath}/notice?selectPage=1&numOfNoticesPerPage=5">
					<img src="${pageContext.request.contextPath}/resources/files/server/icons/customerservice.svg" class="managebtn"/></a>
					<a class="mx" href="${pageContext.request.contextPath}/notice?selectPage=1&numOfNoticesPerPage=5">고객센터</a>
			</div>
			
			
			<sec:authorize access="hasRole('SUPERADMIN')"> 
				
				<div class="d-flex flex-column flex-wrap managetab">
					<a href="${pageContext.request.contextPath}/superadmin/admin/register">
					<img src="${pageContext.request.contextPath}/resources/files/server/icons/manager.jpg" class="managebtn"></a>
					<a class="mx" href="${pageContext.request.contextPath}/superadmin/admin/register" >관리자</a>
				</div>
			
			</sec:authorize>
		</div>


	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>



</body>
</html>