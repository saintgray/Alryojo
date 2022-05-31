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
    border-radius: 6px;
	}
	#reportedarea{
		margin-top: 150px;
	}
	.reportele{
		border-radius: 15px;
		background-color: #E1DEE6;
		position: relative;
		padding:10px;
		padding-top: 25px;
	}
	.reportele img{
		width:45px;
		height:45px;
		border-radius: 50%;
		
	}
	.reportele button{
	    font-size: 0.3rem;
   	    padding: 0.26rem;
	}
	.chathistory{
		position: absolute;
		top:5px;
		right:5px;
	}
</style>
<title>신고내용</title>
</head>
<body>

<div class="gw">

<%@include file="/WEB-INF/views/layout/header.jsp" %>

	
	<div class="container article" id="reportedarea">
	
	
	<c:if test="${empty list}">
		<h1 class="text-center">처리할 신고 내역이 없습니다</h1>
	</c:if>
	
		
		<c:if test="${not empty list}">
			<div class="d-flex flex-wrap">
		
				<c:forEach var="item" items="${list}">
					<div class="reportele d-flex flex-row">
						<span class="d-none mat-idx">${item.match_idx}</span>
						<span class="d-none m_report">${item.m_report}</span>
						<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${item.m_photo}" class="mx-2"/>
						<div class="text-center">
							<p class="my-2">${item.m_email}</p>
							<span class="">${item.report_content}</span>
							<div class="text-end mt-2">
								<button type="button" class="btn btn-grey withdraw">철회</button>
								<button type="button" class="btn btn-general accpet">확인</button>
							</div>
						</div>
						
						<button type="button" class="btn btn-dark rounded-pill chathistory" data-bs-toggle="modal" data-bs-target="#md-chat">채팅내역보기</button>
						
					</div>
				</c:forEach>
				
			</div>
		</c:if>
	</div>
	
	
	
	<!-- 신고에 대한 채팅목록 확인 modal 창 -->
	<div class="modal fade" id="md-chat" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">채팅이력</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        
		      </div>
		      
		    </div>
		</div>	
	</div>

</div>	
<%@include file="/WEB-INF/views/layout/footer.jsp" %>
</body>


<script>
	$(document).ready(function(){
		
		
		
		$('#reportedarea').on('click','.chathistory',function(){
			
			
			var matchidx=$(this).siblings('.mat-idx').text();
			var m_report=$(this).siblings('.m_report').text();
		
			/* console.log(matchidx);
			console.log(myidx);
			console.log(reciever); */
		
			$.ajax({
				url:'${pageContext.request.contextPath}/chat/room/'+matchidx,
				type:'GET',
				data: {matchidx:matchidx},
				success:function(data){
					console.log('통신성공');
					console.log(data);
					
					var chat='';
					
					
					$(data).each(function(index, item){
						if(item.m_sender != ${param.m_idx}){
							chat+='<div class="ps-2 my-2">'+item.message+'\r\n';
							chat+='<span class="descript">'+item.sent+'</span>\r\n';
							chat+='</div>\r\n';
						}else if(item.m_sender == ${param.m_idx}){
							chat+='<div class="pe-2 my-2 text-end" style="color:red">'+item.message+'\r\n';
							chat+='<span class="descript">'+item.sent+'</span>\r\n';
							chat+='</div>\r\n';
						}
					})
					
					$('.modal-body').html(chat);
				},
				error:function(data){
					console.log('통신실패');
					console.log(data);
					
				}
				
				
			})
			
		})
		
	})

</script>

</html>