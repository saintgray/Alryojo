<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	$('#regBtn').on('click',function(){
		
		
		var data={
				
				qna_title:$('#title').val().trim(),
				qna_content:$('#content').summernote('code')
				
				
		}
		
		$.ajax({
			url:'${pageContext.request.contextPath}/member/qna/register',
			type:'POST',
			data: data,
			success:function(data){
				if(data==1){
					alert('문의글이 등록되었습니다');
					location.href='${pageContext.request.contextPath}/qna/list';
					
				}else{
					alert('오류가 발생했습니다 잠시 후 다시 시도하세요');
				}
			},
			error:function(data){
				console.log(data);
				alert('오류가 발생했습니다 잠시 후 다시 시도하세요');
			}
			
		})
		
		
		
		
	})
	
	
	$('#sm_qna').css('color','#FCA106');
	
	
	$('#content').summernote({
		toolbar : [
					['style',[
								'bold',
								'italic',
								'underline',
								'clear' 
							] 
					],
					['font',[
								'strikethrough',
								'superscript',
								'subscript' 
							] 
					],
				[ 'fontsize',
						[ 'fontsize' ] ],
				/* ['color', ['color']], */
				[
						'para',
						[ 'ul', 'ol',
								'paragraph' ] ],
				[ 'height', [ 'height' ] ],
				[
						'insert',
						[ 'link',
								'picture',
								'video' ] ], ],

		minHeight : 500,
		maxHeight : 500,
		codemirror : {
			theme : 'monokai'
		},
		lang : 'ko-KR',
		placeholder : '내용'
	})
	
	
	
	
})



</script>