<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>

//채팅관련 ------------------------------------------------------------------------------
//websocket을 지정한 URL로 연결
var sock = new SockJS("<c:url value="/echo"/>");
sock.onopen = onOpen;
sock.onmessage = onMessage;
sock.onclose = onClose;
sock.onerror = onError;





$(document).ready(function(){
	// 종현 추가
	// 채팅창의 스크롤을 맨 하단으로 내려 최신 메시지를 바로 볼 수 있도록 한다.
	// 이 스크립트는 내가 이전 메시지를 확인하다가 메시지를 전송했을 때도 스크롤을 자동으로 내릴 수 있도록 추가해준다.
	$('#chatBox').animate({scrollTop:$('#chatBox')[0].scrollHeight},500);
	
	// 종현 추가
	// 채팅 입력후 엔터키 누르면 자동으로 전송되도록 한다.
	$('#message').on('keydown',function(e){
		
		if(e.keyCode==13){
			$('#sendBtn').trigger('click');
		}
	})
	
	var myname=$('#myname').text();
	console.log(myname);
	
	// 메세지를 보낼 때
	$('#sendBtn').on('click',function(evt){
		
		console.log('메세지 버튼 클릭');
		sendMessage();	// 메세지 보내는 함수 실행
		
		$('#message').val('');	
		$('#message').focus();
	})
	
	
	// 채팅 나가기 누를때
	 $('#leaveChat').on('click',function(){
		
		$.ajax({
			url : '${pageContext.request.contextPath}/chat/leavechat',
			type : 'POST',
			data : {
				matchidx : ${matchidx},
				myidx : ${myidx},
				reciever : ${reciever},
				mytype : '${mytype}'
			},
			success : function(data){
				if(data==1){
					var mngMsg = $('#myname').text()+'님이 채팅방을 나가셨습니다.';
					sendMngMessage(mngMsg);
					
					location.href="${pageContext.request.contextPath}/chat/chatlist";
					//성공했을때 > 채팅목록으로 나간다
				}else{
					//에러있을때
					alert('오류입니다.');
				}
			}, 
			error : function(){
				console.log('비동기통신 오류');
			}
		})
	})
	
	
	// 매칭하기 확인 누를때
	$("#matchY").on('click',function(){
		
		
		// ajax로 matchyn y으로 update
		var matchYN = 'Y';
		
		matchUpdate(matchYN);
		
		document.getElementById("match").style.display="none";
		document.getElementById("unmatch").style.display="inline-block"; 
		
		// ${reciever}님과 ${myidx}님이 매칭되었습니다! 화면에 채팅으로 올라간다(중앙에)
		var mngMsg = $('#myname').text()+'님과 ${recieverInfo.m_nm} 님의 매칭이 성사되었습니다!';
		console.log(mngMsg);
		
		sendMngMessage(mngMsg);
	})
	
	
	// 매칭취소 확인 누를때
	$('#matchCancleY').on('click', function(){
		
		var matchYN = 'N';
		
		matchUpdate(matchYN);
		
		document.getElementById("match").style.display="inline-block";
		document.getElementById("match").disabled = true;
		document.getElementById("unmatch").style.display="none";
		
		// ${reciever}님과  ${myidx}님의 매칭이 취소되었습니다. 화면에 채팅으로 올라간다(중앙에)
		var mngMsg = $('#myname').text()+'님과 ${recieverInfo.m_nm} 님의 매칭이 취소되었습니다. 더이상의 매칭이 불가합니다.';
		console.log(mngMsg);
		
		sendMngMessage(mngMsg);
	})
})
	  
	// 연결되었을 때
	function onOpen(){
		console.log('Info : connection opened');
	};
	
	// 메세지 보낼때
	function sendMessage() {
		//websocket으로 메시지를 보내기
		
		// 현재 시간
		var today = new Date();
		var todaysMnth = today.getMonth()+1;
		console.log(todaysMnth);
		var time = todaysMnth+"월 "+today.getDate()+"일 " +today.getHours() + ":" + today.getMinutes();
		
		var msg = {
			reciever : ${reciever},
			matchidx : ${matchidx},
			message : $("#message").val(),
			sent : time
		};
		console.log(msg);
		console.log('sendMessage');
		sock.send(JSON.stringify(msg));
	};
	
	 // 시스템메세지
	 function sendMngMessage(mngMsg) {
		
		var sysMsg ='###aljdream###'+mngMsg;
		console.log(sysMsg);
		// 현재 시간
		var today = new Date();
		var todaysMnth = today.getMonth()+1;
		console.log(todaysMnth);
		var time = todaysMnth+"월 "+today.getDate()+"일 " +today.getHours() + ":" + today.getMinutes();
		
		var msg = {
			reciever : ${reciever},
			matchidx : ${matchidx},
			// 시스템 상 메세지는 특정 문자열을 넣어서 진행하도록한다
			message : sysMsg,
			sent : time
		};
		console.log(msg);
		console.log('매니저메세지발송');
		sock.send(JSON.stringify(msg));
	};
	

	// 리뷰 등록시
	 $('#regReview').on('click',function(){
		 
		if(!($('input[name=stars]:checked').length > 0)) {
			
			// 별점이 체크되지않았다면 $('#rate').prop('checked')
		    console.log(stars);
		  alert('리뷰작성시 별점은 필수입니다. 확인해주세요!');
		}
		var stars = $('#rate:checked').val();
		var comment = $('#comment').val();
		console.log('별점 : '+stars);
		console.log('후기 : '+comment);
		$.ajax({
			url : '${pageContext.request.contextPath}/review/regreview',
			type : 'POST',
			data : {
				rating : stars,
				comment : comment,
				matchidx : ${matchidx}
			},
			success : function(data){
				if(data==1){
					alert('리뷰가 등록되었습니다');
					document.getElementById("reviewing").disabled = true;
				}else{
					//에러있을때
					alert('오류입니다.');
				}
			}, 
			error : function(){
				console.log('비동기통신 오류');
			}
		})	
		
	});
	
	
	// 메세지 받을 때
	function onMessage(evt) { 
		console.log('onMessage');
		var data = evt.data; 	// 전달받은 데이터이다.
		console.log(data);
		
		// -----------------------------------------------
		msgData = JSON.parse(data); 
		
		var sessionid = null;
		var message = null;
		
		// 현재 세션아이디 //	
		var currentuser_session = ${myidx};					
		var recievernm = '${recieverInfo.m_nm}';
		console.log('current session id: ' + currentuser_session);
			
		// 1차로, 받은메세지가 시스템메세지인지아닌지 확인한다.
		var msg = msgData.message;
		var sysdistinct = msg.slice(0,14);
		
		if(sysdistinct =='###aljdream###'){
			
			// 매니저 메세지를 송출
			var systemMessage = msg.slice(14);
			console.log(systemMessage);
			var printHTML = "<div class='well text_center'>";
			printHTML += "<span>"+msgData.sent+"</span>";
			printHTML += "<span id='sysmsg' class='rounded-pill'><strong>" +systemMessage+ "<strong></span>";
			printHTML += "</div>";
			$('#chatBox').append(printHTML);
			
		}else{
			// 회원메세지를 송출
			// 나와 상대방이 보낸 메세지를 구분하여 출력
			if (msgData.m_sender == currentuser_session) {			// sender와 내 세션아이디가 같다면 내가보내는 것
				// 오른쪽에 출력되도록하기
				var printHTML = "<div class='well text_right'>";
				printHTML += "<span>"+msgData.sent+"</span>";
				printHTML += "<span id='sendermsg' class='rounded-pill'><strong>" +msgData.message+"<strong></span>";
				printHTML += "</div>";
	
				$('#chatBox').append(printHTML);
				
				// 종현 추가
				// 스크롤 위치 자동으로 내리기
				$('#chatBox').animate({scrollTop:$('#chatBox')[0].scrollHeight},500);
				
			} else {
				var printHTML = "<div class='well text_left'>";
				printHTML += "<span id='recieverphoto'><img src='https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/member${recieverInfo.m_photo}' class='m_photo' /></span>";
				printHTML += "<span id='recievermsg' class='rounded-pill'>" + msgData.message +"</span>";
				printHTML += "<span>"+msgData.sent+"</span>";
				printHTML += "</div>";
				
				$('#chatBox').append(printHTML);
			}
		} 
		console.log('chatting data: ' + data);
		
	};
	
	function onClose(evt) {
		$("#data").append("연결 끊김");
	};
	
	function onError(err){
		console.log('Error:', err);
	};
