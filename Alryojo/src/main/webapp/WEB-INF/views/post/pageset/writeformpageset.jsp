<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<script>
let cat_idx;

function resetCategory(){
	$('#categoryInfos').html('');
	cat_idx=0;
		
	selectCategory();
}

function selectCategory(dom){
	
	var interest = $(dom).children('option:selected').val();

	$.ajax({
		url: '${pageContext.request.contextPath}/category/list',
		type: 'GET',
		data: {interest : interest},
		datatype: 'json',
		success: function(data){
			
			// remove all next Siblings		
			$(dom).parent().nextAll().remove();
			
			if(data.length==0){
				
				// 현재 선택한 interest 값을 cat_idx 에 저장한다.
				cat_idx = interest;
				console.log(cat_idx);
				
			}else{
			
				var html="";
				html+='<div class="selectarea mx-1">\r\n';
				html += '<select class="form-select" onchange="selectCategory(this)">\r\n';
				html += '<option value="">선택</option>\r\n';
				
				$(data).each(function(index, items){
					console.log(items.cat_idx);
					console.log(items.cat_nm);
					
					html += '<option value="'+items.cat_idx+'">'+items.cat_nm+'</option>';
				
				})
				html += '</select>\r\n';
				html+='</div>'
				
				$('#categoryInfos').append(html);
				console.log(html);
			
			}
			
		},
		error: function(data){
			console.log(data);
		}
	});
	
}

// 파일업로드 관련 : 용량 제한, 확장자 제한
function checkExtension(fileName, fileSize){
	
	// var parts = fileName.split('.');
	// var ext = parts[parts.length-1].toLowerCase();
	
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
	
	var contentForm = $('#content');
	contentForm.html('<p><b>1. 희망 수업 방식</b> : 개인 / 그룹 / 화상 / 무관 / 기타 (직접 입력)</p><p><b>2. 희망 진행 장소</b> :&nbsp;</p><p><b>3. 자세한 설명</b> :&nbsp;</p>');
	
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
	
	// ROOT 카테고리의 인덱스인 30을 넣어 카테고리 검색 초기화
	selectCategory(30);	
        
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
					html += '<button type="button" data-file_nm="'+items.file_nm+'" data-originnm="'+items.file_originnm+'" data-exet="'+items.file_exet+'" data-size="'+items.file_size+'" class="btn btn-warning btn-circle">X</button>\r\n';

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
	$('#uploadResult').on("click", "button", function(e){
		console.log('파일 삭제를 시작합니다.');
		
		var file_nm = $(this).data('file_nm');
		var file_exet = $(this).data('exet');
		var file_originnm = $(this).data('originnm')+'.'+file_exet;
		
		console.log('file_nm:', file_nm);
		console.log('file_exet:', file_exet);
		console.log('file_originnm:', file_originnm);
		
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url: '${pageContext.request.contextPath}/post/deleteFile',
			type: 'post',
			data: {file_nm : file_nm, file_exet : file_exet},
			dataType: 'text',
			success: function(result){
				alert(result);
				targetLi.remove();
				
				// attachfiles 에서 제거
				var attachfiles = formData.getAll('attachfiles');
				console.log('삭제전:', attachfiles);
				console.log('삭제대상:', file_originnm);
				
				for(var i=0; i<attachfiles.length; i++){
					console.log(attachfiles[i]);
					
					if(attachfiles[i].name === file_originnm){
						attachfiles.splice(i,1);
						console.log('삭제');
						i--;
					}
				}
				
				console.log('삭제후:', attachfiles);
				formData.set('attachfiles', attachfiles);
			}
		});
	})

	$("#submitBtn").on("click", function(e){

		// 첨부파일 정보 등록
		var html='';
		
		$('#uploadResult button').each(function(index, items){
			var file_nm = $(this).data('file_nm');
			var file_exet = $(this).data('exet');
			var file_size = $(this).data('size');
			var file_originnm = $(this).data('originnm');
			
			html+='<input type="hidden" name="fileList['+index+'].file_nm" value="'+file_nm+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].post_idx" value="0">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_exet" value="'+file_exet+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_size" value="'+file_size+'">\r\n';
			html+='<input type="hidden" name="fileList['+index+'].file_originnm" value="'+file_originnm+'">\r\n';
		});
		
		$('#uploadList').append(html);
		
		var formObj = new FormData(document.getElementById('writePost'));
		
		var post_nm = $('#post_nm').val();
	    var content = $('#content').summernote('code');
	    /* var cat_idx = $('#cat_idx').val(); */
	    
	    formObj.set("post_nm", post_nm);
	    formObj.set("post_content", content);
	    formObj.set("cat_idx", cat_idx);
		
		if(!cat_idx>0){
			alert('분야를 반드시 선택하셔야 합니다.');
			
			$('#uploadList').empty();
			
			return;
		}
		
		/* key 확인하기 */
		for (let key of formObj.keys()) {
			   console.log(key);
		}

		/* value 확인하기 */
		for (let value of formObj.values()) {
		      console.log(value);
		}
		
		
		$.ajax({
			url: '${pageContext.request.contextPath}/post/write',
			type: 'post',
			data: formObj,
			success: function(result){
				
				console.log(result);
				if(result.cnt==1){
					alert('요청글을 등록하였습니다.');
					location.href='${pageContext.request.contextPath}/post/view?idx='+result.idx;
				} else {
					console.log('등록 실패');
				}
			},
			error: function(){
				console.log('통신 오류');
			},
			processData: false,
			contentType: false
		});
		
	});
	
});

</script>