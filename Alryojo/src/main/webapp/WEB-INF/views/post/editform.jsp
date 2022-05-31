<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<c:set var="idx">
		<sec:authentication property="principal.m_idx" />
	</c:set>
	<c:set var="type">
		<sec:authentication property="principal.m_type" />
	</c:set>
</sec:authorize>

<c:if test="${idx ne editRequest.m_idx}">
	<script>
		alert('수정 권한이 없습니다.');
		history.go(-1);
	</script>
</c:if>

<c:if test="${idx eq editRequest.m_idx}">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="UTF-8">
<title>알려드림 : 요청서 수정</title>
<style>
#uploadResult img {
	max-height: 64px;
	max-width: 100px;
	width: auto;
	order: 1;
}
.hide{
	display:none;
}
#btnArea{
	margin: 30px auto;
	text-align: center;
}
</style>
</head>
<body>

<div class="gw">

<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>
<%@ include file="/WEB-INF/views/post/pageset/editformpageset.jsp"%>

<!-- Header -->
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!-- Container -->
<div class="container">

<!-- 프로필 출력 : 멘토일 때에만 -->
	<c:if test="${type eq 'mentor'}">
		<div class="d-flex flex-column my-3">
			<div class="descript my-1 ms-0 me-1">프로필</div>
			<c:if test="${writerProfile ne null}">
				<table>
					<tr>
						<td rowspan="2"><img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${writerProfile.m_photo}" height="50"></td>
						<td>${writerProfile.m_nm}/${writerProfile.loc_nm}</td>
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

<!-- 게시글 작성 -->
<div>

<form role="form" method="post" enctype="multipart/form-data" id="writePost">

<input type="hidden" name="m_idx" value="${idx}">
<input type="hidden" name="wanted" value="${type eq 'mentor'?'mentee':'mentor'}">
<input type="hidden" name="post_idx" value="${editRequest.post_idx}">

<!-- 카테고리 -->
선택된 분야 : ${editRequest.cat_nm}

<!-- 제목 -->
<div class="input-group mb-3">
	<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
	<input type="text" class="form-control" name="post_nm" id="post_nm" value="${editRequest.post_nm}"><br>
</div>

<!-- 내용 -->
<textarea name="post_content" id="content">${editRequest.post_content}</textarea>

<!-- 파일업로드 -->
<div>
	<div class="descript my-1 ms-0 me-1">파일 첨부 | 5mb 이하의 이미지 파일과 PDF 파일만 등록할 수 있습니다.</div>
	<label>
		파일 선택
		<input type="file" name="attachFile" id="attachFile" multiple style="opacity:0;">
	</label>

<!-- 업로드한 결과 -->
	<div id="uploadResult">
		<!-- 기등록 파일 리스트 - update_status : I (Include) -->
		<c:if test="${not empty editRequest.fileList}">
			<ul class="list-group">
				<c:forEach var="postFile" items="${editRequest.fileList}">
					<li class="list-group-item d-flex justify-content-between align-items-center"
							data-file_nm="${postFile.file_nm}" data-exet="${postFile.file_exet}">
					<c:if test="${postFile.file_exet ne 'pdf'}">
						<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/post/attachfiles/${postFile.file_nm}.${postFile.file_exet}">
						<span><i class="bi bi-image fs-4"></i>
						    ${postFile.file_originnm}.${postFile.file_exet} (${postFile.file_size} kb)
							<button type="button" data-file_nm="${postFile.file_nm}"
												  data-originnm="${postFile.file_originnm}"
												  data-exet="${postFile.file_exet}"
												  data-size="${postFile.file_size}"
												  data-update_status="I"
												  class="btn btn-warning btn-circle">X</button>
						</span>

					</c:if>
					<c:if test="${postFile.file_exet eq 'pdf'}">
						<span><i class="bi bi-filetype-pdf fs-4"></i>
							${postFile.file_originnm}.${postFile.file_exet} (${postFile.file_size} kb)
							<button type="button" data-file_nm="${postFile.file_nm}"
												  data-originnm="${postFile.file_originnm}"
												  data-exet="${postFile.file_exet}"
												  data-size="${postFile.file_size}"
												  data-update_status="I"
												  class="btn btn-warning btn-circle">X</button> </span>
					</c:if>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>

</div>

<div id="btnArea">
	<button type="reset" class="btn btn-outline-dark">리셋</button>
	<button type="button" id="submitBtn" class="btn btn-outline-dark">작성</button>
</div>

</form>

</div>

</div>

</div>

<!-- Footer -->
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>

</body>

</html>
</c:if>