<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/chat/chatroom.css">
<title>채팅창</title>

<!-- 채팅을 위해 필요 -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

</head>
<body>



<sec:authorize access="isAuthenticated()">

	<c:set var="myidx">
		<sec:authentication property="principal.m_idx"/>
	</c:set>
	<c:set var="myname">
		<sec:authentication property="principal.name"/>
	</c:set>
	<c:set var="mytype">
		<sec:authentication property="principal.m_type"/>
	</c:set>
</sec:authorize>
<span id="myname" class="d-none">${myname}</span>
<input type="hidden" value="${matchidx}">

<div class="gw">

<!-- Header -->
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
	<div class="chatRoom-whole">
	<!-- chatRoom Header--------------------------------------------------------------------------------------------- -->
		<div class="chatRoom-header">
		
			<div class="recieverName">
				<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${recieverInfo.m_photo}" class="profile_photo">
					<!-- 상대방 프로필로 이동하기 -->
					<c:if test="${mytype=='mentee'}">
						<button type="button" class="btn btn-success" id="profileBtn"><a href="${pageContext.request.contextPath}/member/profile/main?m_idx=${reciever}" style="color: white">${recieverInfo.m_nm}님의 프로필 보기</a></button>
					</c:if>
					<c:if test="${mytype=='mentor'}">
						<button type="button" class="btn btn-success" id="profileBtn">${recieverInfo.m_nm}님과의 채팅방</button>
					</c:if>
			</div>
			
			
			<div class="btns">
					<!-- 채팅나가기 -->
					<!-- 미완 : 채팅목록으로 리다이렉트/그리고 ajax로 update match mentee_outyn또는 mentor_outyn Y로 하기-->
						<button class="btn btn-light" data-bs-target="#leaveChk" data-bs-toggle="modal">채팅 </span>나가기</button>		
					
					<!-- 신고하기버튼  -->
						<button class="btn btn-light" data-bs-target="#reportForm" data-bs-toggle="modal" id="report" >신고하기</button>
				
					<span class="rightBtn">
					<c:if test="${mytype eq 'mentee'}">
						<!-- 매칭하기 -->
						<!-- 보이는 조건 : 매칭여부 N & 매칭날짜가null이어야함(작동)-->
							<button type="button" data-bs-target="#matchChk" data-bs-toggle="modal" id="match"   style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'N' && match.match_date eq null)? 'hide': ''} btn btn-primary">매칭 하기</button>
						<!-- 보이는 조건 : 매칭여부 N & 매칭날짜가 있어야함(비작동)-->
							<button type="button" disabled="true"  style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'N' && match.match_date ne null)? 'hide': ''} btn btn-primary">매칭 하기</button>
					
						<!-- 매칭취소 -->
						<!-- 보이는 조건 : 매칭여부 Y && 매칭날짜가 현재보다 3일이전이어야한다 -->
							<button type="button" data-bs-target="#matchCancleChk" data-bs-toggle="modal" id="unmatch" style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'Y' && unmatchYN eq'Y')? 'hide': ''} btn btn-primary">매칭 취소</button>
							
						<!-- 매칭완료 -->
						<!-- 보이는 조건 : 매칭여부 Y && 매칭날짜가 현재보다 3일이후 -->
							<button id="matched" disabled="true"  style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'Y' && unmatchYN eq'N')? 'hide': ''} btn btn-primary">매칭 완료</button>
						
						<!-- 후기쓰기 -->
							<button type="button" style="background-color : #FFD601; border: 1px solid #FFD601"
							class="btn btn-primary" onclick="review()" id="reviewing">후기 쓰기</button>
					</c:if>
					<c:if test="${mytype eq 'mentor'}">
						<!-- 매칭 전 -->
						<!-- 보이는 조건 : 매칭여부 N & 매칭날짜가null이어야함(작동)-->
							<button type="button" disabled="true" style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'N')? 'hide': ''} btn btn-primary">매칭 전</button>
						
						<!-- 매칭 중 -->
						<!-- 보이는 조건 : 매칭여부 Y && 매칭날짜가 현재보다 3일이후 -->
							<button id="matched" disabled="true" style="background-color : #FFD601; border: 1px solid #FFD601"
							class="${not(match.match_yn eq 'Y')? 'hide': ''} btn btn-primary">매칭 중</button>
					</c:if>
					</span>
			</div>	
		</div>	
		
		<!-- chat Message--------------------------------------------------------------------------------------------- -->
		
		<div id="chatBox" class="chattingBox">
			<c:forEach items ="${chatlist}" var="c">
				<c:set var="presender" value="${c.m_sender}"/>
				<c:set var="prereciever" value="${c.m_reciever}"/>
				<c:set var="present" value="${c.sent}"/>
				<c:set var="premessage" value="${c.message}"/>
				<c:set var="sysmsgYN" value="${c.sysmsgYN}"/>
				
				<!-- 시스템 메세지 창 -->
				<c:if test="${sysmsgYN eq'Y'}">
					<div class='well text_center'>
						<span>${c.sent}</span>
						<span id="sysmsg" class="rounded-pill"><strong>${premessage}</strong></span>
					</div>
				</c:if>
				
				<!-- 일반회원 창 -->
				<c:if test="${sysmsgYN eq'N'}">
					<!-- 내 메세지 창 -->
					<c:if test="${presender==myidx}">
						<div class='well text_right'>
							<span>${c.sent}</span>
							<span id="sendermsg" class="rounded-pill"><strong>${premessage}</strong></span>
						</div>
					</c:if>
					
					<!-- 상대방 메세지 창 -->
					<c:if test="${presender!=myidx}">
						<div class='well text_left'>
							<span id="recieverphoto"><img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${recieverInfo.m_photo}" class="m_photo"></span>
							<span id="recievermsg" class="rounded-pill">${premessage}</span>
							<span>${c.sent}</span>
						</div>
					</c:if>	
				</c:if>
			</c:forEach>	
		</div>
			
		<!-- input Message--------------------------------------------------------------------------------------------- -->
		
		<div class="inputMsg mx-auto mb-5">
			<div class="input-group">
				<input type="text" class="form-control" id="message" />
				<input type="button" class="btn btn-primary" id="sendBtn" value="전송" />
			</div>
			<br>
			<div class="well" id="chatdata">
				<!-- User Session Info Hidden -->
				<input type="hidden" value='${myidx}' id="sessionuseridx">
			</div>
		</div>
	</div>
