<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">
<%@include file="/WEB-INF/views/defaultpageset.jsp" %>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<style>


	#wrap{
	
		margin-top: 150px;
		margin-bottom: 50px;
	}
	
	#regBtn{
	
		width:100px;
	}
</style>
<title>문의글 등록</title>
</head>
<body>

<div class="gw">
	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	
	
	<div class="container" id="wrap">
	
		<div class="d-flex flex-row">
		
		
			<%@include file="/WEB-INF/views/layout/aside.jsp" %>
			
			
			<div class="flex-grow-1 px-5">
			
			
				<div class="input-group mb-2">
				
					<span class="input-group-text">제목</span>
					<input type="text" class="form-control" id="title"/>
					
				</div>
				
				
				<div id="content">
				
					
				
				</div>
			
				
				<div class="text-center my-4">
					<button type="button" class="btn btn-general" id="regBtn">등록</button>
				</div>
				
			</div>
		
		
		</div>
	
	
	
	
	</div>

</div>



<%@include file="/WEB-INF/views/layout/footer.jsp" %>




</body>

<%@ include file="/WEB-INF/views/qna/pageset/qnaregisterformpageset.jsp" %>
</html>