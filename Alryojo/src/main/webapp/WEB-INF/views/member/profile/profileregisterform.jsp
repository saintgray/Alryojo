<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<%@include file="/WEB-INF/views/defaultpageset.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<!-- timepicker script & css -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<!-- profileregisterform CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/profileregisterform.css">

<title>프로필 등록</title>
</head>
<body>

<div class="gw">

	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	<div class="container d-flex- flex-wrap flex-column justify-content-around" id="profileformwrap">
		
		
		
		<div class="banner">프로필 등록</div>
		
		
		<div class="progress">
	  		<div class="progress-bar bg-warning" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
		
		
		<div class="container d-flex flex-column flex-wrap guide" id="linearea">
		
				<div class="subbanner">
					WHO AM I?
				</div>
				<div class="descript mb-5">
					멘티들에게 자신을 한 줄로 소개해보세요!
				</div>
				
				
				<input style="width: 50%" type="text" class="form-control my-5 mx-auto" placeholder="경력  5년의 첼로리스트 !!" name="line">
				
				
			
				<div class="d-flex flex-row justify-content-center my-5">
					<button type="button" class="btn btn-grey prev mx-4">이전</button>
					<button type="button" class="btn btn-general next mx-4">다음</button>
				</div>
		</div>
		
		<div class="container d-flex flex-column flex-wrap guide hide" id="calltimearea">
		
				<div class="subbanner">
					연락 가능한 시간대를 선택하세요
				</div>
				<div class="descript mb-5">
					멘티들에게 더 많은 연락을 받으실 수 있어요!
				</div>
				
				<div class="d-flex flex-wrap p-2 justify-content-center" id="timezone">
					<input  type="text" class="form-control timepicker"  name="calltime_prefix" id="calltime">
					<span>~</span>
					<input type="text" class="form-control timepicker"  name="calltime_suffix" id="calltime">
				</div>
				
			
				<div class="d-flex flex-row justify-content-center my-5 ">
					<button type="button" class="btn btn-grey prev mx-4">이전</button>
					<button type="button" class="btn btn-general mx-4 next">다음</button>
				</div>
		</div>
		
		
		
		<div class="container d-flex flex-column flex-wrap guide hide" id="careerarea">
		
				<div class="subbanner">
					실력있는 멘토는 경력이 필수!
				</div>
				<div class="descript mb-5">
					멘티들에게 자신을 어필해보세요
				</div>
				<div id="career">
					<div class="summernote_career"></div>
				</div>
				
				<div class="descript my-3">실력을 증명하는 자격증으로 멘티의 신뢰도 UP!</div>
				<div id="prof-files">
					<label for="myfiles" class="btn btn-general m-2">
						첨부파일 등록
					</label>
					<input type="file" style="display: none" id="myfiles" multiple>
					<div class="mx-2 mb-2">파일 미리보기</div>
				</div>
				
				
				<div id="selected" class="d-flex flex-wrap">
					
					
				</div>
				<div class="d-flex flex-row justify-content-center my-5 ">
					<button type="button" class="btn btn-grey prev mx-4">이전</button>
					<button type="button" class="btn btn-general next mx-4">다음</button>
				</div>
				
				
			</div>
			
			
			<div class="container d-flex flex-column flex-wrap guide hide" id="qnaarea">
		
				<div class="subbanner">
					QNA
				</div>
				<div class="descript mb-5">
					멘티들에게 자주받는 질문이 있나요? 여기에 등록해보세요
				</div>
				<div id="career">
					<div class="summernote_qna"></div>
				</div>
				
				<div class="d-flex flex-row justify-content-center my-5 ">
					<button type="button" class="btn btn-grey prev mx-4">이전</button>
					<button type="button" class="btn btn-warning next mx-4" id="registerbtn">프로필 등록</button>
				</div>
				
				
				
			</div>

	</div>
	
	
</div>

<%@include file="/WEB-INF/views/layout/footer.jsp" %>


<%@include file="/WEB-INF/views/member/profile/pageset/profileregisterformpageset.jsp" %>
</body>


</html>