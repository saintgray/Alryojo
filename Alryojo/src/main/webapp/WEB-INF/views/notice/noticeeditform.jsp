<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style>
	
	#editarea{
		margin-top: 150px;
		margin-bottom: 150px;
	}
</style>
<title>공지사항수정</title>
</head>
<body>

<div class="gw">
	<%@ include file="/WEB-INF/views/layout/header.jsp" %>

	<div id="editarea" class="container">
		<h1 class="banner">공지사항 수정</h1>
		
	
			
			<input type="text" placeholder="제목" id="notice_title" value="${noticeContent.notice_title}" class="form-control input-group">
				
				
				<div class="notice_content">
					${noticeContent.notice_content}
				</div>
			
			<sec:authorize access="hasRole('ADMIN')">
				<button type="button" class="btn btn-general" id="editProcessbtn">수정</button>
				<button type="button" class="btn btn-danger" id="cancelbtn">취소</button>
			</sec:authorize>	
	
	</div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

</body>

<%@include file="/WEB-INF/views/notice/noticepageset/noticeeditformpageset.jsp" %>

</html>