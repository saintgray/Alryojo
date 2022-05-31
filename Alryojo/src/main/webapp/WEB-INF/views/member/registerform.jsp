<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/registerform.css">
<title>회원가입</title>
</head>
<body>
<%@ include file="/WEB-INF/views/defaultpageset.jsp" %>


	
	
<div class="gw">

	<!-- Header -->
	<%@ include file="/WEB-INF/views/layout/header.jsp" %>

	<!-- Register body area -->
	<div id="regGlobalWrap" class="container">
		<div class="banner">
			관심분야를 선택하세요!
		</div>
		
	
		<div id="basicInfos">
		
			    <div class="d-flex flex-column justify-content-center" id="infoswrap">
					<div>
				        <div class="input-group mt-4" id="emailarea">
				            <input type="text" name="m_email_prefix" class="form-control" placeholder="이메일" aria-label="Username">
				            <span class="input-group-text">@</span>
				            <input type="text" name="m_email_suffix" class="form-control" placeholder="examples.com" aria-label="Server">
				            
				            <button  type="button" class="btn btn-grey" id="authemailbtn">인증번호 발송</button>
				            
				            
				        </div>
				        <div class="input-group mt-2" id="autharea">
				        <!-- 	<input type="text" class="form-control" placeholder="인증번호" aria-label="인증번호" aria-describedby="button-addon2">
		  					<button class="btn btn-grey" type="button" id="button-addon2">인증</button> -->
				        </div>
				        <div class="mb-2 warningmsg" id="w_email">테스트</div>
				     </div>
			     
			     	<div>
			        
			        
				        <div class="input-group mt-2">
				            <input type="password" name="m_password" class="form-control" placeholder="비밀번호" aria-label="Password" id="pw">
				        </div>
				        <div class="mb-2 warningmsg" id="w_pw">테스트</div>
			    	 </div>
			     
			     
				     <div>
				        <div class="input-group mt-2">
				            <input type="password" class="form-control" placeholder="비밀번호 확인" aria-label="Password" id="repw">
				        </div>
				        <div class="mb-2 warningmsg" id="w_repw">테스트</div>
				     </div>
			        
				     <div>
				        <div class="input-group mt-2">
				            <input type="text" name="m_nm" class="form-control" placeholder="닉네임" aria-label="Name" id="m_nm">
				        </div>
				        <div class="mb-2 warningmsg" id="w_nick">테스트</div>
				     </div>
				     
				     	
			        <div id="userinterestselect" class="hidden">
			       	
			       		<!-- 선택한 관심사의 코드번호가 있는 영역 -->
			        	
			        </div>
			         
			        
			        <div>
			        	<div class="descript mb-2">지역을 선택해주세요</div>
			        	<div class="d-flex flex-wrap justify-content-around" id="locarea">
			        		
			        	</div>      
			        </div>
			        	      
				
					<div>
						<div class="descript my-4">프로필 사진</div>
						<div class="d-flex flex-row justify-content-around" id="profilearea">
							<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member/defaultprofile.png" id="preview">
							<div id="insertedProfile">
								<label for="myprofile" class="btn btn-general">	
									사진등록
								</label>
								<input type="file" id="myprofile" class="hidden">
								<button type="button" class="btn btn-grey" id="usedefault">기본 이미지 사용</button>
							</div>
						</div>
					</div>
				
					<div id="regbtncontroller" class="d-flex flex-row justify-content-around">
						
						<button  type="button" class="btn btn-general" id="registerbtn">회원가입</button>
					</div>
				</div>
				
		</div>
		
		
		<div id="interestInfosWrap">
			<div class="d-flex flex-column justify-content-around" id="interestInfos">
			
			
				<!-- 
				
				DB 에서 카테고리를 가져와서 each 문을 사용하여 이 밑의 내용을 출력한다. 
				
				<div class="input-group">
					<label for="interest">
						<img src="${pageContext.request.contextPath}/resources/files/server/icons/check_off.svg">
						<span class="hidden">DB에서 카테고리 타입이 취미인 모든 카테고리를 가져와서 이 span 태그에 그 카테고리의 idx를 넣는다.</span>
						<span> DB에서 카테고리 타입이 취미인 모든 카테고리를 가져와서 이 span 태그에 그 카테고리의 이름을 넣는다</span>
					</label>
					<input type="checkbox" id="interest" name="interest" class="hidden interest" value="DB에서 가져온 카테고리 idx">
					
					
				</div>
				
				-->
			
			</div>
			
			<div class="d-flex flex-row justify-content-around" id="btncontroller">
				<button type="button" class="btn btn-grey" id="skip">건너뛰기</button>
				<button type="button" class="btn btn-general" id="next">다음</button>
			</div>
			
		</div>
		
		
		
		
		
		
		
		
	
	</div>

</div>

<%@include file="/WEB-INF/views/layout/footer.jsp" %>
<%@include file="/WEB-INF/views/member/pageset/registerformpageset.jsp" %>
</body>



</html>