</div>	

<%@include file="/WEB-INF/views/layout/footer.jsp" %>

<!-- 확인모달들 여기 모아둠--------------------------------------------------------------------------------------------- -->
<!-- 채팅 나감 모달 -->
		<div class="modal fade" id="leaveChk" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title"></h5>
		      </div>
		      
		      <div class="modal-body">
		        	<h4>정말로 채팅창을 나가시겠습니까?</h4>
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="leaveChat" >네</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
		      </div>
		    </div>
		  </div>
		</div>



<!-- 매칭 확인  -->
	<div class="modal fade" id="matchChk" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title"></h5>
		      </div>
		      
		      <div class="modal-body">
		        <h3>${recieverInfo.m_nm}님과 매칭하시겠습니까?</h3>
		         (매칭 후 3일동안만 매칭취소가 가능합니다. 3일이후에는 자동으로 매칭이 확정됩니다.)
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="matchY" >네</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
		      </div>
		    </div>
		  </div>
		</div>



<!-- 매칭취소 확인  -->
	<div class="modal fade" id="matchCancleChk" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title"></h5>
		      </div>
		      
		      <div class="modal-body">
		         <h3>${recieverInfo.m_nm}님과 매칭 취소하시겠습니까?</h3>
		                  한번 취소한 매칭은 다시 매칭이 불가능합니다.
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="matchCancleY" >네</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
		      </div>
		    </div>
		  </div>
		</div>





<!-- 리뷰 작성 모달 -->
		<div class="modal fade" id="reviewform" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title">리뷰작성</h5>
		      </div>
		      
		      <div class="modal-body">
		         	<div style=" margin-bottom : 5px;">실거래를 진행한 경우에만 리뷰를 작성해주세요. 허위 또는 악의적 리뷰는 이용에 제한이 있을 수 있습니다.</div>
		         	<br>
		         	<div>
		         		<span><img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${recieverInfo.m_photo}" class="m_photo2"></span>
		         		<span style="font-size: 1.5rem; padding-left: 8px">${recieverInfo.m_nm} 님</span>
		         	</div>
		         	<div>
		         		<form class="rating">
						  <label>
						    <input type="radio" name="stars" value="1" id="rate"/>
						    <span class="icon">★</span>
						  </label>
						  <label>
						    <input type="radio" name="stars" value="2" id="rate"/>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						  </label>
						  <label>
						    <input type="radio" name="stars" value="3" id="rate"/>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>   
						  </label>
						  <label>
						    <input type="radio" name="stars" value="4" id="rate"/>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						  </label>
						  <label>
						    <input type="radio" name="stars" value="5" id="rate"/>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						    <span class="icon">★</span>
						  </label>
						</form>
		         	</div>
		         	<div>
						<textarea class="form-control" rows="3" id="comment" name="text" placeholder="${recieverInfo.m_nm}님에 대한 리뷰를 작성해주세요.">
						</textarea>
					</div>
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal" id="regReview" >등록하기</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소하기</button>
		      </div>
		    </div>
		  </div>
		</div>
		


<!-- 신고하기폼(모달창) -->
		<div class="modal fade" id="reportForm" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalToggleLabel">신고하기</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      
		           <div>신고하기 사유 확인이 불가할 경우 해당 신고건은 미조치됩니다.</div>
		          
						<div class="form-check">
			              <label class="form-check-label">
			                <input type="radio" class="form-check-input" name="rprt" value="add">서비스와 관련없는 광고/홍보
			              </label>
			            </div>
			            <div class="form-check">
			              <label class="form-check-label">
			                <input type="radio" class="form-check-input" name="rprt" value="assert">부적절한 언어(욕설/성희롱 등) 사용
			              </label>
			            </div>
			            <div>서비스 진행중 문제가 생겼다면 증빙자료(대화내역, 문자메세지, 녹취, 사진 등)과 함께 숨고 이메일 admin1@aljdream.com으로 접수해주세요. 신고 결과는 별도안내되지 않습니다.</div>
		            
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" onclick="showConfirmRprt()">신고접수</button>
		        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<!-- 신고 확인(모달창) -->
		<div class="modal fade" id="confirm" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		    
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalToggleLabel2">알림사항</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		        	한번 접수한 신고내용은 수정이나 삭제가 불가합니다. 이 신고내용을 접수하시겠습니까?
		      </div>
		      
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="sendReport">네, 확인했습니다</button>
		        <button type="button" class="btn btn-secondary" data-bs-target="#reportForm" data-bs-toggle="modal" onclick="doubleCheck()" id="doubleChk">한번 더 확인하겠습니다</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- --------------------------------------------------------------------------------------------------------------------------- -->


</body>

<%@ include file="/WEB-INF/views/chat/pageset/chatroompageset.jsp"%>
</html>