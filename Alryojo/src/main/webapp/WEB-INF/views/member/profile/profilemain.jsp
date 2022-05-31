<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/default/summernote-bs4.js"></script> --%>
<!-- timepicker script & css -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<!-- profilemain CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/profilemain.css">

<title>내 프로필</title>
</head>
<body>


<div class="gw">
	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	
	
	<div class="container" id="profileglobalwrap">
		<!-- 등록한 프로필이 없을때 + 등록했던 프로필을 모두 삭제했을 때 -->
		<c:set var="m_idx">
			<sec:authentication property="principal.m_idx"/>
		</c:set>
		
		<!-- 만약 이 프로필이 비어있다면 -->
		<c:if test="${empty profile}">
			<c:set var="m_idx">
				<sec:authentication property="principal.m_idx"/>
			</c:set>
		
			<!-- 이 프로필의 소유주 idx 가 현재 로그인한 사람의 idx 와 같다면 -->
			<c:if test="${profileOwnerIdx eq m_idx}">
		
				<div id="regarea" class="d-flex flex-column mx-auto my-4">
					<h3 id="msg">내 프로필이 비었습니다.<br> 지금 등록하세요</h3>
					
					<img src="${pageContext.request.contextPath}/resources/files/server/icons/icon_plus.png" id="plus_icon">
								
				</div>	
				
			</c:if>
			
			<!-- 이 프로필의 소유주 idx 가 현재 로그인한 사람의 idx 와 다르다면  -->
			<c:if test="${profileOwnerIdx ne m_idx}">
				
				<div class="d-flex flex-wrap justify-content-center">
					<h1> 이 회원은 프로필이 없습니다. </h1>
				</div>
			</c:if>
			
		</c:if>
		
		
		<!-- 해당 유저의 프로필이 있고 해당 유저가 탈퇴하지 않았을 시 -->
		<c:if test="${not empty profile and empty profile.m_quitdate}">
		
			<c:set var="photo">
				<sec:authentication property="principal.photo"/>
			</c:set>
			<c:set var="m_idx">
				<sec:authentication property="principal.m_idx"/>
			</c:set>
			
			<!-- 프로필 사진, 별점, 매칭수, 리뷰수 -->
			<div id="maininfo" class="d-flex flex-wrap my-5 justify-content-center">
				<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${profile.m_photo}" id="myphoto" class="mx-5">
				<div class="d-flex flex-wrap flex-column">
					<div class="d-flex flex-wrap mb-2 justify-content-center">
						<div class="bg_template">
							<span class="d-none rank">${profile.avgStars}</span>
					        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
					        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
					        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
					        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
					        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
					    </div>
					</div>
					
					<div class="d-flex flex-wrap  justify-content-around my-2 activity">
						<span class="activity_info">평점 : ${profile.avgStars}</span>
						<span class="br-col"></span>
						<span class="activity_info">매칭수 : ${profile.matchCount}</span>
						<span class="br-col"></span>
						<span class="activity_info">리뷰수 : ${profile.reviewCount}</span>
					</div>
					
				</div>
			</div>
			<!-- ------------------------------------------- -->
			
			<!-- 한줄 소개 -->
			<div class="d-flex flex-column mb-5">
				<div class="d-flex flex-wrap">
					<div class="banner flex-grow-1">
						한줄 소개
					</div>
					<c:if test="${m_idx eq profile.m_idx}">
						<button id="editline" class="btn btn-general edit ms-auto" data-bs-toggle="modal" data-bs-target="#editarea">수정</button>
					</c:if>
				</div>
				<span id="originline">
					<c:out value="${profile.line}" escapeXml="true"></c:out>
				</span>
			</div>
			<!-- ------------------------------------------- -->
			
			<!-- 연락 가능 시간  -->
			<div class="d-flex flex-column mb-5">
				<div class="d-flex flex-wrap">
					<div class="banner flex-grow-1">
						연락 가능 시간
					</div>
					<c:if test="${m_idx eq profile.m_idx}">
						<button id="editcalltime" class="btn btn-general edit ms-auto" data-bs-toggle="modal" data-bs-target="#editarea">수정</button>
					</c:if>
				</div>
				<span>${profile.calltime}</span>
			</div>
			<!-- ------------------------------------------- -->
			
			<!-- 경력 -->
			
			<div class="d-flex flex-column mb-5">
				<div class="d-flex flex-wrap">
					<div class="banner flex-grow-1">
						경력
					</div>
					<c:if test="${m_idx eq profile.m_idx}">
						<button id="editcareer" class="btn btn-general edit ms-auto" data-bs-toggle="modal" data-bs-target="#editarea">수정</button>			
					</c:if>
				</div>
				<div id="careerarea">
					${profile.career}
				</div>
				
				<div>
			 	<!-- 첨부 파일 영역 -->
			 	<!-- DB에서 해당 프로필의 고유번호에 해당하는 첨부파일을 받아와 다운로드 형태로 출력 -->
				</div>	
			</div>
			
			
			<!-- ------------------------------------------- -->
			
			<!-- QNA -->
			
			<div class="d-flex flex-column mb-5">
				<div class="d-flex flex-wrap">
					<div class="banner flex-grow-1">
						QNA
						
					</div>
					<c:if test="${m_idx eq profile.m_idx}">
						<button id="editqna" class="btn btn-general edit ms-auto" data-bs-toggle="modal" data-bs-target="#editarea">수정</button>
					</c:if>
				</div>
				<div id="qnaarea">
					${profile.qna}
				</div>
			</div>
			
			
			
			
			
			
		
			<!-- 수정시 해당 항목을 수정하는 팝업 영역 -->
			<div class="modal fade" id="editarea" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        
				      </div>
				      <div class="modal-footer d-flex flex-wrap justify-content-around">
				        <button type="button" class="btn btn-grey" data-bs-dismiss="modal" id="close-modal">취소</button>
				        <button type="button" class="btn btn-general editbtn">수정</button>
				      </div>
				    </div>
				</div>	
			</div>
			
			
			<!-- 프로필에 등록한 첨부파일들 -->
			<div class="banner">
				등록파일
			</div>
			<div class="d-flex flex-row flex-wrap" id="attachedFilesWrap">
				
				<div class="d-flex flex-wrap  flex-grow-1" id="attachFilesArea">
					<c:if test="${empty profile.files}">
						<span class="fw-bold" style="color:rgb(70,70,70)">파일이 없습니다</span>
					</c:if>
					
					<c:forEach var="file" items="${profile.files}">
					
						<div class="select d-flex flex-wrap">
							
							<i class="bi bi-file-earmark"></i>
							<span class="fileName d-none">${file.file_nm}</span>
							<a class="mx-2 fw-light" href="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/profile/attachfiles${file.file_nm}" download="${file.file_originnm}">${file.file_originnm}</a>
							<c:if test="${profile.m_idx eq m_idx}">
								<img src="${pageContext.request.contextPath}/resources/files/server/icons/icon_x.png" class="delRow me-3">
							</c:if>
							
						</div>
							
										
					
					</c:forEach>
				
				</div>
				
				<div class="d-flex flex-column justify-content-center">
					
					<c:if test="${profile.m_idx eq m_idx}">
						<label for="addfiles" id="label_files">
							<i class="fi fi-rr-add"></i>
						</label>
						<input multiple type="file" style="display:none" id="addfiles">
					</c:if>
					
				</div>
			</div>
			
			<c:if test="${profile.m_idx eq m_idx}">
				<div class="d-flex flex-wrap justify-content-end mb-5">
					<button type="button" class="btn btn-danger" id="delProfile" style="border-radius: 20px">프로필 삭제</button>
				</div>
			</c:if>
			
			
			
			<!-- 프로필의 소유주가 파라미터로 들어온 m_idx 와 다를때만 = 다른 사람이 내 프로필을 보려고 들어올 때만
					리뷰를 출력해준다. -->
			<c:if test="${not empty param.m_idx}">
				
				
				<c:if test="${profile.m_idx ne m_idx}">
					<div id="revarea" class="d-flex flex-wrap my-5">
						<c:forEach var="rev" items="${profile.reviews}">
							<div class="d-flex flex-column rev my-3 me-2 px-3 py-3">
								
								<div class="text-center classes">
									<c:out value="${rev.post_nm}" escapeXml="true"/> 
								</div>
								
								<div class="d-flex my-2">
									<span class="me-3">
										<c:out value="${rev.mentee_nm}" escapeXml="true"/>
									</span>
									
									<div class="bg_template">								
										<span class="d-none rank">${rev.stars}</span>
								        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
								        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
								        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
								        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
								        <img src="${pageContext.request.contextPath}/resources/files/server/icons/teststar.png" class="star">
							    	</div>
								</div>
								<span class="fw-bold my-4 mx-1">
									<c:out value="${rev.comment}" escapeXml="true"/>
								</span>
								
							</div>
						</c:forEach>
					</div>	
				</c:if>
				
			</c:if>
			
			
		</c:if>
		
		
		<!-- 해당 유저의 프로필은 있는데 탈퇴한 회원인 경우 -->
		<c:if test="${not empty profile and not empty profile.m_quitdate}">
		
			<h1>탈퇴한 회원입니다.</h1>
			
		</c:if>
				
	</div>

</div>
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<c:if test="${empty profile}">
	
	<%@include file="/WEB-INF/views/member/profile/pageset/noneProfilePageset.jsp" %>

</c:if>
<c:if test="${not empty profile}">
	<%@include file="/WEB-INF/views/member/profile/pageset/profilemainpageset.jsp"  %>
	<c:if test="${profile.m_idx eq m_idx}">
		<%@include file="/WEB-INF/views/member/profile/pageset/profileOwnerPageset.jsp" %>
	</c:if>
</c:if>



</body>


</html>