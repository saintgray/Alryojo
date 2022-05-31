<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<style>
	.article{
	padding: 20px 40px 0;
    border: 1px solid;
    border-radius: 6px;
	}
	
	.delRow{
		width:15px;
		height:15px;
		border-radius: 50%;
		border: 1px solid rgb(195,195,195);
		cursor:pointer;
	}

	#files{
		border: 1px solid black;
		border-radius: 10px;
		height:150px;
		
	}
</style>
<title>공지사항</title>
</head>
<body>




<div class="gw">
	
	<%@include file="/WEB-INF/views/layout/header.jsp" %>
	<div class="container">
	
	
		<h1 class="mx-5 banner">공지사항</h1>
		
		
		<div class="d-flex justify-content-start mx-5 my-4">
		    <button type="button" class="btn btn-general" id="listbtn">목록</button>
	    </div>
	    
		<div class="container article">
				
			
			<h1 align="center">제목 : ${content.notice_title}</h1>
			<h6 align="right">작성자: ${content.admin_nm}</h6>
			<h6 align="right">작성일 : ${content.notice_regdate} 
			<c:if test="${not empty content.notice_editdate}">
				수정일  : ${content.notice_editdate}
			</c:if>
			</h6><hr>
			<h4>내용: ${content.notice_content}</h4>
				
		</div>
		
		
		
		<!-- 첨부파일들 -->
		<c:if test="${not empty content.files}">
			<div class="d-flex flex-row flex-wrap p-2 my-5" id="files">
				
			
				<c:forEach items="${content.files}" var="file">
				
					<div class="d-flex flex-row">
					
						<i class="bi bi-file-earmark"></i>
						<span class="fileName d-none">${file.file_nm}</span>
						<a href="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/notice/attachfiles${file.file_nm}" download="${file.file_originnm}" class="mx-2">${file.file_originnm}</a>
						
					</div>
				</c:forEach>
			
			
			</div>
		</c:if>
		
		<div class="d-flex flex-row flex-wrap justify-content-end mx-5 my-4">   
		    <sec:authorize access="hasRole('ADMIN')">
		    	<c:set var="admin_idx">
					<sec:authentication property="principal.admin_idx"/>
				</c:set>
			    <c:if test="${admin_idx eq content.admin_idx}">
			    	<div>
						<button type="button" class="btn btn-general" id="editbtn">수정</button>
						<button type="button" class="btn btn-danger" id="delbtn">삭제</button>
					</div>		
				</c:if>	
			</sec:authorize>
		</div>
	
	</div>
	
	
	    
		
	
</div>

<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>

<%@include file="/WEB-INF/views/notice/noticepageset/noticecontentpageset.jsp" %>
</html>