<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은제의 그룹으로 보여지는 곳</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post/requestgroup.css">
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<%@ include file="/WEB-INF/views/post/pageset/requestgrouppageset.jsp"%>
</head>
<body>




<!-- 현 로그인 정보 -->
	<sec:authorize access="isAuthenticated()">
		<c:set var="myidx">
			<sec:authentication property="principal.m_idx"/>
		</c:set>
		<c:set var="mytype">
			<sec:authentication property="principal.m_type"/>
		</c:set>
	</sec:authorize>

	
	<div class="gw">
		<!-- Header -->
		<%@ include file="/WEB-INF/views/layout/header.jsp" %>
		
		<article>
		
			<section class="container" id="rqg-whole">
				<div class="head-title">
					받은 제의
				</div>
				
				 <!-- 해당 받은 그룹이 없으면 -->   
				<c:if test="${empty requestGroup}">
								<div class="card" id="emptycard">
									  <div class="card-header">
										<div id="emptycard-title">아직 문의받은 게시글이 없습니다.</div>
									  </div>
									  <div class="card-body" id="emptycard-body" >
									  	이런! 아직 도착한 문의가 없네요<br>
									  	먼저 다른 회원을 찾으러 가볼까요?
									  </div>
									  <div class="card-footer">
									  </div>
								</div>
				</c:if>
				<c:if test="${not empty requestGroup}">
				
					<ul class="ul-card">
						<c:forEach items="${requestGroup}" var="rgroup">
							<li class="li-card">
								<div class="card" id="rqcard">
									  <div class="card-header">
									  	<div class="post-date">${rgroup.post_regdate}</div>
										<div class="post-title">${rgroup.post_nm}</div>
										<div class="post-category">	${rgroup.cat_type} / ${rgroup.cat_nm}</div>
									  </div>
									  <div class="card-body">
										  <div class="photo-list">
										  	<c:forEach items="${rgroup.list}" var="member" begin="0" end="3">
										  			<span><img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${member.m_photo}" class="m_photo"></span>
										  	</c:forEach>
										  	<c:if test="${rgroup.overfive eq 'Y'}">
													<img src="${pageContext.request.contextPath}/resources/files/member/threedots.png" class="m_photo">
											</c:if>
										 </div>
									  </div>
									  <div class="card-footer">
										<c:if test="${mytype eq 'mentee'}">
											<a class="btn btn-outline-success rqMember" href="${pageContext.request.contextPath}/post/requestpost?postidx=${rgroup.post_idx}"><strong>제안한 멘토보기</strong></a>
										</c:if>
										<c:if test="${mytype eq 'mentor'}">
											<a class="btn btn-outline-success rqMember" href="${pageContext.request.contextPath}/post/requestpost?postidx=${rgroup.post_idx}"><strong>제안한 멘티보기</strong></a>
										</c:if>
									  </div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</section>
		
		</article>
	
	</div>


<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>

</html>