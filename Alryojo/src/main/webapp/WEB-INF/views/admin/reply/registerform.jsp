<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

	#registerarea{
		margin-top: 150px;
		margin-bottom: 150px;
	}
	#btnarea{
		margin-top: 40px;
	}
</style>

<title>답글등록</title>
</head>
<body>

<div class="gw">
	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	

	<div id="registerarea" class="container">
		
		<h1 class="banner">답글등록</h1>
	
		<input type="text" placeholder="제목" id="reply_title" class="form-control input-group">
		
		
		<div class="reply_content"></div>
		
		
		
		<div class="d-flex justify-content-center" id="btnarea">
			<button type="button" class="btn btn-general" id="registerbtn">등록</button>
			<button type="button" class="btn btn-general" id="cancelbtn">취소</button>
		</div>
	</div>
	
	
	
	
	
</div>

<%@include file="/WEB-INF/views/layout/footer.jsp" %>



</body>


<script>

	$(document).ready(function(){
		
		$('.reply_content').summernote({
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    /* ['color', ['color']], */
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']],
			  ],
			 
			 minHeight: 500,
			 maxHeight: 500,
	         codemirror: {
	        	 theme: 'monokai'
	         },
	         lang: 'ko-KR',
	         placeholder: '내용'		
		})
		
		$('#registerbtn').on('click',
				function() {
					
				$.ajax({
					url:'${pageContext.request.contextPath}/admin/reply/register',
					type:'POST',
					data:{
						m_idx: ${param.m_idx},
						qna_idx:${param.qna_idx},
						reply_content:$('.reply_content').summernote('code')
					},
					success:function(data){
						console.log(data);
						if(data==1){
							history.go(-2);
						}else{
							
							alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
						}
					},
					error:function(data){
						console.log(data);
						alert('오류가 발생하였습니다 잠시 후 다시 시도하세요');
					}
					
				})
					
					
					
				})
				$('#cancelbtn').click(
						function(){
							
							history.go(-2);
						})
		
		
	})

</script>
</html>