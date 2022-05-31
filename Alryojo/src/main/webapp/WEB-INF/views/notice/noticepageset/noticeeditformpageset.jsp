<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	$('.notice_content').summernote({
		toolbar: [
		    // [groupName, [list of button]]
		    ['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    /* ['color', ['color']], */
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert', ['link', 'picture', 'video']],
		  ],
		 
		 minHeight: 500,
		 maxHeight: 500,
         codemirror: {
        	 theme: 'monokai'
         },
         lang: 'ko-KR',
         placeholder: '내용'	
	})
	
	$('#editProcessbtn').on('click',function(){
		

		$.ajax({
			
			url: '${pageContext.request.contextPath}/admin/notice/edit',
			type:'POST',
			// 보낼 data : form 태그 내에 있는 모든 name 속성 요소의 값을 파라미터로 만들어줌
			data:{
				notice_title: $('#notice_title').val(),
				notice_content:$('.notice_content').summernote('code'),
				notice_idx:${param.notice_idx}
			},
			success: function(data){
				if(data==1){
					alert('정상적으로 수정되었습니다');
					
					//location.href="${pageContext.request.contextPath}/notice/content?notice_idx=${noticeContent.notice_idx}&selectPage=${param.selectPage}&numOfNoticesPerPage=${param.numOfNoticesPerPage}";
					history.go(-2);
				}else{
					alert('오류가 발생하였습니다. 잠시 후 다시 시도하세요');
				}
			},
			error: function(data){
				console.log(data);
			}
		})
	})
	
	$('#cancelbtn').click(
	      	function(){
	      	//location.href="${pageContext.request.contextPath}/notice?selectPage=${param.selectPage}&numOfNoticesPerPage=${param.numOfNoticesPerPage}";
	      	history.go(-2);
	      	});
})
</script>