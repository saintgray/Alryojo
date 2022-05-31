<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post/writeform.css">
<title>알려드림 : 요청서 작성</title>

</head>
<body>

<div class="gw">


	

	<!-- Header -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>

	<!-- Container -->
	<div class="container">

 		<sec:authorize access="isAuthenticated()">
			<c:set var="type">
				<sec:authentication property="principal.m_type" />
			</c:set>
			<c:set var="idx">
				<sec:authentication property="principal.m_idx" />
			</c:set>
		</sec:authorize>

		<!-- 프로필 출력 : 멘토일 때에만 -->
		<c:if test="${type eq 'mentor'}">
			<div class="d-flex flex-column mt-3">
				<div class="descript my-1 ms-0 me-1">프로필</div>
				<c:if test="${writerProfile ne null}">
					<table>
						<tr>
							<td rowspan="2"><img
								src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${writerProfile.m_photo}"
								height="50"></td>
							<td>${writerProfile.m_nm}/ ${writerProfile.loc_nm}</td>
						</tr>
						<tr>
							<td colspan="2">${writerProfile.line}</td>
						</tr>
					</table>
				</c:if>

				<c:if test="${writerProfile eq null}">
				<script>
					if(confirm('프로필을 먼저 작성해야 합니다. 작성하시겠습니까?')){
						location.href="${pageContext.request.contextPath}/member/profile/register";	
					} else{
						location.href="${pageContext.request.contextPath}";
					}
				</script>
				</c:if>
			</div>
		</c:if>

		<!-- 게시글 작성 -->
		<div class="my-5">

			<form role="form" method="post" enctype="multipart/form-data"
				id="writePost">

				<!-- 분야 선택 -->
				<div class="d-flex flex-row my-3">
					<div class="descript my-1 ms-0 me-1">분야 선택</div>
					<button type="button" class="btn btn-grey fs-6" onclick="resetCategory()">초기화</button>
				</div>
				<!-- 카테고리 selectbox -->
				<div id="categoryInfos" class="d-flex flex-row flex-wrap  mb-5 mt-1">
				</div>

				<!-- 제목 -->
				<div class="input-group mb-3">
					<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
					<input type="text" class="form-control" name="post_nm" id="post_nm" placeholder="제목을 입력하세요."><br>
				</div>
				
				<!-- 내용 -->
				<div>
				<textarea name="post_content" id="content"></textarea>
				</div>

				<input type="hidden" name="wanted" value="${type eq 'mentor'?'mentee':'mentor'}">
				<input type="hidden" name="m_idx" value="${idx}">

				<!-- 파일업로드 -->
				<div>
				<div class="descript my-1 ms-0 me-1">파일 첨부 | 5mb 이하의 이미지 파일과 PDF 파일만 등록할 수 있습니다.</div>
				<label>
				파일 선택
				<input type="file" name="attachFile" id="attachFile" multiple style="opacity:0;">
				</label>
					<!-- 업로드한 결과 -->
					<div id="uploadResult">
						
					</div>
					<!-- 업로드 파일 정보 저장하는 div -->
					<div id="uploadList">
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
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>	

</body>
<%@ include file="/WEB-INF/views/post/pageset/writeformpageset.jsp"%>

</html>