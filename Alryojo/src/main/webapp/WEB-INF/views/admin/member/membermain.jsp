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
<style>
#listTable span {
	padding: 0 10px;
}

@media ( max-width : 720px) {
	.subinfo{
		display: none;
	}
	
	
}

.m_img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

#globalwrap {
	height: 100%;
	min-width: 500px;
	margin-top: 100px;
	margin-bottom: 50px;
}
/* 	#list{
		margin-top: 10px;
		margin-bottom: 10px;
	}
	 */
#listTable {
	border-collapse: separate;
	border-spacing: 0 30px;
	text-align: center;
}

.indexes {
	outline: 1px solid rgb(123, 123, 123);
	background-color: rgb(197, 197, 197);
}

#membersearch {
	width: 500px;
}

#searchBtn, i, #blacklistcolumn{
	cursor: pointer;
}


</style>
<title>회원리스트</title>
</head>
<body>
	<div class="gw">
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>

		<div class="d-flex flex-column" id="globalwrap">
		
		
		
		
		
			<h1 class="mx-5 banner">
				<a href="${pageContext.request.contextPath}/admin/member/list">회원 목록</a>
			</h1>
			
			
				<div class="d-flex flex-row justify-content-start">
				
					<%@ include file="/WEB-INF/views/layout/aside.jsp" %>
				
				
					
					<div class="ms-5 flex-grow-1">			
						<table class="mx-auto" id=listTable>
							<tr class="py-2 indexes">
								<td>사진</td>
								<td><span class="mx-2">번호</span></td>
								<td>아이디</td>
								<td><span class="mx-2">이름</span></td>
								<td>
									<div class="mx-2 subinfo" id="sortbyregdate">
									
									<c:if test='${member.sortBy eq "asc"}'>
										가입일 <i class="bi bi-arrow-down-short" id="sortbydesc" title="최신순"></i>
									</c:if> 
									<c:if test='${member.sortBy eq "desc"}'>
										가입일 <i class="bi bi-arrow-up-short" id="sortbyasc" title="가입순"></i>
									</c:if>
									</div> 
									
								</td>
								<td><span class="mx-2 subinfo">탈퇴일</span></td>
								<td><span class="mx-2 subinfo" id="blacklistcolumn">블랙리스트</span></td>
								<td><span class="mx-2 subinfo">신고</span></td>
							</tr>
			
			
							<c:forEach var="item" items="${member.list}">
			
								<tr class="py-2">
									<td><img class="mx-2 m_img"
										src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${item.m_photo}"></td>
									<td><span class="mx-2">${item.m_idx}</span></td>
									<td><a class="mx-2 justify-content-center"
										href="${pageContext.request.contextPath}/admin/member/info?m_idx=${item.m_idx}">${item.m_email}</a></td>
									<td><span class="mx-2">${item.m_nm}</span></td>
									<td><span class="mx-2 subinfo">${item.m_regdate}</span></td>
									<td><span class="mx-2 subinfo">${item.m_quitdate}</span></td>
									<td><span class="mx-2 subinfo"> 
										<c:if test="${item.m_blacklist eq 'N'}">
												X
										</c:if> 
										<c:if test="${item.m_blacklist eq 'Y'}">
												O
										</c:if>
									</span></td>
									<td><span class="mx-2 subinfo">${item.reportCount}</span></td>
								</tr>
			
							</c:forEach>
						</table>
			
						<div class="d-flex justify-content-center my-5">
							<div class="input-group inner" id="membersearch">
								<div class="input-group-prepend">
									<select class="form-select" aria-label="Default select example"
										id="searchTypeBox">
										<option value="all" selected>전체</option>
										<option value="onlyname">이름</option>
										<option value="onlyid">아이디</option>
									</select>
								</div>
			
								<input id="searchbararea" style="border-left: 0;" type="text"
									class="form-control" aria-label="Amount (to the nearest dollar)"
									placeholder="검색">
									 <img class="input-group-text" style="height: 100%"
									src="${pageContext.request.contextPath}/resources/files/server/icons/img_searchicon.svg"
									id="searchBtn">
								
							</div>
						</div>
			
						<div class="d-flex justify-content-center my-5" id="paging">
							<c:forEach begin="1" end="${member.totalPage}" var="pnum">
							
								<c:if test="${pnum eq member.selectPage}">
									<span class="mx-2 btn btn-warning">${pnum}</span>
								</c:if>
								<c:if test="${pnum ne member.selectPage}">
									<span class="mx-2 btn btn-grey" style="cursor: pointer">${pnum}</span>
								</c:if>
								
								
			
							</c:forEach>
						</div>
					</div>
					
					
			
			
			</div>
			
			

		</div>
	</div>
	<div class="d-none">
		<div>
			<form method="post" id="searchParameter">
				<input type="text" name="searchType" value="${member.searchType}">
				<input type="text" name="selectPage" value="${member.selectPage}">
				<input type="text" name="numOfMemberPerPage" value="${member.numOfMemberPerPage}"> 
				<input type="text" name="keyword" value="${member.keyword}"> 
				<input type="text" name="sortType" value="${member.sortType}"> 
				<input type="text" name="sortBy" value="${member.sortBy}">
				<input type="text" name="blacklisted" value="${member.blacklisted}">
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
<script>
	$(function() {
		
		
		$('#searchTypeBox option[value='+ $('#searchParameter input[name=searchType]').val()+ ']').prop('selected', true);
		$('#searchbararea').val($('#searchParameter input[name=keyword]').val());

		$('#sortbyregdate').on('click','i',function() {
					$('#searchParameter input[name=selectPage]').val(1);
					$('#searchParameter input[name=sortType]').val('m_regdate');
					
					if($(this).attr('id')=='sortbyasc'){
						$('#searchParameter input[name=sortBy]').val('asc');
					}else{
						$('#searchParameter input[name=sortBy]').val('desc');
					}
					$('#searchParameter').submit();
				})

		$('#blacklistcolumn').click(function() {
			$('#searchParameter input[name=blacklisted]').val('Y');
			$('#searchParameter input[name=selectPage]').val(1);
			$('#searchParameter').submit();

		});
		
		// 페이지를 클릭시 >> 보고 있는 페이지가 해당 페이지가 아닐 때만 페이지 이동
		$('#paging span').on('click', function() {
			
			if($(this).text()!=${member.selectPage}){
				$('#searchParameter input[name=selectPage]').val($(this).text());
				$('#searchParameter').submit();
			}
		})

		
		$('#searchTypeBox').on('change',function() {
					
			// 검색한 회원 결과를 보거나 정렬순으로 보다가 전체 탭으로 변경시 
			if($(this).children('option:selected').val()=='all'){
				
				$('#searchParameter input[name=keyword]').val("");
				$('#searchParameter input[name=searchType]').val("all");
				$('#searchParameter input[name=selectPage]').val(1);
				$('#searchParameter').submit();
				
			}else{
				$('#searchParameter input[name=searchType]').val($('#searchTypeBox option:selected').val());	
			}
			

		})

		$('#searchBtn').on('click',function() {

			$('#searchParameter input[name=keyword]').val($('#searchbararea').val());
			$('#searchParameter input[name=selectPage]').val(1);
			$('#searchParameter').submit();

		})

	})
</script>
</html>