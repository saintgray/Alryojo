<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  
  <!-- 기본 소스들 include : JQuery, Bootstrap... -->
  <%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
  
  <!-- 자신이 만든 보조 커스텀 소스들 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home/home.css">
  <%@ include file="/WEB-INF/views/home/pageset/homepageset.jsp"%>
  
  
  
  <title>HOME</title>
</head>

<body>

	
	
  	<!-- Home body area -->
  	
  	<div class="gw">
  		<!-- Header -->
    	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	  	
	  	<div class="contatiner">
			<div id="globalwrap" class="d-flex flex-column">
		
			    <!-- banner area -->
			    <!-- 관리자는 배너(광고창) 등록 기능을 사용할 수 있습니다. 우선순위 10 -->
			    <!-- 홈페이지 로딩 후 DB의 광고 테이블에서 활성화된 광고들을 받아와 출력할 수 있습니다. -->
			     <div class="shadow d-flex flex-row" id="bannerarea" >
			    	  
				      <img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/banner1.svg">
				      <img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/banner2.png">
				      <img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/banner3.png">
				      <img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/banner4.png">
			
			     </div>
			     
			     <sec:authorize access="hasRole('GENERAL')">
			     	 <c:set var="type">
			     		 <sec:authentication property="principal.m_type"/>
			     	 </c:set>
			     	 <c:set var="idx">
			     		 <sec:authentication property="principal.m_idx"/>
			     	 </c:set>
	
				     <div class="d-flex flex-column justify-content-around" id="mnmbtn">
				     
				     	<button id="writepostbtn" class="btn btn-general my-2 shadow-lg">요청서 작성</button>
				     	
			     		<button id="findbtn" class="btn btn-general my-2 shadow-lg">
			     			<c:if test="${type eq 'mentor'}">
			     				멘티찾기
			     			</c:if>
			     			<c:if test="${type eq 'mentee'}">
			     				멘토찾기
			     			</c:if>
			     		</button>		
				     	
				     </div>
				     
			     </sec:authorize>
	
			     <div id="countarea">
				     <div class="d-flex flex-row justify-content-center">
				     
				     	<table class="text-center" id="maininfo">
				     		
				     		<tr>
				     			<td><span class="descript">회원</span></td>
				     			<td><span class="descript">게시글</span></td>
			     				<td><span class="descript">매칭</span></td>
				     		</tr>
				     	</table>
				     
				  	 </div>
			  	 </div>
			  	 
			  	
			  	 <h1 class="banner mt-5">
			  	 	 
		  	 	
			  	 	당신의 
			  	 	<sec:authorize access="isAnonymous()">
		  	 		 	멘티
		  	 		</sec:authorize>
			  	 	<c:if test="${type eq 'mentor'}">
		  	 		  멘티  
		  	 		 </c:if>
		  	 		 <c:if test="${type eq 'mentee'}">
		  	 		  멘토
		  	 		 </c:if>
		  	 		 <sec:authorize access="hasAnyRole('ADMIN','SUPERADMIN')">
		  	 		 	멘티
		  	 		 </sec:authorize>
			  	 	를 지금 찾아보세요!
			  	 </h1>
			  	
			  	 <div class="d-flex flex-row flex-nowrap justify-content-between mb-5" id="catarea">
			  	 
			  	 	<div class="d-flex flex-column cat-card mx-auto shadow">
			  	 		<a>
			  	 			<img src="${pageContext.request.contextPath}/resources/files/server/category/piano.jpg">
			  	 		</a>
			  	 		<span class="descript ms-2 my-1">음악</span>
			  	 	</div>
			  	 	<div class="d-flex flex-column cat-card mx-auto shadow">
			  	 		<a>
			  	 			<img src="${pageContext.request.contextPath}/resources/files/server/category/draw.png">
			  	 		</a>
			  	 		<span class="descript ms-2 my-1">미술</span>
			  	 	</div>
			  	 	<div class="d-flex flex-column cat-card mx-auto shadow">
			  	 		<a>
			  	 			<img src="${pageContext.request.contextPath}/resources/files/server/category/cook.jpg">
			  	 		</a>
			  	 		<span class="descript ms-2 my-1">요리</span>
			  	 	</div>
			  	 
			  	 </div>
			  	 
			  	
		  	 	<h1 class="banner mt-5">
		  	 		
			  	 		전국의 
			  	 		 <sec:authorize access="isAnonymous()">
			  	 		 	멘티
			  	 		 </sec:authorize>
			  	 		 <c:if test="${type eq 'mentor'}">
			  	 		  멘티  
			  	 		 </c:if>
			  	 		 <c:if test="${type eq 'mentee'}">
			  	 		  멘토
			  	 		 </c:if>
			  	 		 <sec:authorize access="hasAnyRole('ADMIN','SUPERADMIN')">
			  	 		 	멘티
			  	 		 </sec:authorize>
			  	 		 를 지금 만나보세요!
		  	 		
		  	 	</h1>
		  	 	
			  	<div id="locsearcharea" class="container d-flex p-2 flex-wrap mb-5 justify-content-center">
			  	</div>
			  			  	 
		     </div>
	 	</div>
	 </div>
	 
	 
    <!-- footer -->
	<%@include file="/WEB-INF/views/layout/footer.jsp" %>
    
    
		
		
	
  
</body>

</html>