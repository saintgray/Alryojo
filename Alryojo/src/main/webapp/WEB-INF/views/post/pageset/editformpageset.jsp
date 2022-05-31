<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<script>



// 파일업로드 관련 : 용량 제한, 확장자 제한
function checkExtension(fileName, fileSize){
	
	var regex = new RegExp("(.*?)\.(jpg|jpeg|png|PNG|bmp|pdf)$");
	var maxSize = 5242880; // 5MB : 1024*1024*5
	
	if(fileSize > maxSize){
		alert("5MB 이하의 파일만 업로드할 수 있습니다.");
		return false;
	}
	
	if(!regex.test(fileName)){
		alert("이미지 또는 PDF 파일만 업로드할 수 있습니다.");
		return false;
	}
	
	return true;
	
}



$(document).ready(function(){	
	
	$('#content').summernote({
		toolbar: [
		    
		    ['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['insert', ['link', 'picture', 'video']],
		  ],
		 height: 600,
         codemirror: {
        	 theme: 'monokai'
         },
         lang: 'ko-KR',
         placeholder: '내용을 입력하세요.'
         

	});
	
	/* 글 수정시 summernote에 글내용 추가 */
    //$('#content').summernote('editor.insertText', "${editRequest.post_content}");
	
	var formData = new FormData();
	  
	$("#attachFile").on("change", function(e){
		
		console.log(e.target.files);
		var inputFile = e.target.files;
		
		if(inputFile.length == 0){
		    alert("파일이 없습니다.");
		    return;
		}
				
		// 첫 업로드가 아닐 경우, 이미 등록된 파일을 재업로드하지 않기 위해 사전작업을 한다.
		if($('#uploadResult ul')){
			// 이미 등록된 미리보기를 복사한다.
			var cloneResult = $('#uploadResult ul').clone();
			
			// 이미 등록된 파일을 목록에서 지운다.
			formData.delete('attachfiles');
		}
 		
		$('#uploadResult').html('<ul class="list-group"></ul>');
		
		// 새로 등록한 파일을 attachfiles 에 추가한다.
		for(var i=0; i<inputFile.length; i++){
			if(!checkExtension(inputFile[i].name, inputFile[i].size)){
				return false;
			}
			formData.append("attachfiles", inputFile[i]);
			
		}
		console.log('attachfiles 를 formData 에 추가');
		console.log('attachfiles:', formData.getAll('attachfiles'));
		
		/* key 확인하기 */
		for (let key of formData.keys()) {
			   console.log(key);
		}

		/* value 확인하기 */
		for (let value of formData.values()) {
		      console.log(value);
		}
		
		$.ajax({
			url: '${pageContext.request.contextPath}/post/uploadfile',
			type: 'post',
			data: formData,
			dataType: 'json',
			processData: false,
			contentType: false,
			success: function(data){
				console.log(data);
				
				var list = $('#uploadResult ul');
				
				// 이미 존재하는 파일 리스트 붙여넣기
				$(list).html(cloneResult.html());
				
				$(data).each(function(index, items){
					
					var listItem = document.createElement('li');
					var fileName = items.file_nm+'.'+items.file_exet;
					
					var html = '<li class="list-group-item d-flex justify-content-between align-items-center">';
					
					if(items.file_exet=='pdf'){
						html += '<span><i class="bi bi-filetype-pdf fs-4"></i>';
					} else {
						html += '<img src="https://aljdreambucket.s3.ap-northeast-2.amazonaws.com/post/attachfiles/'+fileName+'">\r\n<span>';
					}
					
					// 파일 이름과 크기
					html += items.file_originnm+'.'+items.file_exet+' ('+items.file_size+'kb)\r\n';
					
					// 파일 삭제를 위한 버튼
					// update_status : N (New), 새로 등록하는 파일
					html += '<button type="button" data-file_nm="'+items.file_nm+'" data-originnm="'+items.file_originnm+'" data-exet="'+items.file_exet+'" data-size="'+items.file_size+'" data-update_status="N" class="btn btn-warning btn-circle">X</button>\r\n';

					html += '</span>\r\n'
					html += '</li>';
					
					console.log(html);
					
					list.append(html);
					
				});
				
				
			},
			error: function(){
				console.log('통신 오류');
			}
		});
		
		
	});
	
	// 첨부파일 삭제 이벤트
	// 수정을 완료하지 않았을 경우 실제로 파일이 삭제되지 않도록 화면에서만 삭제한다.
	$('#uploadResult').on("click", "button", function(e){
		console.log('파일 삭제를 시작합니다.');
		
		var post_idx = $('input[name=post_idx]').val();
		
		// console.log(post_idx);
		
		var file_nm = $(this).data('file_nm');
		var file_exet = $(this).data('exet');
		var file_originnm = $(this).data('originnm');
		var originnm = $(this).data('originnm')+'.'+file_exet;
		var file_size = $(this).data('size');
		
		if(confirm("파일을 삭제하시겠습니까? (" + originnm + ")")){
			
			// update_status : E (Exclude), 제외할 파일
			var html='';
				html+='<button type="button" data-file_nm="'+file_nm+'"  data-originnm="'+file_originnm+'" data-exet="'+file_exet+'" data-size="'+file_size+'" data-update_status="E" class="hide">X</button>';
			
			console.log(html);
				
			var targetLi = $(this).closest('li');
			targetLi.remove();
			
			$('#uploadResult ul').append(html);
				
		}
		
	})
	
	$("#submitBtn").on("click", function(e){

		// 첨부파일 정보 등록
		var html='';
		
		$('#uploadResult button').each(function(index, items){
			var file_nm = $(this).data('file_nm');
			var file_exet = $(this).data('exet');
			var file_size = $(this).data('size');
			var file_originnm = $(this).data('originnm');
			var post_idx = $('input[name=post_idx]').val();
			var update_status = $(this).data('update_status');
			console.log('update_status', update_status);
			
			html+='<input type="hidden" name="fileList['+index+'].file_nm" value="'+file_nm+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].post_idx" value="'+post_idx+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_exet" value="'+file_exet+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_size" value="'+file_size+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_originnm" value="'+file_originnm+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].update_status" value="'+update_status+'">\r\n';
			
			
		});
		
		$('#uploadResult').append(html);
		
		var formObj = new FormData(document.getElementById('writePost'));
		
		/* key 확인하기 */
		for (let key of formObj.keys()) {
			   console.log(key);
		}

		/* value 확인하기 */
		for (let value of formObj.values()) {
		      console.log(value);
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/post/edit',
			type : 'post',
			data : formObj,
			success : function(result) {
				console.log(result);

				if (result.cnt == 1) {
					alert('글을 수정했습니다.');
					location.href = '${pageContext.request.contextPath}/post/view?idx='+result.idx;
				} else {
					console.log('수정 실패');
				}
			},
			error : function() {
				console.log('통신 오류');
			},
			processData : false,
			contentType : false
		});
		
	});
	
	
});

</script>