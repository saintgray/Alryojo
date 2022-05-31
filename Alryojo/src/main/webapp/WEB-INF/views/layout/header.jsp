<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout/header.css">
<%@ include file="/WEB-INF/views/layout/pageset/headerpageset.jsp" %>



<div id="headerwrap">

	<ul class="container d-flex">
	      
	      <li id="first" class="mr-auto p-2 d-flex flex-wrap">
	        <img src="${pageContext.request.contextPath}/resources/files/server/icons/icon_sidemenu.svg" data-bs-toggle="offcanvas" data-bs-target="#sidemenu"
	        aria-controls="sidemenu" class="sub"> 
	        <a id="logo"><img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/logo.svg"></a>
	        
	        <!--이 아이콘을 누르면 searchbar 로 focusing 되게 하세요-->
	        <img src="${pageContext.request.contextPath}/resources/files/server/icons/img_searchicon.svg" class="sub" id="searchfocusbtn">
	        
	
	        <div class="input-group inner" id="search">
	          <div class="input-group-prepend">
	            <img class="input-group-text" style="height: 100%" src="${pageContext.request.contextPath}/resources/files/server/icons/img_searchicon.svg">
	          </div>
	          <input id="searchbar" style="border-left: 0;" type="text" class="form-control" aria-label="Amount (to the nearest dollar)"
	            placeholder="어떤 서비스가 필요하세요?">
	        </div>
	
	      </li>
	
	
		  
	      <li id="subnav" class="ml-auto p-2 d-flex">
	      		<sec:authorize access="isAuthenticated()">
	      			<c:set var="type">
	      				<sec:authentication property="principal.m_type"/>
	      			</c:set>
						
						
	      				<sec:authorize access="hasRole('GENERAL')">
	      				
		      				<span class="inner list" id="mypost" onclick="location.href='${pageContext.request.contextPath}/post/list';">내가 쓴 글</span>
		      			
		      				<span class="inner list" id="request">받은 제의</span>
		      				
		      				<span class="inner list" id="chat">채팅</span>
		      				
	      				</sec:authorize>
	      				
	      				<!-- s3 에 등록한 파일의 경우 경로는 다음에 따른다
	      				'https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/(내가 설정했던 uploadPath명)DB에 저장된 파일테이들의 file_nm 컬럼
	      				 -->
	      				<img src='https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member<sec:authentication property="principal.photo"/>' 
	      					id="myPhoto" class="dropdown-toggle" data-bs-toggle="dropdown" aria-expended="false">
	      				
	      				<ul class="dropdown-menu" aria-labelledby="myPhoto" id="menutab">
							
	      					<li>
	      						<!-- <div class="d-flex flex-column  dropdown-item"> -->
	      							<div class="d-flex justify-content-around dropdown-item">
	      								<i class="fi fi-rr-user mx-2""></i>
	      								<div class="flex-grow-1 text-center">
			      							<span id="myinfo">
			      								<sec:authentication property="principal.name"/> 
			      							</span>
			      								<c:if test="${type eq 'mentor'}">
			      									<span class="ms-1">멘토님</span>
			      								</c:if>
			      								<c:if test="${type eq 'mentee'}">
			      									<span class="ms-1">멘티님</span>
			      								</c:if>
		      							</div>
		      							<!-- 자신의 별점과 기타정보들 -->
		      						</div>
	      							
	      							
	      						<!-- </div> -->
	      					</li>
	      					
      					<sec:authorize access="hasRole('GENERAL')">	
	      					
	      					<li>
	      						<c:if test="${type eq 'mentor'}">
	      							
	      							<div class="d-flex justify-content-around dropdown-item">
	      								<i class="fi fi-rr-box-alt mx-2"></i>
	      								<span id="manageprofile" class="flex-grow-1 text-center">프로필 관리</span>
	      							</div>
		     								
	   							</c:if>
	     							
	     							
	      							<div class="d-flex justify-content-around dropdown-item">
		      							<!-- <i class="fi fi-rr-rotate-right"></i> -->
		      							<i class="bi bi-arrow-repeat mx-2"></i>
		      							<span class="changeType flex-grow-1 text-center">
		      								<c:if test="${type eq 'mentor'}">
		      									멘티로 전환
		      								</c:if>
		      								<c:if test="${type eq 'mentee'}">
		      									멘토로 전환
		      								</c:if>
		      							</span>
	      							</div>
	      					</li>
	      					
  						</sec:authorize> 							
	      						
	      					
	      					<li>
	      						<div class="dropdown-item d-flex justify-content-around">
	      							<i class="fi fi-rr-sign-out-alt mx-2"></i>
	      							<span class="logoutbtn flex-grow-1 text-center">로그아웃</span>
	      						</div>
	      						
      						</li>
      						
	      				</ul>
	      				
	      				
	      			
	      			

	      		</sec:authorize>
		        
		        <sec:authorize access="isAnonymous()"> 
		        	<span class="inner list" id="loginTab">로그인</span>
		        	<span class="inner" id="register">회원가입</span>
		        </sec:authorize>
		       
	      </li>
	      
    </ul>
	    
</div>

    <!--offcanvas area-->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="sidemenu" aria-labelledby="offcanvasExampleLabel">
      <div class="offcanvas-header">
      <a id="logo"><img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/logo.svg"></a>
        <!-- <h5 class="offcanvas-title" id="offcanvasExampleLabel">당신의 멘토를 찾아보세요!</h5> -->
        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="container d-flex flex-column">
        	<sec:authorize access="isAnonymous()">
        		<li><h4 class="fs-5 my-2 oc-reg">회원가입</h4></li>
        		<li><h4 class="fs-5 my-2 oc-login">로그인</h4></li>
        	</sec:authorize>
        	<sec:authorize access="isAuthenticated()">
        		<li><h4 class="fs-5 my-2 logoutbtn btn btn-danger">로그아웃</h4></li>
	        		
	        		
	        		
	        		<sec:authorize access="hasRole('GENERAL')">
	        		
		        		<li>
		        			<h5 class="fs-6 mb-2 mt-5" onclick="location.href='${pageContext.request.contextPath}/post/list';">
		        				<i class="bi bi-card-list mx-2"></i>내가 쓴 글
		        			</h5>
		        		</li>
		        		<li>
		        			<h5 class="fs-6 my-2" id="oc-request">
		        				<i class="bi bi-inbox mx-2"></i>받은 제의
		        			</h5>
	        			</li>
		        		<li>
		        			<h5 class="fs-6 changeType">
		        				<i class="bi bi-arrow-repeat mx-2"></i>
				        		<c:set var="type">
				        			<sec:authentication property="principal.m_type"/>
				        		</c:set>	
				        		<c:if test="${type eq 'mentor'}">
										멘티로 전환
									</c:if>
									<c:if test="${type eq 'mentee'}">
										멘토로 전환
									</c:if>
			        		</h5>
		        		</li>
		        		
		        		<li>
		        			<h5 class="fs-6" id="oc-chat">
		        				<i class="bi bi-chat-dots-fill mx-2"></i>채팅
		        			</h5>
		        		</li>
	        		
	        		</sec:authorize> 
	        	
	        	      
		        <li>
		            <h4 class="fs-6 mt-5 mb-2">알려드림은 어떤 서비스인가요?</h4>
		        </li>
	            <li>
		            <h4 class="fs-6 my-2">이용안내</h4>
		            
	        	</li>
	        </sec:authorize>         
        </ul>
        
      </div>
    </div>
    
    
    <!-- end of offcanvas area -->
    