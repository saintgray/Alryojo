<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알려드림 : 내가 쓴 글</title>
</head>
<body>

<div class="gw">

	<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>

	<!-- Header -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>

	<!-- Container -->
	<div class="container" id="PostListArea">

		<div class="descript my-1 ms-0 me-1 text-start">
		${logininfo.name} 
		<c:if test="${logininfo.m_type eq 'mentor'}">
	  		멘토 
	  	</c:if>
	  	<c:if test="${logininfo.m_type eq 'mentee'}">
	  		멘티
	  	</c:if>
		님이 쓰신 글은 총 ${listView.totalCount} 건 입니다.
		</div>
		<div class="list-group">
			<c:if test="${empty listView.list}">
				등록된 요청글이 없습니다.
			</c:if>

			<c:if test="${not empty listView.list}">
				<c:forEach items="${listView.list}" var="listInfo">

					<a href="view?idx=${listInfo.post_idx}" class="list-group-item list-group-item-action">
						<div class="d-flex w-100 justify-content-between">
							<h5 class="mb-1">
								<c:out value="${listInfo.post_nm}" escapeXml="true" />
							</h5>
							<span class="badge bg-warning text-dark">${listInfo.match_count}</span>
						</div>
						<p class="mb-1">${listInfo.cat_nm}/${listInfo.loc_nm}</p>
					</a>

				</c:forEach>
			</c:if>
		</div>

		<nav aria-label="paging">
			<c:if test="${listView.totalPageCount > 0}">
				<ul class="pagination justify-content-center">
					<c:forEach begin="1" end="${listView.totalPageCount}" var="pnum">
						<li class="page-item"><a class="page-link" href="list?p=${pnum}">${pnum}</a></li>
					</c:forEach>
				</ul>
			</c:if>
		</nav>

	</div>

</div>

<!-- Footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</body>
</html>