<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
function deletenotice(idx){
	
	if(confirm('글이 삭제됩니다. 계속 진행하시겠습니까?')){
		
		$.ajax({
			url: '${pageContext.request.contextPath}/notice/delete',
			type: 'notice',
			data: {notice_idx : idx},
			success: function(data){
				if(data==1){
					alert('게시글이 삭제되었습니다.');
					location.href="${pageContext.request.contextPath}/notice";//파라미터추가?				
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
	$('#bigImage').html('<img src="${pageContext.request.contextPath}/notice/display?fileName='+fileName+'" class="img-thumbnail">');
}


	
	$('#attachedfiles').on("click", "li", function(event){
		
		var liObj = $(this);
		console.log(liObj);
		
		var file_nm = $(this).data('file_nm');
		var exet = $(this).data('exet');
		
		console.log("file_nm", file_nm);
		console.log("exet", exet);
		var fileName = file_nm+"."+exet;
		var path = "${pageContext.request.contextPath}/resources/files/notice/attachfiles/"+fileName;
		
		// pdf 파일의 경우 다운로드
 		if(exet == "pdf"){
 		
 			self.location="${pageContext.request.contextPath}/notice/download?fileName="+fileName;

 		} else {
 		// 이미지의 경우 원본 출력
 			showImage(fileName);
 			
		}

	})
	
	// 원본 이미지를 클릭하면 div를 숨긴다.
	$('#imageWrapper').on("click", function(e){
		$('#imageWrapper').hide();
	});
	
});
</script>