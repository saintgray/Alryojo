<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function deletePost(idx){
	
	if(confirm('글이 삭제됩니다. 계속 진행하시겠습니까?')){
		
		$.ajax({
			url: '${pageContext.request.contextPath}/post/delete',
			type: 'post',
			data: {post_idx : idx},
			success: function(data){
				if(data==1){
					alert('게시글이 삭제되었습니다.');
					location.href="${pageContext.request.contextPath}/post/list";
				} else {
					console.log('삭제 실패');
				}
			},
			error: function(){
				console.log('비동기 통신 오류');
			}
		});
		
	}
	
}

function showImage(fileName){
	console.log(fileName);
	$('#imageWrapper').css("display", "flex").show();
	$('#bigImage').html('<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/post/attachfiles/'+fileName+'" class="img-thumbnail">');
}

$(document).ready(function(){
	

	var myname=$('#myname').text();
	console.log(myname);

	// 문의하기 눌렀을 시 멘토멘티 타입확인
	$('#matchBtn').click(function(){	
		
		var mentor = "mentor";
		var mentee = "mentee";	
		var mytype = $('#mytype').val();
		console.log(mytype);
		var myidx = $('#myidx').val();
		console.log(myidx);
		console.log('확인중');
		if(mytype == mentor){
			//멘토면프로필을 확인한다 > 프로필 있을경우 그대로 진행 , 없으면 프로필을 작성해주세요
			console.log('멘토');
			$.ajax({
				url : '${pageContext.request.contextPath}/post/profilechk',
				type : 'post',
				data : {myidx : myidx},
				success : function(data){
					console.log(data);
					if(!data.length==0){
						console.log("멘토확인");
						$('#requestYN').modal('show');
					}else if(data.length==0){

						// 프로필작성하기로 보내기
						alert('작성된 프로필이 없습니다. 프로필을 먼저 등록해주세요!');
						location.href = '${pageContext.request.contextPath}/member/profile/main';
					}
				}
			})
		}else if(mytype == mentee) {
			console.log("멘티확인");
			$('#requestYN').modal('show');
		}
	})
	
	// 문의하기 매칭테이블 유무 체크하기
	$('#rqYes').on('click', function(){
		
		var postidx=$('#postidx').val();
		var wanted=$('#wanted').val();
		var midx=$('#midx').val();
		var myidx=$('#myidx').val();
		
		console.log('postidx:', postidx);
		console.log('wanted:', wanted);
		console.log('midx:', midx);
		console.log('myidx:', myidx);
		
		$.ajax({
			url: "${pageContext.request.contextPath}/post/matchchk",
			type: "post",
			data: {
				postidx : postidx,
				midx : midx,
				myidx : myidx,
				wanted: wanted
			},
			success : function(data){
				if(data==1){
					alert('이미 문의한 게시글입니다. 내 채팅목록을 확인해주세요!');
				}else if(data==2) {
					console.log('insertMatch오류');
				}else{
					// 문의하기 가능 : 시스템메세지 보낸 후 > 채팅모달 띄움
					sysMsg();
				}
			},
			error: function(data){
				console.log('통신 오류');
				console.log(data);
			}
		});
		
	})
	
	

	// 문의 후 시스템 메세지 자동으로 보내지게하기
	function sysMsg(){
		var msg = '###aljdream###'+$('#myname').val()+'님에게서 문의가 도착했습니다. 자유롭게 대화해보세요!';

		sysMsgYN = 'Y';
		console.log(msg);
		
		isnertChat(msg, sysMsgYN);
		// 채팅모달 띄움
		$('#sendchat').modal('show');
	} 
	
	// 채팅 입력했을 시

	$('#msgBtn').click(function(){
		
		// 채팅창이 비어있을 경우 경고
		// 안비어있을 경우에만 데이터전송

		
		var msg = $('.msg').val();
		console.log(msg);
		var sysMsgYN = 'N';
		
		if(!(msg.length>0)){
			alert('채팅 입력내용이 없습니다. 확인해주세요.')
		}else{
			isnertChat(msg, sysMsgYN);

			$('#sendchat').modal('hide');

			alert('채팅을 전송했습니다! 채팅내용은 내 채팅목록에서 확인해주세요.');
		}
	})
	
	
	// chat을 insert하는 메소드
	function isnertChat(msg, sysMsgYN){
		
		console.log('insertChat메소드 들어옴');
		
		var mytype = $('#mytype').val();
		var postidx=$('#postidx').val();
		var midx=$('#midx').val();
		var myidx=$('#myidx').val();

		console.log(mytype);
		console.log(postidx);
		console.log(midx);
		console.log(myidx);
		console.log('insertChat : ajax전송전');
		
		$.ajax({
			url : '${pageContext.request.contextPath}/chat/insertchat',
			type : 'post',
			data : {
			postidx 	: postidx,
			m_reciever	: midx,
			m_sender	: myidx,
			mytype		: mytype,
			msg 		: msg,
			sysMsgYN	: sysMsgYN
			},
			success : function(data){
				// 전송에 성공하면 실행될 코드
				if(data==1){	

					console.log('insertchat성공')
				} else {
					alert('전송오류');
				}
			}
		})
	
	}
	
		
	$('#attachedfiles').on("click", "li", function(event){
		
		var liObj = $(this);
		console.log(liObj);
		
		var file_nm = $(this).data('file_nm');
		var exet = $(this).data('exet');
		
		console.log("file_nm", file_nm);
		console.log("exet", exet);
		var fileName = file_nm+"."+exet;
		var path = "${pageContext.request.contextPath}/resources/files/post/attachfiles/"+fileName;
		
 		if(exet != "pdf"){
 		
 		// 이미지의 경우 원본 크기 출력
 			showImage(fileName);
		}

	})
	
	// 원본 이미지를 클릭하면 div를 숨긴다.
	$('#imageWrapper').on("click", function(e){
		$('#imageWrapper').hide();
	});
	
});
</script>