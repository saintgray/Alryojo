<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty requestPost}">
alert('문의받은 게시물이 존재하지 않습니다.');
location.href='${pageContext.request.contextPath}/post/requestgroup';
</c:if>
<c:if test="${not empty requestPost}">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="shortcut icon" href="#">
<title>받은 제의 리스트</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post/requestpost.css">
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
			<div class="post" id="rqpost">
				<button type="button" class="btn btn-light" id="goPostBtn">
					<a href="${pageContext.request.contextPath}/post/view?idx=${requestPost.post_idx}">작성 게시글 </a>
				</button>
				<span class="post-regdate">${requestPost.post_regdate}</span>
				<div class="post-title">${requestPost.post_nm}</div>
				<div class="post-cat">${requestPost.cat_type} / ${requestPost.cat_nm}</div>
			</div>
			
			<div class="rqMemList">
				<c:forEach items="${requestPost.list}" var="member">
					<div class="oneMember">	
						<table class="memTable">
							<tr>
								<td rowspan="3"><input type="hidden" class="matchidx" value="${member.match_idx}"> </td>
								<td rowspan="3" class="midxRow"><input type="hidden" id="rqM_idx" value="${member.m_idx}"/></td>
								<td rowspan="3">
									<c:if test="${mytype eq 'mentee'}">
										<a href="${pageContext.request.contextPath}/member/profile/main?m_idx=${member.m_idx}">
											<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${member.m_photo}" class="m_photo" id="goMentorProfile">
										</a>
									</c:if>
									<c:if test="${mytype eq 'mentor'}">
										<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${member.m_photo}" class="m_photo">
									</c:if>
								</td>		
								<td>
									<span class="rqMname">${member.m_nm}</span>
									<button type="button" class="btn btn-light" id="goChat">
										<a style="color: white"  href="${pageContext.request.contextPath}/chat/chatroom?myidx=${myidx}&matchidx=${member.match_idx}&reciever=${member.m_idx}" >채팅 하기 </a>
									</button>
								</td>			
							</tr>
							<tr>
								<td>
									<c:if test="${mytype eq 'mentee'}">
										<span style="padding-left: 15px; font-weight: 500; font-size: 1.2em;">${member.line}</span>
									</c:if>
								</td>		
							</tr>
							<tr>	
								<td></td>		
							</tr>
						</table>
					</div>	
				</c:forEach>
			</div>
		</c:if>
	</article>


</div>

 <%@include file="/WEB-INF/views/layout/footer.jsp" %>
	

</body>

</html>