<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout/footer.css">    
<footer>
	<div id="footerwrap">
	
	        <div class="d-flex flex-row flex-wrap justify-content-center" id="innerfooterwrap">
	
	            <!-- 1. 로고 2. 회사소개 3. 오시는 길 4. 고객센터-->
	
	            <img src="${pageContext.request.contextPath}/resources/files/server/bannerimg/logo.svg" class="mx-4" id="logo">
	            <div class="f-info mx-4">
	                
	                <ul class="f-list">
	                	<li><span class="head">회사소개</span></li>
	                    <li>알려드림</li>
	                    <li>채용안내</li>
	                </ul>
	                
	            </div>
	
	            <div class="f-info mx-4">
	            	<ul class="f-list">
	            		<li><span class="head">고객안내</span></li>
	            		<li>이용안내</li>
	                    <li>이용약관</li>
	            	</ul>
	               
	            </div>
	
	            <div class="f-info mx-4">
	                
	                <ul class="f-list">
	                	<li><a class="head" href="${pageContext.request.contextPath}/customerCenter">고객센터</a></li>
	                    <li><a href="${pageContext.request.contextPath}/notice?selectPage=1&numOfNoticesPerPage=5">공지사항</a></li>
	                    <li><a href="${pageContext.request.contextPath}/qna/list?selectPage=1&numOfQnaPerPage=5">1:1문의</a></li>
	                    <li>자주 묻는 질문</li>
	                    
	                </ul>
	                
	               
	            </div>
	
	        </div>
	
	        
	
	</div>
</footer>