<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${viewRequest eq null}">
	<script>
		alert('해당 게시물이 존재하지 않습니다.');
		history.go(-1);
	</script>
</c:if>
<c:if test="${viewRequest ne null}">

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post/view.css">
<meta charset="UTF-8">
<title>알려드림</title>
</head>
<body>

<div class="gw">


<%@ include file="/WEB-INF/views/post/pageset/viewpageset.jsp"%>

<!-- Header -->
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- Container -->
<div class="container" id="PostViewArea">

<sec:authorize access="isAuthenticated()">
	<c:set var="idx">
	   <sec:authentication property="principal.m_idx" />
	</c:set>
	<c:set var="type">
	   <sec:authentication property="principal.m_type" />
	</c:set>
	<c:set var="name">
	   <sec:authentication property="principal.name" />
	</c:set>
</sec:authorize>

<input type="hidden" id="postidx" value="${viewRequest.post_idx}">
<input type="hidden" id="wanted" value="${viewRequest.wanted}">
<input type="hidden" id="midx" value="${viewRequest.m_idx}">
<input type="hidden" id="myidx" value="${idx}">
<input type="hidden" id="mytype" value="${type}">
<input type="hidden" id="myname" value="${name}">

<!-- 제목 -->
<div class="input-group mb-3">
  <span class="input-group-text">${viewRequest.cat_nm}</span>
  <span class="form-control">${viewRequest.post_nm}</span>  
</div>

<!-- 프로필 영역 -->
<div class="row">
<c:if test="${viewRequest.wanted eq 'mentee'}">
	<div class="d-flex flex-column my-3 col text-center border rounded">
		<div class="descript my-1 ms-0 me-1 text-start">프로필</div>
		<c:if test="${writerProfile ne null}">
			<table>
				<tr>
					<td rowspan="2">
					<a href="${pageContext.request.contextPath}/member/profile/main?m_idx=${writerProfile.m_idx}">
					<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${writerProfile.m_photo}" height="50"></a></td>
					<td>${writerProfile.m_nm}/ ${writerProfile.loc_nm}</td>
				</tr>
				<tr>
					<td colspan="2">${writerProfile.line}</td>
				</tr>
			</table>
		</c:if>

		<c:if test="${writerProfile eq null}">
			글쓴이의 프로필 정보가 없습니다. 프로필 등록이 필요합니다.
		</c:if>
	</div>
</c:if>
</div>

<!-- 본문 -->
<div>
${viewRequest.post_content}
</div>

<!-- 첨부파일 -->
<div id="attachedfiles">
	<c:if test="${not empty viewRequest.fileList}">
	<ul class="list-group">
	<c:forEach var="postFile" items="${viewRequest.fileList}">
		<li class="list-group-item d-flex justify-content-between align-items-center"
			data-file_nm="${postFile.file_nm}" data-exet="${postFile.file_exet}">
			
		<c:if test="${postFile.file_exet ne 'pdf'}">
		
			<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/post/attachfiles/${postFile.file_nm}.${postFile.file_exet}">
			<span><i class="bi bi-image fs-4"></i> ${postFile.file_originnm}.${postFile.file_exet} (${postFile.file_size} kb)</span>
			
		</c:if>
			
		<c:if test="${postFile.file_exet eq 'pdf'}">
			<a download="${postFile.file_originnm}.${postFile.file_exet}" 
			   href='https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/post/attachfiles/${postFile.file_nm}.${postFile.file_exet}'>
			
				<span>
					<i class="bi bi-filetype-pdf fs-4"></i>
					${postFile.file_originnm}.${postFile.file_exet} (${postFile.file_size} kb)
				</span>
			</a>
			

		</c:if>
		
		</li>
	</c:forEach>
	</ul>
	</c:if>
</div>

<!-- 첨부파일 이미지 크게 보기 -->
<div id="imageWrapper">
	<div id="bigImage"></div>
</div>

<!-- 매칭정보/문의하기 -->
<div class="row">
<c:if test="${viewRequest.m_idx ne idx && viewRequest.wanted eq type}">
<button type="button" class="btn btn-warning my-5 ms-auto" id="matchBtn">문의하기</button>
</c:if>

<c:if test="${viewRequest.m_idx eq idx}">
	<div class="descript my-1 ms-0 me-1 text-start">문의현황</div>
	<div id="matchInfos">
	
		<c:if test="${viewRequest.match_count eq 0}">
		들어온 문의가 없습니다.
		</c:if>
		<c:if test="${viewRequest.match_count gt 0}">
		들어온 문의는 총 ${viewRequest.match_count} 건입니다. 상세 내용은 받은제의를 확인하세요.
		</c:if>
	
	</div>
	
	<div id="btnArea">
		<button type="button" class="btn btn-outline-dark" onclick="location.href='${pageContext.request.contextPath}/post/edit?idx=${viewRequest.post_idx}'">수정</button>
		<button type="button" class="btn btn-outline-dark" onclick="javascript:deletePost(${viewRequest.post_idx})">삭제</button>
	</div>

</c:if>
</div>

</div>

</div>

<!-- Footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>




<!-- 문의하기 모달 -->
		<div class="modal fade" id="requestYN" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title"></h5>
		      </div>
		      
		      <div class="modal-body">
		        	<h4>${writerProfile.m_nm} 님에게 문의하시겠습니까?</h4>
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="rqYes" >네</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
		      </div>
		    </div>
		  </div>
		</div>		


<!-- 채팅보내기 모달 -->
		<div class="modal fade" id="sendchat" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title"></h5>
		      </div>
		      
		      <div class="modal-body">
		        	 <h4>성공적으로 문의가 이루어졌습니다.</h4>
		        	 <div style="margin-bottom: 10px; font-size: 1.2em">${writerProfile.m_nm} 님에게 근사한 첫인상을 남길 첫마디를 작성해주세요!</div>
	        	<div class="msgRow">
					<input type="text" class="msg" placeholder="내용을입력해주세요." style="width: 100%; margin-bottom: 10px">
					<input type="button" value="채팅보내기" class="btn btn-primary" id="msgBtn"style="float: right">
				</div>
				
		      </div>
		      
		      <div class="modal-footer">
		      	${writerProfile.m_nm}님에게 보낸 첫마디는 내 채팅목록에서 확인해주세요.

		      </div>
		    </div>
		  </div>
		</div>	
  
 
</body>

</html>

</c:if>