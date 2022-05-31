<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>
<style>

	#changeType{
		cursor:pointer;
	}
	#searchBtn{
		width:100px;
		height: 75px;
	}
	#categorySection, #locSection{
		border-right: 1px solid #E1DEE6;
	}
	#PostListArea{
	
	margin-top:150px;
	}
	
</style>
<title>알려드림 : 검색</title>
</head>
<body>

	<div class="gw">

		<!-- Header -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>

		<!-- Container -->
		<div class="container" id="PostListArea">
		
			<sec:authorize access="isAuthenticated()">
				<c:set var="idx">
					<sec:authentication property="principal.m_idx" />
				</c:set>
				<c:set var="type">
					<sec:authentication property="principal.m_type" />
				</c:set>
			</sec:authorize>
	
			<div class="d-flex justify-content-end">
				<span id="changeType" class="pull-right">
					<i class="bi bi-arrow-repeat mx-2"></i>
					<c:if test="${type eq 'mentor'}">
		      		멘티로 전환
		      		</c:if>
		      		<c:if test="${type eq 'mentee'}">
		      		멘토로 전환
		      		</c:if>
				</span>
			</div>
	
			<div class="row">
				<!-- 분야 선택 -->
				<div class="d-flex flex-column my-3 col-4" id="categorySection">
					<div class="descript my-1 ms-0 me-1">분야 선택</div>
					<button type="button" class="btn btn-grey fs-6"	onclick="resetCategory()">초기화</button>
					<!-- 카테고리 selectbox -->
					<div id="categoryInfos" class="d-flex flex-row flex-wrap  mb-5 mt-1">
					</div>
				</div>
	
				<!-- 지역 선택 -->
				<div class="d-flex flex-column my-3 col" id="locSection">
					<div class="descript mb-2">지역 선택</div>
					<div class="d-flex flex-wrap justify-content-around" id="locarea">
	
					</div>
				</div>
				<input type="hidden" id="m_idx" value="${idx}">
				<input type="hidden" id="wanted" value="${type}">
				<button id="searchBtn" class="btn btn-general shadow-sm fs-5 fw-bold my-auto mx-2">검색</button>
	

			</div>
	
			<div id="recommendList" class="list-group my-5">
				
			</div>
	
			<div id="searchResult" class="list-group">
				<div class="descript mb-2">검색 결과</div>
				<c:if test="${not empty listView.list}">
					<c:forEach items="${listView.list}" var="listInfo">
	
						<a href="view?idx=${listInfo.post_idx}" class="list-group-item list-group-item-action">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1">
									<c:out value="${listInfo.post_nm}" escapeXml="true" />
								</h5>
							</div>
							<p class="mb-1">${listInfo.cat_nm}/${listInfo.loc_nm}</p>
						</a>
	
					</c:forEach>
				</c:if>
				
				<c:if test="${empty listView.list}">
					검색 결과가 없습니다.
				</c:if>
				
			</div>
			
			<nav aria-label="paging" class="my-5">
				<c:if test="${listView.totalPageCount > 0}">
					<ul class="pagination justify-content-center">
						<c:forEach begin="1" end="${listView.totalPageCount}" var="pnum">
							<li class="page-item"><a class="page-link" href="search?cat=${cat}&loc=${loc}&p=${pnum}">${pnum}</a></li>
						</c:forEach>
					</ul>
				</c:if>
			</nav>
	
		</div>
	
	
	</div>
	
	<!-- Footer -->
	<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</body>

<%@include file="/WEB-INF/views/post/pageset/searchpageset.jsp" %>
</html>