//채팅관련 끝------------------------------------------------------------------------------
	  
	  
	// match여부 업데이트할 시
	function matchUpdate(matchYN){
		
		console.log(matchYN);		//넘어오는것 확인했습니다.
		console.log(${matchidx});	//넘어오는것 확인했습니다.
		$.ajax({
			url : '${pageContext.request.contextPath}/chat/matchupdate',
			type : 'post',
			data : {
				matchYN : matchYN,
				matchidx : ${matchidx}
				},
			success : function(data){
				if(data==1){
					console.log('match 업데이트 성공');
				}else{
					console.log('match 업데이트 실패');
				}
			}, 
			error : function(){
				console.log('비동기통신 오류');
			}
		})
		
	};
	
	// 리뷰버튼 눌렀을 때
	function review(){
		<!-- 후기등록 조건 : match_yn(매칭여부) Y && unmatchYN(매칭취소가능여부)N -->
		<!-- 후기등록 조건 : ableRv(작성가능여부)Y -->
		
		if(!((${match.match_yn eq 'Y'}) && (${unmatchYN eq 'N'}))){
			alert('후기 등록은 매칭 후 매칭일 3일이 지나고부터 가능합니다.');
		}else if(!${ableRv eq'Y'}){
			alert('이미 후기를 등록한 매칭입니다. 후기 등록은 한번만 가능합니다.');
		}else{
			$('#reviewform').modal('show');
		}
	};
	
	
	// 별점 작성시 함수
	$(':radio').change(function() {
		  console.log('New star rating: ' + this.value);
		});
	
	
	
	// 신고접수하기 눌렀을 때
	function showConfirmRprt(){
		console.log($('input[name=rprt]:checked').length);
		if(!($('input[name=rprt]:checked').length>0)) {
			// 체크되지않았다면
		  alert('접수할 내용을 체크하지 않으셨습니다. 신고내용을 체크해주세요!');
		}else{
			// 확인 모달 보여줌
			$('#confirm').modal('show');
			$('#reportForm').modal('hide');
		}
	}
	
  	
	// 신고접수를 눌렀을 시
	 $("#sendReport").click(function(){
		var content = $('.form-check-input:checked').val();
		console.log(content);
	 	
		// 먼저 이 매칭에서 신고한 적이 없는지 확인
	 	if(!${ableRprt eq 'Y'}){
			 alert('신고는 한 채팅방에 한번만 가능합니다. 기타 문의는 알려드림 이메일 admin1@aljdream.com으로 접수바랍니다.');
		}else{
			console.log('하루최대치전');
			chkTodayRprt(content);
		}
	});
	
	// 신고접수시 한번더확인하겠다고 할 때
	function doubleCheck(){
		$('#confirm').modal('hide');
		
	}
	
	// 하루 신고 최대치를 넘지않았는지 확인
	function chkTodayRprt(content){
		console.log('하루최대치');
		$.ajax({
			url : '${pageContext.request.contextPath}/report/maxrprt',
			type : 'post',
			data : {
				myidx : ${myidx}
			},
			success : function(data){
				if(data==1){
					console.log('신고접수 가능');
					reporting(content);
				}else{
					$('#reportForm').modal('hide');	
					alert('신고는 하루 최대 5번까지만 가능합니다.');
				}
			}, 
			error : function(){
				console.log('비동기통신 오류');
			}
		})
	};
	
	// 신고내용 전송
	function reporting(content){
		console.log(content);
		$.ajax({
			url : '${pageContext.request.contextPath}/report/sendreport',
			type : 'POST',
			data : {
					match_idx : ${matchidx},
					report_content : content,
					m_report : ${myidx},
					m_reported : ${reciever}
					},
					
			success : function(data){
				// 전송에 성공하면 실행될 코드
				if(data==1){	
					$('#reportForm').modal('hide');														
					alert('신고접수가 완료되었습니다.');
					document.getElementById("report").disabled = true;
				} else {
					alert('전송오류');
				}
			}, 
			error : function(){
				console.log('비동기통신 오류');
			}
		})
	};
		
	
	

	
	
</script>