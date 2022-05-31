<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/myinfo.css">

<title>내 정보</title>
</head>
<body>
<c:set var="principal_idx">
	<sec:authentication property="principal.m_idx"/>
</c:set>



<div class="gw">

	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	
	<div id="controlarea">
		<div id="controlbtn" class="d-flex justify-content-center">
				<span class="mx-2 u_txt_chk">
					<sec:authorize access="hasRole('GENERAL')">
					회원정보
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ADMIN','SUPERADMIN')">
					관리자정보
					</sec:authorize>
				</span>
				<div class="form-check form-switch">
					<input class="form-check-input" type="checkbox" role="switch" id="switchSettings">		  
				</div>
				<span class="mx-2">보안설정</span>
		</div>
	</div>
	
	
	<div class="d-flex flex-column justify-content-center" id="infosarea">
			
			
			<!--  회원 정보 란 -->
			<div id="infos">
				
				
				<div class="d-flex flex-row justify-content-center container">
					
					<div id="profileshotarea">
						
						<img class="mx-3" src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${info.m_photo}" id="myprofile">
						<!-- 수정 카메라 이미지가 위치/ 위치는 absolute  <img....>-->
						<label for="editphoto">
							<i id="cameraicon" class="fi fi-rr-camera"></i>
						</label>
						<input type="file" id="editphoto" style="display:none">
					</div>
					
			
					<div class="mx-3 d-flex flex-column justify-content-around">
						<div>
							<h3 class="fw-light fs-4">${info.m_email}</h3>
							
							<div id="namearea" class="d-flex flex-wrap input-group mb-3">
								<%-- <h3 class="fw-normal fs-4">${info.m_nm}<i class="fi fi-rr-pencil" id="editnameicon"></i></h3> --%>
								<h3 class="fw-normal fs-4">${info.m_nm}<i class="bi bi-wrench mx-1" id="editnameicon"></i></h3>
							</div>
							<sec:authorize access="hasRole('GENERAL')">
								<h3 class="fw-normal fs-6 text-end" style="color:rgb(195,195,195)">가입일 : ${info.m_regdate}</h3>
							</sec:authorize>
						</div>
					</div>
					
					
				</div>
				
				<sec:authorize access="hasRole('GENERAL')">
						<div class="my-4 d-flex flex-wrap flex-column justify-content-center container"  id="interestarea">
							
							<div>
								<!--  test -->
								<c:if test="${empty info.list}">
									<h3 class="text-center fw-normal fs-3 pt-2">관심분야가 없습니다</h3>
									<div class="text-center"><img class="my-2" src="${pageContext.request.contextPath}/resources/files/server/icons/icon_plus.png" id="addCat" data-bs-toggle="modal" data-bs-target="#modal-cat"/></div>
									
								</c:if>
								<c:if test="${not empty info.list}">
									<div class="d-flex flex-wrap flex-wrap">
										<div class="d-flex flex-wrap flex-grow-1">
											
											<c:forEach var="item" items="${info.list}">
												<span class="cidx fs-6 mx-2 d-none">${item.cat_idx}</span>
												<span class="cnm fs-6 mx-2">${item.cat_nm}</span>										
											
											</c:forEach>
										</div>
										<div id="editinteresticon" class="pe-2">
											<i class="bi bi-wrench mx-1" data-bs-toggle="modal" data-bs-target="#modal-cat"></i>
										</div>
									</div>
								</c:if>	
							</div>		
					
						</div>
				</sec:authorize>
				
						
			</div>
				
					
			<!--  보안정보 란 -->
			<div class="my-auto text-center d-none" id="securityinfos">
			
				<div class="d-flex flex-column justify-content-center">
					
						
					
					<input type="password" class="form-control my-1 mx-auto" placeholder="새 비밀번호" id="newPw">
					<div id="w_pw" class="warning fw-normal text-center invisible">\r\n
					</div>
					
					
					<input type="password" id="repw" class="form-control my-1 mx-auto" placeholder="비밀번호 확인">
					<div id="w_repw" class="warning fw-normal text-center invisible mb-5">\r\n
					</div>
					
					
					<button type="button" class="btn btn-general mx-auto my-2 input-group" id="editPwBtn">비밀번호 수정</button>
					
				</div>
			
			</div>
					
					
				
		</div>	
				
			
</div>
		
		
<sec:authorize access="hasRole('GENERAL')">
			
					<div class="modal fade" id="modal-cat" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="staticBackdropLabel">관심분야를 선택하세요</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						      
						      	<div id="interestInfosWrap">
									<div class="d-flex flex-column justify-content-around" id="interestInfos">
										<!-- DB에서 가져온 카테고리 내용이 들어갑니다. -->	
									</div>
									<div>
									</div>
								</div>
						       		
						      </div>
						      <div class="modal-footer d-flex flex-wrap justify-content-around">
						        <button type="button" class="btn btn-grey" data-bs-dismiss="modal" id="close-modal">취소</button>
						        <button type="button" class="btn btn-general" id="next">다음</button>
						      </div>
						    </div>
						</div>	
					</div>
					
		<div id="quitguide">
			<span>더 이상 알려드림 서비스를 사용하지 않는다면? >> </span><span id="quitlink" class="fw-bold">회원탈퇴</span>
		</div>
</sec:authorize>


<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<%@include file="/WEB-INF/views/member/pageset/myinfopageset.jsp" %>

<sec:authorize access="hasAnyRole('ADMIN','SUPERADMIN')">
	<%@include file="/WEB-INF/views/member/pageset/myinfoadminpageset.jsp" %>
</sec:authorize>

<sec:authorize access="hasRole('GENERAL')">
	<%@include file="/WEB-INF/views/member/pageset/myinfomemberpageset.jsp" %>
</sec:authorize>

</body>
</html>