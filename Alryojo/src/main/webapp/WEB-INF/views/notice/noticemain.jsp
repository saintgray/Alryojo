<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notice/noticemain.css">

<title>공지사항</title>
</head>
<body>

	<div class="gw">
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>

	
		<div id="globalwrap">

			<h1 class="mx-5 banner">
				<a href="${pageContext.request.contextPath}/notice">공지사항</a>
			</h1>



			


			<div class="d-flex flex-row"
			style="border-top: 1px solid rgb(195,195,195)">
				<%@ include file="/WEB-INF/views/layout/aside.jsp"%>


				<div class="ms-5 flex-grow-1">
					<table class="mx-auto" id="listTable">
						<tr class="py-2 indexes">
							<td>번호</td>
							<td>제목</td>
							<td>작성일</td>
						</tr>
						<c:forEach var="items" items="${notices.noticeList}">

							<tr class="my-5">
								<td><span class="mx-2 px-5">${items.notice_idx}</span></td>
								<td><a class="mx-2 px-5"
									href="${pageContext.request.contextPath}/notice/content?notice_idx=${items.notice_idx}">${items.notice_title}</a></td>
								<td><span class="mx-2 px-5">${items.regdate}</span></td>
							</tr>
						</c:forEach>
					</table>

					<div class="d-flex justify-content-center my-5"
						style="position: relative">

						<div class="input-group inner" id="noticesearch">

							<sec:authorize access="hasRole('ADMIN')">
								<button type="button" class="btn btn-general" id="regnoticebtn">공지사항
									등록</button>
								<!-- button 안에 <a> 태그는 쓰지 않으므로 이 버튼을 눌렀을때 스크립트로 해당 url 로 들어가게끔 할 것 -->
							</sec:authorize>
							
							<input id="searchbararea" style="border-left: 0;" type="text"
								class="form-control" aria-label="Amount (to the nearest dollar)"
								placeholder="검색" value="${notices.keyword}">
								<div class="input-group-append">
								<img class="input-group-text" style="height: 100%"
									src="${pageContext.request.contextPath}/resources/files/server/icons/img_searchicon.svg"
									id="searchBtn">
							</div>

							
						</div>



					</div>


					<div class="d-flex justify-content-center my-5">
						<c:forEach begin="1" end="${notices.totalPage}" var="pnum">
							<c:if test="${pnum eq notices.selectPage}">
								<span class="mx-2 pageNum btn btn-warning">${pnum}</span>
							</c:if>
							<c:if test="${pnum ne notices.selectPage}">
								<span class="mx-2 pageNum btn btn-grey">${pnum}</span>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>







		</div>
	</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>


<sec:authorize access="isAuthenticated()">
	<c:set var="admin_idx">
		<sec:authentication property="principal.admin_idx"/>
	</c:set>
	<c:if test="${not empty admin_idx}">
		<%@ include file="/WEB-INF/views/notice/noticepageset/adminnoticemainpageset.jsp" %>
	</c:if>


</sec:authorize>

</body>


<%@ include file="/WEB-INF/views/notice/noticepageset/noticemainpageset.jsp" %>

</html>