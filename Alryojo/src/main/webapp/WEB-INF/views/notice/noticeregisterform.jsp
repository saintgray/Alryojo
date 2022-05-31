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
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style>
#registerarea {
	margin-top: 150px;
	margin-bottom: 150px;
}

#btnarea {
	margin-top: 40px;
}

.delRow{
		width:15px;
		height:15px;
		border-radius: 50%;
		border: 1px solid rgb(195,195,195);
		cursor:pointer;
}

#selected{
	border: 1px solid black;
	border-radius: 10px;
	height:150px;
	
}
</style>
<title>공지사항등록</title>
</head>
<body>
	

	<div class="gw">
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<div id="registerarea" class="container">
			<h1 class="banner">공지사항 등록</h1>

			<input type="text" placeholder="제목" id="notice_title" class="form-control input-group">


			<div class="notice_content"></div>
			
			
			
			
			
			
			

			
				
				<!-- 파일업로드 -->
				<div class="mt-5">
					
					<label for="myfiles" class="my-1">
						<span class="btn btn-general">파일 선택</span> 
					</label>
					
					<input type="file" class="d-none" id="myfiles" multiple>
					<!-- 업로드한 결과 -->
					
				</div>

				<div id="selected" class="d-flex flex-row flex-wrap">
				
				
				</div>
				
				
			

			<div class="d-flex justify-content-center" id="btnarea">
				<button type="button" class="btn btn-general mx-2" id="registerbtn">등록</button>
				<button type="button" class="btn btn-general mx-2" id="cancelbtn">취소</button>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>

<%@ include file="/WEB-INF/views/notice/noticepageset/noticeregisterformpageset.jsp"%>

</html>